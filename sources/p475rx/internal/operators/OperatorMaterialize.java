package p475rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import p475rx.Notification;
import p475rx.Observable;
import p475rx.Producer;
import p475rx.Subscriber;
import p475rx.plugins.RxJavaPlugins;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorMaterialize */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorMaterialize<T> implements Observable.Operator<Notification<T>, T> {
    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorMaterialize$Holder */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class Holder {
        static final OperatorMaterialize<Object> INSTANCE = new OperatorMaterialize<>();

        private Holder() {
        }
    }

    public static <T> OperatorMaterialize<T> instance() {
        return (OperatorMaterialize<T>) Holder.INSTANCE;
    }

    OperatorMaterialize() {
    }

    public Subscriber<? super T> call(Subscriber<? super Notification<T>> subscriber) {
        final ParentSubscriber parentSubscriber = new ParentSubscriber(subscriber);
        subscriber.add(parentSubscriber);
        subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorMaterialize.1
            @Override // p475rx.Producer
            public void request(long j) {
                if (j > 0) {
                    parentSubscriber.requestMore(j);
                }
            }
        });
        return parentSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorMaterialize$ParentSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class ParentSubscriber<T> extends Subscriber<T> {
        private final Subscriber<? super Notification<T>> child;
        private volatile Notification<T> terminalNotification;
        private boolean busy = false;
        private boolean missed = false;
        private final AtomicLong requested = new AtomicLong();

        ParentSubscriber(Subscriber<? super Notification<T>> subscriber) {
            this.child = subscriber;
        }

        @Override // p475rx.Subscriber
        public void onStart() {
            request(0L);
        }

        void requestMore(long j) {
            BackpressureUtils.getAndAddRequest(this.requested, j);
            request(j);
            drain();
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            this.terminalNotification = Notification.createOnCompleted();
            drain();
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            this.terminalNotification = Notification.createOnError(th);
            RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
            drain();
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            this.child.onNext(Notification.createOnNext(t));
            decrementRequested();
        }

        private void decrementRequested() {
            long j;
            AtomicLong atomicLong = this.requested;
            do {
                j = atomicLong.get();
                if (j == Long.MAX_VALUE) {
                    return;
                }
            } while (!atomicLong.compareAndSet(j, j - 1));
        }

        private void drain() {
            synchronized (this) {
                if (this.busy) {
                    this.missed = true;
                    return;
                }
                AtomicLong atomicLong = this.requested;
                while (!this.child.isUnsubscribed()) {
                    Notification<T> notification = this.terminalNotification;
                    if (notification != null && atomicLong.get() > 0) {
                        this.terminalNotification = null;
                        this.child.onNext(notification);
                        if (this.child.isUnsubscribed()) {
                            return;
                        }
                        this.child.onCompleted();
                        return;
                    }
                    synchronized (this) {
                        if (!this.missed) {
                            this.busy = false;
                            return;
                        }
                    }
                }
            }
        }
    }
}
