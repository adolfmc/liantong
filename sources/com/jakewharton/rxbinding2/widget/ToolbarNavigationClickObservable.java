package com.jakewharton.rxbinding2.widget;

import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Toolbar;
import com.bytedance.applog.tracker.Tracker;
import com.jakewharton.rxbinding2.internal.Notification;
import com.jakewharton.rxbinding2.internal.Preconditions;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

@RequiresApi(21)
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class ToolbarNavigationClickObservable extends Observable<Object> {
    private final Toolbar view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ToolbarNavigationClickObservable(Toolbar toolbar) {
        this.view = toolbar;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super Object> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            observer.onSubscribe(listener);
            this.view.setNavigationOnClickListener(listener);
        }
    }

    @NBSInstrumented
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static final class Listener extends MainThreadDisposable implements View.OnClickListener {
        private final Observer<? super Object> observer;
        private final Toolbar view;

        Listener(Toolbar toolbar, Observer<? super Object> observer) {
            this.view = toolbar;
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
            this.view.setNavigationOnClickListener(null);
        }
    }
}
