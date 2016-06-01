/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.service.remote.aidl;

import com.pan.learn.service.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by panhongchao on 16/5/27.
 */
public class RemoteBindActivity extends Activity {
    IRemoteService mService = null;
    ISecondary mSecondaryService = null;
    Button mKillButton;
    TextView mCallbackText;
    private boolean mIsBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.remote_service_binding);

        // Watch for button clicks.
        Button button = (Button)findViewById(R.id.bind);
        button.setOnClickListener(mBindListener);
        button = (Button)findViewById(R.id.unbind);
        button.setOnClickListener(mUnbindListener);
        mKillButton = (Button)findViewById(R.id.kill);
        mKillButton.setOnClickListener(mKillListener);
        mKillButton.setEnabled(false);

        mCallbackText = (TextView)findViewById(R.id.callback);
        mCallbackText.setText("Not attached.");
    }

    private View.OnClickListener mBindListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            bindService(new Intent(IRemoteService.class.getName()), connection, Context.BIND_AUTO_CREATE);
            bindService(new Intent(ISecondary.class.getName()), secondaryConnection, Context.BIND_AUTO_CREATE);
            mIsBound = true;
            mCallbackText.setText("Binding.");
        }
    };

    private View.OnClickListener mUnbindListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (mIsBound) {
                if (mService != null) {
                    try {
                        mService.unregisterCallback(mCallback);
                    } catch (RemoteException e) {
                    }
                }

                // Detach our existing connection.
                unbindService(connection);
                unbindService(secondaryConnection);
                mKillButton.setEnabled(false);
                mIsBound = false;
                mCallbackText.setText("Unbinding.");
            }
        }
    };

    private View.OnClickListener mKillListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (mSecondaryService != null) {
                try {
                    int pid = mSecondaryService.getPid();
                    Process.killProcess(pid);
                    mCallbackText.setText("Killed service process.");
                } catch (RemoteException ex) {
                    Toast.makeText(RemoteBindActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    /**
     * 和service的主要接口交互的类
     */
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IRemoteService.Stub.asInterface(service);
            mKillButton.setEnabled(true);
            mCallbackText.setText("Attached.");

            try {
                mService.registerCallback(mCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            mKillButton.setEnabled(false);
            mCallbackText.setText("Disconnected.");
            Toast.makeText(RemoteBindActivity.this, "Disconnected from remote service", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 和service的第二个接口交互
     */
    private ServiceConnection secondaryConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            // Connecting to a secondary interface is the same as any
            // other interface.
            mSecondaryService = ISecondary.Stub.asInterface(service);
            mKillButton.setEnabled(true);
        }

        public void onServiceDisconnected(ComponentName className) {
            mSecondaryService = null;
            mKillButton.setEnabled(false);
        }
    };

    private IRemoteServiceCallback mCallback = new IRemoteServiceCallback.Stub() {
        @Override
        public void valueChanged(int value) throws RemoteException {
            mHandler.sendMessage(mHandler.obtainMessage(BUMP_MSG, value, 0));
        }
    };

    private static final int BUMP_MSG = 1;

    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case BUMP_MSG:
                    mCallbackText.setText("Received from service: " + msg.arg1);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }

    };
}
