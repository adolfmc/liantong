package com.vivo.push.util;

import android.os.Looper;
import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.vivo.push.util.k */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class DebugUtil {
    /* renamed from: a */
    public static void m5390a(String str) {
        if (LogUtil.m5349b() && Looper.myLooper() == Looper.getMainLooper()) {
            Log.w("DebugUtil", "Operation: " + str + " in main thread!", new Throwable());
        }
    }
}
