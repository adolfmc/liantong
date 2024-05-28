package p475rx.internal.operators;

import java.io.Serializable;
import p475rx.Notification;
import p475rx.Observer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.NotificationLite */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class NotificationLite<T> {
    private static final NotificationLite INSTANCE = new NotificationLite();
    private static final Object ON_COMPLETED_SENTINEL = new Serializable() { // from class: rx.internal.operators.NotificationLite.1
        private static final long serialVersionUID = 1;

        public String toString() {
            return "Notification=>Completed";
        }
    };
    private static final Object ON_NEXT_NULL_SENTINEL = new Serializable() { // from class: rx.internal.operators.NotificationLite.2
        private static final long serialVersionUID = 2;

        public String toString() {
            return "Notification=>NULL";
        }
    };

    private NotificationLite() {
    }

    public static <T> NotificationLite<T> instance() {
        return INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.NotificationLite$OnErrorSentinel */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class OnErrorSentinel implements Serializable {
        private static final long serialVersionUID = 3;

        /* renamed from: e */
        final Throwable f27582e;

        public OnErrorSentinel(Throwable th) {
            this.f27582e = th;
        }

        public String toString() {
            return "Notification=>Error:" + this.f27582e;
        }
    }

    public Object next(T t) {
        return t == null ? ON_NEXT_NULL_SENTINEL : t;
    }

    public Object completed() {
        return ON_COMPLETED_SENTINEL;
    }

    public Object error(Throwable th) {
        return new OnErrorSentinel(th);
    }

    public boolean accept(Observer<? super T> observer, Object obj) {
        if (obj == ON_COMPLETED_SENTINEL) {
            observer.onCompleted();
            return true;
        } else if (obj == ON_NEXT_NULL_SENTINEL) {
            observer.onNext(null);
            return false;
        } else if (obj != null) {
            if (obj.getClass() == OnErrorSentinel.class) {
                observer.onError(((OnErrorSentinel) obj).f27582e);
                return true;
            }
            observer.onNext(obj);
            return false;
        } else {
            throw new IllegalArgumentException("The lite notification can not be null");
        }
    }

    public boolean isCompleted(Object obj) {
        return obj == ON_COMPLETED_SENTINEL;
    }

    public boolean isError(Object obj) {
        return obj instanceof OnErrorSentinel;
    }

    public boolean isNull(Object obj) {
        return obj == ON_NEXT_NULL_SENTINEL;
    }

    public boolean isNext(Object obj) {
        return (obj == null || isError(obj) || isCompleted(obj)) ? false : true;
    }

    public Notification.Kind kind(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("The lite notification can not be null");
        }
        if (obj == ON_COMPLETED_SENTINEL) {
            return Notification.Kind.OnCompleted;
        }
        if (obj instanceof OnErrorSentinel) {
            return Notification.Kind.OnError;
        }
        return Notification.Kind.OnNext;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T getValue(Object obj) {
        if (obj == ON_NEXT_NULL_SENTINEL) {
            return null;
        }
        return obj;
    }

    public Throwable getError(Object obj) {
        return ((OnErrorSentinel) obj).f27582e;
    }
}
