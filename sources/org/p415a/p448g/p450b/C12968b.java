package org.p415a.p448g.p450b;

import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.g.b.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12968b extends OutputStream {

    /* renamed from: a */
    private OutputStream f26249a;

    /* renamed from: b */
    private OutputStream f26250b;

    public C12968b(OutputStream outputStream, OutputStream outputStream2) {
        this.f26249a = outputStream;
        this.f26250b = outputStream2;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f26249a.close();
        this.f26250b.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        this.f26249a.flush();
        this.f26250b.flush();
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        this.f26249a.write(i);
        this.f26250b.write(i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        this.f26249a.write(bArr);
        this.f26250b.write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        this.f26249a.write(bArr, i, i2);
        this.f26250b.write(bArr, i, i2);
    }
}
