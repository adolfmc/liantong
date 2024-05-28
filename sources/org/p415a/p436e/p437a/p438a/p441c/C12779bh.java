package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p444c.AbstractC12894f;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.bh */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12779bh extends AbstractC12856e {

    /* renamed from: a */
    protected long[] f25992a;

    public C12779bh() {
        this.f25992a = AbstractC12894f.m610b();
    }

    public C12779bh(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 163) {
            throw new IllegalArgumentException("x value invalid for SecT163FieldElement");
        }
        this.f25992a = C12778bg.m1149a(bigInteger);
    }

    protected C12779bh(long[] jArr) {
        this.f25992a = jArr;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public BigInteger mo893a() {
        return AbstractC12894f.m596c(this.f25992a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo899a(int i) {
        if (i < 1) {
            return this;
        }
        long[] m610b = AbstractC12894f.m610b();
        C12778bg.m1146a(this.f25992a, i, m610b);
        return new C12779bh(m610b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo889a(AbstractC12856e abstractC12856e) {
        long[] m610b = AbstractC12894f.m610b();
        C12778bg.m1144a(this.f25992a, ((C12779bh) abstractC12856e).f25992a, m610b);
        return new C12779bh(m610b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo888a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
        long[] jArr = this.f25992a;
        long[] jArr2 = ((C12779bh) abstractC12856e).f25992a;
        long[] jArr3 = ((C12779bh) abstractC12856e2).f25992a;
        long[] m595d = AbstractC12894f.m595d();
        C12778bg.m1135f(jArr, m595d);
        C12778bg.m1138d(jArr2, jArr3, m595d);
        long[] m610b = AbstractC12894f.m610b();
        C12778bg.m1141c(m595d, m610b);
        return new C12779bh(m610b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo887a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e abstractC12856e3) {
        return mo882b(abstractC12856e, abstractC12856e2, abstractC12856e3);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public int mo886b() {
        return 163;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo883b(AbstractC12856e abstractC12856e) {
        return mo889a(abstractC12856e);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo882b(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e abstractC12856e3) {
        long[] jArr = this.f25992a;
        long[] jArr2 = ((C12779bh) abstractC12856e).f25992a;
        long[] jArr3 = ((C12779bh) abstractC12856e2).f25992a;
        long[] jArr4 = ((C12779bh) abstractC12856e3).f25992a;
        long[] m595d = AbstractC12894f.m595d();
        C12778bg.m1138d(jArr, jArr2, m595d);
        C12778bg.m1138d(jArr3, jArr4, m595d);
        long[] m610b = AbstractC12894f.m610b();
        C12778bg.m1141c(m595d, m610b);
        return new C12779bh(m610b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo881c() {
        long[] m610b = AbstractC12894f.m610b();
        C12778bg.m1145a(this.f25992a, m610b);
        return new C12779bh(m610b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo878c(AbstractC12856e abstractC12856e) {
        long[] m610b = AbstractC12894f.m610b();
        C12778bg.m1140c(this.f25992a, ((C12779bh) abstractC12856e).f25992a, m610b);
        return new C12779bh(m610b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo877d() {
        return this;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo875d(AbstractC12856e abstractC12856e) {
        return mo878c(abstractC12856e.mo871f());
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: e */
    public AbstractC12856e mo874e() {
        long[] m610b = AbstractC12894f.m610b();
        C12778bg.m1137e(this.f25992a, m610b);
        return new C12779bh(m610b);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C12779bh) {
            return AbstractC12894f.m611a(this.f25992a, ((C12779bh) obj).f25992a);
        }
        return false;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: f */
    public AbstractC12856e mo871f() {
        long[] m610b = AbstractC12894f.m610b();
        C12778bg.m1143b(this.f25992a, m610b);
        return new C12779bh(m610b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: g */
    public AbstractC12856e mo870g() {
        long[] m610b = AbstractC12894f.m610b();
        C12778bg.m1139d(this.f25992a, m610b);
        return new C12779bh(m610b);
    }

    public int hashCode() {
        return C12957a.m431a(this.f25992a, 0, 3) ^ 163763;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: i */
    public boolean mo896i() {
        return AbstractC12894f.m612a(this.f25992a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: j */
    public boolean mo895j() {
        return AbstractC12894f.m603b(this.f25992a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: k */
    public boolean mo894k() {
        return (this.f25992a[0] & 1) != 0;
    }
}
