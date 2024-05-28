package org.p415a.p424c;

import java.io.ByteArrayOutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.ah */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12586ah extends AbstractC12611h {

    /* renamed from: a */
    private int f25568a;

    /* renamed from: b */
    private int f25569b;

    /* renamed from: c */
    private C12629z f25570c;

    /* renamed from: d */
    private byte[] f25571d;

    public C12586ah(int i, C12629z c12629z, byte[] bArr) {
        this.f25568a = 4;
        this.f25569b = i;
        this.f25570c = c12629z;
        this.f25571d = bArr;
    }

    public C12586ah(C12592b c12592b) {
        this.f25568a = c12592b.read();
        this.f25569b = c12592b.read();
        this.f25570c = new C12629z(c12592b);
        this.f25571d = c12592b.m1577a();
    }

    @Override // org.p415a.p424c.AbstractC12611h
    /* renamed from: a */
    public void mo1537a(C12608e c12608e) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        C12608e c12608e2 = new C12608e(byteArrayOutputStream);
        c12608e2.write(this.f25568a);
        c12608e2.write(this.f25569b);
        c12608e2.m1564a(this.f25570c);
        byte[] bArr = this.f25571d;
        if (bArr != null && bArr.length > 0) {
            c12608e2.write(bArr);
        }
        c12608e2.close();
        c12608e.m1566a(3, byteArrayOutputStream.toByteArray(), true);
    }
}
