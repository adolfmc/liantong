package p475rx.internal.operators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.BlockingOperatorMostRecent */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class BlockingOperatorMostRecent {
    private BlockingOperatorMostRecent() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Iterable<T> mostRecent(final Observable<? extends T> observable, final T t) {
        return new Iterable<T>() { // from class: rx.internal.operators.BlockingOperatorMostRecent.1
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                MostRecentObserver mostRecentObserver = new MostRecentObserver(t);
                observable.subscribe((Subscriber) mostRecentObserver);
                return mostRecentObserver.getIterable();
            }
        };
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.BlockingOperatorMostRecent$MostRecentObserver */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class MostRecentObserver<T> extends Subscriber<T> {

        /* renamed from: nl */
        final NotificationLite<T> f27575nl = NotificationLite.instance();
        volatile Object value;

        MostRecentObserver(T t) {
            this.value = this.f27575nl.next(t);
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            this.value = this.f27575nl.completed();
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            this.value = this.f27575nl.error(th);
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            this.value = this.f27575nl.next(t);
        }

        public Iterator<T> getIterable() {
            return new Iterator<T>() { // from class: rx.internal.operators.BlockingOperatorMostRecent.MostRecentObserver.1
                private Object buf = null;

                @Override // java.util.Iterator
                public boolean hasNext() {
                    this.buf = MostRecentObserver.this.value;
                    return !MostRecentObserver.this.f27575nl.isCompleted(this.buf);
                }

                @Override // java.util.Iterator
                public T next() {
                    try {
                        if (this.buf == null) {
                            this.buf = MostRecentObserver.this.value;
                        }
                        if (MostRecentObserver.this.f27575nl.isCompleted(this.buf)) {
                            throw new NoSuchElementException();
                        }
                        if (MostRecentObserver.this.f27575nl.isError(this.buf)) {
                            throw Exceptions.propagate(MostRecentObserver.this.f27575nl.getError(this.buf));
                        }
                        return MostRecentObserver.this.f27575nl.getValue(this.buf);
                    } finally {
                        this.buf = null;
                    }
                }

                @Override // java.util.Iterator
                public void remove() {
                    throw new UnsupportedOperationException("Read only iterator");
                }
            };
        }
    }
}
