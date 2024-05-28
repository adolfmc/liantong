package com.alipay.apmobilesecuritysdk.p109f;

import java.util.LinkedList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.apmobilesecuritysdk.f.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1979b {

    /* renamed from: a */
    private static C1979b f3502a = new C1979b();

    /* renamed from: b */
    private Thread f3503b = null;

    /* renamed from: c */
    private LinkedList<Runnable> f3504c = new LinkedList<>();

    /* renamed from: a */
    public static C1979b m20964a() {
        return f3502a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static /* synthetic */ Thread m20961b(C1979b c1979b) {
        c1979b.f3503b = null;
        return null;
    }

    /* renamed from: a */
    public final synchronized void m20962a(Runnable runnable) {
        this.f3504c.add(runnable);
        if (this.f3503b == null) {
            this.f3503b = new Thread(new RunnableC1980c(this));
            this.f3503b.start();
        }
    }
}
