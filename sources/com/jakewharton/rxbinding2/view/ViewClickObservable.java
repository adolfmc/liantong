package com.jakewharton.rxbinding2.view;

import android.view.View;
import com.bytedance.applog.tracker.Tracker;
import com.jakewharton.rxbinding2.internal.Notification;
import com.jakewharton.rxbinding2.internal.Preconditions;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class ViewClickObservable extends Observable<Object> {
    private final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewClickObservable(View view) {
        this.view = view;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super Object> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            observer.onSubscribe(listener);
            this.view.setOnClickListener(listener);
        }
    }

    @NBSInstrumented
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static final class Listener extends MainThreadDisposable implements View.OnClickListener {
        private final Observer<? super Object> observer;
        private final View view;

        Listener(View view, Observer<? super Object> observer) {
            this.view = view;
            this.observer = observer;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            if (!isDisposed()) {
                this.observer.onNext(Notification.INSTANCE);
            }
            NBSActionInstrumentation.onClickEventExit();
        }

        @Override // io.reactivex.android.MainThreadDisposable
        public void onDispose() {
            this.view.setOnClickListener(null);
        }
    }
}
