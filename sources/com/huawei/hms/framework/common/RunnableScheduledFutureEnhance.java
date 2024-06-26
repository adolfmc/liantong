package com.huawei.hms.framework.common;

import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class RunnableScheduledFutureEnhance<T> implements RunnableScheduledFuture<T> {
    private String parentName = Thread.currentThread().getName();
    private RunnableScheduledFuture<T> proxy;

    public RunnableScheduledFutureEnhance(RunnableScheduledFuture<T> runnableScheduledFuture) {
        this.proxy = runnableScheduledFuture;
    }

    @Override // java.util.concurrent.RunnableScheduledFuture
    public boolean isPeriodic() {
        return this.proxy.isPeriodic();
    }

    @Override // java.util.concurrent.Delayed
    public long getDelay(TimeUnit timeUnit) {
        return this.proxy.getDelay(timeUnit);
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public void run() {
        this.proxy.run();
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.proxy.isDone();
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return this.proxy.cancel(z);
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.proxy.isCancelled();
    }

    @Override // java.util.concurrent.Future
    public T get() throws ExecutionException, InterruptedException {
        return (T) this.proxy.get();
    }

    @Override // java.util.concurrent.Future
    public T get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return (T) this.proxy.get(j, timeUnit);
    }

    public String getParentName() {
        return this.parentName;
    }

    @Override // java.lang.Comparable
    public int compareTo(Delayed delayed) {
        return this.proxy.compareTo(delayed);
    }

    public boolean equals(Object obj) {
        return this.proxy.equals(obj);
    }

    public int hashCode() {
        return this.proxy.hashCode();
    }
}
