package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Producer;
import p475rx.Subscriber;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorSkip */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorSkip<T> implements Observable.Operator<T, T> {
    final int toSkip;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorSkip(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("n >= 0 required but it was " + i);
        }
        this.toSkip = i;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorSkip.1
            int skipped = 0;

            @Override // p475rx.Observer
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }

            @Override // p475rx.Observer
            public void onNext(T t) {
                if (this.skipped >= OperatorSkip.this.toSkip) {
                    subscriber.onNext(t);
                } else {
                    this.skipped++;
                }
            }

            @Override // p475rx.Subscriber
            public void setProducer(Producer producer) {
                subscriber.setProducer(producer);
                producer.request(OperatorSkip.this.toSkip);
            }
        };
    }
}
