package com.megvii.kas.livenessdetection.obf;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.megvii.kas.livenessdetection.obf.d */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C5330d {

    /* renamed from: a */
    private static boolean f12348a = false;

    /* renamed from: b */
    private static String f12349b = "MegviiSDK";

    /* renamed from: a */
    public static void m13632a() {
        f12348a = true;
    }

    /* renamed from: b */
    public static void m13629b() {
        f12348a = false;
    }

    /* renamed from: a */
    public static void m13631a(String str) {
        m13627b(f12349b, str);
    }

    /* renamed from: b */
    public static void m13628b(String str) {
        m13630a(f12349b, str);
    }

    /* renamed from: a */
    public static void m13630a(String str, String str2) {
        if (f12348a) {
            if (str == null) {
                str = f12349b;
            }
            if (str2 == null) {
                str2 = "";
            }
            Log.e(str, str2);
        }
    }

    /* renamed from: b */
    public static void m13627b(String str, String str2) {
        if (f12348a) {
            if (str == null) {
                str = f12349b;
            }
            if (str2 == null) {
                str2 = "";
            }
            Log.d(str, str2);
        }
    }
}
