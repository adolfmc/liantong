package com.baidu.cloud.videocache.utils;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Logger {
    private static final String TAG = "VideoCache";
    private static volatile boolean loggingEnable;

    /* renamed from: d */
    public static void m19725d(String str) {
        if (loggingEnable) {
            m19724d("VideoCache", str);
        }
    }

    /* renamed from: d */
    public static void m19724d(String str, String str2) {
        if (loggingEnable) {
            Log.d(str, str2);
        }
    }

    /* renamed from: e */
    public static void m19723e(String str) {
        if (loggingEnable) {
            m19722e("VideoCache", str);
        }
    }

    /* renamed from: e */
    public static void m19722e(String str, String str2) {
        if (loggingEnable) {
            Log.e(str, str2);
        }
    }

    public static void enableLogging(boolean z) {
        loggingEnable = z;
    }
}
