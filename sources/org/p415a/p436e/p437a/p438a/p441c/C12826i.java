package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p444c.AbstractC12890b;
import org.p415a.p436e.p444c.AbstractC12893e;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.i */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12826i extends AbstractC12856e {

    /* renamed from: a */
    public static final BigInteger f26026a = C12824g.f26021i;

    /* renamed from: b */
    protected int[] f26027b;

    public C12826i() {
        this.f26027b = AbstractC12893e.m645a();
    }

    public C12826i(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f26026a) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP160R1FieldElement");
        }
        this.f26027b = C12825h.m1020a(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C12826i(int[] iArr) {
        this.f26027b = iArr;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public BigInteger mo893a() {
        return AbstractC12893e.m631c(this.f26027b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo889a(AbstractC12856e abstractC12856e) {
        int[] m645a = AbstractC12893e.m645a();
        C12825h.m1017a(this.f26027b, ((C12826i) abstractC12856e).f26027b, m645a);
        return new C12826i(m645a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public int mo886b() {
        return f26026a.bitLength();
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo883b(AbstractC12856e abstractC12856e) {
        int[] m645a = AbstractC12893e.m645a();
        C12825h.m1011d(this.f26027b, ((C12826i) abstractC12856e).f26027b, m645a);
        return new C12826i(m645a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo881c() {
        int[] m645a = AbstractC12893e.m645a();
        C12825h.m1018a(this.f26027b, m645a);
        return new C12826i(m645a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo878c(AbstractC12856e abstractC12856e) {
        int[] m645a = AbstractC12893e.m645a();
        C12825h.m1015b(this.f26027b, ((C12826i) abstractC12856e).f26027b, m645a);
        return new C12826i(m645a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo877d() {
        int[] m645a = AbstractC12893e.m645a();
        C12825h.m1016b(this.f26027b, m645a);
        return new C12826i(m645a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo875d(AbstractC12856e abstractC12856e) {
        int[] m645a = AbstractC12893e.m645a();
        AbstractC12890b.m713a(C12825h.f26023a, ((C12826i) abstractC12856e).f26027b, m645a);
        C12825h.m1015b(m645a, this.f26027b, m645a);
        return new C12826i(m645a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: e */
    public AbstractC12856e mo874e() {
        int[] m645a = AbstractC12893e.m645a();
        C12825h.m1012d(this.f26027b, m645a);
        return new C12826i(m645a);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C12826i) {
            return AbstractC12893e.m638a(this.f26027b, ((C12826i) obj).f26027b);
        }
        return false;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: f */
    public AbstractC12856e mo871f() {
        int[] m645a = AbstractC12893e.m645a();
        AbstractC12890b.m713a(C12825h.f26023a, this.f26027b, m645a);
        return new C12826i(m645a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: g */
    public AbstractC12856e mo870g() {
        int[] iArr = this.f26027b;
        if (AbstractC12893e.m634b(iArr) || AbstractC12893e.m640a(iArr)) {
            return this;
        }
        int[] m645a = AbstractC12893e.m645a();
        C12825h.m1012d(iArr, m645a);
        C12825h.m1015b(m645a, iArr, m645a);
        int[] m645a2 = AbstractC12893e.m645a();
        C12825h.m1019a(m645a, 2, m645a2);
        C12825h.m1015b(m645a2, m645a, m645a2);
        C12825h.m1019a(m645a2, 4, m645a);
        C12825h.m1015b(m645a, m645a2, m645a);
        C12825h.m1019a(m645a, 8, m645a2);
        C12825h.m1015b(m645a2, m645a, m645a2);
        C12825h.m1019a(m645a2, 16, m645a);
        C12825h.m1015b(m645a, m645a2, m645a);
        C12825h.m1019a(m645a, 32, m645a2);
        C12825h.m1015b(m645a2, m645a, m645a2);
        C12825h.m1019a(m645a2, 64, m645a);
        C12825h.m1015b(m645a, m645a2, m645a);
        C12825h.m1012d(m645a, m645a2);
        C12825h.m1015b(m645a2, iArr, m645a2);
        C12825h.m1019a(m645a2, 29, m645a2);
        C12825h.m1012d(m645a2, m645a);
        if (AbstractC12893e.m638a(iArr, m645a)) {
            return new C12826i(m645a2);
        }
        return null;
    }

    public int hashCode() {
        return f26026a.hashCode() ^ C12957a.m434a(this.f26027b, 0, 5);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: i */
    public boolean mo896i() {
        return AbstractC12893e.m640a(this.f26027b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: j */
    public boolean mo895j() {
        return AbstractC12893e.m634b(this.f26027b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: k */
    public boolean mo894k() {
        return AbstractC12893e.m639a(this.f26027b, 0) == 1;
    }
}
