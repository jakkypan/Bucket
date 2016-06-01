// IRemoteServiceCallback.aidl
package com.pan.learn.service.remote.aidl;

// Declare any non-default types here with import statements

oneway interface IRemoteServiceCallback {
    void valueChanged(int value);
}
