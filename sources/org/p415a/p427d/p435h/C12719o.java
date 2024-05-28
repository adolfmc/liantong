package org.p415a.p427d.p435h;

import org.p415a.p427d.InterfaceC12696f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.h.o */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12719o implements InterfaceC12696f {

    /* renamed from: a */
    private byte[] f25915a;

    public C12719o(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public C12719o(byte[] bArr, int i, int i2) {
        this.f25915a = new byte[i2];
        System.arraycopy(bArr, i, this.f25915a, 0, i2);
    }

    /* renamed from: a */
    public byte[] m1328a() {
        return this.f25915a;
    }
}
