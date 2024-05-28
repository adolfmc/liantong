package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p444c.AbstractC12890b;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.ao */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12759ao extends AbstractC12856e {

    /* renamed from: a */
    public static final BigInteger f25974a = C12757am.f25969i;

    /* renamed from: b */
    protected int[] f25975b;

    public C12759ao() {
        this.f25975b = AbstractC12891c.m712a(12);
    }

    public C12759ao(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f25974a) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP384R1FieldElement");
        }
        this.f25975b = C12758an.m1223a(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C12759ao(int[] iArr) {
        this.f25975b = iArr;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public BigInteger mo893a() {
        return AbstractC12891c.m671f(12, this.f25975b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo889a(AbstractC12856e abstractC12856e) {
        int[] m712a = AbstractC12891c.m712a(12);
        C12758an.m1219a(this.f25975b, ((C12759ao) abstractC12856e).f25975b, m712a);
        return new C12759ao(m712a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public int mo886b() {
        return f25974a.bitLength();
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo883b(AbstractC12856e abstractC12856e) {
        int[] m712a = AbstractC12891c.m712a(12);
        C12758an.m1212d(this.f25975b, ((C12759ao) abstractC12856e).f25975b, m712a);
        return new C12759ao(m712a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo881c() {
        int[] m712a = AbstractC12891c.m712a(12);
        C12758an.m1220a(this.f25975b, m712a);
        return new C12759ao(m712a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo878c(AbstractC12856e abstractC12856e) {
        int[] m712a = AbstractC12891c.m712a(12);
        C12758an.m1214c(this.f25975b, ((C12759ao) abstractC12856e).f25975b, m712a);
        return new C12759ao(m712a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo877d() {
        int[] m712a = AbstractC12891c.m712a(12);
        C12758an.m1217b(this.f25975b, m712a);
        return new C12759ao(m712a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo875d(AbstractC12856e abstractC12856e) {
        int[] m712a = AbstractC12891c.m712a(12);
        AbstractC12890b.m713a(C12758an.f25971a, ((C12759ao) abstractC12856e).f25975b, m712a);
        C12758an.m1214c(m712a, this.f25975b, m712a);
        return new C12759ao(m712a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: e */
    public AbstractC12856e mo874e() {
        int[] m712a = AbstractC12891c.m712a(12);
        C12758an.m1213d(this.f25975b, m712a);
        return new C12759ao(m712a);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C12759ao) {
            return AbstractC12891c.m685b(12, this.f25975b, ((C12759ao) obj).f25975b);
        }
        return false;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: f */
    public AbstractC12856e mo871f() {
        int[] m712a = AbstractC12891c.m712a(12);
        AbstractC12890b.m713a(C12758an.f25971a, this.f25975b, m712a);
        return new C12759ao(m712a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: g */
    public AbstractC12856e mo870g() {
        int[] iArr = this.f25975b;
        if (AbstractC12891c.m673e(12, iArr) || AbstractC12891c.m676d(12, iArr)) {
            return this;
        }
        int[] m712a = AbstractC12891c.m712a(12);
        int[] m712a2 = AbstractC12891c.m712a(12);
        int[] m712a3 = AbstractC12891c.m712a(12);
        int[] m712a4 = AbstractC12891c.m712a(12);
        C12758an.m1213d(iArr, m712a);
        C12758an.m1214c(m712a, iArr, m712a);
        C12758an.m1221a(m712a, 2, m712a2);
        C12758an.m1214c(m712a2, m712a, m712a2);
        C12758an.m1213d(m712a2, m712a2);
        C12758an.m1214c(m712a2, iArr, m712a2);
        C12758an.m1221a(m712a2, 5, m712a3);
        C12758an.m1214c(m712a3, m712a2, m712a3);
        C12758an.m1221a(m712a3, 5, m712a4);
        C12758an.m1214c(m712a4, m712a2, m712a4);
        C12758an.m1221a(m712a4, 15, m712a2);
        C12758an.m1214c(m712a2, m712a4, m712a2);
        C12758an.m1221a(m712a2, 2, m712a3);
        C12758an.m1214c(m712a, m712a3, m712a);
        C12758an.m1221a(m712a3, 28, m712a3);
        C12758an.m1214c(m712a2, m712a3, m712a2);
        C12758an.m1221a(m712a2, 60, m712a3);
        C12758an.m1214c(m712a3, m712a2, m712a3);
        C12758an.m1221a(m712a3, 120, m712a2);
        C12758an.m1214c(m712a2, m712a3, m712a2);
        C12758an.m1221a(m712a2, 15, m712a2);
        C12758an.m1214c(m712a2, m712a4, m712a2);
        C12758an.m1221a(m712a2, 33, m712a2);
        C12758an.m1214c(m712a2, m712a, m712a2);
        C12758an.m1221a(m712a2, 64, m712a2);
        C12758an.m1214c(m712a2, iArr, m712a2);
        C12758an.m1221a(m712a2, 30, m712a);
        C12758an.m1213d(m712a, m712a2);
        if (AbstractC12891c.m685b(12, iArr, m712a2)) {
            return new C12759ao(m712a);
        }
        return null;
    }

    public int hashCode() {
        return f25974a.hashCode() ^ C12957a.m434a(this.f25975b, 0, 12);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: i */
    public boolean mo896i() {
        return AbstractC12891c.m676d(12, this.f25975b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: j */
    public boolean mo895j() {
        return AbstractC12891c.m673e(12, this.f25975b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: k */
    public boolean mo894k() {
        return AbstractC12891c.m692a(this.f25975b, 0) == 1;
    }
}
