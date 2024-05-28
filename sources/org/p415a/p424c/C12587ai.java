package org.p415a.p424c;

import java.io.ByteArrayOutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.ai */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12587ai extends AbstractC12611h {

    /* renamed from: a */
    byte[] f25572a;

    public C12587ai(C12592b c12592b) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = c12592b.read();
            if (read < 0) {
                this.f25572a = byteArrayOutputStream.toByteArray();
                return;
            }
            byteArrayOutputStream.write(read);
        }
    }

    @Override // org.p415a.p424c.AbstractC12611h
    /* renamed from: a */
    public void mo1537a(C12608e c12608e) {
        c12608e.m1566a(12, this.f25572a, true);
    }
}
