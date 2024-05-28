package com.baidu.bdhttpdns;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.bdhttpdns.i */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2445i {

    /* renamed from: a */
    private static volatile C2445i f4345a;

    /* renamed from: b */
    private final Executor f4346b = new ThreadPoolExecutor(5, 25, 20, TimeUnit.SECONDS, new LinkedBlockingDeque(50));

    private C2445i() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C2445i m20101a() {
        if (f4345a == null) {
            synchronized (C2445i.class) {
                if (f4345a == null) {
                    f4345a = new C2445i();
                }
            }
        }
        return f4345a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public Executor m20100b() {
        return this.f4346b;
    }
}
