package org.p415a.p427d.p431d;

import org.p415a.p427d.C12704h;
import org.p415a.p427d.InterfaceC12680d;
import org.p415a.p427d.InterfaceC12696f;
import org.p415a.p427d.InterfaceC12734s;
import org.p415a.p427d.p435h.C12719o;
import org.p415a.p427d.p435h.C12720p;
import org.p415a.p427d.p435h.C12721q;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.d.i */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12689i implements InterfaceC12734s {

    /* renamed from: a */
    private InterfaceC12680d f25838a;

    /* renamed from: b */
    private boolean f25839b;

    /* renamed from: c */
    private C12719o f25840c;

    /* renamed from: d */
    private boolean f25841d;

    /* renamed from: e */
    private byte[] f25842e;

    public C12689i(InterfaceC12680d interfaceC12680d) {
        this(interfaceC12680d, false);
    }

    public C12689i(InterfaceC12680d interfaceC12680d, boolean z) {
        this.f25842e = new byte[]{-90, -90, -90, -90, -90, -90, -90, -90};
        this.f25838a = interfaceC12680d;
        this.f25839b = !z;
    }

    @Override // org.p415a.p427d.InterfaceC12734s
    /* renamed from: a */
    public void mo1303a(boolean z, InterfaceC12696f interfaceC12696f) {
        this.f25841d = z;
        if (interfaceC12696f instanceof C12721q) {
            interfaceC12696f = ((C12721q) interfaceC12696f).m1324b();
        }
        if (interfaceC12696f instanceof C12719o) {
            this.f25840c = (C12719o) interfaceC12696f;
        } else if (interfaceC12696f instanceof C12720p) {
            C12720p c12720p = (C12720p) interfaceC12696f;
            this.f25842e = c12720p.m1327a();
            this.f25840c = (C12719o) c12720p.m1326b();
            if (this.f25842e.length != 8) {
                throw new IllegalArgumentException("IV not equal to 8");
            }
        }
    }

    @Override // org.p415a.p427d.InterfaceC12734s
    /* renamed from: a */
    public byte[] mo1302a(byte[] bArr, int i, int i2) {
        if (this.f25841d) {
            int i3 = i2 / 8;
            if (i3 * 8 == i2) {
                byte[] bArr2 = this.f25842e;
                byte[] bArr3 = new byte[bArr2.length + i2];
                byte[] bArr4 = new byte[bArr2.length + 8];
                System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
                System.arraycopy(bArr, i, bArr3, this.f25842e.length, i2);
                this.f25838a.mo1351a(this.f25839b, this.f25840c);
                for (int i4 = 0; i4 != 6; i4++) {
                    for (int i5 = 1; i5 <= i3; i5++) {
                        System.arraycopy(bArr3, 0, bArr4, 0, this.f25842e.length);
                        int i6 = i5 * 8;
                        System.arraycopy(bArr3, i6, bArr4, this.f25842e.length, 8);
                        this.f25838a.mo1350a(bArr4, 0, bArr4, 0);
                        int i7 = (i3 * i4) + i5;
                        int i8 = 1;
                        while (i7 != 0) {
                            int length = this.f25842e.length - i8;
                            bArr4[length] = (byte) (((byte) i7) ^ bArr4[length]);
                            i7 >>>= 8;
                            i8++;
                        }
                        System.arraycopy(bArr4, 0, bArr3, 0, 8);
                        System.arraycopy(bArr4, 8, bArr3, i6, 8);
                    }
                }
                return bArr3;
            }
            throw new C12704h("wrap data must be a multiple of 8 bytes");
        }
        throw new IllegalStateException("not set for wrapping");
    }
}
