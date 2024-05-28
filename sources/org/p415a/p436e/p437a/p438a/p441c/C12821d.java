package org.p415a.p436e.p437a.p438a.p441c;

import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p437a.AbstractC12860g;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12892d;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.d */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12821d extends AbstractC12860g.AbstractC12862b {
    public C12821d(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
        this(abstractC12850d, abstractC12856e, abstractC12856e2, false);
    }

    public C12821d(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, boolean z) {
        super(abstractC12850d, abstractC12856e, abstractC12856e2);
        if ((abstractC12856e == null) != (abstractC12856e2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f26108f = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12821d(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr, boolean z) {
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
        C12798c c12798c = (C12798c) this.f26105c;
        C12798c c12798c2 = (C12798c) this.f26106d;
        C12798c c12798c3 = (C12798c) abstractC12860g.m858f();
        C12798c c12798c4 = (C12798c) abstractC12860g.mo844g();
        C12798c c12798c5 = (C12798c) this.f26107e[0];
        C12798c c12798c6 = (C12798c) abstractC12860g.mo842a(0);
        int[] m655c = AbstractC12892d.m655c();
        int[] m669a = AbstractC12892d.m669a();
        int[] m669a2 = AbstractC12892d.m669a();
        int[] m669a3 = AbstractC12892d.m669a();
        boolean mo896i = c12798c5.mo896i();
        if (mo896i) {
            iArr = c12798c3.f26003b;
            iArr2 = c12798c4.f26003b;
        } else {
            C12771b.m1170d(c12798c5.f26003b, m669a2);
            C12771b.m1173b(m669a2, c12798c3.f26003b, m669a);
            C12771b.m1173b(m669a2, c12798c5.f26003b, m669a2);
            C12771b.m1173b(m669a2, c12798c4.f26003b, m669a2);
            iArr = m669a;
            iArr2 = m669a2;
        }
        boolean mo896i2 = c12798c6.mo896i();
        if (mo896i2) {
            iArr3 = c12798c.f26003b;
            iArr4 = c12798c2.f26003b;
        } else {
            C12771b.m1170d(c12798c6.f26003b, m669a3);
            C12771b.m1173b(m669a3, c12798c.f26003b, m655c);
            C12771b.m1173b(m669a3, c12798c6.f26003b, m669a3);
            C12771b.m1173b(m669a3, c12798c2.f26003b, m669a3);
            iArr3 = m655c;
            iArr4 = m669a3;
        }
        int[] m669a4 = AbstractC12892d.m669a();
        C12771b.m1169d(iArr3, iArr, m669a4);
        C12771b.m1169d(iArr4, iArr2, m669a);
        if (AbstractC12892d.m659b(m669a4)) {
            return AbstractC12892d.m659b(m669a) ? mo831r() : c.mo901e();
        }
        C12771b.m1170d(m669a4, m669a2);
        int[] m669a5 = AbstractC12892d.m669a();
        C12771b.m1173b(m669a2, m669a4, m669a5);
        C12771b.m1173b(m669a2, iArr3, m669a2);
        C12771b.m1174b(m669a5, m669a5);
        AbstractC12892d.m652c(iArr4, m669a5, m655c);
        C12771b.m1181a(AbstractC12892d.m657b(m669a2, m669a2, m669a5), m669a5);
        C12798c c12798c7 = new C12798c(m669a3);
        C12771b.m1170d(m669a, c12798c7.f26003b);
        C12771b.m1169d(c12798c7.f26003b, m669a5, c12798c7.f26003b);
        C12798c c12798c8 = new C12798c(m669a5);
        C12771b.m1169d(m669a2, c12798c7.f26003b, c12798c8.f26003b);
        C12771b.m1171c(c12798c8.f26003b, m669a, m655c);
        C12771b.m1172c(m655c, c12798c8.f26003b);
        C12798c c12798c9 = new C12798c(m669a4);
        if (!mo896i) {
            C12771b.m1173b(c12798c9.f26003b, c12798c5.f26003b, c12798c9.f26003b);
        }
        if (!mo896i2) {
            C12771b.m1173b(c12798c9.f26003b, c12798c6.f26003b, c12798c9.f26003b);
        }
        return new C12821d(c, c12798c7, c12798c8, new AbstractC12856e[]{c12798c9}, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: d */
    public AbstractC12860g mo836d(AbstractC12860g abstractC12860g) {
        return this == abstractC12860g ? mo830s() : m851n() ? abstractC12860g : abstractC12860g.m851n() ? mo831r() : this.f26106d.mo895j() ? abstractC12860g : mo831r().mo839b(abstractC12860g);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: q */
    public AbstractC12860g mo832q() {
        return m851n() ? this : new C12821d(this.f26104b, this.f26105c, this.f26106d.mo877d(), this.f26107e, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: r */
    public AbstractC12860g mo831r() {
        if (m851n()) {
            return this;
        }
        AbstractC12850d c = m861c();
        C12798c c12798c = (C12798c) this.f26106d;
        if (c12798c.mo895j()) {
            return c.mo901e();
        }
        C12798c c12798c2 = (C12798c) this.f26105c;
        C12798c c12798c3 = (C12798c) this.f26107e[0];
        int[] m669a = AbstractC12892d.m669a();
        int[] m669a2 = AbstractC12892d.m669a();
        int[] m669a3 = AbstractC12892d.m669a();
        C12771b.m1170d(c12798c.f26003b, m669a3);
        int[] m669a4 = AbstractC12892d.m669a();
        C12771b.m1170d(m669a3, m669a4);
        boolean mo896i = c12798c3.mo896i();
        int[] iArr = c12798c3.f26003b;
        if (!mo896i) {
            C12771b.m1170d(c12798c3.f26003b, m669a2);
            iArr = m669a2;
        }
        C12771b.m1169d(c12798c2.f26003b, iArr, m669a);
        C12771b.m1176a(c12798c2.f26003b, iArr, m669a2);
        C12771b.m1173b(m669a2, m669a, m669a2);
        C12771b.m1181a(AbstractC12892d.m657b(m669a2, m669a2, m669a2), m669a2);
        C12771b.m1173b(m669a3, c12798c2.f26003b, m669a3);
        C12771b.m1181a(AbstractC12891c.m680c(4, m669a3, 2, 0), m669a3);
        C12771b.m1181a(AbstractC12891c.m702a(4, m669a4, 3, 0, m669a), m669a);
        C12798c c12798c4 = new C12798c(m669a4);
        C12771b.m1170d(m669a2, c12798c4.f26003b);
        C12771b.m1169d(c12798c4.f26003b, m669a3, c12798c4.f26003b);
        C12771b.m1169d(c12798c4.f26003b, m669a3, c12798c4.f26003b);
        C12798c c12798c5 = new C12798c(m669a3);
        C12771b.m1169d(m669a3, c12798c4.f26003b, c12798c5.f26003b);
        C12771b.m1173b(c12798c5.f26003b, m669a2, c12798c5.f26003b);
        C12771b.m1169d(c12798c5.f26003b, m669a, c12798c5.f26003b);
        C12798c c12798c6 = new C12798c(m669a2);
        C12771b.m1168e(c12798c.f26003b, c12798c6.f26003b);
        if (!mo896i) {
            C12771b.m1173b(c12798c6.f26003b, c12798c3.f26003b, c12798c6.f26003b);
        }
        return new C12821d(c, c12798c4, c12798c5, new AbstractC12856e[]{c12798c6}, this.f26108f);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12860g
    /* renamed from: s */
    public AbstractC12860g mo830s() {
        return (m851n() || this.f26106d.mo895j()) ? this : mo831r().mo839b(this);
    }
}
