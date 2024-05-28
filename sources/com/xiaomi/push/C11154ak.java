package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.ak */
/* loaded from: E:\11617560_dexfile_execute.dex */
class C11154ak {

    /* renamed from: a */
    private static volatile boolean f21507a;

    /* renamed from: a */
    private static void m4887a(Class<?> cls, Context context) {
        if (f21507a) {
            return;
        }
        try {
            f21507a = true;
            cls.getDeclaredMethod("InitEntry", Context.class).invoke(cls, context);
        } catch (Throwable th) {
            AbstractC11049b.m5282a("mdid:load lib error " + th);
        }
    }

    /* renamed from: a */
    public static boolean m4888a(Context context) {
        try {
            Class<?> m2929a = C11479r.m2929a(context, "com.bun.miitmdid.core.JLibrary");
            if (m2929a != null) {
                m4887a(m2929a, context);
                return true;
            }
            return false;
        } catch (Throwable th) {
            AbstractC11049b.m5282a("mdid:check error " + th);
            return false;
        }
    }
}
