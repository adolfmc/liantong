package p475rx.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import p475rx.Subscription;
import p475rx.functions.Action0;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.subscriptions.BooleanSubscription */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class BooleanSubscription implements Subscription {
    static final Action0 EMPTY_ACTION = new Action0() { // from class: rx.subscriptions.BooleanSubscription.1
        @Override // p475rx.functions.Action0
        public void call() {
        }
    };
    final AtomicReference<Action0> actionRef;

    public BooleanSubscription() {
        this.actionRef = new AtomicReference<>();
    }

    private BooleanSubscription(Action0 action0) {
        this.actionRef = new AtomicReference<>(action0);
    }

    public static BooleanSubscription create() {
        return new BooleanSubscription();
    }

    public static BooleanSubscription create(Action0 action0) {
        return new BooleanSubscription(action0);
    }

    @Override // p475rx.Subscription
    public boolean isUnsubscribed() {
        return this.actionRef.get() == EMPTY_ACTION;
    }

    @Override // p475rx.Subscription
    public final void unsubscribe() {
        Action0 andSet;
        Action0 action0 = this.actionRef.get();
        Action0 action02 = EMPTY_ACTION;
        if (action0 == action02 || (andSet = this.actionRef.getAndSet(action02)) == null || andSet == EMPTY_ACTION) {
            return;
        }
        andSet.call();
    }
}
