package io.reactivex.exceptions;

import io.reactivex.annotations.Beta;
import io.reactivex.annotations.NonNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Beta
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class OnErrorNotImplementedException extends RuntimeException {
    private static final long serialVersionUID = -6298857009889503852L;

    public OnErrorNotImplementedException(String str, @NonNull Throwable th) {
        super(str, th == null ? new NullPointerException() : th);
    }

    public OnErrorNotImplementedException(@NonNull Throwable th) {
        super(th != null ? th.getMessage() : null, th == null ? new NullPointerException() : th);
    }
}
