package p475rx.subjects;

import java.util.ArrayList;
import p475rx.Observable;
import p475rx.annotations.Beta;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Action1;
import p475rx.internal.operators.NotificationLite;
import p475rx.subjects.SubjectSubscriptionManager;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.subjects.PublishSubject */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class PublishSubject<T> extends Subject<T, T> {

    /* renamed from: nl */
    private final NotificationLite<T> f27644nl;
    final SubjectSubscriptionManager<T> state;

    public static <T> PublishSubject<T> create() {
        final SubjectSubscriptionManager subjectSubscriptionManager = new SubjectSubscriptionManager();
        subjectSubscriptionManager.onTerminated = new Action1<SubjectSubscriptionManager.SubjectObserver<T>>() { // from class: rx.subjects.PublishSubject.1
            @Override // p475rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((SubjectSubscriptionManager.SubjectObserver) ((SubjectSubscriptionManager.SubjectObserver) obj));
            }

            public void call(SubjectSubscriptionManager.SubjectObserver<T> subjectObserver) {
                subjectObserver.emitFirst(SubjectSubscriptionManager.this.getLatest(), SubjectSubscriptionManager.this.f27647nl);
            }
        };
        return new PublishSubject<>(subjectSubscriptionManager, subjectSubscriptionManager);
    }

    protected PublishSubject(Observable.OnSubscribe<T> onSubscribe, SubjectSubscriptionManager<T> subjectSubscriptionManager) {
        super(onSubscribe);
        this.f27644nl = NotificationLite.instance();
        this.state = subjectSubscriptionManager;
    }

    @Override // p475rx.Observer
    public void onCompleted() {
        if (this.state.active) {
            Object completed = this.f27644nl.completed();
            for (SubjectSubscriptionManager.SubjectObserver<T> subjectObserver : this.state.terminate(completed)) {
                subjectObserver.emitNext(completed, this.state.f27647nl);
            }
        }
    }

    @Override // p475rx.Observer
    public void onError(Throwable th) {
        if (this.state.active) {
            Object error = this.f27644nl.error(th);
            ArrayList arrayList = null;
            for (SubjectSubscriptionManager.SubjectObserver<T> subjectObserver : this.state.terminate(error)) {
                try {
                    subjectObserver.emitNext(error, this.state.f27647nl);
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
        for (SubjectSubscriptionManager.SubjectObserver<T> subjectObserver : this.state.observers()) {
            subjectObserver.onNext(t);
        }
    }

    @Override // p475rx.subjects.Subject
    public boolean hasObservers() {
        return this.state.observers().length > 0;
    }

    @Beta
    public boolean hasThrowable() {
        return this.f27644nl.isError(this.state.getLatest());
    }

    @Beta
    public boolean hasCompleted() {
        Object latest = this.state.getLatest();
        return (latest == null || this.f27644nl.isError(latest)) ? false : true;
    }

    @Beta
    public Throwable getThrowable() {
        Object latest = this.state.getLatest();
        if (this.f27644nl.isError(latest)) {
            return this.f27644nl.getError(latest);
        }
        return null;
    }
}
