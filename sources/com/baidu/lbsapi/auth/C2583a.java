package com.baidu.lbsapi.auth;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.lbsapi.auth.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2583a {

    /* renamed from: a */
    public static boolean f4967a = false;

    /* renamed from: b */
    private static String f4968b = "BaiduApiAuth";

    /* renamed from: a */
    public static String m19677a() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
        return stackTraceElement.getFileName() + "[" + stackTraceElement.getLineNumber() + "]";
    }

    /* renamed from: a */
    public static void m19676a(String str) {
        if (!f4967a || Thread.currentThread().getStackTrace().length == 0) {
            return;
        }
        String str2 = f4968b;
        Log.d(str2, m19677a() + ";" + str);
    }

    /* renamed from: b */
    public static void m19675b(String str) {
        if (Thread.currentThread().getStackTrace().length == 0) {
            return;
        }
        Log.i(f4968b, str);
    }

    /* renamed from: c */
    public static void m19674c(String str) {
        if (!f4967a || Thread.currentThread().getStackTrace().length == 0) {
            return;
        }
        String str2 = f4968b;
        Log.e(str2, m19677a() + ";" + str);
    }
}
