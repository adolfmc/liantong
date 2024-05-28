package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0669a;

/* renamed from: a.a.a.a.a.e.a.c.m0.i */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0573i {

    /* renamed from: a */
    public byte[] f1834a;

    /* renamed from: b */
    public int f1835b;

    public C0573i(byte[] bArr, int i) {
        this.f1834a = bArr;
        this.f1835b = i;
    }

    /* renamed from: a */
    public int m22809a() {
        return this.f1835b;
    }

    /* renamed from: b */
    public byte[] m22808b() {
        return this.f1834a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C0573i) {
            C0573i c0573i = (C0573i) obj;
            if (c0573i.f1835b != this.f1835b) {
                return false;
            }
            return C0669a.m22499a(this.f1834a, c0573i.f1834a);
        }
        return false;
    }

    public int hashCode() {
        return this.f1835b ^ C0669a.m22472b(this.f1834a);
    }
}
