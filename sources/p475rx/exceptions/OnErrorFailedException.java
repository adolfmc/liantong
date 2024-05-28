package p475rx.exceptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.exceptions.OnErrorFailedException */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class OnErrorFailedException extends RuntimeException {
    private static final long serialVersionUID = -419289748403337611L;

    public OnErrorFailedException(String str, Throwable th) {
        super(str, th == null ? new NullPointerException() : th);
    }

    public OnErrorFailedException(Throwable th) {
        super(th != null ? th.getMessage() : null, th == null ? new NullPointerException() : th);
    }
}
