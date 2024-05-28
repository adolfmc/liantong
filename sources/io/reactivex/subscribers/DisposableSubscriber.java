package io.reactivex.subscribers;

import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.EndConsumerHelper;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class DisposableSubscriber<T> implements FlowableSubscriber<T>, Disposable {

    /* renamed from: s */
    final AtomicReference<Subscription> f24809s = new AtomicReference<>();

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public final void onSubscribe(Subscription subscription) {
        if (EndConsumerHelper.setOnce(this.f24809s, subscription, getClass())) {
            onStart();
        }
    }

    protected void onStart() {
        this.f24809s.get().request(Long.MAX_VALUE);
    }

    protected final void request(long j) {
        this.f24809s.get().request(j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void cancel() {
        dispose();
    }

    @Override // io.reactivex.disposables.Disposable
    public final boolean isDisposed() {
        return this.f24809s.get() == SubscriptionHelper.CANCELLED;
    }

    @Override // io.reactivex.disposables.Disposable
    public final void dispose() {
        SubscriptionHelper.cancel(this.f24809s);
    }
}
