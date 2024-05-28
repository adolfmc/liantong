package org.p415a.p436e.p437a.p438a.p441c;

import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p437a.AbstractC12860g;
import org.p415a.p436e.p437a.InterfaceC12849c;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.bj */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12781bj extends AbstractC12860g.AbstractC12861a {
    public C12781bj(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
        this(abstractC12850d, abstractC12856e, abstractC12856e2, false);
    }

    public C12781bj(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, boolean z) {
        super(abstractC12850d, abstractC12856e, abstractC12856e2);
        if ((abstractC12856e == null) != (abstractC12856e2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f26108f = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12781bj(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr, boolean z) {
        super(abstractC12850d, abstractC12856e, abstractC12856e2, abstractC12856eArr);
        this.f26108f = z;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: b */
    public AbstractC12860g mo839b(AbstractC12860g abstractC12860g) {
        AbstractC12856e abstractC12856e;
        AbstractC12856e abstractC12856e2;
        AbstractC12856e abstractC12856e3;
        AbstractC12856e mo878c;
        AbstractC12856e abstractC12856e4;
        AbstractC12856e abstractC12856e5;
        if (m851n()) {
            return abstractC12860g;
        }
        if (abstractC12860g.m851n()) {
            return this;
        }
        AbstractC12850d c = m861c();
        AbstractC12856e abstractC12856e6 = this.f26105c;
        AbstractC12856e m857h = abstractC12860g.m857h();
        if (abstractC12856e6.mo895j()) {
            return m857h.mo895j() ? c.mo901e() : abstractC12860g.mo839b(this);
        }
        AbstractC12856e abstractC12856e7 = this.f26106d;
        AbstractC12856e abstractC12856e8 = this.f26107e[0];
        AbstractC12856e m856i = abstractC12860g.m856i();
        AbstractC12856e mo842a = abstractC12860g.mo842a(0);
        boolean mo896i = abstractC12856e8.mo896i();
        if (mo896i) {
            abstractC12856e = m857h;
            abstractC12856e2 = m856i;
        } else {
            abstractC12856e = m857h.mo878c(abstractC12856e8);
            abstractC12856e2 = m856i.mo878c(abstractC12856e8);
        }
        boolean mo896i2 = mo842a.mo896i();
        if (mo896i2) {
            abstractC12856e3 = abstractC12856e7;
        } else {
            abstractC12856e6 = abstractC12856e6.mo878c(mo842a);
            abstractC12856e3 = abstractC12856e7.mo878c(mo842a);
        }
        AbstractC12856e mo889a = abstractC12856e3.mo889a(abstractC12856e2);
        AbstractC12856e mo889a2 = abstractC12856e6.mo889a(abstractC12856e);
        if (mo889a2.mo895j()) {
            return mo889a.mo895j() ? mo831r() : c.mo901e();
        }
        if (m857h.mo895j()) {
            AbstractC12860g m = m852m();
            AbstractC12856e m858f = m.m858f();
            AbstractC12856e mo844g = m.mo844g();
            AbstractC12856e mo875d = mo844g.mo889a(m856i).mo875d(m858f);
            abstractC12856e4 = mo875d.mo874e().mo889a(mo875d).mo889a(m858f).mo881c();
            if (abstractC12856e4.mo895j()) {
                return new C12781bj(c, abstractC12856e4, c.m922h(), this.f26108f);
            }
            abstractC12856e5 = mo875d.mo878c(m858f.mo889a(abstractC12856e4)).mo889a(abstractC12856e4).mo889a(mo844g).mo875d(abstractC12856e4).mo889a(abstractC12856e4);
            mo878c = c.mo906a(InterfaceC12849c.f26070d);
        } else {
            AbstractC12856e mo874e = mo889a2.mo874e();
            AbstractC12856e mo878c2 = mo889a.mo878c(abstractC12856e6);
            AbstractC12856e mo878c3 = mo889a.mo878c(abstractC12856e);
            AbstractC12856e mo878c4 = mo878c2.mo878c(mo878c3);
            if (mo878c4.mo895j()) {
                return new C12781bj(c, mo878c4, c.m922h(), this.f26108f);
            }
            AbstractC12856e mo878c5 = mo889a.mo878c(mo874e);
            mo878c = !mo896i2 ? mo878c5.mo878c(mo842a) : mo878c5;
            AbstractC12856e mo888a = mo878c3.mo889a(mo874e).mo888a(mo878c, abstractC12856e7.mo889a(abstractC12856e8));
            if (!mo896i) {
                mo878c = mo878c.mo878c(abstractC12856e8);
            }
            abstractC12856e4 = mo878c4;
            abstractC12856e5 = mo888a;
        }
        return new C12781bj(c, abstractC12856e4, abstractC12856e5, new AbstractC12856e[]{mo878c}, this.f26108f);
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
        AbstractC12856e mo889a = mo874e3.mo889a(mo874e2).mo889a(abstractC12856e2.mo878c(abstractC12856e3));
        AbstractC12856e mo882b = m856i.mo878c(mo874e3).mo889a(mo874e2).mo882b(mo889a, mo874e, mo874e3);
        AbstractC12856e mo878c = m857h.mo878c(mo874e3);
        AbstractC12856e mo874e4 = mo878c.mo889a(mo889a).mo874e();
        if (mo874e4.mo895j()) {
            return mo882b.mo895j() ? abstractC12860g.mo831r() : c.mo901e();
        } else if (mo882b.mo895j()) {
            return new C12781bj(c, mo882b, c.m922h(), this.f26108f);
        } else {
            AbstractC12856e mo878c2 = mo882b.mo874e().mo878c(mo878c);
            AbstractC12856e mo878c3 = mo882b.mo878c(mo874e4).mo878c(mo874e3);
            return new C12781bj(c, mo878c2, mo882b.mo889a(mo874e4).mo874e().mo882b(mo889a, m856i.mo881c(), mo878c3), new AbstractC12856e[]{mo878c3}, this.f26108f);
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
        return new C12781bj(this.f26104b, abstractC12856e, abstractC12856e2.mo889a(abstractC12856e3), new AbstractC12856e[]{abstractC12856e3}, this.f26108f);
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
        AbstractC12856e mo878c = mo896i ? abstractC12856e2 : abstractC12856e2.mo878c(abstractC12856e3);
        if (!mo896i) {
            abstractC12856e3 = abstractC12856e3.mo874e();
        }
        AbstractC12856e mo889a = abstractC12856e2.mo874e().mo889a(mo878c).mo889a(abstractC12856e3);
        if (mo889a.mo895j()) {
            return new C12781bj(c, mo889a, c.m922h(), this.f26108f);
        }
        AbstractC12856e mo874e = mo889a.mo874e();
        AbstractC12856e mo878c2 = mo896i ? mo889a : mo889a.mo878c(abstractC12856e3);
        AbstractC12856e mo874e2 = abstractC12856e2.mo889a(abstractC12856e).mo874e();
        return new C12781bj(c, mo874e, mo874e2.mo889a(mo889a).mo889a(abstractC12856e3).mo878c(mo874e2).mo889a(mo874e), new AbstractC12856e[]{mo878c2}, this.f26108f);
    }
}
