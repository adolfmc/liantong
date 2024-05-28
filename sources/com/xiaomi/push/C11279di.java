package com.xiaomi.push;

import android.content.Context;
import android.os.Build;
import com.xiaomi.push.service.C11537ah;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.di */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11279di {
    /* renamed from: a */
    public static byte[] m4358a(String str, byte[] bArr) {
        byte[] m4796a = C11180ay.m4796a(str);
        try {
            m4357a(m4796a);
            return C11425h.m3410a(m4796a, bArr);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: b */
    public static byte[] m4356b(String str, byte[] bArr) {
        byte[] m4796a = C11180ay.m4796a(str);
        try {
            m4357a(m4796a);
            return C11425h.m3409b(m4796a, bArr);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static void m4357a(byte[] bArr) {
        if (bArr.length >= 2) {
            bArr[0] = 99;
            bArr[1] = 100;
        }
    }

    /* renamed from: a */
    public static boolean m4359a(Context context, String str, long j) {
        if (C11537ah.m2715a(context).m2716a(EnumC11409gk.DCJobMutualSwitch.m3637a(), false)) {
            return (Build.VERSION.SDK_INT < 29 || context.getApplicationInfo().targetSdkVersion < 29) && !C11132ac.m4941a(context, str, j);
        }
        return false;
    }
}
