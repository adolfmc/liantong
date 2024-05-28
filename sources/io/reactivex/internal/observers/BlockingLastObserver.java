package io.reactivex.internal.observers;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class BlockingLastObserver<T> extends BlockingBaseObserver<T> {
    @Override // io.reactivex.Observer
    public void onNext(T t) {
        this.value = t;
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable th) {
        this.value = null;
        this.error = th;
        countDown();
    }
}
