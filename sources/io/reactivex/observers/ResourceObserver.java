package io.reactivex.observers;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.ListCompositeDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.EndConsumerHelper;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class ResourceObserver<T> implements Observer<T>, Disposable {

    /* renamed from: s */
    private final AtomicReference<Disposable> f24802s = new AtomicReference<>();
    private final ListCompositeDisposable resources = new ListCompositeDisposable();

    protected void onStart() {
    }

    public final void add(@NonNull Disposable disposable) {
        ObjectHelper.requireNonNull(disposable, "resource is null");
        this.resources.add(disposable);
    }

    @Override // io.reactivex.Observer
    public final void onSubscribe(Disposable disposable) {
        if (EndConsumerHelper.setOnce(this.f24802s, disposable, getClass())) {
            onStart();
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public final void dispose() {
        if (DisposableHelper.dispose(this.f24802s)) {
            this.resources.dispose();
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public final boolean isDisposed() {
        return DisposableHelper.isDisposed(this.f24802s.get());
    }
}
