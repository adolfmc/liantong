package com.jakewharton.rxbinding2.widget;

import android.view.View;
import android.widget.AdapterView;
import com.bytedance.applog.tracker.Tracker;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Preconditions;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class AdapterViewSelectionObservable extends InitialValueObservable<AdapterViewSelectionEvent> {
    private final AdapterView<?> view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdapterViewSelectionObservable(AdapterView<?> adapterView) {
        this.view = adapterView;
    }

    @Override // com.jakewharton.rxbinding2.InitialValueObservable
    public void subscribeListener(Observer<? super AdapterViewSelectionEvent> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            this.view.setOnItemSelectedListener(listener);
            observer.onSubscribe(listener);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jakewharton.rxbinding2.InitialValueObservable
    public AdapterViewSelectionEvent getInitialValue() {
        int selectedItemPosition = this.view.getSelectedItemPosition();
        if (selectedItemPosition == -1) {
            return AdapterViewNothingSelectionEvent.create(this.view);
        }
        return AdapterViewItemSelectionEvent.create(this.view, this.view.getSelectedView(), selectedItemPosition, this.view.getSelectedItemId());
    }

    @NBSInstrumented
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static final class Listener extends MainThreadDisposable implements AdapterView.OnItemSelectedListener {
        private final Observer<? super AdapterViewSelectionEvent> observer;
        private final AdapterView<?> view;

        Listener(AdapterView<?> adapterView, Observer<? super AdapterViewSelectionEvent> observer) {
            this.view = adapterView;
            this.observer = observer;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            NBSActionInstrumentation.onItemSelectedEnter(view, i, this);
            Tracker.onItemSelected(adapterView, view, i, j);
            if (!isDisposed()) {
                this.observer.onNext(AdapterViewItemSelectionEvent.create(adapterView, view, i, j));
            }
            NBSActionInstrumentation.onItemSelectedExit();
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
            if (isDisposed()) {
                return;
            }
            this.observer.onNext(AdapterViewNothingSelectionEvent.create(adapterView));
        }

        @Override // io.reactivex.android.MainThreadDisposable
        public void onDispose() {
            this.view.setOnItemSelectedListener(null);
        }
    }
}
