package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p444c.AbstractC12890b;
import org.p415a.p436e.p444c.AbstractC12893e;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.m */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12830m extends AbstractC12856e {

    /* renamed from: a */
    public static final BigInteger f26033a = C12828k.f26028i;

    /* renamed from: b */
    protected int[] f26034b;

    public C12830m() {
        this.f26034b = AbstractC12893e.m645a();
    }

    public C12830m(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f26033a) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP160R2FieldElement");
        }
        this.f26034b = C12829l.m1008a(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C12830m(int[] iArr) {
        this.f26034b = iArr;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public BigInteger mo893a() {
        return AbstractC12893e.m631c(this.f26034b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo889a(AbstractC12856e abstractC12856e) {
        int[] m645a = AbstractC12893e.m645a();
        C12829l.m1005a(this.f26034b, ((C12830m) abstractC12856e).f26034b, m645a);
        return new C12830m(m645a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public int mo886b() {
        return f26033a.bitLength();
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo883b(AbstractC12856e abstractC12856e) {
        int[] m645a = AbstractC12893e.m645a();
        C12829l.m999d(this.f26034b, ((C12830m) abstractC12856e).f26034b, m645a);
        return new C12830m(m645a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo881c() {
        int[] m645a = AbstractC12893e.m645a();
        C12829l.m1006a(this.f26034b, m645a);
        return new C12830m(m645a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo878c(AbstractC12856e abstractC12856e) {
        int[] m645a = AbstractC12893e.m645a();
        C12829l.m1003b(this.f26034b, ((C12830m) abstractC12856e).f26034b, m645a);
        return new C12830m(m645a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo877d() {
        int[] m645a = AbstractC12893e.m645a();
        C12829l.m1004b(this.f26034b, m645a);
        return new C12830m(m645a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo875d(AbstractC12856e abstractC12856e) {
        int[] m645a = AbstractC12893e.m645a();
        AbstractC12890b.m713a(C12829l.f26030a, ((C12830m) abstractC12856e).f26034b, m645a);
        C12829l.m1003b(m645a, this.f26034b, m645a);
        return new C12830m(m645a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: e */
    public AbstractC12856e mo874e() {
        int[] m645a = AbstractC12893e.m645a();
        C12829l.m1000d(this.f26034b, m645a);
        return new C12830m(m645a);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C12830m) {
            return AbstractC12893e.m638a(this.f26034b, ((C12830m) obj).f26034b);
        }
        return false;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: f */
    public AbstractC12856e mo871f() {
        int[] m645a = AbstractC12893e.m645a();
        AbstractC12890b.m713a(C12829l.f26030a, this.f26034b, m645a);
        return new C12830m(m645a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: g */
    public AbstractC12856e mo870g() {
        int[] iArr = this.f26034b;
        if (AbstractC12893e.m634b(iArr) || AbstractC12893e.m640a(iArr)) {
            return this;
        }
        int[] m645a = AbstractC12893e.m645a();
        C12829l.m1000d(iArr, m645a);
        C12829l.m1003b(m645a, iArr, m645a);
        int[] m645a2 = AbstractC12893e.m645a();
        C12829l.m1000d(m645a, m645a2);
        C12829l.m1003b(m645a2, iArr, m645a2);
        int[] m645a3 = AbstractC12893e.m645a();
        C12829l.m1000d(m645a2, m645a3);
        C12829l.m1003b(m645a3, iArr, m645a3);
        int[] m645a4 = AbstractC12893e.m645a();
        C12829l.m1007a(m645a3, 3, m645a4);
        C12829l.m1003b(m645a4, m645a2, m645a4);
        C12829l.m1007a(m645a4, 7, m645a3);
        C12829l.m1003b(m645a3, m645a4, m645a3);
        C12829l.m1007a(m645a3, 3, m645a4);
        C12829l.m1003b(m645a4, m645a2, m645a4);
        int[] m645a5 = AbstractC12893e.m645a();
        C12829l.m1007a(m645a4, 14, m645a5);
        C12829l.m1003b(m645a5, m645a3, m645a5);
        C12829l.m1007a(m645a5, 31, m645a3);
        C12829l.m1003b(m645a3, m645a5, m645a3);
        C12829l.m1007a(m645a3, 62, m645a5);
        C12829l.m1003b(m645a5, m645a3, m645a5);
        C12829l.m1007a(m645a5, 3, m645a3);
        C12829l.m1003b(m645a3, m645a2, m645a3);
        C12829l.m1007a(m645a3, 18, m645a3);
        C12829l.m1003b(m645a3, m645a4, m645a3);
        C12829l.m1007a(m645a3, 2, m645a3);
        C12829l.m1003b(m645a3, iArr, m645a3);
        C12829l.m1007a(m645a3, 3, m645a3);
        C12829l.m1003b(m645a3, m645a, m645a3);
        C12829l.m1007a(m645a3, 6, m645a3);
        C12829l.m1003b(m645a3, m645a2, m645a3);
        C12829l.m1007a(m645a3, 2, m645a3);
        C12829l.m1003b(m645a3, iArr, m645a3);
        C12829l.m1000d(m645a3, m645a);
        if (AbstractC12893e.m638a(iArr, m645a)) {
            return new C12830m(m645a3);
        }
        return null;
    }

    public int hashCode() {
        return f26033a.hashCode() ^ C12957a.m434a(this.f26034b, 0, 5);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: i */
    public boolean mo896i() {
        return AbstractC12893e.m640a(this.f26034b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: j */
    public boolean mo895j() {
        return AbstractC12893e.m634b(this.f26034b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: k */
    public boolean mo894k() {
        return AbstractC12893e.m639a(this.f26034b, 0) == 1;
    }
}
