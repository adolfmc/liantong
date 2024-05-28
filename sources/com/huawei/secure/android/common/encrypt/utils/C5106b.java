package com.huawei.secure.android.common.encrypt.utils;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.secure.android.common.encrypt.utils.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5106b {

    /* renamed from: a */
    private static final String f11980a = "SecurityComp10200303: ";

    /* renamed from: a */
    public static void m13944a(String str, String str2) {
    }

    /* renamed from: a */
    public static void m13943a(String str, String str2, Throwable th) {
        Log.e(m13945a(str), str2, th);
    }

    /* renamed from: b */
    public static void m13942b(String str, String str2) {
        Log.e(m13945a(str), str2);
    }

    /* renamed from: c */
    public static void m13941c(String str, String str2) {
        Log.i(m13945a(str), str2);
    }

    /* renamed from: d */
    public static void m13940d(String str, String str2) {
        Log.v(m13945a(str), str2);
    }

    /* renamed from: e */
    public static void m13939e(String str, String str2) {
        Log.w(m13945a(str), str2);
    }

    /* renamed from: a */
    private static String m13945a(String str) {
        return "SecurityComp10200303: " + str;
    }
}
