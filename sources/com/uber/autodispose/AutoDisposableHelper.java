package com.uber.autodispose;

import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReference;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
enum AutoDisposableHelper implements Disposable {
    DISPOSED;

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return true;
    }

    static boolean setIfNotSet(AtomicReference<Disposable> atomicReference, Disposable disposable) {
        AutoDisposeUtil.checkNotNull(disposable, "d is null");
        return atomicReference.compareAndSet(null, disposable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean dispose(AtomicReference<Disposable> atomicReference) {
        Disposable andSet;
        Disposable disposable = atomicReference.get();
        AutoDisposableHelper autoDisposableHelper = DISPOSED;
        if (disposable == autoDisposableHelper || (andSet = atomicReference.getAndSet(autoDisposableHelper)) == autoDisposableHelper) {
            return false;
        }
        if (andSet != null) {
            andSet.dispose();
            return true;
        }
        return true;
    }
}
