package com.p201hb.omapi.union.sim.org.bouncycastle.crypto;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hb.omapi.union.sim.org.bouncycastle.crypto.CryptoException */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class CryptoException extends Exception {
    public Throwable cause;

    public CryptoException() {
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public CryptoException(String str) {
        super(str);
    }

    public CryptoException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }
}
