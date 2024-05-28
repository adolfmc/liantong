package p475rx.internal.operators;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import p475rx.Single;
import p475rx.SingleSubscriber;
import p475rx.Subscription;
import p475rx.exceptions.Exceptions;
import p475rx.functions.FuncN;
import p475rx.plugins.RxJavaPlugins;
import p475rx.subscriptions.CompositeSubscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.SingleOperatorZip */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SingleOperatorZip {
    public static <T, R> Single<R> zip(final Single<? extends T>[] singleArr, final FuncN<? extends R> funcN) {
        return Single.create(new Single.OnSubscribe<R>() { // from class: rx.internal.operators.SingleOperatorZip.1
            @Override // p475rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((SingleSubscriber) ((SingleSubscriber) obj));
            }

            public void call(final SingleSubscriber<? super R> singleSubscriber) {
                Single[] singleArr2 = singleArr;
                if (singleArr2.length == 0) {
                    singleSubscriber.onError(new NoSuchElementException("Can't zip 0 Singles."));
                    return;
                }
                final AtomicInteger atomicInteger = new AtomicInteger(singleArr2.length);
                final AtomicBoolean atomicBoolean = new AtomicBoolean();
                final Object[] objArr = new Object[singleArr.length];
                CompositeSubscription compositeSubscription = new CompositeSubscription();
                singleSubscriber.add(compositeSubscription);
                for (int i = 0; i < singleArr.length && !compositeSubscription.isUnsubscribed() && !atomicBoolean.get(); i++) {
                    final int i2 = i;
                    Subscription subscription = new SingleSubscriber<T>() { // from class: rx.internal.operators.SingleOperatorZip.1.1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // p475rx.SingleSubscriber
                        public void onSuccess(T t) {
                            objArr[i2] = t;
                            if (atomicInteger.decrementAndGet() == 0) {
                                try {
                                    singleSubscriber.onSuccess(funcN.call(objArr));
                                } catch (Throwable th) {
                                    Exceptions.throwIfFatal(th);
                                    onError(th);
                                }
                            }
                        }

                        @Override // p475rx.SingleSubscriber
                        public void onError(Throwable th) {
                            if (atomicBoolean.compareAndSet(false, true)) {
                                singleSubscriber.onError(th);
                            } else {
                                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
                            }
                        }
                    };
                    compositeSubscription.add(subscription);
                    if (compositeSubscription.isUnsubscribed() || atomicBoolean.get()) {
                        return;
                    }
                    singleArr[i].subscribe((SingleSubscriber) subscription);
                }
            }
        });
    }
}
