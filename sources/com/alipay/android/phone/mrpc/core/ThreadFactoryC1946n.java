package com.alipay.android.phone.mrpc.core;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.android.phone.mrpc.core.n */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class ThreadFactoryC1946n implements ThreadFactory {

    /* renamed from: a */
    private final AtomicInteger f3430a = new AtomicInteger(1);

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "com.alipay.mobile.common.transport.http.HttpManager.HttpWorker #" + this.f3430a.getAndIncrement());
        thread.setPriority(4);
        return thread;
    }
}
