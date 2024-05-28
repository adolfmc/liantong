package com.jakewharton.rxbinding2.widget;

import android.view.View;
import android.widget.AdapterView;
import com.bytedance.applog.tracker.Tracker;
import com.jakewharton.rxbinding2.internal.Preconditions;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class AdapterViewItemClickObservable extends Observable<Integer> {
    private final AdapterView<?> view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdapterViewItemClickObservable(AdapterView<?> adapterView) {
        this.view = adapterView;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super Integer> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            observer.onSubscribe(listener);
            this.view.setOnItemClickListener(listener);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static final class Listener extends MainThreadDisposable implements AdapterView.OnItemClickListener {
        private final Observer<? super Integer> observer;
        private final AdapterView<?> view;

        Listener(AdapterView<?> adapterView, Observer<? super Integer> observer) {
            this.view = adapterView;
            this.observer = observer;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            NBSActionInstrumentation.onItemClickEnter(view, i, this);
            Tracker.onItemClick(adapterView, view, i, j);
            if (!isDisposed()) {
                this.observer.onNext(Integer.valueOf(i));
            }
            NBSActionInstrumentation.onItemClickExit();
        }

        @Override // io.reactivex.android.MainThreadDisposable
        public void onDispose() {
            this.view.setOnItemClickListener(null);
        }
    }
}
