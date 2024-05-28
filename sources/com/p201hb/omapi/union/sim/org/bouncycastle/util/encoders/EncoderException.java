package com.p201hb.omapi.union.sim.org.bouncycastle.util.encoders;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hb.omapi.union.sim.org.bouncycastle.util.encoders.EncoderException */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class EncoderException extends IllegalStateException {
    public Throwable cause;

    public EncoderException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
