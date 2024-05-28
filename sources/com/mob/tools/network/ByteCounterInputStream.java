package com.mob.tools.network;

import com.mob.tools.proguard.PublicMemberKeeper;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ByteCounterInputStream extends InputStream implements PublicMemberKeeper {

    /* renamed from: a */
    private InputStream f14994a;

    /* renamed from: b */
    private long f14995b;

    /* renamed from: c */
    private OnReadListener f14996c;

    public ByteCounterInputStream(InputStream inputStream) {
        this.f14994a = inputStream;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int read = this.f14994a.read();
        if (read >= 0) {
            this.f14995b++;
            OnReadListener onReadListener = this.f14996c;
            if (onReadListener != null) {
                onReadListener.onRead(this.f14995b);
            }
        }
        return read;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.f14994a.read(bArr, i, i2);
        if (read > 0) {
            this.f14995b += read;
            OnReadListener onReadListener = this.f14996c;
            if (onReadListener != null) {
                onReadListener.onRead(this.f14995b);
            }
        }
        return read;
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        this.f14994a.mark(i);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.f14994a.markSupported();
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        this.f14994a.reset();
        this.f14995b = 0L;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        return this.f14994a.skip(j);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.f14994a.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f14994a.close();
    }

    public void setOnInputStreamReadListener(OnReadListener onReadListener) {
        this.f14996c = onReadListener;
    }
}
