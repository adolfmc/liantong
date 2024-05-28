package org.bouncycastle.i18n;

import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class LocalizedException extends Exception {
    private Throwable cause;
    protected ErrorBundle message;

    public LocalizedException(ErrorBundle errorBundle) {
        super(errorBundle.getText(Locale.getDefault()));
        this.message = errorBundle;
    }

    public LocalizedException(ErrorBundle errorBundle, Throwable th) {
        super(errorBundle.getText(Locale.getDefault()));
        this.message = errorBundle;
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public ErrorBundle getErrorMessage() {
        return this.message;
    }
}
