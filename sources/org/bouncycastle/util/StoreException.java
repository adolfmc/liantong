package org.bouncycastle.util;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class StoreException extends RuntimeException {

    /* renamed from: _e */
    private Throwable f27377_e;

    public StoreException(String str, Throwable th) {
        super(str);
        this.f27377_e = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f27377_e;
    }
}
