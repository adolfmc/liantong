package com.baidu.p120ar.utils;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.utils.ARLog */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class ARLog {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    private static final String TAG = "ARLOG";
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static int outputPriority = 2;

    private ARLog() {
    }

    public static void setDebugEnable(boolean z) {
        if (z) {
            outputPriority = 2;
        } else {
            outputPriority = 6;
        }
    }

    /* renamed from: v */
    public static void m20416v(String str) {
        m20415v("ARLOG", getFileLineMethod() + " " + str);
    }

    /* renamed from: v */
    public static void m20415v(String str, String str2) {
        if (outputPriority > 2) {
            return;
        }
        Log.v(str, str2);
    }

    /* renamed from: d */
    public static void m20422d(String str) {
        m20421d("ARLOG", getFileLineMethod() + " " + str);
    }

    /* renamed from: d */
    public static void m20421d(String str, String str2) {
        if (outputPriority > 3) {
            return;
        }
        Log.d(str, str2);
    }

    /* renamed from: i */
    public static void m20418i(String str) {
        m20417i("ARLOG", getFileLineMethod() + " " + str);
    }

    /* renamed from: i */
    public static void m20417i(String str, String str2) {
        if (outputPriority > 4) {
            return;
        }
        Log.i(str, str2);
    }

    /* renamed from: w */
    public static void m20414w(String str) {
        m20413w("ARLOG", getFileLineMethod() + " " + str);
    }

    /* renamed from: w */
    public static void m20413w(String str, String str2) {
        if (outputPriority > 5) {
            return;
        }
        Log.w(str, str2);
    }

    /* renamed from: e */
    public static void m20420e(String str) {
        m20419e("ARLOG", getFileLineMethod() + " " + str);
    }

    /* renamed from: e */
    public static void m20419e(String str, String str2) {
        if (outputPriority > 6) {
            return;
        }
        Log.e(str, str2);
    }

    public static String getFileLineMethod() {
        StackTraceElement stackTraceElement = new Exception().getStackTrace()[2];
        StringBuffer stringBuffer = new StringBuffer("[");
        stringBuffer.append(stackTraceElement.getFileName());
        stringBuffer.append(" | ");
        stringBuffer.append(stackTraceElement.getLineNumber());
        stringBuffer.append(" | ");
        stringBuffer.append(stackTraceElement.getMethodName());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
