package com.baidu.location.p137b;

import android.os.HandlerThread;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b.v */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2677v {

    /* renamed from: a */
    private static HandlerThread f5435a;

    /* renamed from: a */
    public static synchronized HandlerThread m19311a() {
        HandlerThread handlerThread;
        synchronized (C2677v.class) {
            if (f5435a == null) {
                f5435a = new HandlerThread("ServiceStartArguments", 10);
                f5435a.start();
            }
            handlerThread = f5435a;
        }
        return handlerThread;
    }
}
