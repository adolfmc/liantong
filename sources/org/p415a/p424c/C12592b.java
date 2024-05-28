package org.p415a.p424c;

import java.io.EOFException;
import java.io.InputStream;
import org.p415a.p448g.p450b.C12967a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12592b extends InputStream {

    /* renamed from: a */
    InputStream f25579a;

    /* renamed from: b */
    boolean f25580b = false;

    /* renamed from: c */
    int f25581c;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.c.b$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class C12593a extends InputStream {

        /* renamed from: a */
        private C12592b f25582a;

        /* renamed from: b */
        private boolean f25583b;

        /* renamed from: c */
        private int f25584c;

        C12593a(C12592b c12592b, boolean z, int i) {
            this.f25582a = c12592b;
            this.f25583b = z;
            this.f25584c = i;
        }

        /* renamed from: a */
        private int m1572a() {
            int read = this.f25582a.read();
            if (read < 0) {
                return -1;
            }
            this.f25583b = false;
            if (read >= 192) {
                if (read <= 223) {
                    read = ((read - 192) << 8) + this.f25582a.read() + 192;
                } else if (read == 255) {
                    read = (this.f25582a.read() << 24) | (this.f25582a.read() << 16) | (this.f25582a.read() << 8) | this.f25582a.read();
                } else {
                    this.f25583b = true;
                    read = 1 << (read & 31);
                }
            }
            this.f25584c = read;
            return this.f25584c;
        }

        @Override // java.io.InputStream
        public int available() {
            int available = this.f25582a.available();
            int i = this.f25584c;
            if (available <= i || i < 0) {
                return available;
            }
            if (this.f25583b && i == 0) {
                return 1;
            }
            return this.f25584c;
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x001b  */
        @Override // java.io.InputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int read() {
            /*
                r2 = this;
            L0:
                int r0 = r2.f25584c
                if (r0 == 0) goto L1b
                org.a.c.b r0 = r2.f25582a
                int r0 = r0.read()
                if (r0 < 0) goto L13
                int r1 = r2.f25584c
                int r1 = r1 + (-1)
                r2.f25584c = r1
                return r0
            L13:
                java.io.EOFException r0 = new java.io.EOFException
                java.lang.String r1 = "premature end of stream in PartialInputStream"
                r0.<init>(r1)
                throw r0
            L1b:
                boolean r0 = r2.f25583b
                if (r0 == 0) goto L25
                int r0 = r2.m1572a()
                if (r0 >= 0) goto L0
            L25:
                r0 = -1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.p415a.p424c.C12592b.C12593a.read():int");
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) {
            do {
                int i3 = this.f25584c;
                if (i3 != 0) {
                    if (i3 <= i2 && i3 >= 0) {
                        i2 = i3;
                    }
                    int read = this.f25582a.read(bArr, i, i2);
                    if (read >= 0) {
                        this.f25584c -= read;
                        return read;
                    }
                    throw new EOFException("premature end of stream in PartialInputStream");
                } else if (!this.f25583b) {
                    return -1;
                }
            } while (m1572a() >= 0);
            return -1;
        }
    }

    public C12592b(InputStream inputStream) {
        this.f25579a = inputStream;
    }

    /* renamed from: a */
    public void m1576a(byte[] bArr) {
        m1575a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public void m1575a(byte[] bArr, int i, int i2) {
        if (C12967a.m402a(this, bArr, i, i2) < i2) {
            throw new EOFException();
        }
    }

    /* renamed from: a */
    public byte[] m1577a() {
        return C12967a.m405a(this);
    }

    @Override // java.io.InputStream
    public int available() {
        return this.f25579a.available();
    }

    /* renamed from: b */
    public int m1574b() {
        if (!this.f25580b) {
            try {
                this.f25581c = this.f25579a.read();
            } catch (EOFException unused) {
                this.f25581c = -1;
            }
            this.f25580b = true;
        }
        int i = this.f25581c;
        if (i < 0) {
            return i;
        }
        int i2 = i & 63;
        return (i & 64) == 0 ? i2 >> 2 : i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0135  */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.p415a.p424c.C12624u m1573c() {
        /*
            Method dump skipped, instructions count: 392
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.p415a.p424c.C12592b.m1573c():org.a.c.u");
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f25579a.close();
    }

    @Override // java.io.InputStream
    public int read() {
        if (this.f25580b) {
            this.f25580b = false;
            return this.f25581c;
        }
        return this.f25579a.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        if (this.f25580b) {
            int i3 = this.f25581c;
            if (i3 < 0) {
                return -1;
            }
            bArr[i] = (byte) i3;
            this.f25580b = false;
            return 1;
        }
        return this.f25579a.read(bArr, i, i2);
    }
}
