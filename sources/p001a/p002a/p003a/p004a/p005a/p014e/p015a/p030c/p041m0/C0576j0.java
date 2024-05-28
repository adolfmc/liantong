package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.c.m0.j0 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0576j0 implements InterfaceC0542i {

    /* renamed from: a */
    public byte[] f1839a;

    /* renamed from: b */
    public InterfaceC0542i f1840b;

    public C0576j0(InterfaceC0542i interfaceC0542i, byte[] bArr) {
        this(interfaceC0542i, bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public byte[] m22804a() {
        return this.f1839a;
    }

    /* renamed from: b */
    public InterfaceC0542i m22803b() {
        return this.f1840b;
    }

    public C0576j0(InterfaceC0542i interfaceC0542i, byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        this.f1839a = bArr2;
        this.f1840b = interfaceC0542i;
        System.arraycopy(bArr, i, bArr2, 0, i2);
    }
}
