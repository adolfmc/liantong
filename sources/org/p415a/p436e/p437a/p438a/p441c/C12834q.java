package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p444c.AbstractC12890b;
import org.p415a.p436e.p444c.AbstractC12894f;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.q */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12834q extends AbstractC12856e {

    /* renamed from: a */
    public static final BigInteger f26040a = C12832o.f26035i;

    /* renamed from: b */
    protected int[] f26041b;

    public C12834q() {
        this.f26041b = AbstractC12894f.m624a();
    }

    public C12834q(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f26040a) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP192K1FieldElement");
        }
        this.f26041b = C12833p.m996a(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C12834q(int[] iArr) {
        this.f26041b = iArr;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public BigInteger mo893a() {
        return AbstractC12894f.m601c(this.f26041b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo889a(AbstractC12856e abstractC12856e) {
        int[] m624a = AbstractC12894f.m624a();
        C12833p.m993a(this.f26041b, ((C12834q) abstractC12856e).f26041b, m624a);
        return new C12834q(m624a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public int mo886b() {
        return f26040a.bitLength();
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo883b(AbstractC12856e abstractC12856e) {
        int[] m624a = AbstractC12894f.m624a();
        C12833p.m987d(this.f26041b, ((C12834q) abstractC12856e).f26041b, m624a);
        return new C12834q(m624a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo881c() {
        int[] m624a = AbstractC12894f.m624a();
        C12833p.m994a(this.f26041b, m624a);
        return new C12834q(m624a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo878c(AbstractC12856e abstractC12856e) {
        int[] m624a = AbstractC12894f.m624a();
        C12833p.m991b(this.f26041b, ((C12834q) abstractC12856e).f26041b, m624a);
        return new C12834q(m624a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo877d() {
        int[] m624a = AbstractC12894f.m624a();
        C12833p.m992b(this.f26041b, m624a);
        return new C12834q(m624a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo875d(AbstractC12856e abstractC12856e) {
        int[] m624a = AbstractC12894f.m624a();
        AbstractC12890b.m713a(C12833p.f26037a, ((C12834q) abstractC12856e).f26041b, m624a);
        C12833p.m991b(m624a, this.f26041b, m624a);
        return new C12834q(m624a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: e */
    public AbstractC12856e mo874e() {
        int[] m624a = AbstractC12894f.m624a();
        C12833p.m988d(this.f26041b, m624a);
        return new C12834q(m624a);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C12834q) {
            return AbstractC12894f.m614a(this.f26041b, ((C12834q) obj).f26041b);
        }
        return false;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: f */
    public AbstractC12856e mo871f() {
        int[] m624a = AbstractC12894f.m624a();
        AbstractC12890b.m713a(C12833p.f26037a, this.f26041b, m624a);
        return new C12834q(m624a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: g */
    public AbstractC12856e mo870g() {
        int[] iArr = this.f26041b;
        if (AbstractC12894f.m608b(iArr) || AbstractC12894f.m619a(iArr)) {
            return this;
        }
        int[] m624a = AbstractC12894f.m624a();
        C12833p.m988d(iArr, m624a);
        C12833p.m991b(m624a, iArr, m624a);
        int[] m624a2 = AbstractC12894f.m624a();
        C12833p.m988d(m624a, m624a2);
        C12833p.m991b(m624a2, iArr, m624a2);
        int[] m624a3 = AbstractC12894f.m624a();
        C12833p.m995a(m624a2, 3, m624a3);
        C12833p.m991b(m624a3, m624a2, m624a3);
        C12833p.m995a(m624a3, 2, m624a3);
        C12833p.m991b(m624a3, m624a, m624a3);
        C12833p.m995a(m624a3, 8, m624a);
        C12833p.m991b(m624a, m624a3, m624a);
        C12833p.m995a(m624a, 3, m624a3);
        C12833p.m991b(m624a3, m624a2, m624a3);
        int[] m624a4 = AbstractC12894f.m624a();
        C12833p.m995a(m624a3, 16, m624a4);
        C12833p.m991b(m624a4, m624a, m624a4);
        C12833p.m995a(m624a4, 35, m624a);
        C12833p.m991b(m624a, m624a4, m624a);
        C12833p.m995a(m624a, 70, m624a4);
        C12833p.m991b(m624a4, m624a, m624a4);
        C12833p.m995a(m624a4, 19, m624a);
        C12833p.m991b(m624a, m624a3, m624a);
        C12833p.m995a(m624a, 20, m624a);
        C12833p.m991b(m624a, m624a3, m624a);
        C12833p.m995a(m624a, 4, m624a);
        C12833p.m991b(m624a, m624a2, m624a);
        C12833p.m995a(m624a, 6, m624a);
        C12833p.m991b(m624a, m624a2, m624a);
        C12833p.m988d(m624a, m624a);
        C12833p.m988d(m624a, m624a2);
        if (AbstractC12894f.m614a(iArr, m624a2)) {
            return new C12834q(m624a);
        }
        return null;
    }

    public int hashCode() {
        return f26040a.hashCode() ^ C12957a.m434a(this.f26041b, 0, 6);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: i */
    public boolean mo896i() {
        return AbstractC12894f.m619a(this.f26041b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: j */
    public boolean mo895j() {
        return AbstractC12894f.m608b(this.f26041b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: k */
    public boolean mo894k() {
        return AbstractC12894f.m618a(this.f26041b, 0) == 1;
    }
}
