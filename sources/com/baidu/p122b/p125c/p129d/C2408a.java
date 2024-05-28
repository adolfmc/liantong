package com.baidu.p122b.p125c.p129d;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.c.d.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2408a {

    /* renamed from: a */
    private C2410c f4238a;

    private C2408a() {
    }

    /* renamed from: a */
    public static C2408a m20242a() {
        C2408a c2408a = new C2408a();
        c2408a.f4238a = new C2410c();
        c2408a.f4238a.m20230a("PKCS1Padding");
        return c2408a;
    }

    /* renamed from: a */
    public void m20241a(int i, InterfaceC2411d interfaceC2411d) {
        this.f4238a.m20232a(i, interfaceC2411d, C2409b.f4239a);
    }

    /* renamed from: a */
    public final byte[] m20240a(byte[] bArr) {
        if (bArr != null) {
            return this.f4238a.m20229a(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("Null input buffer");
    }
}
