package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p051l;

import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: a.a.a.a.a.e.a.e.l.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0691b extends InputStream {

    /* renamed from: C */
    public final InputStream f2050C;

    /* renamed from: D */
    public final OutputStream f2051D;

    public C0691b(InputStream inputStream, OutputStream outputStream) {
        this.f2050C = inputStream;
        this.f2051D = outputStream;
    }

    /* renamed from: a */
    public OutputStream m22388a() {
        return this.f2051D;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f2050C.close();
        this.f2051D.close();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        int read = this.f2050C.read(bArr, i, i2);
        if (read > 0) {
            this.f2051D.write(bArr, i, read);
        }
        return read;
    }

    @Override // java.io.InputStream
    public int read() {
        int read = this.f2050C.read();
        if (read >= 0) {
            this.f2051D.write(read);
        }
        return read;
    }
}
