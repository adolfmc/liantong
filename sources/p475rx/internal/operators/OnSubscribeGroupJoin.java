package p475rx.internal.operators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import p475rx.Observable;
import p475rx.Observer;
import p475rx.Subscriber;
import p475rx.Subscription;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Func1;
import p475rx.functions.Func2;
import p475rx.observers.SerializedObserver;
import p475rx.observers.SerializedSubscriber;
import p475rx.subjects.PublishSubject;
import p475rx.subscriptions.CompositeSubscription;
import p475rx.subscriptions.RefCountSubscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OnSubscribeGroupJoin */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OnSubscribeGroupJoin<T1, T2, D1, D2, R> implements Observable.OnSubscribe<R> {
    protected final Observable<T1> left;
    protected final Func1<? super T1, ? extends Observable<D1>> leftDuration;
    protected final Func2<? super T1, ? super Observable<T2>, ? extends R> resultSelector;
    protected final Observable<T2> right;
    protected final Func1<? super T2, ? extends Observable<D2>> rightDuration;

    @Override // p475rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeGroupJoin(Observable<T1> observable, Observable<T2> observable2, Func1<? super T1, ? extends Observable<D1>> func1, Func1<? super T2, ? extends Observable<D2>> func12, Func2<? super T1, ? super Observable<T2>, ? extends R> func2) {
        this.left = observable;
        this.right = observable2;
        this.leftDuration = func1;
        this.rightDuration = func12;
        this.resultSelector = func2;
    }

    public void call(Subscriber<? super R> subscriber) {
        ResultManager resultManager = new ResultManager(new SerializedSubscriber(subscriber));
        subscriber.add(resultManager);
        resultManager.init();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OnSubscribeGroupJoin$ResultManager */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public final class ResultManager implements Subscription {
        boolean leftDone;
        int leftIds;
        boolean rightDone;
        int rightIds;
        final Subscriber<? super R> subscriber;
        final Object guard = new Object();
        final Map<Integer, Observer<T2>> leftMap = new HashMap();
        final Map<Integer, T2> rightMap = new HashMap();

        /* renamed from: group  reason: collision with root package name */
        final CompositeSubscription f27874group = new CompositeSubscription();
        final RefCountSubscription cancel = new RefCountSubscription(this.f27874group);

        public ResultManager(Subscriber<? super R> subscriber) {
            this.subscriber = subscriber;
        }

        public void init() {
            LeftObserver leftObserver = new LeftObserver();
            RightObserver rightObserver = new RightObserver();
            this.f27874group.add(leftObserver);
            this.f27874group.add(rightObserver);
            OnSubscribeGroupJoin.this.left.unsafeSubscribe(leftObserver);
            OnSubscribeGroupJoin.this.right.unsafeSubscribe(rightObserver);
        }

        @Override // p475rx.Subscription
        public void unsubscribe() {
            this.cancel.unsubscribe();
        }

        @Override // p475rx.Subscription
        public boolean isUnsubscribed() {
            return this.cancel.isUnsubscribed();
        }

        void errorAll(Throwable th) {
            ArrayList<Observer> arrayList;
            synchronized (this.guard) {
                arrayList = new ArrayList(this.leftMap.values());
                this.leftMap.clear();
                this.rightMap.clear();
            }
            for (Observer observer : arrayList) {
                observer.onError(th);
            }
            this.subscriber.onError(th);
            this.cancel.unsubscribe();
        }

        void errorMain(Throwable th) {
            synchronized (this.guard) {
                this.leftMap.clear();
                this.rightMap.clear();
            }
            this.subscriber.onError(th);
            this.cancel.unsubscribe();
        }

        void complete(List<Observer<T2>> list) {
            if (list != null) {
                for (Observer<T2> observer : list) {
                    observer.onCompleted();
                }
                this.subscriber.onCompleted();
                this.cancel.unsubscribe();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: rx.internal.operators.OnSubscribeGroupJoin$ResultManager$LeftObserver */
        /* loaded from: E:\9227576_dexfile_execute.dex */
        public final class LeftObserver extends Subscriber<T1> {
            LeftObserver() {
            }

            @Override // p475rx.Observer
            public void onNext(T1 t1) {
                int i;
                ArrayList<Object> arrayList;
                try {
                    PublishSubject create = PublishSubject.create();
                    SerializedObserver serializedObserver = new SerializedObserver(create);
                    synchronized (ResultManager.this.guard) {
                        ResultManager resultManager = ResultManager.this;
                        i = resultManager.leftIds;
                        resultManager.leftIds = i + 1;
                        ResultManager.this.leftMap.put(Integer.valueOf(i), serializedObserver);
                    }
                    Observable create2 = Observable.create(new WindowObservableFunc(create, ResultManager.this.cancel));
                    LeftDurationObserver leftDurationObserver = new LeftDurationObserver(i);
                    ResultManager.this.f27874group.add(leftDurationObserver);
                    OnSubscribeGroupJoin.this.leftDuration.call(t1).unsafeSubscribe(leftDurationObserver);
                    R call = OnSubscribeGroupJoin.this.resultSelector.call(t1, create2);
                    synchronized (ResultManager.this.guard) {
                        arrayList = new ArrayList(ResultManager.this.rightMap.values());
                    }
                    ResultManager.this.subscriber.onNext(call);
                    for (Object obj : arrayList) {
                        serializedObserver.onNext(obj);
                    }
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                ArrayList arrayList;
                synchronized (ResultManager.this.guard) {
                    ResultManager.this.leftDone = true;
                    if (ResultManager.this.rightDone) {
                        arrayList = new ArrayList(ResultManager.this.leftMap.values());
                        ResultManager.this.leftMap.clear();
                        ResultManager.this.rightMap.clear();
                    } else {
                        arrayList = null;
                    }
                }
                ResultManager.this.complete(arrayList);
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                ResultManager.this.errorAll(th);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: rx.internal.operators.OnSubscribeGroupJoin$ResultManager$RightObserver */
        /* loaded from: E:\9227576_dexfile_execute.dex */
        public final class RightObserver extends Subscriber<T2> {
            RightObserver() {
            }

            @Override // p475rx.Observer
            public void onNext(T2 t2) {
                int i;
                ArrayList<Observer> arrayList;
                try {
                    synchronized (ResultManager.this.guard) {
                        ResultManager resultManager = ResultManager.this;
                        i = resultManager.rightIds;
                        resultManager.rightIds = i + 1;
                        ResultManager.this.rightMap.put(Integer.valueOf(i), t2);
                    }
                    RightDurationObserver rightDurationObserver = new RightDurationObserver(i);
                    ResultManager.this.f27874group.add(rightDurationObserver);
                    OnSubscribeGroupJoin.this.rightDuration.call(t2).unsafeSubscribe(rightDurationObserver);
                    synchronized (ResultManager.this.guard) {
                        arrayList = new ArrayList(ResultManager.this.leftMap.values());
                    }
                    for (Observer observer : arrayList) {
                        observer.onNext(t2);
                    }
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                ArrayList arrayList;
                synchronized (ResultManager.this.guard) {
                    ResultManager.this.rightDone = true;
                    if (ResultManager.this.leftDone) {
                        arrayList = new ArrayList(ResultManager.this.leftMap.values());
                        ResultManager.this.leftMap.clear();
                        ResultManager.this.rightMap.clear();
                    } else {
                        arrayList = null;
                    }
                }
                ResultManager.this.complete(arrayList);
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                ResultManager.this.errorAll(th);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: rx.internal.operators.OnSubscribeGroupJoin$ResultManager$LeftDurationObserver */
        /* loaded from: E:\9227576_dexfile_execute.dex */
        final class LeftDurationObserver extends Subscriber<D1> {

            /* renamed from: id */
            final int f27588id;
            boolean once = true;

            public LeftDurationObserver(int i) {
                this.f27588id = i;
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                Observer<T2> remove;
                if (this.once) {
                    this.once = false;
                    synchronized (ResultManager.this.guard) {
                        remove = ResultManager.this.leftMap.remove(Integer.valueOf(this.f27588id));
                    }
                    if (remove != null) {
                        remove.onCompleted();
                    }
                    ResultManager.this.f27874group.remove(this);
                }
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                ResultManager.this.errorMain(th);
            }

            @Override // p475rx.Observer
            public void onNext(D1 d1) {
                onCompleted();
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: rx.internal.operators.OnSubscribeGroupJoin$ResultManager$RightDurationObserver */
        /* loaded from: E:\9227576_dexfile_execute.dex */
        final class RightDurationObserver extends Subscriber<D2> {

            /* renamed from: id */
            final int f27589id;
            boolean once = true;

            public RightDurationObserver(int i) {
                this.f27589id = i;
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                if (this.once) {
                    this.once = false;
                    synchronized (ResultManager.this.guard) {
                        ResultManager.this.rightMap.remove(Integer.valueOf(this.f27589id));
                    }
                    ResultManager.this.f27874group.remove(this);
                }
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                ResultManager.this.errorMain(th);
            }

            @Override // p475rx.Observer
            public void onNext(D2 d2) {
                onCompleted();
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OnSubscribeGroupJoin$WindowObservableFunc */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class WindowObservableFunc<T> implements Observable.OnSubscribe<T> {
        final RefCountSubscription refCount;
        final Observable<T> underlying;

        @Override // p475rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        public WindowObservableFunc(Observable<T> observable, RefCountSubscription refCountSubscription) {
            this.refCount = refCountSubscription;
            this.underlying = observable;
        }

        public void call(Subscriber<? super T> subscriber) {
            Subscription subscription = this.refCount.get();
            WindowSubscriber windowSubscriber = new WindowSubscriber(subscriber, subscription);
            windowSubscriber.add(subscription);
            this.underlying.unsafeSubscribe(windowSubscriber);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: rx.internal.operators.OnSubscribeGroupJoin$WindowObservableFunc$WindowSubscriber */
        /* loaded from: E:\9227576_dexfile_execute.dex */
        public final class WindowSubscriber extends Subscriber<T> {
            private final Subscription ref;
            final Subscriber<? super T> subscriber;

            public WindowSubscriber(Subscriber<? super T> subscriber, Subscription subscription) {
                super(subscriber);
                this.subscriber = subscriber;
                this.ref = subscription;
            }

            @Override // p475rx.Observer
            public void onNext(T t) {
                this.subscriber.onNext(t);
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                this.subscriber.onError(th);
                this.ref.unsubscribe();
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                this.subscriber.onCompleted();
                this.ref.unsubscribe();
            }
        }
    }
}
