package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.c.m0.m0 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0582m0 implements InterfaceC0542i {

    /* renamed from: a */
    public byte[] f1857a;

    /* renamed from: b */
    public InterfaceC0542i f1858b;

    public C0582m0(InterfaceC0542i interfaceC0542i, byte[] bArr) {
        this(interfaceC0542i, bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public InterfaceC0542i m22788a() {
        return this.f1858b;
    }

    /* renamed from: b */
    public byte[] m22787b() {
        return this.f1857a;
    }

    public C0582m0(InterfaceC0542i interfaceC0542i, byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        this.f1857a = bArr2;
        this.f1858b = interfaceC0542i;
        System.arraycopy(bArr, i, bArr2, 0, i2);
    }
}
