package p475rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.Subscription;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Action0;
import p475rx.functions.Action1;
import p475rx.functions.Func0;
import p475rx.functions.Func1;
import p475rx.observers.Subscribers;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OnSubscribeUsing */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OnSubscribeUsing<T, Resource> implements Observable.OnSubscribe<T> {
    private final Action1<? super Resource> dispose;
    private final boolean disposeEagerly;
    private final Func1<? super Resource, ? extends Observable<? extends T>> observableFactory;
    private final Func0<Resource> resourceFactory;

    @Override // p475rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeUsing(Func0<Resource> func0, Func1<? super Resource, ? extends Observable<? extends T>> func1, Action1<? super Resource> action1, boolean z) {
        this.resourceFactory = func0;
        this.observableFactory = func1;
        this.dispose = action1;
        this.disposeEagerly = z;
    }

    public void call(Subscriber<? super T> subscriber) {
        try {
            Resource call = this.resourceFactory.call();
            DisposeAction disposeAction = new DisposeAction(this.dispose, call);
            subscriber.add(disposeAction);
            Observable<? extends T> call2 = this.observableFactory.call(call);
            if (this.disposeEagerly) {
                call2 = call2.doOnTerminate(disposeAction);
            }
            call2.unsafeSubscribe(Subscribers.wrap(subscriber));
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, subscriber);
        }
    }

    private Throwable disposeEagerlyIfRequested(Action0 action0) {
        if (this.disposeEagerly) {
            try {
                action0.call();
                return null;
            } catch (Throwable th) {
                return th;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OnSubscribeUsing$DisposeAction */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class DisposeAction<Resource> extends AtomicBoolean implements Subscription, Action0 {
        private static final long serialVersionUID = 4262875056400218316L;
        private Action1<? super Resource> dispose;
        private Resource resource;

        DisposeAction(Action1<? super Resource> action1, Resource resource) {
            this.dispose = action1;
            this.resource = resource;
            lazySet(false);
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [Resource, rx.functions.Action1<? super Resource>] */
        @Override // p475rx.functions.Action0
        public void call() {
            if (compareAndSet(false, true)) {
                ?? r0 = (Resource) false;
                try {
                    this.dispose.call((Resource) this.resource);
                } finally {
                    this.resource = null;
                    this.dispose = null;
                }
            }
        }

        @Override // p475rx.Subscription
        public boolean isUnsubscribed() {
            return get();
        }

        @Override // p475rx.Subscription
        public void unsubscribe() {
            call();
        }
    }
}
