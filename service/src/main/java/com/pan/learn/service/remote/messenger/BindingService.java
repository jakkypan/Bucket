/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.service.remote.messenger;

import java.util.ArrayList;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Messenger 适合串行的操作
 *
 * Created by panhongchao on 16/5/25.
 */
public class BindingService extends Service {
    ArrayList<Messenger> mClients = new ArrayList<Messenger>();

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mClients.add(msg.replyTo);
                    break;
                case 100:
                    int o =  msg.arg1;
                    try {
                        mClients.get(0).send(Message.obtain(null, 100, o, 0));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case 200:
                    try {
                        Message message = Message.obtain(null, 200, 100000, 0);
//                        message.obj = "i have die!";
                        mClients.get(0).send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    Messenger messenger = new Messenger(new IncomingHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    @Override
    public void onCreate() {
        Log.e("111", "i have created.....");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("111", "onUnbind.....");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e("111", "onDestroy.....");
        super.onDestroy();
    }


}
