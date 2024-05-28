package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0648c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.d.a.l */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0663l {

    /* renamed from: a */
    public static final BigInteger f2017a = InterfaceC0647b.f1977b.negate();

    /* renamed from: b */
    public static final BigInteger f2018b = InterfaceC0647b.f1978c.negate();

    /* renamed from: c */
    public static final BigInteger f2019c = InterfaceC0647b.f1979d.negate();

    /* renamed from: d */
    public static final byte f2020d = 4;

    /* renamed from: e */
    public static final byte f2021e = 16;

    /* renamed from: f */
    public static final C0668q[] f2022f;

    /* renamed from: g */
    public static final byte[][] f2023g;

    /* renamed from: h */
    public static final C0668q[] f2024h;

    /* renamed from: i */
    public static final byte[][] f2025i;

    static {
        BigInteger bigInteger = f2017a;
        f2022f = new C0668q[]{null, new C0668q(InterfaceC0647b.f1977b, InterfaceC0647b.f1976a), null, new C0668q(f2019c, f2017a), null, new C0668q(bigInteger, bigInteger), null, new C0668q(InterfaceC0647b.f1977b, f2017a), null};
        f2023g = new byte[][]{null, new byte[]{1}, null, new byte[]{-1, 0, 1}, null, new byte[]{1, 0, 1}, null, new byte[]{-1, 0, 0, 1}};
        BigInteger bigInteger2 = InterfaceC0647b.f1977b;
        f2024h = new C0668q[]{null, new C0668q(InterfaceC0647b.f1977b, InterfaceC0647b.f1976a), null, new C0668q(f2019c, InterfaceC0647b.f1977b), null, new C0668q(f2017a, InterfaceC0647b.f1977b), null, new C0668q(bigInteger2, bigInteger2), null};
        f2025i = new byte[][]{null, new byte[]{1}, null, new byte[]{-1, 0, 1}, null, new byte[]{1, 0, 1}, null, new byte[]{-1, 0, 0, -1}};
    }

    /* renamed from: a */
    public static BigInteger m22526a(byte b, C0668q c0668q) {
        BigInteger bigInteger = c0668q.f2029a;
        BigInteger multiply = bigInteger.multiply(bigInteger);
        BigInteger multiply2 = c0668q.f2029a.multiply(c0668q.f2030b);
        BigInteger bigInteger2 = c0668q.f2030b;
        BigInteger shiftLeft = bigInteger2.multiply(bigInteger2).shiftLeft(1);
        if (b == 1) {
            return multiply.add(multiply2).add(shiftLeft);
        }
        if (b == -1) {
            return multiply.subtract(multiply2).add(shiftLeft);
        }
        throw new IllegalArgumentException("mu must be 1 or -1");
    }

    /* renamed from: b */
    public static byte[] m22515b(byte b, C0668q c0668q) {
        BigInteger subtract;
        if (b != 1 && b != -1) {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
        int bitLength = m22526a(b, c0668q).bitLength();
        byte[] bArr = new byte[bitLength > 30 ? bitLength + 4 : 34];
        BigInteger bigInteger = c0668q.f2029a;
        BigInteger bigInteger2 = c0668q.f2030b;
        int i = 0;
        int i2 = 0;
        while (true) {
            if (bigInteger.equals(InterfaceC0647b.f1976a) && bigInteger2.equals(InterfaceC0647b.f1976a)) {
                int i3 = i + 1;
                byte[] bArr2 = new byte[i3];
                System.arraycopy(bArr, 0, bArr2, 0, i3);
                return bArr2;
            }
            if (bigInteger.testBit(0)) {
                bArr[i2] = (byte) InterfaceC0647b.f1978c.subtract(bigInteger.subtract(bigInteger2.shiftLeft(1)).mod(InterfaceC0647b.f1980e)).intValue();
                if (bArr[i2] == 1) {
                    bigInteger = bigInteger.clearBit(0);
                } else {
                    bigInteger = bigInteger.add(InterfaceC0647b.f1977b);
                }
                i = i2;
            } else {
                bArr[i2] = 0;
            }
            BigInteger shiftRight = bigInteger.shiftRight(1);
            if (b == 1) {
                subtract = bigInteger2.add(shiftRight);
            } else {
                subtract = bigInteger2.subtract(shiftRight);
            }
            BigInteger negate = bigInteger.shiftRight(1).negate();
            i2++;
            bigInteger = subtract;
            bigInteger2 = negate;
        }
    }

    /* renamed from: a */
    public static C0662k m22527a(byte b, C0662k c0662k, C0662k c0662k2) {
        C0662k m22536d = c0662k.m22536d(c0662k);
        C0662k m22536d2 = c0662k.m22536d(c0662k2);
        C0662k m22543b = c0662k2.m22536d(c0662k2).m22543b(1);
        if (b == 1) {
            return m22536d.m22547a(m22536d2).m22547a(m22543b);
        }
        if (b == -1) {
            return m22536d.m22533e(m22536d2).m22547a(m22543b);
        }
        throw new IllegalArgumentException("mu must be 1 or -1");
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0090, code lost:
        if (r7.m22541b(p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.C0663l.f2018b) < 0) goto L27;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.C0668q m22518a(p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.C0662k r7, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.C0662k r8, byte r9) {
        /*
            int r0 = r7.m22544b()
            int r1 = r8.m22544b()
            if (r1 != r0) goto Lac
            r0 = -1
            r1 = 1
            if (r9 == r1) goto L19
            if (r9 != r0) goto L11
            goto L19
        L11:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "mu must be 1 or -1"
            r7.<init>(r8)
            throw r7
        L19:
            java.math.BigInteger r2 = r7.m22531f()
            java.math.BigInteger r3 = r8.m22531f()
            a.a.a.a.a.e.a.d.a.k r7 = r7.m22532e(r2)
            a.a.a.a.a.e.a.d.a.k r8 = r8.m22532e(r3)
            a.a.a.a.a.e.a.d.a.k r4 = r7.m22547a(r7)
            if (r9 != r1) goto L34
            a.a.a.a.a.e.a.d.a.k r4 = r4.m22547a(r8)
            goto L38
        L34:
            a.a.a.a.a.e.a.d.a.k r4 = r4.m22533e(r8)
        L38:
            a.a.a.a.a.e.a.d.a.k r5 = r8.m22547a(r8)
            a.a.a.a.a.e.a.d.a.k r5 = r5.m22547a(r8)
            a.a.a.a.a.e.a.d.a.k r8 = r5.m22547a(r8)
            if (r9 != r1) goto L4f
            a.a.a.a.a.e.a.d.a.k r5 = r7.m22533e(r5)
            a.a.a.a.a.e.a.d.a.k r7 = r7.m22547a(r8)
            goto L57
        L4f:
            a.a.a.a.a.e.a.d.a.k r5 = r7.m22547a(r5)
            a.a.a.a.a.e.a.d.a.k r7 = r7.m22533e(r8)
        L57:
            java.math.BigInteger r8 = p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.InterfaceC0647b.f1977b
            int r8 = r4.m22541b(r8)
            r6 = 0
            if (r8 < 0) goto L6c
            java.math.BigInteger r8 = p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.C0663l.f2017a
            int r8 = r5.m22541b(r8)
            if (r8 >= 0) goto L69
            goto L74
        L69:
            r8 = r6
            r6 = r1
            goto L77
        L6c:
            java.math.BigInteger r8 = p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.InterfaceC0647b.f1978c
            int r8 = r7.m22541b(r8)
            if (r8 < 0) goto L76
        L74:
            r8 = r9
            goto L77
        L76:
            r8 = r6
        L77:
            java.math.BigInteger r1 = p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.C0663l.f2017a
            int r1 = r4.m22541b(r1)
            if (r1 >= 0) goto L8a
            java.math.BigInteger r7 = p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.InterfaceC0647b.f1977b
            int r7 = r5.m22541b(r7)
            if (r7 < 0) goto L88
            goto L92
        L88:
            r6 = r0
            goto L94
        L8a:
            java.math.BigInteger r0 = p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.C0663l.f2018b
            int r7 = r7.m22541b(r0)
            if (r7 >= 0) goto L94
        L92:
            int r7 = -r9
            byte r8 = (byte) r7
        L94:
            long r0 = (long) r6
            java.math.BigInteger r7 = java.math.BigInteger.valueOf(r0)
            java.math.BigInteger r7 = r2.add(r7)
            long r8 = (long) r8
            java.math.BigInteger r8 = java.math.BigInteger.valueOf(r8)
            java.math.BigInteger r8 = r3.add(r8)
            a.a.a.a.a.e.a.d.a.q r9 = new a.a.a.a.a.e.a.d.a.q
            r9.<init>(r7, r8)
            return r9
        Lac:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "lambda0 and lambda1 do not have same scale"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.C0663l.m22518a(a.a.a.a.a.e.a.d.a.k, a.a.a.a.a.e.a.d.a.k, byte):a.a.a.a.a.e.a.d.a.q");
    }

    /* renamed from: b */
    public static BigInteger[] m22514b(AbstractC0648c.C0649a c0649a) {
        BigInteger add;
        BigInteger add2;
        if (c0649a.m22609m()) {
            int m22613i = c0649a.m22613i();
            int intValue = c0649a.m22622a().mo22584g().intValue();
            byte m22612j = c0649a.m22612j();
            int intValue2 = c0649a.m22617e().intValue();
            BigInteger[] m22528a = m22528a(m22612j, (m22613i + 3) - intValue, false);
            if (m22612j == 1) {
                add = InterfaceC0647b.f1977b.subtract(m22528a[1]);
                add2 = InterfaceC0647b.f1977b.subtract(m22528a[0]);
            } else if (m22612j == -1) {
                add = InterfaceC0647b.f1977b.add(m22528a[1]);
                add2 = InterfaceC0647b.f1977b.add(m22528a[0]);
            } else {
                throw new IllegalArgumentException("mu must be 1 or -1");
            }
            BigInteger[] bigIntegerArr = new BigInteger[2];
            if (intValue2 == 2) {
                bigIntegerArr[0] = add.shiftRight(1);
                bigIntegerArr[1] = add2.shiftRight(1).negate();
            } else if (intValue2 == 4) {
                bigIntegerArr[0] = add.shiftRight(2);
                bigIntegerArr[1] = add2.shiftRight(2).negate();
            } else {
                throw new IllegalArgumentException("h (Cofactor) must be 2 or 4");
            }
            return bigIntegerArr;
        }
        throw new IllegalArgumentException("si is defined for Koblitz curves only");
    }

    /* renamed from: a */
    public static C0662k m22516a(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, byte b, int i, int i2) {
        int i3 = ((i + 5) / 2) + i2;
        BigInteger multiply = bigInteger2.multiply(bigInteger.shiftRight(((i - i3) - 2) + b));
        BigInteger add = multiply.add(bigInteger3.multiply(multiply.shiftRight(i)));
        int i4 = i3 - i2;
        BigInteger shiftRight = add.shiftRight(i4);
        if (add.testBit(i4 - 1)) {
            shiftRight = shiftRight.add(InterfaceC0647b.f1977b);
        }
        return new C0662k(shiftRight, i2);
    }

    /* renamed from: a */
    public static AbstractC0655f.C0656a m22523a(AbstractC0655f.C0656a c0656a) {
        if (c0656a.m22575g()) {
            return c0656a;
        }
        return new AbstractC0655f.C0656a(c0656a.m22580b(), c0656a.m22578d().mo22585f(), c0656a.m22577e().mo22585f(), c0656a.m22576f());
    }

    /* renamed from: a */
    public static byte m22524a(AbstractC0648c.C0649a c0649a) {
        BigInteger mo22584g = c0649a.m22622a().mo22584g();
        if (mo22584g.equals(InterfaceC0647b.f1976a)) {
            return (byte) -1;
        }
        if (mo22584g.equals(InterfaceC0647b.f1977b)) {
            return (byte) 1;
        }
        throw new IllegalArgumentException("No Koblitz curve (ABC), TNAF multiplication not possible");
    }

    /* renamed from: a */
    public static BigInteger[] m22528a(byte b, int i, boolean z) {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        if (b == 1 || b == -1) {
            if (z) {
                bigInteger = InterfaceC0647b.f1978c;
                bigInteger2 = BigInteger.valueOf(b);
            } else {
                bigInteger = InterfaceC0647b.f1976a;
                bigInteger2 = InterfaceC0647b.f1977b;
            }
            BigInteger bigInteger3 = bigInteger2;
            BigInteger bigInteger4 = bigInteger;
            int i2 = 1;
            while (i2 < i) {
                i2++;
                BigInteger bigInteger5 = bigInteger3;
                bigInteger3 = (b == 1 ? bigInteger3 : bigInteger3.negate()).subtract(bigInteger4.shiftLeft(1));
                bigInteger4 = bigInteger5;
            }
            return new BigInteger[]{bigInteger4, bigInteger3};
        }
        throw new IllegalArgumentException("mu must be 1 or -1");
    }

    /* renamed from: a */
    public static BigInteger m22529a(byte b, int i) {
        if (i == 4) {
            if (b == 1) {
                return BigInteger.valueOf(6L);
            }
            return BigInteger.valueOf(10L);
        }
        BigInteger[] m22528a = m22528a(b, i, false);
        BigInteger bit = InterfaceC0647b.f1976a.setBit(i);
        return InterfaceC0647b.f1978c.multiply(m22528a[0]).multiply(m22528a[1].modInverse(bit)).mod(bit);
    }

    /* renamed from: a */
    public static C0668q m22517a(BigInteger bigInteger, int i, byte b, BigInteger[] bigIntegerArr, byte b2, byte b3) {
        BigInteger subtract;
        if (b2 == 1) {
            subtract = bigIntegerArr[0].add(bigIntegerArr[1]);
        } else {
            subtract = bigIntegerArr[0].subtract(bigIntegerArr[1]);
        }
        BigInteger bigInteger2 = m22528a(b2, i, true)[1];
        C0668q m22518a = m22518a(m22516a(bigInteger, bigIntegerArr[0], bigInteger2, b, i, b3), m22516a(bigInteger, bigIntegerArr[1], bigInteger2, b, i, b3), b2);
        return new C0668q(bigInteger.subtract(subtract.multiply(m22518a.f2029a)).subtract(BigInteger.valueOf(2L).multiply(bigIntegerArr[1]).multiply(m22518a.f2030b)), bigIntegerArr[1].multiply(m22518a.f2029a).subtract(bigIntegerArr[0].multiply(m22518a.f2030b)));
    }

    /* renamed from: a */
    public static AbstractC0655f.C0656a m22520a(AbstractC0655f.C0656a c0656a, BigInteger bigInteger) {
        AbstractC0648c.C0649a c0649a = (AbstractC0648c.C0649a) c0656a.m22580b();
        return m22521a(c0656a, m22517a(bigInteger, c0649a.m22613i(), (byte) c0649a.m22622a().mo22584g().intValue(), c0649a.m22610l(), c0649a.m22612j(), (byte) 10));
    }

    /* renamed from: a */
    public static AbstractC0655f.C0656a m22521a(AbstractC0655f.C0656a c0656a, C0668q c0668q) {
        return m22519a(c0656a, m22515b(((AbstractC0648c.C0649a) c0656a.m22580b()).m22612j(), c0668q));
    }

    /* renamed from: a */
    public static AbstractC0655f.C0656a m22519a(AbstractC0655f.C0656a c0656a, byte[] bArr) {
        AbstractC0655f.C0656a c0656a2 = (AbstractC0655f.C0656a) ((AbstractC0648c.C0649a) c0656a.m22580b()).mo22603d();
        for (int length = bArr.length - 1; length >= 0; length--) {
            c0656a2 = m22523a(c0656a2);
            if (bArr[length] == 1) {
                c0656a2 = c0656a2.m22573a(c0656a);
            } else if (bArr[length] == -1) {
                c0656a2 = c0656a2.m22571b(c0656a);
            }
        }
        return c0656a2;
    }

    /* renamed from: a */
    public static byte[] m22525a(byte b, C0668q c0668q, byte b2, BigInteger bigInteger, BigInteger bigInteger2, C0668q[] c0668qArr) {
        BigInteger subtract;
        byte intValue;
        byte b3;
        boolean z;
        if (b != 1 && b != -1) {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
        int bitLength = m22526a(b, c0668q).bitLength();
        byte[] bArr = new byte[bitLength > 30 ? bitLength + 4 + b2 : b2 + 34];
        BigInteger shiftRight = bigInteger.shiftRight(1);
        BigInteger bigInteger3 = c0668q.f2029a;
        BigInteger bigInteger4 = c0668q.f2030b;
        int i = 0;
        while (true) {
            if (bigInteger3.equals(InterfaceC0647b.f1976a) && bigInteger4.equals(InterfaceC0647b.f1976a)) {
                return bArr;
            }
            if (bigInteger3.testBit(0)) {
                BigInteger mod = bigInteger3.add(bigInteger4.multiply(bigInteger2)).mod(bigInteger);
                if (mod.compareTo(shiftRight) >= 0) {
                    intValue = (byte) mod.subtract(bigInteger).intValue();
                } else {
                    intValue = (byte) mod.intValue();
                }
                bArr[i] = intValue;
                if (intValue < 0) {
                    b3 = (byte) (-intValue);
                    z = false;
                } else {
                    b3 = intValue;
                    z = true;
                }
                if (z) {
                    bigInteger3 = bigInteger3.subtract(c0668qArr[b3].f2029a);
                    bigInteger4 = bigInteger4.subtract(c0668qArr[b3].f2030b);
                } else {
                    bigInteger3 = bigInteger3.add(c0668qArr[b3].f2029a);
                    bigInteger4 = bigInteger4.add(c0668qArr[b3].f2030b);
                }
            } else {
                bArr[i] = 0;
            }
            if (b == 1) {
                subtract = bigInteger4.add(bigInteger3.shiftRight(1));
            } else {
                subtract = bigInteger4.subtract(bigInteger3.shiftRight(1));
            }
            BigInteger negate = bigInteger3.shiftRight(1).negate();
            i++;
            bigInteger3 = subtract;
            bigInteger4 = negate;
        }
    }

    /* renamed from: a */
    public static AbstractC0655f.C0656a[] m22522a(AbstractC0655f.C0656a c0656a, byte b) {
        byte[][] bArr;
        AbstractC0655f.C0656a[] c0656aArr = new AbstractC0655f.C0656a[16];
        c0656aArr[1] = c0656a;
        if (b == 0) {
            bArr = f2023g;
        } else {
            bArr = f2025i;
        }
        int length = bArr.length;
        for (int i = 3; i < length; i += 2) {
            c0656aArr[i] = m22519a(c0656a, bArr[i]);
        }
        return c0656aArr;
    }
}
