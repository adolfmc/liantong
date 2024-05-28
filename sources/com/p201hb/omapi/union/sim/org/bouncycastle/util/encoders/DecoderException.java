package com.p201hb.omapi.union.sim.org.bouncycastle.util.encoders;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hb.omapi.union.sim.org.bouncycastle.util.encoders.DecoderException */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DecoderException extends IllegalStateException {
    public Throwable cause;

    public DecoderException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
