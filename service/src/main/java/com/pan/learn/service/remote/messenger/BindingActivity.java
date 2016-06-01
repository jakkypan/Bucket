/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.service.remote.messenger;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by panhongchao on 16/5/25.
 */
public class BindingActivity extends Activity {
    Messenger mService = null;

    final Messenger mMessenger = new Messenger(new IncomingHandler());

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    Toast.makeText(BindingActivity.this, "Received from service: " + msg.arg1, Toast.LENGTH_SHORT).show();
                    break;
                case 200:
                    Toast.makeText(BindingActivity.this, "" + msg.arg1, Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Button b = new Button(this);
        b.setText("click me");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(new Intent(BindingActivity.this, BindingService.class), connection, Service
                        .BIND_AUTO_CREATE);
            }
        });

        setContentView(b);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);

            Message msg = Message.obtain(null, 0);
            msg.replyTo = mMessenger;
            try {
                mService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            msg = Message.obtain(null, 100, this.hashCode(), 0);
            try {
                mService.send(msg);
            } catch (RemoteException e) {
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            mService = null;
        }
    };

    @Override
    protected void onDestroy() {
        unbindService(connection);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Message msg = Message.obtain(null, 200, 0, 0);
        try {
            mService.send(msg);
        } catch (RemoteException e) {
        }
    }
}
