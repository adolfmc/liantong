package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Producer;
import p475rx.Subscriber;
import p475rx.functions.Action1;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorDoOnRequest */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class OperatorDoOnRequest<T> implements Observable.Operator<T, T> {
    final Action1<Long> request;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorDoOnRequest(Action1<Long> action1) {
        this.request = action1;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        final ParentSubscriber parentSubscriber = new ParentSubscriber(subscriber);
        subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorDoOnRequest.1
            @Override // p475rx.Producer
            public void request(long j) {
                OperatorDoOnRequest.this.request.call(Long.valueOf(j));
                parentSubscriber.requestMore(j);
            }
        });
        subscriber.add(parentSubscriber);
        return parentSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorDoOnRequest$ParentSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class ParentSubscriber<T> extends Subscriber<T> {
        private final Subscriber<? super T> child;

        ParentSubscriber(Subscriber<? super T> subscriber) {
            this.child = subscriber;
            request(0L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void requestMore(long j) {
            request(j);
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            this.child.onCompleted();
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            this.child.onError(th);
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            this.child.onNext(t);
        }
    }
}
