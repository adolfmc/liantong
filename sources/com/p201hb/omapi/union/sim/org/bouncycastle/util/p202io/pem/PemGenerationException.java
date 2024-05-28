package com.p201hb.omapi.union.sim.org.bouncycastle.util.p202io.pem;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hb.omapi.union.sim.org.bouncycastle.util.io.pem.PemGenerationException */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class PemGenerationException extends IOException {
    public Throwable cause;

    public PemGenerationException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public PemGenerationException(String str) {
        super(str);
    }
}
