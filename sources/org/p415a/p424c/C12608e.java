package org.p415a.p424c;

import java.io.IOException;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.e */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12608e extends OutputStream {

    /* renamed from: a */
    OutputStream f25585a;

    /* renamed from: b */
    private byte[] f25586b;

    /* renamed from: c */
    private int f25587c;

    /* renamed from: d */
    private int f25588d;

    /* renamed from: e */
    private int f25589e;

    public C12608e(OutputStream outputStream) {
        this.f25585a = outputStream;
    }

    public C12608e(OutputStream outputStream, int i) {
        this.f25585a = outputStream;
        m1567a(i, true, true, 0L);
    }

    public C12608e(OutputStream outputStream, int i, long j) {
        this.f25585a = outputStream;
        m1567a(i, false, false, j);
    }

    public C12608e(OutputStream outputStream, int i, long j, boolean z) {
        this.f25585a = outputStream;
        if (j <= 4294967295L) {
            m1567a(i, z, false, j);
            return;
        }
        m1567a(i, false, true, 0L);
        this.f25587c = 65536;
        this.f25586b = new byte[this.f25587c];
        this.f25588d = 16;
        this.f25589e = 0;
    }

    public C12608e(OutputStream outputStream, int i, byte[] bArr) {
        this.f25585a = outputStream;
        m1567a(i, false, true, 0L);
        this.f25586b = bArr;
        int length = this.f25586b.length;
        this.f25588d = 0;
        while (length != 1) {
            length >>>= 1;
            this.f25588d++;
        }
        int i2 = this.f25588d;
        if (i2 > 30) {
            throw new IOException("Buffer cannot be greater than 2^30 in length.");
        }
        this.f25587c = 1 << i2;
        this.f25589e = 0;
    }

    /* renamed from: a */
    private void m1568a(byte b) {
        if (this.f25589e == this.f25587c) {
            m1562a(false);
        }
        byte[] bArr = this.f25586b;
        int i = this.f25589e;
        this.f25589e = i + 1;
        bArr[i] = b;
    }

    /* renamed from: a */
    private void m1567a(int i, boolean z, boolean z2, long j) {
        int i2;
        int i3;
        if (this.f25586b != null) {
            m1562a(true);
            this.f25586b = null;
        }
        if (!z) {
            write(i | 64 | 128);
            if (z2) {
                this.f25589e = 0;
                return;
            } else {
                m1565a(j);
                return;
            }
        }
        int i4 = (i << 2) | 128;
        if (z2) {
            i3 = i4 | 3;
        } else {
            if (j > 255) {
                if (j <= 65535) {
                    i2 = i4 | 1;
                } else {
                    write(i4 | 2);
                    write((byte) (j >> 24));
                    i2 = (byte) (j >> 16);
                }
                write(i2);
                i4 = (byte) (j >> 8);
            }
            write(i4);
            i3 = (byte) j;
        }
        write(i3);
    }

    /* renamed from: a */
    private void m1565a(long j) {
        if (j >= 192) {
            if (j <= 8383) {
                j -= 192;
                this.f25585a.write((byte) (((j >> 8) & 255) + 192));
            } else {
                this.f25585a.write(255);
                this.f25585a.write((byte) (j >> 24));
                this.f25585a.write((byte) (j >> 16));
                this.f25585a.write((byte) (j >> 8));
            }
        }
        this.f25585a.write((byte) j);
    }

    /* renamed from: a */
    private void m1562a(boolean z) {
        OutputStream outputStream;
        byte[] bArr;
        int i;
        if (z) {
            m1565a(this.f25589e);
            outputStream = this.f25585a;
            bArr = this.f25586b;
            i = this.f25589e;
        } else {
            this.f25585a.write(this.f25588d | 224);
            outputStream = this.f25585a;
            bArr = this.f25586b;
            i = this.f25587c;
        }
        outputStream.write(bArr, 0, i);
        this.f25589e = 0;
    }

    /* renamed from: a */
    private void m1561a(byte[] bArr, int i, int i2) {
        if (this.f25589e == this.f25587c) {
            m1562a(false);
        }
        int i3 = this.f25587c;
        int i4 = this.f25589e;
        if (i2 <= i3 - i4) {
            System.arraycopy(bArr, i, this.f25586b, i4, i2);
        } else {
            System.arraycopy(bArr, i, this.f25586b, i4, i3 - i4);
            int i5 = this.f25587c;
            int i6 = this.f25589e;
            int i7 = i + (i5 - i6);
            int i8 = i5 - i6;
            while (true) {
                i2 -= i8;
                m1562a(false);
                int i9 = this.f25587c;
                if (i2 <= i9) {
                    break;
                }
                System.arraycopy(bArr, i7, this.f25586b, 0, i9);
                i8 = this.f25587c;
                i7 += i8;
            }
            System.arraycopy(bArr, i7, this.f25586b, 0, i2);
        }
        this.f25589e += i2;
    }

    /* renamed from: a */
    public void m1569a() {
        if (this.f25586b != null) {
            m1562a(true);
            this.f25586b = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m1566a(int i, byte[] bArr, boolean z) {
        m1567a(i, z, false, bArr.length);
        write(bArr);
    }

    /* renamed from: a */
    public void m1564a(AbstractC12607d abstractC12607d) {
        abstractC12607d.mo1533a(this);
    }

    /* renamed from: a */
    public void m1563a(AbstractC12611h abstractC12611h) {
        abstractC12611h.mo1537a(this);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        m1569a();
        this.f25585a.flush();
        this.f25585a.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        this.f25585a.flush();
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        if (this.f25586b != null) {
            m1568a((byte) i);
        } else {
            this.f25585a.write(i);
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        if (this.f25586b != null) {
            m1561a(bArr, i, i2);
        } else {
            this.f25585a.write(bArr, i, i2);
        }
    }
}
