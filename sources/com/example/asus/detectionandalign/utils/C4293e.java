package com.example.asus.detectionandalign.utils;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.example.asus.detectionandalign.utils.e */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4293e {

    /* renamed from: a */
    private static long f10089a = 0;

    /* renamed from: b */
    private static long f10090b = 2000;

    /* renamed from: c */
    private static int f10091c = -1;

    /* renamed from: a */
    public static boolean m15951a(int i) {
        return m15950a(i, f10090b);
    }

    /* renamed from: a */
    public static boolean m15950a(int i, long j) {
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = currentTimeMillis - f10089a;
        f10089a = currentTimeMillis;
        if (f10091c != i || j2 >= j) {
            f10091c = i;
            return true;
        }
        Log.v("isFastDoubleClick", "短时间内按钮多次触发");
        return false;
    }
}
