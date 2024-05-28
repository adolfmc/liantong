package org.p415a.p436e.p437a.p438a.p440b;

import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p437a.AbstractC12860g;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12896h;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.b.d */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12743d extends AbstractC12860g.AbstractC12862b {
    public C12743d(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
        this(abstractC12850d, abstractC12856e, abstractC12856e2, false);
    }

    public C12743d(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, boolean z) {
        super(abstractC12850d, abstractC12856e, abstractC12856e2);
        if ((abstractC12856e == null) != (abstractC12856e2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f26108f = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12743d(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr, boolean z) {
        super(abstractC12850d, abstractC12856e, abstractC12856e2, abstractC12856eArr);
        this.f26108f = z;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: b */
    public AbstractC12860g mo839b(AbstractC12860g abstractC12860g) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        if (m851n()) {
            return abstractC12860g;
        }
        if (abstractC12860g.m851n()) {
            return this;
        }
        if (this == abstractC12860g) {
            return mo831r();
        }
        AbstractC12850d c = m861c();
        C12742c c12742c = (C12742c) this.f26105c;
        C12742c c12742c2 = (C12742c) this.f26106d;
        C12742c c12742c3 = (C12742c) abstractC12860g.m858f();
        C12742c c12742c4 = (C12742c) abstractC12860g.mo844g();
        C12742c c12742c5 = (C12742c) this.f26107e[0];
        C12742c c12742c6 = (C12742c) abstractC12860g.mo842a(0);
        int[] m546c = AbstractC12896h.m546c();
        int[] m569a = AbstractC12896h.m569a();
        int[] m569a2 = AbstractC12896h.m569a();
        int[] m569a3 = AbstractC12896h.m569a();
        boolean mo896i = c12742c5.mo896i();
        if (mo896i) {
            iArr = c12742c3.f25946b;
            iArr2 = c12742c4.f25946b;
        } else {
            C12741b.m1272d(c12742c5.f25946b, m569a2);
            C12741b.m1275b(m569a2, c12742c3.f25946b, m569a);
            C12741b.m1275b(m569a2, c12742c5.f25946b, m569a2);
            C12741b.m1275b(m569a2, c12742c4.f25946b, m569a2);
            iArr = m569a;
            iArr2 = m569a2;
        }
        boolean mo896i2 = c12742c6.mo896i();
        if (mo896i2) {
            iArr3 = c12742c.f25946b;
            iArr4 = c12742c2.f25946b;
        } else {
            C12741b.m1272d(c12742c6.f25946b, m569a3);
            C12741b.m1275b(m569a3, c12742c.f25946b, m546c);
            C12741b.m1275b(m569a3, c12742c6.f25946b, m569a3);
            C12741b.m1275b(m569a3, c12742c2.f25946b, m569a3);
            iArr3 = m546c;
            iArr4 = m569a3;
        }
        int[] m569a4 = AbstractC12896h.m569a();
        C12741b.m1271d(iArr3, iArr, m569a4);
        C12741b.m1271d(iArr4, iArr2, m569a);
        if (AbstractC12896h.m552b(m569a4)) {
            return AbstractC12896h.m552b(m569a) ? mo831r() : c.mo901e();
        }
        C12741b.m1272d(m569a4, m569a2);
        int[] m569a5 = AbstractC12896h.m569a();
        C12741b.m1275b(m569a2, m569a4, m569a5);
        C12741b.m1275b(m569a2, iArr3, m569a2);
        C12741b.m1276b(m569a5, m569a5);
        AbstractC12896h.m541c(iArr4, m569a5, m546c);
        C12741b.m1283a(AbstractC12896h.m548b(m569a2, m569a2, m569a5), m569a5);
        C12742c c12742c7 = new C12742c(m569a3);
        C12741b.m1272d(m569a, c12742c7.f25946b);
        C12741b.m1271d(c12742c7.f25946b, m569a5, c12742c7.f25946b);
        C12742c c12742c8 = new C12742c(m569a5);
        C12741b.m1271d(m569a2, c12742c7.f25946b, c12742c8.f25946b);
        C12741b.m1273c(c12742c8.f25946b, m569a, m546c);
        C12741b.m1274c(m546c, c12742c8.f25946b);
        C12742c c12742c9 = new C12742c(m569a4);
        if (!mo896i) {
            C12741b.m1275b(c12742c9.f25946b, c12742c5.f25946b, c12742c9.f25946b);
        }
        if (!mo896i2) {
            C12741b.m1275b(c12742c9.f25946b, c12742c6.f25946b, c12742c9.f25946b);
        }
        return new C12743d(c, c12742c7, c12742c8, new AbstractC12856e[]{c12742c9}, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: d */
    public AbstractC12860g mo836d(AbstractC12860g abstractC12860g) {
        return this == abstractC12860g ? mo830s() : m851n() ? abstractC12860g : abstractC12860g.m851n() ? mo831r() : this.f26106d.mo895j() ? abstractC12860g : mo831r().mo839b(abstractC12860g);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: q */
    public AbstractC12860g mo832q() {
        return m851n() ? this : new C12743d(this.f26104b, this.f26105c, this.f26106d.mo877d(), this.f26107e, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: r */
    public AbstractC12860g mo831r() {
        if (m851n()) {
            return this;
        }
        AbstractC12850d c = m861c();
        C12742c c12742c = (C12742c) this.f26106d;
        if (c12742c.mo895j()) {
            return c.mo901e();
        }
        C12742c c12742c2 = (C12742c) this.f26105c;
        C12742c c12742c3 = (C12742c) this.f26107e[0];
        int[] m569a = AbstractC12896h.m569a();
        int[] m569a2 = AbstractC12896h.m569a();
        int[] m569a3 = AbstractC12896h.m569a();
        C12741b.m1272d(c12742c.f25946b, m569a3);
        int[] m569a4 = AbstractC12896h.m569a();
        C12741b.m1272d(m569a3, m569a4);
        boolean mo896i = c12742c3.mo896i();
        int[] iArr = c12742c3.f25946b;
        if (!mo896i) {
            C12741b.m1272d(c12742c3.f25946b, m569a2);
            iArr = m569a2;
        }
        C12741b.m1271d(c12742c2.f25946b, iArr, m569a);
        C12741b.m1278a(c12742c2.f25946b, iArr, m569a2);
        C12741b.m1275b(m569a2, m569a, m569a2);
        C12741b.m1283a(AbstractC12896h.m548b(m569a2, m569a2, m569a2), m569a2);
        C12741b.m1275b(m569a3, c12742c2.f25946b, m569a3);
        C12741b.m1283a(AbstractC12891c.m680c(8, m569a3, 2, 0), m569a3);
        C12741b.m1283a(AbstractC12891c.m702a(8, m569a4, 3, 0, m569a), m569a);
        C12742c c12742c4 = new C12742c(m569a4);
        C12741b.m1272d(m569a2, c12742c4.f25946b);
        C12741b.m1271d(c12742c4.f25946b, m569a3, c12742c4.f25946b);
        C12741b.m1271d(c12742c4.f25946b, m569a3, c12742c4.f25946b);
        C12742c c12742c5 = new C12742c(m569a3);
        C12741b.m1271d(m569a3, c12742c4.f25946b, c12742c5.f25946b);
        C12741b.m1275b(c12742c5.f25946b, m569a2, c12742c5.f25946b);
        C12741b.m1271d(c12742c5.f25946b, m569a, c12742c5.f25946b);
        C12742c c12742c6 = new C12742c(m569a2);
        C12741b.m1270e(c12742c.f25946b, c12742c6.f25946b);
        if (!mo896i) {
            C12741b.m1275b(c12742c6.f25946b, c12742c3.f25946b, c12742c6.f25946b);
        }
        return new C12743d(c, c12742c4, c12742c5, new AbstractC12856e[]{c12742c6}, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: s */
    public AbstractC12860g mo830s() {
        return (m851n() || this.f26106d.mo895j()) ? this : mo831r().mo839b(this);
    }
}
