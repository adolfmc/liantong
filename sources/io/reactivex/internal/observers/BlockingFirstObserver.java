package io.reactivex.internal.observers;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class BlockingFirstObserver<T> extends BlockingBaseObserver<T> {
    @Override // io.reactivex.Observer
    public void onNext(T t) {
        if (this.value == null) {
            this.value = t;
            this.f24408d.dispose();
            countDown();
        }
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable th) {
        if (this.value == null) {
            this.error = th;
        }
        countDown();
    }
}
