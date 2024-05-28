package org.bouncycastle.util.encoders;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class EncoderException extends IllegalStateException {
    private Throwable cause;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EncoderException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
