package org.p415a.p436e.p437a.p438a.p441c;

import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p437a.AbstractC12860g;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12894f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.r */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12835r extends AbstractC12860g.AbstractC12862b {
    public C12835r(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
        this(abstractC12850d, abstractC12856e, abstractC12856e2, false);
    }

    public C12835r(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, boolean z) {
        super(abstractC12850d, abstractC12856e, abstractC12856e2);
        if ((abstractC12856e == null) != (abstractC12856e2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f26108f = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12835r(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr, boolean z) {
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
        C12834q c12834q = (C12834q) this.f26105c;
        C12834q c12834q2 = (C12834q) this.f26106d;
        C12834q c12834q3 = (C12834q) abstractC12860g.m858f();
        C12834q c12834q4 = (C12834q) abstractC12860g.mo844g();
        C12834q c12834q5 = (C12834q) this.f26107e[0];
        C12834q c12834q6 = (C12834q) abstractC12860g.mo842a(0);
        int[] m602c = AbstractC12894f.m602c();
        int[] m624a = AbstractC12894f.m624a();
        int[] m624a2 = AbstractC12894f.m624a();
        int[] m624a3 = AbstractC12894f.m624a();
        boolean mo896i = c12834q5.mo896i();
        if (mo896i) {
            iArr = c12834q3.f26041b;
            iArr2 = c12834q4.f26041b;
        } else {
            C12833p.m988d(c12834q5.f26041b, m624a2);
            C12833p.m991b(m624a2, c12834q3.f26041b, m624a);
            C12833p.m991b(m624a2, c12834q5.f26041b, m624a2);
            C12833p.m991b(m624a2, c12834q4.f26041b, m624a2);
            iArr = m624a;
            iArr2 = m624a2;
        }
        boolean mo896i2 = c12834q6.mo896i();
        if (mo896i2) {
            iArr3 = c12834q.f26041b;
            iArr4 = c12834q2.f26041b;
        } else {
            C12833p.m988d(c12834q6.f26041b, m624a3);
            C12833p.m991b(m624a3, c12834q.f26041b, m602c);
            C12833p.m991b(m624a3, c12834q6.f26041b, m624a3);
            C12833p.m991b(m624a3, c12834q2.f26041b, m624a3);
            iArr3 = m602c;
            iArr4 = m624a3;
        }
        int[] m624a4 = AbstractC12894f.m624a();
        C12833p.m987d(iArr3, iArr, m624a4);
        C12833p.m987d(iArr4, iArr2, m624a);
        if (AbstractC12894f.m608b(m624a4)) {
            return AbstractC12894f.m608b(m624a) ? mo831r() : c.mo901e();
        }
        C12833p.m988d(m624a4, m624a2);
        int[] m624a5 = AbstractC12894f.m624a();
        C12833p.m991b(m624a2, m624a4, m624a5);
        C12833p.m991b(m624a2, iArr3, m624a2);
        C12833p.m992b(m624a5, m624a5);
        AbstractC12894f.m597c(iArr4, m624a5, m602c);
        C12833p.m997a(AbstractC12894f.m604b(m624a2, m624a2, m624a5), m624a5);
        C12834q c12834q7 = new C12834q(m624a3);
        C12833p.m988d(m624a, c12834q7.f26041b);
        C12833p.m987d(c12834q7.f26041b, m624a5, c12834q7.f26041b);
        C12834q c12834q8 = new C12834q(m624a5);
        C12833p.m987d(m624a2, c12834q7.f26041b, c12834q8.f26041b);
        C12833p.m989c(c12834q8.f26041b, m624a, m602c);
        C12833p.m990c(m602c, c12834q8.f26041b);
        C12834q c12834q9 = new C12834q(m624a4);
        if (!mo896i) {
            C12833p.m991b(c12834q9.f26041b, c12834q5.f26041b, c12834q9.f26041b);
        }
        if (!mo896i2) {
            C12833p.m991b(c12834q9.f26041b, c12834q6.f26041b, c12834q9.f26041b);
        }
        return new C12835r(c, c12834q7, c12834q8, new AbstractC12856e[]{c12834q9}, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: d */
    public AbstractC12860g mo836d(AbstractC12860g abstractC12860g) {
        return this == abstractC12860g ? mo830s() : m851n() ? abstractC12860g : abstractC12860g.m851n() ? mo831r() : this.f26106d.mo895j() ? abstractC12860g : mo831r().mo839b(abstractC12860g);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: q */
    public AbstractC12860g mo832q() {
        return m851n() ? this : new C12835r(this.f26104b, this.f26105c, this.f26106d.mo877d(), this.f26107e, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: r */
    public AbstractC12860g mo831r() {
        if (m851n()) {
            return this;
        }
        AbstractC12850d c = m861c();
        C12834q c12834q = (C12834q) this.f26106d;
        if (c12834q.mo895j()) {
            return c.mo901e();
        }
        C12834q c12834q2 = (C12834q) this.f26105c;
        C12834q c12834q3 = (C12834q) this.f26107e[0];
        int[] m624a = AbstractC12894f.m624a();
        C12833p.m988d(c12834q.f26041b, m624a);
        int[] m624a2 = AbstractC12894f.m624a();
        C12833p.m988d(m624a, m624a2);
        int[] m624a3 = AbstractC12894f.m624a();
        C12833p.m988d(c12834q2.f26041b, m624a3);
        C12833p.m997a(AbstractC12894f.m604b(m624a3, m624a3, m624a3), m624a3);
        C12833p.m991b(m624a, c12834q2.f26041b, m624a);
        C12833p.m997a(AbstractC12891c.m680c(6, m624a, 2, 0), m624a);
        int[] m624a4 = AbstractC12894f.m624a();
        C12833p.m997a(AbstractC12891c.m702a(6, m624a2, 3, 0, m624a4), m624a4);
        C12834q c12834q4 = new C12834q(m624a2);
        C12833p.m988d(m624a3, c12834q4.f26041b);
        C12833p.m987d(c12834q4.f26041b, m624a, c12834q4.f26041b);
        C12833p.m987d(c12834q4.f26041b, m624a, c12834q4.f26041b);
        C12834q c12834q5 = new C12834q(m624a);
        C12833p.m987d(m624a, c12834q4.f26041b, c12834q5.f26041b);
        C12833p.m991b(c12834q5.f26041b, m624a3, c12834q5.f26041b);
        C12833p.m987d(c12834q5.f26041b, m624a4, c12834q5.f26041b);
        C12834q c12834q6 = new C12834q(m624a3);
        C12833p.m986e(c12834q.f26041b, c12834q6.f26041b);
        if (!c12834q3.mo896i()) {
            C12833p.m991b(c12834q6.f26041b, c12834q3.f26041b, c12834q6.f26041b);
        }
        return new C12835r(c, c12834q4, c12834q5, new AbstractC12856e[]{c12834q6}, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: s */
    public AbstractC12860g mo830s() {
        return (m851n() || this.f26106d.mo895j()) ? this : mo831r().mo839b(this);
    }
}
