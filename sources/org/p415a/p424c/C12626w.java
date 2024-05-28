package org.p415a.p424c;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.w */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12626w extends AbstractC12611h {

    /* renamed from: a */
    private int f25623a;

    /* renamed from: b */
    private long f25624b;

    /* renamed from: c */
    private int f25625c;

    /* renamed from: d */
    private int f25626d;

    /* renamed from: e */
    private InterfaceC12606c f25627e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12626w(C12592b c12592b) {
        InterfaceC12606c c12628y;
        this.f25623a = c12592b.read();
        this.f25624b = (c12592b.read() << 24) | (c12592b.read() << 16) | (c12592b.read() << 8) | c12592b.read();
        if (this.f25623a <= 3) {
            this.f25625c = (c12592b.read() << 8) | c12592b.read();
        }
        this.f25626d = (byte) c12592b.read();
        int i = this.f25626d;
        switch (i) {
            case 1:
            case 2:
            case 3:
                c12628y = new C12628y(c12592b);
                break;
            default:
                switch (i) {
                    case 16:
                    case 20:
                        c12628y = new C12616m(c12592b);
                        break;
                    case 17:
                        c12628y = new C12612i(c12592b);
                        break;
                    case 18:
                        c12628y = new C12613j(c12592b);
                        break;
                    case 19:
                        c12628y = new C12614k(c12592b);
                        break;
                    default:
                        throw new IOException("unknown PGP public key algorithm encountered");
                }
        }
        this.f25627e = c12628y;
    }

    /* renamed from: a */
    public int m1541a() {
        return this.f25623a;
    }

    @Override // org.p415a.p424c.AbstractC12611h
    /* renamed from: a */
    public void mo1537a(C12608e c12608e) {
        c12608e.m1566a(6, m1538d(), true);
    }

    /* renamed from: b */
    public int m1540b() {
        return this.f25626d;
    }

    /* renamed from: c */
    public InterfaceC12606c m1539c() {
        return this.f25627e;
    }

    /* renamed from: d */
    public byte[] m1538d() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        C12608e c12608e = new C12608e(byteArrayOutputStream);
        c12608e.write(this.f25623a);
        c12608e.write((byte) (this.f25624b >> 24));
        c12608e.write((byte) (this.f25624b >> 16));
        c12608e.write((byte) (this.f25624b >> 8));
        c12608e.write((byte) this.f25624b);
        if (this.f25623a <= 3) {
            c12608e.write((byte) (this.f25625c >> 8));
            c12608e.write((byte) this.f25625c);
        }
        c12608e.write(this.f25626d);
        c12608e.m1564a((AbstractC12607d) this.f25627e);
        c12608e.close();
        return byteArrayOutputStream.toByteArray();
    }
}
