package p475rx.subjects;

import p475rx.Observable;
import p475rx.Observer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.subjects.Subject */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class Subject<T, R> extends Observable<R> implements Observer<T> {
    public abstract boolean hasObservers();

    /* JADX INFO: Access modifiers changed from: protected */
    public Subject(Observable.OnSubscribe<R> onSubscribe) {
        super(onSubscribe);
    }

    public final SerializedSubject<T, R> toSerialized() {
        if (getClass() == SerializedSubject.class) {
            return (SerializedSubject) this;
        }
        return new SerializedSubject<>(this);
    }
}
