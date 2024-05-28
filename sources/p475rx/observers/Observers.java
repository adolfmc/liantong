package p475rx.observers;

import p475rx.Observer;
import p475rx.exceptions.OnErrorNotImplementedException;
import p475rx.functions.Action0;
import p475rx.functions.Action1;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.observers.Observers */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class Observers {
    private static final Observer<Object> EMPTY = new Observer<Object>() { // from class: rx.observers.Observers.1
        @Override // p475rx.Observer
        public final void onCompleted() {
        }

        @Override // p475rx.Observer
        public final void onNext(Object obj) {
        }

        @Override // p475rx.Observer
        public final void onError(Throwable th) {
            throw new OnErrorNotImplementedException(th);
        }
    };

    private Observers() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Observer<T> empty() {
        return (Observer<T>) EMPTY;
    }

    public static <T> Observer<T> create(final Action1<? super T> action1) {
        if (action1 == null) {
            throw new IllegalArgumentException("onNext can not be null");
        }
        return new Observer<T>() { // from class: rx.observers.Observers.2
            @Override // p475rx.Observer
            public final void onCompleted() {
            }

            @Override // p475rx.Observer
            public final void onError(Throwable th) {
                throw new OnErrorNotImplementedException(th);
            }

            @Override // p475rx.Observer
            public final void onNext(T t) {
                Action1.this.call(t);
            }
        };
    }

    public static <T> Observer<T> create(final Action1<? super T> action1, final Action1<Throwable> action12) {
        if (action1 != null) {
            if (action12 == null) {
                throw new IllegalArgumentException("onError can not be null");
            }
            return new Observer<T>() { // from class: rx.observers.Observers.3
                @Override // p475rx.Observer
                public final void onCompleted() {
                }

                @Override // p475rx.Observer
                public final void onError(Throwable th) {
                    Action1.this.call(th);
                }

                @Override // p475rx.Observer
                public final void onNext(T t) {
                    action1.call(t);
                }
            };
        }
        throw new IllegalArgumentException("onNext can not be null");
    }

    public static <T> Observer<T> create(final Action1<? super T> action1, final Action1<Throwable> action12, final Action0 action0) {
        if (action1 != null) {
            if (action12 != null) {
                if (action0 == null) {
                    throw new IllegalArgumentException("onComplete can not be null");
                }
                return new Observer<T>() { // from class: rx.observers.Observers.4
                    @Override // p475rx.Observer
                    public final void onCompleted() {
                        Action0.this.call();
                    }

                    @Override // p475rx.Observer
                    public final void onError(Throwable th) {
                        action12.call(th);
                    }

                    @Override // p475rx.Observer
                    public final void onNext(T t) {
                        action1.call(t);
                    }
                };
            }
            throw new IllegalArgumentException("onError can not be null");
        }
        throw new IllegalArgumentException("onNext can not be null");
    }
}
