package com.p201hb.omapi.union.sim.org.bouncycastle.asn1;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hb.omapi.union.sim.org.bouncycastle.asn1.ASN1ParsingException */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ASN1ParsingException extends IllegalStateException {
    public Throwable cause;

    public ASN1ParsingException(String str) {
        super(str);
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public ASN1ParsingException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }
}
