package p475rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import p475rx.Completable;
import p475rx.Subscription;
import p475rx.subscriptions.SerialSubscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.CompletableOnSubscribeConcatArray */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class CompletableOnSubscribeConcatArray implements Completable.CompletableOnSubscribe {
    final Completable[] sources;

    public CompletableOnSubscribeConcatArray(Completable[] completableArr) {
        this.sources = completableArr;
    }

    @Override // p475rx.functions.Action1
    public void call(Completable.CompletableSubscriber completableSubscriber) {
        ConcatInnerSubscriber concatInnerSubscriber = new ConcatInnerSubscriber(completableSubscriber, this.sources);
        completableSubscriber.onSubscribe(concatInnerSubscriber.f27579sd);
        concatInnerSubscriber.next();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.CompletableOnSubscribeConcatArray$ConcatInnerSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class ConcatInnerSubscriber extends AtomicInteger implements Completable.CompletableSubscriber {
        private static final long serialVersionUID = -7965400327305809232L;
        final Completable.CompletableSubscriber actual;
        int index;

        /* renamed from: sd */
        final SerialSubscription f27579sd = new SerialSubscription();
        final Completable[] sources;

        public ConcatInnerSubscriber(Completable.CompletableSubscriber completableSubscriber, Completable[] completableArr) {
            this.actual = completableSubscriber;
            this.sources = completableArr;
        }

        @Override // p475rx.Completable.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
            this.f27579sd.set(subscription);
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
            if (!this.f27579sd.isUnsubscribed() && getAndIncrement() == 0) {
                Completable[] completableArr = this.sources;
                while (!this.f27579sd.isUnsubscribed()) {
                    int i = this.index;
                    this.index = i + 1;
                    if (i == completableArr.length) {
                        this.actual.onCompleted();
                        return;
                    }
                    completableArr[i].subscribe(this);
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
    }
}
