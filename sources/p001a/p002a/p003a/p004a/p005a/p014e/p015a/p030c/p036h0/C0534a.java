package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p036h0;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.C0530g;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0501a0;

/* renamed from: a.a.a.a.a.e.a.c.h0.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0534a extends FilterInputStream {

    /* renamed from: J */
    public static final int f1741J = 2048;

    /* renamed from: C */
    public C0530g f1742C;

    /* renamed from: D */
    public InterfaceC0501a0 f1743D;

    /* renamed from: E */
    public byte[] f1744E;

    /* renamed from: F */
    public byte[] f1745F;

    /* renamed from: G */
    public int f1746G;

    /* renamed from: H */
    public int f1747H;

    /* renamed from: I */
    public boolean f1748I;

    public C0534a(InputStream inputStream, C0530g c0530g) {
        super(inputStream);
        this.f1742C = c0530g;
        this.f1744E = new byte[c0530g.mo22857a(2048)];
        this.f1745F = new byte[2048];
    }

    /* renamed from: a */
    private int m22884a() {
        int read;
        int available = super.available();
        if (available <= 0) {
            available = 1;
        }
        byte[] bArr = this.f1745F;
        if (available > bArr.length) {
            read = super.read(bArr, 0, bArr.length);
        } else {
            read = super.read(bArr, 0, available);
        }
        if (read < 0) {
            if (this.f1748I) {
                return -1;
            }
            try {
                if (this.f1742C != null) {
                    this.f1747H = this.f1742C.mo22855a(this.f1744E, 0);
                } else {
                    this.f1747H = 0;
                }
                this.f1746G = 0;
                this.f1748I = true;
                if (this.f1747H == 0) {
                    return -1;
                }
            } catch (Exception e) {
                throw new IOException("error processing stream: " + e.toString());
            }
        } else {
            this.f1746G = 0;
            try {
                if (this.f1742C != null) {
                    this.f1747H = this.f1742C.mo22854a(this.f1745F, 0, read, this.f1744E, 0);
                } else {
                    this.f1743D.mo22627a(this.f1745F, 0, read, this.f1744E, 0);
                    this.f1747H = read;
                }
                if (this.f1747H == 0) {
                    return m22884a();
                }
            } catch (Exception e2) {
                throw new IOException("error processing stream: " + e2.toString());
            }
        }
        return this.f1747H;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() {
        return this.f1747H - this.f1746G;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        if (this.f1746G != this.f1747H || m22884a() >= 0) {
            byte[] bArr = this.f1744E;
            int i = this.f1746G;
            this.f1746G = i + 1;
            return bArr[i] & 255;
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) {
        if (j <= 0) {
            return 0L;
        }
        int i = this.f1747H;
        int i2 = this.f1746G;
        long j2 = i - i2;
        if (j > j2) {
            this.f1746G = i;
            return j2;
        }
        int i3 = (int) j;
        this.f1746G = i2 + i3;
        return i3;
    }

    public C0534a(InputStream inputStream, InterfaceC0501a0 interfaceC0501a0) {
        super(inputStream);
        this.f1743D = interfaceC0501a0;
        this.f1744E = new byte[2048];
        this.f1745F = new byte[2048];
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        if (this.f1746G != this.f1747H || m22884a() >= 0) {
            int i3 = this.f1747H;
            int i4 = this.f1746G;
            int i5 = i3 - i4;
            if (i2 > i5) {
                System.arraycopy(this.f1744E, i4, bArr, i, i5);
                this.f1746G = this.f1747H;
                return i5;
            }
            System.arraycopy(this.f1744E, i4, bArr, i, i2);
            this.f1746G += i2;
            return i2;
        }
        return -1;
    }
}
