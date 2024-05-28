package org.p415a.p445f;

import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.z */
/* loaded from: E:\11617560_dexfile_execute.dex */
class C12956z extends OutputStream {

    /* renamed from: a */
    private final OutputStream f26234a;

    /* renamed from: b */
    private final InterfaceC12955y f26235b;

    public C12956z(OutputStream outputStream, InterfaceC12955y interfaceC12955y) {
        this.f26234a = outputStream;
        this.f26235b = interfaceC12955y;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f26235b.mo443a();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        this.f26234a.flush();
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        this.f26234a.write(i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        this.f26234a.write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        this.f26234a.write(bArr, i, i2);
    }
}
