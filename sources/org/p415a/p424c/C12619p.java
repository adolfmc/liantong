package org.p415a.p424c;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.p */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12619p extends C12618o {

    /* renamed from: a */
    int f25607a;

    /* renamed from: b */
    byte[] f25608b;

    /* renamed from: c */
    long f25609c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12619p(C12592b c12592b) {
        super(c12592b);
        this.f25607a = c12592b.read();
        this.f25608b = new byte[c12592b.read()];
        int i = 0;
        while (true) {
            byte[] bArr = this.f25608b;
            if (i == bArr.length) {
                this.f25609c = (c12592b.read() << 24) | (c12592b.read() << 16) | (c12592b.read() << 8) | c12592b.read();
                return;
            } else {
                bArr[i] = (byte) c12592b.read();
                i++;
            }
        }
    }
}
