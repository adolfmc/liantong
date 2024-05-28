package p475rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import p475rx.Observable;
import p475rx.Producer;
import p475rx.Subscriber;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorElementAt */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorElementAt<T> implements Observable.Operator<T, T> {
    final T defaultValue;
    final boolean hasDefault;
    final int index;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorElementAt(int i) {
        this(i, null, false);
    }

    public OperatorElementAt(int i, T t) {
        this(i, t, true);
    }

    private OperatorElementAt(int i, T t, boolean z) {
        if (i < 0) {
            throw new IndexOutOfBoundsException(i + " is out of bounds");
        }
        this.index = i;
        this.defaultValue = t;
        this.hasDefault = z;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        Subscriber subscriber2 = (Subscriber<T>) new Subscriber<T>() { // from class: rx.internal.operators.OperatorElementAt.1
            private int currentIndex = 0;

            @Override // p475rx.Observer
            public void onNext(T t) {
                int i = this.currentIndex;
                this.currentIndex = i + 1;
                if (i == OperatorElementAt.this.index) {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                    unsubscribe();
                }
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                if (this.currentIndex <= OperatorElementAt.this.index) {
                    if (OperatorElementAt.this.hasDefault) {
                        subscriber.onNext(OperatorElementAt.this.defaultValue);
                        subscriber.onCompleted();
                        return;
                    }
                    Subscriber subscriber3 = subscriber;
                    subscriber3.onError(new IndexOutOfBoundsException(OperatorElementAt.this.index + " is out of bounds"));
                }
            }

            @Override // p475rx.Subscriber
            public void setProducer(Producer producer) {
                subscriber.setProducer(new InnerProducer(producer));
            }
        };
        subscriber.add(subscriber2);
        return subscriber2;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorElementAt$InnerProducer */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class InnerProducer extends AtomicBoolean implements Producer {
        private static final long serialVersionUID = 1;
        final Producer actual;

        public InnerProducer(Producer producer) {
            this.actual = producer;
        }

        @Override // p475rx.Producer
        public void request(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i < 0) {
                throw new IllegalArgumentException("n >= 0 required");
            }
            if (i <= 0 || !compareAndSet(false, true)) {
                return;
            }
            this.actual.request(Long.MAX_VALUE);
        }
    }
}
