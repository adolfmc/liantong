package org.p415a.p436e.p437a.p438a.p441c;

import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p437a.AbstractC12860g;
import org.p415a.p436e.p444c.AbstractC12891c;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.at */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12764at extends AbstractC12860g.AbstractC12862b {
    public C12764at(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
        this(abstractC12850d, abstractC12856e, abstractC12856e2, false);
    }

    public C12764at(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, boolean z) {
        super(abstractC12850d, abstractC12856e, abstractC12856e2);
        if ((abstractC12856e == null) != (abstractC12856e2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f26108f = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12764at(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr, boolean z) {
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
        C12763as c12763as = (C12763as) this.f26105c;
        C12763as c12763as2 = (C12763as) this.f26106d;
        C12763as c12763as3 = (C12763as) abstractC12860g.m858f();
        C12763as c12763as4 = (C12763as) abstractC12860g.mo844g();
        C12763as c12763as5 = (C12763as) this.f26107e[0];
        C12763as c12763as6 = (C12763as) abstractC12860g.mo842a(0);
        int[] m712a = AbstractC12891c.m712a(17);
        int[] m712a2 = AbstractC12891c.m712a(17);
        int[] m712a3 = AbstractC12891c.m712a(17);
        int[] m712a4 = AbstractC12891c.m712a(17);
        boolean mo896i = c12763as5.mo896i();
        if (mo896i) {
            iArr = c12763as3.f25980b;
            iArr2 = c12763as4.f25980b;
        } else {
            C12762ar.m1201d(c12763as5.f25980b, m712a3);
            C12762ar.m1204b(m712a3, c12763as3.f25980b, m712a2);
            C12762ar.m1204b(m712a3, c12763as5.f25980b, m712a3);
            C12762ar.m1204b(m712a3, c12763as4.f25980b, m712a3);
            iArr = m712a2;
            iArr2 = m712a3;
        }
        boolean mo896i2 = c12763as6.mo896i();
        if (mo896i2) {
            iArr3 = c12763as.f25980b;
            iArr4 = c12763as2.f25980b;
        } else {
            C12762ar.m1201d(c12763as6.f25980b, m712a4);
            C12762ar.m1204b(m712a4, c12763as.f25980b, m712a);
            C12762ar.m1204b(m712a4, c12763as6.f25980b, m712a4);
            C12762ar.m1204b(m712a4, c12763as2.f25980b, m712a4);
            iArr3 = m712a;
            iArr4 = m712a4;
        }
        int[] m712a5 = AbstractC12891c.m712a(17);
        C12762ar.m1202c(iArr3, iArr, m712a5);
        C12762ar.m1202c(iArr4, iArr2, m712a2);
        if (AbstractC12891c.m673e(17, m712a5)) {
            return AbstractC12891c.m673e(17, m712a2) ? mo831r() : c.mo901e();
        }
        C12762ar.m1201d(m712a5, m712a3);
        int[] m712a6 = AbstractC12891c.m712a(17);
        C12762ar.m1204b(m712a3, m712a5, m712a6);
        C12762ar.m1204b(m712a3, iArr3, m712a3);
        C12762ar.m1204b(iArr4, m712a6, m712a);
        C12763as c12763as7 = new C12763as(m712a4);
        C12762ar.m1201d(m712a2, c12763as7.f25980b);
        C12762ar.m1206a(c12763as7.f25980b, m712a6, c12763as7.f25980b);
        C12762ar.m1202c(c12763as7.f25980b, m712a3, c12763as7.f25980b);
        C12762ar.m1202c(c12763as7.f25980b, m712a3, c12763as7.f25980b);
        C12763as c12763as8 = new C12763as(m712a6);
        C12762ar.m1202c(m712a3, c12763as7.f25980b, c12763as8.f25980b);
        C12762ar.m1204b(c12763as8.f25980b, m712a2, m712a2);
        C12762ar.m1202c(m712a2, m712a, c12763as8.f25980b);
        C12763as c12763as9 = new C12763as(m712a5);
        if (!mo896i) {
            C12762ar.m1204b(c12763as9.f25980b, c12763as5.f25980b, c12763as9.f25980b);
        }
        if (!mo896i2) {
            C12762ar.m1204b(c12763as9.f25980b, c12763as6.f25980b, c12763as9.f25980b);
        }
        return new C12764at(c, c12763as7, c12763as8, new AbstractC12856e[]{c12763as9}, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: d */
    public AbstractC12860g mo836d(AbstractC12860g abstractC12860g) {
        return this == abstractC12860g ? mo830s() : m851n() ? abstractC12860g : abstractC12860g.m851n() ? mo831r() : this.f26106d.mo895j() ? abstractC12860g : mo831r().mo839b(abstractC12860g);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: q */
    public AbstractC12860g mo832q() {
        return m851n() ? this : new C12764at(this.f26104b, this.f26105c, this.f26106d.mo877d(), this.f26107e, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: r */
    public AbstractC12860g mo831r() {
        if (m851n()) {
            return this;
        }
        AbstractC12850d c = m861c();
        C12763as c12763as = (C12763as) this.f26106d;
        if (c12763as.mo895j()) {
            return c.mo901e();
        }
        C12763as c12763as2 = (C12763as) this.f26105c;
        C12763as c12763as3 = (C12763as) this.f26107e[0];
        int[] m712a = AbstractC12891c.m712a(17);
        int[] m712a2 = AbstractC12891c.m712a(17);
        int[] m712a3 = AbstractC12891c.m712a(17);
        C12762ar.m1201d(c12763as.f25980b, m712a3);
        int[] m712a4 = AbstractC12891c.m712a(17);
        C12762ar.m1201d(m712a3, m712a4);
        boolean mo896i = c12763as3.mo896i();
        int[] iArr = c12763as3.f25980b;
        if (!mo896i) {
            C12762ar.m1201d(c12763as3.f25980b, m712a2);
            iArr = m712a2;
        }
        C12762ar.m1202c(c12763as2.f25980b, iArr, m712a);
        C12762ar.m1206a(c12763as2.f25980b, iArr, m712a2);
        C12762ar.m1204b(m712a2, m712a, m712a2);
        AbstractC12891c.m684b(17, m712a2, m712a2, m712a2);
        C12762ar.m1209a(m712a2);
        C12762ar.m1204b(m712a3, c12763as2.f25980b, m712a3);
        AbstractC12891c.m680c(17, m712a3, 2, 0);
        C12762ar.m1209a(m712a3);
        AbstractC12891c.m702a(17, m712a4, 3, 0, m712a);
        C12762ar.m1209a(m712a);
        C12763as c12763as4 = new C12763as(m712a4);
        C12762ar.m1201d(m712a2, c12763as4.f25980b);
        C12762ar.m1202c(c12763as4.f25980b, m712a3, c12763as4.f25980b);
        C12762ar.m1202c(c12763as4.f25980b, m712a3, c12763as4.f25980b);
        C12763as c12763as5 = new C12763as(m712a3);
        C12762ar.m1202c(m712a3, c12763as4.f25980b, c12763as5.f25980b);
        C12762ar.m1204b(c12763as5.f25980b, m712a2, c12763as5.f25980b);
        C12762ar.m1202c(c12763as5.f25980b, m712a, c12763as5.f25980b);
        C12763as c12763as6 = new C12763as(m712a2);
        C12762ar.m1199e(c12763as.f25980b, c12763as6.f25980b);
        if (!mo896i) {
            C12762ar.m1204b(c12763as6.f25980b, c12763as3.f25980b, c12763as6.f25980b);
        }
        return new C12764at(c, c12763as4, c12763as5, new AbstractC12856e[]{c12763as6}, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: s */
    public AbstractC12860g mo830s() {
        return (m851n() || this.f26106d.mo895j()) ? this : mo831r().mo839b(this);
    }
}
