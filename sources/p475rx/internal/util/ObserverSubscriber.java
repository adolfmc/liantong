package p475rx.internal.util;

import p475rx.Observer;
import p475rx.Subscriber;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.util.ObserverSubscriber */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class ObserverSubscriber<T> extends Subscriber<T> {
    final Observer<? super T> observer;

    public ObserverSubscriber(Observer<? super T> observer) {
        this.observer = observer;
    }

    @Override // p475rx.Observer
    public void onNext(T t) {
        this.observer.onNext(t);
    }

    @Override // p475rx.Observer
    public void onError(Throwable th) {
        this.observer.onError(th);
    }

    @Override // p475rx.Observer
    public void onCompleted() {
        this.observer.onCompleted();
    }
}
