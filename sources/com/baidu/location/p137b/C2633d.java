package com.baidu.location.p137b;

import android.os.Bundle;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2633d {

    /* renamed from: a */
    private static Object f5186a = new Object();

    /* renamed from: b */
    private static C2633d f5187b = null;

    /* renamed from: c */
    private int f5188c = -1;

    /* renamed from: a */
    public static C2633d m19512a() {
        C2633d c2633d;
        synchronized (f5186a) {
            if (f5187b == null) {
                f5187b = new C2633d();
            }
            c2633d = f5187b;
        }
        return c2633d;
    }

    /* renamed from: a */
    public void m19511a(int i, int i2, String str) {
        if (i2 != this.f5188c) {
            this.f5188c = i2;
            Bundle bundle = new Bundle();
            bundle.putInt("loctype", i);
            bundle.putInt("diagtype", i2);
            bundle.putByteArray("diagmessage", str.getBytes());
            C2628b.m19560a().m19559a(bundle, 303);
        }
    }
}
