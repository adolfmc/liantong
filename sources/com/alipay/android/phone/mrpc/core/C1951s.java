package com.alipay.android.phone.mrpc.core;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.android.phone.mrpc.core.s */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1951s {

    /* renamed from: a */
    private static Boolean f3460a;

    /* renamed from: a */
    public static final boolean m21070a(Context context) {
        Boolean bool = f3460a;
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            Boolean valueOf = Boolean.valueOf((context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 2) != 0);
            f3460a = valueOf;
            return valueOf.booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }
}
