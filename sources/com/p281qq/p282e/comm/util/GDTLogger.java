package com.p281qq.p282e.comm.util;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.util.GDTLogger */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class GDTLogger {
    public static final boolean DEBUG_ENABLE = false;

    /* renamed from: d */
    public static void m8235d(String str) {
    }

    /* renamed from: e */
    public static void m8234e(String str) {
        Log.e("gdt_ad_mob", str);
    }

    /* renamed from: e */
    public static void m8233e(String str, Throwable th) {
        if (th == null) {
            Log.e("gdt_ad_mob", str);
        } else {
            Log.e("gdt_ad_mob", str, th);
        }
    }

    /* renamed from: i */
    public static void m8232i(String str) {
    }

    /* renamed from: w */
    public static void m8231w(String str) {
        Log.e("gdt_ad_mob", str);
    }

    /* renamed from: w */
    public static void m8230w(String str, Throwable th) {
        if (th == null) {
            Log.w("gdt_ad_mob", str);
        } else {
            Log.w("gdt_ad_mob", str, th);
        }
    }
}
