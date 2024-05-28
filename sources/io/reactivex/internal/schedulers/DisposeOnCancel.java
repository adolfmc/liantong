package io.reactivex.internal.schedulers;

import io.reactivex.disposables.Disposable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
final class DisposeOnCancel implements Future<Object> {

    /* renamed from: d */
    final Disposable f24763d;

    @Override // java.util.concurrent.Future
    public Object get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override // java.util.concurrent.Future
    public Object get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DisposeOnCancel(Disposable disposable) {
        this.f24763d = disposable;
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        this.f24763d.dispose();
        return false;
    }
}
