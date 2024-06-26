package com.jakewharton.rxbinding2.view;

import android.view.View;
import com.jakewharton.rxbinding2.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class ViewSystemUiVisibilityChangeObservable extends Observable<Integer> {
    private final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewSystemUiVisibilityChangeObservable(View view) {
        this.view = view;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super Integer> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            observer.onSubscribe(listener);
            this.view.setOnSystemUiVisibilityChangeListener(listener);
        }
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static final class Listener extends MainThreadDisposable implements View.OnSystemUiVisibilityChangeListener {
        private final Observer<? super Integer> observer;
        private final View view;

        Listener(View view, Observer<? super Integer> observer) {
            this.view = view;
            this.observer = observer;
        }

        @Override // android.view.View.OnSystemUiVisibilityChangeListener
        public void onSystemUiVisibilityChange(int i) {
            if (isDisposed()) {
                return;
            }
            this.observer.onNext(Integer.valueOf(i));
        }

        @Override // io.reactivex.android.MainThreadDisposable
        public void onDispose() {
            this.view.setOnSystemUiVisibilityChangeListener(null);
        }
    }
}
