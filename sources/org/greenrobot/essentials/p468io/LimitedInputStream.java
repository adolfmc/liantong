package org.greenrobot.essentials.p468io;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.greenrobot.essentials.io.LimitedInputStream */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class LimitedInputStream extends InputStream {
    private int bytesLeft;

    /* renamed from: in */
    private final InputStream f27409in;

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    public static GZIPInputStream createGZIPInputStream(InputStream inputStream, int i) throws IOException {
        return new GZIPInputStream(new LimitedInputStream(inputStream, i));
    }

    public LimitedInputStream(InputStream inputStream, int i) {
        this.f27409in = inputStream;
        this.bytesLeft = i;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return Math.min(this.f27409in.available(), this.bytesLeft);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.bytesLeft <= 0) {
            return -1;
        }
        int read = this.f27409in.read();
        if (read != -1) {
            this.bytesLeft--;
        }
        return read;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.bytesLeft;
        if (i3 <= 0) {
            return -1;
        }
        int read = this.f27409in.read(bArr, i, Math.min(i3, i2));
        if (read > 0) {
            this.bytesLeft -= read;
        }
        return read;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        if (j <= 0) {
            return 0L;
        }
        long skip = this.f27409in.skip(Math.min(this.bytesLeft, j));
        if (skip > 0) {
            this.bytesLeft = (int) (this.bytesLeft - skip);
        }
        return skip;
    }

    public int getBytesLeft() {
        return this.bytesLeft;
    }
}
