package com.xiaomi.push;

import android.os.Looper;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.ag */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11144ag {
    /* renamed from: a */
    public static void m4910a() {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            throw new RuntimeException("can't do this on ui thread");
        }
    }
}
