package p475rx.android;

import android.os.Looper;
import java.util.concurrent.atomic.AtomicBoolean;
import p475rx.Subscription;
import p475rx.android.schedulers.AndroidSchedulers;
import p475rx.functions.Action0;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.android.MainThreadSubscription */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class MainThreadSubscription implements Subscription {
    private final AtomicBoolean unsubscribed = new AtomicBoolean();

    protected abstract void onUnsubscribe();

    public static void verifyMainThread() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return;
        }
        throw new IllegalStateException("Expected to be called on the main thread but was " + Thread.currentThread().getName());
    }

    @Override // p475rx.Subscription
    public final boolean isUnsubscribed() {
        return this.unsubscribed.get();
    }

    @Override // p475rx.Subscription
    public final void unsubscribe() {
        if (this.unsubscribed.compareAndSet(false, true)) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                onUnsubscribe();
            } else {
                AndroidSchedulers.mainThread().createWorker().schedule(new Action0() { // from class: rx.android.MainThreadSubscription.1
                    @Override // p475rx.functions.Action0
                    public void call() {
                        MainThreadSubscription.this.onUnsubscribe();
                    }
                });
            }
        }
    }
}
