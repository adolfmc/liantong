package org.p415a.p427d.p435h;

import org.p415a.p427d.InterfaceC12696f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.h.p */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12720p implements InterfaceC12696f {

    /* renamed from: a */
    private byte[] f25916a;

    /* renamed from: b */
    private InterfaceC12696f f25917b;

    public C12720p(InterfaceC12696f interfaceC12696f, byte[] bArr) {
        this(interfaceC12696f, bArr, 0, bArr.length);
    }

    public C12720p(InterfaceC12696f interfaceC12696f, byte[] bArr, int i, int i2) {
        this.f25916a = new byte[i2];
        this.f25917b = interfaceC12696f;
        System.arraycopy(bArr, i, this.f25916a, 0, i2);
    }

    /* renamed from: a */
    public byte[] m1327a() {
        return this.f25916a;
    }

    /* renamed from: b */
    public InterfaceC12696f m1326b() {
        return this.f25917b;
    }
}
