package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c;

import java.security.SecureRandom;

/* renamed from: a.a.a.a.a.e.a.c.h */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0533h {

    /* renamed from: a */
    public SecureRandom f1739a;

    /* renamed from: b */
    public int f1740b;

    /* renamed from: a */
    public void m22885a(C0639t c0639t) {
        this.f1739a = c0639t.m22659a();
        this.f1740b = (c0639t.m22658b() + 7) / 8;
    }

    /* renamed from: a */
    public byte[] m22886a() {
        byte[] bArr = new byte[this.f1740b];
        this.f1739a.nextBytes(bArr);
        return bArr;
    }
}
