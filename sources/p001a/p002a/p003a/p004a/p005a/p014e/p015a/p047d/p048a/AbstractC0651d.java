package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a;

import java.math.BigInteger;
import java.util.Random;

/* renamed from: a.a.a.a.a.e.a.d.a.d */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC0651d implements InterfaceC0647b {

    /* renamed from: a.a.a.a.a.e.a.d.a.d$a */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public static class C0652a extends AbstractC0651d {

        /* renamed from: m */
        public static final int f1994m = 1;

        /* renamed from: n */
        public static final int f1995n = 2;

        /* renamed from: o */
        public static final int f1996o = 3;

        /* renamed from: f */
        public int f1997f;

        /* renamed from: g */
        public int f1998g;

        /* renamed from: h */
        public int f1999h;

        /* renamed from: i */
        public int f2000i;

        /* renamed from: j */
        public int f2001j;

        /* renamed from: k */
        public C0659h f2002k;

        /* renamed from: l */
        public int f2003l;

        public C0652a(int i, int i2, int i3, int i4, BigInteger bigInteger) {
            int i5 = (i + 31) >> 5;
            this.f2003l = i5;
            this.f2002k = new C0659h(bigInteger, i5);
            if (i3 == 0 && i4 == 0) {
                this.f1997f = 2;
            } else if (i3 >= i4) {
                throw new IllegalArgumentException("k2 must be smaller than k3");
            } else {
                if (i3 > 0) {
                    this.f1997f = 3;
                } else {
                    throw new IllegalArgumentException("k2 must be larger than 0");
                }
            }
            if (bigInteger.signum() >= 0) {
                this.f1998g = i;
                this.f1999h = i2;
                this.f2000i = i3;
                this.f2001j = i4;
                return;
            }
            throw new IllegalArgumentException("x value cannot be negative");
        }

        /* renamed from: a */
        public static void m22601a(AbstractC0651d abstractC0651d, AbstractC0651d abstractC0651d2) {
            if ((abstractC0651d instanceof C0652a) && (abstractC0651d2 instanceof C0652a)) {
                C0652a c0652a = (C0652a) abstractC0651d;
                C0652a c0652a2 = (C0652a) abstractC0651d2;
                if (c0652a.f1998g == c0652a2.f1998g && c0652a.f1999h == c0652a2.f1999h && c0652a.f2000i == c0652a2.f2000i && c0652a.f2001j == c0652a2.f2001j) {
                    if (c0652a.f1997f != c0652a2.f1997f) {
                        throw new IllegalArgumentException("One of the field elements are not elements has incorrect representation");
                    }
                    return;
                }
                throw new IllegalArgumentException("Field elements are not elements of the same field F2m");
            }
            throw new IllegalArgumentException("Field elements are not both instances of ECFieldElement.F2m");
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d
        /* renamed from: a */
        public String mo22595a() {
            return "F2m";
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d
        /* renamed from: b */
        public int mo22592b() {
            return this.f1998g;
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d
        /* renamed from: c */
        public AbstractC0651d mo22589c(AbstractC0651d abstractC0651d) {
            C0659h m22558b = this.f2002k.m22558b(((C0652a) abstractC0651d).f2002k, this.f1998g);
            m22558b.m22562a(this.f1998g, new int[]{this.f1999h, this.f2000i, this.f2001j});
            return new C0652a(this.f1998g, this.f1999h, this.f2000i, this.f2001j, m22558b);
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d
        /* renamed from: d */
        public AbstractC0651d mo22588d() {
            return this;
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d
        /* renamed from: d */
        public AbstractC0651d mo22587d(AbstractC0651d abstractC0651d) {
            return mo22594a(abstractC0651d);
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d
        /* renamed from: e */
        public AbstractC0651d mo22586e() {
            throw new RuntimeException("Not implemented");
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof C0652a) {
                C0652a c0652a = (C0652a) obj;
                return this.f1998g == c0652a.f1998g && this.f1999h == c0652a.f1999h && this.f2000i == c0652a.f2000i && this.f2001j == c0652a.f2001j && this.f1997f == c0652a.f1997f && this.f2002k.equals(c0652a.f2002k);
            }
            return false;
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d
        /* renamed from: f */
        public AbstractC0651d mo22585f() {
            C0659h m22554d = this.f2002k.m22554d(this.f1998g);
            m22554d.m22562a(this.f1998g, new int[]{this.f1999h, this.f2000i, this.f2001j});
            return new C0652a(this.f1998g, this.f1999h, this.f2000i, this.f2001j, m22554d);
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d
        /* renamed from: g */
        public BigInteger mo22584g() {
            return this.f2002k.m22551f();
        }

        /* renamed from: h */
        public int m22600h() {
            return this.f1999h;
        }

        public int hashCode() {
            return (((this.f2002k.hashCode() ^ this.f1998g) ^ this.f1999h) ^ this.f2000i) ^ this.f2001j;
        }

        /* renamed from: i */
        public int m22599i() {
            return this.f2000i;
        }

        /* renamed from: j */
        public int m22598j() {
            return this.f2001j;
        }

        /* renamed from: k */
        public int m22597k() {
            return this.f1998g;
        }

        /* renamed from: l */
        public int m22596l() {
            return this.f1997f;
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d
        /* renamed from: b */
        public AbstractC0651d mo22591b(AbstractC0651d abstractC0651d) {
            return mo22589c(abstractC0651d.mo22590c());
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d
        /* renamed from: c */
        public AbstractC0651d mo22590c() {
            C0659h c0659h = (C0659h) this.f2002k.clone();
            C0659h c0659h2 = new C0659h(this.f2003l);
            c0659h2.m22559b(this.f1998g);
            c0659h2.m22559b(0);
            c0659h2.m22559b(this.f1999h);
            if (this.f1997f == 3) {
                c0659h2.m22559b(this.f2000i);
                c0659h2.m22559b(this.f2001j);
            }
            C0659h c0659h3 = new C0659h(this.f2003l);
            c0659h3.m22559b(0);
            C0659h c0659h4 = new C0659h(this.f2003l);
            while (!c0659h.m22555d()) {
                int m22564a = c0659h.m22564a() - c0659h2.m22564a();
                if (m22564a < 0) {
                    m22564a = -m22564a;
                    C0659h c0659h5 = c0659h2;
                    c0659h2 = c0659h;
                    c0659h = c0659h5;
                    C0659h c0659h6 = c0659h4;
                    c0659h4 = c0659h3;
                    c0659h3 = c0659h6;
                }
                int i = m22564a >> 5;
                int i2 = m22564a & 31;
                c0659h.m22561a(c0659h2.m22556c(i2), i);
                c0659h3.m22561a(c0659h4.m22556c(i2), i);
            }
            return new C0652a(this.f1998g, this.f1999h, this.f2000i, this.f2001j, c0659h4);
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d
        /* renamed from: a */
        public AbstractC0651d mo22594a(AbstractC0651d abstractC0651d) {
            C0659h c0659h = (C0659h) this.f2002k.clone();
            c0659h.m22561a(((C0652a) abstractC0651d).f2002k, 0);
            return new C0652a(this.f1998g, this.f1999h, this.f2000i, this.f2001j, c0659h);
        }

        public C0652a(int i, int i2, BigInteger bigInteger) {
            this(i, i2, 0, 0, bigInteger);
        }

        public C0652a(int i, int i2, int i3, int i4, C0659h c0659h) {
            this.f2003l = (i + 31) >> 5;
            this.f2002k = c0659h;
            this.f1998g = i;
            this.f1999h = i2;
            this.f2000i = i3;
            this.f2001j = i4;
            if (i3 == 0 && i4 == 0) {
                this.f1997f = 2;
            } else {
                this.f1997f = 3;
            }
        }
    }

    /* renamed from: a.a.a.a.a.e.a.d.a.d$b */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public static class C0653b extends AbstractC0651d {

        /* renamed from: f */
        public BigInteger f2004f;

        /* renamed from: g */
        public BigInteger f2005g;

        public C0653b(BigInteger bigInteger, BigInteger bigInteger2) {
            this.f2004f = bigInteger2;
            if (bigInteger2.compareTo(bigInteger) < 0) {
                this.f2005g = bigInteger;
                return;
            }
            throw new IllegalArgumentException("x value too large in field element");
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d
        /* renamed from: a */
        public AbstractC0651d mo22594a(AbstractC0651d abstractC0651d) {
            return new C0653b(this.f2005g, this.f2004f.add(abstractC0651d.mo22584g()).mod(this.f2005g));
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d
        /* renamed from: a */
        public String mo22595a() {
            return "Fp";
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d
        /* renamed from: b */
        public int mo22592b() {
            return this.f2005g.bitLength();
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d
        /* renamed from: c */
        public AbstractC0651d mo22589c(AbstractC0651d abstractC0651d) {
            return new C0653b(this.f2005g, this.f2004f.multiply(abstractC0651d.mo22584g()).mod(this.f2005g));
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d
        /* renamed from: d */
        public AbstractC0651d mo22587d(AbstractC0651d abstractC0651d) {
            return new C0653b(this.f2005g, this.f2004f.subtract(abstractC0651d.mo22584g()).mod(this.f2005g));
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d
        /* renamed from: e */
        public AbstractC0651d mo22586e() {
            if (this.f2005g.testBit(0)) {
                if (this.f2005g.testBit(1)) {
                    BigInteger bigInteger = this.f2005g;
                    C0653b c0653b = new C0653b(bigInteger, this.f2004f.modPow(bigInteger.shiftRight(2).add(InterfaceC0647b.f1977b), this.f2005g));
                    if (c0653b.mo22585f().equals(this)) {
                        return c0653b;
                    }
                    return null;
                }
                BigInteger subtract = this.f2005g.subtract(InterfaceC0647b.f1977b);
                BigInteger shiftRight = subtract.shiftRight(1);
                if (!this.f2004f.modPow(shiftRight, this.f2005g).equals(InterfaceC0647b.f1977b)) {
                    return null;
                }
                BigInteger add = subtract.shiftRight(2).shiftLeft(1).add(InterfaceC0647b.f1977b);
                BigInteger bigInteger2 = this.f2004f;
                BigInteger mod = bigInteger2.shiftLeft(2).mod(this.f2005g);
                Random random = new Random();
                while (true) {
                    BigInteger bigInteger3 = new BigInteger(this.f2005g.bitLength(), random);
                    if (bigInteger3.compareTo(this.f2005g) < 0 && bigInteger3.multiply(bigInteger3).subtract(mod).modPow(shiftRight, this.f2005g).equals(subtract)) {
                        BigInteger[] m22593a = m22593a(this.f2005g, bigInteger3, bigInteger2, add);
                        BigInteger bigInteger4 = m22593a[0];
                        BigInteger bigInteger5 = m22593a[1];
                        if (bigInteger5.multiply(bigInteger5).mod(this.f2005g).equals(mod)) {
                            if (bigInteger5.testBit(0)) {
                                bigInteger5 = bigInteger5.add(this.f2005g);
                            }
                            return new C0653b(this.f2005g, bigInteger5.shiftRight(1));
                        } else if (!bigInteger4.equals(InterfaceC0647b.f1977b) && !bigInteger4.equals(subtract)) {
                            return null;
                        }
                    }
                }
            } else {
                throw new RuntimeException("not done yet");
            }
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof C0653b) {
                C0653b c0653b = (C0653b) obj;
                return this.f2005g.equals(c0653b.f2005g) && this.f2004f.equals(c0653b.f2004f);
            }
            return false;
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d
        /* renamed from: f */
        public AbstractC0651d mo22585f() {
            BigInteger bigInteger = this.f2005g;
            BigInteger bigInteger2 = this.f2004f;
            return new C0653b(bigInteger, bigInteger2.multiply(bigInteger2).mod(this.f2005g));
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d
        /* renamed from: g */
        public BigInteger mo22584g() {
            return this.f2004f;
        }

        /* renamed from: h */
        public BigInteger m22583h() {
            return this.f2005g;
        }

        public int hashCode() {
            return this.f2005g.hashCode() ^ this.f2004f.hashCode();
        }

        /* renamed from: a */
        public static BigInteger[] m22593a(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
            int bitLength = bigInteger4.bitLength();
            int lowestSetBit = bigInteger4.getLowestSetBit();
            BigInteger bigInteger5 = InterfaceC0647b.f1977b;
            BigInteger bigInteger6 = InterfaceC0647b.f1978c;
            BigInteger bigInteger7 = InterfaceC0647b.f1977b;
            BigInteger bigInteger8 = bigInteger5;
            BigInteger bigInteger9 = bigInteger6;
            BigInteger bigInteger10 = bigInteger7;
            BigInteger bigInteger11 = bigInteger2;
            for (int i = bitLength - 1; i >= lowestSetBit + 1; i--) {
                bigInteger7 = bigInteger7.multiply(bigInteger10).mod(bigInteger);
                if (bigInteger4.testBit(i)) {
                    bigInteger10 = bigInteger7.multiply(bigInteger3).mod(bigInteger);
                    bigInteger8 = bigInteger8.multiply(bigInteger11).mod(bigInteger);
                    bigInteger9 = bigInteger11.multiply(bigInteger9).subtract(bigInteger2.multiply(bigInteger7)).mod(bigInteger);
                    bigInteger11 = bigInteger11.multiply(bigInteger11).subtract(bigInteger10.shiftLeft(1)).mod(bigInteger);
                } else {
                    BigInteger mod = bigInteger8.multiply(bigInteger9).subtract(bigInteger7).mod(bigInteger);
                    bigInteger11 = bigInteger11.multiply(bigInteger9).subtract(bigInteger2.multiply(bigInteger7)).mod(bigInteger);
                    bigInteger9 = bigInteger9.multiply(bigInteger9).subtract(bigInteger7.shiftLeft(1)).mod(bigInteger);
                    bigInteger8 = mod;
                    bigInteger10 = bigInteger7;
                }
            }
            BigInteger mod2 = bigInteger7.multiply(bigInteger10).mod(bigInteger);
            BigInteger mod3 = mod2.multiply(bigInteger3).mod(bigInteger);
            BigInteger mod4 = bigInteger8.multiply(bigInteger9).subtract(mod2).mod(bigInteger);
            BigInteger mod5 = bigInteger11.multiply(bigInteger9).subtract(bigInteger2.multiply(mod2)).mod(bigInteger);
            BigInteger mod6 = mod2.multiply(mod3).mod(bigInteger);
            BigInteger bigInteger12 = mod5;
            for (int i2 = 1; i2 <= lowestSetBit; i2++) {
                mod4 = mod4.multiply(bigInteger12).mod(bigInteger);
                bigInteger12 = bigInteger12.multiply(bigInteger12).subtract(mod6.shiftLeft(1)).mod(bigInteger);
                mod6 = mod6.multiply(mod6).mod(bigInteger);
            }
            return new BigInteger[]{mod4, bigInteger12};
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d
        /* renamed from: b */
        public AbstractC0651d mo22591b(AbstractC0651d abstractC0651d) {
            return new C0653b(this.f2005g, this.f2004f.multiply(abstractC0651d.mo22584g().modInverse(this.f2005g)).mod(this.f2005g));
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d
        /* renamed from: c */
        public AbstractC0651d mo22590c() {
            BigInteger bigInteger = this.f2005g;
            return new C0653b(bigInteger, this.f2004f.modInverse(bigInteger));
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d
        /* renamed from: d */
        public AbstractC0651d mo22588d() {
            return new C0653b(this.f2005g, this.f2004f.negate().mod(this.f2005g));
        }
    }

    /* renamed from: a */
    public abstract AbstractC0651d mo22594a(AbstractC0651d abstractC0651d);

    /* renamed from: a */
    public abstract String mo22595a();

    /* renamed from: b */
    public abstract int mo22592b();

    /* renamed from: b */
    public abstract AbstractC0651d mo22591b(AbstractC0651d abstractC0651d);

    /* renamed from: c */
    public abstract AbstractC0651d mo22590c();

    /* renamed from: c */
    public abstract AbstractC0651d mo22589c(AbstractC0651d abstractC0651d);

    /* renamed from: d */
    public abstract AbstractC0651d mo22588d();

    /* renamed from: d */
    public abstract AbstractC0651d mo22587d(AbstractC0651d abstractC0651d);

    /* renamed from: e */
    public abstract AbstractC0651d mo22586e();

    /* renamed from: f */
    public abstract AbstractC0651d mo22585f();

    /* renamed from: g */
    public abstract BigInteger mo22584g();

    public String toString() {
        return mo22584g().toString(2);
    }
}
