package org.p415a.p436e.p437a.p438a.p440b;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p444c.AbstractC12890b;
import org.p415a.p436e.p444c.AbstractC12896h;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.b.c */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12742c extends AbstractC12856e {

    /* renamed from: a */
    public static final BigInteger f25945a = C12740a.f25941i;

    /* renamed from: b */
    protected int[] f25946b;

    public C12742c() {
        this.f25946b = AbstractC12896h.m569a();
    }

    public C12742c(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f25945a) >= 0) {
            throw new IllegalArgumentException("x value invalid for SM2P256V1FieldElement");
        }
        this.f25946b = C12741b.m1282a(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C12742c(int[] iArr) {
        this.f25946b = iArr;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public BigInteger mo893a() {
        return AbstractC12896h.m545c(this.f25946b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo889a(AbstractC12856e abstractC12856e) {
        int[] m569a = AbstractC12896h.m569a();
        C12741b.m1278a(this.f25946b, ((C12742c) abstractC12856e).f25946b, m569a);
        return new C12742c(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public int mo886b() {
        return f25945a.bitLength();
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo883b(AbstractC12856e abstractC12856e) {
        int[] m569a = AbstractC12896h.m569a();
        C12741b.m1271d(this.f25946b, ((C12742c) abstractC12856e).f25946b, m569a);
        return new C12742c(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo881c() {
        int[] m569a = AbstractC12896h.m569a();
        C12741b.m1279a(this.f25946b, m569a);
        return new C12742c(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo878c(AbstractC12856e abstractC12856e) {
        int[] m569a = AbstractC12896h.m569a();
        C12741b.m1275b(this.f25946b, ((C12742c) abstractC12856e).f25946b, m569a);
        return new C12742c(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo877d() {
        int[] m569a = AbstractC12896h.m569a();
        C12741b.m1276b(this.f25946b, m569a);
        return new C12742c(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo875d(AbstractC12856e abstractC12856e) {
        int[] m569a = AbstractC12896h.m569a();
        AbstractC12890b.m713a(C12741b.f25943a, ((C12742c) abstractC12856e).f25946b, m569a);
        C12741b.m1275b(m569a, this.f25946b, m569a);
        return new C12742c(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: e */
    public AbstractC12856e mo874e() {
        int[] m569a = AbstractC12896h.m569a();
        C12741b.m1272d(this.f25946b, m569a);
        return new C12742c(m569a);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C12742c) {
            return AbstractC12896h.m549b(this.f25946b, ((C12742c) obj).f25946b);
        }
        return false;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: f */
    public AbstractC12856e mo871f() {
        int[] m569a = AbstractC12896h.m569a();
        AbstractC12890b.m713a(C12741b.f25943a, this.f25946b, m569a);
        return new C12742c(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: g */
    public AbstractC12856e mo870g() {
        int[] iArr = this.f25946b;
        if (AbstractC12896h.m552b(iArr) || AbstractC12896h.m563a(iArr)) {
            return this;
        }
        int[] m569a = AbstractC12896h.m569a();
        C12741b.m1272d(iArr, m569a);
        C12741b.m1275b(m569a, iArr, m569a);
        int[] m569a2 = AbstractC12896h.m569a();
        C12741b.m1280a(m569a, 2, m569a2);
        C12741b.m1275b(m569a2, m569a, m569a2);
        int[] m569a3 = AbstractC12896h.m569a();
        C12741b.m1280a(m569a2, 2, m569a3);
        C12741b.m1275b(m569a3, m569a, m569a3);
        C12741b.m1280a(m569a3, 6, m569a);
        C12741b.m1275b(m569a, m569a3, m569a);
        int[] m569a4 = AbstractC12896h.m569a();
        C12741b.m1280a(m569a, 12, m569a4);
        C12741b.m1275b(m569a4, m569a, m569a4);
        C12741b.m1280a(m569a4, 6, m569a);
        C12741b.m1275b(m569a, m569a3, m569a);
        C12741b.m1272d(m569a, m569a3);
        C12741b.m1275b(m569a3, iArr, m569a3);
        C12741b.m1280a(m569a3, 31, m569a4);
        C12741b.m1275b(m569a4, m569a3, m569a);
        C12741b.m1280a(m569a4, 32, m569a4);
        C12741b.m1275b(m569a4, m569a, m569a4);
        C12741b.m1280a(m569a4, 62, m569a4);
        C12741b.m1275b(m569a4, m569a, m569a4);
        C12741b.m1280a(m569a4, 4, m569a4);
        C12741b.m1275b(m569a4, m569a2, m569a4);
        C12741b.m1280a(m569a4, 32, m569a4);
        C12741b.m1275b(m569a4, iArr, m569a4);
        C12741b.m1280a(m569a4, 62, m569a4);
        C12741b.m1272d(m569a4, m569a2);
        if (AbstractC12896h.m549b(iArr, m569a2)) {
            return new C12742c(m569a4);
        }
        return null;
    }

    public int hashCode() {
        return f25945a.hashCode() ^ C12957a.m434a(this.f25946b, 0, 8);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: i */
    public boolean mo896i() {
        return AbstractC12896h.m563a(this.f25946b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: j */
    public boolean mo895j() {
        return AbstractC12896h.m552b(this.f25946b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: k */
    public boolean mo894k() {
        return AbstractC12896h.m562a(this.f25946b, 0) == 1;
    }
}
