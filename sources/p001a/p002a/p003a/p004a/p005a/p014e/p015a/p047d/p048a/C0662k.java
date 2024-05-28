package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a;

import java.math.BigInteger;

/* renamed from: a.a.a.a.a.e.a.d.a.k */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0662k {

    /* renamed from: c */
    public static final long f2014c = 1;

    /* renamed from: a */
    public final BigInteger f2015a;

    /* renamed from: b */
    public final int f2016b;

    public C0662k(BigInteger bigInteger, int i) {
        if (i >= 0) {
            this.f2015a = bigInteger;
            this.f2016b = i;
            return;
        }
        throw new IllegalArgumentException("scale may not be negative");
    }

    /* renamed from: a */
    public static C0662k m22545a(BigInteger bigInteger, int i) {
        return new C0662k(bigInteger.shiftLeft(i), i);
    }

    /* renamed from: f */
    private void m22530f(C0662k c0662k) {
        if (this.f2016b != c0662k.f2016b) {
            throw new IllegalArgumentException("Only SimpleBigDecimal of same scale allowed in arithmetic operations");
        }
    }

    /* renamed from: b */
    public C0662k m22543b(int i) {
        return new C0662k(this.f2015a.shiftLeft(i), this.f2016b);
    }

    /* renamed from: c */
    public C0662k m22539c(C0662k c0662k) {
        m22530f(c0662k);
        return new C0662k(this.f2015a.shiftLeft(this.f2016b).divide(c0662k.f2015a), this.f2016b);
    }

    /* renamed from: d */
    public C0662k m22536d(C0662k c0662k) {
        m22530f(c0662k);
        BigInteger multiply = this.f2015a.multiply(c0662k.f2015a);
        int i = this.f2016b;
        return new C0662k(multiply, i + i);
    }

    /* renamed from: e */
    public C0662k m22534e() {
        return new C0662k(this.f2015a.negate(), this.f2016b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0662k) {
            C0662k c0662k = (C0662k) obj;
            return this.f2015a.equals(c0662k.f2015a) && this.f2016b == c0662k.f2016b;
        }
        return false;
    }

    public int hashCode() {
        return this.f2015a.hashCode() ^ this.f2016b;
    }

    public String toString() {
        if (this.f2016b == 0) {
            return this.f2015a.toString();
        }
        BigInteger m22549a = m22549a();
        BigInteger subtract = this.f2015a.subtract(m22549a.shiftLeft(this.f2016b));
        if (this.f2015a.signum() == -1) {
            subtract = InterfaceC0647b.f1977b.shiftLeft(this.f2016b).subtract(subtract);
        }
        if (m22549a.signum() == -1 && !subtract.equals(InterfaceC0647b.f1976a)) {
            m22549a = m22549a.add(InterfaceC0647b.f1977b);
        }
        String bigInteger = m22549a.toString();
        char[] cArr = new char[this.f2016b];
        String bigInteger2 = subtract.toString(2);
        int length = bigInteger2.length();
        int i = this.f2016b - length;
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

    /* renamed from: a */
    public C0662k m22548a(int i) {
        if (i >= 0) {
            int i2 = this.f2016b;
            if (i == i2) {
                return new C0662k(this);
            }
            return new C0662k(this.f2015a.shiftLeft(i - i2), i);
        }
        throw new IllegalArgumentException("scale may not be negative");
    }

    /* renamed from: b */
    public int m22542b(C0662k c0662k) {
        m22530f(c0662k);
        return this.f2015a.compareTo(c0662k.f2015a);
    }

    /* renamed from: e */
    public C0662k m22533e(C0662k c0662k) {
        return m22547a(c0662k.m22534e());
    }

    /* renamed from: d */
    public C0662k m22535d(BigInteger bigInteger) {
        return new C0662k(this.f2015a.multiply(bigInteger), this.f2016b);
    }

    /* renamed from: e */
    public C0662k m22532e(BigInteger bigInteger) {
        return new C0662k(this.f2015a.subtract(bigInteger.shiftLeft(this.f2016b)), this.f2016b);
    }

    /* renamed from: b */
    public int m22541b(BigInteger bigInteger) {
        return this.f2015a.compareTo(bigInteger.shiftLeft(this.f2016b));
    }

    /* renamed from: c */
    public C0662k m22538c(BigInteger bigInteger) {
        return new C0662k(this.f2015a.divide(bigInteger), this.f2016b);
    }

    /* renamed from: d */
    public long m22537d() {
        return m22549a().longValue();
    }

    /* renamed from: f */
    public BigInteger m22531f() {
        return m22547a(new C0662k(InterfaceC0647b.f1977b, 1).m22548a(this.f2016b)).m22549a();
    }

    /* renamed from: b */
    public int m22544b() {
        return this.f2016b;
    }

    /* renamed from: c */
    public int m22540c() {
        return m22549a().intValue();
    }

    /* renamed from: a */
    public C0662k m22547a(C0662k c0662k) {
        m22530f(c0662k);
        return new C0662k(this.f2015a.add(c0662k.f2015a), this.f2016b);
    }

    public C0662k(C0662k c0662k) {
        this.f2015a = c0662k.f2015a;
        this.f2016b = c0662k.f2016b;
    }

    /* renamed from: a */
    public C0662k m22546a(BigInteger bigInteger) {
        return new C0662k(this.f2015a.add(bigInteger.shiftLeft(this.f2016b)), this.f2016b);
    }

    /* renamed from: a */
    public BigInteger m22549a() {
        return this.f2015a.shiftRight(this.f2016b);
    }
}
