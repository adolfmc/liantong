package org.p415a.p427d.p435h;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.h.n */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12718n extends C12715k {

    /* renamed from: b */
    private BigInteger f25914b;

    public C12718n(BigInteger bigInteger, C12716l c12716l) {
        super(false, c12716l);
        this.f25914b = bigInteger;
    }

    /* renamed from: c */
    public BigInteger m1329c() {
        return this.f25914b;
    }

    @Override // org.p415a.p427d.p435h.C12715k
    public boolean equals(Object obj) {
        return (obj instanceof C12718n) && ((C12718n) obj).m1329c().equals(this.f25914b) && super.equals(obj);
    }

    @Override // org.p415a.p427d.p435h.C12715k
    public int hashCode() {
        return this.f25914b.hashCode() ^ super.hashCode();
    }
}
