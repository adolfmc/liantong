package p475rx;

import p475rx.internal.util.SubscriptionList;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.Subscriber */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class Subscriber<T> implements Observer<T>, Subscription {
    private static final Long NOT_SET = Long.MIN_VALUE;
    private Producer producer;
    private long requested;
    private final Subscriber<?> subscriber;
    private final SubscriptionList subscriptions;

    public void onStart() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Subscriber() {
        this(null, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Subscriber(Subscriber<?> subscriber) {
        this(subscriber, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Subscriber(Subscriber<?> subscriber, boolean z) {
        this.requested = NOT_SET.longValue();
        this.subscriber = subscriber;
        this.subscriptions = (!z || subscriber == null) ? new SubscriptionList() : subscriber.subscriptions;
    }

    public final void add(Subscription subscription) {
        this.subscriptions.add(subscription);
    }

    @Override // p475rx.Subscription
    public final void unsubscribe() {
        this.subscriptions.unsubscribe();
    }

    @Override // p475rx.Subscription
    public final boolean isUnsubscribed() {
        return this.subscriptions.isUnsubscribed();
    }

    public final void request(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("number requested cannot be negative: " + j);
        }
        synchronized (this) {
            if (this.producer != null) {
                this.producer.request(j);
                return;
            }
            addToRequested(j);
        }
    }

    private void addToRequested(long j) {
        if (this.requested == NOT_SET.longValue()) {
            this.requested = j;
            return;
        }
        long j2 = this.requested + j;
        if (j2 < 0) {
            this.requested = Long.MAX_VALUE;
        } else {
            this.requested = j2;
        }
    }

    public void setProducer(Producer producer) {
        long j;
        boolean z;
        synchronized (this) {
            j = this.requested;
            this.producer = producer;
            z = this.subscriber != null && j == NOT_SET.longValue();
        }
        if (z) {
            this.subscriber.setProducer(this.producer);
        } else if (j == NOT_SET.longValue()) {
            this.producer.request(Long.MAX_VALUE);
        } else {
            this.producer.request(j);
        }
    }
}
