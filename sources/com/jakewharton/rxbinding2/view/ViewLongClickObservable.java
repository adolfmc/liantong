package com.jakewharton.rxbinding2.view;

import android.view.View;
import com.jakewharton.rxbinding2.internal.Notification;
import com.jakewharton.rxbinding2.internal.Preconditions;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import java.util.concurrent.Callable;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class ViewLongClickObservable extends Observable<Object> {
    private final Callable<Boolean> handled;
    private final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewLongClickObservable(View view, Callable<Boolean> callable) {
        this.view = view;
        this.handled = callable;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super Object> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, this.handled, observer);
            observer.onSubscribe(listener);
            this.view.setOnLongClickListener(listener);
        }
    }

    @NBSInstrumented
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static final class Listener extends MainThreadDisposable implements View.OnLongClickListener {
        private final Callable<Boolean> handled;
        private final Observer<? super Object> observer;
        private final View view;

        Listener(View view, Callable<Boolean> callable, Observer<? super Object> observer) {
            this.view = view;
            this.observer = observer;
            this.handled = callable;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            NBSActionInstrumentation.onLongClickEventEnter(view, this);
            if (!isDisposed()) {
                try {
                    if (this.handled.call().booleanValue()) {
                        this.observer.onNext(Notification.INSTANCE);
                        NBSActionInstrumentation.onLongClickEventExit();
                        return true;
                    }
                } catch (Exception e) {
                    this.observer.onError(e);
                    dispose();
                }
            }
            NBSActionInstrumentation.onLongClickEventExit();
            return false;
        }

        @Override // io.reactivex.android.MainThreadDisposable
        public void onDispose() {
            this.view.setOnLongClickListener(null);
        }
    }
}
