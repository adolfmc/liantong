package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.c.m0.n0 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0584n0 implements InterfaceC0542i {

    /* renamed from: a */
    public byte[] f1860a;

    /* renamed from: b */
    public int f1861b;

    public C0584n0(byte[] bArr) {
        this(bArr, bArr.length > 128 ? 1024 : bArr.length * 8);
    }

    /* renamed from: a */
    public int m22785a() {
        return this.f1861b;
    }

    /* renamed from: b */
    public byte[] m22784b() {
        return this.f1860a;
    }

    public C0584n0(byte[] bArr, int i) {
        byte[] bArr2 = new byte[bArr.length];
        this.f1860a = bArr2;
        this.f1861b = i;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
    }
}
