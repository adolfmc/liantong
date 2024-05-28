package p475rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import p475rx.Completable;
import p475rx.Subscription;
import p475rx.plugins.RxJavaPlugins;
import p475rx.subscriptions.CompositeSubscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.CompletableOnSubscribeMergeArray */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class CompletableOnSubscribeMergeArray implements Completable.CompletableOnSubscribe {
    final Completable[] sources;

    public CompletableOnSubscribeMergeArray(Completable[] completableArr) {
        this.sources = completableArr;
    }

    @Override // p475rx.functions.Action1
    public void call(final Completable.CompletableSubscriber completableSubscriber) {
        final CompositeSubscription compositeSubscription = new CompositeSubscription();
        boolean z = true;
        final AtomicInteger atomicInteger = new AtomicInteger(this.sources.length + 1);
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        completableSubscriber.onSubscribe(compositeSubscription);
        Completable[] completableArr = this.sources;
        int length = completableArr.length;
        boolean z2 = false;
        int i = 0;
        while (i < length) {
            Completable completable = completableArr[i];
            if (compositeSubscription.isUnsubscribed()) {
                return;
            }
            if (completable == null) {
                compositeSubscription.unsubscribe();
                Throwable nullPointerException = new NullPointerException("A completable source is null");
                if (atomicBoolean.compareAndSet(z2, z)) {
                    completableSubscriber.onError(nullPointerException);
                    return;
                }
                RxJavaPlugins.getInstance().getErrorHandler().handleError(nullPointerException);
            }
            completable.subscribe(new Completable.CompletableSubscriber() { // from class: rx.internal.operators.CompletableOnSubscribeMergeArray.1
                @Override // p475rx.Completable.CompletableSubscriber
                public void onSubscribe(Subscription subscription) {
                    compositeSubscription.add(subscription);
                }

                @Override // p475rx.Completable.CompletableSubscriber
                public void onError(Throwable th) {
                    compositeSubscription.unsubscribe();
                    if (atomicBoolean.compareAndSet(false, true)) {
                        completableSubscriber.onError(th);
                    } else {
                        RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
                    }
                }

                @Override // p475rx.Completable.CompletableSubscriber
                public void onCompleted() {
                    if (atomicInteger.decrementAndGet() == 0 && atomicBoolean.compareAndSet(false, true)) {
                        completableSubscriber.onCompleted();
                    }
                }
            });
            i++;
            z = true;
            z2 = false;
        }
        if (atomicInteger.decrementAndGet() == 0 && atomicBoolean.compareAndSet(false, true)) {
            completableSubscriber.onCompleted();
        }
    }
}
