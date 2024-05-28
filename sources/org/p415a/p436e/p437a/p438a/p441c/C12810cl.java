package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12899k;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.cl */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12810cl extends AbstractC12856e {

    /* renamed from: a */
    protected long[] f26010a;

    public C12810cl() {
        this.f26010a = AbstractC12899k.m524a();
    }

    public C12810cl(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 409) {
            throw new IllegalArgumentException("x value invalid for SecT409FieldElement");
        }
        this.f26010a = C12809ck.m1061a(bigInteger);
    }

    protected C12810cl(long[] jArr) {
        this.f26010a = jArr;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public BigInteger mo893a() {
        return AbstractC12899k.m518c(this.f26010a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo899a(int i) {
        if (i < 1) {
            return this;
        }
        long[] m524a = AbstractC12899k.m524a();
        C12809ck.m1058a(this.f26010a, i, m524a);
        return new C12810cl(m524a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo889a(AbstractC12856e abstractC12856e) {
        long[] m524a = AbstractC12899k.m524a();
        C12809ck.m1055a(this.f26010a, ((C12810cl) abstractC12856e).f26010a, m524a);
        return new C12810cl(m524a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo888a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
        long[] jArr = this.f26010a;
        long[] jArr2 = ((C12810cl) abstractC12856e).f26010a;
        long[] jArr3 = ((C12810cl) abstractC12856e2).f26010a;
        long[] m691b = AbstractC12891c.m691b(13);
        C12809ck.m1046f(jArr, m691b);
        C12809ck.m1049d(jArr2, jArr3, m691b);
        long[] m524a = AbstractC12899k.m524a();
        C12809ck.m1052c(m691b, m524a);
        return new C12810cl(m524a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo887a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e abstractC12856e3) {
        return mo882b(abstractC12856e, abstractC12856e2, abstractC12856e3);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public int mo886b() {
        return 409;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo883b(AbstractC12856e abstractC12856e) {
        return mo889a(abstractC12856e);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo882b(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e abstractC12856e3) {
        long[] jArr = this.f26010a;
        long[] jArr2 = ((C12810cl) abstractC12856e).f26010a;
        long[] jArr3 = ((C12810cl) abstractC12856e2).f26010a;
        long[] jArr4 = ((C12810cl) abstractC12856e3).f26010a;
        long[] m691b = AbstractC12891c.m691b(13);
        C12809ck.m1049d(jArr, jArr2, m691b);
        C12809ck.m1049d(jArr3, jArr4, m691b);
        long[] m524a = AbstractC12899k.m524a();
        C12809ck.m1052c(m691b, m524a);
        return new C12810cl(m524a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo881c() {
        long[] m524a = AbstractC12899k.m524a();
        C12809ck.m1056a(this.f26010a, m524a);
        return new C12810cl(m524a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo878c(AbstractC12856e abstractC12856e) {
        long[] m524a = AbstractC12899k.m524a();
        C12809ck.m1051c(this.f26010a, ((C12810cl) abstractC12856e).f26010a, m524a);
        return new C12810cl(m524a);
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
        long[] m524a = AbstractC12899k.m524a();
        C12809ck.m1048e(this.f26010a, m524a);
        return new C12810cl(m524a);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C12810cl) {
            return AbstractC12899k.m521a(this.f26010a, ((C12810cl) obj).f26010a);
        }
        return false;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: f */
    public AbstractC12856e mo871f() {
        long[] m524a = AbstractC12899k.m524a();
        C12809ck.m1054b(this.f26010a, m524a);
        return new C12810cl(m524a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: g */
    public AbstractC12856e mo870g() {
        long[] m524a = AbstractC12899k.m524a();
        C12809ck.m1050d(this.f26010a, m524a);
        return new C12810cl(m524a);
    }

    public int hashCode() {
        return C12957a.m431a(this.f26010a, 0, 7) ^ 4090087;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: i */
    public boolean mo896i() {
        return AbstractC12899k.m522a(this.f26010a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: j */
    public boolean mo895j() {
        return AbstractC12899k.m519b(this.f26010a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: k */
    public boolean mo894k() {
        return (this.f26010a[0] & 1) != 0;
    }
}
