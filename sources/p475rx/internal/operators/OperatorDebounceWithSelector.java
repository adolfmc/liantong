package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Func1;
import p475rx.internal.operators.OperatorDebounceWithTime;
import p475rx.observers.SerializedSubscriber;
import p475rx.subscriptions.SerialSubscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorDebounceWithSelector */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorDebounceWithSelector<T, U> implements Observable.Operator<T, T> {
    final Func1<? super T, ? extends Observable<U>> selector;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorDebounceWithSelector(Func1<? super T, ? extends Observable<U>> func1) {
        this.selector = func1;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        SerialSubscription serialSubscription = new SerialSubscription();
        subscriber.add(serialSubscription);
        return new C139101(subscriber, serializedSubscriber, serialSubscription);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorDebounceWithSelector$1 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public class C139101 extends Subscriber<T> {
        final Subscriber<?> self;
        final OperatorDebounceWithTime.DebounceState<T> state;
        final /* synthetic */ SerializedSubscriber val$s;
        final /* synthetic */ SerialSubscription val$ssub;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C139101(Subscriber subscriber, SerializedSubscriber serializedSubscriber, SerialSubscription serialSubscription) {
            super(subscriber);
            this.val$s = serializedSubscriber;
            this.val$ssub = serialSubscription;
            this.state = new OperatorDebounceWithTime.DebounceState<>();
            this.self = this;
        }

        @Override // p475rx.Subscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            try {
                Observable<U> call = OperatorDebounceWithSelector.this.selector.call(t);
                final int next = this.state.next(t);
                Subscriber<U> subscriber = new Subscriber<U>() { // from class: rx.internal.operators.OperatorDebounceWithSelector.1.1
                    @Override // p475rx.Observer
                    public void onNext(U u) {
                        onCompleted();
                    }

                    @Override // p475rx.Observer
                    public void onError(Throwable th) {
                        C139101.this.self.onError(th);
                    }

                    @Override // p475rx.Observer
                    public void onCompleted() {
                        C139101.this.state.emit(next, C139101.this.val$s, C139101.this.self);
                        unsubscribe();
                    }
                };
                this.val$ssub.set(subscriber);
                call.unsafeSubscribe(subscriber);
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this);
            }
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            this.val$s.onError(th);
            unsubscribe();
            this.state.clear();
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            this.state.emitAndComplete(this.val$s, this);
        }
    }
}
