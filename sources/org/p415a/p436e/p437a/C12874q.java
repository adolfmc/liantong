package org.p415a.p436e.p437a;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p436e.p437a.AbstractC12860g;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.q */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12874q {

    /* renamed from: a */
    public static final C12880w[] f26125a;

    /* renamed from: b */
    public static final byte[][] f26126b;

    /* renamed from: c */
    public static final C12880w[] f26127c;

    /* renamed from: d */
    public static final byte[][] f26128d;

    /* renamed from: e */
    private static final BigInteger f26129e = InterfaceC12849c.f26070d.negate();

    /* renamed from: f */
    private static final BigInteger f26130f = InterfaceC12849c.f26071e.negate();

    /* renamed from: g */
    private static final BigInteger f26131g = InterfaceC12849c.f26072f.negate();

    static {
        BigInteger bigInteger = f26129e;
        f26125a = new C12880w[]{null, new C12880w(InterfaceC12849c.f26070d, InterfaceC12849c.f26069c), null, new C12880w(f26131g, f26129e), null, new C12880w(bigInteger, bigInteger), null, new C12880w(InterfaceC12849c.f26070d, f26129e), null};
        f26126b = new byte[][]{null, new byte[]{1}, null, new byte[]{-1, 0, 1}, null, new byte[]{1, 0, 1}, null, new byte[]{-1, 0, 0, 1}};
        f26127c = new C12880w[]{null, new C12880w(InterfaceC12849c.f26070d, InterfaceC12849c.f26069c), null, new C12880w(f26131g, InterfaceC12849c.f26070d), null, new C12880w(f26129e, InterfaceC12849c.f26070d), null, new C12880w(InterfaceC12849c.f26070d, InterfaceC12849c.f26070d), null};
        f26128d = new byte[][]{null, new byte[]{1}, null, new byte[]{-1, 0, 1}, null, new byte[]{1, 0, 1}, null, new byte[]{-1, 0, 0, -1}};
    }

    /* renamed from: a */
    public static byte m763a(int i) {
        return (byte) (i == 0 ? -1 : 1);
    }

    /* renamed from: a */
    protected static int m762a(BigInteger bigInteger) {
        if (bigInteger != null) {
            if (bigInteger.equals(InterfaceC12849c.f26071e)) {
                return 1;
            }
            if (bigInteger.equals(InterfaceC12849c.f26073g)) {
                return 2;
            }
        }
        throw new IllegalArgumentException("h (Cofactor) must be 2 or 4");
    }

    /* renamed from: a */
    public static BigInteger m767a(byte b, int i) {
        if (i == 4) {
            return b == 1 ? BigInteger.valueOf(6L) : BigInteger.valueOf(10L);
        }
        BigInteger[] m766a = m766a(b, i, false);
        BigInteger bit = InterfaceC12849c.f26069c.setBit(i);
        return InterfaceC12849c.f26071e.multiply(m766a[0]).multiply(m766a[1].modInverse(bit)).mod(bit);
    }

    /* renamed from: a */
    public static BigInteger m765a(byte b, C12880w c12880w) {
        BigInteger subtract;
        BigInteger multiply = c12880w.f26140a.multiply(c12880w.f26140a);
        BigInteger multiply2 = c12880w.f26140a.multiply(c12880w.f26141b);
        BigInteger shiftLeft = c12880w.f26141b.multiply(c12880w.f26141b).shiftLeft(1);
        if (b == 1) {
            subtract = multiply.add(multiply2);
        } else if (b != -1) {
            throw new IllegalArgumentException("mu must be 1 or -1");
        } else {
            subtract = multiply.subtract(multiply2);
        }
        return subtract.add(shiftLeft);
    }

    /* renamed from: a */
    public static AbstractC12860g.AbstractC12861a m757a(AbstractC12860g.AbstractC12861a abstractC12861a, byte[] bArr) {
        AbstractC12860g.AbstractC12861a abstractC12861a2 = (AbstractC12860g.AbstractC12861a) abstractC12861a.mo832q();
        AbstractC12860g.AbstractC12861a abstractC12861a3 = (AbstractC12860g.AbstractC12861a) abstractC12861a.m861c().mo901e();
        int i = 0;
        for (int length = bArr.length - 1; length >= 0; length--) {
            i++;
            byte b = bArr[length];
            if (b != 0) {
                abstractC12861a3 = (AbstractC12860g.AbstractC12861a) abstractC12861a3.m848c(i).mo839b(b > 0 ? abstractC12861a : abstractC12861a2);
                i = 0;
            }
        }
        return i > 0 ? abstractC12861a3.m848c(i) : abstractC12861a3;
    }

    /* renamed from: a */
    public static C12873p m760a(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, byte b, int i, int i2) {
        int i3 = ((i + 5) / 2) + i2;
        BigInteger multiply = bigInteger2.multiply(bigInteger.shiftRight(((i - i3) - 2) + b));
        BigInteger add = multiply.add(bigInteger3.multiply(multiply.shiftRight(i)));
        int i4 = i3 - i2;
        BigInteger shiftRight = add.shiftRight(i4);
        if (add.testBit(i4 - 1)) {
            shiftRight = shiftRight.add(InterfaceC12849c.f26070d);
        }
        return new C12873p(shiftRight, i2);
    }

    /* renamed from: a */
    public static C12880w m761a(BigInteger bigInteger, int i, byte b, BigInteger[] bigIntegerArr, byte b2, byte b3) {
        BigInteger add = b2 == 1 ? bigIntegerArr[0].add(bigIntegerArr[1]) : bigIntegerArr[0].subtract(bigIntegerArr[1]);
        BigInteger bigInteger2 = m766a(b2, i, true)[1];
        C12880w m756a = m756a(m760a(bigInteger, bigIntegerArr[0], bigInteger2, b, i, b3), m760a(bigInteger, bigIntegerArr[1], bigInteger2, b, i, b3), b2);
        return new C12880w(bigInteger.subtract(add.multiply(m756a.f26140a)).subtract(BigInteger.valueOf(2L).multiply(bigIntegerArr[1]).multiply(m756a.f26141b)), bigIntegerArr[1].multiply(m756a.f26140a).subtract(bigIntegerArr[0].multiply(m756a.f26141b)));
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0090, code lost:
        if (r7.m772b(org.p415a.p436e.p437a.C12874q.f26130f) < 0) goto L27;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.p415a.p436e.p437a.C12880w m756a(org.p415a.p436e.p437a.C12873p r7, org.p415a.p436e.p437a.C12873p r8, byte r9) {
        /*
            int r0 = r7.m768d()
            int r1 = r8.m768d()
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
            java.math.BigInteger r2 = r7.m770c()
            java.math.BigInteger r3 = r8.m770c()
            org.a.e.a.p r7 = r7.m775a(r2)
            org.a.e.a.p r8 = r8.m775a(r3)
            org.a.e.a.p r4 = r7.m774a(r7)
            if (r9 != r1) goto L34
            org.a.e.a.p r4 = r4.m774a(r8)
            goto L38
        L34:
            org.a.e.a.p r4 = r4.m771b(r8)
        L38:
            org.a.e.a.p r5 = r8.m774a(r8)
            org.a.e.a.p r5 = r5.m774a(r8)
            org.a.e.a.p r8 = r5.m774a(r8)
            if (r9 != r1) goto L4f
            org.a.e.a.p r5 = r7.m771b(r5)
            org.a.e.a.p r7 = r7.m774a(r8)
            goto L57
        L4f:
            org.a.e.a.p r5 = r7.m774a(r5)
            org.a.e.a.p r7 = r7.m771b(r8)
        L57:
            java.math.BigInteger r8 = org.p415a.p436e.p437a.InterfaceC12849c.f26070d
            int r8 = r4.m772b(r8)
            r6 = 0
            if (r8 < 0) goto L6c
            java.math.BigInteger r8 = org.p415a.p436e.p437a.C12874q.f26129e
            int r8 = r5.m772b(r8)
            if (r8 >= 0) goto L69
            goto L74
        L69:
            r8 = r6
            r6 = r1
            goto L77
        L6c:
            java.math.BigInteger r8 = org.p415a.p436e.p437a.InterfaceC12849c.f26071e
            int r8 = r7.m772b(r8)
            if (r8 < 0) goto L76
        L74:
            r8 = r9
            goto L77
        L76:
            r8 = r6
        L77:
            java.math.BigInteger r1 = org.p415a.p436e.p437a.C12874q.f26129e
            int r1 = r4.m772b(r1)
            if (r1 >= 0) goto L8a
            java.math.BigInteger r7 = org.p415a.p436e.p437a.InterfaceC12849c.f26070d
            int r7 = r5.m772b(r7)
            if (r7 < 0) goto L88
            goto L92
        L88:
            r6 = r0
            goto L94
        L8a:
            java.math.BigInteger r0 = org.p415a.p436e.p437a.C12874q.f26130f
            int r7 = r7.m772b(r0)
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
            org.a.e.a.w r9 = new org.a.e.a.w
            r9.<init>(r7, r8)
            return r9
        Lac:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "lambda0 and lambda1 do not have same scale"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.p415a.p436e.p437a.C12874q.m756a(org.a.e.a.p, org.a.e.a.p, byte):org.a.e.a.w");
    }

    /* renamed from: a */
    public static byte[] m764a(byte b, C12880w c12880w, byte b2, BigInteger bigInteger, BigInteger bigInteger2, C12880w[] c12880wArr) {
        byte b3;
        boolean z;
        if (b != 1 && b != -1) {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
        int bitLength = m765a(b, c12880w).bitLength();
        byte[] bArr = new byte[bitLength > 30 ? bitLength + 4 + b2 : b2 + 34];
        BigInteger shiftRight = bigInteger.shiftRight(1);
        BigInteger bigInteger3 = c12880w.f26140a;
        BigInteger bigInteger4 = c12880w.f26141b;
        int i = 0;
        while (true) {
            if (bigInteger3.equals(InterfaceC12849c.f26069c) && bigInteger4.equals(InterfaceC12849c.f26069c)) {
                return bArr;
            }
            if (bigInteger3.testBit(0)) {
                BigInteger mod = bigInteger3.add(bigInteger4.multiply(bigInteger2)).mod(bigInteger);
                if (mod.compareTo(shiftRight) >= 0) {
                    mod = mod.subtract(bigInteger);
                }
                byte intValue = (byte) mod.intValue();
                bArr[i] = intValue;
                if (intValue < 0) {
                    b3 = (byte) (-intValue);
                    z = false;
                } else {
                    b3 = intValue;
                    z = true;
                }
                if (z) {
                    bigInteger3 = bigInteger3.subtract(c12880wArr[b3].f26140a);
                    bigInteger4 = bigInteger4.subtract(c12880wArr[b3].f26141b);
                } else {
                    bigInteger3 = bigInteger3.add(c12880wArr[b3].f26140a);
                    bigInteger4 = bigInteger4.add(c12880wArr[b3].f26141b);
                }
            } else {
                bArr[i] = 0;
            }
            BigInteger add = b == 1 ? bigInteger4.add(bigInteger3.shiftRight(1)) : bigInteger4.subtract(bigInteger3.shiftRight(1));
            BigInteger negate = bigInteger3.shiftRight(1).negate();
            i++;
            bigInteger3 = add;
            bigInteger4 = negate;
        }
    }

    /* renamed from: a */
    public static BigInteger[] m766a(byte b, int i, boolean z) {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        if (b == 1 || b == -1) {
            if (z) {
                bigInteger = InterfaceC12849c.f26071e;
                bigInteger2 = BigInteger.valueOf(b);
            } else {
                bigInteger = InterfaceC12849c.f26069c;
                bigInteger2 = InterfaceC12849c.f26070d;
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
    public static BigInteger[] m759a(AbstractC12850d.AbstractC12851a abstractC12851a) {
        if (abstractC12851a.mo913n()) {
            int a = abstractC12851a.mo908a();
            int intValue = abstractC12851a.m923g().mo893a().intValue();
            byte m763a = m763a(intValue);
            int m762a = m762a(abstractC12851a.m920j());
            BigInteger[] m766a = m766a(m763a, (a + 3) - intValue, false);
            if (m763a == 1) {
                m766a[0] = m766a[0].negate();
                m766a[1] = m766a[1].negate();
            }
            return new BigInteger[]{InterfaceC12849c.f26070d.add(m766a[1]).shiftRight(m762a), InterfaceC12849c.f26070d.add(m766a[0]).shiftRight(m762a).negate()};
        }
        throw new IllegalArgumentException("si is defined for Koblitz curves only");
    }

    /* renamed from: a */
    public static AbstractC12860g.AbstractC12861a[] m758a(AbstractC12860g.AbstractC12861a abstractC12861a, byte b) {
        byte[][] bArr = b == 0 ? f26126b : f26128d;
        AbstractC12860g.AbstractC12861a[] abstractC12861aArr = new AbstractC12860g.AbstractC12861a[(bArr.length + 1) >>> 1];
        abstractC12861aArr[0] = abstractC12861a;
        int length = bArr.length;
        for (int i = 3; i < length; i += 2) {
            abstractC12861aArr[i >>> 1] = m757a(abstractC12861a, bArr[i]);
        }
        abstractC12861a.m861c().m930a(abstractC12861aArr);
        return abstractC12861aArr;
    }
}
