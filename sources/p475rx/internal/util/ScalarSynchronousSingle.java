package p475rx.internal.util;

import p475rx.Scheduler;
import p475rx.Single;
import p475rx.SingleSubscriber;
import p475rx.Subscriber;
import p475rx.functions.Action0;
import p475rx.functions.Func1;
import p475rx.internal.schedulers.EventLoopsScheduler;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.util.ScalarSynchronousSingle */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class ScalarSynchronousSingle<T> extends Single<T> {
    final T value;

    public static final <T> ScalarSynchronousSingle<T> create(T t) {
        return new ScalarSynchronousSingle<>(t);
    }

    protected ScalarSynchronousSingle(final T t) {
        super(new Single.OnSubscribe<T>() { // from class: rx.internal.util.ScalarSynchronousSingle.1
            @Override // p475rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((SingleSubscriber) ((SingleSubscriber) obj));
            }

            public void call(SingleSubscriber<? super T> singleSubscriber) {
                singleSubscriber.onSuccess((Object) t);
            }
        });
        this.value = t;
    }

    public T get() {
        return this.value;
    }

    public Single<T> scalarScheduleOn(Scheduler scheduler) {
        if (scheduler instanceof EventLoopsScheduler) {
            return create((Single.OnSubscribe) new DirectScheduledEmission((EventLoopsScheduler) scheduler, this.value));
        }
        return create((Single.OnSubscribe) new NormalScheduledEmission(scheduler, this.value));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.util.ScalarSynchronousSingle$DirectScheduledEmission */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class DirectScheduledEmission<T> implements Single.OnSubscribe<T> {

        /* renamed from: es */
        private final EventLoopsScheduler f27626es;
        private final T value;

        @Override // p475rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((SingleSubscriber) ((SingleSubscriber) obj));
        }

        DirectScheduledEmission(EventLoopsScheduler eventLoopsScheduler, T t) {
            this.f27626es = eventLoopsScheduler;
            this.value = t;
        }

        public void call(SingleSubscriber<? super T> singleSubscriber) {
            singleSubscriber.add(this.f27626es.scheduleDirect(new ScalarSynchronousSingleAction(singleSubscriber, this.value)));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.util.ScalarSynchronousSingle$NormalScheduledEmission */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class NormalScheduledEmission<T> implements Single.OnSubscribe<T> {
        private final Scheduler scheduler;
        private final T value;

        @Override // p475rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((SingleSubscriber) ((SingleSubscriber) obj));
        }

        NormalScheduledEmission(Scheduler scheduler, T t) {
            this.scheduler = scheduler;
            this.value = t;
        }

        public void call(SingleSubscriber<? super T> singleSubscriber) {
            Scheduler.Worker createWorker = this.scheduler.createWorker();
            singleSubscriber.add(createWorker);
            createWorker.schedule(new ScalarSynchronousSingleAction(singleSubscriber, this.value));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.util.ScalarSynchronousSingle$ScalarSynchronousSingleAction */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class ScalarSynchronousSingleAction<T> implements Action0 {
        private final SingleSubscriber<? super T> subscriber;
        private final T value;

        ScalarSynchronousSingleAction(SingleSubscriber<? super T> singleSubscriber, T t) {
            this.subscriber = singleSubscriber;
            this.value = t;
        }

        @Override // p475rx.functions.Action0
        public void call() {
            try {
                this.subscriber.onSuccess((T) this.value);
            } catch (Throwable th) {
                this.subscriber.onError(th);
            }
        }
    }

    public <R> Single<R> scalarFlatMap(final Func1<? super T, ? extends Single<? extends R>> func1) {
        return create((Single.OnSubscribe) new Single.OnSubscribe<R>() { // from class: rx.internal.util.ScalarSynchronousSingle.2
            @Override // p475rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((SingleSubscriber) ((SingleSubscriber) obj));
            }

            public void call(final SingleSubscriber<? super R> singleSubscriber) {
                Single single = (Single) func1.call(ScalarSynchronousSingle.this.value);
                if (single instanceof ScalarSynchronousSingle) {
                    singleSubscriber.onSuccess((T) ((ScalarSynchronousSingle) single).value);
                    return;
                }
                Subscriber<R> subscriber = new Subscriber<R>() { // from class: rx.internal.util.ScalarSynchronousSingle.2.1
                    @Override // p475rx.Observer
                    public void onCompleted() {
                    }

                    @Override // p475rx.Observer
                    public void onError(Throwable th) {
                        singleSubscriber.onError(th);
                    }

                    @Override // p475rx.Observer
                    public void onNext(R r) {
                        singleSubscriber.onSuccess(r);
                    }
                };
                singleSubscriber.add(subscriber);
                single.unsafeSubscribe(subscriber);
            }
        });
    }
}
