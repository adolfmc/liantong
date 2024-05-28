package com.baidu.location.p137b;

import java.util.concurrent.ExecutorService;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b.w */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2678w {

    /* renamed from: a */
    private ExecutorService f5436a;

    /* renamed from: b */
    private ExecutorService f5437b;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.w$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C2680a {

        /* renamed from: a */
        private static C2678w f5438a = new C2678w();
    }

    private C2678w() {
        this.f5436a = null;
        this.f5437b = null;
    }

    /* renamed from: a */
    public static C2678w m19310a() {
        return C2680a.f5438a;
    }

    /* renamed from: b */
    public synchronized ExecutorService m19309b() {
        return this.f5436a;
    }

    /* renamed from: c */
    public synchronized ExecutorService m19308c() {
        return this.f5437b;
    }

    /* renamed from: d */
    public void m19307d() {
        ExecutorService executorService = this.f5436a;
        if (executorService != null) {
            executorService.shutdown();
        }
        ExecutorService executorService2 = this.f5437b;
        if (executorService2 != null) {
            executorService2.shutdown();
        }
    }
}
