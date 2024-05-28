package p475rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import p475rx.Observable;
import p475rx.Producer;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorTakeLastOne */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class OperatorTakeLastOne<T> implements Observable.Operator<T, T> {
    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorTakeLastOne$Holder */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Holder {
        static final OperatorTakeLastOne<Object> INSTANCE = new OperatorTakeLastOne<>();

        private Holder() {
        }
    }

    public static <T> OperatorTakeLastOne<T> instance() {
        return (OperatorTakeLastOne<T>) Holder.INSTANCE;
    }

    OperatorTakeLastOne() {
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        final ParentSubscriber parentSubscriber = new ParentSubscriber(subscriber);
        subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorTakeLastOne.1
            @Override // p475rx.Producer
            public void request(long j) {
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
    /* renamed from: rx.internal.operators.OperatorTakeLastOne$ParentSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class ParentSubscriber<T> extends Subscriber<T> {
        private static final Object ABSENT = new Object();
        private static final int NOT_REQUESTED_COMPLETED = 1;
        private static final int NOT_REQUESTED_NOT_COMPLETED = 0;
        private static final int REQUESTED_COMPLETED = 3;
        private static final int REQUESTED_NOT_COMPLETED = 2;
        private final Subscriber<? super T> child;
        private T last = (T) ABSENT;
        private final AtomicInteger state = new AtomicInteger(0);

        ParentSubscriber(Subscriber<? super T> subscriber) {
            this.child = subscriber;
        }

        void requestMore(long j) {
            if (j <= 0) {
                return;
            }
            while (true) {
                int i = this.state.get();
                if (i == 0) {
                    if (this.state.compareAndSet(0, 2)) {
                        return;
                    }
                } else if (i != 1) {
                    return;
                } else {
                    if (this.state.compareAndSet(1, 3)) {
                        emit();
                        return;
                    }
                }
            }
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            if (this.last == ABSENT) {
                this.child.onCompleted();
                return;
            }
            while (true) {
                int i = this.state.get();
                if (i == 0) {
                    if (this.state.compareAndSet(0, 1)) {
                        return;
                    }
                } else if (i != 2) {
                    return;
                } else {
                    if (this.state.compareAndSet(2, 3)) {
                        emit();
                        return;
                    }
                }
            }
        }

        private void emit() {
            if (isUnsubscribed()) {
                this.last = null;
                return;
            }
            T t = this.last;
            this.last = null;
            if (t != ABSENT) {
                try {
                    this.child.onNext(t);
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this.child);
                    return;
                }
            }
            if (isUnsubscribed()) {
                return;
            }
            this.child.onCompleted();
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            this.child.onError(th);
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            this.last = t;
        }
    }
}
