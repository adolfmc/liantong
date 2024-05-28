package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0669a;

/* renamed from: a.a.a.a.a.e.a.c.m0.p */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0587p {

    /* renamed from: a */
    public int f1865a;

    /* renamed from: b */
    public byte[] f1866b;

    /* renamed from: c */
    public int f1867c;

    public C0587p(byte[] bArr, int i) {
        this(bArr, i, -1);
    }

    /* renamed from: a */
    public int m22780a() {
        return this.f1867c;
    }

    /* renamed from: b */
    public byte[] m22779b() {
        return this.f1866b;
    }

    /* renamed from: c */
    public int m22778c() {
        return this.f1865a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C0587p) {
            C0587p c0587p = (C0587p) obj;
            if (c0587p.f1867c != this.f1867c) {
                return false;
            }
            return C0669a.m22499a(this.f1866b, c0587p.f1866b);
        }
        return false;
    }

    public int hashCode() {
        return this.f1867c ^ C0669a.m22472b(this.f1866b);
    }

    public C0587p(byte[] bArr, int i, int i2) {
        this.f1866b = bArr;
        this.f1867c = i;
        this.f1865a = i2;
    }
}
