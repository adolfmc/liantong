package org.p415a.p418b;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.p415a.p448g.C12957a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0164j1;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.o */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12563o extends AbstractC12570t {

    /* renamed from: c */
    private static final ConcurrentMap<C12564a, C12563o> f25505c = new ConcurrentHashMap();

    /* renamed from: a */
    private final String f25506a;

    /* renamed from: b */
    private byte[] f25507b;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.b.o$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class C12564a {

        /* renamed from: a */
        private final int f25508a;

        /* renamed from: b */
        private final byte[] f25509b;

        C12564a(byte[] bArr) {
            this.f25508a = C12957a.m441a(bArr);
            this.f25509b = bArr;
        }

        public boolean equals(Object obj) {
            if (obj instanceof C12564a) {
                return C12957a.m438a(this.f25509b, ((C12564a) obj).f25509b);
            }
            return false;
        }

        public int hashCode() {
            return this.f25508a;
        }
    }

    public C12563o(String str) {
        if (str == null) {
            throw new IllegalArgumentException("'identifier' cannot be null");
        }
        if (m1634c(str)) {
            this.f25506a = str;
            return;
        }
        throw new IllegalArgumentException("string " + str + " not an OID");
    }

    C12563o(C12563o c12563o, String str) {
        if (!m1638a(str, 0)) {
            throw new IllegalArgumentException("string " + str + " not a valid OID branch");
        }
        this.f25506a = c12563o.m1636b() + "." + str;
    }

    C12563o(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = true;
        long j = 0;
        BigInteger bigInteger = null;
        for (int i = 0; i != bArr.length; i++) {
            int i2 = bArr[i] & 255;
            if (j <= C0164j1.f203x3) {
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
        this.f25506a = stringBuffer.toString();
        this.f25507b = C12957a.m430b(bArr);
    }

    /* renamed from: a */
    public static C12563o m1639a(Object obj) {
        if (obj == null || (obj instanceof C12563o)) {
            return (C12563o) obj;
        }
        if (obj instanceof InterfaceC12554f) {
            InterfaceC12554f interfaceC12554f = (InterfaceC12554f) obj;
            if (interfaceC12554f.mo1617h() instanceof C12563o) {
                return (C12563o) interfaceC12554f.mo1617h();
            }
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (C12563o) m1619b((byte[]) obj);
        } catch (IOException e) {
            throw new IllegalArgumentException("failed to construct object identifier from byte[]: " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C12563o m1637a(byte[] bArr) {
        C12563o c12563o = f25505c.get(new C12564a(bArr));
        return c12563o == null ? new C12563o(bArr) : c12563o;
    }

    /* renamed from: a */
    private void m1642a(ByteArrayOutputStream byteArrayOutputStream) {
        C12465by c12465by = new C12465by(this.f25506a);
        int parseInt = Integer.parseInt(c12465by.m1695b()) * 40;
        String m1695b = c12465by.m1695b();
        if (m1695b.length() <= 18) {
            m1641a(byteArrayOutputStream, parseInt + Long.parseLong(m1695b));
        } else {
            m1640a(byteArrayOutputStream, new BigInteger(m1695b).add(BigInteger.valueOf(parseInt)));
        }
        while (c12465by.m1696a()) {
            String m1695b2 = c12465by.m1695b();
            if (m1695b2.length() <= 18) {
                m1641a(byteArrayOutputStream, Long.parseLong(m1695b2));
            } else {
                m1640a(byteArrayOutputStream, new BigInteger(m1695b2));
            }
        }
    }

    /* renamed from: a */
    private void m1641a(ByteArrayOutputStream byteArrayOutputStream, long j) {
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
    private void m1640a(ByteArrayOutputStream byteArrayOutputStream, BigInteger bigInteger) {
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
        byteArrayOutputStream.write(bArr, 0, bArr.length);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001a, code lost:
        if (r3 != '.') goto L17;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean m1638a(java.lang.String r5, int r6) {
        /*
            int r0 = r5.length()
            r1 = 0
        L5:
            r2 = r1
        L6:
            int r0 = r0 + (-1)
            if (r0 < r6) goto L1f
            char r3 = r5.charAt(r0)
            r4 = 48
            if (r4 > r3) goto L18
            r4 = 57
            if (r3 > r4) goto L18
            r2 = 1
            goto L6
        L18:
            r4 = 46
            if (r3 != r4) goto L1e
            if (r2 != 0) goto L5
        L1e:
            return r1
        L1f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.p415a.p418b.C12563o.m1638a(java.lang.String, int):boolean");
    }

    /* renamed from: c */
    private static boolean m1634c(String str) {
        char charAt;
        if (str.length() < 3 || str.charAt(1) != '.' || (charAt = str.charAt(0)) < '0' || charAt > '2') {
            return false;
        }
        return m1638a(str, 2);
    }

    /* renamed from: d */
    private synchronized byte[] m1633d() {
        if (this.f25507b == null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            m1642a(byteArrayOutputStream);
            this.f25507b = byteArrayOutputStream.toByteArray();
        }
        return this.f25507b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public void mo1597a(C12567r c12567r) {
        byte[] m1633d = m1633d();
        c12567r.mo1620b(6);
        c12567r.m1629a(m1633d.length);
        c12567r.m1623a(m1633d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public boolean mo1611a() {
        return false;
    }

    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    boolean mo1596a(AbstractC12570t abstractC12570t) {
        if (abstractC12570t == this) {
            return true;
        }
        if (abstractC12570t instanceof C12563o) {
            return this.f25506a.equals(((C12563o) abstractC12570t).f25506a);
        }
        return false;
    }

    /* renamed from: b */
    public String m1636b() {
        return this.f25506a;
    }

    /* renamed from: b */
    public C12563o m1635b(String str) {
        return new C12563o(this, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: c */
    public int mo1618c() {
        int length = m1633d().length;
        return C12466bz.m1694a(length) + 1 + length;
    }

    @Override // org.p415a.p418b.AbstractC12570t, org.p415a.p418b.AbstractC12562n
    public int hashCode() {
        return this.f25506a.hashCode();
    }

    public String toString() {
        return m1636b();
    }
}
