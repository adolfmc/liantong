package org.p415a.p436e.p437a.p438a.p439a;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p444c.AbstractC12890b;
import org.p415a.p436e.p444c.AbstractC12896h;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.a.c */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12738c extends AbstractC12856e {

    /* renamed from: a */
    public static final BigInteger f25938a = C12736a.f25934i;

    /* renamed from: i */
    private static final int[] f25939i = {1242472624, -991028441, -1389370248, 792926214, 1039914919, 726466713, 1338105611, 730014848};

    /* renamed from: b */
    protected int[] f25940b;

    public C12738c() {
        this.f25940b = AbstractC12896h.m569a();
    }

    public C12738c(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f25938a) >= 0) {
            throw new IllegalArgumentException("x value invalid for Curve25519FieldElement");
        }
        this.f25940b = C12737b.m1300a(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C12738c(int[] iArr) {
        this.f25940b = iArr;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public BigInteger mo893a() {
        return AbstractC12896h.m545c(this.f25940b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo889a(AbstractC12856e abstractC12856e) {
        int[] m569a = AbstractC12896h.m569a();
        C12737b.m1296a(this.f25940b, ((C12738c) abstractC12856e).f25940b, m569a);
        return new C12738c(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public int mo886b() {
        return f25938a.bitLength();
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo883b(AbstractC12856e abstractC12856e) {
        int[] m569a = AbstractC12896h.m569a();
        C12737b.m1288d(this.f25940b, ((C12738c) abstractC12856e).f25940b, m569a);
        return new C12738c(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo881c() {
        int[] m569a = AbstractC12896h.m569a();
        C12737b.m1297a(this.f25940b, m569a);
        return new C12738c(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo878c(AbstractC12856e abstractC12856e) {
        int[] m569a = AbstractC12896h.m569a();
        C12737b.m1293b(this.f25940b, ((C12738c) abstractC12856e).f25940b, m569a);
        return new C12738c(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo877d() {
        int[] m569a = AbstractC12896h.m569a();
        C12737b.m1294b(this.f25940b, m569a);
        return new C12738c(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo875d(AbstractC12856e abstractC12856e) {
        int[] m569a = AbstractC12896h.m569a();
        AbstractC12890b.m713a(C12737b.f25936a, ((C12738c) abstractC12856e).f25940b, m569a);
        C12737b.m1293b(m569a, this.f25940b, m569a);
        return new C12738c(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: e */
    public AbstractC12856e mo874e() {
        int[] m569a = AbstractC12896h.m569a();
        C12737b.m1289d(this.f25940b, m569a);
        return new C12738c(m569a);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C12738c) {
            return AbstractC12896h.m549b(this.f25940b, ((C12738c) obj).f25940b);
        }
        return false;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: f */
    public AbstractC12856e mo871f() {
        int[] m569a = AbstractC12896h.m569a();
        AbstractC12890b.m713a(C12737b.f25936a, this.f25940b, m569a);
        return new C12738c(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: g */
    public AbstractC12856e mo870g() {
        int[] iArr = this.f25940b;
        if (AbstractC12896h.m552b(iArr) || AbstractC12896h.m563a(iArr)) {
            return this;
        }
        int[] m569a = AbstractC12896h.m569a();
        C12737b.m1289d(iArr, m569a);
        C12737b.m1293b(m569a, iArr, m569a);
        C12737b.m1289d(m569a, m569a);
        C12737b.m1293b(m569a, iArr, m569a);
        int[] m569a2 = AbstractC12896h.m569a();
        C12737b.m1289d(m569a, m569a2);
        C12737b.m1293b(m569a2, iArr, m569a2);
        int[] m569a3 = AbstractC12896h.m569a();
        C12737b.m1298a(m569a2, 3, m569a3);
        C12737b.m1293b(m569a3, m569a, m569a3);
        C12737b.m1298a(m569a3, 4, m569a);
        C12737b.m1293b(m569a, m569a2, m569a);
        C12737b.m1298a(m569a, 4, m569a3);
        C12737b.m1293b(m569a3, m569a2, m569a3);
        C12737b.m1298a(m569a3, 15, m569a2);
        C12737b.m1293b(m569a2, m569a3, m569a2);
        C12737b.m1298a(m569a2, 30, m569a3);
        C12737b.m1293b(m569a3, m569a2, m569a3);
        C12737b.m1298a(m569a3, 60, m569a2);
        C12737b.m1293b(m569a2, m569a3, m569a2);
        C12737b.m1298a(m569a2, 11, m569a3);
        C12737b.m1293b(m569a3, m569a, m569a3);
        C12737b.m1298a(m569a3, 120, m569a);
        C12737b.m1293b(m569a, m569a2, m569a);
        C12737b.m1289d(m569a, m569a);
        C12737b.m1289d(m569a, m569a2);
        if (AbstractC12896h.m549b(iArr, m569a2)) {
            return new C12738c(m569a);
        }
        C12737b.m1293b(m569a, f25939i, m569a);
        C12737b.m1289d(m569a, m569a2);
        if (AbstractC12896h.m549b(iArr, m569a2)) {
            return new C12738c(m569a);
        }
        return null;
    }

    public int hashCode() {
        return f25938a.hashCode() ^ C12957a.m434a(this.f25940b, 0, 8);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: i */
    public boolean mo896i() {
        return AbstractC12896h.m563a(this.f25940b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: j */
    public boolean mo895j() {
        return AbstractC12896h.m552b(this.f25940b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: k */
    public boolean mo894k() {
        return AbstractC12896h.m562a(this.f25940b, 0) == 1;
    }
}
