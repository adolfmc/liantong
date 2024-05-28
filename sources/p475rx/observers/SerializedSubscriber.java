package p475rx.observers;

import p475rx.Observer;
import p475rx.Subscriber;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.observers.SerializedSubscriber */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SerializedSubscriber<T> extends Subscriber<T> {

    /* renamed from: s */
    private final Observer<T> f27640s;

    public SerializedSubscriber(Subscriber<? super T> subscriber) {
        this(subscriber, true);
    }

    public SerializedSubscriber(Subscriber<? super T> subscriber, boolean z) {
        super(subscriber, z);
        this.f27640s = new SerializedObserver(subscriber);
    }

    @Override // p475rx.Observer
    public void onCompleted() {
        this.f27640s.onCompleted();
    }

    @Override // p475rx.Observer
    public void onError(Throwable th) {
        this.f27640s.onError(th);
    }

    @Override // p475rx.Observer
    public void onNext(T t) {
        this.f27640s.onNext(t);
    }
}
