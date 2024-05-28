package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p444c.AbstractC12890b;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.as */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12763as extends AbstractC12856e {

    /* renamed from: a */
    public static final BigInteger f25979a = C12761aq.f25976i;

    /* renamed from: b */
    protected int[] f25980b;

    public C12763as() {
        this.f25980b = AbstractC12891c.m712a(17);
    }

    public C12763as(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f25979a) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP521R1FieldElement");
        }
        this.f25980b = C12762ar.m1210a(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C12763as(int[] iArr) {
        this.f25980b = iArr;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public BigInteger mo893a() {
        return AbstractC12891c.m671f(17, this.f25980b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo889a(AbstractC12856e abstractC12856e) {
        int[] m712a = AbstractC12891c.m712a(17);
        C12762ar.m1206a(this.f25980b, ((C12763as) abstractC12856e).f25980b, m712a);
        return new C12763as(m712a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public int mo886b() {
        return f25979a.bitLength();
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo883b(AbstractC12856e abstractC12856e) {
        int[] m712a = AbstractC12891c.m712a(17);
        C12762ar.m1202c(this.f25980b, ((C12763as) abstractC12856e).f25980b, m712a);
        return new C12763as(m712a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo881c() {
        int[] m712a = AbstractC12891c.m712a(17);
        C12762ar.m1207a(this.f25980b, m712a);
        return new C12763as(m712a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo878c(AbstractC12856e abstractC12856e) {
        int[] m712a = AbstractC12891c.m712a(17);
        C12762ar.m1204b(this.f25980b, ((C12763as) abstractC12856e).f25980b, m712a);
        return new C12763as(m712a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo877d() {
        int[] m712a = AbstractC12891c.m712a(17);
        C12762ar.m1205b(this.f25980b, m712a);
        return new C12763as(m712a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo875d(AbstractC12856e abstractC12856e) {
        int[] m712a = AbstractC12891c.m712a(17);
        AbstractC12890b.m713a(C12762ar.f25978a, ((C12763as) abstractC12856e).f25980b, m712a);
        C12762ar.m1204b(m712a, this.f25980b, m712a);
        return new C12763as(m712a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: e */
    public AbstractC12856e mo874e() {
        int[] m712a = AbstractC12891c.m712a(17);
        C12762ar.m1201d(this.f25980b, m712a);
        return new C12763as(m712a);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C12763as) {
            return AbstractC12891c.m685b(17, this.f25980b, ((C12763as) obj).f25980b);
        }
        return false;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: f */
    public AbstractC12856e mo871f() {
        int[] m712a = AbstractC12891c.m712a(17);
        AbstractC12890b.m713a(C12762ar.f25978a, this.f25980b, m712a);
        return new C12763as(m712a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: g */
    public AbstractC12856e mo870g() {
        int[] iArr = this.f25980b;
        if (AbstractC12891c.m673e(17, iArr) || AbstractC12891c.m676d(17, iArr)) {
            return this;
        }
        int[] m712a = AbstractC12891c.m712a(17);
        int[] m712a2 = AbstractC12891c.m712a(17);
        C12762ar.m1208a(iArr, 519, m712a);
        C12762ar.m1201d(m712a, m712a2);
        if (AbstractC12891c.m685b(17, iArr, m712a2)) {
            return new C12763as(m712a);
        }
        return null;
    }

    public int hashCode() {
        return f25979a.hashCode() ^ C12957a.m434a(this.f25980b, 0, 17);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: i */
    public boolean mo896i() {
        return AbstractC12891c.m676d(17, this.f25980b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: j */
    public boolean mo895j() {
        return AbstractC12891c.m673e(17, this.f25980b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: k */
    public boolean mo894k() {
        return AbstractC12891c.m692a(this.f25980b, 0) == 1;
    }
}
