package com.uber.autodispose;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.ProtocolViolationException;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
final class AutoDisposeEndConsumerHelper {
    private AutoDisposeEndConsumerHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static boolean setOnce(AtomicReference<Disposable> atomicReference, Disposable disposable, Class<?> cls) {
        AutoDisposeUtil.checkNotNull(disposable, "next is null");
        if (atomicReference.compareAndSet(null, disposable)) {
            return true;
        }
        disposable.dispose();
        if (atomicReference.get() != AutoDisposableHelper.DISPOSED) {
            reportDoubleSubscription(cls);
            return false;
        }
        return false;
    }

    public static boolean setOnce(AtomicReference<Subscription> atomicReference, Subscription subscription, Class<?> cls) {
        AutoDisposeUtil.checkNotNull(subscription, "next is null");
        if (atomicReference.compareAndSet(null, subscription)) {
            return true;
        }
        subscription.cancel();
        if (atomicReference.get() != AutoSubscriptionHelper.CANCELLED) {
            reportDoubleSubscription(cls);
            return false;
        }
        return false;
    }

    public static String composeMessage(String str) {
        return "It is not allowed to subscribe with a(n) " + str + " multiple times. Please create a fresh instance of " + str + " and subscribe that to the target source instead.";
    }

    public static void reportDoubleSubscription(Class<?> cls) {
        RxJavaPlugins.onError(new ProtocolViolationException(composeMessage(cls.getName())));
    }
}
