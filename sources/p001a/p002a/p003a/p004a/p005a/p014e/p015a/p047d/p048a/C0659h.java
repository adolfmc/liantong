package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0669a;

/* renamed from: a.a.a.a.a.e.a.d.a.h */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0659h {

    /* renamed from: a */
    public int[] f2013a;

    public C0659h(int i) {
        this.f2013a = new int[i];
    }

    /* renamed from: f */
    private int[] m22550f(int i) {
        int[] iArr = new int[i];
        int length = this.f2013a.length;
        if (length < i) {
            i = length;
        }
        System.arraycopy(this.f2013a, 0, iArr, 0, i);
        return iArr;
    }

    /* renamed from: a */
    public int m22564a() {
        int m22557c = m22557c();
        if (m22557c == 0) {
            return 0;
        }
        int i = m22557c - 1;
        int i2 = this.f2013a[i];
        int i3 = (i << 5) + 1;
        if (((-65536) & i2) != 0) {
            if (((-16777216) & i2) != 0) {
                i3 += 24;
                i2 >>>= 24;
            } else {
                i3 += 16;
                i2 >>>= 16;
            }
        } else if (i2 > 255) {
            i3 += 8;
            i2 >>>= 8;
        }
        while (i2 != 1) {
            i3++;
            i2 >>>= 1;
        }
        return i3;
    }

    /* renamed from: b */
    public int m22560b() {
        return this.f2013a.length;
    }

    /* renamed from: c */
    public int m22557c() {
        int[] iArr = this.f2013a;
        int length = iArr.length;
        if (length < 1) {
            return 0;
        }
        if (iArr[0] != 0) {
            do {
                length--;
            } while (this.f2013a[length] == 0);
            return length + 1;
        }
        do {
            length--;
            if (this.f2013a[length] != 0) {
                return length + 1;
            }
        } while (length > 0);
        return 0;
    }

    public Object clone() {
        return new C0659h(C0669a.m22492a(this.f2013a));
    }

    /* renamed from: d */
    public boolean m22555d() {
        int[] iArr = this.f2013a;
        return iArr.length == 0 || (iArr[0] == 0 && m22557c() == 0);
    }

    /* renamed from: e */
    public void m22553e() {
        int m22557c = m22557c();
        if (m22557c == 0) {
            return;
        }
        int[] iArr = this.f2013a;
        if (iArr[m22557c - 1] < 0 && (m22557c = m22557c + 1) > iArr.length) {
            this.f2013a = m22550f(iArr.length + 1);
        }
        int i = 0;
        boolean z = false;
        while (i < m22557c) {
            boolean z2 = this.f2013a[i] < 0;
            int[] iArr2 = this.f2013a;
            iArr2[i] = iArr2[i] << 1;
            if (z) {
                iArr2[i] = iArr2[i] | 1;
            }
            i++;
            z = z2;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof C0659h) {
            C0659h c0659h = (C0659h) obj;
            int m22557c = m22557c();
            if (c0659h.m22557c() != m22557c) {
                return false;
            }
            for (int i = 0; i < m22557c; i++) {
                if (this.f2013a[i] != c0659h.f2013a[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        int m22557c = m22557c();
        int i = 1;
        for (int i2 = 0; i2 < m22557c; i2++) {
            i = (i * 31) + this.f2013a[i2];
        }
        return i;
    }

    public String toString() {
        int m22557c = m22557c();
        if (m22557c == 0) {
            return "0";
        }
        StringBuffer stringBuffer = new StringBuffer(Integer.toBinaryString(this.f2013a[m22557c - 1]));
        for (int i = m22557c - 2; i >= 0; i--) {
            String binaryString = Integer.toBinaryString(this.f2013a[i]);
            for (int length = binaryString.length(); length < 8; length++) {
                binaryString = "0" + binaryString;
            }
            stringBuffer.append(binaryString);
        }
        return stringBuffer.toString();
    }

    /* renamed from: b */
    public void m22559b(int i) {
        int i2 = i >> 5;
        int[] iArr = this.f2013a;
        iArr[i2] = (1 << (i & 31)) | iArr[i2];
    }

    public C0659h(int[] iArr) {
        this.f2013a = iArr;
    }

    /* renamed from: b */
    public C0659h m22558b(C0659h c0659h, int i) {
        int i2 = (i + 31) >> 5;
        if (this.f2013a.length < i2) {
            this.f2013a = m22550f(i2);
        }
        int i3 = 1;
        C0659h c0659h2 = new C0659h(c0659h.m22550f(c0659h.m22560b() + 1));
        C0659h c0659h3 = new C0659h(((i + i) + 31) >> 5);
        for (int i4 = 0; i4 < 32; i4++) {
            for (int i5 = 0; i5 < i2; i5++) {
                if ((this.f2013a[i5] & i3) != 0) {
                    c0659h3.m22561a(c0659h2, i5);
                }
            }
            i3 <<= 1;
            c0659h2.m22553e();
        }
        return c0659h3;
    }

    /* renamed from: d */
    public C0659h m22554d(int i) {
        int[] iArr = {0, 1, 4, 5, 16, 17, 20, 21, 64, 65, 68, 69, 80, 81, 84, 85};
        int i2 = (i + 31) >> 5;
        if (this.f2013a.length < i2) {
            this.f2013a = m22550f(i2);
        }
        C0659h c0659h = new C0659h(i2 + i2);
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = 0;
            for (int i5 = 0; i5 < 4; i5++) {
                i4 = (i4 >>> 8) | (iArr[(this.f2013a[i3] >>> (i5 * 4)) & 15] << 24);
            }
            int i6 = i3 + i3;
            c0659h.f2013a[i6] = i4;
            int i7 = this.f2013a[i3] >>> 16;
            int i8 = 0;
            for (int i9 = 0; i9 < 4; i9++) {
                i8 = (i8 >>> 8) | (iArr[(i7 >>> (i9 * 4)) & 15] << 24);
            }
            c0659h.f2013a[i6 + 1] = i8;
        }
        return c0659h;
    }

    public C0659h(BigInteger bigInteger) {
        this(bigInteger, 0);
    }

    /* renamed from: f */
    public BigInteger m22551f() {
        int m22557c = m22557c();
        if (m22557c == 0) {
            return InterfaceC0647b.f1976a;
        }
        int i = m22557c - 1;
        int i2 = this.f2013a[i];
        byte[] bArr = new byte[4];
        int i3 = 0;
        boolean z = false;
        for (int i4 = 3; i4 >= 0; i4--) {
            byte b = (byte) (i2 >>> (i4 * 8));
            if (z || b != 0) {
                bArr[i3] = b;
                i3++;
                z = true;
            }
        }
        byte[] bArr2 = new byte[(i * 4) + i3];
        for (int i5 = 0; i5 < i3; i5++) {
            bArr2[i5] = bArr[i5];
        }
        for (int i6 = m22557c - 2; i6 >= 0; i6--) {
            int i7 = 3;
            while (i7 >= 0) {
                bArr2[i3] = (byte) (this.f2013a[i6] >>> (i7 * 8));
                i7--;
                i3++;
            }
        }
        return new BigInteger(1, bArr2);
    }

    public C0659h(BigInteger bigInteger, int i) {
        int i2;
        int i3;
        if (bigInteger.signum() != -1) {
            if (bigInteger.equals(InterfaceC0647b.f1976a)) {
                this.f2013a = new int[]{0};
                return;
            }
            byte[] byteArray = bigInteger.toByteArray();
            int length = byteArray.length;
            if (byteArray[0] == 0) {
                length--;
                i2 = 1;
            } else {
                i2 = 0;
            }
            int i4 = (length + 3) / 4;
            if (i4 < i) {
                this.f2013a = new int[i];
            } else {
                this.f2013a = new int[i4];
            }
            int i5 = i4 - 1;
            int i6 = (length % 4) + i2;
            if (i2 < i6) {
                int i7 = 0;
                while (i2 < i6) {
                    int i8 = i7 << 8;
                    int i9 = byteArray[i2];
                    if (i9 < 0) {
                        i9 += 256;
                    }
                    i7 = i8 | i9;
                    i2++;
                }
                i3 = i5 - 1;
                this.f2013a[i5] = i7;
            } else {
                i3 = i5;
            }
            while (i3 >= 0) {
                int i10 = 0;
                int i11 = 0;
                while (i10 < 4) {
                    int i12 = i11 << 8;
                    int i13 = i2 + 1;
                    int i14 = byteArray[i2];
                    if (i14 < 0) {
                        i14 += 256;
                    }
                    i11 = i12 | i14;
                    i10++;
                    i2 = i13;
                }
                this.f2013a[i3] = i11;
                i3--;
            }
            return;
        }
        throw new IllegalArgumentException("Only positive Integers allowed");
    }

    /* renamed from: a */
    public void m22561a(C0659h c0659h, int i) {
        int m22557c = c0659h.m22557c();
        int i2 = m22557c + i;
        if (i2 > this.f2013a.length) {
            this.f2013a = m22550f(i2);
        }
        for (int i3 = 0; i3 < m22557c; i3++) {
            int[] iArr = this.f2013a;
            int i4 = i3 + i;
            iArr[i4] = iArr[i4] ^ c0659h.f2013a[i3];
        }
    }

    /* renamed from: a */
    public void m22563a(int i) {
        int i2 = i >> 5;
        int[] iArr = this.f2013a;
        iArr[i2] = (1 << (i & 31)) ^ iArr[i2];
    }

    /* renamed from: c */
    public C0659h m22556c(int i) {
        int m22557c = m22557c();
        if (m22557c == 0 || i == 0) {
            return this;
        }
        if (i <= 31) {
            int[] iArr = new int[m22557c + 1];
            int i2 = 32 - i;
            iArr[0] = this.f2013a[0] << i;
            for (int i3 = 1; i3 < m22557c; i3++) {
                int[] iArr2 = this.f2013a;
                iArr[i3] = (iArr2[i3 - 1] >>> i2) | (iArr2[i3] << i);
            }
            iArr[m22557c] = this.f2013a[m22557c - 1] >>> i2;
            return new C0659h(iArr);
        }
        throw new IllegalArgumentException("shiftLeft() for max 31 bits , " + i + "bit shift is not possible");
    }

    /* renamed from: a */
    public void m22562a(int i, int[] iArr) {
        for (int i2 = (i + i) - 2; i2 >= i; i2--) {
            if (m22552e(i2)) {
                int i3 = i2 - i;
                m22563a(i3);
                m22563a(i2);
                int length = iArr.length;
                while (true) {
                    length--;
                    if (length >= 0) {
                        m22563a(iArr[length] + i3);
                    }
                }
            }
        }
        this.f2013a = m22550f((i + 31) >> 5);
    }

    /* renamed from: e */
    public boolean m22552e(int i) {
        return ((1 << (i & 31)) & this.f2013a[i >> 5]) != 0;
    }
}
