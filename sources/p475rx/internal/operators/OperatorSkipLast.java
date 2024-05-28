package p475rx.internal.operators;

import java.util.ArrayDeque;
import java.util.Deque;
import p475rx.Observable;
import p475rx.Subscriber;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorSkipLast */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class OperatorSkipLast<T> implements Observable.Operator<T, T> {
    final int count;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorSkipLast(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("count could not be negative");
        }
        this.count = i;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorSkipLast.1

            /* renamed from: on */
            private final NotificationLite<T> f27603on = NotificationLite.instance();
            private final Deque<Object> deque = new ArrayDeque();

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
                if (OperatorSkipLast.this.count == 0) {
                    subscriber.onNext(t);
                    return;
                }
                if (this.deque.size() == OperatorSkipLast.this.count) {
                    subscriber.onNext(this.f27603on.getValue(this.deque.removeFirst()));
                } else {
                    request(1L);
                }
                this.deque.offerLast(this.f27603on.next(t));
            }
        };
    }
}
