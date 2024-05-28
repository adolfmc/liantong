package p475rx.internal.operators;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import p475rx.Completable;
import p475rx.Scheduler;
import p475rx.Subscription;
import p475rx.functions.Action0;
import p475rx.plugins.RxJavaPlugins;
import p475rx.subscriptions.CompositeSubscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.CompletableOnSubscribeTimeout */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class CompletableOnSubscribeTimeout implements Completable.CompletableOnSubscribe {
    final Completable other;
    final Scheduler scheduler;
    final Completable source;
    final long timeout;
    final TimeUnit unit;

    public CompletableOnSubscribeTimeout(Completable completable, long j, TimeUnit timeUnit, Scheduler scheduler, Completable completable2) {
        this.source = completable;
        this.timeout = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.other = completable2;
    }

    @Override // p475rx.functions.Action1
    public void call(final Completable.CompletableSubscriber completableSubscriber) {
        final CompositeSubscription compositeSubscription = new CompositeSubscription();
        completableSubscriber.onSubscribe(compositeSubscription);
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        compositeSubscription.add(createWorker);
        createWorker.schedule(new Action0() { // from class: rx.internal.operators.CompletableOnSubscribeTimeout.1
            @Override // p475rx.functions.Action0
            public void call() {
                if (atomicBoolean.compareAndSet(false, true)) {
                    compositeSubscription.clear();
                    if (CompletableOnSubscribeTimeout.this.other == null) {
                        completableSubscriber.onError(new TimeoutException());
                    } else {
                        CompletableOnSubscribeTimeout.this.other.subscribe(new Completable.CompletableSubscriber() { // from class: rx.internal.operators.CompletableOnSubscribeTimeout.1.1
                            @Override // p475rx.Completable.CompletableSubscriber
                            public void onSubscribe(Subscription subscription) {
                                compositeSubscription.add(subscription);
                            }

                            @Override // p475rx.Completable.CompletableSubscriber
                            public void onError(Throwable th) {
                                compositeSubscription.unsubscribe();
                                completableSubscriber.onError(th);
                            }

                            @Override // p475rx.Completable.CompletableSubscriber
                            public void onCompleted() {
                                compositeSubscription.unsubscribe();
                                completableSubscriber.onCompleted();
                            }
                        });
                    }
                }
            }
        }, this.timeout, this.unit);
        this.source.subscribe(new Completable.CompletableSubscriber() { // from class: rx.internal.operators.CompletableOnSubscribeTimeout.2
            @Override // p475rx.Completable.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                compositeSubscription.add(subscription);
            }

            @Override // p475rx.Completable.CompletableSubscriber
            public void onError(Throwable th) {
                if (atomicBoolean.compareAndSet(false, true)) {
                    compositeSubscription.unsubscribe();
                    completableSubscriber.onError(th);
                    return;
                }
                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
            }

            @Override // p475rx.Completable.CompletableSubscriber
            public void onCompleted() {
                if (atomicBoolean.compareAndSet(false, true)) {
                    compositeSubscription.unsubscribe();
                    completableSubscriber.onCompleted();
                }
            }
        });
    }
}
