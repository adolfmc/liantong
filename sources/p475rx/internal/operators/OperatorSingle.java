package p475rx.internal.operators;

import java.util.NoSuchElementException;
import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.internal.producers.SingleProducer;
import p475rx.internal.util.RxJavaPluginUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorSingle */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorSingle<T> implements Observable.Operator<T, T> {
    private final T defaultValue;
    private final boolean hasDefaultValue;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorSingle$Holder */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Holder {
        static final OperatorSingle<?> INSTANCE = new OperatorSingle<>();

        private Holder() {
        }
    }

    public static <T> OperatorSingle<T> instance() {
        return (OperatorSingle<T>) Holder.INSTANCE;
    }

    OperatorSingle() {
        this(false, null);
    }

    public OperatorSingle(T t) {
        this(true, t);
    }

    private OperatorSingle(boolean z, T t) {
        this.hasDefaultValue = z;
        this.defaultValue = t;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        ParentSubscriber parentSubscriber = new ParentSubscriber(subscriber, this.hasDefaultValue, this.defaultValue);
        subscriber.add(parentSubscriber);
        return parentSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorSingle$ParentSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class ParentSubscriber<T> extends Subscriber<T> {
        private final Subscriber<? super T> child;
        private final T defaultValue;
        private final boolean hasDefaultValue;
        private boolean hasTooManyElements;
        private boolean isNonEmpty;
        private T value;

        ParentSubscriber(Subscriber<? super T> subscriber, boolean z, T t) {
            this.child = subscriber;
            this.hasDefaultValue = z;
            this.defaultValue = t;
            request(2L);
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            if (this.hasTooManyElements) {
                return;
            }
            if (this.isNonEmpty) {
                this.hasTooManyElements = true;
                this.child.onError(new IllegalArgumentException("Sequence contains too many elements"));
                unsubscribe();
                return;
            }
            this.value = t;
            this.isNonEmpty = true;
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            if (this.hasTooManyElements) {
                return;
            }
            if (this.isNonEmpty) {
                Subscriber<? super T> subscriber = this.child;
                subscriber.setProducer(new SingleProducer(subscriber, this.value));
            } else if (this.hasDefaultValue) {
                Subscriber<? super T> subscriber2 = this.child;
                subscriber2.setProducer(new SingleProducer(subscriber2, this.defaultValue));
            } else {
                this.child.onError(new NoSuchElementException("Sequence contains no elements"));
            }
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            if (this.hasTooManyElements) {
                RxJavaPluginUtils.handleException(th);
            } else {
                this.child.onError(th);
            }
        }
    }
}
