package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Func1;
import p475rx.internal.util.UtilityFunctions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorDistinctUntilChanged */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorDistinctUntilChanged<T, U> implements Observable.Operator<T, T> {
    final Func1<? super T, ? extends U> keySelector;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorDistinctUntilChanged$Holder */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Holder {
        static final OperatorDistinctUntilChanged<?, ?> INSTANCE = new OperatorDistinctUntilChanged<>(UtilityFunctions.identity());

        private Holder() {
        }
    }

    public static <T> OperatorDistinctUntilChanged<T, T> instance() {
        return (OperatorDistinctUntilChanged<T, T>) Holder.INSTANCE;
    }

    public OperatorDistinctUntilChanged(Func1<? super T, ? extends U> func1) {
        this.keySelector = func1;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorDistinctUntilChanged.1
            boolean hasPrevious;
            U previousKey;

            @Override // p475rx.Observer
            public void onNext(T t) {
                U u = this.previousKey;
                try {
                    U call = OperatorDistinctUntilChanged.this.keySelector.call(t);
                    this.previousKey = call;
                    if (this.hasPrevious) {
                        if (u != call && (call == null || !call.equals(u))) {
                            subscriber.onNext(t);
                            return;
                        } else {
                            request(1L);
                            return;
                        }
                    }
                    this.hasPrevious = true;
                    subscriber.onNext(t);
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, subscriber, t);
                }
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                subscriber.onCompleted();
            }
        };
    }
}
