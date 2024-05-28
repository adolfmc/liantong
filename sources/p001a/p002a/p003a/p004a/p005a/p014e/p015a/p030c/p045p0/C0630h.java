package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p045p0;

import java.security.SecureRandom;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0500a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0643x;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0578k0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0588p0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0592r0;

/* renamed from: a.a.a.a.a.e.a.c.p0.h */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0630h implements InterfaceC0643x {

    /* renamed from: m */
    public static final byte f1956m = -68;

    /* renamed from: a */
    public InterfaceC0605o f1957a;

    /* renamed from: b */
    public InterfaceC0605o f1958b;

    /* renamed from: c */
    public InterfaceC0500a f1959c;

    /* renamed from: d */
    public SecureRandom f1960d;

    /* renamed from: e */
    public int f1961e;

    /* renamed from: f */
    public int f1962f;

    /* renamed from: g */
    public int f1963g;

    /* renamed from: h */
    public int f1964h;

    /* renamed from: i */
    public byte[] f1965i;

    /* renamed from: j */
    public byte[] f1966j;

    /* renamed from: k */
    public byte[] f1967k;

    /* renamed from: l */
    public byte f1968l;

    public C0630h(InterfaceC0500a interfaceC0500a, InterfaceC0605o interfaceC0605o, int i) {
        this(interfaceC0500a, interfaceC0605o, i, (byte) -68);
    }

    /* renamed from: c */
    private void m22697c(byte[] bArr) {
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = 0;
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0643x
    /* renamed from: a */
    public void mo22637a(boolean z, InterfaceC0542i interfaceC0542i) {
        C0592r0 c0592r0;
        if (interfaceC0542i instanceof C0578k0) {
            C0578k0 c0578k0 = (C0578k0) interfaceC0542i;
            InterfaceC0542i m22801a = c0578k0.m22801a();
            this.f1960d = c0578k0.m22800b();
            interfaceC0542i = m22801a;
        } else if (z) {
            this.f1960d = new SecureRandom();
        }
        this.f1959c.mo22898a(z, interfaceC0542i);
        if (interfaceC0542i instanceof C0588p0) {
            c0592r0 = ((C0588p0) interfaceC0542i).m22776b();
        } else {
            c0592r0 = (C0592r0) interfaceC0542i;
        }
        int bitLength = c0592r0.m22766c().bitLength() - 1;
        this.f1964h = bitLength;
        if (bitLength >= (this.f1961e * 8) + (this.f1963g * 8) + 9) {
            this.f1967k = new byte[(bitLength + 7) / 8];
            mo22635b();
            return;
        }
        throw new IllegalArgumentException("key too small for specified hash and salt lengths");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0643x
    /* renamed from: b */
    public void mo22635b() {
        this.f1957a.mo22744b();
    }

    public C0630h(InterfaceC0500a interfaceC0500a, InterfaceC0605o interfaceC0605o, InterfaceC0605o interfaceC0605o2, int i) {
        this(interfaceC0500a, interfaceC0605o, interfaceC0605o2, i, (byte) -68);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0643x
    /* renamed from: b */
    public boolean mo22634b(byte[] bArr) {
        InterfaceC0605o interfaceC0605o = this.f1957a;
        byte[] bArr2 = this.f1966j;
        interfaceC0605o.mo22746a(bArr2, (bArr2.length - this.f1961e) - this.f1963g);
        try {
            byte[] mo22897a = this.f1959c.mo22897a(bArr, 0, bArr.length);
            System.arraycopy(mo22897a, 0, this.f1967k, this.f1967k.length - mo22897a.length, mo22897a.length);
            byte[] bArr3 = this.f1967k;
            if (bArr3[bArr3.length - 1] != this.f1968l) {
                m22697c(bArr3);
                return false;
            }
            int length = bArr3.length;
            int i = this.f1961e;
            byte[] m22698a = m22698a(bArr3, (length - i) - 1, i, (bArr3.length - i) - 1);
            for (int i2 = 0; i2 != m22698a.length; i2++) {
                byte[] bArr4 = this.f1967k;
                bArr4[i2] = (byte) (bArr4[i2] ^ m22698a[i2]);
            }
            byte[] bArr5 = this.f1967k;
            bArr5[0] = (byte) (bArr5[0] & (255 >> ((bArr5.length * 8) - this.f1964h)));
            int i3 = 0;
            while (true) {
                byte[] bArr6 = this.f1967k;
                int length2 = bArr6.length;
                int i4 = this.f1961e;
                int i5 = this.f1963g;
                if (i3 != ((length2 - i4) - i5) - 2) {
                    if (bArr6[i3] != 0) {
                        m22697c(bArr6);
                        return false;
                    }
                    i3++;
                } else if (bArr6[((bArr6.length - i4) - i5) - 2] != 1) {
                    m22697c(bArr6);
                    return false;
                } else {
                    byte[] bArr7 = this.f1966j;
                    System.arraycopy(bArr6, ((bArr6.length - i5) - i4) - 1, bArr7, bArr7.length - i5, i5);
                    InterfaceC0605o interfaceC0605o2 = this.f1957a;
                    byte[] bArr8 = this.f1966j;
                    interfaceC0605o2.mo22745a(bArr8, 0, bArr8.length);
                    InterfaceC0605o interfaceC0605o3 = this.f1957a;
                    byte[] bArr9 = this.f1966j;
                    interfaceC0605o3.mo22746a(bArr9, bArr9.length - this.f1961e);
                    int length3 = this.f1967k.length;
                    int i6 = this.f1961e;
                    int i7 = (length3 - i6) - 1;
                    int length4 = this.f1966j.length - i6;
                    while (true) {
                        byte[] bArr10 = this.f1966j;
                        if (length4 != bArr10.length) {
                            if ((this.f1967k[i7] ^ bArr10[length4]) != 0) {
                                m22697c(bArr10);
                                m22697c(this.f1967k);
                                return false;
                            }
                            i7++;
                            length4++;
                        } else {
                            m22697c(bArr10);
                            m22697c(this.f1967k);
                            return true;
                        }
                    }
                }
            }
        } catch (Exception unused) {
            return false;
        }
    }

    public C0630h(InterfaceC0500a interfaceC0500a, InterfaceC0605o interfaceC0605o, int i, byte b) {
        this(interfaceC0500a, interfaceC0605o, interfaceC0605o, i, b);
    }

    public C0630h(InterfaceC0500a interfaceC0500a, InterfaceC0605o interfaceC0605o, InterfaceC0605o interfaceC0605o2, int i, byte b) {
        this.f1959c = interfaceC0500a;
        this.f1957a = interfaceC0605o;
        this.f1958b = interfaceC0605o2;
        this.f1961e = interfaceC0605o.mo22743c();
        this.f1962f = interfaceC0605o2.mo22743c();
        this.f1963g = i;
        this.f1965i = new byte[i];
        this.f1966j = new byte[i + 8 + this.f1961e];
        this.f1968l = b;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0643x
    /* renamed from: a */
    public void mo22638a(byte b) {
        this.f1957a.mo22747a(b);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0643x
    /* renamed from: a */
    public void mo22636a(byte[] bArr, int i, int i2) {
        this.f1957a.mo22745a(bArr, i, i2);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0643x
    /* renamed from: a */
    public byte[] mo22639a() {
        InterfaceC0605o interfaceC0605o = this.f1957a;
        byte[] bArr = this.f1966j;
        interfaceC0605o.mo22746a(bArr, (bArr.length - this.f1961e) - this.f1963g);
        if (this.f1963g != 0) {
            this.f1960d.nextBytes(this.f1965i);
            byte[] bArr2 = this.f1965i;
            byte[] bArr3 = this.f1966j;
            int length = bArr3.length;
            int i = this.f1963g;
            System.arraycopy(bArr2, 0, bArr3, length - i, i);
        }
        int i2 = this.f1961e;
        byte[] bArr4 = new byte[i2];
        InterfaceC0605o interfaceC0605o2 = this.f1957a;
        byte[] bArr5 = this.f1966j;
        interfaceC0605o2.mo22745a(bArr5, 0, bArr5.length);
        this.f1957a.mo22746a(bArr4, 0);
        byte[] bArr6 = this.f1967k;
        int length2 = bArr6.length;
        int i3 = this.f1963g;
        int i4 = this.f1961e;
        bArr6[(((length2 - i3) - 1) - i4) - 1] = 1;
        System.arraycopy(this.f1965i, 0, bArr6, ((bArr6.length - i3) - i4) - 1, i3);
        byte[] m22698a = m22698a(bArr4, 0, i2, (this.f1967k.length - this.f1961e) - 1);
        for (int i5 = 0; i5 != m22698a.length; i5++) {
            byte[] bArr7 = this.f1967k;
            bArr7[i5] = (byte) (bArr7[i5] ^ m22698a[i5]);
        }
        byte[] bArr8 = this.f1967k;
        bArr8[0] = (byte) (bArr8[0] & (255 >> ((bArr8.length * 8) - this.f1964h)));
        int length3 = bArr8.length;
        int i6 = this.f1961e;
        System.arraycopy(bArr4, 0, bArr8, (length3 - i6) - 1, i6);
        byte[] bArr9 = this.f1967k;
        bArr9[bArr9.length - 1] = this.f1968l;
        byte[] mo22897a = this.f1959c.mo22897a(bArr9, 0, bArr9.length);
        m22697c(this.f1967k);
        return mo22897a;
    }

    /* renamed from: a */
    private void m22699a(int i, byte[] bArr) {
        bArr[0] = (byte) (i >>> 24);
        bArr[1] = (byte) (i >>> 16);
        bArr[2] = (byte) (i >>> 8);
        bArr[3] = (byte) (i >>> 0);
    }

    /* renamed from: a */
    private byte[] m22698a(byte[] bArr, int i, int i2, int i3) {
        int i4;
        byte[] bArr2 = new byte[i3];
        byte[] bArr3 = new byte[this.f1962f];
        byte[] bArr4 = new byte[4];
        this.f1958b.mo22744b();
        int i5 = 0;
        while (true) {
            i4 = this.f1962f;
            if (i5 >= i3 / i4) {
                break;
            }
            m22699a(i5, bArr4);
            this.f1958b.mo22745a(bArr, i, i2);
            this.f1958b.mo22745a(bArr4, 0, 4);
            this.f1958b.mo22746a(bArr3, 0);
            int i6 = this.f1962f;
            System.arraycopy(bArr3, 0, bArr2, i5 * i6, i6);
            i5++;
        }
        if (i4 * i5 < i3) {
            m22699a(i5, bArr4);
            this.f1958b.mo22745a(bArr, i, i2);
            this.f1958b.mo22745a(bArr4, 0, 4);
            this.f1958b.mo22746a(bArr3, 0);
            int i7 = i5 * this.f1962f;
            System.arraycopy(bArr3, 0, bArr2, i7, i3 - i7);
        }
        return bArr2;
    }
}
