package com.p201hb.omapi.union.sim.org.bouncycastle.util;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hb.omapi.union.sim.org.bouncycastle.util.StoreException */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class StoreException extends RuntimeException {

    /* renamed from: _e */
    public Throwable f10538_e;

    public StoreException(String str, Throwable th) {
        super(str);
        this.f10538_e = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f10538_e;
    }
}
