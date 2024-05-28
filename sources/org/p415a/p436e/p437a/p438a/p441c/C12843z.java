package org.p415a.p436e.p437a.p438a.p441c;

import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p437a.AbstractC12860g;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12895g;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.z */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12843z extends AbstractC12860g.AbstractC12862b {
    public C12843z(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
        this(abstractC12850d, abstractC12856e, abstractC12856e2, false);
    }

    public C12843z(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, boolean z) {
        super(abstractC12850d, abstractC12856e, abstractC12856e2);
        if ((abstractC12856e == null) != (abstractC12856e2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f26108f = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12843z(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr, boolean z) {
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
        C12842y c12842y = (C12842y) this.f26105c;
        C12842y c12842y2 = (C12842y) this.f26106d;
        C12842y c12842y3 = (C12842y) abstractC12860g.m858f();
        C12842y c12842y4 = (C12842y) abstractC12860g.mo844g();
        C12842y c12842y5 = (C12842y) this.f26107e[0];
        C12842y c12842y6 = (C12842y) abstractC12860g.mo842a(0);
        int[] m581b = AbstractC12895g.m581b();
        int[] m590a = AbstractC12895g.m590a();
        int[] m590a2 = AbstractC12895g.m590a();
        int[] m590a3 = AbstractC12895g.m590a();
        boolean mo896i = c12842y5.mo896i();
        if (mo896i) {
            iArr = c12842y3.f26056b;
            iArr2 = c12842y4.f26056b;
        } else {
            C12841x.m962d(c12842y5.f26056b, m590a2);
            C12841x.m965b(m590a2, c12842y3.f26056b, m590a);
            C12841x.m965b(m590a2, c12842y5.f26056b, m590a2);
            C12841x.m965b(m590a2, c12842y4.f26056b, m590a2);
            iArr = m590a;
            iArr2 = m590a2;
        }
        boolean mo896i2 = c12842y6.mo896i();
        if (mo896i2) {
            iArr3 = c12842y.f26056b;
            iArr4 = c12842y2.f26056b;
        } else {
            C12841x.m962d(c12842y6.f26056b, m590a3);
            C12841x.m965b(m590a3, c12842y.f26056b, m581b);
            C12841x.m965b(m590a3, c12842y6.f26056b, m590a3);
            C12841x.m965b(m590a3, c12842y2.f26056b, m590a3);
            iArr3 = m581b;
            iArr4 = m590a3;
        }
        int[] m590a4 = AbstractC12895g.m590a();
        C12841x.m961d(iArr3, iArr, m590a4);
        C12841x.m961d(iArr4, iArr2, m590a);
        if (AbstractC12895g.m580b(m590a4)) {
            return AbstractC12895g.m580b(m590a) ? mo831r() : c.mo901e();
        }
        C12841x.m962d(m590a4, m590a2);
        int[] m590a5 = AbstractC12895g.m590a();
        C12841x.m965b(m590a2, m590a4, m590a5);
        C12841x.m965b(m590a2, iArr3, m590a2);
        C12841x.m966b(m590a5, m590a5);
        AbstractC12895g.m575c(iArr4, m590a5, m581b);
        C12841x.m971a(AbstractC12895g.m578b(m590a2, m590a2, m590a5), m590a5);
        C12842y c12842y7 = new C12842y(m590a3);
        C12841x.m962d(m590a, c12842y7.f26056b);
        C12841x.m961d(c12842y7.f26056b, m590a5, c12842y7.f26056b);
        C12842y c12842y8 = new C12842y(m590a5);
        C12841x.m961d(m590a2, c12842y7.f26056b, c12842y8.f26056b);
        C12841x.m963c(c12842y8.f26056b, m590a, m581b);
        C12841x.m964c(m581b, c12842y8.f26056b);
        C12842y c12842y9 = new C12842y(m590a4);
        if (!mo896i) {
            C12841x.m965b(c12842y9.f26056b, c12842y5.f26056b, c12842y9.f26056b);
        }
        if (!mo896i2) {
            C12841x.m965b(c12842y9.f26056b, c12842y6.f26056b, c12842y9.f26056b);
        }
        return new C12843z(c, c12842y7, c12842y8, new AbstractC12856e[]{c12842y9}, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: d */
    public AbstractC12860g mo836d(AbstractC12860g abstractC12860g) {
        return this == abstractC12860g ? mo830s() : m851n() ? abstractC12860g : abstractC12860g.m851n() ? mo831r() : this.f26106d.mo895j() ? abstractC12860g : mo831r().mo839b(abstractC12860g);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: q */
    public AbstractC12860g mo832q() {
        return m851n() ? this : new C12843z(this.f26104b, this.f26105c, this.f26106d.mo877d(), this.f26107e, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: r */
    public AbstractC12860g mo831r() {
        if (m851n()) {
            return this;
        }
        AbstractC12850d c = m861c();
        C12842y c12842y = (C12842y) this.f26106d;
        if (c12842y.mo895j()) {
            return c.mo901e();
        }
        C12842y c12842y2 = (C12842y) this.f26105c;
        C12842y c12842y3 = (C12842y) this.f26107e[0];
        int[] m590a = AbstractC12895g.m590a();
        C12841x.m962d(c12842y.f26056b, m590a);
        int[] m590a2 = AbstractC12895g.m590a();
        C12841x.m962d(m590a, m590a2);
        int[] m590a3 = AbstractC12895g.m590a();
        C12841x.m962d(c12842y2.f26056b, m590a3);
        C12841x.m971a(AbstractC12895g.m578b(m590a3, m590a3, m590a3), m590a3);
        C12841x.m965b(m590a, c12842y2.f26056b, m590a);
        C12841x.m971a(AbstractC12891c.m680c(7, m590a, 2, 0), m590a);
        int[] m590a4 = AbstractC12895g.m590a();
        C12841x.m971a(AbstractC12891c.m702a(7, m590a2, 3, 0, m590a4), m590a4);
        C12842y c12842y4 = new C12842y(m590a2);
        C12841x.m962d(m590a3, c12842y4.f26056b);
        C12841x.m961d(c12842y4.f26056b, m590a, c12842y4.f26056b);
        C12841x.m961d(c12842y4.f26056b, m590a, c12842y4.f26056b);
        C12842y c12842y5 = new C12842y(m590a);
        C12841x.m961d(m590a, c12842y4.f26056b, c12842y5.f26056b);
        C12841x.m965b(c12842y5.f26056b, m590a3, c12842y5.f26056b);
        C12841x.m961d(c12842y5.f26056b, m590a4, c12842y5.f26056b);
        C12842y c12842y6 = new C12842y(m590a3);
        C12841x.m960e(c12842y.f26056b, c12842y6.f26056b);
        if (!c12842y3.mo896i()) {
            C12841x.m965b(c12842y6.f26056b, c12842y3.f26056b, c12842y6.f26056b);
        }
        return new C12843z(c, c12842y4, c12842y5, new AbstractC12856e[]{c12842y6}, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: s */
    public AbstractC12860g mo830s() {
        return (m851n() || this.f26106d.mo895j()) ? this : mo831r().mo839b(this);
    }
}
