package com.jakewharton.rxbinding2.widget;

import android.view.MenuItem;
import android.widget.PopupMenu;
import com.jakewharton.rxbinding2.internal.Preconditions;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class PopupMenuItemClickObservable extends Observable<MenuItem> {
    private final PopupMenu view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PopupMenuItemClickObservable(PopupMenu popupMenu) {
        this.view = popupMenu;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super MenuItem> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            this.view.setOnMenuItemClickListener(listener);
            observer.onSubscribe(listener);
        }
    }

    @NBSInstrumented
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static final class Listener extends MainThreadDisposable implements PopupMenu.OnMenuItemClickListener {
        private final Observer<? super MenuItem> observer;
        private final PopupMenu view;

        Listener(PopupMenu popupMenu, Observer<? super MenuItem> observer) {
            this.view = popupMenu;
            this.observer = observer;
        }

        @Override // android.widget.PopupMenu.OnMenuItemClickListener
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
