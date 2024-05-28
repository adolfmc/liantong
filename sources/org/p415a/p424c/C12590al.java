package org.p415a.p424c;

import java.io.EOFException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.al */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12590al extends InputStream {

    /* renamed from: a */
    InputStream f25577a;

    public C12590al(InputStream inputStream) {
        this.f25577a = inputStream;
    }

    /* renamed from: a */
    private void m1578a(byte[] bArr, int i, int i2) {
        if (i2 > 0) {
            int read = read();
            if (read < 0) {
                throw new EOFException();
            }
            bArr[i] = (byte) read;
            i++;
            i2--;
        }
        while (i2 > 0) {
            int read2 = this.f25577a.read(bArr, i, i2);
            if (read2 < 0) {
                throw new EOFException();
            }
            i += read2;
            i2 -= read2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0064  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.p415a.p424c.C12589ak m1579a() {
        /*
            r6 = this;
            int r0 = r6.read()
            if (r0 >= 0) goto L8
            r0 = 0
            return r0
        L8:
            r1 = 0
            r2 = 1
            r3 = 192(0xc0, float:2.69E-43)
            if (r0 >= r3) goto L10
        Le:
            r3 = r1
            goto L47
        L10:
            r4 = 223(0xdf, float:3.12E-43)
            if (r0 > r4) goto L21
            int r0 = r0 + (-192)
            int r0 = r0 << 8
            java.io.InputStream r4 = r6.f25577a
            int r4 = r4.read()
            int r0 = r0 + r4
            int r0 = r0 + r3
            goto Le
        L21:
            r3 = 255(0xff, float:3.57E-43)
            if (r0 != r3) goto L6c
            java.io.InputStream r0 = r6.f25577a
            int r0 = r0.read()
            int r0 = r0 << 24
            java.io.InputStream r3 = r6.f25577a
            int r3 = r3.read()
            int r3 = r3 << 16
            r0 = r0 | r3
            java.io.InputStream r3 = r6.f25577a
            int r3 = r3.read()
            int r3 = r3 << 8
            r0 = r0 | r3
            java.io.InputStream r3 = r6.f25577a
            int r3 = r3.read()
            r0 = r0 | r3
            r3 = r2
        L47:
            java.io.InputStream r4 = r6.f25577a
            int r4 = r4.read()
            if (r4 < 0) goto L64
            int r0 = r0 - r2
            byte[] r0 = new byte[r0]
            int r5 = r0.length
            r6.m1578a(r0, r1, r5)
            if (r4 == r2) goto L5e
            org.a.c.ak r1 = new org.a.c.ak
            r1.<init>(r4, r3, r0)
            return r1
        L5e:
            org.a.c.a.a r1 = new org.a.c.a.a
            r1.<init>(r3, r0)
            return r1
        L64:
            java.io.EOFException r0 = new java.io.EOFException
            java.lang.String r1 = "unexpected EOF reading user attribute sub packet"
            r0.<init>(r1)
            throw r0
        L6c:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "unrecognised length reading user attribute sub packet"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.p415a.p424c.C12590al.m1579a():org.a.c.ak");
    }

    @Override // java.io.InputStream
    public int available() {
        return this.f25577a.available();
    }

    @Override // java.io.InputStream
    public int read() {
        return this.f25577a.read();
    }
}
