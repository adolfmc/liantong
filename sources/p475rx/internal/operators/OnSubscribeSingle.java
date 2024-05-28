package p475rx.internal.operators;

import java.util.NoSuchElementException;
import p475rx.Observable;
import p475rx.Single;
import p475rx.SingleSubscriber;
import p475rx.Subscriber;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OnSubscribeSingle */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class OnSubscribeSingle<T> implements Single.OnSubscribe<T> {
    private final Observable<T> observable;

    @Override // p475rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public OnSubscribeSingle(Observable<T> observable) {
        this.observable = observable;
    }

    public void call(final SingleSubscriber<? super T> singleSubscriber) {
        Subscriber<T> subscriber = new Subscriber<T>() { // from class: rx.internal.operators.OnSubscribeSingle.1
            private boolean emittedTooMany = false;
            private boolean itemEmitted = false;
            private T emission = null;

            @Override // p475rx.Subscriber
            public void onStart() {
                request(2L);
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                if (this.emittedTooMany) {
                    return;
                }
                if (this.itemEmitted) {
                    singleSubscriber.onSuccess(this.emission);
                } else {
                    singleSubscriber.onError(new NoSuchElementException("Observable emitted no items"));
                }
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                singleSubscriber.onError(th);
                unsubscribe();
            }

            @Override // p475rx.Observer
            public void onNext(T t) {
                if (this.itemEmitted) {
                    this.emittedTooMany = true;
                    singleSubscriber.onError(new IllegalArgumentException("Observable emitted too many elements"));
                    unsubscribe();
                    return;
                }
                this.itemEmitted = true;
                this.emission = t;
            }
        };
        singleSubscriber.add(subscriber);
        this.observable.unsafeSubscribe(subscriber);
    }

    public static <T> OnSubscribeSingle<T> create(Observable<T> observable) {
        return new OnSubscribeSingle<>(observable);
    }
}
