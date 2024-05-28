package p475rx.internal.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import p475rx.Subscription;
import p475rx.exceptions.Exceptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.util.SubscriptionList */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class SubscriptionList implements Subscription {
    private LinkedList<Subscription> subscriptions;
    private volatile boolean unsubscribed;

    public SubscriptionList() {
    }

    public SubscriptionList(Subscription... subscriptionArr) {
        this.subscriptions = new LinkedList<>(Arrays.asList(subscriptionArr));
    }

    public SubscriptionList(Subscription subscription) {
        this.subscriptions = new LinkedList<>();
        this.subscriptions.add(subscription);
    }

    @Override // p475rx.Subscription
    public boolean isUnsubscribed() {
        return this.unsubscribed;
    }

    public void add(Subscription subscription) {
        if (subscription.isUnsubscribed()) {
            return;
        }
        if (!this.unsubscribed) {
            synchronized (this) {
                if (!this.unsubscribed) {
                    LinkedList<Subscription> linkedList = this.subscriptions;
                    if (linkedList == null) {
                        linkedList = new LinkedList<>();
                        this.subscriptions = linkedList;
                    }
                    linkedList.add(subscription);
                    return;
                }
            }
        }
        subscription.unsubscribe();
    }

    public void remove(Subscription subscription) {
        if (this.unsubscribed) {
            return;
        }
        synchronized (this) {
            LinkedList<Subscription> linkedList = this.subscriptions;
            if (!this.unsubscribed && linkedList != null) {
                boolean remove = linkedList.remove(subscription);
                if (remove) {
                    subscription.unsubscribe();
                }
            }
        }
    }

    @Override // p475rx.Subscription
    public void unsubscribe() {
        if (this.unsubscribed) {
            return;
        }
        synchronized (this) {
            if (this.unsubscribed) {
                return;
            }
            this.unsubscribed = true;
            LinkedList<Subscription> linkedList = this.subscriptions;
            this.subscriptions = null;
            unsubscribeFromAll(linkedList);
        }
    }

    private static void unsubscribeFromAll(Collection<Subscription> collection) {
        if (collection == null) {
            return;
        }
        ArrayList arrayList = null;
        for (Subscription subscription : collection) {
            try {
                subscription.unsubscribe();
            } catch (Throwable th) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(th);
            }
        }
        Exceptions.throwIfAny(arrayList);
    }

    public void clear() {
        LinkedList<Subscription> linkedList;
        if (this.unsubscribed) {
            return;
        }
        synchronized (this) {
            linkedList = this.subscriptions;
            this.subscriptions = null;
        }
        unsubscribeFromAll(linkedList);
    }

    public boolean hasSubscriptions() {
        boolean z = false;
        if (this.unsubscribed) {
            return false;
        }
        synchronized (this) {
            if (!this.unsubscribed && this.subscriptions != null && !this.subscriptions.isEmpty()) {
                z = true;
            }
        }
        return z;
    }
}
