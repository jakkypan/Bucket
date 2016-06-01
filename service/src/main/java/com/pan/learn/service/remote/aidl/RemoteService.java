/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.service.remote.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Process;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by panhongchao on 16/5/26.
 */
public class RemoteService extends Service {
    RemoteCallbackList<IRemoteServiceCallback> mCallbacks = new RemoteCallbackList<>();
//    List<IRemoteServiceCallback> iRemoteServiceCallbacks = new ArrayList<>();
    int mValue = 0;
    private static final int REPORT_MSG = 1;

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REPORT_MSG: {
                    int value = ++mValue;

                    final int N = mCallbacks.beginBroadcast();
//                    int N = iRemoteServiceCallbacks.size();
                    for (int i=0; i<N; i++) {
                        try {
//                            iRemoteServiceCallbacks.get(i).valueChanged(value);
                            mCallbacks.getBroadcastItem(i).valueChanged(value);
                        } catch (RemoteException e) {
                        }
                    }
                    mCallbacks.finishBroadcast();

                    sendMessageDelayed(obtainMessage(REPORT_MSG), 1000);
                } break;
                default:
                    super.handleMessage(msg);
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if (IRemoteService.class.getName().equals(intent.getAction())) {
            return mBinder;
        }
        if (ISecondary.class.getName().equals(intent.getAction())) {
            return mSecondaryBinder;
        }
        return null;
    }

    private final IRemoteService.Stub mBinder = new IRemoteService.Stub() {
        @Override
        public void registerCallback(IRemoteServiceCallback cb) throws RemoteException {
            if (cb != null) {
                mCallbacks.register(cb);
//                iRemoteServiceCallbacks.add(cb);
            }
        }

        @Override
        public void unregisterCallback(IRemoteServiceCallback cb) throws RemoteException {
            if (cb != null) {
                mCallbacks.unregister(cb);
//                iRemoteServiceCallbacks.remove(cb);
            }
        }
    };

    private final ISecondary.Stub mSecondaryBinder = new ISecondary.Stub() {
        @Override
        public int getPid() throws RemoteException {
            return Process.myPid();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler.sendEmptyMessage(REPORT_MSG);
    }

    @Override
    public void onDestroy() {
        mCallbacks.kill();
//        iRemoteServiceCallbacks.clear();
        mHandler.removeMessages(REPORT_MSG);
        super.onDestroy();
    }
}
