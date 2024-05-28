package p475rx.subscriptions;

import java.util.concurrent.Future;
import p475rx.Subscription;
import p475rx.functions.Action0;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.subscriptions.Subscriptions */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class Subscriptions {
    private static final Unsubscribed UNSUBSCRIBED = new Unsubscribed();

    private Subscriptions() {
        throw new IllegalStateException("No instances!");
    }

    public static Subscription empty() {
        return BooleanSubscription.create();
    }

    public static Subscription unsubscribed() {
        return UNSUBSCRIBED;
    }

    public static Subscription create(Action0 action0) {
        return BooleanSubscription.create(action0);
    }

    public static Subscription from(Future<?> future) {
        return new FutureSubscription(future);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.subscriptions.Subscriptions$FutureSubscription */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class FutureSubscription implements Subscription {

        /* renamed from: f */
        final Future<?> f27648f;

        public FutureSubscription(Future<?> future) {
            this.f27648f = future;
        }

        @Override // p475rx.Subscription
        public void unsubscribe() {
            this.f27648f.cancel(true);
        }

        @Override // p475rx.Subscription
        public boolean isUnsubscribed() {
            return this.f27648f.isCancelled();
        }
    }

    public static CompositeSubscription from(Subscription... subscriptionArr) {
        return new CompositeSubscription(subscriptionArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.subscriptions.Subscriptions$Unsubscribed */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class Unsubscribed implements Subscription {
        @Override // p475rx.Subscription
        public boolean isUnsubscribed() {
            return true;
        }

        @Override // p475rx.Subscription
        public void unsubscribe() {
        }

        Unsubscribed() {
        }
    }
}
