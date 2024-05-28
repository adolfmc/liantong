package com.baidu.p120ar.bus;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.bus.Subscription */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class Subscription {
    volatile boolean active = true;
    final Object subscriber;
    final SubscriberMethod subscriberMethod;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Subscription(Object obj, SubscriberMethod subscriberMethod) {
        this.subscriber = obj;
        this.subscriberMethod = subscriberMethod;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Subscription) {
            Subscription subscription = (Subscription) obj;
            return this.subscriber == subscription.subscriber && this.subscriberMethod.equals(subscription.subscriberMethod);
        }
        return false;
    }

    public int hashCode() {
        return this.subscriber.hashCode() + this.subscriberMethod.methodString.hashCode();
    }
}
