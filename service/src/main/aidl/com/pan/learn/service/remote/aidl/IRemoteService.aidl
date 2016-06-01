// IRemoteService.aidl
package com.pan.learn.service.remote.aidl;

import com.pan.learn.service.remote.aidl.IRemoteServiceCallback;

// Declare any non-default types here with import statements

interface IRemoteService {
    void registerCallback(IRemoteServiceCallback cb);

    void unregisterCallback(IRemoteServiceCallback cb);
}
