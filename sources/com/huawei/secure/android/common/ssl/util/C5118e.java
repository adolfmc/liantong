package com.huawei.secure.android.common.ssl.util;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.secure.android.common.ssl.util.e */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5118e {

    /* renamed from: a */
    private static final String f12115a = "SecurityComp10200303: ";

    /* renamed from: a */
    public static void m13856a(String str, String str2) {
    }

    /* renamed from: a */
    public static void m13855a(String str, String str2, Throwable th) {
        Log.e(m13857a(str), str2, th);
    }

    /* renamed from: b */
    public static void m13854b(String str, String str2) {
        Log.e(m13857a(str), str2);
    }

    /* renamed from: c */
    public static void m13853c(String str, String str2) {
        Log.i(m13857a(str), str2);
    }

    /* renamed from: d */
    public static void m13852d(String str, String str2) {
        Log.v(m13857a(str), str2);
    }

    /* renamed from: e */
    public static void m13851e(String str, String str2) {
        Log.w(m13857a(str), str2);
    }

    /* renamed from: a */
    private static String m13857a(String str) {
        return "SecurityComp10200303: " + str;
    }
}
