package org.p415a.p427d.p435h;

import java.math.BigInteger;
import org.p415a.p427d.InterfaceC12696f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.h.c */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12707c implements InterfaceC12696f {

    /* renamed from: a */
    private BigInteger f25893a;

    /* renamed from: b */
    private BigInteger f25894b;

    /* renamed from: c */
    private BigInteger f25895c;

    public C12707c(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f25893a = bigInteger3;
        this.f25895c = bigInteger;
        this.f25894b = bigInteger2;
    }

    /* renamed from: a */
    public BigInteger m1344a() {
        return this.f25895c;
    }

    /* renamed from: b */
    public BigInteger m1343b() {
        return this.f25894b;
    }

    /* renamed from: c */
    public BigInteger m1342c() {
        return this.f25893a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C12707c) {
            C12707c c12707c = (C12707c) obj;
            return c12707c.m1344a().equals(this.f25895c) && c12707c.m1343b().equals(this.f25894b) && c12707c.m1342c().equals(this.f25893a);
        }
        return false;
    }

    public int hashCode() {
        return (m1344a().hashCode() ^ m1343b().hashCode()) ^ m1342c().hashCode();
    }
}
