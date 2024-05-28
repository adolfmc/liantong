package p475rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import p475rx.Observable;
import p475rx.Producer;
import p475rx.Subscriber;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OnSubscribeRange */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OnSubscribeRange implements Observable.OnSubscribe<Integer> {
    private final int endIndex;
    private final int startIndex;

    public OnSubscribeRange(int i, int i2) {
        this.startIndex = i;
        this.endIndex = i2;
    }

    @Override // p475rx.functions.Action1
    public void call(Subscriber<? super Integer> subscriber) {
        subscriber.setProducer(new RangeProducer(subscriber, this.startIndex, this.endIndex));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OnSubscribeRange$RangeProducer */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class RangeProducer extends AtomicLong implements Producer {
        private static final long serialVersionUID = 4114392207069098388L;
        private final Subscriber<? super Integer> childSubscriber;
        private long currentIndex;
        private final int endOfRange;

        RangeProducer(Subscriber<? super Integer> subscriber, int i, int i2) {
            this.childSubscriber = subscriber;
            this.currentIndex = i;
            this.endOfRange = i2;
        }

        @Override // p475rx.Producer
        public void request(long j) {
            if (get() == Long.MAX_VALUE) {
                return;
            }
            if (j == Long.MAX_VALUE && compareAndSet(0L, Long.MAX_VALUE)) {
                fastpath();
            } else if (j <= 0 || BackpressureUtils.getAndAddRequest(this, j) != 0) {
            } else {
                slowpath(j);
            }
        }

        void slowpath(long j) {
            long j2 = this.endOfRange + 1;
            long j3 = this.currentIndex;
            Subscriber<? super Integer> subscriber = this.childSubscriber;
            long j4 = j3;
            long j5 = j;
            long j6 = 0;
            while (true) {
                if (j6 != j5 && j4 != j2) {
                    if (subscriber.isUnsubscribed()) {
                        return;
                    }
                    subscriber.onNext(Integer.valueOf((int) j4));
                    j4++;
                    j6++;
                } else if (subscriber.isUnsubscribed()) {
                    return;
                } else {
                    if (j4 == j2) {
                        subscriber.onCompleted();
                        return;
                    }
                    j5 = get();
                    if (j5 == j6) {
                        this.currentIndex = j4;
                        j5 = addAndGet(-j6);
                        if (j5 == 0) {
                            return;
                        }
                        j6 = 0;
                    } else {
                        continue;
                    }
                }
            }
        }

        void fastpath() {
            long j = this.endOfRange + 1;
            Subscriber<? super Integer> subscriber = this.childSubscriber;
            for (long j2 = this.currentIndex; j2 != j; j2++) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(Integer.valueOf((int) j2));
            }
            if (subscriber.isUnsubscribed()) {
                return;
            }
            subscriber.onCompleted();
        }
    }
}
