package com.uber.autodispose;

import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.jetbrains.annotations.Nullable;
import org.reactivestreams.Subscription;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
enum AutoSubscriptionHelper implements Subscription {
    CANCELLED;

    @Override // org.reactivestreams.Subscription
    public void cancel() {
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j) {
    }

    static boolean validate(@Nullable Subscription subscription, Subscription subscription2) {
        if (subscription2 == null) {
            RxJavaPlugins.onError(new NullPointerException("next is null"));
            return false;
        } else if (subscription != null) {
            subscription2.cancel();
            reportSubscriptionSet();
            return false;
        } else {
            return true;
        }
    }

    static void reportSubscriptionSet() {
        RxJavaPlugins.onError(new IllegalStateException("Subscription already set!"));
    }

    static boolean validate(long j) {
        if (j <= 0) {
            RxJavaPlugins.onError(new IllegalArgumentException("n > 0 required but it was " + j));
            return false;
        }
        return true;
    }

    static void reportMoreProduced(long j) {
        RxJavaPlugins.onError(new IllegalStateException("More produced than requested: " + j));
    }

    static boolean isCancelled(Subscription subscription) {
        return subscription == CANCELLED;
    }

    static boolean set(AtomicReference<Subscription> atomicReference, @Nullable Subscription subscription) {
        Subscription subscription2;
        do {
            subscription2 = atomicReference.get();
            if (subscription2 == CANCELLED) {
                if (subscription != null) {
                    subscription.cancel();
                    return false;
                }
                return false;
            }
        } while (!atomicReference.compareAndSet(subscription2, subscription));
        if (subscription2 != null) {
            subscription2.cancel();
            return true;
        }
        return true;
    }

    static boolean setOnce(AtomicReference<Subscription> atomicReference, Subscription subscription) {
        AutoDisposeUtil.checkNotNull(subscription, "s is null");
        if (atomicReference.compareAndSet(null, subscription)) {
            return true;
        }
        subscription.cancel();
        if (atomicReference.get() != CANCELLED) {
            reportSubscriptionSet();
            return false;
        }
        return false;
    }

    static boolean setIfNotSet(AtomicReference<Subscription> atomicReference, Subscription subscription) {
        AutoDisposeUtil.checkNotNull(subscription, "s is null");
        return atomicReference.compareAndSet(null, subscription);
    }

    static boolean replace(AtomicReference<Subscription> atomicReference, @Nullable Subscription subscription) {
        Subscription subscription2;
        do {
            subscription2 = atomicReference.get();
            if (subscription2 == CANCELLED) {
                if (subscription != null) {
                    subscription.cancel();
                    return false;
                }
                return false;
            }
        } while (!atomicReference.compareAndSet(subscription2, subscription));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean cancel(AtomicReference<Subscription> atomicReference) {
        Subscription andSet;
        Subscription subscription = atomicReference.get();
        AutoSubscriptionHelper autoSubscriptionHelper = CANCELLED;
        if (subscription == autoSubscriptionHelper || (andSet = atomicReference.getAndSet(autoSubscriptionHelper)) == CANCELLED) {
            return false;
        }
        if (andSet != null) {
            andSet.cancel();
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean deferredSetOnce(AtomicReference<Subscription> atomicReference, AtomicLong atomicLong, Subscription subscription) {
        if (setOnce(atomicReference, subscription)) {
            long andSet = atomicLong.getAndSet(0L);
            if (andSet != 0) {
                subscription.request(andSet);
                return true;
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void deferredRequest(AtomicReference<Subscription> atomicReference, AtomicLong atomicLong, long j) {
        Subscription subscription = atomicReference.get();
        if (subscription != null) {
            subscription.request(j);
        } else if (validate(j)) {
            AutoDisposeBackpressureHelper.add(atomicLong, j);
            Subscription subscription2 = atomicReference.get();
            if (subscription2 != null) {
                long andSet = atomicLong.getAndSet(0L);
                if (andSet != 0) {
                    subscription2.request(andSet);
                }
            }
        }
    }
}
