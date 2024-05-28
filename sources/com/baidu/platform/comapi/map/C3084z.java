package com.baidu.platform.comapi.map;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.z */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C3084z {

    /* renamed from: a */
    static boolean f8021a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m17719a(String str, String str2) {
        if (f8021a) {
            Log.d("MapTrace-" + str, "thread:" + Thread.currentThread().getName() + ":" + Thread.currentThread().getId() + "," + str2);
        }
    }
}
