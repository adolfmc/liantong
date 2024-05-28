package io.reactivex.subscribers;

import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.EndConsumerHelper;
import org.reactivestreams.Subscription;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class DefaultSubscriber<T> implements FlowableSubscriber<T> {

    /* renamed from: s */
    private Subscription f24808s;

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public final void onSubscribe(Subscription subscription) {
        if (EndConsumerHelper.validate(this.f24808s, subscription, getClass())) {
            this.f24808s = subscription;
            onStart();
        }
    }

    protected final void request(long j) {
        Subscription subscription = this.f24808s;
        if (subscription != null) {
            subscription.request(j);
        }
    }

    protected final void cancel() {
        Subscription subscription = this.f24808s;
        this.f24808s = SubscriptionHelper.CANCELLED;
        subscription.cancel();
    }

    protected void onStart() {
        request(Long.MAX_VALUE);
    }
}
