package com.jakewharton.rxbinding2.widget;

import android.support.annotation.RequiresApi;
import android.view.MenuItem;
import android.widget.Toolbar;
import com.jakewharton.rxbinding2.internal.Preconditions;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

@RequiresApi(21)
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class ToolbarItemClickObservable extends Observable<MenuItem> {
    private final Toolbar view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ToolbarItemClickObservable(Toolbar toolbar) {
        this.view = toolbar;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super MenuItem> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            observer.onSubscribe(listener);
            this.view.setOnMenuItemClickListener(listener);
        }
    }

    @NBSInstrumented
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static final class Listener extends MainThreadDisposable implements Toolbar.OnMenuItemClickListener {
        private final Observer<? super MenuItem> observer;
        private final Toolbar view;

        Listener(Toolbar toolbar, Observer<? super MenuItem> observer) {
            this.view = toolbar;
            this.observer = observer;
        }

        @Override // android.widget.Toolbar.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            NBSActionInstrumentation.onMenuItemClickEnter(menuItem, this);
            if (!isDisposed()) {
                this.observer.onNext(menuItem);
                NBSActionInstrumentation.onMenuItemClickExit();
                return true;
            }
            NBSActionInstrumentation.onMenuItemClickExit();
            return false;
        }

        @Override // io.reactivex.android.MainThreadDisposable
        public void onDispose() {
            this.view.setOnMenuItemClickListener(null);
        }
    }
}
