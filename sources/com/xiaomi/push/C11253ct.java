package com.xiaomi.push;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.ct */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11253ct {

    /* renamed from: a */
    private static C11242cl f21767a;

    /* renamed from: a */
    private static C11243cm f21768a;

    /* renamed from: a */
    public static void m4460a(Context context, AbstractC11356fa abstractC11356fa) {
        if (m4457b(context)) {
            if (f21767a == null) {
                f21767a = new C11242cl(context);
            }
            if (f21768a == null) {
                f21768a = new C11243cm(context);
            }
            C11242cl c11242cl = f21767a;
            abstractC11356fa.m3889a(c11242cl, c11242cl);
            C11243cm c11243cm = f21768a;
            abstractC11356fa.m3877b(c11243cm, c11243cm);
            m4459a("startStats");
        }
    }

    /* renamed from: b */
    public static void m4456b(Context context, AbstractC11356fa abstractC11356fa) {
        C11242cl c11242cl = f21767a;
        if (c11242cl != null) {
            abstractC11356fa.m3890a(c11242cl);
            f21767a = null;
        }
        C11243cm c11243cm = f21768a;
        if (c11243cm != null) {
            abstractC11356fa.m3878b(c11243cm);
            f21768a = null;
        }
        m4459a("stopStats");
    }

    /* renamed from: b */
    private static boolean m4457b(Context context) {
        return C11241ck.m4526a(context);
    }

    /* renamed from: a */
    public static void m4462a(Context context) {
        m4459a("onSendMsg");
        if (m4457b(context)) {
            C11256cw.m4418a(context, System.currentTimeMillis(), m4461a(context));
        }
    }

    /* renamed from: b */
    public static void m4458b(Context context) {
        m4459a("onReceiveMsg");
        if (m4457b(context)) {
            C11256cw.m4414b(context, System.currentTimeMillis(), m4461a(context));
        }
    }

    /* renamed from: c */
    public static void m4455c(Context context) {
        m4459a("onPing");
        if (m4457b(context)) {
            C11256cw.m4413c(context, System.currentTimeMillis(), m4461a(context));
        }
    }

    /* renamed from: d */
    public static void m4454d(Context context) {
        m4459a("onPong");
        if (m4457b(context)) {
            C11256cw.m4412d(context, System.currentTimeMillis(), m4461a(context));
        }
    }

    /* renamed from: a */
    public static boolean m4461a(Context context) {
        return C11455i.m3040b(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m4459a(String str) {
        C11241ck.m4524a("Push-PowerStats", str);
    }
}
