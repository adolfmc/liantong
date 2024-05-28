package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p034f0;

import com.p201hb.omapi.union.sim.org.bouncycastle.crypto.InvalidCipherTextException;
import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0500a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0578k0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0592r0;

/* renamed from: a.a.a.a.a.e.a.c.f0.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0526a implements InterfaceC0500a {

    /* renamed from: f */
    public static final BigInteger f1707f = BigInteger.valueOf(16);

    /* renamed from: g */
    public static final BigInteger f1708g = BigInteger.valueOf(6);

    /* renamed from: h */
    public static byte[] f1709h = {14, 3, 5, 8, 9, 4, 2, 15, 0, 13, 11, 6, 7, 10, 12, 1};

    /* renamed from: i */
    public static byte[] f1710i = {8, 15, 6, 1, 5, 2, 11, 12, 3, 4, 13, 10, 14, 9, 0, 7};

    /* renamed from: a */
    public InterfaceC0500a f1711a;

    /* renamed from: b */
    public boolean f1712b;

    /* renamed from: c */
    public int f1713c;

    /* renamed from: d */
    public int f1714d = 0;

    /* renamed from: e */
    public BigInteger f1715e;

    public C0526a(InterfaceC0500a interfaceC0500a) {
        this.f1711a = interfaceC0500a;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0500a
    /* renamed from: a */
    public void mo22898a(boolean z, InterfaceC0542i interfaceC0542i) {
        C0592r0 c0592r0;
        if (interfaceC0542i instanceof C0578k0) {
            c0592r0 = (C0592r0) ((C0578k0) interfaceC0542i).m22801a();
        } else {
            c0592r0 = (C0592r0) interfaceC0542i;
        }
        this.f1711a.mo22898a(z, interfaceC0542i);
        BigInteger m22766c = c0592r0.m22766c();
        this.f1715e = m22766c;
        this.f1713c = m22766c.bitLength();
        this.f1712b = z;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0500a
    /* renamed from: b */
    public int mo22896b() {
        int mo22896b = this.f1711a.mo22896b();
        return this.f1712b ? mo22896b : (mo22896b + 1) / 2;
    }

    /* renamed from: c */
    public int m22907c() {
        return this.f1714d;
    }

    /* renamed from: d */
    public InterfaceC0500a m22905d() {
        return this.f1711a;
    }

    /* renamed from: c */
    private byte[] m22906c(byte[] bArr, int i, int i2) {
        int i3 = this.f1713c;
        int i4 = (i3 + 7) / 8;
        byte[] bArr2 = new byte[i4];
        int i5 = 1;
        int i6 = this.f1714d + 1;
        int i7 = (i3 + 13) / 16;
        int i8 = 0;
        while (i8 < i7) {
            if (i8 > i7 - i2) {
                int i9 = i7 - i8;
                System.arraycopy(bArr, (i + i2) - i9, bArr2, i4 - i7, i9);
            } else {
                System.arraycopy(bArr, i, bArr2, i4 - (i8 + i2), i2);
            }
            i8 += i2;
        }
        for (int i10 = i4 - (i7 * 2); i10 != i4; i10 += 2) {
            byte b = bArr2[(i4 - i7) + (i10 / 2)];
            byte[] bArr3 = f1709h;
            bArr2[i10] = (byte) (bArr3[b & 15] | (bArr3[(b & 255) >>> 4] << 4));
            bArr2[i10 + 1] = b;
        }
        int i11 = i4 - (i2 * 2);
        bArr2[i11] = (byte) (bArr2[i11] ^ i6);
        int i12 = i4 - 1;
        bArr2[i12] = (byte) ((bArr2[i12] << 4) | 6);
        int i13 = 8 - ((this.f1713c - 1) % 8);
        if (i13 != 8) {
            bArr2[0] = (byte) (bArr2[0] & (255 >>> i13));
            bArr2[0] = (byte) ((128 >>> i13) | bArr2[0]);
            i5 = 0;
        } else {
            bArr2[0] = 0;
            bArr2[1] = (byte) (bArr2[1] | 128);
        }
        return this.f1711a.mo22897a(bArr2, i5, i4 - i5);
    }

    /* renamed from: b */
    private byte[] m22908b(byte[] bArr, int i, int i2) {
        byte[] mo22897a = this.f1711a.mo22897a(bArr, i, i2);
        int i3 = (this.f1713c + 13) / 16;
        BigInteger bigInteger = new BigInteger(1, mo22897a);
        if (!bigInteger.mod(f1707f).equals(f1708g)) {
            if (this.f1715e.subtract(bigInteger).mod(f1707f).equals(f1708g)) {
                bigInteger = this.f1715e.subtract(bigInteger);
            } else {
                throw new InvalidCipherTextException("resulting integer iS or (modulus - iS) is not congruent to 6 mod 16");
            }
        }
        byte[] m22909a = m22909a(bigInteger);
        if ((m22909a[m22909a.length - 1] & 15) == 6) {
            m22909a[m22909a.length - 1] = (byte) (((m22909a[m22909a.length - 1] & 255) >>> 4) | (f1710i[(m22909a[m22909a.length - 2] & 255) >> 4] << 4));
            byte[] bArr2 = f1709h;
            m22909a[0] = (byte) (bArr2[m22909a[1] & 15] | (bArr2[(m22909a[1] & 255) >>> 4] << 4));
            int i4 = 1;
            int i5 = 0;
            boolean z = false;
            for (int length = m22909a.length - 1; length >= m22909a.length - (i3 * 2); length -= 2) {
                byte[] bArr3 = f1709h;
                int i6 = bArr3[m22909a[length] & 15] | (bArr3[(m22909a[length] & 255) >>> 4] << 4);
                int i7 = length - 1;
                if (((m22909a[i7] ^ i6) & 255) != 0) {
                    if (!z) {
                        z = true;
                        i4 = (m22909a[i7] ^ i6) & 255;
                        i5 = i7;
                    } else {
                        throw new InvalidCipherTextException("invalid tsums in block");
                    }
                }
            }
            m22909a[i5] = 0;
            int length2 = (m22909a.length - i5) / 2;
            byte[] bArr4 = new byte[length2];
            for (int i8 = 0; i8 < length2; i8++) {
                bArr4[i8] = m22909a[(i8 * 2) + i5 + 1];
            }
            this.f1714d = i4 - 1;
            return bArr4;
        }
        throw new InvalidCipherTextException("invalid forcing byte in block");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0500a
    /* renamed from: a */
    public int mo22899a() {
        int mo22899a = this.f1711a.mo22899a();
        return this.f1712b ? (mo22899a + 1) / 2 : mo22899a;
    }

    /* renamed from: a */
    public void m22910a(int i) {
        if (i <= 7) {
            this.f1714d = i;
            return;
        }
        throw new IllegalArgumentException("padBits > 7");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0500a
    /* renamed from: a */
    public byte[] mo22897a(byte[] bArr, int i, int i2) {
        if (this.f1712b) {
            return m22906c(bArr, i, i2);
        }
        return m22908b(bArr, i, i2);
    }

    /* renamed from: a */
    public static byte[] m22909a(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray[0] == 0) {
            int length = byteArray.length - 1;
            byte[] bArr = new byte[length];
            System.arraycopy(byteArray, 1, bArr, 0, length);
            return bArr;
        }
        return byteArray;
    }
}
