package org.p415a.p427d.p431d;

import org.p415a.p427d.C12704h;
import org.p415a.p427d.C12730o;
import org.p415a.p427d.InterfaceC12696f;
import org.p415a.p427d.p435h.C12719o;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.d.f */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12686f extends C12685e {

    /* renamed from: a */
    private int[] f25826a = null;

    /* renamed from: b */
    private int[] f25827b = null;

    /* renamed from: c */
    private int[] f25828c = null;

    /* renamed from: d */
    private boolean f25829d;

    @Override // org.p415a.p427d.p431d.C12685e, org.p415a.p427d.InterfaceC12680d
    /* renamed from: a */
    public int mo1350a(byte[] bArr, int i, byte[] bArr2, int i2) {
        C12686f c12686f;
        int i3;
        byte[] bArr3;
        int[] iArr;
        int[] iArr2 = this.f25826a;
        if (iArr2 != null) {
            if (i + 8 <= bArr.length) {
                if (i2 + 8 <= bArr2.length) {
                    byte[] bArr4 = new byte[8];
                    if (this.f25829d) {
                        c12686f = this;
                        c12686f.m1409a(iArr2, bArr, i, bArr4, 0);
                        i3 = 0;
                        bArr3 = bArr4;
                        c12686f.m1409a(this.f25827b, bArr3, 0, bArr4, 0);
                        iArr = this.f25828c;
                    } else {
                        c12686f = this;
                        c12686f.m1409a(this.f25828c, bArr, i, bArr4, 0);
                        i3 = 0;
                        bArr3 = bArr4;
                        c12686f.m1409a(this.f25827b, bArr3, 0, bArr4, 0);
                        iArr = this.f25826a;
                    }
                    c12686f.m1409a(iArr, bArr3, i3, bArr2, i2);
                    return 8;
                }
                throw new C12730o("output buffer too short");
            }
            throw new C12704h("input buffer too short");
        }
        throw new IllegalStateException("DESede engine not initialised");
    }

    @Override // org.p415a.p427d.p431d.C12685e, org.p415a.p427d.InterfaceC12680d
    /* renamed from: a */
    public String mo1353a() {
        return "DESede";
    }

    @Override // org.p415a.p427d.p431d.C12685e, org.p415a.p427d.InterfaceC12680d
    /* renamed from: a */
    public void mo1351a(boolean z, InterfaceC12696f interfaceC12696f) {
        int[] iArr;
        if (!(interfaceC12696f instanceof C12719o)) {
            throw new IllegalArgumentException("invalid parameter passed to DESede init - " + interfaceC12696f.getClass().getName());
        }
        byte[] m1328a = ((C12719o) interfaceC12696f).m1328a();
        if (m1328a.length != 24 && m1328a.length != 16) {
            throw new IllegalArgumentException("key size must be 16 or 24 bytes.");
        }
        this.f25829d = z;
        byte[] bArr = new byte[8];
        System.arraycopy(m1328a, 0, bArr, 0, bArr.length);
        this.f25826a = m1410a(z, bArr);
        byte[] bArr2 = new byte[8];
        System.arraycopy(m1328a, 8, bArr2, 0, bArr2.length);
        this.f25827b = m1410a(!z, bArr2);
        if (m1328a.length == 24) {
            byte[] bArr3 = new byte[8];
            System.arraycopy(m1328a, 16, bArr3, 0, bArr3.length);
            iArr = m1410a(z, bArr3);
        } else {
            iArr = this.f25826a;
        }
        this.f25828c = iArr;
    }

    @Override // org.p415a.p427d.p431d.C12685e, org.p415a.p427d.InterfaceC12680d
    /* renamed from: b */
    public int mo1349b() {
        return 8;
    }

    @Override // org.p415a.p427d.p431d.C12685e, org.p415a.p427d.InterfaceC12680d
    /* renamed from: c */
    public void mo1347c() {
    }
}
