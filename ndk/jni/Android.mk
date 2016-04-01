#################一个简单的返回字符串的例子
LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := HelloWorld
LOCAL_SRC_FILES := helloworld.c

include $(BUILD_SHARED_LIBRARY)



#################一个宽两个C文件，实现相加的例子
# first lib, which will be built statically
#
include $(CLEAR_VARS)

LOCAL_MODULE    := First
LOCAL_SRC_FILES := first.c

include $(BUILD_STATIC_LIBRARY)

# second lib, which will depend on and include the first one
#
include $(CLEAR_VARS)

LOCAL_MODULE    := Second
LOCAL_SRC_FILES := second.c

LOCAL_STATIC_LIBRARIES := First

include $(BUILD_SHARED_LIBRARY)