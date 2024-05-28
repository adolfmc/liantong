package p475rx.subjects;

import java.util.ArrayList;
import p475rx.Observable;
import p475rx.annotations.Beta;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Action1;
import p475rx.internal.operators.NotificationLite;
import p475rx.internal.producers.SingleProducer;
import p475rx.subjects.SubjectSubscriptionManager;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.subjects.AsyncSubject */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class AsyncSubject<T> extends Subject<T, T> {
    volatile Object lastValue;

    /* renamed from: nl */
    private final NotificationLite<T> f27642nl;
    final SubjectSubscriptionManager<T> state;

    public static <T> AsyncSubject<T> create() {
        final SubjectSubscriptionManager subjectSubscriptionManager = new SubjectSubscriptionManager();
        subjectSubscriptionManager.onTerminated = new Action1<SubjectSubscriptionManager.SubjectObserver<T>>() { // from class: rx.subjects.AsyncSubject.1
            @Override // p475rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((SubjectSubscriptionManager.SubjectObserver) ((SubjectSubscriptionManager.SubjectObserver) obj));
            }

            public void call(SubjectSubscriptionManager.SubjectObserver<T> subjectObserver) {
                Object latest = SubjectSubscriptionManager.this.getLatest();
                NotificationLite<T> notificationLite = SubjectSubscriptionManager.this.f27647nl;
                if (latest == null || notificationLite.isCompleted(latest)) {
                    subjectObserver.onCompleted();
                } else if (notificationLite.isError(latest)) {
                    subjectObserver.onError(notificationLite.getError(latest));
                } else {
                    subjectObserver.actual.setProducer(new SingleProducer(subjectObserver.actual, notificationLite.getValue(latest)));
                }
            }
        };
        return new AsyncSubject<>(subjectSubscriptionManager, subjectSubscriptionManager);
    }

    protected AsyncSubject(Observable.OnSubscribe<T> onSubscribe, SubjectSubscriptionManager<T> subjectSubscriptionManager) {
        super(onSubscribe);
        this.f27642nl = NotificationLite.instance();
        this.state = subjectSubscriptionManager;
    }

    @Override // p475rx.Observer
    public void onCompleted() {
        SubjectSubscriptionManager.SubjectObserver<T>[] terminate;
        if (this.state.active) {
            Object obj = this.lastValue;
            if (obj == null) {
                obj = this.f27642nl.completed();
            }
            for (SubjectSubscriptionManager.SubjectObserver<T> subjectObserver : this.state.terminate(obj)) {
                if (obj == this.f27642nl.completed()) {
                    subjectObserver.onCompleted();
                } else {
                    subjectObserver.actual.setProducer(new SingleProducer(subjectObserver.actual, this.f27642nl.getValue(obj)));
                }
            }
        }
    }

    @Override // p475rx.Observer
    public void onError(Throwable th) {
        if (this.state.active) {
            ArrayList arrayList = null;
            for (SubjectSubscriptionManager.SubjectObserver<T> subjectObserver : this.state.terminate(this.f27642nl.error(th))) {
                try {
                    subjectObserver.onError(th);
                } catch (Throwable th2) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(th2);
                }
            }
            Exceptions.throwIfAny(arrayList);
        }
    }

    @Override // p475rx.Observer
    public void onNext(T t) {
        this.lastValue = this.f27642nl.next(t);
    }

    @Override // p475rx.subjects.Subject
    public boolean hasObservers() {
        return this.state.observers().length > 0;
    }

    @Beta
    public boolean hasValue() {
        return !this.f27642nl.isError(this.state.getLatest()) && this.f27642nl.isNext(this.lastValue);
    }

    @Beta
    public boolean hasThrowable() {
        return this.f27642nl.isError(this.state.getLatest());
    }

    @Beta
    public boolean hasCompleted() {
        Object latest = this.state.getLatest();
        return (latest == null || this.f27642nl.isError(latest)) ? false : true;
    }

    @Beta
    public T getValue() {
        Object obj = this.lastValue;
        if (this.f27642nl.isError(this.state.getLatest()) || !this.f27642nl.isNext(obj)) {
            return null;
        }
        return this.f27642nl.getValue(obj);
    }

    @Beta
    public Throwable getThrowable() {
        Object latest = this.state.getLatest();
        if (this.f27642nl.isError(latest)) {
            return this.f27642nl.getError(latest);
        }
        return null;
    }
}
