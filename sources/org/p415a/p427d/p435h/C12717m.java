package org.p415a.p427d.p435h;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.h.m */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12717m extends C12715k {

    /* renamed from: b */
    private BigInteger f25913b;

    /* renamed from: c */
    public BigInteger m1330c() {
        return this.f25913b;
    }

    @Override // org.p415a.p427d.p435h.C12715k
    public boolean equals(Object obj) {
        if ((obj instanceof C12717m) && ((C12717m) obj).m1330c().equals(this.f25913b)) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // org.p415a.p427d.p435h.C12715k
    public int hashCode() {
        return m1330c().hashCode();
    }
}
