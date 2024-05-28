package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p444c.AbstractC12896h;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.bp */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12787bp extends AbstractC12856e {

    /* renamed from: a */
    protected long[] f25996a;

    public C12787bp() {
        this.f25996a = AbstractC12896h.m554b();
    }

    public C12787bp(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 193) {
            throw new IllegalArgumentException("x value invalid for SecT193FieldElement");
        }
        this.f25996a = C12786bo.m1132a(bigInteger);
    }

    protected C12787bp(long[] jArr) {
        this.f25996a = jArr;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public BigInteger mo893a() {
        return AbstractC12896h.m540c(this.f25996a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo899a(int i) {
        if (i < 1) {
            return this;
        }
        long[] m554b = AbstractC12896h.m554b();
        C12786bo.m1129a(this.f25996a, i, m554b);
        return new C12787bp(m554b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo889a(AbstractC12856e abstractC12856e) {
        long[] m554b = AbstractC12896h.m554b();
        C12786bo.m1127a(this.f25996a, ((C12787bp) abstractC12856e).f25996a, m554b);
        return new C12787bp(m554b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo888a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
        long[] jArr = this.f25996a;
        long[] jArr2 = ((C12787bp) abstractC12856e).f25996a;
        long[] jArr3 = ((C12787bp) abstractC12856e2).f25996a;
        long[] m539d = AbstractC12896h.m539d();
        C12786bo.m1118f(jArr, m539d);
        C12786bo.m1121d(jArr2, jArr3, m539d);
        long[] m554b = AbstractC12896h.m554b();
        C12786bo.m1124c(m539d, m554b);
        return new C12787bp(m554b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo887a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e abstractC12856e3) {
        return mo882b(abstractC12856e, abstractC12856e2, abstractC12856e3);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public int mo886b() {
        return 193;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo883b(AbstractC12856e abstractC12856e) {
        return mo889a(abstractC12856e);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo882b(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e abstractC12856e3) {
        long[] jArr = this.f25996a;
        long[] jArr2 = ((C12787bp) abstractC12856e).f25996a;
        long[] jArr3 = ((C12787bp) abstractC12856e2).f25996a;
        long[] jArr4 = ((C12787bp) abstractC12856e3).f25996a;
        long[] m539d = AbstractC12896h.m539d();
        C12786bo.m1121d(jArr, jArr2, m539d);
        C12786bo.m1121d(jArr3, jArr4, m539d);
        long[] m554b = AbstractC12896h.m554b();
        C12786bo.m1124c(m539d, m554b);
        return new C12787bp(m554b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo881c() {
        long[] m554b = AbstractC12896h.m554b();
        C12786bo.m1128a(this.f25996a, m554b);
        return new C12787bp(m554b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo878c(AbstractC12856e abstractC12856e) {
        long[] m554b = AbstractC12896h.m554b();
        C12786bo.m1123c(this.f25996a, ((C12787bp) abstractC12856e).f25996a, m554b);
        return new C12787bp(m554b);
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
        long[] m554b = AbstractC12896h.m554b();
        C12786bo.m1120e(this.f25996a, m554b);
        return new C12787bp(m554b);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C12787bp) {
            return AbstractC12896h.m555a(this.f25996a, ((C12787bp) obj).f25996a);
        }
        return false;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: f */
    public AbstractC12856e mo871f() {
        long[] m554b = AbstractC12896h.m554b();
        C12786bo.m1126b(this.f25996a, m554b);
        return new C12787bp(m554b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: g */
    public AbstractC12856e mo870g() {
        long[] m554b = AbstractC12896h.m554b();
        C12786bo.m1122d(this.f25996a, m554b);
        return new C12787bp(m554b);
    }

    public int hashCode() {
        return C12957a.m431a(this.f25996a, 0, 4) ^ 1930015;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: i */
    public boolean mo896i() {
        return AbstractC12896h.m556a(this.f25996a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: j */
    public boolean mo895j() {
        return AbstractC12896h.m547b(this.f25996a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: k */
    public boolean mo894k() {
        return (this.f25996a[0] & 1) != 0;
    }
}
