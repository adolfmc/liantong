package org.p415a.p436e.p437a;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.p */
/* loaded from: E:\11617560_dexfile_execute.dex */
class C12873p {

    /* renamed from: a */
    private final BigInteger f26123a;

    /* renamed from: b */
    private final int f26124b;

    public C12873p(BigInteger bigInteger, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("scale may not be negative");
        }
        this.f26123a = bigInteger;
        this.f26124b = i;
    }

    /* renamed from: c */
    private void m769c(C12873p c12873p) {
        if (this.f26124b != c12873p.f26124b) {
            throw new IllegalArgumentException("Only SimpleBigDecimal of same scale allowed in arithmetic operations");
        }
    }

    /* renamed from: a */
    public C12873p m777a() {
        return new C12873p(this.f26123a.negate(), this.f26124b);
    }

    /* renamed from: a */
    public C12873p m776a(int i) {
        if (i >= 0) {
            int i2 = this.f26124b;
            return i == i2 ? this : new C12873p(this.f26123a.shiftLeft(i - i2), i);
        }
        throw new IllegalArgumentException("scale may not be negative");
    }

    /* renamed from: a */
    public C12873p m775a(BigInteger bigInteger) {
        return new C12873p(this.f26123a.subtract(bigInteger.shiftLeft(this.f26124b)), this.f26124b);
    }

    /* renamed from: a */
    public C12873p m774a(C12873p c12873p) {
        m769c(c12873p);
        return new C12873p(this.f26123a.add(c12873p.f26123a), this.f26124b);
    }

    /* renamed from: b */
    public int m772b(BigInteger bigInteger) {
        return this.f26123a.compareTo(bigInteger.shiftLeft(this.f26124b));
    }

    /* renamed from: b */
    public BigInteger m773b() {
        return this.f26123a.shiftRight(this.f26124b);
    }

    /* renamed from: b */
    public C12873p m771b(C12873p c12873p) {
        return m774a(c12873p.m777a());
    }

    /* renamed from: c */
    public BigInteger m770c() {
        return m774a(new C12873p(InterfaceC12849c.f26070d, 1).m776a(this.f26124b)).m773b();
    }

    /* renamed from: d */
    public int m768d() {
        return this.f26124b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C12873p) {
            C12873p c12873p = (C12873p) obj;
            return this.f26123a.equals(c12873p.f26123a) && this.f26124b == c12873p.f26124b;
        }
        return false;
    }

    public int hashCode() {
        return this.f26123a.hashCode() ^ this.f26124b;
    }

    public String toString() {
        if (this.f26124b == 0) {
            return this.f26123a.toString();
        }
        BigInteger m773b = m773b();
        BigInteger subtract = this.f26123a.subtract(m773b.shiftLeft(this.f26124b));
        if (this.f26123a.signum() == -1) {
            subtract = InterfaceC12849c.f26070d.shiftLeft(this.f26124b).subtract(subtract);
        }
        if (m773b.signum() == -1 && !subtract.equals(InterfaceC12849c.f26069c)) {
            m773b = m773b.add(InterfaceC12849c.f26070d);
        }
        String bigInteger = m773b.toString();
        char[] cArr = new char[this.f26124b];
        String bigInteger2 = subtract.toString(2);
        int length = bigInteger2.length();
        int i = this.f26124b - length;
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = '0';
        }
        for (int i3 = 0; i3 < length; i3++) {
            cArr[i + i3] = bigInteger2.charAt(i3);
        }
        String str = new String(cArr);
        StringBuffer stringBuffer = new StringBuffer(bigInteger);
        stringBuffer.append(".");
        stringBuffer.append(str);
        return stringBuffer.toString();
    }
}
