package p475rx.internal.schedulers;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import p475rx.Subscription;
import p475rx.functions.Action0;
import p475rx.internal.util.SubscriptionList;
import p475rx.subscriptions.CompositeSubscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.schedulers.ScheduledAction */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class ScheduledAction extends AtomicReference<Thread> implements Runnable, Subscription {
    private static final long serialVersionUID = -3962399486978279857L;
    final Action0 action;
    final SubscriptionList cancel;

    public ScheduledAction(Action0 action0) {
        this.action = action0;
        this.cancel = new SubscriptionList();
    }

    public ScheduledAction(Action0 action0, CompositeSubscription compositeSubscription) {
        this.action = action0;
        this.cancel = new SubscriptionList(new Remover(this, compositeSubscription));
    }

    public ScheduledAction(Action0 action0, SubscriptionList subscriptionList) {
        this.action = action0;
        this.cancel = new SubscriptionList(new Remover2(this, subscriptionList));
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            lazySet(Thread.currentThread());
            this.action.call();
        } finally {
            try {
            } finally {
            }
        }
    }

    @Override // p475rx.Subscription
    public boolean isUnsubscribed() {
        return this.cancel.isUnsubscribed();
    }

    @Override // p475rx.Subscription
    public void unsubscribe() {
        if (this.cancel.isUnsubscribed()) {
            return;
        }
        this.cancel.unsubscribe();
    }

    public void add(Subscription subscription) {
        this.cancel.add(subscription);
    }

    public void add(Future<?> future) {
        this.cancel.add(new FutureCompleter(future));
    }

    public void addParent(CompositeSubscription compositeSubscription) {
        this.cancel.add(new Remover(this, compositeSubscription));
    }

    public void addParent(SubscriptionList subscriptionList) {
        this.cancel.add(new Remover2(this, subscriptionList));
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.schedulers.ScheduledAction$FutureCompleter */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    final class FutureCompleter implements Subscription {

        /* renamed from: f */
        private final Future<?> f27613f;

        FutureCompleter(Future<?> future) {
            this.f27613f = future;
        }

        @Override // p475rx.Subscription
        public void unsubscribe() {
            if (ScheduledAction.this.get() != Thread.currentThread()) {
                this.f27613f.cancel(true);
            } else {
                this.f27613f.cancel(false);
            }
        }

        @Override // p475rx.Subscription
        public boolean isUnsubscribed() {
            return this.f27613f.isCancelled();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.schedulers.ScheduledAction$Remover */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class Remover extends AtomicBoolean implements Subscription {
        private static final long serialVersionUID = 247232374289553518L;
        final CompositeSubscription parent;

        /* renamed from: s */
        final ScheduledAction f27614s;

        public Remover(ScheduledAction scheduledAction, CompositeSubscription compositeSubscription) {
            this.f27614s = scheduledAction;
            this.parent = compositeSubscription;
        }

        @Override // p475rx.Subscription
        public boolean isUnsubscribed() {
            return this.f27614s.isUnsubscribed();
        }

        @Override // p475rx.Subscription
        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.parent.remove(this.f27614s);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.schedulers.ScheduledAction$Remover2 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class Remover2 extends AtomicBoolean implements Subscription {
        private static final long serialVersionUID = 247232374289553518L;
        final SubscriptionList parent;

        /* renamed from: s */
        final ScheduledAction f27615s;

        public Remover2(ScheduledAction scheduledAction, SubscriptionList subscriptionList) {
            this.f27615s = scheduledAction;
            this.parent = subscriptionList;
        }

        @Override // p475rx.Subscription
        public boolean isUnsubscribed() {
            return this.f27615s.isUnsubscribed();
        }

        @Override // p475rx.Subscription
        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.parent.remove(this.f27615s);
            }
        }
    }
}
