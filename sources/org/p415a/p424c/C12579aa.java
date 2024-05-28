package org.p415a.p424c;

import java.io.ByteArrayOutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.aa */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12579aa extends AbstractC12611h {

    /* renamed from: a */
    private C12626w f25545a;

    /* renamed from: b */
    private byte[] f25546b;

    /* renamed from: c */
    private int f25547c;

    /* renamed from: d */
    private int f25548d;

    /* renamed from: e */
    private C12629z f25549e;

    /* renamed from: f */
    private byte[] f25550f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12579aa(C12592b c12592b) {
        this.f25545a = this instanceof C12580ab ? new C12627x(c12592b) : new C12626w(c12592b);
        this.f25547c = c12592b.read();
        int i = this.f25547c;
        if (i == 255 || i == 254) {
            this.f25548d = c12592b.read();
            this.f25549e = new C12629z(c12592b);
        } else {
            this.f25548d = i;
        }
        C12629z c12629z = this.f25549e;
        if ((c12629z == null || c12629z.m1532b() != 101 || this.f25549e.m1528f() != 1) && this.f25547c != 0) {
            this.f25550f = new byte[this.f25548d < 7 ? 8 : 16];
            byte[] bArr = this.f25550f;
            c12592b.m1575a(bArr, 0, bArr.length);
        }
        this.f25546b = c12592b.m1577a();
    }

    /* renamed from: a */
    public C12626w m1587a() {
        return this.f25545a;
    }

    @Override // org.p415a.p424c.AbstractC12611h
    /* renamed from: a */
    public void mo1537a(C12608e c12608e) {
        c12608e.m1566a(5, m1586b(), true);
    }

    /* renamed from: b */
    public byte[] m1586b() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        C12608e c12608e = new C12608e(byteArrayOutputStream);
        c12608e.write(this.f25545a.m1538d());
        c12608e.write(this.f25547c);
        int i = this.f25547c;
        if (i == 255 || i == 254) {
            c12608e.write(this.f25548d);
            c12608e.m1564a(this.f25549e);
        }
        byte[] bArr = this.f25550f;
        if (bArr != null) {
            c12608e.write(bArr);
        }
        byte[] bArr2 = this.f25546b;
        if (bArr2 != null && bArr2.length > 0) {
            c12608e.write(bArr2);
        }
        c12608e.close();
        return byteArrayOutputStream.toByteArray();
    }
}
