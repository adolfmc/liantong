package org.p415a.p436e.p437a;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.t */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12877t {

    /* renamed from: a */
    private static final int[] f26135a = {13, 41, 121, 337, 897, 2305};

    /* renamed from: b */
    private static final byte[] f26136b = new byte[0];

    /* renamed from: c */
    private static final int[] f26137c = new int[0];

    /* renamed from: d */
    private static final AbstractC12860g[] f26138d = new AbstractC12860g[0];

    /* renamed from: a */
    public static int m748a(int i) {
        return m746a(i, f26135a);
    }

    /* renamed from: a */
    public static int m746a(int i, int[] iArr) {
        int i2 = 0;
        while (i2 < iArr.length && i >= iArr[i2]) {
            i2++;
        }
        return i2 + 2;
    }

    /* renamed from: a */
    public static AbstractC12860g m742a(AbstractC12860g abstractC12860g, int i, boolean z, InterfaceC12865h interfaceC12865h) {
        AbstractC12850d m861c = abstractC12860g.m861c();
        C12876s m743a = m743a(abstractC12860g, i, z);
        AbstractC12860g mo778a = interfaceC12865h.mo778a(abstractC12860g);
        C12876s m741a = m741a(m861c.m933a(mo778a, "bc_wnaf"));
        AbstractC12860g m749c = m743a.m749c();
        if (m749c != null) {
            m741a.m753a(interfaceC12865h.mo778a(m749c));
        }
        AbstractC12860g[] m754a = m743a.m754a();
        AbstractC12860g[] abstractC12860gArr = new AbstractC12860g[m754a.length];
        for (int i2 = 0; i2 < m754a.length; i2++) {
            abstractC12860gArr[i2] = interfaceC12865h.mo778a(m754a[i2]);
        }
        m741a.m752a(abstractC12860gArr);
        if (z) {
            AbstractC12860g[] abstractC12860gArr2 = new AbstractC12860g[abstractC12860gArr.length];
            for (int i3 = 0; i3 < abstractC12860gArr2.length; i3++) {
                abstractC12860gArr2[i3] = abstractC12860gArr[i3].mo832q();
            }
            m741a.m750b(abstractC12860gArr2);
        }
        m861c.m932a(mo778a, "bc_wnaf", m741a);
        return mo778a;
    }

    /* renamed from: a */
    public static C12876s m744a(AbstractC12860g abstractC12860g) {
        return m741a(abstractC12860g.m861c().m933a(abstractC12860g, "bc_wnaf"));
    }

    /* renamed from: a */
    public static C12876s m743a(AbstractC12860g abstractC12860g, int i, boolean z) {
        int length;
        int i2;
        AbstractC12850d m861c = abstractC12860g.m861c();
        C12876s m741a = m741a(m861c.m933a(abstractC12860g, "bc_wnaf"));
        int i3 = 0;
        int max = 1 << Math.max(0, i - 2);
        AbstractC12860g[] m754a = m741a.m754a();
        if (m754a == null) {
            m754a = f26138d;
            length = 0;
        } else {
            length = m754a.length;
        }
        if (length < max) {
            m754a = m738a(m754a, max);
            if (max == 1) {
                m754a[0] = abstractC12860g.m852m();
            } else {
                if (length == 0) {
                    m754a[0] = abstractC12860g;
                    i2 = 1;
                } else {
                    i2 = length;
                }
                AbstractC12856e abstractC12856e = null;
                if (max == 2) {
                    m754a[1] = abstractC12860g.mo830s();
                } else {
                    AbstractC12860g m749c = m741a.m749c();
                    AbstractC12860g abstractC12860g2 = m754a[i2 - 1];
                    if (m749c == null) {
                        m749c = m754a[0].mo831r();
                        m741a.m753a(m749c);
                        if (!m749c.m851n() && C12844b.m951b(m861c) && m861c.mo908a() >= 64) {
                            switch (m861c.m919k()) {
                                case 2:
                                case 3:
                                case 4:
                                    abstractC12856e = m749c.mo842a(0);
                                    m749c = m861c.m926b(m749c.m858f().mo893a(), m749c.mo844g().mo893a());
                                    AbstractC12856e mo874e = abstractC12856e.mo874e();
                                    abstractC12860g2 = abstractC12860g2.mo849b(mo874e).mo847c(mo874e.mo878c(abstractC12856e));
                                    if (length == 0) {
                                        m754a[0] = abstractC12860g2;
                                        break;
                                    }
                                    break;
                            }
                        }
                    }
                    while (i2 < max) {
                        abstractC12860g2 = abstractC12860g2.mo839b(m749c);
                        m754a[i2] = abstractC12860g2;
                        i2++;
                    }
                }
                m861c.m928a(m754a, length, max - length, abstractC12856e);
            }
        }
        m741a.m752a(m754a);
        if (z) {
            AbstractC12860g[] m751b = m741a.m751b();
            if (m751b == null) {
                m751b = new AbstractC12860g[max];
            } else {
                i3 = m751b.length;
                if (i3 < max) {
                    m751b = m738a(m751b, max);
                }
            }
            while (i3 < max) {
                m751b[i3] = m754a[i3].mo832q();
                i3++;
            }
            m741a.m750b(m751b);
        }
        m861c.m932a(abstractC12860g, "bc_wnaf", m741a);
        return m741a;
    }

    /* renamed from: a */
    public static C12876s m741a(InterfaceC12871n interfaceC12871n) {
        return (interfaceC12871n == null || !(interfaceC12871n instanceof C12876s)) ? new C12876s() : (C12876s) interfaceC12871n;
    }

    /* renamed from: a */
    private static byte[] m740a(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        return bArr2;
    }

    /* renamed from: a */
    public static int[] m747a(int i, BigInteger bigInteger) {
        if (i == 2) {
            return m745a(bigInteger);
        }
        if (i < 2 || i > 16) {
            throw new IllegalArgumentException("'width' must be in the range [2, 16]");
        }
        if ((bigInteger.bitLength() >>> 16) == 0) {
            if (bigInteger.signum() == 0) {
                return f26137c;
            }
            int[] iArr = new int[(bigInteger.bitLength() / i) + 1];
            int i2 = 1 << i;
            int i3 = i2 - 1;
            int i4 = i2 >>> 1;
            BigInteger bigInteger2 = bigInteger;
            int i5 = 0;
            int i6 = 0;
            boolean z = false;
            while (i5 <= bigInteger2.bitLength()) {
                if (bigInteger2.testBit(i5) == z) {
                    i5++;
                } else {
                    bigInteger2 = bigInteger2.shiftRight(i5);
                    int intValue = bigInteger2.intValue() & i3;
                    if (z) {
                        intValue++;
                    }
                    z = (intValue & i4) != 0;
                    if (z) {
                        intValue -= i2;
                    }
                    if (i6 > 0) {
                        i5--;
                    }
                    iArr[i6] = i5 | (intValue << 16);
                    i5 = i;
                    i6++;
                }
            }
            return iArr.length > i6 ? m739a(iArr, i6) : iArr;
        }
        throw new IllegalArgumentException("'k' must have bitlength < 2^16");
    }

    /* renamed from: a */
    public static int[] m745a(BigInteger bigInteger) {
        if ((bigInteger.bitLength() >>> 16) == 0) {
            if (bigInteger.signum() == 0) {
                return f26137c;
            }
            BigInteger add = bigInteger.shiftLeft(1).add(bigInteger);
            int bitLength = add.bitLength();
            int[] iArr = new int[bitLength >> 1];
            BigInteger xor = add.xor(bigInteger);
            int i = bitLength - 1;
            int i2 = 0;
            int i3 = 0;
            int i4 = 1;
            while (i4 < i) {
                if (xor.testBit(i4)) {
                    iArr[i2] = i3 | ((bigInteger.testBit(i4) ? -1 : 1) << 16);
                    i4++;
                    i3 = 1;
                    i2++;
                } else {
                    i3++;
                }
                i4++;
            }
            int i5 = i2 + 1;
            iArr[i2] = 65536 | i3;
            return iArr.length > i5 ? m739a(iArr, i5) : iArr;
        }
        throw new IllegalArgumentException("'k' must have bitlength < 2^16");
    }

    /* renamed from: a */
    private static int[] m739a(int[] iArr, int i) {
        int[] iArr2 = new int[i];
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
        return iArr2;
    }

    /* renamed from: a */
    private static AbstractC12860g[] m738a(AbstractC12860g[] abstractC12860gArr, int i) {
        AbstractC12860g[] abstractC12860gArr2 = new AbstractC12860g[i];
        System.arraycopy(abstractC12860gArr, 0, abstractC12860gArr2, 0, abstractC12860gArr.length);
        return abstractC12860gArr2;
    }

    /* renamed from: b */
    public static byte[] m737b(int i, BigInteger bigInteger) {
        if (i == 2) {
            return m736b(bigInteger);
        }
        if (i < 2 || i > 8) {
            throw new IllegalArgumentException("'width' must be in the range [2, 8]");
        }
        if (bigInteger.signum() == 0) {
            return f26136b;
        }
        byte[] bArr = new byte[bigInteger.bitLength() + 1];
        int i2 = 1 << i;
        int i3 = i2 - 1;
        int i4 = i2 >>> 1;
        BigInteger bigInteger2 = bigInteger;
        int i5 = 0;
        int i6 = 0;
        boolean z = false;
        while (i5 <= bigInteger2.bitLength()) {
            if (bigInteger2.testBit(i5) == z) {
                i5++;
            } else {
                bigInteger2 = bigInteger2.shiftRight(i5);
                int intValue = bigInteger2.intValue() & i3;
                if (z) {
                    intValue++;
                }
                z = (intValue & i4) != 0;
                if (z) {
                    intValue -= i2;
                }
                if (i6 > 0) {
                    i5--;
                }
                int i7 = i6 + i5;
                bArr[i7] = (byte) intValue;
                i6 = i7 + 1;
                i5 = i;
            }
        }
        return bArr.length > i6 ? m740a(bArr, i6) : bArr;
    }

    /* renamed from: b */
    public static byte[] m736b(BigInteger bigInteger) {
        if (bigInteger.signum() == 0) {
            return f26136b;
        }
        BigInteger add = bigInteger.shiftLeft(1).add(bigInteger);
        int bitLength = add.bitLength() - 1;
        byte[] bArr = new byte[bitLength];
        BigInteger xor = add.xor(bigInteger);
        int i = 1;
        while (i < bitLength) {
            if (xor.testBit(i)) {
                bArr[i - 1] = (byte) (bigInteger.testBit(i) ? -1 : 1);
                i++;
            }
            i++;
        }
        bArr[bitLength - 1] = 1;
        return bArr;
    }

    /* renamed from: c */
    public static int m735c(BigInteger bigInteger) {
        if (bigInteger.signum() == 0) {
            return 0;
        }
        return bigInteger.shiftLeft(1).add(bigInteger).xor(bigInteger).bitCount();
    }
}
