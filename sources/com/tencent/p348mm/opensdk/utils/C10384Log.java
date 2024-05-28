package com.tencent.p348mm.opensdk.utils;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.utils.Log */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C10384Log {
    private static ILog logImpl;

    /* renamed from: d */
    public static void m6211d(String str, String str2) {
        ILog iLog = logImpl;
        if (iLog == null) {
            Log.d(str, str2);
        } else {
            iLog.m6216d(str, str2);
        }
    }

    /* renamed from: e */
    public static void m6210e(String str, String str2) {
        ILog iLog = logImpl;
        if (iLog == null) {
            Log.e(str, str2);
        } else {
            iLog.m6215e(str, str2);
        }
    }

    /* renamed from: i */
    public static void m6209i(String str, String str2) {
        ILog iLog = logImpl;
        if (iLog == null) {
            Log.i(str, str2);
        } else {
            iLog.m6214i(str, str2);
        }
    }

    public static void setLogImpl(ILog iLog) {
        logImpl = iLog;
    }

    /* renamed from: v */
    public static void m6208v(String str, String str2) {
        ILog iLog = logImpl;
        if (iLog == null) {
            Log.v(str, str2);
        } else {
            iLog.m6213v(str, str2);
        }
    }

    /* renamed from: w */
    public static void m6207w(String str, String str2) {
        ILog iLog = logImpl;
        if (iLog == null) {
            Log.w(str, str2);
        } else {
            iLog.m6212w(str, str2);
        }
    }
}
