package p475rx.internal.util;

import p475rx.Subscriber;
import p475rx.functions.Action0;
import p475rx.functions.Action1;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.util.ActionSubscriber */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class ActionSubscriber<T> extends Subscriber<T> {
    final Action0 onCompleted;
    final Action1<Throwable> onError;
    final Action1<? super T> onNext;

    public ActionSubscriber(Action1<? super T> action1, Action1<Throwable> action12, Action0 action0) {
        this.onNext = action1;
        this.onError = action12;
        this.onCompleted = action0;
    }

    @Override // p475rx.Observer
    public void onNext(T t) {
        this.onNext.call(t);
    }

    @Override // p475rx.Observer
    public void onError(Throwable th) {
        this.onError.call(th);
    }

    @Override // p475rx.Observer
    public void onCompleted() {
        this.onCompleted.call();
    }
}
