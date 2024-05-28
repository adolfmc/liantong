package org.p415a.p418b;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.ac */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12411ac extends AbstractC12405a {
    public C12411ac(int i, C12555g c12555g) {
        super(true, i, m1733a(c12555g));
    }

    /* renamed from: a */
    private static byte[] m1733a(C12555g c12555g) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i != c12555g.m1661a(); i++) {
            try {
                byteArrayOutputStream.write(((AbstractC12562n) c12555g.m1660a(i)).m1644a("BER"));
            } catch (IOException e) {
                throw new C12569s("malformed object: " + e, e);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12405a, org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public void mo1597a(C12567r c12567r) {
        c12567r.m1628a(this.f25104a ? 96 : 64, this.f25105b);
        c12567r.mo1620b(128);
        c12567r.m1623a(this.f25106c);
        c12567r.mo1620b(0);
        c12567r.mo1620b(0);
    }
}
