package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p444c.AbstractC12890b;
import org.p415a.p436e.p444c.AbstractC12892d;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.c */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12798c extends AbstractC12856e {

    /* renamed from: a */
    public static final BigInteger f26002a = C12744a.f25947i;

    /* renamed from: b */
    protected int[] f26003b;

    public C12798c() {
        this.f26003b = AbstractC12892d.m669a();
    }

    public C12798c(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f26002a) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP128R1FieldElement");
        }
        this.f26003b = C12771b.m1180a(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C12798c(int[] iArr) {
        this.f26003b = iArr;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public BigInteger mo893a() {
        return AbstractC12892d.m654c(this.f26003b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo889a(AbstractC12856e abstractC12856e) {
        int[] m669a = AbstractC12892d.m669a();
        C12771b.m1176a(this.f26003b, ((C12798c) abstractC12856e).f26003b, m669a);
        return new C12798c(m669a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public int mo886b() {
        return f26002a.bitLength();
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo883b(AbstractC12856e abstractC12856e) {
        int[] m669a = AbstractC12892d.m669a();
        C12771b.m1169d(this.f26003b, ((C12798c) abstractC12856e).f26003b, m669a);
        return new C12798c(m669a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo881c() {
        int[] m669a = AbstractC12892d.m669a();
        C12771b.m1177a(this.f26003b, m669a);
        return new C12798c(m669a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo878c(AbstractC12856e abstractC12856e) {
        int[] m669a = AbstractC12892d.m669a();
        C12771b.m1173b(this.f26003b, ((C12798c) abstractC12856e).f26003b, m669a);
        return new C12798c(m669a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo877d() {
        int[] m669a = AbstractC12892d.m669a();
        C12771b.m1174b(this.f26003b, m669a);
        return new C12798c(m669a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo875d(AbstractC12856e abstractC12856e) {
        int[] m669a = AbstractC12892d.m669a();
        AbstractC12890b.m713a(C12771b.f25984a, ((C12798c) abstractC12856e).f26003b, m669a);
        C12771b.m1173b(m669a, this.f26003b, m669a);
        return new C12798c(m669a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: e */
    public AbstractC12856e mo874e() {
        int[] m669a = AbstractC12892d.m669a();
        C12771b.m1170d(this.f26003b, m669a);
        return new C12798c(m669a);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C12798c) {
            return AbstractC12892d.m665a(this.f26003b, ((C12798c) obj).f26003b);
        }
        return false;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: f */
    public AbstractC12856e mo871f() {
        int[] m669a = AbstractC12892d.m669a();
        AbstractC12890b.m713a(C12771b.f25984a, this.f26003b, m669a);
        return new C12798c(m669a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: g */
    public AbstractC12856e mo870g() {
        int[] iArr = this.f26003b;
        if (AbstractC12892d.m659b(iArr) || AbstractC12892d.m667a(iArr)) {
            return this;
        }
        int[] m669a = AbstractC12892d.m669a();
        C12771b.m1170d(iArr, m669a);
        C12771b.m1173b(m669a, iArr, m669a);
        int[] m669a2 = AbstractC12892d.m669a();
        C12771b.m1178a(m669a, 2, m669a2);
        C12771b.m1173b(m669a2, m669a, m669a2);
        int[] m669a3 = AbstractC12892d.m669a();
        C12771b.m1178a(m669a2, 4, m669a3);
        C12771b.m1173b(m669a3, m669a2, m669a3);
        C12771b.m1178a(m669a3, 2, m669a2);
        C12771b.m1173b(m669a2, m669a, m669a2);
        C12771b.m1178a(m669a2, 10, m669a);
        C12771b.m1173b(m669a, m669a2, m669a);
        C12771b.m1178a(m669a, 10, m669a3);
        C12771b.m1173b(m669a3, m669a2, m669a3);
        C12771b.m1170d(m669a3, m669a2);
        C12771b.m1173b(m669a2, iArr, m669a2);
        C12771b.m1178a(m669a2, 95, m669a2);
        C12771b.m1170d(m669a2, m669a3);
        if (AbstractC12892d.m665a(iArr, m669a3)) {
            return new C12798c(m669a2);
        }
        return null;
    }

    public int hashCode() {
        return f26002a.hashCode() ^ C12957a.m434a(this.f26003b, 0, 4);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: i */
    public boolean mo896i() {
        return AbstractC12892d.m667a(this.f26003b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: j */
    public boolean mo895j() {
        return AbstractC12892d.m659b(this.f26003b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: k */
    public boolean mo894k() {
        return AbstractC12892d.m666a(this.f26003b, 0) == 1;
    }
}
