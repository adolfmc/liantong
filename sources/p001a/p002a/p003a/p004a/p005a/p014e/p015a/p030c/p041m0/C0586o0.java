package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.c.m0.o0 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0586o0 implements InterfaceC0542i {

    /* renamed from: a */
    public byte[] f1863a;

    /* renamed from: b */
    public int f1864b;

    public C0586o0(byte[] bArr, int i) {
        if (bArr.length <= 255) {
            byte[] bArr2 = new byte[bArr.length];
            this.f1863a = bArr2;
            this.f1864b = i;
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            return;
        }
        throw new IllegalArgumentException("RC5 key length can be no greater than 255");
    }

    /* renamed from: a */
    public byte[] m22782a() {
        return this.f1863a;
    }

    /* renamed from: b */
    public int m22781b() {
        return this.f1864b;
    }
}
