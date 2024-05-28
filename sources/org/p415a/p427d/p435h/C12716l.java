package org.p415a.p427d.p435h;

import java.math.BigInteger;
import org.p415a.p427d.InterfaceC12696f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.h.l */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12716l implements InterfaceC12696f {

    /* renamed from: a */
    private BigInteger f25910a;

    /* renamed from: b */
    private BigInteger f25911b;

    /* renamed from: c */
    private int f25912c;

    public C12716l(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, 0);
    }

    public C12716l(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        this.f25910a = bigInteger2;
        this.f25911b = bigInteger;
        this.f25912c = i;
    }

    /* renamed from: a */
    public BigInteger m1333a() {
        return this.f25911b;
    }

    /* renamed from: b */
    public BigInteger m1332b() {
        return this.f25910a;
    }

    /* renamed from: c */
    public int m1331c() {
        return this.f25912c;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C12716l) {
            C12716l c12716l = (C12716l) obj;
            return c12716l.m1333a().equals(this.f25911b) && c12716l.m1332b().equals(this.f25910a) && c12716l.m1331c() == this.f25912c;
        }
        return false;
    }

    public int hashCode() {
        return (m1333a().hashCode() ^ m1332b().hashCode()) + this.f25912c;
    }
}
