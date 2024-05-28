package com.xiaomi.push;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.ab */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11131ab {

    /* renamed from: a */
    static final char[] f21453a = "0123456789ABCDEF".toCharArray();

    /* renamed from: a */
    public static boolean m4943a(Context context) {
        return C11130aa.f21452a;
    }

    /* renamed from: a */
    public static String m4942a(byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder(i2 * 2);
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = bArr[i + i3] & 255;
            sb.append(f21453a[i4 >> 4]);
            sb.append(f21453a[i4 & 15]);
        }
        return sb.toString();
    }
}
