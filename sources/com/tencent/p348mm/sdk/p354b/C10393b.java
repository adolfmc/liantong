package com.tencent.p348mm.sdk.p354b;

import android.os.Build;
import android.os.Looper;
import android.os.Process;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.b.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C10393b {

    /* renamed from: h */
    public static C10397e f20001h = null;

    /* renamed from: i */
    private static InterfaceC10394a f20002i = null;

    /* renamed from: j */
    private static InterfaceC10394a f20003j = null;

    /* renamed from: k */
    private static final String f20004k;
    private static int level = 6;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.sdk.b.b$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC10394a {
        /* renamed from: b */
        int mo6190b();

        /* renamed from: d */
        void mo6189d(String str, String str2);

        /* renamed from: e */
        void mo6188e(String str, String str2);

        /* renamed from: f */
        void mo6187f(String str, String str2);
    }

    static {
        C10395c c10395c = new C10395c();
        f20002i = c10395c;
        f20003j = c10395c;
        StringBuilder sb = new StringBuilder();
        sb.append("VERSION.RELEASE:[" + Build.VERSION.RELEASE);
        sb.append("] VERSION.CODENAME:[" + Build.VERSION.CODENAME);
        sb.append("] VERSION.INCREMENTAL:[" + Build.VERSION.INCREMENTAL);
        sb.append("] BOARD:[" + Build.BOARD);
        sb.append("] DEVICE:[" + Build.DEVICE);
        sb.append("] DISPLAY:[" + Build.DISPLAY);
        sb.append("] FINGERPRINT:[" + Build.FINGERPRINT);
        sb.append("] HOST:[" + Build.HOST);
        sb.append("] MANUFACTURER:[" + Build.MANUFACTURER);
        sb.append("] MODEL:[" + Build.MODEL);
        sb.append("] PRODUCT:[" + Build.PRODUCT);
        sb.append("] TAGS:[" + Build.TAGS);
        sb.append("] TYPE:[" + Build.TYPE);
        sb.append("] USER:[" + Build.USER + "]");
        f20004k = sb.toString();
    }

    /* renamed from: a */
    public static void m6195a(String str, String str2) {
        m6194a(str, str2, null);
    }

    /* renamed from: a */
    public static void m6194a(String str, String str2, Object... objArr) {
        InterfaceC10394a interfaceC10394a = f20003j;
        if (interfaceC10394a == null || interfaceC10394a.mo6190b() > 4) {
            return;
        }
        if (objArr != null) {
            str2 = String.format(str2, objArr);
        }
        if (str2 == null) {
            str2 = "";
        }
        String m6193b = m6193b(str);
        InterfaceC10394a interfaceC10394a2 = f20003j;
        Process.myPid();
        Thread.currentThread().getId();
        Looper.getMainLooper().getThread().getId();
        interfaceC10394a2.mo6187f(m6193b, str2);
    }

    /* renamed from: b */
    private static String m6193b(String str) {
        C10397e c10397e = f20001h;
        return c10397e != null ? c10397e.m6185b(str) : str;
    }

    /* renamed from: b */
    public static void m6192b(String str, String str2) {
        InterfaceC10394a interfaceC10394a = f20003j;
        if (interfaceC10394a == null || interfaceC10394a.mo6190b() > 2) {
            return;
        }
        if (str2 == null) {
            str2 = "";
        }
        String m6193b = m6193b(str);
        InterfaceC10394a interfaceC10394a2 = f20003j;
        Process.myPid();
        Thread.currentThread().getId();
        Looper.getMainLooper().getThread().getId();
        interfaceC10394a2.mo6189d(m6193b, str2);
    }

    /* renamed from: c */
    public static void m6191c(String str, String str2) {
        InterfaceC10394a interfaceC10394a = f20003j;
        if (interfaceC10394a == null || interfaceC10394a.mo6190b() > 1) {
            return;
        }
        if (str2 == null) {
            str2 = "";
        }
        String m6193b = m6193b(str);
        InterfaceC10394a interfaceC10394a2 = f20003j;
        Process.myPid();
        Thread.currentThread().getId();
        Looper.getMainLooper().getThread().getId();
        interfaceC10394a2.mo6188e(m6193b, str2);
    }
}
