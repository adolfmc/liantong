package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12894f;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.bb */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12773bb extends AbstractC12856e {

    /* renamed from: a */
    protected long[] f25988a;

    public C12773bb() {
        this.f25988a = AbstractC12894f.m610b();
    }

    public C12773bb(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 131) {
            throw new IllegalArgumentException("x value invalid for SecT131FieldElement");
        }
        this.f25988a = C12772ba.m1166a(bigInteger);
    }

    protected C12773bb(long[] jArr) {
        this.f25988a = jArr;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public BigInteger mo893a() {
        return AbstractC12894f.m596c(this.f25988a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo899a(int i) {
        if (i < 1) {
            return this;
        }
        long[] m610b = AbstractC12894f.m610b();
        C12772ba.m1163a(this.f25988a, i, m610b);
        return new C12773bb(m610b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo889a(AbstractC12856e abstractC12856e) {
        long[] m610b = AbstractC12894f.m610b();
        C12772ba.m1161a(this.f25988a, ((C12773bb) abstractC12856e).f25988a, m610b);
        return new C12773bb(m610b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo888a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
        long[] jArr = this.f25988a;
        long[] jArr2 = ((C12773bb) abstractC12856e).f25988a;
        long[] jArr3 = ((C12773bb) abstractC12856e2).f25988a;
        long[] m691b = AbstractC12891c.m691b(5);
        C12772ba.m1152f(jArr, m691b);
        C12772ba.m1155d(jArr2, jArr3, m691b);
        long[] m610b = AbstractC12894f.m610b();
        C12772ba.m1158c(m691b, m610b);
        return new C12773bb(m610b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo887a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e abstractC12856e3) {
        return mo882b(abstractC12856e, abstractC12856e2, abstractC12856e3);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public int mo886b() {
        return 131;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo883b(AbstractC12856e abstractC12856e) {
        return mo889a(abstractC12856e);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo882b(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e abstractC12856e3) {
        long[] jArr = this.f25988a;
        long[] jArr2 = ((C12773bb) abstractC12856e).f25988a;
        long[] jArr3 = ((C12773bb) abstractC12856e2).f25988a;
        long[] jArr4 = ((C12773bb) abstractC12856e3).f25988a;
        long[] m691b = AbstractC12891c.m691b(5);
        C12772ba.m1155d(jArr, jArr2, m691b);
        C12772ba.m1155d(jArr3, jArr4, m691b);
        long[] m610b = AbstractC12894f.m610b();
        C12772ba.m1158c(m691b, m610b);
        return new C12773bb(m610b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo881c() {
        long[] m610b = AbstractC12894f.m610b();
        C12772ba.m1162a(this.f25988a, m610b);
        return new C12773bb(m610b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo878c(AbstractC12856e abstractC12856e) {
        long[] m610b = AbstractC12894f.m610b();
        C12772ba.m1157c(this.f25988a, ((C12773bb) abstractC12856e).f25988a, m610b);
        return new C12773bb(m610b);
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
        C12772ba.m1154e(this.f25988a, m610b);
        return new C12773bb(m610b);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C12773bb) {
            return AbstractC12894f.m611a(this.f25988a, ((C12773bb) obj).f25988a);
        }
        return false;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: f */
    public AbstractC12856e mo871f() {
        long[] m610b = AbstractC12894f.m610b();
        C12772ba.m1160b(this.f25988a, m610b);
        return new C12773bb(m610b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: g */
    public AbstractC12856e mo870g() {
        long[] m610b = AbstractC12894f.m610b();
        C12772ba.m1156d(this.f25988a, m610b);
        return new C12773bb(m610b);
    }

    public int hashCode() {
        return C12957a.m431a(this.f25988a, 0, 3) ^ 131832;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: i */
    public boolean mo896i() {
        return AbstractC12894f.m612a(this.f25988a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: j */
    public boolean mo895j() {
        return AbstractC12894f.m603b(this.f25988a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: k */
    public boolean mo894k() {
        return (this.f25988a[0] & 1) != 0;
    }
}
