package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p051l;

import java.io.OutputStream;

/* renamed from: a.a.a.a.a.e.a.e.l.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0692c extends OutputStream {

    /* renamed from: C */
    public OutputStream f2052C;

    /* renamed from: D */
    public OutputStream f2053D;

    public C0692c(OutputStream outputStream, OutputStream outputStream2) {
        this.f2052C = outputStream;
        this.f2053D = outputStream2;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f2052C.close();
        this.f2053D.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        this.f2052C.flush();
        this.f2053D.flush();
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        this.f2052C.write(bArr);
        this.f2053D.write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        this.f2052C.write(bArr, i, i2);
        this.f2053D.write(bArr, i, i2);
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        this.f2052C.write(i);
        this.f2053D.write(i);
    }
}
