package org.p415a.p418b.p423e;

import java.math.BigInteger;
import org.p415a.p418b.AbstractC12562n;
import org.p415a.p418b.AbstractC12570t;
import org.p415a.p418b.C12444bd;
import org.p415a.p418b.C12555g;
import org.p415a.p418b.C12560l;
import org.p415a.p418b.C12563o;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.e.h */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12551h extends AbstractC12562n implements InterfaceC12553j {

    /* renamed from: ap */
    private C12563o f25427ap;

    /* renamed from: aq */
    private AbstractC12570t f25428aq;

    public C12551h(int i, int i2) {
        this(i, i2, 0, 0);
    }

    public C12551h(int i, int i2, int i3, int i4) {
        this.f25427ap = f25473d;
        C12555g c12555g = new C12555g();
        c12555g.m1659a(new C12560l(i));
        if (i3 == 0) {
            if (i4 != 0) {
                throw new IllegalArgumentException("inconsistent k values");
            }
            c12555g.m1659a(f25475f);
            c12555g.m1659a(new C12560l(i2));
        } else if (i3 <= i2 || i4 <= i3) {
            throw new IllegalArgumentException("inconsistent k values");
        } else {
            c12555g.m1659a(f25476g);
            C12555g c12555g2 = new C12555g();
            c12555g2.m1659a(new C12560l(i2));
            c12555g2.m1659a(new C12560l(i3));
            c12555g2.m1659a(new C12560l(i4));
            c12555g.m1659a(new C12444bd(c12555g2));
        }
        this.f25428aq = new C12444bd(c12555g);
    }

    public C12551h(BigInteger bigInteger) {
        this.f25427ap = f25472c;
        this.f25428aq = new C12560l(bigInteger);
    }

    @Override // org.p415a.p418b.AbstractC12562n, org.p415a.p418b.InterfaceC12554f
    /* renamed from: h */
    public AbstractC12570t mo1617h() {
        C12555g c12555g = new C12555g();
        c12555g.m1659a(this.f25427ap);
        c12555g.m1659a(this.f25428aq);
        return new C12444bd(c12555g);
    }
}
