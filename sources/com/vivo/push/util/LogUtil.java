package com.vivo.push.util;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.vivo.push.util.u */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class LogUtil {

    /* renamed from: a */
    public static final LogProxy f21241a = new LogController();

    /* renamed from: b */
    private static boolean f21242b;

    /* renamed from: c */
    private static boolean f21243c;

    static {
        m5344c();
    }

    /* renamed from: c */
    private static void m5344c() {
        f21242b = Utility.m5435b("persist.sys.log.ctrl", "no").equals("yes");
    }

    /* renamed from: a */
    public static boolean m5358a() {
        return f21242b;
    }

    /* renamed from: b */
    public static boolean m5349b() {
        return f21242b && f21243c;
    }

    /* renamed from: a */
    public static void m5350a(boolean z) {
        m5344c();
        f21243c = z;
    }

    /* renamed from: a */
    public static int m5354a(String str, String str2) {
        return f21241a.mo5369a(str, str2);
    }

    /* renamed from: a */
    public static int m5352a(String str, Throwable th) {
        return f21241a.mo5367a(str, th);
    }

    /* renamed from: a */
    public static int m5353a(String str, String str2, Throwable th) {
        return f21241a.mo5368a(str, str2, th);
    }

    /* renamed from: b */
    public static int m5346b(String str, String str2) {
        return f21241a.mo5364b(str, str2);
    }

    /* renamed from: c */
    public static int m5342c(String str, String str2) {
        return f21241a.mo5361c(str, str2);
    }

    /* renamed from: d */
    public static int m5341d(String str, String str2) {
        return f21241a.mo5360d(str, str2);
    }

    /* renamed from: b */
    public static int m5345b(String str, String str2, Throwable th) {
        return f21241a.mo5363b(str, str2, th);
    }

    /* renamed from: e */
    public static int m5340e(String str, String str2) {
        return f21241a.mo5359e(str, str2);
    }

    /* renamed from: a */
    public static String m5351a(Throwable th) {
        return f21241a.mo5366a(th);
    }

    /* renamed from: a */
    public static void m5356a(Context context, String str) {
        f21241a.mo5370a(context, str);
    }

    /* renamed from: b */
    public static void m5348b(Context context, String str) {
        f21241a.mo5365b(context, str);
    }

    /* renamed from: c */
    public static void m5343c(Context context, String str) {
        f21241a.mo5362c(context, str);
    }

    /* renamed from: a */
    public static void m5355a(String str) {
        if (f21242b) {
            f21241a.mo5361c("VIVO.PUSH.MSG_NODE", str);
        }
    }

    /* renamed from: b */
    public static void m5347b(String str) {
        if (f21242b) {
            f21241a.mo5361c("VIVO.PUSH.PROFILE.SYNC", str);
        }
    }

    /* renamed from: a */
    public static void m5357a(int i, String str) {
        m5354a("RunTimeException", "code: " + i + ", exceptionMsg: " + str);
    }
}
