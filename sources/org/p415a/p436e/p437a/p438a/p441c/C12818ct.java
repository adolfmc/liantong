package org.p415a.p436e.p437a.p438a.p441c;

import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p437a.AbstractC12860g;
import org.p415a.p436e.p437a.InterfaceC12849c;
import org.p415a.p436e.p444c.AbstractC12901m;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.ct */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12818ct extends AbstractC12860g.AbstractC12861a {
    public C12818ct(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
        this(abstractC12850d, abstractC12856e, abstractC12856e2, false);
    }

    public C12818ct(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, boolean z) {
        super(abstractC12850d, abstractC12856e, abstractC12856e2);
        if ((abstractC12856e == null) != (abstractC12856e2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f26108f = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12818ct(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr, boolean z) {
        super(abstractC12850d, abstractC12856e, abstractC12856e2, abstractC12856eArr);
        this.f26108f = z;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: b */
    public AbstractC12860g mo839b(AbstractC12860g abstractC12860g) {
        long[] jArr;
        long[] jArr2;
        long[] jArr3;
        long[] jArr4;
        C12816cr c12816cr;
        C12816cr c12816cr2;
        C12816cr c12816cr3;
        if (m851n()) {
            return abstractC12860g;
        }
        if (abstractC12860g.m851n()) {
            return this;
        }
        AbstractC12850d c = m861c();
        C12816cr c12816cr4 = (C12816cr) this.f26105c;
        C12816cr c12816cr5 = (C12816cr) abstractC12860g.m857h();
        if (c12816cr4.mo895j()) {
            return c12816cr5.mo895j() ? c.mo901e() : abstractC12860g.mo839b(this);
        }
        C12816cr c12816cr6 = (C12816cr) this.f26106d;
        C12816cr c12816cr7 = (C12816cr) this.f26107e[0];
        C12816cr c12816cr8 = (C12816cr) abstractC12860g.m856i();
        C12816cr c12816cr9 = (C12816cr) abstractC12860g.mo842a(0);
        long[] m515a = AbstractC12901m.m515a();
        long[] m515a2 = AbstractC12901m.m515a();
        long[] m515a3 = AbstractC12901m.m515a();
        long[] m515a4 = AbstractC12901m.m515a();
        long[] m1042a = c12816cr7.mo896i() ? null : C12815cq.m1042a(c12816cr7.f26014a);
        if (m1042a == null) {
            jArr = c12816cr5.f26014a;
            jArr2 = c12816cr8.f26014a;
        } else {
            C12815cq.m1026f(c12816cr5.f26014a, m1042a, m515a2);
            C12815cq.m1026f(c12816cr8.f26014a, m1042a, m515a4);
            jArr = m515a2;
            jArr2 = m515a4;
        }
        long[] m1042a2 = c12816cr9.mo896i() ? null : C12815cq.m1042a(c12816cr9.f26014a);
        if (m1042a2 == null) {
            jArr3 = c12816cr4.f26014a;
            jArr4 = c12816cr6.f26014a;
        } else {
            C12815cq.m1026f(c12816cr4.f26014a, m1042a2, m515a);
            C12815cq.m1026f(c12816cr6.f26014a, m1042a2, m515a3);
            jArr3 = m515a;
            jArr4 = m515a3;
        }
        C12815cq.m1037a(jArr4, jArr2, m515a3);
        C12815cq.m1037a(jArr3, jArr, m515a4);
        if (AbstractC12901m.m510b(m515a4)) {
            return AbstractC12901m.m510b(m515a3) ? mo831r() : c.mo901e();
        }
        if (c12816cr5.mo895j()) {
            AbstractC12860g m = m852m();
            C12816cr c12816cr10 = (C12816cr) m.m858f();
            AbstractC12856e mo844g = m.mo844g();
            AbstractC12856e mo875d = mo844g.mo889a(c12816cr8).mo875d(c12816cr10);
            c12816cr = (C12816cr) mo875d.mo874e().mo889a(mo875d).mo889a(c12816cr10);
            if (c12816cr.mo895j()) {
                return new C12818ct(c, c12816cr, c.m922h(), this.f26108f);
            }
            c12816cr3 = (C12816cr) c.mo906a(InterfaceC12849c.f26070d);
            c12816cr2 = (C12816cr) mo875d.mo878c(c12816cr10.mo889a(c12816cr)).mo889a(c12816cr).mo889a(mo844g).mo875d(c12816cr).mo889a(c12816cr);
        } else {
            C12815cq.m1029e(m515a4, m515a4);
            long[] m1042a3 = C12815cq.m1042a(m515a3);
            C12815cq.m1026f(jArr3, m1042a3, m515a);
            C12815cq.m1026f(jArr, m1042a3, m515a2);
            C12816cr c12816cr11 = new C12816cr(m515a);
            C12815cq.m1030d(m515a, m515a2, c12816cr11.f26014a);
            if (c12816cr11.mo895j()) {
                return new C12818ct(c, c12816cr11, c.m922h(), this.f26108f);
            }
            C12816cr c12816cr12 = new C12816cr(m515a3);
            C12815cq.m1026f(m515a4, m1042a3, c12816cr12.f26014a);
            if (m1042a2 != null) {
                C12815cq.m1026f(c12816cr12.f26014a, m1042a2, c12816cr12.f26014a);
            }
            long[] m511b = AbstractC12901m.m511b();
            C12815cq.m1037a(m515a2, m515a4, m515a4);
            C12815cq.m1027f(m515a4, m511b);
            C12815cq.m1037a(c12816cr6.f26014a, c12816cr7.f26014a, m515a4);
            C12815cq.m1028e(m515a4, c12816cr12.f26014a, m511b);
            C12816cr c12816cr13 = new C12816cr(m515a4);
            C12815cq.m1033c(m511b, c12816cr13.f26014a);
            if (m1042a != null) {
                C12815cq.m1026f(c12816cr12.f26014a, m1042a, c12816cr12.f26014a);
            }
            c12816cr = c12816cr11;
            c12816cr2 = c12816cr13;
            c12816cr3 = c12816cr12;
        }
        return new C12818ct(c, c12816cr, c12816cr2, new AbstractC12856e[]{c12816cr3}, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: d */
    public AbstractC12860g mo836d(AbstractC12860g abstractC12860g) {
        if (m851n()) {
            return abstractC12860g;
        }
        if (abstractC12860g.m851n()) {
            return mo831r();
        }
        AbstractC12850d c = m861c();
        AbstractC12856e abstractC12856e = this.f26105c;
        if (abstractC12856e.mo895j()) {
            return abstractC12860g;
        }
        AbstractC12856e m857h = abstractC12860g.m857h();
        AbstractC12856e mo842a = abstractC12860g.mo842a(0);
        if (m857h.mo895j() || !mo842a.mo896i()) {
            return mo831r().mo839b(abstractC12860g);
        }
        AbstractC12856e abstractC12856e2 = this.f26106d;
        AbstractC12856e abstractC12856e3 = this.f26107e[0];
        AbstractC12856e m856i = abstractC12860g.m856i();
        AbstractC12856e mo874e = abstractC12856e.mo874e();
        AbstractC12856e mo874e2 = abstractC12856e2.mo874e();
        AbstractC12856e mo874e3 = abstractC12856e3.mo874e();
        AbstractC12856e mo889a = mo874e2.mo889a(abstractC12856e2.mo878c(abstractC12856e3));
        AbstractC12856e mo881c = m856i.mo881c();
        AbstractC12856e mo882b = mo881c.mo878c(mo874e3).mo889a(mo874e2).mo882b(mo889a, mo874e, mo874e3);
        AbstractC12856e mo878c = m857h.mo878c(mo874e3);
        AbstractC12856e mo874e4 = mo878c.mo889a(mo889a).mo874e();
        if (mo874e4.mo895j()) {
            return mo882b.mo895j() ? abstractC12860g.mo831r() : c.mo901e();
        } else if (mo882b.mo895j()) {
            return new C12818ct(c, mo882b, c.m922h(), this.f26108f);
        } else {
            AbstractC12856e mo878c2 = mo882b.mo874e().mo878c(mo878c);
            AbstractC12856e mo878c3 = mo882b.mo878c(mo874e4).mo878c(mo874e3);
            return new C12818ct(c, mo878c2, mo882b.mo889a(mo874e4).mo874e().mo882b(mo889a, mo881c, mo878c3), new AbstractC12856e[]{mo878c3}, this.f26108f);
        }
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: g */
    public AbstractC12856e mo844g() {
        AbstractC12856e abstractC12856e = this.f26105c;
        AbstractC12856e abstractC12856e2 = this.f26106d;
        if (m851n() || abstractC12856e.mo895j()) {
            return abstractC12856e2;
        }
        AbstractC12856e mo878c = abstractC12856e2.mo889a(abstractC12856e).mo878c(abstractC12856e);
        AbstractC12856e abstractC12856e3 = this.f26107e[0];
        return !abstractC12856e3.mo896i() ? mo878c.mo875d(abstractC12856e3) : mo878c;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: p */
    public boolean mo843p() {
        AbstractC12856e h = m857h();
        return (h.mo895j() || m856i().mo894k() == h.mo894k()) ? false : true;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: q */
    public AbstractC12860g mo832q() {
        if (m851n()) {
            return this;
        }
        AbstractC12856e abstractC12856e = this.f26105c;
        if (abstractC12856e.mo895j()) {
            return this;
        }
        AbstractC12856e abstractC12856e2 = this.f26106d;
        AbstractC12856e abstractC12856e3 = this.f26107e[0];
        return new C12818ct(this.f26104b, abstractC12856e, abstractC12856e2.mo889a(abstractC12856e3), new AbstractC12856e[]{abstractC12856e3}, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: r */
    public AbstractC12860g mo831r() {
        if (m851n()) {
            return this;
        }
        AbstractC12850d c = m861c();
        AbstractC12856e abstractC12856e = this.f26105c;
        if (abstractC12856e.mo895j()) {
            return c.mo901e();
        }
        AbstractC12856e abstractC12856e2 = this.f26106d;
        AbstractC12856e abstractC12856e3 = this.f26107e[0];
        boolean mo896i = abstractC12856e3.mo896i();
        AbstractC12856e mo874e = mo896i ? abstractC12856e3 : abstractC12856e3.mo874e();
        AbstractC12856e mo889a = mo896i ? abstractC12856e2.mo874e().mo889a(abstractC12856e2) : abstractC12856e2.mo889a(abstractC12856e3).mo878c(abstractC12856e2);
        if (mo889a.mo895j()) {
            return new C12818ct(c, mo889a, c.m922h(), this.f26108f);
        }
        AbstractC12856e mo874e2 = mo889a.mo874e();
        AbstractC12856e mo878c = mo896i ? mo889a : mo889a.mo878c(mo874e);
        AbstractC12856e mo874e3 = abstractC12856e2.mo889a(abstractC12856e).mo874e();
        if (!mo896i) {
            abstractC12856e3 = mo874e.mo874e();
        }
        return new C12818ct(c, mo874e2, mo874e3.mo889a(mo889a).mo889a(mo874e).mo878c(mo874e3).mo889a(abstractC12856e3).mo889a(mo874e2).mo889a(mo878c), new AbstractC12856e[]{mo878c}, this.f26108f);
    }
}
