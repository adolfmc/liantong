package com.bytedance.pangle.res.p181a;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.bytedance.pangle.res.a.j */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC3918j extends FilterInputStream {
    /* renamed from: a */
    protected void mo16690a(int i) {
    }

    public AbstractC3918j(InputStream inputStream) {
        super(inputStream);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        try {
            int read = this.in.read();
            mo16690a(read != -1 ? 1 : -1);
            return read;
        } catch (IOException e) {
            throw e;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        try {
            int read = this.in.read(bArr);
            mo16690a(read);
            return read;
        } catch (IOException e) {
            throw e;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        try {
            int read = this.in.read(bArr, i, i2);
            mo16690a(read);
            return read;
        } catch (IOException e) {
            throw e;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) {
        try {
            return this.in.skip(j);
        } catch (IOException e) {
            throw e;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() {
        try {
            return super.available();
        } catch (IOException e) {
            throw e;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            this.in.close();
        } catch (IOException e) {
            throw e;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i) {
        this.in.mark(i);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() {
        try {
            this.in.reset();
        } catch (IOException e) {
            throw e;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return this.in.markSupported();
    }
}
