package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p444c.AbstractC12901m;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.cr */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12816cr extends AbstractC12856e {

    /* renamed from: a */
    protected long[] f26014a;

    public C12816cr() {
        this.f26014a = AbstractC12901m.m515a();
    }

    public C12816cr(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 571) {
            throw new IllegalArgumentException("x value invalid for SecT571FieldElement");
        }
        this.f26014a = C12815cq.m1043a(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C12816cr(long[] jArr) {
        this.f26014a = jArr;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public BigInteger mo893a() {
        return AbstractC12901m.m509c(this.f26014a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo899a(int i) {
        if (i < 1) {
            return this;
        }
        long[] m515a = AbstractC12901m.m515a();
        C12815cq.m1040a(this.f26014a, i, m515a);
        return new C12816cr(m515a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo889a(AbstractC12856e abstractC12856e) {
        long[] m515a = AbstractC12901m.m515a();
        C12815cq.m1037a(this.f26014a, ((C12816cr) abstractC12856e).f26014a, m515a);
        return new C12816cr(m515a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo888a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
        long[] jArr = this.f26014a;
        long[] jArr2 = ((C12816cr) abstractC12856e).f26014a;
        long[] jArr3 = ((C12816cr) abstractC12856e2).f26014a;
        long[] m511b = AbstractC12901m.m511b();
        C12815cq.m1027f(jArr, m511b);
        C12815cq.m1028e(jArr2, jArr3, m511b);
        long[] m515a = AbstractC12901m.m515a();
        C12815cq.m1033c(m511b, m515a);
        return new C12816cr(m515a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo887a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e abstractC12856e3) {
        return mo882b(abstractC12856e, abstractC12856e2, abstractC12856e3);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public int mo886b() {
        return 571;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo883b(AbstractC12856e abstractC12856e) {
        return mo889a(abstractC12856e);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo882b(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e abstractC12856e3) {
        long[] jArr = this.f26014a;
        long[] jArr2 = ((C12816cr) abstractC12856e).f26014a;
        long[] jArr3 = ((C12816cr) abstractC12856e2).f26014a;
        long[] jArr4 = ((C12816cr) abstractC12856e3).f26014a;
        long[] m511b = AbstractC12901m.m511b();
        C12815cq.m1028e(jArr, jArr2, m511b);
        C12815cq.m1028e(jArr3, jArr4, m511b);
        long[] m515a = AbstractC12901m.m515a();
        C12815cq.m1033c(m511b, m515a);
        return new C12816cr(m515a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo881c() {
        long[] m515a = AbstractC12901m.m515a();
        C12815cq.m1038a(this.f26014a, m515a);
        return new C12816cr(m515a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo878c(AbstractC12856e abstractC12856e) {
        long[] m515a = AbstractC12901m.m515a();
        C12815cq.m1030d(this.f26014a, ((C12816cr) abstractC12856e).f26014a, m515a);
        return new C12816cr(m515a);
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
        long[] m515a = AbstractC12901m.m515a();
        C12815cq.m1029e(this.f26014a, m515a);
        return new C12816cr(m515a);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C12816cr) {
            return AbstractC12901m.m512a(this.f26014a, ((C12816cr) obj).f26014a);
        }
        return false;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: f */
    public AbstractC12856e mo871f() {
        long[] m515a = AbstractC12901m.m515a();
        C12815cq.m1035b(this.f26014a, m515a);
        return new C12816cr(m515a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: g */
    public AbstractC12856e mo870g() {
        long[] m515a = AbstractC12901m.m515a();
        C12815cq.m1031d(this.f26014a, m515a);
        return new C12816cr(m515a);
    }

    public int hashCode() {
        return C12957a.m431a(this.f26014a, 0, 9) ^ 5711052;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: i */
    public boolean mo896i() {
        return AbstractC12901m.m513a(this.f26014a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: j */
    public boolean mo895j() {
        return AbstractC12901m.m510b(this.f26014a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: k */
    public boolean mo894k() {
        return (this.f26014a[0] & 1) != 0;
    }
}
