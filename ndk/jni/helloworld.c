//
// Created by panhongchao on 16/4/1.
//

#include "com_pan_learn_ndk_simple_MainActivity.h"

JNIEXPORT jstring JNICALL Java_com_pan_learn_ndk_simple_MainActivity_getStringFromNative
  (JNIEnv * env, jobject jObj)
{
    jstring result = (*env)->NewStringUTF(env, "Hello World!");
    return result;
}
