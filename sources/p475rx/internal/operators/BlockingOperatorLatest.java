package p475rx.internal.operators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import p475rx.Notification;
import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.BlockingOperatorLatest */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class BlockingOperatorLatest {
    private BlockingOperatorLatest() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Iterable<T> latest(final Observable<? extends T> observable) {
        return new Iterable<T>() { // from class: rx.internal.operators.BlockingOperatorLatest.1
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                LatestObserverIterator latestObserverIterator = new LatestObserverIterator();
                Observable.this.materialize().subscribe((Subscriber<? super Notification<T>>) latestObserverIterator);
                return latestObserverIterator;
            }
        };
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.BlockingOperatorLatest$LatestObserverIterator */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class LatestObserverIterator<T> extends Subscriber<Notification<? extends T>> implements Iterator<T> {
        Notification<? extends T> iNotif;
        final Semaphore notify = new Semaphore(0);
        final AtomicReference<Notification<? extends T>> value = new AtomicReference<>();

        @Override // p475rx.Observer
        public void onCompleted() {
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
        }

        LatestObserverIterator() {
        }

        @Override // p475rx.Observer
        public /* bridge */ /* synthetic */ void onNext(Object obj) {
            onNext((Notification) ((Notification) obj));
        }

        public void onNext(Notification<? extends T> notification) {
            if (this.value.getAndSet(notification) == null) {
                this.notify.release();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            Notification<? extends T> notification = this.iNotif;
            if (notification != null && notification.isOnError()) {
                throw Exceptions.propagate(this.iNotif.getThrowable());
            }
            Notification<? extends T> notification2 = this.iNotif;
            if ((notification2 == null || !notification2.isOnCompleted()) && this.iNotif == null) {
                try {
                    this.notify.acquire();
                    this.iNotif = this.value.getAndSet(null);
                    if (this.iNotif.isOnError()) {
                        throw Exceptions.propagate(this.iNotif.getThrowable());
                    }
                } catch (InterruptedException e) {
                    unsubscribe();
                    Thread.currentThread().interrupt();
                    this.iNotif = Notification.createOnError(e);
                    throw Exceptions.propagate(e);
                }
            }
            return !this.iNotif.isOnCompleted();
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext() && this.iNotif.isOnNext()) {
                T value = this.iNotif.getValue();
                this.iNotif = null;
                return value;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Read-only iterator.");
        }
    }
}
