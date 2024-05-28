package p475rx;

import p475rx.annotations.Beta;
import p475rx.internal.util.SubscriptionList;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@Beta
/* renamed from: rx.SingleSubscriber */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class SingleSubscriber<T> implements Subscription {

    /* renamed from: cs */
    private final SubscriptionList f27574cs = new SubscriptionList();

    public abstract void onError(Throwable th);

    public abstract void onSuccess(T t);

    public final void add(Subscription subscription) {
        this.f27574cs.add(subscription);
    }

    @Override // p475rx.Subscription
    public final void unsubscribe() {
        this.f27574cs.unsubscribe();
    }

    @Override // p475rx.Subscription
    public final boolean isUnsubscribed() {
        return this.f27574cs.isUnsubscribed();
    }
}
