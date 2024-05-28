package p475rx.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import p475rx.Subscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.subscriptions.SerialSubscription */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class SerialSubscription implements Subscription {
    final AtomicReference<State> state = new AtomicReference<>(new State(false, Subscriptions.empty()));

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.subscriptions.SerialSubscription$State */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class State {
        final boolean isUnsubscribed;
        final Subscription subscription;

        State(boolean z, Subscription subscription) {
            this.isUnsubscribed = z;
            this.subscription = subscription;
        }

        State unsubscribe() {
            return new State(true, this.subscription);
        }

        State set(Subscription subscription) {
            return new State(this.isUnsubscribed, subscription);
        }
    }

    @Override // p475rx.Subscription
    public boolean isUnsubscribed() {
        return this.state.get().isUnsubscribed;
    }

    @Override // p475rx.Subscription
    public void unsubscribe() {
        State state;
        AtomicReference<State> atomicReference = this.state;
        do {
            state = atomicReference.get();
            if (state.isUnsubscribed) {
                return;
            }
        } while (!atomicReference.compareAndSet(state, state.unsubscribe()));
        state.subscription.unsubscribe();
    }

    public void set(Subscription subscription) {
        State state;
        if (subscription == null) {
            throw new IllegalArgumentException("Subscription can not be null");
        }
        AtomicReference<State> atomicReference = this.state;
        do {
            state = atomicReference.get();
            if (state.isUnsubscribed) {
                subscription.unsubscribe();
                return;
            }
        } while (!atomicReference.compareAndSet(state, state.set(subscription)));
        state.subscription.unsubscribe();
    }

    public Subscription get() {
        return this.state.get().subscription;
    }
}
