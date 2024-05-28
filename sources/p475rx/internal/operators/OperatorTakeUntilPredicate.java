package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Producer;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Func1;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorTakeUntilPredicate */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorTakeUntilPredicate<T> implements Observable.Operator<T, T> {
    final Func1<? super T, Boolean> stopPredicate;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorTakeUntilPredicate$ParentSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public final class ParentSubscriber extends Subscriber<T> {
        private final Subscriber<? super T> child;
        private boolean done = false;

        ParentSubscriber(Subscriber<? super T> subscriber) {
            this.child = subscriber;
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            this.child.onNext(t);
            try {
                if (OperatorTakeUntilPredicate.this.stopPredicate.call(t).booleanValue()) {
                    this.done = true;
                    this.child.onCompleted();
                    unsubscribe();
                }
            } catch (Throwable th) {
                this.done = true;
                Exceptions.throwOrReport(th, this.child, t);
                unsubscribe();
            }
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.child.onCompleted();
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            if (this.done) {
                return;
            }
            this.child.onError(th);
        }

        void downstreamRequest(long j) {
            request(j);
        }
    }

    public OperatorTakeUntilPredicate(Func1<? super T, Boolean> func1) {
        this.stopPredicate = func1;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        final ParentSubscriber parentSubscriber = new ParentSubscriber(subscriber);
        subscriber.add(parentSubscriber);
        subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorTakeUntilPredicate.1
            @Override // p475rx.Producer
            public void request(long j) {
                parentSubscriber.downstreamRequest(j);
            }
        });
        return parentSubscriber;
    }
}
