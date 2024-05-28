package com.alipay.apmobilesecuritysdk.p106c;

import android.content.Context;
import android.os.Build;
import com.alipay.security.mobile.module.p114d.C2096a;
import com.alipay.security.mobile.module.p114d.C2099d;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.apmobilesecuritysdk.c.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1961a {
    /* renamed from: a */
    public static synchronized void m21048a(Context context, String str, String str2, String str3) {
        synchronized (C1961a.class) {
            C2096a m21045b = m21045b(context, str, str2, str3);
            C2099d.m20479a(context.getFilesDir().getAbsolutePath() + "/log/ap", new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".log", m21045b.toString());
        }
    }

    /* renamed from: a */
    public static synchronized void m21047a(String str) {
        synchronized (C1961a.class) {
            C2099d.m20480a(str);
        }
    }

    /* renamed from: a */
    public static synchronized void m21046a(Throwable th) {
        synchronized (C1961a.class) {
            C2099d.m20478a(th);
        }
    }

    /* renamed from: b */
    private static C2096a m21045b(Context context, String str, String str2, String str3) {
        String str4 = "";
        try {
            str4 = context.getPackageName();
        } catch (Throwable unused) {
        }
        return new C2096a(Build.MODEL, str4, "APPSecuritySDK-ALIPAYSDK", "3.4.0.201910161639", str, str2, str3);
    }
}
