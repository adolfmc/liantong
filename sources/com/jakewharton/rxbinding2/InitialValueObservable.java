package com.jakewharton.rxbinding2;

import io.reactivex.Observable;
import io.reactivex.Observer;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class InitialValueObservable<T> extends Observable<T> {
    protected abstract T getInitialValue();

    protected abstract void subscribeListener(Observer<? super T> observer);

    @Override // io.reactivex.Observable
    public final void subscribeActual(Observer<? super T> observer) {
        subscribeListener(observer);
        observer.onNext(getInitialValue());
    }

    public final Observable<T> skipInitialValue() {
        return new Skipped();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    final class Skipped extends Observable<T> {
        Skipped() {
        }

        @Override // io.reactivex.Observable
        public void subscribeActual(Observer<? super T> observer) {
            InitialValueObservable.this.subscribeListener(observer);
        }
    }
}
