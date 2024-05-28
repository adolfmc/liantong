package com.jakewharton.rxbinding2.view;

import android.view.View;
import com.bytedance.applog.tracker.Tracker;
import com.jakewharton.rxbinding2.InitialValueObservable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class ViewFocusChangeObservable extends InitialValueObservable<Boolean> {
    private final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewFocusChangeObservable(View view) {
        this.view = view;
    }

    @Override // com.jakewharton.rxbinding2.InitialValueObservable
    public void subscribeListener(Observer<? super Boolean> observer) {
        Listener listener = new Listener(this.view, observer);
        observer.onSubscribe(listener);
        this.view.setOnFocusChangeListener(listener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jakewharton.rxbinding2.InitialValueObservable
    public Boolean getInitialValue() {
        return Boolean.valueOf(this.view.hasFocus());
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static final class Listener extends MainThreadDisposable implements View.OnFocusChangeListener {
        private final Observer<? super Boolean> observer;
        private final View view;

        Listener(View view, Observer<? super Boolean> observer) {
            this.view = view;
            this.observer = observer;
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            Tracker.onFocusChange(view, z);
            if (isDisposed()) {
                return;
            }
            this.observer.onNext(Boolean.valueOf(z));
        }

        @Override // io.reactivex.android.MainThreadDisposable
        public void onDispose() {
            this.view.setOnFocusChangeListener(null);
        }
    }
}
