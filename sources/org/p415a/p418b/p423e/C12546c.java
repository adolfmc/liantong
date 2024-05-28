package org.p415a.p418b.p423e;

import org.p415a.p418b.AbstractC12562n;
import org.p415a.p418b.C12563o;
import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p436e.p437a.C12844b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.e.c */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12546c extends AbstractC12562n implements InterfaceC12553j {

    /* renamed from: ap */
    private AbstractC12850d f25411ap;

    /* renamed from: aq */
    private byte[] f25412aq;

    /* renamed from: ar */
    private C12563o f25413ar = null;

    public C12546c(AbstractC12850d abstractC12850d, byte[] bArr) {
        this.f25411ap = abstractC12850d;
        this.f25412aq = bArr;
        m1670a();
    }

    /* renamed from: a */
    private void m1670a() {
        C12563o c12563o;
        if (C12844b.m951b(this.f25411ap)) {
            c12563o = f25472c;
        } else if (!C12844b.m959a(this.f25411ap)) {
            throw new IllegalArgumentException("This type of ECCurve is not implemented");
        } else {
            c12563o = f25473d;
        }
        this.f25413ar = c12563o;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0060  */
    @Override // org.p415a.p418b.AbstractC12562n, org.p415a.p418b.InterfaceC12554f
    /* renamed from: h */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.p415a.p418b.AbstractC12570t mo1617h() {
        /*
            r3 = this;
            org.a.b.g r0 = new org.a.b.g
            r0.<init>()
            org.a.b.o r1 = r3.f25413ar
            org.a.b.o r2 = org.p415a.p418b.p423e.C12546c.f25472c
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L34
            org.a.b.e.g r1 = new org.a.b.e.g
            org.a.e.a.d r2 = r3.f25411ap
            org.a.e.a.e r2 = r2.m923g()
            r1.<init>(r2)
            org.a.b.t r1 = r1.mo1617h()
            r0.m1659a(r1)
            org.a.b.e.g r1 = new org.a.b.e.g
            org.a.e.a.d r2 = r3.f25411ap
            org.a.e.a.e r2 = r2.m922h()
            r1.<init>(r2)
        L2c:
            org.a.b.t r1 = r1.mo1617h()
            r0.m1659a(r1)
            goto L5c
        L34:
            org.a.b.o r1 = r3.f25413ar
            org.a.b.o r2 = org.p415a.p418b.p423e.C12546c.f25473d
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L5c
            org.a.b.e.g r1 = new org.a.b.e.g
            org.a.e.a.d r2 = r3.f25411ap
            org.a.e.a.e r2 = r2.m923g()
            r1.<init>(r2)
            org.a.b.t r1 = r1.mo1617h()
            r0.m1659a(r1)
            org.a.b.e.g r1 = new org.a.b.e.g
            org.a.e.a.d r2 = r3.f25411ap
            org.a.e.a.e r2 = r2.m922h()
            r1.<init>(r2)
            goto L2c
        L5c:
            byte[] r1 = r3.f25412aq
            if (r1 == 0) goto L68
            org.a.b.aq r2 = new org.a.b.aq
            r2.<init>(r1)
            r0.m1659a(r2)
        L68:
            org.a.b.bd r1 = new org.a.b.bd
            r1.<init>(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.p415a.p418b.p423e.C12546c.mo1617h():org.a.b.t");
    }
}
