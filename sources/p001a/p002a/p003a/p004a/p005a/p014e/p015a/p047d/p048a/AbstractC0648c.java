package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a;

import java.math.BigInteger;
import java.util.Random;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f;

/* renamed from: a.a.a.a.a.e.a.d.a.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC0648c {

    /* renamed from: a */
    public AbstractC0651d f1981a;

    /* renamed from: b */
    public AbstractC0651d f1982b;

    /* renamed from: a.a.a.a.a.e.a.d.a.c$a */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public static class C0649a extends AbstractC0648c {

        /* renamed from: c */
        public int f1983c;

        /* renamed from: d */
        public int f1984d;

        /* renamed from: e */
        public int f1985e;

        /* renamed from: f */
        public int f1986f;

        /* renamed from: g */
        public BigInteger f1987g;

        /* renamed from: h */
        public BigInteger f1988h;

        /* renamed from: i */
        public AbstractC0655f.C0656a f1989i;

        /* renamed from: j */
        public byte f1990j;

        /* renamed from: k */
        public BigInteger[] f1991k;

        public C0649a(int i, int i2, BigInteger bigInteger, BigInteger bigInteger2) {
            this(i, i2, 0, 0, bigInteger, bigInteger2, null, null);
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0648c
        /* renamed from: a */
        public AbstractC0651d mo22606a(BigInteger bigInteger) {
            return new AbstractC0651d.C0652a(this.f1983c, this.f1984d, this.f1985e, this.f1986f, bigInteger);
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0648c
        /* renamed from: c */
        public int mo22604c() {
            return this.f1983c;
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0648c
        /* renamed from: d */
        public AbstractC0655f mo22603d() {
            return this.f1989i;
        }

        /* renamed from: e */
        public BigInteger m22617e() {
            return this.f1988h;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof C0649a) {
                C0649a c0649a = (C0649a) obj;
                return this.f1983c == c0649a.f1983c && this.f1984d == c0649a.f1984d && this.f1985e == c0649a.f1985e && this.f1986f == c0649a.f1986f && this.f1981a.equals(c0649a.f1981a) && this.f1982b.equals(c0649a.f1982b);
            }
            return false;
        }

        /* renamed from: f */
        public int m22616f() {
            return this.f1984d;
        }

        /* renamed from: g */
        public int m22615g() {
            return this.f1985e;
        }

        /* renamed from: h */
        public int m22614h() {
            return this.f1986f;
        }

        public int hashCode() {
            return ((((this.f1981a.hashCode() ^ this.f1982b.hashCode()) ^ this.f1983c) ^ this.f1984d) ^ this.f1985e) ^ this.f1986f;
        }

        /* renamed from: i */
        public int m22613i() {
            return this.f1983c;
        }

        /* renamed from: j */
        public synchronized byte m22612j() {
            if (this.f1990j == 0) {
                this.f1990j = C0663l.m22524a(this);
            }
            return this.f1990j;
        }

        /* renamed from: k */
        public BigInteger m22611k() {
            return this.f1987g;
        }

        /* renamed from: l */
        public synchronized BigInteger[] m22610l() {
            if (this.f1991k == null) {
                this.f1991k = C0663l.m22514b(this);
            }
            return this.f1991k;
        }

        /* renamed from: m */
        public boolean m22609m() {
            return (this.f1987g == null || this.f1988h == null || (!this.f1981a.mo22584g().equals(InterfaceC0647b.f1976a) && !this.f1981a.mo22584g().equals(InterfaceC0647b.f1977b)) || !this.f1982b.mo22584g().equals(InterfaceC0647b.f1977b)) ? false : true;
        }

        /* renamed from: n */
        public boolean m22608n() {
            return this.f1985e == 0 && this.f1986f == 0;
        }

        public C0649a(int i, int i2, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
            this(i, i2, 0, 0, bigInteger, bigInteger2, bigInteger3, bigInteger4);
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0648c
        /* renamed from: a */
        public AbstractC0655f mo22605a(BigInteger bigInteger, BigInteger bigInteger2, boolean z) {
            return new AbstractC0655f.C0656a(this, mo22606a(bigInteger), mo22606a(bigInteger2), z);
        }

        public C0649a(int i, int i2, int i3, int i4, BigInteger bigInteger, BigInteger bigInteger2) {
            this(i, i2, i3, i4, bigInteger, bigInteger2, null, null);
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0648c
        /* renamed from: a */
        public AbstractC0655f mo22607a(int i, BigInteger bigInteger) {
            AbstractC0651d mo22589c;
            AbstractC0651d mo22606a = mo22606a(bigInteger);
            if (mo22606a.mo22584g().equals(InterfaceC0647b.f1976a)) {
                mo22589c = (AbstractC0651d.C0652a) this.f1982b;
                for (int i2 = 0; i2 < this.f1983c - 1; i2++) {
                    mo22589c = mo22589c.mo22585f();
                }
            } else {
                AbstractC0651d m22618a = m22618a(mo22606a.mo22594a(this.f1981a).mo22594a(this.f1982b.mo22589c(mo22606a.mo22585f().mo22590c())));
                if (m22618a != null) {
                    if (m22618a.mo22584g().testBit(0) != i) {
                        m22618a = m22618a.mo22594a(mo22606a(InterfaceC0647b.f1977b));
                    }
                    mo22589c = mo22606a.mo22589c(m22618a);
                } else {
                    throw new IllegalArgumentException("Invalid point compression");
                }
            }
            return new AbstractC0655f.C0656a(this, mo22606a, mo22589c, true);
        }

        public C0649a(int i, int i2, int i3, int i4, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
            this.f1990j = (byte) 0;
            this.f1991k = null;
            this.f1983c = i;
            this.f1984d = i2;
            this.f1985e = i3;
            this.f1986f = i4;
            this.f1987g = bigInteger3;
            this.f1988h = bigInteger4;
            if (i2 != 0) {
                if (i3 == 0) {
                    if (i4 != 0) {
                        throw new IllegalArgumentException("k3 must be 0 if k2 == 0");
                    }
                } else if (i3 <= i2) {
                    throw new IllegalArgumentException("k2 must be > k1");
                } else {
                    if (i4 <= i3) {
                        throw new IllegalArgumentException("k3 must be > k2");
                    }
                }
                this.f1981a = mo22606a(bigInteger);
                this.f1982b = mo22606a(bigInteger2);
                this.f1989i = new AbstractC0655f.C0656a(this, null, null);
                return;
            }
            throw new IllegalArgumentException("k1 must be > 0");
        }

        /* renamed from: a */
        private AbstractC0651d m22618a(AbstractC0651d abstractC0651d) {
            AbstractC0651d abstractC0651d2;
            AbstractC0651d.C0652a c0652a = new AbstractC0651d.C0652a(this.f1983c, this.f1984d, this.f1985e, this.f1986f, InterfaceC0647b.f1976a);
            if (abstractC0651d.mo22584g().equals(InterfaceC0647b.f1976a)) {
                return c0652a;
            }
            Random random = new Random();
            do {
                int i = this.f1983c;
                AbstractC0651d.C0652a c0652a2 = new AbstractC0651d.C0652a(i, this.f1984d, this.f1985e, this.f1986f, new BigInteger(i, random));
                AbstractC0651d abstractC0651d3 = abstractC0651d;
                abstractC0651d2 = c0652a;
                for (int i2 = 1; i2 <= this.f1983c - 1; i2++) {
                    AbstractC0651d mo22585f = abstractC0651d3.mo22585f();
                    abstractC0651d2 = abstractC0651d2.mo22585f().mo22594a(mo22585f.mo22589c(c0652a2));
                    abstractC0651d3 = mo22585f.mo22594a(abstractC0651d);
                }
                if (!abstractC0651d3.mo22584g().equals(InterfaceC0647b.f1976a)) {
                    return null;
                }
            } while (abstractC0651d2.mo22585f().mo22594a(abstractC0651d2).mo22584g().equals(InterfaceC0647b.f1976a));
            return abstractC0651d2;
        }
    }

    /* renamed from: a.a.a.a.a.e.a.d.a.c$b */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public static class C0650b extends AbstractC0648c {

        /* renamed from: c */
        public BigInteger f1992c;

        /* renamed from: d */
        public AbstractC0655f.C0657b f1993d;

        public C0650b(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            this.f1992c = bigInteger;
            this.f1981a = mo22606a(bigInteger2);
            this.f1982b = mo22606a(bigInteger3);
            this.f1993d = new AbstractC0655f.C0657b(this, null, null);
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0648c
        /* renamed from: a */
        public AbstractC0651d mo22606a(BigInteger bigInteger) {
            return new AbstractC0651d.C0653b(this.f1992c, bigInteger);
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0648c
        /* renamed from: c */
        public int mo22604c() {
            return this.f1992c.bitLength();
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0648c
        /* renamed from: d */
        public AbstractC0655f mo22603d() {
            return this.f1993d;
        }

        /* renamed from: e */
        public BigInteger m22602e() {
            return this.f1992c;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof C0650b) {
                C0650b c0650b = (C0650b) obj;
                return this.f1992c.equals(c0650b.f1992c) && this.f1981a.equals(c0650b.f1981a) && this.f1982b.equals(c0650b.f1982b);
            }
            return false;
        }

        public int hashCode() {
            return (this.f1981a.hashCode() ^ this.f1982b.hashCode()) ^ this.f1992c.hashCode();
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0648c
        /* renamed from: a */
        public AbstractC0655f mo22605a(BigInteger bigInteger, BigInteger bigInteger2, boolean z) {
            return new AbstractC0655f.C0657b(this, mo22606a(bigInteger), mo22606a(bigInteger2), z);
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0648c
        /* renamed from: a */
        public AbstractC0655f mo22607a(int i, BigInteger bigInteger) {
            AbstractC0651d mo22606a = mo22606a(bigInteger);
            AbstractC0651d mo22586e = mo22606a.mo22589c(mo22606a.mo22585f().mo22594a(this.f1981a)).mo22594a(this.f1982b).mo22586e();
            if (mo22586e != null) {
                BigInteger mo22584g = mo22586e.mo22584g();
                if (mo22584g.testBit(0) != i) {
                    mo22586e = mo22606a(this.f1992c.subtract(mo22584g));
                }
                return new AbstractC0655f.C0657b(this, mo22606a, mo22586e, true);
            }
            throw new RuntimeException("Invalid point compression");
        }
    }

    /* renamed from: a */
    public AbstractC0651d m22622a() {
        return this.f1981a;
    }

    /* renamed from: a */
    public abstract AbstractC0651d mo22606a(BigInteger bigInteger);

    /* renamed from: a */
    public abstract AbstractC0655f mo22607a(int i, BigInteger bigInteger);

    /* renamed from: a */
    public abstract AbstractC0655f mo22605a(BigInteger bigInteger, BigInteger bigInteger2, boolean z);

    /* renamed from: b */
    public AbstractC0651d m22619b() {
        return this.f1982b;
    }

    /* renamed from: c */
    public abstract int mo22604c();

    /* renamed from: d */
    public abstract AbstractC0655f mo22603d();

    /* renamed from: a */
    public AbstractC0655f m22621a(byte[] bArr) {
        int mo22604c = (mo22604c() + 7) / 8;
        switch (bArr[0]) {
            case 0:
                if (bArr.length == 1) {
                    return mo22603d();
                }
                throw new IllegalArgumentException("Incorrect length for infinity encoding");
            case 1:
            case 5:
            default:
                throw new IllegalArgumentException("Invalid point encoding 0x" + Integer.toString(bArr[0], 16));
            case 2:
            case 3:
                if (bArr.length == mo22604c + 1) {
                    return mo22607a(bArr[0] & 1, m22620a(bArr, 1, mo22604c));
                }
                throw new IllegalArgumentException("Incorrect length for compressed encoding");
            case 4:
            case 6:
            case 7:
                if (bArr.length == (mo22604c * 2) + 1) {
                    return mo22605a(m22620a(bArr, 1, mo22604c), m22620a(bArr, mo22604c + 1, mo22604c), false);
                }
                throw new IllegalArgumentException("Incorrect length for uncompressed/hybrid encoding");
        }
    }

    /* renamed from: a */
    public static BigInteger m22620a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return new BigInteger(1, bArr2);
    }
}
