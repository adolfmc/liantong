package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0669a;

/* renamed from: a.a.a.a.a.e.a.b.j1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0164j1 extends AbstractC0258r {

    /* renamed from: x3 */
    public static final long f203x3 = 72057594037927808L;

    /* renamed from: y3 */
    public static C0178n[][] f204y3 = new C0178n[256];

    /* renamed from: v3 */
    public String f205v3;

    /* renamed from: w3 */
    public byte[] f206w3;

    public C0164j1(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = true;
        long j = 0;
        BigInteger bigInteger = null;
        for (int i = 0; i != bArr.length; i++) {
            int i2 = bArr[i] & 255;
            if (j <= f203x3) {
                long j2 = j + (i2 & 127);
                if ((i2 & 128) == 0) {
                    if (z) {
                        if (j2 < 40) {
                            stringBuffer.append('0');
                        } else if (j2 < 80) {
                            stringBuffer.append('1');
                            j2 -= 40;
                        } else {
                            stringBuffer.append('2');
                            j2 -= 80;
                        }
                        z = false;
                    }
                    stringBuffer.append('.');
                    stringBuffer.append(j2);
                    j = 0;
                } else {
                    j = j2 << 7;
                }
            } else {
                BigInteger or = (bigInteger == null ? BigInteger.valueOf(j) : bigInteger).or(BigInteger.valueOf(i2 & 127));
                if ((i2 & 128) == 0) {
                    if (z) {
                        stringBuffer.append('2');
                        or = or.subtract(BigInteger.valueOf(80L));
                        z = false;
                    }
                    stringBuffer.append('.');
                    stringBuffer.append(or);
                    j = 0;
                    bigInteger = null;
                } else {
                    bigInteger = or.shiftLeft(7);
                }
            }
        }
        this.f205v3 = stringBuffer.toString();
        this.f206w3 = C0669a.m22503a(bArr);
    }

    /* renamed from: a */
    public static C0178n m24118a(Object obj) {
        if (obj != null && !(obj instanceof C0178n)) {
            if (obj instanceof C0164j1) {
                return new C0178n(((C0164j1) obj).m24113n());
            }
            if (obj instanceof InterfaceC0136d) {
                InterfaceC0136d interfaceC0136d = (InterfaceC0136d) obj;
                if (interfaceC0136d.mo23015d() instanceof C0178n) {
                    return (C0178n) interfaceC0136d.mo23015d();
                }
            }
            if (obj instanceof byte[]) {
                return m24115b((byte[]) obj);
            }
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        return (C0178n) obj;
    }

    /* renamed from: b */
    public static boolean m24116b(String str) {
        char charAt;
        if (str.length() < 3 || str.charAt(1) != '.' || (charAt = str.charAt(0)) < '0' || charAt > '2') {
            return false;
        }
        return m24117a(str, 2);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m
    public int hashCode() {
        return this.f205v3.hashCode();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: i */
    public int mo22977i() {
        int length = m24114m().length;
        return C0177m2.m24099a(length) + 1 + length;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: j */
    public boolean mo22976j() {
        return false;
    }

    /* renamed from: m */
    public synchronized byte[] m24114m() {
        if (this.f206w3 == null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            m24121a(byteArrayOutputStream);
            this.f206w3 = byteArrayOutputStream.toByteArray();
        }
        return this.f206w3;
    }

    /* renamed from: n */
    public String m24113n() {
        return this.f205v3;
    }

    public String toString() {
        return m24113n();
    }

    /* renamed from: b */
    public static C0178n m24115b(byte[] bArr) {
        if (bArr.length < 3) {
            return new C0178n(bArr);
        }
        int i = bArr[bArr.length - 2] & 255;
        int i2 = bArr[bArr.length - 1] & Byte.MAX_VALUE;
        synchronized (f204y3) {
            C0178n[] c0178nArr = f204y3[i];
            if (c0178nArr == null) {
                C0178n[] c0178nArr2 = new C0178n[128];
                f204y3[i] = c0178nArr2;
                c0178nArr = c0178nArr2;
            }
            C0178n c0178n = c0178nArr[i2];
            if (c0178n == null) {
                C0178n c0178n2 = new C0178n(bArr);
                c0178nArr[i2] = c0178n2;
                return c0178n2;
            } else if (C0669a.m22499a(bArr, c0178n.m24114m())) {
                return c0178n;
            } else {
                int i3 = (i + 1) & 255;
                C0178n[] c0178nArr3 = f204y3[i3];
                if (c0178nArr3 == null) {
                    C0178n[] c0178nArr4 = new C0178n[128];
                    f204y3[i3] = c0178nArr4;
                    c0178nArr3 = c0178nArr4;
                }
                C0178n c0178n3 = c0178nArr3[i2];
                if (c0178n3 == null) {
                    C0178n c0178n4 = new C0178n(bArr);
                    c0178nArr3[i2] = c0178n4;
                    return c0178n4;
                } else if (C0669a.m22499a(bArr, c0178n3.m24114m())) {
                    return c0178n3;
                } else {
                    int i4 = (i2 + 1) & 127;
                    C0178n c0178n5 = c0178nArr3[i4];
                    if (c0178n5 != null) {
                        return C0669a.m22499a(bArr, c0178n5.m24114m()) ? c0178n5 : new C0178n(bArr);
                    }
                    C0178n c0178n6 = new C0178n(bArr);
                    c0178nArr3[i4] = c0178n6;
                    return c0178n6;
                }
            }
        }
    }

    /* renamed from: a */
    public static C0178n m24122a(AbstractC0494y abstractC0494y, boolean z) {
        AbstractC0258r m23004m = abstractC0494y.m23004m();
        if (!z && !(m23004m instanceof C0164j1)) {
            return m24115b(AbstractC0182o.m24089a((Object) abstractC0494y.m23004m()).mo24088m());
        }
        return m24118a((Object) m23004m);
    }

    /* renamed from: a */
    private void m24120a(ByteArrayOutputStream byteArrayOutputStream, long j) {
        byte[] bArr = new byte[9];
        int i = 8;
        bArr[8] = (byte) (((int) j) & 127);
        while (j >= 128) {
            j >>= 7;
            i--;
            bArr[i] = (byte) ((((int) j) & 127) | 128);
        }
        byteArrayOutputStream.write(bArr, i, 9 - i);
    }

    /* renamed from: a */
    private void m24119a(ByteArrayOutputStream byteArrayOutputStream, BigInteger bigInteger) {
        int bitLength = (bigInteger.bitLength() + 6) / 7;
        if (bitLength == 0) {
            byteArrayOutputStream.write(0);
            return;
        }
        byte[] bArr = new byte[bitLength];
        int i = bitLength - 1;
        BigInteger bigInteger2 = bigInteger;
        for (int i2 = i; i2 >= 0; i2--) {
            bArr[i2] = (byte) ((bigInteger2.intValue() & 127) | 128);
            bigInteger2 = bigInteger2.shiftRight(7);
        }
        bArr[i] = (byte) (bArr[i] & Byte.MAX_VALUE);
        byteArrayOutputStream.write(bArr, 0, bitLength);
    }

    /* renamed from: a */
    private void m24121a(ByteArrayOutputStream byteArrayOutputStream) {
        C0173l2 c0173l2 = new C0173l2(this.f205v3);
        int parseInt = Integer.parseInt(c0173l2.m24104b()) * 40;
        String m24104b = c0173l2.m24104b();
        if (m24104b.length() <= 18) {
            m24120a(byteArrayOutputStream, parseInt + Long.parseLong(m24104b));
        } else {
            m24119a(byteArrayOutputStream, new BigInteger(m24104b).add(BigInteger.valueOf(parseInt)));
        }
        while (c0173l2.m24105a()) {
            String m24104b2 = c0173l2.m24104b();
            if (m24104b2.length() <= 18) {
                m24120a(byteArrayOutputStream, Long.parseLong(m24104b2));
            } else {
                m24119a(byteArrayOutputStream, new BigInteger(m24104b2));
            }
        }
    }

    public C0164j1(String str) {
        if (str != null) {
            if (m24116b(str)) {
                this.f205v3 = str;
                return;
            }
            throw new IllegalArgumentException("string " + str + " not an OID");
        }
        throw new IllegalArgumentException("'identifier' cannot be null");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public void mo22982a(C0252q c0252q) {
        byte[] m24114m = m24114m();
        c0252q.mo23763a(6);
        c0252q.m23767b(m24114m.length);
        c0252q.m23770a(m24114m);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public boolean mo22981a(AbstractC0258r abstractC0258r) {
        if (abstractC0258r instanceof C0164j1) {
            return this.f205v3.equals(((C0164j1) abstractC0258r).f205v3);
        }
        return false;
    }

    public C0164j1(C0164j1 c0164j1, String str) {
        if (m24117a(str, 0)) {
            this.f205v3 = c0164j1.m24113n() + "." + str;
            return;
        }
        throw new IllegalArgumentException("string " + str + " not a valid OID branch");
    }

    /* renamed from: a */
    public static boolean m24117a(String str, int i) {
        int length = str.length();
        boolean z = false;
        while (true) {
            length--;
            if (length < i) {
                return z;
            }
            char charAt = str.charAt(length);
            if ('0' <= charAt && charAt <= '9') {
                z = true;
            } else if (charAt != '.' || !z) {
                return false;
            } else {
                z = false;
            }
        }
    }
}
