package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p444c.AbstractC12890b;
import org.p415a.p436e.p444c.AbstractC12896h;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.ak */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12755ak extends AbstractC12856e {

    /* renamed from: a */
    public static final BigInteger f25967a = C12753ai.f25963i;

    /* renamed from: b */
    protected int[] f25968b;

    public C12755ak() {
        this.f25968b = AbstractC12896h.m569a();
    }

    public C12755ak(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f25967a) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP256R1FieldElement");
        }
        this.f25968b = C12754aj.m1237a(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C12755ak(int[] iArr) {
        this.f25968b = iArr;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public BigInteger mo893a() {
        return AbstractC12896h.m545c(this.f25968b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo889a(AbstractC12856e abstractC12856e) {
        int[] m569a = AbstractC12896h.m569a();
        C12754aj.m1233a(this.f25968b, ((C12755ak) abstractC12856e).f25968b, m569a);
        return new C12755ak(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public int mo886b() {
        return f25967a.bitLength();
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo883b(AbstractC12856e abstractC12856e) {
        int[] m569a = AbstractC12896h.m569a();
        C12754aj.m1226d(this.f25968b, ((C12755ak) abstractC12856e).f25968b, m569a);
        return new C12755ak(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo881c() {
        int[] m569a = AbstractC12896h.m569a();
        C12754aj.m1234a(this.f25968b, m569a);
        return new C12755ak(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo878c(AbstractC12856e abstractC12856e) {
        int[] m569a = AbstractC12896h.m569a();
        C12754aj.m1230b(this.f25968b, ((C12755ak) abstractC12856e).f25968b, m569a);
        return new C12755ak(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo877d() {
        int[] m569a = AbstractC12896h.m569a();
        C12754aj.m1231b(this.f25968b, m569a);
        return new C12755ak(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo875d(AbstractC12856e abstractC12856e) {
        int[] m569a = AbstractC12896h.m569a();
        AbstractC12890b.m713a(C12754aj.f25965a, ((C12755ak) abstractC12856e).f25968b, m569a);
        C12754aj.m1230b(m569a, this.f25968b, m569a);
        return new C12755ak(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: e */
    public AbstractC12856e mo874e() {
        int[] m569a = AbstractC12896h.m569a();
        C12754aj.m1227d(this.f25968b, m569a);
        return new C12755ak(m569a);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C12755ak) {
            return AbstractC12896h.m549b(this.f25968b, ((C12755ak) obj).f25968b);
        }
        return false;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: f */
    public AbstractC12856e mo871f() {
        int[] m569a = AbstractC12896h.m569a();
        AbstractC12890b.m713a(C12754aj.f25965a, this.f25968b, m569a);
        return new C12755ak(m569a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: g */
    public AbstractC12856e mo870g() {
        int[] iArr = this.f25968b;
        if (AbstractC12896h.m552b(iArr) || AbstractC12896h.m563a(iArr)) {
            return this;
        }
        int[] m569a = AbstractC12896h.m569a();
        int[] m569a2 = AbstractC12896h.m569a();
        C12754aj.m1227d(iArr, m569a);
        C12754aj.m1230b(m569a, iArr, m569a);
        C12754aj.m1235a(m569a, 2, m569a2);
        C12754aj.m1230b(m569a2, m569a, m569a2);
        C12754aj.m1235a(m569a2, 4, m569a);
        C12754aj.m1230b(m569a, m569a2, m569a);
        C12754aj.m1235a(m569a, 8, m569a2);
        C12754aj.m1230b(m569a2, m569a, m569a2);
        C12754aj.m1235a(m569a2, 16, m569a);
        C12754aj.m1230b(m569a, m569a2, m569a);
        C12754aj.m1235a(m569a, 32, m569a);
        C12754aj.m1230b(m569a, iArr, m569a);
        C12754aj.m1235a(m569a, 96, m569a);
        C12754aj.m1230b(m569a, iArr, m569a);
        C12754aj.m1235a(m569a, 94, m569a);
        C12754aj.m1227d(m569a, m569a2);
        if (AbstractC12896h.m549b(iArr, m569a2)) {
            return new C12755ak(m569a);
        }
        return null;
    }

    public int hashCode() {
        return f25967a.hashCode() ^ C12957a.m434a(this.f25968b, 0, 8);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: i */
    public boolean mo896i() {
        return AbstractC12896h.m563a(this.f25968b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: j */
    public boolean mo895j() {
        return AbstractC12896h.m552b(this.f25968b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: k */
    public boolean mo894k() {
        return AbstractC12896h.m562a(this.f25968b, 0) == 1;
    }
}
