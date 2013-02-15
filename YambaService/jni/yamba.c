/*
 * yamba.c
 *
 *  Created on: Feb 15, 2013
 *      Author: marko
 */
#include <stdio.h>
#include <android/log.h>
#include "com_control4_yamba_yambaservice_YambaLib.h"

JNIEXPORT void JNICALL Java_com_control4_yamba_yambaservice_YambaLib_log
  (JNIEnv *env, jclass clazz, jstring message) {

	const char *msg = (*env)->GetStringUTFChars(env, message, NULL);

	__android_log_print(ANDROID_LOG_DEBUG, "YambaService", "msg=%s", msg);

	(*env)->ReleaseStringUTFChars(env, message, msg);
}



