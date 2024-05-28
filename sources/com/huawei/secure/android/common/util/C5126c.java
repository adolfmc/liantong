package com.huawei.secure.android.common.util;

import android.os.Handler;
import android.os.Looper;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.secure.android.common.util.c */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5126c {

    /* renamed from: a */
    private static Handler f12169a = new Handler(Looper.getMainLooper());

    /* renamed from: a */
    public static void m13767a(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        f12169a.post(runnable);
    }
}
