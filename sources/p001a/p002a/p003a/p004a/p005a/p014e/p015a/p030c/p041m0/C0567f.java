package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;

/* renamed from: a.a.a.a.a.e.a.c.m0.f */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0567f implements InterfaceC0542i {

    /* renamed from: h */
    public static final int f1819h = 160;

    /* renamed from: a */
    public BigInteger f1820a;

    /* renamed from: b */
    public BigInteger f1821b;

    /* renamed from: c */
    public BigInteger f1822c;

    /* renamed from: d */
    public BigInteger f1823d;

    /* renamed from: e */
    public int f1824e;

    /* renamed from: f */
    public int f1825f;

    /* renamed from: g */
    public C0573i f1826g;

    public C0567f(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, null, 0);
    }

    /* renamed from: a */
    public static int m22823a(int i) {
        return (i != 0 && i < 160) ? i : f1819h;
    }

    /* renamed from: a */
    public BigInteger m22824a() {
        return this.f1820a;
    }

    /* renamed from: b */
    public BigInteger m22822b() {
        return this.f1823d;
    }

    /* renamed from: c */
    public int m22821c() {
        return this.f1825f;
    }

    /* renamed from: d */
    public int m22820d() {
        return this.f1824e;
    }

    /* renamed from: e */
    public BigInteger m22819e() {
        return this.f1821b;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C0567f) {
            C0567f c0567f = (C0567f) obj;
            if (m22818f() != null) {
                if (!m22818f().equals(c0567f.m22818f())) {
                    return false;
                }
            } else if (c0567f.m22818f() != null) {
                return false;
            }
            return c0567f.m22819e().equals(this.f1821b) && c0567f.m22824a().equals(this.f1820a);
        }
        return false;
    }

    /* renamed from: f */
    public BigInteger m22818f() {
        return this.f1822c;
    }

    /* renamed from: g */
    public C0573i m22817g() {
        return this.f1826g;
    }

    public int hashCode() {
        return (m22819e().hashCode() ^ m22824a().hashCode()) ^ (m22818f() != null ? m22818f().hashCode() : 0);
    }

    public C0567f(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this(bigInteger, bigInteger2, bigInteger3, 0);
    }

    public C0567f(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i) {
        this(bigInteger, bigInteger2, bigInteger3, m22823a(i), i, null, null);
    }

    public C0567f(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i, int i2) {
        this(bigInteger, bigInteger2, bigInteger3, i, i2, null, null);
    }

    public C0567f(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, C0573i c0573i) {
        this(bigInteger, bigInteger2, bigInteger3, f1819h, 0, bigInteger4, c0573i);
    }

    public C0567f(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i, int i2, BigInteger bigInteger4, C0573i c0573i) {
        if (i2 != 0) {
            if (BigInteger.valueOf((i2 - 1) ^ 2).compareTo(bigInteger) == 1) {
                throw new IllegalArgumentException("when l value specified, it must satisfy 2^(l-1) <= p");
            }
            if (i2 < i) {
                throw new IllegalArgumentException("when l value specified, it may not be less than m value");
            }
        }
        this.f1820a = bigInteger2;
        this.f1821b = bigInteger;
        this.f1822c = bigInteger3;
        this.f1824e = i;
        this.f1825f = i2;
        this.f1823d = bigInteger4;
        this.f1826g = c0573i;
    }
}
