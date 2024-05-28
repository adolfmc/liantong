package org.p415a.p436e.p437a.p438a.p441c;

import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p437a.AbstractC12860g;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12893e;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.j */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12827j extends AbstractC12860g.AbstractC12862b {
    public C12827j(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
        this(abstractC12850d, abstractC12856e, abstractC12856e2, false);
    }

    public C12827j(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, boolean z) {
        super(abstractC12850d, abstractC12856e, abstractC12856e2);
        if ((abstractC12856e == null) != (abstractC12856e2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f26108f = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12827j(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr, boolean z) {
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
        C12826i c12826i = (C12826i) this.f26105c;
        C12826i c12826i2 = (C12826i) this.f26106d;
        C12826i c12826i3 = (C12826i) abstractC12860g.m858f();
        C12826i c12826i4 = (C12826i) abstractC12860g.mo844g();
        C12826i c12826i5 = (C12826i) this.f26107e[0];
        C12826i c12826i6 = (C12826i) abstractC12860g.mo842a(0);
        int[] m636b = AbstractC12893e.m636b();
        int[] m645a = AbstractC12893e.m645a();
        int[] m645a2 = AbstractC12893e.m645a();
        int[] m645a3 = AbstractC12893e.m645a();
        boolean mo896i = c12826i5.mo896i();
        if (mo896i) {
            iArr = c12826i3.f26027b;
            iArr2 = c12826i4.f26027b;
        } else {
            C12825h.m1012d(c12826i5.f26027b, m645a2);
            C12825h.m1015b(m645a2, c12826i3.f26027b, m645a);
            C12825h.m1015b(m645a2, c12826i5.f26027b, m645a2);
            C12825h.m1015b(m645a2, c12826i4.f26027b, m645a2);
            iArr = m645a;
            iArr2 = m645a2;
        }
        boolean mo896i2 = c12826i6.mo896i();
        if (mo896i2) {
            iArr3 = c12826i.f26027b;
            iArr4 = c12826i2.f26027b;
        } else {
            C12825h.m1012d(c12826i6.f26027b, m645a3);
            C12825h.m1015b(m645a3, c12826i.f26027b, m636b);
            C12825h.m1015b(m645a3, c12826i6.f26027b, m645a3);
            C12825h.m1015b(m645a3, c12826i2.f26027b, m645a3);
            iArr3 = m636b;
            iArr4 = m645a3;
        }
        int[] m645a4 = AbstractC12893e.m645a();
        C12825h.m1011d(iArr3, iArr, m645a4);
        C12825h.m1011d(iArr4, iArr2, m645a);
        if (AbstractC12893e.m634b(m645a4)) {
            return AbstractC12893e.m634b(m645a) ? mo831r() : c.mo901e();
        }
        C12825h.m1012d(m645a4, m645a2);
        int[] m645a5 = AbstractC12893e.m645a();
        C12825h.m1015b(m645a2, m645a4, m645a5);
        C12825h.m1015b(m645a2, iArr3, m645a2);
        C12825h.m1016b(m645a5, m645a5);
        AbstractC12893e.m629c(iArr4, m645a5, m636b);
        C12825h.m1021a(AbstractC12893e.m632b(m645a2, m645a2, m645a5), m645a5);
        C12826i c12826i7 = new C12826i(m645a3);
        C12825h.m1012d(m645a, c12826i7.f26027b);
        C12825h.m1011d(c12826i7.f26027b, m645a5, c12826i7.f26027b);
        C12826i c12826i8 = new C12826i(m645a5);
        C12825h.m1011d(m645a2, c12826i7.f26027b, c12826i8.f26027b);
        C12825h.m1013c(c12826i8.f26027b, m645a, m636b);
        C12825h.m1014c(m636b, c12826i8.f26027b);
        C12826i c12826i9 = new C12826i(m645a4);
        if (!mo896i) {
            C12825h.m1015b(c12826i9.f26027b, c12826i5.f26027b, c12826i9.f26027b);
        }
        if (!mo896i2) {
            C12825h.m1015b(c12826i9.f26027b, c12826i6.f26027b, c12826i9.f26027b);
        }
        return new C12827j(c, c12826i7, c12826i8, new AbstractC12856e[]{c12826i9}, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: d */
    public AbstractC12860g mo836d(AbstractC12860g abstractC12860g) {
        return this == abstractC12860g ? mo830s() : m851n() ? abstractC12860g : abstractC12860g.m851n() ? mo831r() : this.f26106d.mo895j() ? abstractC12860g : mo831r().mo839b(abstractC12860g);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: q */
    public AbstractC12860g mo832q() {
        return m851n() ? this : new C12827j(this.f26104b, this.f26105c, this.f26106d.mo877d(), this.f26107e, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: r */
    public AbstractC12860g mo831r() {
        if (m851n()) {
            return this;
        }
        AbstractC12850d c = m861c();
        C12826i c12826i = (C12826i) this.f26106d;
        if (c12826i.mo895j()) {
            return c.mo901e();
        }
        C12826i c12826i2 = (C12826i) this.f26105c;
        C12826i c12826i3 = (C12826i) this.f26107e[0];
        int[] m645a = AbstractC12893e.m645a();
        int[] m645a2 = AbstractC12893e.m645a();
        int[] m645a3 = AbstractC12893e.m645a();
        C12825h.m1012d(c12826i.f26027b, m645a3);
        int[] m645a4 = AbstractC12893e.m645a();
        C12825h.m1012d(m645a3, m645a4);
        boolean mo896i = c12826i3.mo896i();
        int[] iArr = c12826i3.f26027b;
        if (!mo896i) {
            C12825h.m1012d(c12826i3.f26027b, m645a2);
            iArr = m645a2;
        }
        C12825h.m1011d(c12826i2.f26027b, iArr, m645a);
        C12825h.m1017a(c12826i2.f26027b, iArr, m645a2);
        C12825h.m1015b(m645a2, m645a, m645a2);
        C12825h.m1021a(AbstractC12893e.m632b(m645a2, m645a2, m645a2), m645a2);
        C12825h.m1015b(m645a3, c12826i2.f26027b, m645a3);
        C12825h.m1021a(AbstractC12891c.m680c(5, m645a3, 2, 0), m645a3);
        C12825h.m1021a(AbstractC12891c.m702a(5, m645a4, 3, 0, m645a), m645a);
        C12826i c12826i4 = new C12826i(m645a4);
        C12825h.m1012d(m645a2, c12826i4.f26027b);
        C12825h.m1011d(c12826i4.f26027b, m645a3, c12826i4.f26027b);
        C12825h.m1011d(c12826i4.f26027b, m645a3, c12826i4.f26027b);
        C12826i c12826i5 = new C12826i(m645a3);
        C12825h.m1011d(m645a3, c12826i4.f26027b, c12826i5.f26027b);
        C12825h.m1015b(c12826i5.f26027b, m645a2, c12826i5.f26027b);
        C12825h.m1011d(c12826i5.f26027b, m645a, c12826i5.f26027b);
        C12826i c12826i6 = new C12826i(m645a2);
        C12825h.m1010e(c12826i.f26027b, c12826i6.f26027b);
        if (!mo896i) {
            C12825h.m1015b(c12826i6.f26027b, c12826i3.f26027b, c12826i6.f26027b);
        }
        return new C12827j(c, c12826i4, c12826i5, new AbstractC12856e[]{c12826i6}, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: s */
    public AbstractC12860g mo830s() {
        return (m851n() || this.f26106d.mo895j()) ? this : mo831r().mo839b(this);
    }
}
