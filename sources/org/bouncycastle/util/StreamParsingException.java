package org.bouncycastle.util;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class StreamParsingException extends Exception {

    /* renamed from: _e */
    Throwable f27378_e;

    public StreamParsingException(String str, Throwable th) {
        super(str);
        this.f27378_e = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f27378_e;
    }
}
