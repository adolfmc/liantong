package com.alipay.security.mobile.module.p112b;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.security.mobile.module.b.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2086a {

    /* renamed from: a */
    private static C2086a f3988a = new C2086a();

    private C2086a() {
    }

    /* renamed from: a */
    public static C2086a m20557a() {
        return f3988a;
    }

    /* renamed from: a */
    public static String m20556a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16).versionName;
        } catch (Exception unused) {
            return "0.0.0";
        }
    }
}
