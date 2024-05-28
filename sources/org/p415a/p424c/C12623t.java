package org.p415a.p424c;

import java.io.ByteArrayOutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.t */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12623t extends AbstractC12611h {

    /* renamed from: a */
    private int f25613a;

    /* renamed from: b */
    private int f25614b;

    /* renamed from: c */
    private int f25615c;

    /* renamed from: d */
    private int f25616d;

    /* renamed from: e */
    private long f25617e;

    /* renamed from: f */
    private int f25618f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12623t(C12592b c12592b) {
        this.f25613a = c12592b.read();
        this.f25614b = c12592b.read();
        this.f25615c = c12592b.read();
        this.f25616d = c12592b.read();
        this.f25617e |= c12592b.read() << 56;
        this.f25617e |= c12592b.read() << 48;
        this.f25617e |= c12592b.read() << 40;
        this.f25617e |= c12592b.read() << 32;
        this.f25617e |= c12592b.read() << 24;
        this.f25617e |= c12592b.read() << 16;
        this.f25617e |= c12592b.read() << 8;
        this.f25617e |= c12592b.read();
        this.f25618f = c12592b.read();
    }

    /* renamed from: a */
    public int m1542a() {
        return this.f25614b;
    }

    @Override // org.p415a.p424c.AbstractC12611h
    /* renamed from: a */
    public void mo1537a(C12608e c12608e) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        C12608e c12608e2 = new C12608e(byteArrayOutputStream);
        c12608e2.write(this.f25613a);
        c12608e2.write(this.f25614b);
        c12608e2.write(this.f25615c);
        c12608e2.write(this.f25616d);
        c12608e2.write((byte) (this.f25617e >> 56));
        c12608e2.write((byte) (this.f25617e >> 48));
        c12608e2.write((byte) (this.f25617e >> 40));
        c12608e2.write((byte) (this.f25617e >> 32));
        c12608e2.write((byte) (this.f25617e >> 24));
        c12608e2.write((byte) (this.f25617e >> 16));
        c12608e2.write((byte) (this.f25617e >> 8));
        c12608e2.write((byte) this.f25617e);
        c12608e2.write(this.f25618f);
        c12608e2.close();
        c12608e.m1566a(4, byteArrayOutputStream.toByteArray(), true);
    }
}
