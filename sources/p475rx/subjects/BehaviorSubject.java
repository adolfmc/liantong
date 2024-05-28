package p475rx.subjects;

import java.lang.reflect.Array;
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
/* renamed from: rx.subjects.BehaviorSubject */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class BehaviorSubject<T> extends Subject<T, T> {
    private static final Object[] EMPTY_ARRAY = new Object[0];

    /* renamed from: nl */
    private final NotificationLite<T> f27643nl;
    private final SubjectSubscriptionManager<T> state;

    public static <T> BehaviorSubject<T> create() {
        return create(null, false);
    }

    public static <T> BehaviorSubject<T> create(T t) {
        return create(t, true);
    }

    private static <T> BehaviorSubject<T> create(T t, boolean z) {
        final SubjectSubscriptionManager subjectSubscriptionManager = new SubjectSubscriptionManager();
        if (z) {
            subjectSubscriptionManager.setLatest(NotificationLite.instance().next(t));
        }
        subjectSubscriptionManager.onAdded = new Action1<SubjectSubscriptionManager.SubjectObserver<T>>() { // from class: rx.subjects.BehaviorSubject.1
            @Override // p475rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((SubjectSubscriptionManager.SubjectObserver) ((SubjectSubscriptionManager.SubjectObserver) obj));
            }

            public void call(SubjectSubscriptionManager.SubjectObserver<T> subjectObserver) {
                subjectObserver.emitFirst(SubjectSubscriptionManager.this.getLatest(), SubjectSubscriptionManager.this.f27647nl);
            }
        };
        subjectSubscriptionManager.onTerminated = subjectSubscriptionManager.onAdded;
        return new BehaviorSubject<>(subjectSubscriptionManager, subjectSubscriptionManager);
    }

    protected BehaviorSubject(Observable.OnSubscribe<T> onSubscribe, SubjectSubscriptionManager<T> subjectSubscriptionManager) {
        super(onSubscribe);
        this.f27643nl = NotificationLite.instance();
        this.state = subjectSubscriptionManager;
    }

    @Override // p475rx.Observer
    public void onCompleted() {
        if (this.state.getLatest() == null || this.state.active) {
            Object completed = this.f27643nl.completed();
            for (SubjectSubscriptionManager.SubjectObserver<T> subjectObserver : this.state.terminate(completed)) {
                subjectObserver.emitNext(completed, this.state.f27647nl);
            }
        }
    }

    @Override // p475rx.Observer
    public void onError(Throwable th) {
        if (this.state.getLatest() == null || this.state.active) {
            Object error = this.f27643nl.error(th);
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
        if (this.state.getLatest() == null || this.state.active) {
            Object next = this.f27643nl.next(t);
            for (SubjectSubscriptionManager.SubjectObserver<T> subjectObserver : this.state.next(next)) {
                subjectObserver.emitNext(next, this.state.f27647nl);
            }
        }
    }

    int subscriberCount() {
        return this.state.observers().length;
    }

    @Override // p475rx.subjects.Subject
    public boolean hasObservers() {
        return this.state.observers().length > 0;
    }

    @Beta
    public boolean hasValue() {
        return this.f27643nl.isNext(this.state.getLatest());
    }

    @Beta
    public boolean hasThrowable() {
        return this.f27643nl.isError(this.state.getLatest());
    }

    @Beta
    public boolean hasCompleted() {
        return this.f27643nl.isCompleted(this.state.getLatest());
    }

    @Beta
    public T getValue() {
        Object latest = this.state.getLatest();
        if (this.f27643nl.isNext(latest)) {
            return this.f27643nl.getValue(latest);
        }
        return null;
    }

    @Beta
    public Throwable getThrowable() {
        Object latest = this.state.getLatest();
        if (this.f27643nl.isError(latest)) {
            return this.f27643nl.getError(latest);
        }
        return null;
    }

    @Beta
    public T[] getValues(T[] tArr) {
        Object latest = this.state.getLatest();
        if (this.f27643nl.isNext(latest)) {
            if (tArr.length == 0) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), 1));
            }
            tArr[0] = this.f27643nl.getValue(latest);
            if (tArr.length > 1) {
                tArr[1] = null;
            }
        } else if (tArr.length > 0) {
            tArr[0] = null;
        }
        return tArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Beta
    public Object[] getValues() {
        Object[] values = getValues(EMPTY_ARRAY);
        return values == EMPTY_ARRAY ? new Object[0] : values;
    }
}
