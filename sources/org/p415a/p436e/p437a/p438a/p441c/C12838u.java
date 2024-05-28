package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p444c.AbstractC12890b;
import org.p415a.p436e.p444c.AbstractC12894f;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.u */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12838u extends AbstractC12856e {

    /* renamed from: a */
    public static final BigInteger f26047a = C12836s.f26042i;

    /* renamed from: b */
    protected int[] f26048b;

    public C12838u() {
        this.f26048b = AbstractC12894f.m624a();
    }

    public C12838u(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f26047a) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP192R1FieldElement");
        }
        this.f26048b = C12837t.m984a(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C12838u(int[] iArr) {
        this.f26048b = iArr;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public BigInteger mo893a() {
        return AbstractC12894f.m601c(this.f26048b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo889a(AbstractC12856e abstractC12856e) {
        int[] m624a = AbstractC12894f.m624a();
        C12837t.m980a(this.f26048b, ((C12838u) abstractC12856e).f26048b, m624a);
        return new C12838u(m624a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public int mo886b() {
        return f26047a.bitLength();
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo883b(AbstractC12856e abstractC12856e) {
        int[] m624a = AbstractC12894f.m624a();
        C12837t.m973d(this.f26048b, ((C12838u) abstractC12856e).f26048b, m624a);
        return new C12838u(m624a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo881c() {
        int[] m624a = AbstractC12894f.m624a();
        C12837t.m981a(this.f26048b, m624a);
        return new C12838u(m624a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo878c(AbstractC12856e abstractC12856e) {
        int[] m624a = AbstractC12894f.m624a();
        C12837t.m977b(this.f26048b, ((C12838u) abstractC12856e).f26048b, m624a);
        return new C12838u(m624a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo877d() {
        int[] m624a = AbstractC12894f.m624a();
        C12837t.m978b(this.f26048b, m624a);
        return new C12838u(m624a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo875d(AbstractC12856e abstractC12856e) {
        int[] m624a = AbstractC12894f.m624a();
        AbstractC12890b.m713a(C12837t.f26044a, ((C12838u) abstractC12856e).f26048b, m624a);
        C12837t.m977b(m624a, this.f26048b, m624a);
        return new C12838u(m624a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: e */
    public AbstractC12856e mo874e() {
        int[] m624a = AbstractC12894f.m624a();
        C12837t.m974d(this.f26048b, m624a);
        return new C12838u(m624a);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C12838u) {
            return AbstractC12894f.m614a(this.f26048b, ((C12838u) obj).f26048b);
        }
        return false;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: f */
    public AbstractC12856e mo871f() {
        int[] m624a = AbstractC12894f.m624a();
        AbstractC12890b.m713a(C12837t.f26044a, this.f26048b, m624a);
        return new C12838u(m624a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: g */
    public AbstractC12856e mo870g() {
        int[] iArr = this.f26048b;
        if (AbstractC12894f.m608b(iArr) || AbstractC12894f.m619a(iArr)) {
            return this;
        }
        int[] m624a = AbstractC12894f.m624a();
        int[] m624a2 = AbstractC12894f.m624a();
        C12837t.m974d(iArr, m624a);
        C12837t.m977b(m624a, iArr, m624a);
        C12837t.m982a(m624a, 2, m624a2);
        C12837t.m977b(m624a2, m624a, m624a2);
        C12837t.m982a(m624a2, 4, m624a);
        C12837t.m977b(m624a, m624a2, m624a);
        C12837t.m982a(m624a, 8, m624a2);
        C12837t.m977b(m624a2, m624a, m624a2);
        C12837t.m982a(m624a2, 16, m624a);
        C12837t.m977b(m624a, m624a2, m624a);
        C12837t.m982a(m624a, 32, m624a2);
        C12837t.m977b(m624a2, m624a, m624a2);
        C12837t.m982a(m624a2, 64, m624a);
        C12837t.m977b(m624a, m624a2, m624a);
        C12837t.m982a(m624a, 62, m624a);
        C12837t.m974d(m624a, m624a2);
        if (AbstractC12894f.m614a(iArr, m624a2)) {
            return new C12838u(m624a);
        }
        return null;
    }

    public int hashCode() {
        return f26047a.hashCode() ^ C12957a.m434a(this.f26048b, 0, 6);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: i */
    public boolean mo896i() {
        return AbstractC12894f.m619a(this.f26048b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: j */
    public boolean mo895j() {
        return AbstractC12894f.m608b(this.f26048b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: k */
    public boolean mo894k() {
        return AbstractC12894f.m618a(this.f26048b, 0) == 1;
    }
}
