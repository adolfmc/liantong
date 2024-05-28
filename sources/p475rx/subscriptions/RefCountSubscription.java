package p475rx.subscriptions;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p475rx.Subscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.subscriptions.RefCountSubscription */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class RefCountSubscription implements Subscription {
    static final State EMPTY_STATE = new State(false, 0);
    private final Subscription actual;
    final AtomicReference<State> state = new AtomicReference<>(EMPTY_STATE);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.subscriptions.RefCountSubscription$State */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class State {
        final int children;
        final boolean isUnsubscribed;

        State(boolean z, int i) {
            this.isUnsubscribed = z;
            this.children = i;
        }

        State addChild() {
            return new State(this.isUnsubscribed, this.children + 1);
        }

        State removeChild() {
            return new State(this.isUnsubscribed, this.children - 1);
        }

        State unsubscribe() {
            return new State(true, this.children);
        }
    }

    public RefCountSubscription(Subscription subscription) {
        if (subscription == null) {
            throw new IllegalArgumentException("s");
        }
        this.actual = subscription;
    }

    public Subscription get() {
        State state;
        AtomicReference<State> atomicReference = this.state;
        do {
            state = atomicReference.get();
            if (state.isUnsubscribed) {
                return Subscriptions.unsubscribed();
            }
        } while (!atomicReference.compareAndSet(state, state.addChild()));
        return new InnerSubscription(this);
    }

    @Override // p475rx.Subscription
    public boolean isUnsubscribed() {
        return this.state.get().isUnsubscribed;
    }

    @Override // p475rx.Subscription
    public void unsubscribe() {
        State state;
        State unsubscribe;
        AtomicReference<State> atomicReference = this.state;
        do {
            state = atomicReference.get();
            if (state.isUnsubscribed) {
                return;
            }
            unsubscribe = state.unsubscribe();
        } while (!atomicReference.compareAndSet(state, unsubscribe));
        unsubscribeActualIfApplicable(unsubscribe);
    }

    private void unsubscribeActualIfApplicable(State state) {
        if (state.isUnsubscribed && state.children == 0) {
            this.actual.unsubscribe();
        }
    }

    void unsubscribeAChild() {
        State state;
        State removeChild;
        AtomicReference<State> atomicReference = this.state;
        do {
            state = atomicReference.get();
            removeChild = state.removeChild();
        } while (!atomicReference.compareAndSet(state, removeChild));
        unsubscribeActualIfApplicable(removeChild);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.subscriptions.RefCountSubscription$InnerSubscription */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class InnerSubscription extends AtomicInteger implements Subscription {
        final RefCountSubscription parent;

        public InnerSubscription(RefCountSubscription refCountSubscription) {
            this.parent = refCountSubscription;
        }

        @Override // p475rx.Subscription
        public void unsubscribe() {
            if (compareAndSet(0, 1)) {
                this.parent.unsubscribeAChild();
            }
        }

        @Override // p475rx.Subscription
        public boolean isUnsubscribed() {
            return get() != 0;
        }
    }
}
