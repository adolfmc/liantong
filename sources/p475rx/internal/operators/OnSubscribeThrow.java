package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Subscriber;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OnSubscribeThrow */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OnSubscribeThrow<T> implements Observable.OnSubscribe<T> {
    private final Throwable exception;

    @Override // p475rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeThrow(Throwable th) {
        this.exception = th;
    }

    public void call(Subscriber<? super T> subscriber) {
        subscriber.onError(this.exception);
    }
}
