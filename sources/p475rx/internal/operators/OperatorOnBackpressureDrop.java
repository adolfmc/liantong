package p475rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import p475rx.Observable;
import p475rx.Producer;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Action1;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorOnBackpressureDrop */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class OperatorOnBackpressureDrop<T> implements Observable.Operator<T, T> {
    final Action1<? super T> onDrop;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorOnBackpressureDrop$Holder */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class Holder {
        static final OperatorOnBackpressureDrop<Object> INSTANCE = new OperatorOnBackpressureDrop<>();

        private Holder() {
        }
    }

    public static <T> OperatorOnBackpressureDrop<T> instance() {
        return (OperatorOnBackpressureDrop<T>) Holder.INSTANCE;
    }

    OperatorOnBackpressureDrop() {
        this(null);
    }

    public OperatorOnBackpressureDrop(Action1<? super T> action1) {
        this.onDrop = action1;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final AtomicLong atomicLong = new AtomicLong();
        subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorOnBackpressureDrop.1
            @Override // p475rx.Producer
            public void request(long j) {
                BackpressureUtils.getAndAddRequest(atomicLong, j);
            }
        });
        return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorOnBackpressureDrop.2
            @Override // p475rx.Subscriber
            public void onStart() {
                request(Long.MAX_VALUE);
            }

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
                if (atomicLong.get() > 0) {
                    subscriber.onNext(t);
                    atomicLong.decrementAndGet();
                } else if (OperatorOnBackpressureDrop.this.onDrop != null) {
                    try {
                        OperatorOnBackpressureDrop.this.onDrop.call(t);
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, subscriber, t);
                    }
                }
            }
        };
    }
}
