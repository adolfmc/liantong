package io.reactivex.observers;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.EndConsumerHelper;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class DefaultObserver<T> implements Observer<T> {

    /* renamed from: s */
    private Disposable f24795s;

    protected void onStart() {
    }

    @Override // io.reactivex.Observer
    public final void onSubscribe(@NonNull Disposable disposable) {
        if (EndConsumerHelper.validate(this.f24795s, disposable, getClass())) {
            this.f24795s = disposable;
            onStart();
        }
    }

    protected final void cancel() {
        Disposable disposable = this.f24795s;
        this.f24795s = DisposableHelper.DISPOSED;
        disposable.dispose();
    }
}
