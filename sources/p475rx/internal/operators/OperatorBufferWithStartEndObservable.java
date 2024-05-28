package p475rx.internal.operators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Func1;
import p475rx.observers.SerializedSubscriber;
import p475rx.subscriptions.CompositeSubscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorBufferWithStartEndObservable */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorBufferWithStartEndObservable<T, TOpening, TClosing> implements Observable.Operator<List<T>, T> {
    final Func1<? super TOpening, ? extends Observable<? extends TClosing>> bufferClosing;
    final Observable<? extends TOpening> bufferOpening;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorBufferWithStartEndObservable(Observable<? extends TOpening> observable, Func1<? super TOpening, ? extends Observable<? extends TClosing>> func1) {
        this.bufferOpening = observable;
        this.bufferClosing = func1;
    }

    public Subscriber<? super T> call(Subscriber<? super List<T>> subscriber) {
        final BufferingSubscriber bufferingSubscriber = new BufferingSubscriber(new SerializedSubscriber(subscriber));
        Subscriber<TOpening> subscriber2 = new Subscriber<TOpening>() { // from class: rx.internal.operators.OperatorBufferWithStartEndObservable.1
            @Override // p475rx.Observer
            public void onNext(TOpening topening) {
                bufferingSubscriber.startBuffer(topening);
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                bufferingSubscriber.onError(th);
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                bufferingSubscriber.onCompleted();
            }
        };
        subscriber.add(subscriber2);
        subscriber.add(bufferingSubscriber);
        this.bufferOpening.unsafeSubscribe(subscriber2);
        return bufferingSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorBufferWithStartEndObservable$BufferingSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public final class BufferingSubscriber extends Subscriber<T> {
        final Subscriber<? super List<T>> child;
        final List<List<T>> chunks = new LinkedList();
        final CompositeSubscription closingSubscriptions = new CompositeSubscription();
        boolean done;

        public BufferingSubscriber(Subscriber<? super List<T>> subscriber) {
            this.child = subscriber;
            add(this.closingSubscriptions);
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            synchronized (this) {
                for (List<T> list : this.chunks) {
                    list.add(t);
                }
            }
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            synchronized (this) {
                if (this.done) {
                    return;
                }
                this.done = true;
                this.chunks.clear();
                this.child.onError(th);
                unsubscribe();
            }
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            try {
                synchronized (this) {
                    if (this.done) {
                        return;
                    }
                    this.done = true;
                    LinkedList<List> linkedList = new LinkedList(this.chunks);
                    this.chunks.clear();
                    for (List list : linkedList) {
                        this.child.onNext(list);
                    }
                    this.child.onCompleted();
                    unsubscribe();
                }
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this.child);
            }
        }

        void startBuffer(TOpening topening) {
            final ArrayList arrayList = new ArrayList();
            synchronized (this) {
                if (this.done) {
                    return;
                }
                this.chunks.add(arrayList);
                try {
                    Observable<? extends TClosing> call = OperatorBufferWithStartEndObservable.this.bufferClosing.call(topening);
                    Subscriber<TClosing> subscriber = new Subscriber<TClosing>() { // from class: rx.internal.operators.OperatorBufferWithStartEndObservable.BufferingSubscriber.1
                        @Override // p475rx.Observer
                        public void onNext(TClosing tclosing) {
                            BufferingSubscriber.this.closingSubscriptions.remove(this);
                            BufferingSubscriber.this.endBuffer(arrayList);
                        }

                        @Override // p475rx.Observer
                        public void onError(Throwable th) {
                            BufferingSubscriber.this.onError(th);
                        }

                        @Override // p475rx.Observer
                        public void onCompleted() {
                            BufferingSubscriber.this.closingSubscriptions.remove(this);
                            BufferingSubscriber.this.endBuffer(arrayList);
                        }
                    };
                    this.closingSubscriptions.add(subscriber);
                    call.unsafeSubscribe(subscriber);
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }
        }

        void endBuffer(List<T> list) {
            boolean z;
            synchronized (this) {
                if (this.done) {
                    return;
                }
                Iterator<List<T>> it = this.chunks.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    } else if (it.next() == list) {
                        z = true;
                        it.remove();
                        break;
                    }
                }
                if (z) {
                    this.child.onNext(list);
                }
            }
        }
    }
}
