package p475rx.internal.operators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.Subscription;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Func1;
import p475rx.functions.Func2;
import p475rx.observers.SerializedSubscriber;
import p475rx.subscriptions.CompositeSubscription;
import p475rx.subscriptions.SerialSubscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OnSubscribeJoin */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OnSubscribeJoin<TLeft, TRight, TLeftDuration, TRightDuration, R> implements Observable.OnSubscribe<R> {
    final Observable<TLeft> left;
    final Func1<TLeft, Observable<TLeftDuration>> leftDurationSelector;
    final Func2<TLeft, TRight, R> resultSelector;
    final Observable<TRight> right;
    final Func1<TRight, Observable<TRightDuration>> rightDurationSelector;

    @Override // p475rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeJoin(Observable<TLeft> observable, Observable<TRight> observable2, Func1<TLeft, Observable<TLeftDuration>> func1, Func1<TRight, Observable<TRightDuration>> func12, Func2<TLeft, TRight, R> func2) {
        this.left = observable;
        this.right = observable2;
        this.leftDurationSelector = func1;
        this.rightDurationSelector = func12;
        this.resultSelector = func2;
    }

    public void call(Subscriber<? super R> subscriber) {
        new ResultSink(new SerializedSubscriber(subscriber)).run();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OnSubscribeJoin$ResultSink */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public final class ResultSink {
        boolean leftDone;
        int leftId;
        boolean rightDone;
        int rightId;
        final Subscriber<? super R> subscriber;
        final Object guard = new Object();

        /* renamed from: group  reason: collision with root package name */
        final CompositeSubscription f27875group = new CompositeSubscription();
        final Map<Integer, TLeft> leftMap = new HashMap();
        final Map<Integer, TRight> rightMap = new HashMap();

        public ResultSink(Subscriber<? super R> subscriber) {
            this.subscriber = subscriber;
        }

        public void run() {
            this.subscriber.add(this.f27875group);
            LeftSubscriber leftSubscriber = new LeftSubscriber();
            RightSubscriber rightSubscriber = new RightSubscriber();
            this.f27875group.add(leftSubscriber);
            this.f27875group.add(rightSubscriber);
            OnSubscribeJoin.this.left.unsafeSubscribe(leftSubscriber);
            OnSubscribeJoin.this.right.unsafeSubscribe(rightSubscriber);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: rx.internal.operators.OnSubscribeJoin$ResultSink$LeftSubscriber */
        /* loaded from: E:\9227576_dexfile_execute.dex */
        public final class LeftSubscriber extends Subscriber<TLeft> {
            LeftSubscriber() {
            }

            protected void expire(int i, Subscription subscription) {
                boolean z;
                synchronized (ResultSink.this.guard) {
                    z = ResultSink.this.leftMap.remove(Integer.valueOf(i)) != null && ResultSink.this.leftMap.isEmpty() && ResultSink.this.leftDone;
                }
                if (z) {
                    ResultSink.this.subscriber.onCompleted();
                    ResultSink.this.subscriber.unsubscribe();
                    return;
                }
                ResultSink.this.f27875group.remove(subscription);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // p475rx.Observer
            public void onNext(TLeft tleft) {
                int i;
                int i2;
                synchronized (ResultSink.this.guard) {
                    ResultSink resultSink = ResultSink.this;
                    i = resultSink.leftId;
                    resultSink.leftId = i + 1;
                    ResultSink.this.leftMap.put(Integer.valueOf(i), tleft);
                    i2 = ResultSink.this.rightId;
                }
                try {
                    LeftDurationSubscriber leftDurationSubscriber = new LeftDurationSubscriber(i);
                    ResultSink.this.f27875group.add(leftDurationSubscriber);
                    OnSubscribeJoin.this.leftDurationSelector.call(tleft).unsafeSubscribe(leftDurationSubscriber);
                    ArrayList<Object> arrayList = new ArrayList();
                    synchronized (ResultSink.this.guard) {
                        for (Map.Entry<Integer, TRight> entry : ResultSink.this.rightMap.entrySet()) {
                            if (entry.getKey().intValue() < i2) {
                                arrayList.add(entry.getValue());
                            }
                        }
                    }
                    for (Object obj : arrayList) {
                        ResultSink.this.subscriber.onNext(OnSubscribeJoin.this.resultSelector.call(tleft, obj));
                    }
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                ResultSink.this.subscriber.onError(th);
                ResultSink.this.subscriber.unsubscribe();
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                boolean z;
                synchronized (ResultSink.this.guard) {
                    z = true;
                    ResultSink.this.leftDone = true;
                    if (!ResultSink.this.rightDone && !ResultSink.this.leftMap.isEmpty()) {
                        z = false;
                    }
                }
                if (z) {
                    ResultSink.this.subscriber.onCompleted();
                    ResultSink.this.subscriber.unsubscribe();
                    return;
                }
                ResultSink.this.f27875group.remove(this);
            }

            /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
            /* renamed from: rx.internal.operators.OnSubscribeJoin$ResultSink$LeftSubscriber$LeftDurationSubscriber */
            /* loaded from: E:\9227576_dexfile_execute.dex */
            final class LeftDurationSubscriber extends Subscriber<TLeftDuration> {

                /* renamed from: id */
                final int f27590id;
                boolean once = true;

                public LeftDurationSubscriber(int i) {
                    this.f27590id = i;
                }

                @Override // p475rx.Observer
                public void onNext(TLeftDuration tleftduration) {
                    onCompleted();
                }

                @Override // p475rx.Observer
                public void onError(Throwable th) {
                    LeftSubscriber.this.onError(th);
                }

                @Override // p475rx.Observer
                public void onCompleted() {
                    if (this.once) {
                        this.once = false;
                        LeftSubscriber.this.expire(this.f27590id, this);
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: rx.internal.operators.OnSubscribeJoin$ResultSink$RightSubscriber */
        /* loaded from: E:\9227576_dexfile_execute.dex */
        public final class RightSubscriber extends Subscriber<TRight> {
            RightSubscriber() {
            }

            void expire(int i, Subscription subscription) {
                boolean z;
                synchronized (ResultSink.this.guard) {
                    z = ResultSink.this.rightMap.remove(Integer.valueOf(i)) != null && ResultSink.this.rightMap.isEmpty() && ResultSink.this.rightDone;
                }
                if (z) {
                    ResultSink.this.subscriber.onCompleted();
                    ResultSink.this.subscriber.unsubscribe();
                    return;
                }
                ResultSink.this.f27875group.remove(subscription);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // p475rx.Observer
            public void onNext(TRight tright) {
                int i;
                int i2;
                synchronized (ResultSink.this.guard) {
                    ResultSink resultSink = ResultSink.this;
                    i = resultSink.rightId;
                    resultSink.rightId = i + 1;
                    ResultSink.this.rightMap.put(Integer.valueOf(i), tright);
                    i2 = ResultSink.this.leftId;
                }
                ResultSink.this.f27875group.add(new SerialSubscription());
                try {
                    RightDurationSubscriber rightDurationSubscriber = new RightDurationSubscriber(i);
                    ResultSink.this.f27875group.add(rightDurationSubscriber);
                    OnSubscribeJoin.this.rightDurationSelector.call(tright).unsafeSubscribe(rightDurationSubscriber);
                    ArrayList<Object> arrayList = new ArrayList();
                    synchronized (ResultSink.this.guard) {
                        for (Map.Entry<Integer, TLeft> entry : ResultSink.this.leftMap.entrySet()) {
                            if (entry.getKey().intValue() < i2) {
                                arrayList.add(entry.getValue());
                            }
                        }
                    }
                    for (Object obj : arrayList) {
                        ResultSink.this.subscriber.onNext(OnSubscribeJoin.this.resultSelector.call(obj, tright));
                    }
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                ResultSink.this.subscriber.onError(th);
                ResultSink.this.subscriber.unsubscribe();
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                boolean z;
                synchronized (ResultSink.this.guard) {
                    z = true;
                    ResultSink.this.rightDone = true;
                    if (!ResultSink.this.leftDone && !ResultSink.this.rightMap.isEmpty()) {
                        z = false;
                    }
                }
                if (z) {
                    ResultSink.this.subscriber.onCompleted();
                    ResultSink.this.subscriber.unsubscribe();
                    return;
                }
                ResultSink.this.f27875group.remove(this);
            }

            /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
            /* renamed from: rx.internal.operators.OnSubscribeJoin$ResultSink$RightSubscriber$RightDurationSubscriber */
            /* loaded from: E:\9227576_dexfile_execute.dex */
            final class RightDurationSubscriber extends Subscriber<TRightDuration> {

                /* renamed from: id */
                final int f27591id;
                boolean once = true;

                public RightDurationSubscriber(int i) {
                    this.f27591id = i;
                }

                @Override // p475rx.Observer
                public void onNext(TRightDuration trightduration) {
                    onCompleted();
                }

                @Override // p475rx.Observer
                public void onError(Throwable th) {
                    RightSubscriber.this.onError(th);
                }

                @Override // p475rx.Observer
                public void onCompleted() {
                    if (this.once) {
                        this.once = false;
                        RightSubscriber.this.expire(this.f27591id, this);
                    }
                }
            }
        }
    }
}
