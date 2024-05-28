package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p444c.AbstractC12890b;
import org.p415a.p436e.p444c.AbstractC12896h;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.ag */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12751ag extends AbstractC12856e {

    /* renamed from: a */
    public static final BigInteger f25961a = C12749ae.f25956i;

    /* renamed from: b */
    protected int[] f25962b;

    public C12751ag() {
        this.f25962b = AbstractC12896h.m569a();
    }

    public C12751ag(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f25961a) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP256K1FieldElement");
        }
        this.f25962b = C12750af.m1249a(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C12751ag(int[] iArr) {
        this.f25962b = iArr;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public BigInteger mo893a() {
        return AbstractC12896h.m545c(this.f25962b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo889a(AbstractC12856e abstractC12856e) {
        int[] m569a = AbstractC12896h.m569a();
        C12750af.m1246a(this.f25962b, ((C12751ag) abstractC12856e).f25962b, m569a);
        return new C12751ag(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public int mo886b() {
        return f25961a.bitLength();
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo883b(AbstractC12856e abstractC12856e) {
        int[] m569a = AbstractC12896h.m569a();
        C12750af.m1240d(this.f25962b, ((C12751ag) abstractC12856e).f25962b, m569a);
        return new C12751ag(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo881c() {
        int[] m569a = AbstractC12896h.m569a();
        C12750af.m1247a(this.f25962b, m569a);
        return new C12751ag(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo878c(AbstractC12856e abstractC12856e) {
        int[] m569a = AbstractC12896h.m569a();
        C12750af.m1244b(this.f25962b, ((C12751ag) abstractC12856e).f25962b, m569a);
        return new C12751ag(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo877d() {
        int[] m569a = AbstractC12896h.m569a();
        C12750af.m1245b(this.f25962b, m569a);
        return new C12751ag(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo875d(AbstractC12856e abstractC12856e) {
        int[] m569a = AbstractC12896h.m569a();
        AbstractC12890b.m713a(C12750af.f25958a, ((C12751ag) abstractC12856e).f25962b, m569a);
        C12750af.m1244b(m569a, this.f25962b, m569a);
        return new C12751ag(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: e */
    public AbstractC12856e mo874e() {
        int[] m569a = AbstractC12896h.m569a();
        C12750af.m1241d(this.f25962b, m569a);
        return new C12751ag(m569a);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C12751ag) {
            return AbstractC12896h.m549b(this.f25962b, ((C12751ag) obj).f25962b);
        }
        return false;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: f */
    public AbstractC12856e mo871f() {
        int[] m569a = AbstractC12896h.m569a();
        AbstractC12890b.m713a(C12750af.f25958a, this.f25962b, m569a);
        return new C12751ag(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: g */
    public AbstractC12856e mo870g() {
        int[] iArr = this.f25962b;
        if (AbstractC12896h.m552b(iArr) || AbstractC12896h.m563a(iArr)) {
            return this;
        }
        int[] m569a = AbstractC12896h.m569a();
        C12750af.m1241d(iArr, m569a);
        C12750af.m1244b(m569a, iArr, m569a);
        int[] m569a2 = AbstractC12896h.m569a();
        C12750af.m1241d(m569a, m569a2);
        C12750af.m1244b(m569a2, iArr, m569a2);
        int[] m569a3 = AbstractC12896h.m569a();
        C12750af.m1248a(m569a2, 3, m569a3);
        C12750af.m1244b(m569a3, m569a2, m569a3);
        C12750af.m1248a(m569a3, 3, m569a3);
        C12750af.m1244b(m569a3, m569a2, m569a3);
        C12750af.m1248a(m569a3, 2, m569a3);
        C12750af.m1244b(m569a3, m569a, m569a3);
        int[] m569a4 = AbstractC12896h.m569a();
        C12750af.m1248a(m569a3, 11, m569a4);
        C12750af.m1244b(m569a4, m569a3, m569a4);
        C12750af.m1248a(m569a4, 22, m569a3);
        C12750af.m1244b(m569a3, m569a4, m569a3);
        int[] m569a5 = AbstractC12896h.m569a();
        C12750af.m1248a(m569a3, 44, m569a5);
        C12750af.m1244b(m569a5, m569a3, m569a5);
        int[] m569a6 = AbstractC12896h.m569a();
        C12750af.m1248a(m569a5, 88, m569a6);
        C12750af.m1244b(m569a6, m569a5, m569a6);
        C12750af.m1248a(m569a6, 44, m569a5);
        C12750af.m1244b(m569a5, m569a3, m569a5);
        C12750af.m1248a(m569a5, 3, m569a3);
        C12750af.m1244b(m569a3, m569a2, m569a3);
        C12750af.m1248a(m569a3, 23, m569a3);
        C12750af.m1244b(m569a3, m569a4, m569a3);
        C12750af.m1248a(m569a3, 6, m569a3);
        C12750af.m1244b(m569a3, m569a, m569a3);
        C12750af.m1248a(m569a3, 2, m569a3);
        C12750af.m1241d(m569a3, m569a);
        if (AbstractC12896h.m549b(iArr, m569a)) {
            return new C12751ag(m569a3);
        }
        return null;
    }

    public int hashCode() {
        return f25961a.hashCode() ^ C12957a.m434a(this.f25962b, 0, 8);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: i */
    public boolean mo896i() {
        return AbstractC12896h.m563a(this.f25962b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: j */
    public boolean mo895j() {
        return AbstractC12896h.m552b(this.f25962b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: k */
    public boolean mo894k() {
        return AbstractC12896h.m562a(this.f25962b, 0) == 1;
    }
}
