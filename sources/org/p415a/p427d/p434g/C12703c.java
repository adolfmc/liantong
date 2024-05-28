package org.p415a.p427d.p434g;

import org.p415a.p427d.C12704h;
import org.p415a.p427d.C12730o;
import org.p415a.p427d.InterfaceC12680d;
import org.p415a.p427d.InterfaceC12696f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.g.c */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12703c implements InterfaceC12680d {

    /* renamed from: a */
    private byte[] f25884a;

    /* renamed from: b */
    private byte[] f25885b;

    /* renamed from: c */
    private byte[] f25886c;

    /* renamed from: d */
    private InterfaceC12680d f25887d;

    /* renamed from: e */
    private int f25888e;

    /* renamed from: f */
    private int f25889f;

    /* renamed from: g */
    private boolean f25890g;

    public C12703c(InterfaceC12680d interfaceC12680d) {
        this.f25887d = interfaceC12680d;
        this.f25889f = interfaceC12680d.mo1349b();
        int i = this.f25889f;
        this.f25884a = new byte[i];
        this.f25885b = new byte[i];
        this.f25886c = new byte[i];
    }

    /* renamed from: a */
    private byte m1352a(byte b, int i) {
        return (byte) (b ^ this.f25886c[i]);
    }

    /* renamed from: b */
    private int m1348b(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3;
        int i4 = this.f25889f;
        if (i + i4 <= bArr.length) {
            if (i2 + i4 <= bArr2.length) {
                int i5 = this.f25888e;
                int i6 = 2;
                int i7 = 0;
                if (i5 > i4) {
                    byte[] bArr3 = this.f25885b;
                    int i8 = i4 - 2;
                    byte m1352a = m1352a(bArr[i], i4 - 2);
                    bArr2[i2] = m1352a;
                    bArr3[i8] = m1352a;
                    byte[] bArr4 = this.f25885b;
                    int i9 = this.f25889f;
                    int i10 = i9 - 1;
                    byte m1352a2 = m1352a(bArr[i + 1], i9 - 1);
                    bArr2[i2 + 1] = m1352a2;
                    bArr4[i10] = m1352a2;
                    this.f25887d.mo1350a(this.f25885b, 0, this.f25886c, 0);
                    while (i6 < this.f25889f) {
                        byte[] bArr5 = this.f25885b;
                        int i11 = i6 - 2;
                        byte m1352a3 = m1352a(bArr[i + i6], i11);
                        bArr2[i2 + i6] = m1352a3;
                        bArr5[i11] = m1352a3;
                        i6++;
                    }
                } else {
                    if (i5 != 0) {
                        if (i5 == i4) {
                            this.f25887d.mo1350a(this.f25885b, 0, this.f25886c, 0);
                            bArr2[i2] = m1352a(bArr[i], 0);
                            bArr2[i2 + 1] = m1352a(bArr[i + 1], 1);
                            byte[] bArr6 = this.f25885b;
                            System.arraycopy(bArr6, 2, bArr6, 0, this.f25889f - 2);
                            System.arraycopy(bArr2, i2, this.f25885b, this.f25889f - 2, 2);
                            this.f25887d.mo1350a(this.f25885b, 0, this.f25886c, 0);
                            while (true) {
                                i3 = this.f25889f;
                                if (i6 >= i3) {
                                    break;
                                }
                                byte[] bArr7 = this.f25885b;
                                int i12 = i6 - 2;
                                byte m1352a4 = m1352a(bArr[i + i6], i12);
                                bArr2[i2 + i6] = m1352a4;
                                bArr7[i12] = m1352a4;
                                i6++;
                            }
                        }
                    } else {
                        this.f25887d.mo1350a(this.f25885b, 0, this.f25886c, 0);
                        while (true) {
                            i3 = this.f25889f;
                            if (i7 >= i3) {
                                break;
                            }
                            byte[] bArr8 = this.f25885b;
                            byte m1352a5 = m1352a(bArr[i + i7], i7);
                            bArr2[i2 + i7] = m1352a5;
                            bArr8[i7] = m1352a5;
                            i7++;
                        }
                    }
                    this.f25888e += i3;
                }
                return this.f25889f;
            }
            throw new C12730o("output buffer too short");
        }
        throw new C12704h("input buffer too short");
    }

    /* renamed from: c */
    private int m1346c(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3;
        int i4;
        int i5;
        int i6 = this.f25889f;
        if (i + i6 <= bArr.length) {
            if (i2 + i6 <= bArr2.length) {
                int i7 = this.f25888e;
                int i8 = 2;
                int i9 = 0;
                if (i7 > i6) {
                    byte b = bArr[i];
                    this.f25885b[i6 - 2] = b;
                    bArr2[i2] = m1352a(b, i6 - 2);
                    byte b2 = bArr[i + 1];
                    byte[] bArr3 = this.f25885b;
                    int i10 = this.f25889f;
                    bArr3[i10 - 1] = b2;
                    bArr2[i2 + 1] = m1352a(b2, i10 - 1);
                    this.f25887d.mo1350a(this.f25885b, 0, this.f25886c, 0);
                    while (i8 < this.f25889f) {
                        byte b3 = bArr[i + i8];
                        int i11 = i8 - 2;
                        this.f25885b[i11] = b3;
                        bArr2[i2 + i8] = m1352a(b3, i11);
                        i8++;
                    }
                } else {
                    if (i7 == 0) {
                        this.f25887d.mo1350a(this.f25885b, 0, this.f25886c, 0);
                        while (true) {
                            i5 = this.f25889f;
                            if (i9 >= i5) {
                                break;
                            }
                            int i12 = i + i9;
                            this.f25885b[i9] = bArr[i12];
                            bArr2[i9] = m1352a(bArr[i12], i9);
                            i9++;
                        }
                        i4 = this.f25888e + i5;
                    } else if (i7 == i6) {
                        this.f25887d.mo1350a(this.f25885b, 0, this.f25886c, 0);
                        byte b4 = bArr[i];
                        byte b5 = bArr[i + 1];
                        bArr2[i2] = m1352a(b4, 0);
                        bArr2[i2 + 1] = m1352a(b5, 1);
                        byte[] bArr4 = this.f25885b;
                        System.arraycopy(bArr4, 2, bArr4, 0, this.f25889f - 2);
                        byte[] bArr5 = this.f25885b;
                        int i13 = this.f25889f;
                        bArr5[i13 - 2] = b4;
                        bArr5[i13 - 1] = b5;
                        this.f25887d.mo1350a(bArr5, 0, this.f25886c, 0);
                        while (true) {
                            i3 = this.f25889f;
                            if (i8 >= i3) {
                                break;
                            }
                            byte b6 = bArr[i + i8];
                            int i14 = i8 - 2;
                            this.f25885b[i14] = b6;
                            bArr2[i2 + i8] = m1352a(b6, i14);
                            i8++;
                        }
                        i4 = this.f25888e + i3;
                    }
                    this.f25888e = i4;
                }
                return this.f25889f;
            }
            throw new C12730o("output buffer too short");
        }
        throw new C12704h("input buffer too short");
    }

    @Override // org.p415a.p427d.InterfaceC12680d
    /* renamed from: a */
    public int mo1350a(byte[] bArr, int i, byte[] bArr2, int i2) {
        return this.f25890g ? m1348b(bArr, i, bArr2, i2) : m1346c(bArr, i, bArr2, i2);
    }

    @Override // org.p415a.p427d.InterfaceC12680d
    /* renamed from: a */
    public String mo1353a() {
        return this.f25887d.mo1353a() + "/OpenPGPCFB";
    }

    @Override // org.p415a.p427d.InterfaceC12680d
    /* renamed from: a */
    public void mo1351a(boolean z, InterfaceC12696f interfaceC12696f) {
        this.f25890g = z;
        mo1347c();
        this.f25887d.mo1351a(true, interfaceC12696f);
    }

    @Override // org.p415a.p427d.InterfaceC12680d
    /* renamed from: b */
    public int mo1349b() {
        return this.f25887d.mo1349b();
    }

    @Override // org.p415a.p427d.InterfaceC12680d
    /* renamed from: c */
    public void mo1347c() {
        this.f25888e = 0;
        byte[] bArr = this.f25884a;
        byte[] bArr2 = this.f25885b;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.f25887d.mo1347c();
    }
}
