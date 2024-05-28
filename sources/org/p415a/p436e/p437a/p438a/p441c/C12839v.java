package org.p415a.p436e.p437a.p438a.p441c;

import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p437a.AbstractC12860g;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12894f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.v */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12839v extends AbstractC12860g.AbstractC12862b {
    public C12839v(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
        this(abstractC12850d, abstractC12856e, abstractC12856e2, false);
    }

    public C12839v(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, boolean z) {
        super(abstractC12850d, abstractC12856e, abstractC12856e2);
        if ((abstractC12856e == null) != (abstractC12856e2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f26108f = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12839v(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr, boolean z) {
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
        C12838u c12838u = (C12838u) this.f26105c;
        C12838u c12838u2 = (C12838u) this.f26106d;
        C12838u c12838u3 = (C12838u) abstractC12860g.m858f();
        C12838u c12838u4 = (C12838u) abstractC12860g.mo844g();
        C12838u c12838u5 = (C12838u) this.f26107e[0];
        C12838u c12838u6 = (C12838u) abstractC12860g.mo842a(0);
        int[] m602c = AbstractC12894f.m602c();
        int[] m624a = AbstractC12894f.m624a();
        int[] m624a2 = AbstractC12894f.m624a();
        int[] m624a3 = AbstractC12894f.m624a();
        boolean mo896i = c12838u5.mo896i();
        if (mo896i) {
            iArr = c12838u3.f26048b;
            iArr2 = c12838u4.f26048b;
        } else {
            C12837t.m974d(c12838u5.f26048b, m624a2);
            C12837t.m977b(m624a2, c12838u3.f26048b, m624a);
            C12837t.m977b(m624a2, c12838u5.f26048b, m624a2);
            C12837t.m977b(m624a2, c12838u4.f26048b, m624a2);
            iArr = m624a;
            iArr2 = m624a2;
        }
        boolean mo896i2 = c12838u6.mo896i();
        if (mo896i2) {
            iArr3 = c12838u.f26048b;
            iArr4 = c12838u2.f26048b;
        } else {
            C12837t.m974d(c12838u6.f26048b, m624a3);
            C12837t.m977b(m624a3, c12838u.f26048b, m602c);
            C12837t.m977b(m624a3, c12838u6.f26048b, m624a3);
            C12837t.m977b(m624a3, c12838u2.f26048b, m624a3);
            iArr3 = m602c;
            iArr4 = m624a3;
        }
        int[] m624a4 = AbstractC12894f.m624a();
        C12837t.m973d(iArr3, iArr, m624a4);
        C12837t.m973d(iArr4, iArr2, m624a);
        if (AbstractC12894f.m608b(m624a4)) {
            return AbstractC12894f.m608b(m624a) ? mo831r() : c.mo901e();
        }
        C12837t.m974d(m624a4, m624a2);
        int[] m624a5 = AbstractC12894f.m624a();
        C12837t.m977b(m624a2, m624a4, m624a5);
        C12837t.m977b(m624a2, iArr3, m624a2);
        C12837t.m978b(m624a5, m624a5);
        AbstractC12894f.m597c(iArr4, m624a5, m602c);
        C12837t.m985a(AbstractC12894f.m604b(m624a2, m624a2, m624a5), m624a5);
        C12838u c12838u7 = new C12838u(m624a3);
        C12837t.m974d(m624a, c12838u7.f26048b);
        C12837t.m973d(c12838u7.f26048b, m624a5, c12838u7.f26048b);
        C12838u c12838u8 = new C12838u(m624a5);
        C12837t.m973d(m624a2, c12838u7.f26048b, c12838u8.f26048b);
        C12837t.m975c(c12838u8.f26048b, m624a, m602c);
        C12837t.m976c(m602c, c12838u8.f26048b);
        C12838u c12838u9 = new C12838u(m624a4);
        if (!mo896i) {
            C12837t.m977b(c12838u9.f26048b, c12838u5.f26048b, c12838u9.f26048b);
        }
        if (!mo896i2) {
            C12837t.m977b(c12838u9.f26048b, c12838u6.f26048b, c12838u9.f26048b);
        }
        return new C12839v(c, c12838u7, c12838u8, new AbstractC12856e[]{c12838u9}, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: d */
    public AbstractC12860g mo836d(AbstractC12860g abstractC12860g) {
        return this == abstractC12860g ? mo830s() : m851n() ? abstractC12860g : abstractC12860g.m851n() ? mo831r() : this.f26106d.mo895j() ? abstractC12860g : mo831r().mo839b(abstractC12860g);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: q */
    public AbstractC12860g mo832q() {
        return m851n() ? this : new C12839v(this.f26104b, this.f26105c, this.f26106d.mo877d(), this.f26107e, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: r */
    public AbstractC12860g mo831r() {
        if (m851n()) {
            return this;
        }
        AbstractC12850d c = m861c();
        C12838u c12838u = (C12838u) this.f26106d;
        if (c12838u.mo895j()) {
            return c.mo901e();
        }
        C12838u c12838u2 = (C12838u) this.f26105c;
        C12838u c12838u3 = (C12838u) this.f26107e[0];
        int[] m624a = AbstractC12894f.m624a();
        int[] m624a2 = AbstractC12894f.m624a();
        int[] m624a3 = AbstractC12894f.m624a();
        C12837t.m974d(c12838u.f26048b, m624a3);
        int[] m624a4 = AbstractC12894f.m624a();
        C12837t.m974d(m624a3, m624a4);
        boolean mo896i = c12838u3.mo896i();
        int[] iArr = c12838u3.f26048b;
        if (!mo896i) {
            C12837t.m974d(c12838u3.f26048b, m624a2);
            iArr = m624a2;
        }
        C12837t.m973d(c12838u2.f26048b, iArr, m624a);
        C12837t.m980a(c12838u2.f26048b, iArr, m624a2);
        C12837t.m977b(m624a2, m624a, m624a2);
        C12837t.m985a(AbstractC12894f.m604b(m624a2, m624a2, m624a2), m624a2);
        C12837t.m977b(m624a3, c12838u2.f26048b, m624a3);
        C12837t.m985a(AbstractC12891c.m680c(6, m624a3, 2, 0), m624a3);
        C12837t.m985a(AbstractC12891c.m702a(6, m624a4, 3, 0, m624a), m624a);
        C12838u c12838u4 = new C12838u(m624a4);
        C12837t.m974d(m624a2, c12838u4.f26048b);
        C12837t.m973d(c12838u4.f26048b, m624a3, c12838u4.f26048b);
        C12837t.m973d(c12838u4.f26048b, m624a3, c12838u4.f26048b);
        C12838u c12838u5 = new C12838u(m624a3);
        C12837t.m973d(m624a3, c12838u4.f26048b, c12838u5.f26048b);
        C12837t.m977b(c12838u5.f26048b, m624a2, c12838u5.f26048b);
        C12837t.m973d(c12838u5.f26048b, m624a, c12838u5.f26048b);
        C12838u c12838u6 = new C12838u(m624a2);
        C12837t.m972e(c12838u.f26048b, c12838u6.f26048b);
        if (!mo896i) {
            C12837t.m977b(c12838u6.f26048b, c12838u3.f26048b, c12838u6.f26048b);
        }
        return new C12839v(c, c12838u4, c12838u5, new AbstractC12856e[]{c12838u6}, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: s */
    public AbstractC12860g mo830s() {
        return (m851n() || this.f26106d.mo895j()) ? this : mo831r().mo839b(this);
    }
}
