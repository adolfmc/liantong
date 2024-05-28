package p475rx.internal.util;

import p475rx.Notification;
import p475rx.Observer;
import p475rx.functions.Action1;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.util.ActionNotificationObserver */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class ActionNotificationObserver<T> implements Observer<T> {
    final Action1<Notification<? super T>> onNotification;

    public ActionNotificationObserver(Action1<Notification<? super T>> action1) {
        this.onNotification = action1;
    }

    @Override // p475rx.Observer
    public void onNext(T t) {
        this.onNotification.call(Notification.createOnNext(t));
    }

    @Override // p475rx.Observer
    public void onError(Throwable th) {
        this.onNotification.call(Notification.createOnError(th));
    }

    @Override // p475rx.Observer
    public void onCompleted() {
        this.onNotification.call(Notification.createOnCompleted());
    }
}
