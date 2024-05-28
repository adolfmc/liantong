package p475rx.plugins;

import p475rx.Observable;
import p475rx.Single;
import p475rx.Subscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.plugins.RxJavaSingleExecutionHook */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class RxJavaSingleExecutionHook {
    public <T> Single.OnSubscribe<T> onCreate(Single.OnSubscribe<T> onSubscribe) {
        return onSubscribe;
    }

    public <T, R> Observable.Operator<? extends R, ? super T> onLift(Observable.Operator<? extends R, ? super T> operator) {
        return operator;
    }

    public <T> Throwable onSubscribeError(Throwable th) {
        return th;
    }

    public <T> Subscription onSubscribeReturn(Subscription subscription) {
        return subscription;
    }

    public <T> Observable.OnSubscribe<T> onSubscribeStart(Single<? extends T> single, Observable.OnSubscribe<T> onSubscribe) {
        return onSubscribe;
    }
}
