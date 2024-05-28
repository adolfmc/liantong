package p475rx.internal.operators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import p475rx.Notification;
import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.BlockingOperatorNext */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class BlockingOperatorNext {
    private BlockingOperatorNext() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Iterable<T> next(final Observable<? extends T> observable) {
        return new Iterable<T>() { // from class: rx.internal.operators.BlockingOperatorNext.1
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return new NextIterator(Observable.this, new NextObserver());
            }
        };
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.BlockingOperatorNext$NextIterator */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class NextIterator<T> implements Iterator<T> {
        private final Observable<? extends T> items;
        private T next;
        private final NextObserver<T> observer;
        private boolean hasNext = true;
        private boolean isNextConsumed = true;
        private Throwable error = null;
        private boolean started = false;

        NextIterator(Observable<? extends T> observable, NextObserver<T> nextObserver) {
            this.items = observable;
            this.observer = nextObserver;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            Throwable th = this.error;
            if (th != null) {
                throw Exceptions.propagate(th);
            }
            if (this.hasNext) {
                if (this.isNextConsumed) {
                    return moveToNext();
                }
                return true;
            }
            return false;
        }

        private boolean moveToNext() {
            try {
                if (!this.started) {
                    this.started = true;
                    this.observer.setWaiting(1);
                    this.items.materialize().subscribe((Subscriber<? super Notification<? extends T>>) this.observer);
                }
                Notification<? extends T> takeNext = this.observer.takeNext();
                if (takeNext.isOnNext()) {
                    this.isNextConsumed = false;
                    this.next = takeNext.getValue();
                    return true;
                }
                this.hasNext = false;
                if (takeNext.isOnCompleted()) {
                    return false;
                }
                if (takeNext.isOnError()) {
                    this.error = takeNext.getThrowable();
                    throw Exceptions.propagate(this.error);
                }
                throw new IllegalStateException("Should not reach here");
            } catch (InterruptedException e) {
                this.observer.unsubscribe();
                Thread.currentThread().interrupt();
                this.error = e;
                throw Exceptions.propagate(this.error);
            }
        }

        @Override // java.util.Iterator
        public T next() {
            Throwable th = this.error;
            if (th != null) {
                throw Exceptions.propagate(th);
            }
            if (hasNext()) {
                this.isNextConsumed = true;
                return this.next;
            }
            throw new NoSuchElementException("No more elements");
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Read only iterator");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.BlockingOperatorNext$NextObserver */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class NextObserver<T> extends Subscriber<Notification<? extends T>> {
        private final BlockingQueue<Notification<? extends T>> buf = new ArrayBlockingQueue(1);
        final AtomicInteger waiting = new AtomicInteger();

        @Override // p475rx.Observer
        public void onCompleted() {
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
        }

        @Override // p475rx.Observer
        public /* bridge */ /* synthetic */ void onNext(Object obj) {
            onNext((Notification) ((Notification) obj));
        }

        NextObserver() {
        }

        public void onNext(Notification<? extends T> notification) {
            if (this.waiting.getAndSet(0) == 1 || !notification.isOnNext()) {
                while (!this.buf.offer(notification)) {
                    Notification<? extends T> poll = this.buf.poll();
                    if (poll != null && !poll.isOnNext()) {
                        notification = poll;
                    }
                }
            }
        }

        public Notification<? extends T> takeNext() throws InterruptedException {
            setWaiting(1);
            return this.buf.take();
        }

        void setWaiting(int i) {
            this.waiting.set(i);
        }
    }
}
