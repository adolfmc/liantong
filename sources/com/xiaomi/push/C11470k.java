package com.xiaomi.push;

import android.os.Build;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.k */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11470k {

    /* renamed from: a */
    private static long f23346a;

    /* renamed from: a */
    private static String f23347a;

    /* renamed from: a */
    public static synchronized String m2955a() {
        String str;
        synchronized (C11470k.class) {
            long currentTimeMillis = System.currentTimeMillis();
            if (Math.abs(currentTimeMillis - f23346a) > 86400000) {
                f23346a = currentTimeMillis;
                f23347a = Build.MODEL;
            }
            str = f23347a == null ? "" : f23347a;
        }
        return str;
    }
}
