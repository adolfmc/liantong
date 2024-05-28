package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p034f0;

import com.p201hb.omapi.union.sim.org.bouncycastle.crypto.InvalidCipherTextException;
import java.security.SecureRandom;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0500a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0.C0512f;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0578k0;

/* renamed from: a.a.a.a.a.e.a.c.f0.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0527b implements InterfaceC0500a {

    /* renamed from: a */
    public byte[] f1716a;

    /* renamed from: b */
    public InterfaceC0605o f1717b;

    /* renamed from: c */
    public InterfaceC0500a f1718c;

    /* renamed from: d */
    public SecureRandom f1719d;

    /* renamed from: e */
    public boolean f1720e;

    public C0527b(InterfaceC0500a interfaceC0500a) {
        this(interfaceC0500a, new C0512f(), null);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0500a
    /* renamed from: a */
    public void mo22898a(boolean z, InterfaceC0542i interfaceC0542i) {
        if (interfaceC0542i instanceof C0578k0) {
            this.f1719d = ((C0578k0) interfaceC0542i).m22800b();
        } else {
            this.f1719d = new SecureRandom();
        }
        this.f1718c.mo22898a(z, interfaceC0542i);
        this.f1720e = z;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0500a
    /* renamed from: b */
    public int mo22896b() {
        int mo22896b = this.f1718c.mo22896b();
        return this.f1720e ? mo22896b : (mo22896b - 1) - (this.f1716a.length * 2);
    }

    /* renamed from: c */
    public InterfaceC0500a m22901c() {
        return this.f1718c;
    }

    public C0527b(InterfaceC0500a interfaceC0500a, InterfaceC0605o interfaceC0605o) {
        this(interfaceC0500a, interfaceC0605o, null);
    }

    /* renamed from: c */
    public byte[] m22900c(byte[] bArr, int i, int i2) {
        int mo22899a = mo22899a() + 1 + (this.f1716a.length * 2);
        byte[] bArr2 = new byte[mo22899a];
        int i3 = mo22899a - i2;
        System.arraycopy(bArr, i, bArr2, i3, i2);
        bArr2[i3 - 1] = 1;
        byte[] bArr3 = this.f1716a;
        System.arraycopy(bArr3, 0, bArr2, bArr3.length, bArr3.length);
        int length = this.f1716a.length;
        byte[] bArr4 = new byte[length];
        this.f1719d.nextBytes(bArr4);
        byte[] m22903a = m22903a(bArr4, 0, length, mo22899a - this.f1716a.length);
        for (int length2 = this.f1716a.length; length2 != mo22899a; length2++) {
            bArr2[length2] = (byte) (bArr2[length2] ^ m22903a[length2 - this.f1716a.length]);
        }
        System.arraycopy(bArr4, 0, bArr2, 0, this.f1716a.length);
        byte[] bArr5 = this.f1716a;
        byte[] m22903a2 = m22903a(bArr2, bArr5.length, mo22899a - bArr5.length, bArr5.length);
        for (int i4 = 0; i4 != this.f1716a.length; i4++) {
            bArr2[i4] = (byte) (bArr2[i4] ^ m22903a2[i4]);
        }
        return this.f1718c.mo22897a(bArr2, 0, mo22899a);
    }

    public C0527b(InterfaceC0500a interfaceC0500a, InterfaceC0605o interfaceC0605o, byte[] bArr) {
        this(interfaceC0500a, interfaceC0605o, interfaceC0605o, bArr);
    }

    public C0527b(InterfaceC0500a interfaceC0500a, InterfaceC0605o interfaceC0605o, InterfaceC0605o interfaceC0605o2, byte[] bArr) {
        this.f1718c = interfaceC0500a;
        this.f1717b = interfaceC0605o2;
        this.f1716a = new byte[interfaceC0605o.mo22743c()];
        interfaceC0605o.mo22744b();
        if (bArr != null) {
            interfaceC0605o.mo22745a(bArr, 0, bArr.length);
        }
        interfaceC0605o.mo22746a(this.f1716a, 0);
    }

    /* renamed from: b */
    public byte[] m22902b(byte[] bArr, int i, int i2) {
        byte[] bArr2;
        byte[] bArr3;
        byte[] mo22897a = this.f1718c.mo22897a(bArr, i, i2);
        if (mo22897a.length < this.f1718c.mo22896b()) {
            int mo22896b = this.f1718c.mo22896b();
            byte[] bArr4 = new byte[mo22896b];
            System.arraycopy(mo22897a, 0, bArr4, mo22896b - mo22897a.length, mo22897a.length);
            mo22897a = bArr4;
        }
        int length = mo22897a.length;
        byte[] bArr5 = this.f1716a;
        if (length >= (bArr5.length * 2) + 1) {
            byte[] m22903a = m22903a(mo22897a, bArr5.length, mo22897a.length - bArr5.length, bArr5.length);
            int i3 = 0;
            while (true) {
                bArr2 = this.f1716a;
                if (i3 == bArr2.length) {
                    break;
                }
                mo22897a[i3] = (byte) (mo22897a[i3] ^ m22903a[i3]);
                i3++;
            }
            byte[] m22903a2 = m22903a(mo22897a, 0, bArr2.length, mo22897a.length - bArr2.length);
            for (int length2 = this.f1716a.length; length2 != mo22897a.length; length2++) {
                mo22897a[length2] = (byte) (mo22897a[length2] ^ m22903a2[length2 - this.f1716a.length]);
            }
            int i4 = 0;
            boolean z = false;
            while (true) {
                bArr3 = this.f1716a;
                if (i4 == bArr3.length) {
                    break;
                }
                if (bArr3[i4] != mo22897a[bArr3.length + i4]) {
                    z = true;
                }
                i4++;
            }
            if (!z) {
                int length3 = bArr3.length * 2;
                while (length3 != mo22897a.length && mo22897a[length3] == 0) {
                    length3++;
                }
                if (length3 < mo22897a.length - 1 && mo22897a[length3] == 1) {
                    int i5 = length3 + 1;
                    int length4 = mo22897a.length - i5;
                    byte[] bArr6 = new byte[length4];
                    System.arraycopy(mo22897a, i5, bArr6, 0, length4);
                    return bArr6;
                }
                throw new InvalidCipherTextException("data start wrong " + length3);
            }
            throw new InvalidCipherTextException("data hash wrong");
        }
        throw new InvalidCipherTextException("data too short");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0500a
    /* renamed from: a */
    public int mo22899a() {
        int mo22899a = this.f1718c.mo22899a();
        return this.f1720e ? (mo22899a - 1) - (this.f1716a.length * 2) : mo22899a;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0500a
    /* renamed from: a */
    public byte[] mo22897a(byte[] bArr, int i, int i2) {
        if (this.f1720e) {
            return m22900c(bArr, i, i2);
        }
        return m22902b(bArr, i, i2);
    }

    /* renamed from: a */
    private void m22904a(int i, byte[] bArr) {
        bArr[0] = (byte) (i >>> 24);
        bArr[1] = (byte) (i >>> 16);
        bArr[2] = (byte) (i >>> 8);
        bArr[3] = (byte) (i >>> 0);
    }

    /* renamed from: a */
    private byte[] m22903a(byte[] bArr, int i, int i2, int i3) {
        byte[] bArr2 = new byte[i3];
        int mo22743c = this.f1717b.mo22743c();
        byte[] bArr3 = new byte[mo22743c];
        byte[] bArr4 = new byte[4];
        this.f1717b.mo22744b();
        int i4 = 0;
        while (i4 < i3 / mo22743c) {
            m22904a(i4, bArr4);
            this.f1717b.mo22745a(bArr, i, i2);
            this.f1717b.mo22745a(bArr4, 0, 4);
            this.f1717b.mo22746a(bArr3, 0);
            System.arraycopy(bArr3, 0, bArr2, i4 * mo22743c, mo22743c);
            i4++;
        }
        int i5 = mo22743c * i4;
        if (i5 < i3) {
            m22904a(i4, bArr4);
            this.f1717b.mo22745a(bArr, i, i2);
            this.f1717b.mo22745a(bArr4, 0, 4);
            this.f1717b.mo22746a(bArr3, 0);
            System.arraycopy(bArr3, 0, bArr2, i5, i3 - i5);
        }
        return bArr2;
    }
}
