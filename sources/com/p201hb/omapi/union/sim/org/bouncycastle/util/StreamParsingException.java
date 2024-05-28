package com.p201hb.omapi.union.sim.org.bouncycastle.util;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hb.omapi.union.sim.org.bouncycastle.util.StreamParsingException */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class StreamParsingException extends Exception {

    /* renamed from: _e */
    public Throwable f10539_e;

    public StreamParsingException(String str, Throwable th) {
        super(str);
        this.f10539_e = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f10539_e;
    }
}
