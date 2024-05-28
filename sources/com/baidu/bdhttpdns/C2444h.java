package com.baidu.bdhttpdns;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.bdhttpdns.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2444h {

    /* renamed from: a */
    private static boolean f4344a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m20103a(String str, Object... objArr) {
        if (f4344a) {
            Log.v("BDHttpDns", String.format(str, objArr));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m20102a(boolean z) {
        f4344a = z;
    }
}
