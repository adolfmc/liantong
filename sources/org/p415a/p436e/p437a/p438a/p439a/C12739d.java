package org.p415a.p436e.p437a.p438a.p439a;

import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p437a.AbstractC12860g;
import org.p415a.p436e.p444c.AbstractC12896h;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.a.d */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12739d extends AbstractC12860g.AbstractC12862b {
    public C12739d(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
        this(abstractC12850d, abstractC12856e, abstractC12856e2, false);
    }

    public C12739d(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, boolean z) {
        super(abstractC12850d, abstractC12856e, abstractC12856e2);
        if ((abstractC12856e == null) != (abstractC12856e2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f26108f = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12739d(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr, boolean z) {
        super(abstractC12850d, abstractC12856e, abstractC12856e2, abstractC12856eArr);
        this.f26108f = z;
    }

    /* renamed from: a */
    protected C12738c m1286a(C12738c c12738c, int[] iArr) {
        C12738c c12738c2 = (C12738c) m861c().m923g();
        if (c12738c.mo896i()) {
            return c12738c2;
        }
        C12738c c12738c3 = new C12738c();
        if (iArr == null) {
            iArr = c12738c3.f25940b;
            C12737b.m1289d(c12738c.f25940b, iArr);
        }
        C12737b.m1289d(iArr, c12738c3.f25940b);
        C12737b.m1293b(c12738c3.f25940b, c12738c2.f25940b, c12738c3.f25940b);
        return c12738c3;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: a */
    public AbstractC12856e mo842a(int i) {
        return i == 1 ? m1284t() : super.mo842a(i);
    }

    /* renamed from: b */
    protected C12739d m1285b(boolean z) {
        C12738c c12738c = (C12738c) this.f26105c;
        C12738c c12738c2 = (C12738c) this.f26106d;
        C12738c c12738c3 = (C12738c) this.f26107e[0];
        C12738c m1284t = m1284t();
        int[] m569a = AbstractC12896h.m569a();
        C12737b.m1289d(c12738c.f25940b, m569a);
        C12737b.m1301a(AbstractC12896h.m548b(m569a, m569a, m569a) + AbstractC12896h.m558a(m1284t.f25940b, m569a), m569a);
        int[] m569a2 = AbstractC12896h.m569a();
        C12737b.m1287e(c12738c2.f25940b, m569a2);
        int[] m569a3 = AbstractC12896h.m569a();
        C12737b.m1293b(m569a2, c12738c2.f25940b, m569a3);
        int[] m569a4 = AbstractC12896h.m569a();
        C12737b.m1293b(m569a3, c12738c.f25940b, m569a4);
        C12737b.m1287e(m569a4, m569a4);
        int[] m569a5 = AbstractC12896h.m569a();
        C12737b.m1289d(m569a3, m569a5);
        C12737b.m1287e(m569a5, m569a5);
        C12738c c12738c4 = new C12738c(m569a3);
        C12737b.m1289d(m569a, c12738c4.f25940b);
        C12737b.m1288d(c12738c4.f25940b, m569a4, c12738c4.f25940b);
        C12737b.m1288d(c12738c4.f25940b, m569a4, c12738c4.f25940b);
        C12738c c12738c5 = new C12738c(m569a4);
        C12737b.m1288d(m569a4, c12738c4.f25940b, c12738c5.f25940b);
        C12737b.m1293b(c12738c5.f25940b, m569a, c12738c5.f25940b);
        C12737b.m1288d(c12738c5.f25940b, m569a5, c12738c5.f25940b);
        C12738c c12738c6 = new C12738c(m569a2);
        if (!AbstractC12896h.m563a(c12738c3.f25940b)) {
            C12737b.m1293b(c12738c6.f25940b, c12738c3.f25940b, c12738c6.f25940b);
        }
        C12738c c12738c7 = null;
        if (z) {
            c12738c7 = new C12738c(m569a5);
            C12737b.m1293b(c12738c7.f25940b, m1284t.f25940b, c12738c7.f25940b);
            C12737b.m1287e(c12738c7.f25940b, c12738c7.f25940b);
        }
        return new C12739d(m861c(), c12738c4, c12738c5, new AbstractC12856e[]{c12738c6, c12738c7}, this.f26108f);
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
        C12738c c12738c = (C12738c) this.f26105c;
        C12738c c12738c2 = (C12738c) this.f26106d;
        C12738c c12738c3 = (C12738c) this.f26107e[0];
        C12738c c12738c4 = (C12738c) abstractC12860g.m858f();
        C12738c c12738c5 = (C12738c) abstractC12860g.mo844g();
        C12738c c12738c6 = (C12738c) abstractC12860g.mo842a(0);
        int[] m546c = AbstractC12896h.m546c();
        int[] m569a = AbstractC12896h.m569a();
        int[] m569a2 = AbstractC12896h.m569a();
        int[] m569a3 = AbstractC12896h.m569a();
        boolean mo896i = c12738c3.mo896i();
        if (mo896i) {
            iArr = c12738c4.f25940b;
            iArr2 = c12738c5.f25940b;
        } else {
            C12737b.m1289d(c12738c3.f25940b, m569a2);
            C12737b.m1293b(m569a2, c12738c4.f25940b, m569a);
            C12737b.m1293b(m569a2, c12738c3.f25940b, m569a2);
            C12737b.m1293b(m569a2, c12738c5.f25940b, m569a2);
            iArr = m569a;
            iArr2 = m569a2;
        }
        boolean mo896i2 = c12738c6.mo896i();
        if (mo896i2) {
            iArr3 = c12738c.f25940b;
            iArr4 = c12738c2.f25940b;
        } else {
            C12737b.m1289d(c12738c6.f25940b, m569a3);
            C12737b.m1293b(m569a3, c12738c.f25940b, m546c);
            C12737b.m1293b(m569a3, c12738c6.f25940b, m569a3);
            C12737b.m1293b(m569a3, c12738c2.f25940b, m569a3);
            iArr3 = m546c;
            iArr4 = m569a3;
        }
        int[] m569a4 = AbstractC12896h.m569a();
        C12737b.m1288d(iArr3, iArr, m569a4);
        C12737b.m1288d(iArr4, iArr2, m569a);
        if (AbstractC12896h.m552b(m569a4)) {
            return AbstractC12896h.m552b(m569a) ? mo831r() : c.mo901e();
        }
        int[] m569a5 = AbstractC12896h.m569a();
        C12737b.m1289d(m569a4, m569a5);
        int[] m569a6 = AbstractC12896h.m569a();
        C12737b.m1293b(m569a5, m569a4, m569a6);
        C12737b.m1293b(m569a5, iArr3, m569a2);
        C12737b.m1294b(m569a6, m569a6);
        AbstractC12896h.m541c(iArr4, m569a6, m546c);
        C12737b.m1301a(AbstractC12896h.m548b(m569a2, m569a2, m569a6), m569a6);
        C12738c c12738c7 = new C12738c(m569a3);
        C12737b.m1289d(m569a, c12738c7.f25940b);
        C12737b.m1288d(c12738c7.f25940b, m569a6, c12738c7.f25940b);
        C12738c c12738c8 = new C12738c(m569a6);
        C12737b.m1288d(m569a2, c12738c7.f25940b, c12738c8.f25940b);
        C12737b.m1290c(c12738c8.f25940b, m569a, m546c);
        C12737b.m1291c(m546c, c12738c8.f25940b);
        C12738c c12738c9 = new C12738c(m569a4);
        if (!mo896i) {
            C12737b.m1293b(c12738c9.f25940b, c12738c3.f25940b, c12738c9.f25940b);
        }
        if (!mo896i2) {
            C12737b.m1293b(c12738c9.f25940b, c12738c6.f25940b, c12738c9.f25940b);
        }
        return new C12739d(c, c12738c7, c12738c8, new AbstractC12856e[]{c12738c9, m1286a(c12738c9, (mo896i && mo896i2) ? null : null)}, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: d */
    public AbstractC12860g mo836d(AbstractC12860g abstractC12860g) {
        return this == abstractC12860g ? mo830s() : m851n() ? abstractC12860g : abstractC12860g.m851n() ? mo831r() : this.f26106d.mo895j() ? abstractC12860g : m1285b(false).mo839b(abstractC12860g);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: q */
    public AbstractC12860g mo832q() {
        return m851n() ? this : new C12739d(m861c(), this.f26105c, this.f26106d.mo877d(), this.f26107e, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: r */
    public AbstractC12860g mo831r() {
        if (m851n()) {
            return this;
        }
        return this.f26106d.mo895j() ? m861c().mo901e() : m1285b(true);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: s */
    public AbstractC12860g mo830s() {
        return (m851n() || this.f26106d.mo895j()) ? this : m1285b(false).mo839b(this);
    }

    /* renamed from: t */
    protected C12738c m1284t() {
        C12738c c12738c = (C12738c) this.f26107e[1];
        if (c12738c == null) {
            AbstractC12856e[] abstractC12856eArr = this.f26107e;
            C12738c m1286a = m1286a((C12738c) this.f26107e[0], (int[]) null);
            abstractC12856eArr[1] = m1286a;
            return m1286a;
        }
        return c12738c;
    }
}
