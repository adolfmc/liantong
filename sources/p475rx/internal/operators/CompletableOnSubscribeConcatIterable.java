package p475rx.internal.operators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import p475rx.Completable;
import p475rx.Subscription;
import p475rx.subscriptions.SerialSubscription;
import p475rx.subscriptions.Subscriptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.CompletableOnSubscribeConcatIterable */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class CompletableOnSubscribeConcatIterable implements Completable.CompletableOnSubscribe {
    final Iterable<? extends Completable> sources;

    public CompletableOnSubscribeConcatIterable(Iterable<? extends Completable> iterable) {
        this.sources = iterable;
    }

    @Override // p475rx.functions.Action1
    public void call(Completable.CompletableSubscriber completableSubscriber) {
        try {
            Iterator<? extends Completable> it = this.sources.iterator();
            if (it == null) {
                completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
                completableSubscriber.onError(new NullPointerException("The iterator returned is null"));
                return;
            }
            ConcatInnerSubscriber concatInnerSubscriber = new ConcatInnerSubscriber(completableSubscriber, it);
            completableSubscriber.onSubscribe(concatInnerSubscriber.f27580sd);
            concatInnerSubscriber.next();
        } catch (Throwable th) {
            completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
            completableSubscriber.onError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.CompletableOnSubscribeConcatIterable$ConcatInnerSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class ConcatInnerSubscriber extends AtomicInteger implements Completable.CompletableSubscriber {
        private static final long serialVersionUID = -7965400327305809232L;
        final Completable.CompletableSubscriber actual;
        int index;

        /* renamed from: sd */
        final SerialSubscription f27580sd = new SerialSubscription();
        final Iterator<? extends Completable> sources;

        public ConcatInnerSubscriber(Completable.CompletableSubscriber completableSubscriber, Iterator<? extends Completable> it) {
            this.actual = completableSubscriber;
            this.sources = it;
        }

        @Override // p475rx.Completable.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
            this.f27580sd.set(subscription);
        }

        @Override // p475rx.Completable.CompletableSubscriber
        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        @Override // p475rx.Completable.CompletableSubscriber
        public void onCompleted() {
            next();
        }

        void next() {
            if (!this.f27580sd.isUnsubscribed() && getAndIncrement() == 0) {
                Iterator<? extends Completable> it = this.sources;
                while (!this.f27580sd.isUnsubscribed()) {
                    try {
                        if (!it.hasNext()) {
                            this.actual.onCompleted();
                            return;
                        }
                        try {
                            Completable next = it.next();
                            if (next == null) {
                                this.actual.onError(new NullPointerException("The completable returned is null"));
                                return;
                            }
                            next.subscribe(this);
                            if (decrementAndGet() == 0) {
                                return;
                            }
                        } catch (Throwable th) {
                            this.actual.onError(th);
                            return;
                        }
                    } catch (Throwable th2) {
                        this.actual.onError(th2);
                        return;
                    }
                }
            }
        }
    }
}
