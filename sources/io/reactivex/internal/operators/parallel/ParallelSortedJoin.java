package io.reactivex.internal.operators.parallel;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ParallelSortedJoin<T> extends Flowable<T> {
    final Comparator<? super T> comparator;
    final ParallelFlowable<List<T>> source;

    public ParallelSortedJoin(ParallelFlowable<List<T>> parallelFlowable, Comparator<? super T> comparator) {
        this.source = parallelFlowable;
        this.comparator = comparator;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        SortedJoinSubscription sortedJoinSubscription = new SortedJoinSubscription(subscriber, this.source.parallelism(), this.comparator);
        subscriber.onSubscribe(sortedJoinSubscription);
        this.source.subscribe(sortedJoinSubscription.subscribers);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static final class SortedJoinSubscription<T> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = 3481980673745556697L;
        final Subscriber<? super T> actual;
        volatile boolean cancelled;
        final Comparator<? super T> comparator;
        final int[] indexes;
        final List<T>[] lists;
        final SortedJoinInnerSubscriber<T>[] subscribers;
        final AtomicLong requested = new AtomicLong();
        final AtomicInteger remaining = new AtomicInteger();
        final AtomicReference<Throwable> error = new AtomicReference<>();

        SortedJoinSubscription(Subscriber<? super T> subscriber, int i, Comparator<? super T> comparator) {
            this.actual = subscriber;
            this.comparator = comparator;
            SortedJoinInnerSubscriber<T>[] sortedJoinInnerSubscriberArr = new SortedJoinInnerSubscriber[i];
            for (int i2 = 0; i2 < i; i2++) {
                sortedJoinInnerSubscriberArr[i2] = new SortedJoinInnerSubscriber<>(this, i2);
            }
            this.subscribers = sortedJoinInnerSubscriberArr;
            this.lists = new List[i];
            this.indexes = new int[i];
            this.remaining.lazySet(i);
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                if (this.remaining.get() == 0) {
                    drain();
                }
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            cancelAll();
            if (getAndIncrement() == 0) {
                Arrays.fill(this.lists, (Object) null);
            }
        }

        void cancelAll() {
            for (SortedJoinInnerSubscriber<T> sortedJoinInnerSubscriber : this.subscribers) {
                sortedJoinInnerSubscriber.cancel();
            }
        }

        void innerNext(List<T> list, int i) {
            this.lists[i] = list;
            if (this.remaining.decrementAndGet() == 0) {
                drain();
            }
        }

        void innerError(Throwable th) {
            if (this.error.compareAndSet(null, th)) {
                drain();
            } else if (th != this.error.get()) {
                RxJavaPlugins.onError(th);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:42:0x00a6, code lost:
            if (r13 != 0) goto L96;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x00aa, code lost:
            if (r18.cancelled == false) goto L56;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x00ac, code lost:
            java.util.Arrays.fill(r3, (java.lang.Object) null);
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x00af, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x00b0, code lost:
            r5 = r18.error.get();
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x00b8, code lost:
            if (r5 == null) goto L58;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x00ba, code lost:
            cancelAll();
            java.util.Arrays.fill(r3, (java.lang.Object) null);
            r2.onError(r5);
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x00c3, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x00c4, code lost:
            r5 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x00c5, code lost:
            if (r5 >= r4) goto L88;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00cf, code lost:
            if (r0[r5] == r3[r5].size()) goto L62;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x00d1, code lost:
            r16 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x00d4, code lost:
            r5 = r5 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x00d7, code lost:
            r16 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x00d9, code lost:
            if (r16 == false) goto L66;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x00db, code lost:
            java.util.Arrays.fill(r3, (java.lang.Object) null);
            r2.onComplete();
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x00e1, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x00e2, code lost:
            r13 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x00e5, code lost:
            r13 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x00e9, code lost:
            if (r11 == r13) goto L72;
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x00f2, code lost:
            if (r7 == Long.MAX_VALUE) goto L72;
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:0x00f4, code lost:
            r18.requested.addAndGet(-r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x00fa, code lost:
            r5 = get();
         */
        /* JADX WARN: Code restructure failed: missing block: B:69:0x00fe, code lost:
            if (r5 != r6) goto L74;
         */
        /* JADX WARN: Code restructure failed: missing block: B:70:0x0100, code lost:
            r5 = addAndGet(-r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:71:0x0105, code lost:
            if (r5 != 0) goto L81;
         */
        /* JADX WARN: Code restructure failed: missing block: B:72:0x0107, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:73:0x0108, code lost:
            r6 = r5;
         */
        /* JADX WARN: Code restructure failed: missing block: B:74:0x010b, code lost:
            r6 = r5;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void drain() {
            /*
                Method dump skipped, instructions count: 270
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.parallel.ParallelSortedJoin.SortedJoinSubscription.drain():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static final class SortedJoinInnerSubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<List<T>> {
        private static final long serialVersionUID = 6751017204873808094L;
        final int index;
        final SortedJoinSubscription<T> parent;

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
        }

        @Override // org.reactivestreams.Subscriber
        public /* bridge */ /* synthetic */ void onNext(Object obj) {
            onNext((List) ((List) obj));
        }

        SortedJoinInnerSubscriber(SortedJoinSubscription<T> sortedJoinSubscription, int i) {
            this.parent = sortedJoinSubscription;
            this.index = i;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this, subscription)) {
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(List<T> list) {
            this.parent.innerNext(list, this.index);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.parent.innerError(th);
        }

        void cancel() {
            SubscriptionHelper.cancel(this);
        }
    }
}
