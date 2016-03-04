/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.crashpick.common;

/**
 * Created by panhongchao on 16/3/3.
 */
import static org.acra.ACRA.LOG_TAG;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.acra.ACRA;
import org.acra.ACRAConstants;
import org.acra.CrashReportDialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

public class CrashHandler implements UncaughtExceptionHandler {
    private static final String TAG = "CrashHandler";
    private static final boolean DEBUG = true;

    private static String PATH = Environment.getExternalStorageDirectory().getPath() + "/CrashTest/log/";;
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".trace";

    private static CrashHandler sInstance;
    private UncaughtExceptionHandler mDefaultCrashHandler;
    private Context mContext;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        if (sInstance == null) {
            sInstance = new CrashHandler();
        }
        return sInstance;
    }

    public void init(Context context) {
//        PATH = getDiskCacheDir(context).getPath() + "/CrashTest/log/";
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
    }

    /**
     * 这个是最关键的函数，当程序中有未被捕获的异常，系统将会自动调用#uncaughtException方法
     * thread为出现未捕获异常的线程，ex为未捕获的异常，有了这个ex，我们就可以得到异常信息。
     */
    @Override
    public void uncaughtException(final Thread thread, final Throwable ex) {
        try {
            //导出异常信息到SD卡中
            dumpExceptionToSDCard(ex);
            uploadExceptionToServer();
            //这里可以通过网络上传异常信息到服务器，便于开发人员分析日志从而解决bug

            new Thread("crash-report") {
                @Override
                public void run() {
                    /**
                     * 弹出dialog
                     */
                    final Intent dialogIntent = new Intent(mContext, AfterCrashActivity.class);
                    dialogIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(dialogIntent);

                    /**
                     * 弹出toast
                     */
//                    Looper.prepare();
//                    try {
//                        Toast.makeText(mContext, "ooooooook!!", Toast.LENGTH_LONG).show();
//                    } catch (RuntimeException e) {
//                        ACRA.log.w(LOG_TAG, "Could not send crash Toast", e);
//                    }
//                    Looper.loop();
                }
            }.start();
        } catch (IOException e) {
            //如果系统提供了默认的异常处理器，则交给系统去结束我们的程序，否则就由我们自己结束自己
            if (mDefaultCrashHandler != null) {
                mDefaultCrashHandler.uncaughtException(thread, ex);
            } else {
                Process.killProcess(Process.myPid());
            }
        }
    }

    private void dumpExceptionToSDCard(Throwable ex) throws IOException {
        //如果SD卡不存在或无法使用，则无法把异常信息写入SD卡
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (DEBUG) {
                Log.w(TAG, "sdcard unmounted,skip dump exception");
                return;
            }
        }

        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));
        File file = new File(PATH + FILE_NAME + new Date().getTime() + FILE_NAME_SUFFIX);

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.println(time);
            dumpPhoneInfo(pw);
            pw.println();
            ex.printStackTrace(pw);
            pw.close();
        } catch (Exception e) {
            Log.e(TAG, "dump crash info failed");
        }
    }

    private void dumpPhoneInfo(PrintWriter pw) throws NameNotFoundException {
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        pw.print("App Version: ");
        pw.print(pi.versionName);
        pw.print('_');
        pw.println(pi.versionCode);

        //android版本号
        pw.print("OS Version: ");
        pw.print(Build.VERSION.RELEASE);
        pw.print("_");
        pw.println(Build.VERSION.SDK_INT);

        //手机制造商
        pw.print("Vendor: ");
        pw.println(Build.MANUFACTURER);

        //手机型号
        pw.print("Model: ");
        pw.println(Build.MODEL);

        //cpu架构
        pw.print("CPU ABI: ");
        pw.println(Build.CPU_ABI);
    }

    private void uploadExceptionToServer() {
        //TODO Upload Exception Message To Your Web Server
    }

    /**
     * crash日志写入的路径
     *
     * @param context
     * @return
     */
    public File getDiskCacheDir(Context context) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath);
    }

}
