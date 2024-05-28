package com.alipay.sdk.app;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.app.j */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C1998j {

    /* renamed from: a */
    private static boolean f3587a;

    /* renamed from: b */
    private static String f3588b;

    /* renamed from: a */
    public static void m20913a(String str) {
        f3588b = str;
    }

    /* renamed from: a */
    public static String m20915a() {
        return f3588b;
    }

    /* renamed from: b */
    public static boolean m20911b() {
        return f3587a;
    }

    /* renamed from: a */
    public static void m20912a(boolean z) {
        f3587a = z;
    }

    /* renamed from: c */
    public static String m20910c() {
        EnumC1999k m20903b = EnumC1999k.m20903b(EnumC1999k.CANCELED.m20907a());
        return m20914a(m20903b.m20907a(), m20903b.m20904b(), "");
    }

    /* renamed from: d */
    public static String m20909d() {
        EnumC1999k m20903b = EnumC1999k.m20903b(EnumC1999k.DOUBLE_REQUEST.m20907a());
        return m20914a(m20903b.m20907a(), m20903b.m20904b(), "");
    }

    /* renamed from: e */
    public static String m20908e() {
        EnumC1999k m20903b = EnumC1999k.m20903b(EnumC1999k.PARAMS_ERROR.m20907a());
        return m20914a(m20903b.m20907a(), m20903b.m20904b(), "");
    }

    /* renamed from: a */
    public static String m20914a(int i, String str, String str2) {
        return "resultStatus={" + i + "};memo={" + str + "};result={" + str2 + "}";
    }
}
