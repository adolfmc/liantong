package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p033e0;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f;

/* renamed from: a.a.a.a.a.e.a.c.e0.g */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0522g {

    /* renamed from: a */
    public final AbstractC0655f f1702a;

    /* renamed from: b */
    public final AbstractC0655f f1703b;

    public C0522g(AbstractC0655f abstractC0655f, AbstractC0655f abstractC0655f2) {
        this.f1702a = abstractC0655f;
        this.f1703b = abstractC0655f2;
    }

    /* renamed from: a */
    public byte[] m22925a() {
        byte[] m22579c = this.f1702a.m22579c();
        byte[] m22579c2 = this.f1703b.m22579c();
        byte[] bArr = new byte[m22579c.length + m22579c2.length];
        System.arraycopy(m22579c, 0, bArr, 0, m22579c.length);
        System.arraycopy(m22579c2, 0, bArr, m22579c.length, m22579c2.length);
        return bArr;
    }

    /* renamed from: b */
    public AbstractC0655f m22924b() {
        return this.f1702a;
    }

    /* renamed from: c */
    public AbstractC0655f m22923c() {
        return this.f1703b;
    }
}
