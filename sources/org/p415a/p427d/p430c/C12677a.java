package org.p415a.p427d.p430c;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import org.p415a.p427d.C12727l;
import org.p415a.p427d.InterfaceC12630a;
import org.p415a.p427d.InterfaceC12696f;
import org.p415a.p427d.p435h.C12705a;
import org.p415a.p427d.p435h.C12721q;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.c.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12677a implements InterfaceC12630a {

    /* renamed from: a */
    private SecureRandom f25753a;

    /* renamed from: b */
    private InterfaceC12630a f25754b;

    /* renamed from: c */
    private boolean f25755c;

    /* renamed from: d */
    private boolean f25756d;

    /* renamed from: h */
    private byte[] f25760h;

    /* renamed from: f */
    private int f25758f = -1;

    /* renamed from: g */
    private byte[] f25759g = null;

    /* renamed from: e */
    private boolean f25757e = m1455c();

    public C12677a(InterfaceC12630a interfaceC12630a) {
        this.f25754b = interfaceC12630a;
    }

    /* renamed from: a */
    private int m1458a(byte b, byte[] bArr) {
        int i = -1;
        boolean z = false;
        for (int i2 = 1; i2 != bArr.length; i2++) {
            byte b2 = bArr[i2];
            if ((b2 == 0) & (i < 0)) {
                i = i2;
            }
            z |= (b2 != -1) & (b == 1) & (i < 0);
        }
        if (z) {
            return -1;
        }
        return i;
    }

    /* renamed from: a */
    private static int m1457a(byte[] bArr, int i) {
        int i2 = 0 | (bArr[0] ^ 2);
        int i3 = i + 1;
        int length = bArr.length - i3;
        int i4 = i2;
        for (int i5 = 1; i5 < length; i5++) {
            byte b = bArr[i5];
            int i6 = b | (b >> 1);
            int i7 = i6 | (i6 >> 2);
            i4 |= ((i7 | (i7 >> 4)) & 1) - 1;
        }
        int i8 = bArr[bArr.length - i3] | i4;
        int i9 = i8 | (i8 >> 1);
        int i10 = i9 | (i9 >> 2);
        return ~(((i10 | (i10 >> 4)) & 1) - 1);
    }

    /* renamed from: b */
    private byte[] m1456b(byte[] bArr, int i, int i2) {
        if (i2 <= mo1399a()) {
            byte[] bArr2 = new byte[this.f25754b.mo1399a()];
            if (this.f25756d) {
                bArr2[0] = 1;
                for (int i3 = 1; i3 != (bArr2.length - i2) - 1; i3++) {
                    bArr2[i3] = -1;
                }
            } else {
                this.f25753a.nextBytes(bArr2);
                bArr2[0] = 2;
                for (int i4 = 1; i4 != (bArr2.length - i2) - 1; i4++) {
                    while (bArr2[i4] == 0) {
                        bArr2[i4] = (byte) this.f25753a.nextInt();
                    }
                }
            }
            bArr2[(bArr2.length - i2) - 1] = 0;
            System.arraycopy(bArr, i, bArr2, bArr2.length - i2, i2);
            return this.f25754b.mo1397a(bArr2, 0, bArr2.length);
        }
        throw new IllegalArgumentException("input data too large");
    }

    /* renamed from: c */
    private boolean m1455c() {
        String str = (String) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.a.d.c.a.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                return System.getProperty("org.bouncycastle1.pkcs1.strict");
            }
        });
        String str2 = (String) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.a.d.c.a.2
            @Override // java.security.PrivilegedAction
            public Object run() {
                return System.getProperty("org.bouncycastle1.pkcs1.not_strict");
            }
        });
        return str2 != null ? !str2.equals("true") : str == null || str.equals("true");
    }

    /* renamed from: c */
    private byte[] m1454c(byte[] bArr, int i, int i2) {
        if (!this.f25756d) {
            throw new C12727l("sorry, this method is only for decryption, not for signing");
        }
        byte[] mo1397a = this.f25754b.mo1397a(bArr, i, i2);
        byte[] bArr2 = this.f25759g;
        if (bArr2 == null) {
            bArr2 = new byte[this.f25758f];
            this.f25753a.nextBytes(bArr2);
        }
        if (this.f25757e & (mo1397a.length != this.f25754b.mo1396b())) {
            mo1397a = this.f25760h;
        }
        int m1457a = m1457a(mo1397a, this.f25758f);
        byte[] bArr3 = new byte[this.f25758f];
        int i3 = 0;
        while (true) {
            int i4 = this.f25758f;
            if (i3 >= i4) {
                C12957a.m440a(mo1397a, (byte) 0);
                return bArr3;
            }
            bArr3[i3] = (byte) ((mo1397a[(mo1397a.length - i4) + i3] & (~m1457a)) | (bArr2[i3] & m1457a));
            i3++;
        }
    }

    /* renamed from: d */
    private byte[] m1453d(byte[] bArr, int i, int i2) {
        if (this.f25758f != -1) {
            return m1454c(bArr, i, i2);
        }
        byte[] mo1397a = this.f25754b.mo1397a(bArr, i, i2);
        boolean z = this.f25757e & (mo1397a.length != this.f25754b.mo1396b());
        if (mo1397a.length < mo1396b()) {
            mo1397a = this.f25760h;
        }
        byte b = mo1397a[0];
        boolean z2 = !this.f25756d ? b == 1 : b == 2;
        int m1458a = m1458a(b, mo1397a) + 1;
        if (z2 || (m1458a < 10)) {
            C12957a.m440a(mo1397a, (byte) 0);
            throw new C12727l("block incorrect");
        } else if (z) {
            C12957a.m440a(mo1397a, (byte) 0);
            throw new C12727l("block incorrect size");
        } else {
            byte[] bArr2 = new byte[mo1397a.length - m1458a];
            System.arraycopy(mo1397a, m1458a, bArr2, 0, bArr2.length);
            return bArr2;
        }
    }

    @Override // org.p415a.p427d.InterfaceC12630a
    /* renamed from: a */
    public int mo1399a() {
        int mo1399a = this.f25754b.mo1399a();
        return this.f25755c ? mo1399a - 10 : mo1399a;
    }

    @Override // org.p415a.p427d.InterfaceC12630a
    /* renamed from: a */
    public void mo1398a(boolean z, InterfaceC12696f interfaceC12696f) {
        C12705a c12705a;
        if (interfaceC12696f instanceof C12721q) {
            C12721q c12721q = (C12721q) interfaceC12696f;
            this.f25753a = c12721q.m1325a();
            c12705a = (C12705a) c12721q.m1324b();
        } else {
            c12705a = (C12705a) interfaceC12696f;
            if (!c12705a.m1345a() && z) {
                this.f25753a = new SecureRandom();
            }
        }
        this.f25754b.mo1398a(z, interfaceC12696f);
        this.f25756d = c12705a.m1345a();
        this.f25755c = z;
        this.f25760h = new byte[this.f25754b.mo1396b()];
        if (this.f25758f > 0 && this.f25759g == null && this.f25753a == null) {
            throw new IllegalArgumentException("encoder requires random");
        }
    }

    @Override // org.p415a.p427d.InterfaceC12630a
    /* renamed from: a */
    public byte[] mo1397a(byte[] bArr, int i, int i2) {
        return this.f25755c ? m1456b(bArr, i, i2) : m1453d(bArr, i, i2);
    }

    @Override // org.p415a.p427d.InterfaceC12630a
    /* renamed from: b */
    public int mo1396b() {
        int mo1396b = this.f25754b.mo1396b();
        return this.f25755c ? mo1396b : mo1396b - 10;
    }
}
