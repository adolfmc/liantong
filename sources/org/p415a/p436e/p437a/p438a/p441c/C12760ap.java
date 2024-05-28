package org.p415a.p436e.p437a.p438a.p441c;

import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p437a.AbstractC12860g;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12898j;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.ap */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12760ap extends AbstractC12860g.AbstractC12862b {
    public C12760ap(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
        this(abstractC12850d, abstractC12856e, abstractC12856e2, false);
    }

    public C12760ap(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, boolean z) {
        super(abstractC12850d, abstractC12856e, abstractC12856e2);
        if ((abstractC12856e == null) != (abstractC12856e2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f26108f = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12760ap(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr, boolean z) {
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
        C12759ao c12759ao = (C12759ao) this.f26105c;
        C12759ao c12759ao2 = (C12759ao) this.f26106d;
        C12759ao c12759ao3 = (C12759ao) abstractC12860g.m858f();
        C12759ao c12759ao4 = (C12759ao) abstractC12860g.mo844g();
        C12759ao c12759ao5 = (C12759ao) this.f26107e[0];
        C12759ao c12759ao6 = (C12759ao) abstractC12860g.mo842a(0);
        int[] m712a = AbstractC12891c.m712a(24);
        int[] m712a2 = AbstractC12891c.m712a(24);
        int[] m712a3 = AbstractC12891c.m712a(12);
        int[] m712a4 = AbstractC12891c.m712a(12);
        boolean mo896i = c12759ao5.mo896i();
        if (mo896i) {
            iArr = c12759ao3.f25975b;
            iArr2 = c12759ao4.f25975b;
        } else {
            C12758an.m1213d(c12759ao5.f25975b, m712a3);
            C12758an.m1214c(m712a3, c12759ao3.f25975b, m712a2);
            C12758an.m1214c(m712a3, c12759ao5.f25975b, m712a3);
            C12758an.m1214c(m712a3, c12759ao4.f25975b, m712a3);
            iArr = m712a2;
            iArr2 = m712a3;
        }
        boolean mo896i2 = c12759ao6.mo896i();
        if (mo896i2) {
            iArr3 = c12759ao.f25975b;
            iArr4 = c12759ao2.f25975b;
        } else {
            C12758an.m1213d(c12759ao6.f25975b, m712a4);
            C12758an.m1214c(m712a4, c12759ao.f25975b, m712a);
            C12758an.m1214c(m712a4, c12759ao6.f25975b, m712a4);
            C12758an.m1214c(m712a4, c12759ao2.f25975b, m712a4);
            iArr3 = m712a;
            iArr4 = m712a4;
        }
        int[] m712a5 = AbstractC12891c.m712a(12);
        C12758an.m1212d(iArr3, iArr, m712a5);
        int[] m712a6 = AbstractC12891c.m712a(12);
        C12758an.m1212d(iArr4, iArr2, m712a6);
        if (AbstractC12891c.m673e(12, m712a5)) {
            return AbstractC12891c.m673e(12, m712a6) ? mo831r() : c.mo901e();
        }
        C12758an.m1213d(m712a5, m712a3);
        int[] m712a7 = AbstractC12891c.m712a(12);
        C12758an.m1214c(m712a3, m712a5, m712a7);
        C12758an.m1214c(m712a3, iArr3, m712a3);
        C12758an.m1217b(m712a7, m712a7);
        AbstractC12898j.m525a(iArr4, m712a7, m712a);
        C12758an.m1224a(AbstractC12891c.m684b(12, m712a3, m712a3, m712a7), m712a7);
        C12759ao c12759ao7 = new C12759ao(m712a4);
        C12758an.m1213d(m712a6, c12759ao7.f25975b);
        C12758an.m1212d(c12759ao7.f25975b, m712a7, c12759ao7.f25975b);
        C12759ao c12759ao8 = new C12759ao(m712a7);
        C12758an.m1212d(m712a3, c12759ao7.f25975b, c12759ao8.f25975b);
        AbstractC12898j.m525a(c12759ao8.f25975b, m712a6, m712a2);
        C12758an.m1216b(m712a, m712a2, m712a);
        C12758an.m1215c(m712a, c12759ao8.f25975b);
        C12759ao c12759ao9 = new C12759ao(m712a5);
        if (!mo896i) {
            C12758an.m1214c(c12759ao9.f25975b, c12759ao5.f25975b, c12759ao9.f25975b);
        }
        if (!mo896i2) {
            C12758an.m1214c(c12759ao9.f25975b, c12759ao6.f25975b, c12759ao9.f25975b);
        }
        return new C12760ap(c, c12759ao7, c12759ao8, new AbstractC12856e[]{c12759ao9}, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: d */
    public AbstractC12860g mo836d(AbstractC12860g abstractC12860g) {
        return this == abstractC12860g ? mo830s() : m851n() ? abstractC12860g : abstractC12860g.m851n() ? mo831r() : this.f26106d.mo895j() ? abstractC12860g : mo831r().mo839b(abstractC12860g);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: q */
    public AbstractC12860g mo832q() {
        return m851n() ? this : new C12760ap(this.f26104b, this.f26105c, this.f26106d.mo877d(), this.f26107e, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: r */
    public AbstractC12860g mo831r() {
        if (m851n()) {
            return this;
        }
        AbstractC12850d c = m861c();
        C12759ao c12759ao = (C12759ao) this.f26106d;
        if (c12759ao.mo895j()) {
            return c.mo901e();
        }
        C12759ao c12759ao2 = (C12759ao) this.f26105c;
        C12759ao c12759ao3 = (C12759ao) this.f26107e[0];
        int[] m712a = AbstractC12891c.m712a(12);
        int[] m712a2 = AbstractC12891c.m712a(12);
        int[] m712a3 = AbstractC12891c.m712a(12);
        C12758an.m1213d(c12759ao.f25975b, m712a3);
        int[] m712a4 = AbstractC12891c.m712a(12);
        C12758an.m1213d(m712a3, m712a4);
        boolean mo896i = c12759ao3.mo896i();
        int[] iArr = c12759ao3.f25975b;
        if (!mo896i) {
            C12758an.m1213d(c12759ao3.f25975b, m712a2);
            iArr = m712a2;
        }
        C12758an.m1212d(c12759ao2.f25975b, iArr, m712a);
        C12758an.m1219a(c12759ao2.f25975b, iArr, m712a2);
        C12758an.m1214c(m712a2, m712a, m712a2);
        C12758an.m1224a(AbstractC12891c.m684b(12, m712a2, m712a2, m712a2), m712a2);
        C12758an.m1214c(m712a3, c12759ao2.f25975b, m712a3);
        C12758an.m1224a(AbstractC12891c.m680c(12, m712a3, 2, 0), m712a3);
        C12758an.m1224a(AbstractC12891c.m702a(12, m712a4, 3, 0, m712a), m712a);
        C12759ao c12759ao4 = new C12759ao(m712a4);
        C12758an.m1213d(m712a2, c12759ao4.f25975b);
        C12758an.m1212d(c12759ao4.f25975b, m712a3, c12759ao4.f25975b);
        C12758an.m1212d(c12759ao4.f25975b, m712a3, c12759ao4.f25975b);
        C12759ao c12759ao5 = new C12759ao(m712a3);
        C12758an.m1212d(m712a3, c12759ao4.f25975b, c12759ao5.f25975b);
        C12758an.m1214c(c12759ao5.f25975b, m712a2, c12759ao5.f25975b);
        C12758an.m1212d(c12759ao5.f25975b, m712a, c12759ao5.f25975b);
        C12759ao c12759ao6 = new C12759ao(m712a2);
        C12758an.m1211e(c12759ao.f25975b, c12759ao6.f25975b);
        if (!mo896i) {
            C12758an.m1214c(c12759ao6.f25975b, c12759ao3.f25975b, c12759ao6.f25975b);
        }
        return new C12760ap(c, c12759ao4, c12759ao5, new AbstractC12856e[]{c12759ao6}, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: s */
    public AbstractC12860g mo830s() {
        return (m851n() || this.f26106d.mo895j()) ? this : mo831r().mo839b(this);
    }
}
