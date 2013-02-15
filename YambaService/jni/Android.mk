LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_LDLIBS += -llog

LOCAL_MODULE    := yamba
LOCAL_SRC_FILES := yamba.c

include $(BUILD_SHARED_LIBRARY)
