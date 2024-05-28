package org.p415a.p436e.p437a;

import java.math.BigInteger;
import java.util.Random;
import org.p415a.p436e.p444c.AbstractC12890b;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p448g.C12957a;
import org.p415a.p448g.C12966b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.e */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12856e implements InterfaceC12849c {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.e.a.e$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C12857a extends AbstractC12856e {

        /* renamed from: a */
        private int f26096a;

        /* renamed from: b */
        private int f26097b;

        /* renamed from: i */
        private int[] f26098i;

        /* renamed from: j */
        private C12870m f26099j;

        public C12857a(int i, int i2, int i3, int i4, BigInteger bigInteger) {
            if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > i) {
                throw new IllegalArgumentException("x value invalid in F2m field element");
            }
            if (i3 == 0 && i4 == 0) {
                this.f26096a = 2;
                this.f26098i = new int[]{i2};
            } else if (i3 >= i4) {
                throw new IllegalArgumentException("k2 must be smaller than k3");
            } else {
                if (i3 <= 0) {
                    throw new IllegalArgumentException("k2 must be larger than 0");
                }
                this.f26096a = 3;
                this.f26098i = new int[]{i2, i3, i4};
            }
            this.f26097b = i;
            this.f26099j = new C12870m(bigInteger);
        }

        private C12857a(int i, int[] iArr, C12870m c12870m) {
            this.f26097b = i;
            this.f26096a = iArr.length == 1 ? 2 : 3;
            this.f26098i = iArr;
            this.f26099j = c12870m;
        }

        /* renamed from: b */
        public static void m898b(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
            if (!(abstractC12856e instanceof C12857a) || !(abstractC12856e2 instanceof C12857a)) {
                throw new IllegalArgumentException("Field elements are not both instances of ECFieldElement.F2m");
            }
            C12857a c12857a = (C12857a) abstractC12856e;
            C12857a c12857a2 = (C12857a) abstractC12856e2;
            if (c12857a.f26096a != c12857a2.f26096a) {
                throw new IllegalArgumentException("One of the F2m field elements has incorrect representation");
            }
            if (c12857a.f26097b != c12857a2.f26097b || !C12957a.m433a(c12857a.f26098i, c12857a2.f26098i)) {
                throw new IllegalArgumentException("Field elements are not elements of the same field F2m");
            }
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: a */
        public BigInteger mo893a() {
            return this.f26099j.m781e();
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: a */
        public AbstractC12856e mo899a(int i) {
            if (i < 1) {
                return this;
            }
            int i2 = this.f26097b;
            int[] iArr = this.f26098i;
            return new C12857a(i2, iArr, this.f26099j.m816a(i, i2, iArr));
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: a */
        public AbstractC12856e mo889a(AbstractC12856e abstractC12856e) {
            C12870m c12870m = (C12870m) this.f26099j.clone();
            c12870m.m812a(((C12857a) abstractC12856e).f26099j, 0);
            return new C12857a(this.f26097b, this.f26098i, c12870m);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: a */
        public AbstractC12856e mo888a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
            C12870m c12870m = this.f26099j;
            C12870m c12870m2 = ((C12857a) abstractC12856e).f26099j;
            C12870m c12870m3 = ((C12857a) abstractC12856e2).f26099j;
            C12870m m789c = c12870m.m789c(this.f26097b, this.f26098i);
            C12870m m797b = c12870m2.m797b(c12870m3, this.f26097b, this.f26098i);
            if (m789c == c12870m) {
                m789c = (C12870m) m789c.clone();
            }
            m789c.m812a(m797b, 0);
            m789c.m815a(this.f26097b, this.f26098i);
            return new C12857a(this.f26097b, this.f26098i, m789c);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: a */
        public AbstractC12856e mo887a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e abstractC12856e3) {
            return mo882b(abstractC12856e, abstractC12856e2, abstractC12856e3);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: b */
        public int mo886b() {
            return this.f26097b;
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: b */
        public AbstractC12856e mo883b(AbstractC12856e abstractC12856e) {
            return mo889a(abstractC12856e);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: b */
        public AbstractC12856e mo882b(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e abstractC12856e3) {
            C12870m c12870m = this.f26099j;
            C12870m c12870m2 = ((C12857a) abstractC12856e).f26099j;
            C12870m c12870m3 = ((C12857a) abstractC12856e2).f26099j;
            C12870m c12870m4 = ((C12857a) abstractC12856e3).f26099j;
            C12870m m797b = c12870m.m797b(c12870m2, this.f26097b, this.f26098i);
            C12870m m797b2 = c12870m3.m797b(c12870m4, this.f26097b, this.f26098i);
            if (m797b == c12870m || m797b == c12870m2) {
                m797b = (C12870m) m797b.clone();
            }
            m797b.m812a(m797b2, 0);
            m797b.m815a(this.f26097b, this.f26098i);
            return new C12857a(this.f26097b, this.f26098i, m797b);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: c */
        public AbstractC12856e mo881c() {
            return new C12857a(this.f26097b, this.f26098i, this.f26099j.m780f());
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: c */
        public AbstractC12856e mo878c(AbstractC12856e abstractC12856e) {
            int i = this.f26097b;
            int[] iArr = this.f26098i;
            return new C12857a(i, iArr, this.f26099j.m810a(((C12857a) abstractC12856e).f26099j, i, iArr));
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: d */
        public AbstractC12856e mo877d() {
            return this;
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: d */
        public AbstractC12856e mo875d(AbstractC12856e abstractC12856e) {
            return mo878c(abstractC12856e.mo871f());
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: e */
        public AbstractC12856e mo874e() {
            int i = this.f26097b;
            int[] iArr = this.f26098i;
            return new C12857a(i, iArr, this.f26099j.m798b(i, iArr));
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof C12857a) {
                C12857a c12857a = (C12857a) obj;
                return this.f26097b == c12857a.f26097b && this.f26096a == c12857a.f26096a && C12957a.m433a(this.f26098i, c12857a.f26098i) && this.f26099j.equals(c12857a.f26099j);
            }
            return false;
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: f */
        public AbstractC12856e mo871f() {
            int i = this.f26097b;
            int[] iArr = this.f26098i;
            return new C12857a(i, iArr, this.f26099j.m784d(i, iArr));
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: g */
        public AbstractC12856e mo870g() {
            return (this.f26099j.m800b() || this.f26099j.m818a()) ? this : mo899a(this.f26097b - 1);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: h */
        public int mo897h() {
            return this.f26099j.m786d();
        }

        public int hashCode() {
            return (this.f26099j.hashCode() ^ this.f26097b) ^ C12957a.m435a(this.f26098i);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: i */
        public boolean mo896i() {
            return this.f26099j.m818a();
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: j */
        public boolean mo895j() {
            return this.f26099j.m800b();
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: k */
        public boolean mo894k() {
            return this.f26099j.m779g();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.e.a.e$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C12858b extends AbstractC12856e {

        /* renamed from: a */
        BigInteger f26100a;

        /* renamed from: b */
        BigInteger f26101b;

        /* renamed from: i */
        BigInteger f26102i;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C12858b(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            if (bigInteger3 == null || bigInteger3.signum() < 0 || bigInteger3.compareTo(bigInteger) >= 0) {
                throw new IllegalArgumentException("x value invalid in Fp field element");
            }
            this.f26100a = bigInteger;
            this.f26101b = bigInteger2;
            this.f26102i = bigInteger3;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: a */
        public static BigInteger m892a(BigInteger bigInteger) {
            int bitLength = bigInteger.bitLength();
            if (bitLength < 96 || bigInteger.shiftRight(bitLength - 64).longValue() != -1) {
                return null;
            }
            return f26070d.shiftLeft(bitLength).subtract(bigInteger);
        }

        /* renamed from: a */
        private BigInteger[] m890a(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            int bitLength = bigInteger3.bitLength();
            int lowestSetBit = bigInteger3.getLowestSetBit();
            BigInteger bigInteger4 = InterfaceC12849c.f26070d;
            BigInteger bigInteger5 = InterfaceC12849c.f26071e;
            BigInteger bigInteger6 = InterfaceC12849c.f26070d;
            BigInteger bigInteger7 = InterfaceC12849c.f26070d;
            BigInteger bigInteger8 = bigInteger;
            for (int i = bitLength - 1; i >= lowestSetBit + 1; i--) {
                bigInteger6 = m884b(bigInteger6, bigInteger7);
                if (bigInteger3.testBit(i)) {
                    bigInteger7 = m884b(bigInteger6, bigInteger2);
                    bigInteger4 = m884b(bigInteger4, bigInteger8);
                    bigInteger5 = m873e(bigInteger8.multiply(bigInteger5).subtract(bigInteger.multiply(bigInteger6)));
                    bigInteger8 = m873e(bigInteger8.multiply(bigInteger8).subtract(bigInteger7.shiftLeft(1)));
                } else {
                    bigInteger4 = m873e(bigInteger4.multiply(bigInteger5).subtract(bigInteger6));
                    BigInteger m873e = m873e(bigInteger8.multiply(bigInteger5).subtract(bigInteger.multiply(bigInteger6)));
                    bigInteger5 = m873e(bigInteger5.multiply(bigInteger5).subtract(bigInteger6.shiftLeft(1)));
                    bigInteger8 = m873e;
                    bigInteger7 = bigInteger6;
                }
            }
            BigInteger m884b = m884b(bigInteger6, bigInteger7);
            BigInteger m884b2 = m884b(m884b, bigInteger2);
            BigInteger m873e2 = m873e(bigInteger4.multiply(bigInteger5).subtract(m884b));
            BigInteger m873e3 = m873e(bigInteger8.multiply(bigInteger5).subtract(bigInteger.multiply(m884b)));
            BigInteger m884b3 = m884b(m884b, m884b2);
            BigInteger bigInteger9 = m873e3;
            for (int i2 = 1; i2 <= lowestSetBit; i2++) {
                m873e2 = m884b(m873e2, bigInteger9);
                bigInteger9 = m873e(bigInteger9.multiply(bigInteger9).subtract(m884b3.shiftLeft(1)));
                m884b3 = m884b(m884b3, m884b3);
            }
            return new BigInteger[]{m873e2, bigInteger9};
        }

        /* renamed from: e */
        private AbstractC12856e m872e(AbstractC12856e abstractC12856e) {
            if (abstractC12856e.mo874e().equals(this)) {
                return abstractC12856e;
            }
            return null;
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: a */
        public BigInteger mo893a() {
            return this.f26102i;
        }

        /* renamed from: a */
        protected BigInteger m891a(BigInteger bigInteger, BigInteger bigInteger2) {
            BigInteger add = bigInteger.add(bigInteger2);
            return add.compareTo(this.f26100a) >= 0 ? add.subtract(this.f26100a) : add;
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: a */
        public AbstractC12856e mo889a(AbstractC12856e abstractC12856e) {
            return new C12858b(this.f26100a, this.f26101b, m891a(this.f26102i, abstractC12856e.mo893a()));
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: a */
        public AbstractC12856e mo888a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
            BigInteger bigInteger = this.f26102i;
            BigInteger mo893a = abstractC12856e.mo893a();
            BigInteger mo893a2 = abstractC12856e2.mo893a();
            return new C12858b(this.f26100a, this.f26101b, m873e(bigInteger.multiply(bigInteger).add(mo893a.multiply(mo893a2))));
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: a */
        public AbstractC12856e mo887a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e abstractC12856e3) {
            BigInteger bigInteger = this.f26102i;
            BigInteger mo893a = abstractC12856e.mo893a();
            BigInteger mo893a2 = abstractC12856e2.mo893a();
            BigInteger mo893a3 = abstractC12856e3.mo893a();
            return new C12858b(this.f26100a, this.f26101b, m873e(bigInteger.multiply(mo893a).subtract(mo893a2.multiply(mo893a3))));
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: b */
        public int mo886b() {
            return this.f26100a.bitLength();
        }

        /* renamed from: b */
        protected BigInteger m885b(BigInteger bigInteger) {
            BigInteger shiftLeft = bigInteger.shiftLeft(1);
            return shiftLeft.compareTo(this.f26100a) >= 0 ? shiftLeft.subtract(this.f26100a) : shiftLeft;
        }

        /* renamed from: b */
        protected BigInteger m884b(BigInteger bigInteger, BigInteger bigInteger2) {
            return m873e(bigInteger.multiply(bigInteger2));
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: b */
        public AbstractC12856e mo883b(AbstractC12856e abstractC12856e) {
            return new C12858b(this.f26100a, this.f26101b, m879c(this.f26102i, abstractC12856e.mo893a()));
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: b */
        public AbstractC12856e mo882b(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e abstractC12856e3) {
            BigInteger bigInteger = this.f26102i;
            BigInteger mo893a = abstractC12856e.mo893a();
            BigInteger mo893a2 = abstractC12856e2.mo893a();
            BigInteger mo893a3 = abstractC12856e3.mo893a();
            return new C12858b(this.f26100a, this.f26101b, m873e(bigInteger.multiply(mo893a).add(mo893a2.multiply(mo893a3))));
        }

        /* renamed from: c */
        protected BigInteger m880c(BigInteger bigInteger) {
            if (bigInteger.testBit(0)) {
                bigInteger = this.f26100a.subtract(bigInteger);
            }
            return bigInteger.shiftRight(1);
        }

        /* renamed from: c */
        protected BigInteger m879c(BigInteger bigInteger, BigInteger bigInteger2) {
            BigInteger subtract = bigInteger.subtract(bigInteger2);
            return subtract.signum() < 0 ? subtract.add(this.f26100a) : subtract;
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: c */
        public AbstractC12856e mo881c() {
            BigInteger add = this.f26102i.add(InterfaceC12849c.f26070d);
            if (add.compareTo(this.f26100a) == 0) {
                add = InterfaceC12849c.f26069c;
            }
            return new C12858b(this.f26100a, this.f26101b, add);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: c */
        public AbstractC12856e mo878c(AbstractC12856e abstractC12856e) {
            return new C12858b(this.f26100a, this.f26101b, m884b(this.f26102i, abstractC12856e.mo893a()));
        }

        /* renamed from: d */
        protected BigInteger m876d(BigInteger bigInteger) {
            int mo886b = mo886b();
            int i = (mo886b + 31) >> 5;
            int[] m707a = AbstractC12891c.m707a(mo886b, this.f26100a);
            int[] m707a2 = AbstractC12891c.m707a(mo886b, bigInteger);
            int[] m712a = AbstractC12891c.m712a(i);
            AbstractC12890b.m713a(m707a, m707a2, m712a);
            return AbstractC12891c.m671f(i, m712a);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: d */
        public AbstractC12856e mo877d() {
            if (this.f26102i.signum() == 0) {
                return this;
            }
            BigInteger bigInteger = this.f26100a;
            return new C12858b(bigInteger, this.f26101b, bigInteger.subtract(this.f26102i));
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: d */
        public AbstractC12856e mo875d(AbstractC12856e abstractC12856e) {
            return new C12858b(this.f26100a, this.f26101b, m884b(this.f26102i, m876d(abstractC12856e.mo893a())));
        }

        /* renamed from: e */
        protected BigInteger m873e(BigInteger bigInteger) {
            if (this.f26101b != null) {
                boolean z = bigInteger.signum() < 0;
                if (z) {
                    bigInteger = bigInteger.abs();
                }
                int bitLength = this.f26100a.bitLength();
                boolean equals = this.f26101b.equals(InterfaceC12849c.f26070d);
                while (bigInteger.bitLength() > bitLength + 1) {
                    BigInteger shiftRight = bigInteger.shiftRight(bitLength);
                    BigInteger subtract = bigInteger.subtract(shiftRight.shiftLeft(bitLength));
                    if (!equals) {
                        shiftRight = shiftRight.multiply(this.f26101b);
                    }
                    bigInteger = shiftRight.add(subtract);
                }
                while (bigInteger.compareTo(this.f26100a) >= 0) {
                    bigInteger = bigInteger.subtract(this.f26100a);
                }
                return (!z || bigInteger.signum() == 0) ? bigInteger : this.f26100a.subtract(bigInteger);
            }
            return bigInteger.mod(this.f26100a);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: e */
        public AbstractC12856e mo874e() {
            BigInteger bigInteger = this.f26100a;
            BigInteger bigInteger2 = this.f26101b;
            BigInteger bigInteger3 = this.f26102i;
            return new C12858b(bigInteger, bigInteger2, m884b(bigInteger3, bigInteger3));
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof C12858b) {
                C12858b c12858b = (C12858b) obj;
                return this.f26100a.equals(c12858b.f26100a) && this.f26102i.equals(c12858b.f26102i);
            }
            return false;
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: f */
        public AbstractC12856e mo871f() {
            return new C12858b(this.f26100a, this.f26101b, m876d(this.f26102i));
        }

        @Override // org.p415a.p436e.p437a.AbstractC12856e
        /* renamed from: g */
        public AbstractC12856e mo870g() {
            if (mo895j() || mo896i()) {
                return this;
            }
            if (!this.f26100a.testBit(0)) {
                throw new RuntimeException("not done yet");
            }
            if (this.f26100a.testBit(1)) {
                BigInteger add = this.f26100a.shiftRight(2).add(InterfaceC12849c.f26070d);
                BigInteger bigInteger = this.f26100a;
                return m872e(new C12858b(bigInteger, this.f26101b, this.f26102i.modPow(add, bigInteger)));
            } else if (this.f26100a.testBit(2)) {
                BigInteger modPow = this.f26102i.modPow(this.f26100a.shiftRight(3), this.f26100a);
                BigInteger m884b = m884b(modPow, this.f26102i);
                if (m884b(m884b, modPow).equals(InterfaceC12849c.f26070d)) {
                    return m872e(new C12858b(this.f26100a, this.f26101b, m884b));
                }
                return m872e(new C12858b(this.f26100a, this.f26101b, m884b(m884b, InterfaceC12849c.f26071e.modPow(this.f26100a.shiftRight(2), this.f26100a))));
            } else {
                BigInteger shiftRight = this.f26100a.shiftRight(1);
                if (!this.f26102i.modPow(shiftRight, this.f26100a).equals(InterfaceC12849c.f26070d)) {
                    return null;
                }
                BigInteger bigInteger2 = this.f26102i;
                BigInteger m885b = m885b(m885b(bigInteger2));
                BigInteger add2 = shiftRight.add(InterfaceC12849c.f26070d);
                BigInteger subtract = this.f26100a.subtract(InterfaceC12849c.f26070d);
                Random random = new Random();
                while (true) {
                    BigInteger bigInteger3 = new BigInteger(this.f26100a.bitLength(), random);
                    if (bigInteger3.compareTo(this.f26100a) < 0 && m873e(bigInteger3.multiply(bigInteger3).subtract(m885b)).modPow(shiftRight, this.f26100a).equals(subtract)) {
                        BigInteger[] m890a = m890a(bigInteger3, bigInteger2, add2);
                        BigInteger bigInteger4 = m890a[0];
                        BigInteger bigInteger5 = m890a[1];
                        if (m884b(bigInteger5, bigInteger5).equals(m885b)) {
                            return new C12858b(this.f26100a, this.f26101b, m880c(bigInteger5));
                        }
                        if (!bigInteger4.equals(InterfaceC12849c.f26070d) && !bigInteger4.equals(subtract)) {
                            return null;
                        }
                    }
                }
            }
        }

        public int hashCode() {
            return this.f26100a.hashCode() ^ this.f26102i.hashCode();
        }
    }

    /* renamed from: a */
    public abstract BigInteger mo893a();

    /* renamed from: a */
    public AbstractC12856e mo899a(int i) {
        AbstractC12856e abstractC12856e = this;
        for (int i2 = 0; i2 < i; i2++) {
            abstractC12856e = abstractC12856e.mo874e();
        }
        return abstractC12856e;
    }

    /* renamed from: a */
    public abstract AbstractC12856e mo889a(AbstractC12856e abstractC12856e);

    /* renamed from: a */
    public AbstractC12856e mo888a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
        return mo874e().mo889a(abstractC12856e.mo878c(abstractC12856e2));
    }

    /* renamed from: a */
    public AbstractC12856e mo887a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e abstractC12856e3) {
        return mo878c(abstractC12856e).mo883b(abstractC12856e2.mo878c(abstractC12856e3));
    }

    /* renamed from: b */
    public abstract int mo886b();

    /* renamed from: b */
    public abstract AbstractC12856e mo883b(AbstractC12856e abstractC12856e);

    /* renamed from: b */
    public AbstractC12856e mo882b(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e abstractC12856e3) {
        return mo878c(abstractC12856e).mo889a(abstractC12856e2.mo878c(abstractC12856e3));
    }

    /* renamed from: c */
    public abstract AbstractC12856e mo881c();

    /* renamed from: c */
    public abstract AbstractC12856e mo878c(AbstractC12856e abstractC12856e);

    /* renamed from: d */
    public abstract AbstractC12856e mo877d();

    /* renamed from: d */
    public abstract AbstractC12856e mo875d(AbstractC12856e abstractC12856e);

    /* renamed from: e */
    public abstract AbstractC12856e mo874e();

    /* renamed from: f */
    public abstract AbstractC12856e mo871f();

    /* renamed from: g */
    public abstract AbstractC12856e mo870g();

    /* renamed from: h */
    public int mo897h() {
        return mo893a().bitLength();
    }

    /* renamed from: i */
    public boolean mo896i() {
        return mo897h() == 1;
    }

    /* renamed from: j */
    public boolean mo895j() {
        return mo893a().signum() == 0;
    }

    /* renamed from: k */
    public boolean mo894k() {
        return mo893a().testBit(0);
    }

    /* renamed from: l */
    public byte[] m900l() {
        return C12966b.m409a((mo886b() + 7) / 8, mo893a());
    }

    public String toString() {
        return mo893a().toString(16);
    }
}
