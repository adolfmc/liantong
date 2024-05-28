package p475rx.internal.operators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import p475rx.Observable;
import p475rx.Producer;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OnSubscribeFromIterable */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OnSubscribeFromIterable<T> implements Observable.OnSubscribe<T> {

    /* renamed from: is */
    final Iterable<? extends T> f27585is;

    @Override // p475rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeFromIterable(Iterable<? extends T> iterable) {
        if (iterable == null) {
            throw new NullPointerException("iterable must not be null");
        }
        this.f27585is = iterable;
    }

    public void call(Subscriber<? super T> subscriber) {
        try {
            Iterator<? extends T> it = this.f27585is.iterator();
            boolean hasNext = it.hasNext();
            if (subscriber.isUnsubscribed()) {
                return;
            }
            if (!hasNext) {
                subscriber.onCompleted();
            } else {
                subscriber.setProducer(new IterableProducer(subscriber, it));
            }
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, subscriber);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OnSubscribeFromIterable$IterableProducer */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class IterableProducer<T> extends AtomicLong implements Producer {
        private static final long serialVersionUID = -8730475647105475802L;

        /* renamed from: it */
        private final Iterator<? extends T> f27586it;

        /* renamed from: o */
        private final Subscriber<? super T> f27587o;

        /* JADX INFO: Access modifiers changed from: package-private */
        public IterableProducer(Subscriber<? super T> subscriber, Iterator<? extends T> it) {
            this.f27587o = subscriber;
            this.f27586it = it;
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
            Subscriber<? super T> subscriber = this.f27587o;
            Iterator<? extends T> it = this.f27586it;
            long j2 = j;
            long j3 = 0;
            while (true) {
                if (j3 != j2) {
                    if (subscriber.isUnsubscribed()) {
                        return;
                    }
                    try {
                        subscriber.onNext((T) it.next());
                        if (subscriber.isUnsubscribed()) {
                            return;
                        }
                        try {
                            if (!it.hasNext()) {
                                if (subscriber.isUnsubscribed()) {
                                    return;
                                }
                                subscriber.onCompleted();
                                return;
                            }
                            j3++;
                        } catch (Throwable th) {
                            Exceptions.throwOrReport(th, subscriber);
                            return;
                        }
                    } catch (Throwable th2) {
                        Exceptions.throwOrReport(th2, subscriber);
                        return;
                    }
                } else {
                    j2 = get();
                    if (j3 == j2) {
                        j2 = BackpressureUtils.produced(this, j3);
                        if (j2 == 0) {
                            return;
                        }
                        j3 = 0;
                    } else {
                        continue;
                    }
                }
            }
        }

        void fastpath() {
            Subscriber<? super T> subscriber = this.f27587o;
            Iterator<? extends T> it = this.f27586it;
            while (!subscriber.isUnsubscribed()) {
                try {
                    subscriber.onNext((T) it.next());
                    if (subscriber.isUnsubscribed()) {
                        return;
                    }
                    try {
                        if (!it.hasNext()) {
                            if (subscriber.isUnsubscribed()) {
                                return;
                            }
                            subscriber.onCompleted();
                            return;
                        }
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, subscriber);
                        return;
                    }
                } catch (Throwable th2) {
                    Exceptions.throwOrReport(th2, subscriber);
                    return;
                }
            }
        }
    }
}
