package org.p415a.p427d.p434g;

import org.p415a.p427d.AbstractC12732q;
import org.p415a.p427d.InterfaceC12680d;
import org.p415a.p427d.InterfaceC12696f;
import org.p415a.p427d.p435h.C12720p;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.g.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12702b extends AbstractC12732q {

    /* renamed from: a */
    private byte[] f25876a;

    /* renamed from: b */
    private byte[] f25877b;

    /* renamed from: c */
    private byte[] f25878c;

    /* renamed from: d */
    private byte[] f25879d;

    /* renamed from: e */
    private int f25880e;

    /* renamed from: f */
    private InterfaceC12680d f25881f;

    /* renamed from: g */
    private boolean f25882g;

    /* renamed from: h */
    private int f25883h;

    public C12702b(InterfaceC12680d interfaceC12680d, int i) {
        super(interfaceC12680d);
        this.f25881f = null;
        this.f25881f = interfaceC12680d;
        this.f25880e = i / 8;
        this.f25876a = new byte[interfaceC12680d.mo1349b()];
        this.f25877b = new byte[interfaceC12680d.mo1349b()];
        this.f25878c = new byte[interfaceC12680d.mo1349b()];
        this.f25879d = new byte[this.f25880e];
    }

    /* renamed from: c */
    private byte m1355c(byte b) {
        if (this.f25883h == 0) {
            this.f25881f.mo1350a(this.f25877b, 0, this.f25878c, 0);
        }
        byte[] bArr = this.f25878c;
        int i = this.f25883h;
        byte b2 = (byte) (b ^ bArr[i]);
        byte[] bArr2 = this.f25879d;
        this.f25883h = i + 1;
        bArr2[i] = b2;
        int i2 = this.f25883h;
        int i3 = this.f25880e;
        if (i2 == i3) {
            this.f25883h = 0;
            byte[] bArr3 = this.f25877b;
            System.arraycopy(bArr3, i3, bArr3, 0, bArr3.length - i3);
            byte[] bArr4 = this.f25879d;
            byte[] bArr5 = this.f25877b;
            int length = bArr5.length;
            int i4 = this.f25880e;
            System.arraycopy(bArr4, 0, bArr5, length - i4, i4);
        }
        return b2;
    }

    /* renamed from: d */
    private byte m1354d(byte b) {
        if (this.f25883h == 0) {
            this.f25881f.mo1350a(this.f25877b, 0, this.f25878c, 0);
        }
        byte[] bArr = this.f25879d;
        int i = this.f25883h;
        bArr[i] = b;
        byte[] bArr2 = this.f25878c;
        this.f25883h = i + 1;
        byte b2 = (byte) (b ^ bArr2[i]);
        int i2 = this.f25883h;
        int i3 = this.f25880e;
        if (i2 == i3) {
            this.f25883h = 0;
            byte[] bArr3 = this.f25877b;
            System.arraycopy(bArr3, i3, bArr3, 0, bArr3.length - i3);
            byte[] bArr4 = this.f25879d;
            byte[] bArr5 = this.f25877b;
            int length = bArr5.length;
            int i4 = this.f25880e;
            System.arraycopy(bArr4, 0, bArr5, length - i4, i4);
        }
        return b2;
    }

    @Override // org.p415a.p427d.InterfaceC12680d
    /* renamed from: a */
    public int mo1350a(byte[] bArr, int i, byte[] bArr2, int i2) {
        mo1305a(bArr, i, this.f25880e, bArr2, i2);
        return this.f25880e;
    }

    @Override // org.p415a.p427d.InterfaceC12680d
    /* renamed from: a */
    public String mo1353a() {
        return this.f25881f.mo1353a() + "/CFB" + (this.f25880e * 8);
    }

    @Override // org.p415a.p427d.InterfaceC12680d
    /* renamed from: a */
    public void mo1351a(boolean z, InterfaceC12696f interfaceC12696f) {
        InterfaceC12680d interfaceC12680d;
        this.f25882g = z;
        if (interfaceC12696f instanceof C12720p) {
            C12720p c12720p = (C12720p) interfaceC12696f;
            byte[] m1327a = c12720p.m1327a();
            int length = m1327a.length;
            byte[] bArr = this.f25876a;
            if (length < bArr.length) {
                System.arraycopy(m1327a, 0, bArr, bArr.length - m1327a.length, m1327a.length);
                int i = 0;
                while (true) {
                    byte[] bArr2 = this.f25876a;
                    if (i >= bArr2.length - m1327a.length) {
                        break;
                    }
                    bArr2[i] = 0;
                    i++;
                }
            } else {
                System.arraycopy(m1327a, 0, bArr, 0, bArr.length);
            }
            mo1347c();
            if (c12720p.m1326b() == null) {
                return;
            }
            interfaceC12680d = this.f25881f;
            interfaceC12696f = c12720p.m1326b();
        } else {
            mo1347c();
            if (interfaceC12696f == null) {
                return;
            }
            interfaceC12680d = this.f25881f;
        }
        interfaceC12680d.mo1351a(true, interfaceC12696f);
    }

    @Override // org.p415a.p427d.AbstractC12732q
    /* renamed from: b */
    public byte mo1307b(byte b) {
        return this.f25882g ? m1355c(b) : m1354d(b);
    }

    @Override // org.p415a.p427d.InterfaceC12680d
    /* renamed from: b */
    public int mo1349b() {
        return this.f25880e;
    }

    @Override // org.p415a.p427d.InterfaceC12680d
    /* renamed from: c */
    public void mo1347c() {
        byte[] bArr = this.f25876a;
        System.arraycopy(bArr, 0, this.f25877b, 0, bArr.length);
        C12957a.m440a(this.f25879d, (byte) 0);
        this.f25883h = 0;
        this.f25881f.mo1347c();
    }
}
