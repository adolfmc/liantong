package p475rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import p475rx.Observable;
import p475rx.Producer;
import p475rx.Subscriber;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorTake */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorTake<T> implements Observable.Operator<T, T> {
    final int limit;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorTake(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("limit >= 0 required but it was " + i);
        }
        this.limit = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorTake$1 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public class C139841 extends Subscriber<T> {
        boolean completed;
        int count;
        final /* synthetic */ Subscriber val$child;

        C139841(Subscriber subscriber) {
            this.val$child = subscriber;
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            if (this.completed) {
                return;
            }
            this.completed = true;
            this.val$child.onCompleted();
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            if (this.completed) {
                return;
            }
            this.completed = true;
            try {
                this.val$child.onError(th);
            } finally {
                unsubscribe();
            }
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            if (isUnsubscribed()) {
                return;
            }
            int i = this.count;
            this.count = i + 1;
            if (i < OperatorTake.this.limit) {
                boolean z = this.count == OperatorTake.this.limit;
                this.val$child.onNext(t);
                if (!z || this.completed) {
                    return;
                }
                this.completed = true;
                try {
                    this.val$child.onCompleted();
                } finally {
                    unsubscribe();
                }
            }
        }

        @Override // p475rx.Subscriber
        public void setProducer(final Producer producer) {
            this.val$child.setProducer(new Producer() { // from class: rx.internal.operators.OperatorTake.1.1
                final AtomicLong requested = new AtomicLong(0);

                @Override // p475rx.Producer
                public void request(long j) {
                    long j2;
                    long min;
                    if (j <= 0 || C139841.this.completed) {
                        return;
                    }
                    do {
                        j2 = this.requested.get();
                        min = Math.min(j, OperatorTake.this.limit - j2);
                        if (min == 0) {
                            return;
                        }
                    } while (!this.requested.compareAndSet(j2, j2 + min));
                    producer.request(min);
                }
            });
        }
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        C139841 c139841 = new C139841(subscriber);
        if (this.limit == 0) {
            subscriber.onCompleted();
            c139841.unsubscribe();
        }
        subscriber.add(c139841);
        return c139841;
    }
}
