package p475rx.internal.util;

import p475rx.Subscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.util.SynchronizedSubscription */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SynchronizedSubscription implements Subscription {

    /* renamed from: s */
    private final Subscription f27627s;

    public SynchronizedSubscription(Subscription subscription) {
        this.f27627s = subscription;
    }

    @Override // p475rx.Subscription
    public synchronized void unsubscribe() {
        this.f27627s.unsubscribe();
    }

    @Override // p475rx.Subscription
    public synchronized boolean isUnsubscribed() {
        return this.f27627s.isUnsubscribed();
    }
}
