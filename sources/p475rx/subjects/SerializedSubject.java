package p475rx.subjects;

import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.observers.SerializedObserver;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.subjects.SerializedSubject */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SerializedSubject<T, R> extends Subject<T, R> {
    private final Subject<T, R> actual;
    private final SerializedObserver<T> observer;

    public SerializedSubject(final Subject<T, R> subject) {
        super(new Observable.OnSubscribe<R>() { // from class: rx.subjects.SerializedSubject.1
            @Override // p475rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((Subscriber) ((Subscriber) obj));
            }

            public void call(Subscriber<? super R> subscriber) {
                Subject.this.unsafeSubscribe(subscriber);
            }
        });
        this.actual = subject;
        this.observer = new SerializedObserver<>(subject);
    }

    @Override // p475rx.Observer
    public void onCompleted() {
        this.observer.onCompleted();
    }

    @Override // p475rx.Observer
    public void onError(Throwable th) {
        this.observer.onError(th);
    }

    @Override // p475rx.Observer
    public void onNext(T t) {
        this.observer.onNext(t);
    }

    @Override // p475rx.subjects.Subject
    public boolean hasObservers() {
        return this.actual.hasObservers();
    }
}
