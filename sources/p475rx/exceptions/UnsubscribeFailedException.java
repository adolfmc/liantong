package p475rx.exceptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.exceptions.UnsubscribeFailedException */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class UnsubscribeFailedException extends RuntimeException {
    private static final long serialVersionUID = 4594672310593167598L;

    public UnsubscribeFailedException(Throwable th) {
        super(th == null ? new NullPointerException() : th);
    }

    public UnsubscribeFailedException(String str, Throwable th) {
        super(str, th == null ? new NullPointerException() : th);
    }
}
