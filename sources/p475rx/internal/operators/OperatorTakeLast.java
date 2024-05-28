package p475rx.internal.operators;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicLong;
import p475rx.Observable;
import p475rx.Producer;
import p475rx.Subscriber;
import p475rx.functions.Func1;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorTakeLast */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorTakeLast<T> implements Observable.Operator<T, T> {
    final int count;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorTakeLast(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("count cannot be negative");
        }
        this.count = i;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        final TakeLastSubscriber takeLastSubscriber = new TakeLastSubscriber(subscriber, this.count);
        subscriber.add(takeLastSubscriber);
        subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorTakeLast.1
            @Override // p475rx.Producer
            public void request(long j) {
                takeLastSubscriber.requestMore(j);
            }
        });
        return takeLastSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorTakeLast$TakeLastSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class TakeLastSubscriber<T> extends Subscriber<T> implements Func1<Object, T> {
        final Subscriber<? super T> actual;
        final int count;
        final AtomicLong requested = new AtomicLong();
        final ArrayDeque<Object> queue = new ArrayDeque<>();

        /* renamed from: nl */
        final NotificationLite<T> f27606nl = NotificationLite.instance();

        public TakeLastSubscriber(Subscriber<? super T> subscriber, int i) {
            this.actual = subscriber;
            this.count = i;
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            if (this.queue.size() == this.count) {
                this.queue.poll();
            }
            this.queue.offer(this.f27606nl.next(t));
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            this.queue.clear();
            this.actual.onError(th);
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            BackpressureUtils.postCompleteDone(this.requested, this.queue, this.actual, this);
        }

        @Override // p475rx.functions.Func1
        public T call(Object obj) {
            return this.f27606nl.getValue(obj);
        }

        void requestMore(long j) {
            if (j > 0) {
                BackpressureUtils.postCompleteRequest(this.requested, j, this.queue, this.actual, this);
            }
        }
    }
}
