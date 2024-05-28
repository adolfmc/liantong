package com.alipay.sdk.app.statistic;

import android.content.Context;
import com.alipay.sdk.util.C2040c;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.app.statistic.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2000a {

    /* renamed from: a */
    public static final String f3599a = "alipay_cashier_statistic_record";

    /* renamed from: b */
    private static C2002c f3600b;

    /* renamed from: a */
    public static void m20902a(Context context) {
        if (f3600b != null) {
            return;
        }
        f3600b = new C2002c(context);
    }

    /* renamed from: a */
    public static void m20901a(Context context, String str) {
        new Thread(new RunnableC2001b(context, str)).start();
    }

    /* renamed from: b */
    public static synchronized void m20895b(Context context, String str) {
        synchronized (C2000a.class) {
            if (f3600b == null) {
                return;
            }
            m20901a(context, f3600b.m20892a(str));
            f3600b = null;
        }
    }

    /* renamed from: a */
    public static void m20896a(String str, Throwable th) {
        if (f3600b == null || th == null || th.getClass() == null) {
            return;
        }
        f3600b.m20889a(str, th.getClass().getSimpleName(), th);
    }

    /* renamed from: a */
    public static void m20897a(String str, String str2, Throwable th, String str3) {
        C2002c c2002c = f3600b;
        if (c2002c == null) {
            return;
        }
        c2002c.m20888a(str, str2, th, str3);
    }

    /* renamed from: a */
    public static void m20898a(String str, String str2, Throwable th) {
        C2002c c2002c = f3600b;
        if (c2002c == null) {
            return;
        }
        c2002c.m20889a(str, str2, th);
    }

    /* renamed from: a */
    public static void m20899a(String str, String str2, String str3) {
        C2002c c2002c = f3600b;
        if (c2002c == null) {
            return;
        }
        c2002c.m20891a(str, str2, str3);
    }

    /* renamed from: a */
    public static void m20900a(Context context, String str, String str2, String str3) {
        if (context == null) {
            return;
        }
        try {
            C2002c c2002c = new C2002c(context);
            c2002c.m20891a(str, str2, str3);
            m20901a(context, c2002c.m20892a(""));
        } catch (Throwable th) {
            C2040c.m20715a(th);
        }
    }
}
