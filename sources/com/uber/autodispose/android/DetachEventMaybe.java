package com.uber.autodispose.android;

import android.os.Build;
import android.support.annotation.RestrictTo;
import android.view.View;
import com.uber.autodispose.LifecycleNotStartedException;
import com.uber.autodispose.android.internal.AutoDisposeAndroidUtil;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.android.MainThreadDisposable;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class DetachEventMaybe extends Maybe<Object> {
    private final View view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DetachEventMaybe(View view) {
        this.view = view;
    }

    @Override // io.reactivex.Maybe
    public void subscribeActual(MaybeObserver<? super Object> maybeObserver) {
        Listener listener = new Listener(this.view, maybeObserver);
        maybeObserver.onSubscribe(listener);
        if (!AutoDisposeAndroidUtil.isMainThread()) {
            maybeObserver.onError(new IllegalStateException("Views can only be bound to on the main thread!"));
            return;
        }
        if (!((Build.VERSION.SDK_INT >= 19 && this.view.isAttachedToWindow()) || this.view.getWindowToken() != null)) {
            maybeObserver.onError(new LifecycleNotStartedException("View is not attached!"));
            return;
        }
        this.view.addOnAttachStateChangeListener(listener);
        if (listener.isDisposed()) {
            this.view.removeOnAttachStateChangeListener(listener);
        }
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class Listener extends MainThreadDisposable implements View.OnAttachStateChangeListener {
        private static final Object INSTANCE = new Object();
        private final MaybeObserver<? super Object> observer;
        private final View view;

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        Listener(View view, MaybeObserver<? super Object> maybeObserver) {
            this.view = view;
            this.observer = maybeObserver;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            if (isDisposed()) {
                return;
            }
            this.observer.onSuccess(INSTANCE);
        }

        @Override // io.reactivex.android.MainThreadDisposable
        public void onDispose() {
            this.view.removeOnAttachStateChangeListener(this);
        }
    }
}
