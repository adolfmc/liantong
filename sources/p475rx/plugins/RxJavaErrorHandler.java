package p475rx.plugins;

import p475rx.annotations.Beta;
import p475rx.exceptions.Exceptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.plugins.RxJavaErrorHandler */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class RxJavaErrorHandler {
    protected static final String ERROR_IN_RENDERING_SUFFIX = ".errorRendering";

    public void handleError(Throwable th) {
    }

    @Beta
    protected String render(Object obj) throws InterruptedException {
        return null;
    }

    @Beta
    public final String handleOnNextValueRendering(Object obj) {
        try {
            return render(obj);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            return obj.getClass().getName() + ".errorRendering";
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            return obj.getClass().getName() + ".errorRendering";
        }
    }
}
