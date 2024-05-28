package com.sina.weibo.sdk.p311b;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sina.weibo.sdk.b.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C7092c {

    /* renamed from: ai */
    private static boolean f18305ai;

    public static void setLoggerEnable(boolean z) {
        f18305ai = z;
    }

    /* renamed from: a */
    public static void m8072a(String str, String str2) {
        if (f18305ai) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            Log.d(str, (stackTraceElement.getFileName() + "(" + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName()) + ": " + str2);
        }
    }

    /* renamed from: b */
    public static void m8071b(String str, String str2) {
        if (f18305ai) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            Log.e(str, (stackTraceElement.getFileName() + "(" + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName()) + ": " + str2);
        }
    }
}
