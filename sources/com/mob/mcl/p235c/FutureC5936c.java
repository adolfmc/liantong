package com.mob.mcl.p235c;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: com.mob.mcl.c.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FutureC5936c implements Future<C5938e> {

    /* renamed from: a */
    final CountDownLatch f14603a = new CountDownLatch(1);

    /* renamed from: b */
    final AtomicReference<C5938e> f14604b = new AtomicReference<>();

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.f14603a.getCount() == 0;
    }

    @Override // java.util.concurrent.Future
    /* renamed from: a */
    public C5938e get() throws InterruptedException, ExecutionException {
        this.f14603a.await();
        return this.f14604b.get();
    }

    @Override // java.util.concurrent.Future
    /* renamed from: a */
    public C5938e get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        if (!this.f14603a.await(j, timeUnit)) {
            throw new TimeoutException("tcp get msg timeout");
        }
        return this.f14604b.get();
    }

    /* renamed from: a */
    public void m12032a(C5938e c5938e) {
        synchronized (this.f14603a) {
            this.f14604b.set(c5938e);
            this.f14603a.countDown();
        }
    }
}
