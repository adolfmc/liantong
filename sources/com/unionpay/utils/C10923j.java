package com.unionpay.utils;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.unionpay.utils.j */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C10923j {

    /* renamed from: a */
    private static int f20831a = Integer.MAX_VALUE;

    /* renamed from: a */
    private static int m5831a(int i, String str, String str2) {
        if (str != null && str2 != null) {
            switch (i) {
                case 2:
                    return Log.v(str, str2);
                case 3:
                    return Log.d(str, str2);
                case 4:
                    return Log.i(str, str2);
                case 5:
                    return Log.w(str, str2);
                case 6:
                    return Log.e(str, str2);
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static int m5830a(String str, String str2) {
        if (f20831a <= 3) {
            m5831a(3, str, str2);
            return 0;
        }
        return 0;
    }

    /* renamed from: b */
    public static int m5829b(String str, String str2) {
        if (f20831a <= 4) {
            m5831a(4, str, str2);
            return 0;
        }
        return 0;
    }

    /* renamed from: c */
    public static int m5828c(String str, String str2) {
        if (f20831a <= 6) {
            return m5831a(6, str, str2);
        }
        return 0;
    }
}
