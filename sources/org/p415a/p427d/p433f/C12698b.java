package org.p415a.p427d.p433f;

import java.io.FilterOutputStream;
import java.io.OutputStream;
import org.p415a.p427d.C12693e;
import org.p415a.p427d.InterfaceC12733r;
import org.p415a.p427d.p434g.InterfaceC12701a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.f.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12698b extends FilterOutputStream {

    /* renamed from: a */
    private C12693e f25870a;

    /* renamed from: b */
    private InterfaceC12733r f25871b;

    /* renamed from: c */
    private InterfaceC12701a f25872c;

    /* renamed from: d */
    private final byte[] f25873d;

    /* renamed from: e */
    private byte[] f25874e;

    public C12698b(OutputStream outputStream, C12693e c12693e) {
        super(outputStream);
        this.f25873d = new byte[1];
        this.f25870a = c12693e;
    }

    /* renamed from: a */
    private void m1360a(int i, boolean z) {
        if (z) {
            C12693e c12693e = this.f25870a;
            if (c12693e != null) {
                i = c12693e.m1365b(i);
            } else {
                InterfaceC12701a interfaceC12701a = this.f25872c;
                if (interfaceC12701a != null) {
                    i = interfaceC12701a.m1356b(i);
                }
            }
        } else {
            C12693e c12693e2 = this.f25870a;
            if (c12693e2 != null) {
                i = c12693e2.m1370a(i);
            } else {
                InterfaceC12701a interfaceC12701a2 = this.f25872c;
                if (interfaceC12701a2 != null) {
                    i = interfaceC12701a2.m1359a(i);
                }
            }
        }
        byte[] bArr = this.f25874e;
        if (bArr == null || bArr.length < i) {
            this.f25874e = new byte[i];
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0055, code lost:
        if (r1 != null) goto L12;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x005c  */
    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void close() {
        /*
            r4 = this;
            r0 = 0
            r1 = 1
            r4.m1360a(r0, r1)
            org.a.d.e r1 = r4.f25870a     // Catch: java.lang.Exception -> L3a org.p415a.p427d.C12727l -> L43
            if (r1 == 0) goto L1b
            org.a.d.e r1 = r4.f25870a     // Catch: java.lang.Exception -> L3a org.p415a.p427d.C12727l -> L43
            byte[] r2 = r4.f25874e     // Catch: java.lang.Exception -> L3a org.p415a.p427d.C12727l -> L43
            int r1 = r1.m1368a(r2, r0)     // Catch: java.lang.Exception -> L3a org.p415a.p427d.C12727l -> L43
            if (r1 == 0) goto L37
            java.io.OutputStream r2 = r4.out     // Catch: java.lang.Exception -> L3a org.p415a.p427d.C12727l -> L43
            byte[] r3 = r4.f25874e     // Catch: java.lang.Exception -> L3a org.p415a.p427d.C12727l -> L43
        L17:
            r2.write(r3, r0, r1)     // Catch: java.lang.Exception -> L3a org.p415a.p427d.C12727l -> L43
            goto L37
        L1b:
            org.a.d.g.a r1 = r4.f25872c     // Catch: java.lang.Exception -> L3a org.p415a.p427d.C12727l -> L43
            if (r1 == 0) goto L2e
            org.a.d.g.a r1 = r4.f25872c     // Catch: java.lang.Exception -> L3a org.p415a.p427d.C12727l -> L43
            byte[] r2 = r4.f25874e     // Catch: java.lang.Exception -> L3a org.p415a.p427d.C12727l -> L43
            int r1 = r1.m1358a(r2, r0)     // Catch: java.lang.Exception -> L3a org.p415a.p427d.C12727l -> L43
            if (r1 == 0) goto L37
            java.io.OutputStream r2 = r4.out     // Catch: java.lang.Exception -> L3a org.p415a.p427d.C12727l -> L43
            byte[] r3 = r4.f25874e     // Catch: java.lang.Exception -> L3a org.p415a.p427d.C12727l -> L43
            goto L17
        L2e:
            org.a.d.r r0 = r4.f25871b     // Catch: java.lang.Exception -> L3a org.p415a.p427d.C12727l -> L43
            if (r0 == 0) goto L37
            org.a.d.r r0 = r4.f25871b     // Catch: java.lang.Exception -> L3a org.p415a.p427d.C12727l -> L43
            r0.m1304c()     // Catch: java.lang.Exception -> L3a org.p415a.p427d.C12727l -> L43
        L37:
            r0 = 0
            r1 = r0
            goto L4b
        L3a:
            r0 = move-exception
            org.a.d.f.a r1 = new org.a.d.f.a
            java.lang.String r2 = "Error closing stream: "
            r1.<init>(r2, r0)
            goto L4b
        L43:
            r0 = move-exception
            org.a.d.f.c r1 = new org.a.d.f.c
            java.lang.String r2 = "Error finalising cipher data"
            r1.<init>(r2, r0)
        L4b:
            r4.flush()     // Catch: java.io.IOException -> L54
            java.io.OutputStream r0 = r4.out     // Catch: java.io.IOException -> L54
            r0.close()     // Catch: java.io.IOException -> L54
            goto L58
        L54:
            r0 = move-exception
            if (r1 != 0) goto L58
            goto L59
        L58:
            r0 = r1
        L59:
            if (r0 != 0) goto L5c
            return
        L5c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.p415a.p427d.p433f.C12698b.close():void");
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() {
        this.out.flush();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) {
        byte[] bArr = this.f25873d;
        byte b = (byte) i;
        bArr[0] = b;
        if (this.f25871b != null) {
            this.out.write(this.f25871b.mo1306a(b));
        } else {
            write(bArr, 0, 1);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        int m1357a;
        m1360a(i2, false);
        C12693e c12693e = this.f25870a;
        if (c12693e != null) {
            m1357a = c12693e.m1367a(bArr, i, i2, this.f25874e, 0);
            if (m1357a == 0) {
                return;
            }
        } else {
            InterfaceC12701a interfaceC12701a = this.f25872c;
            if (interfaceC12701a == null) {
                this.f25871b.mo1305a(bArr, i, i2, this.f25874e, 0);
                this.out.write(this.f25874e, 0, i2);
                return;
            }
            m1357a = interfaceC12701a.m1357a(bArr, i, i2, this.f25874e, 0);
            if (m1357a == 0) {
                return;
            }
        }
        this.out.write(this.f25874e, 0, m1357a);
    }
}
