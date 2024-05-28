package org.apache.http.impl.p452io;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.p453io.SessionOutputBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* renamed from: org.apache.http.impl.io.ContentLengthOutputStream */
/* loaded from: E:\452516_dexfile_execute.dex */
public class ContentLengthOutputStream extends OutputStream {
    private final long contentLength;
    private final SessionOutputBuffer out;
    private long total = 0;
    private boolean closed = false;

    public ContentLengthOutputStream(SessionOutputBuffer sessionOutputBuffer, long j) {
        if (sessionOutputBuffer == null) {
            throw new IllegalArgumentException("Session output buffer may not be null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("Content length may not be negative");
        }
        this.out = sessionOutputBuffer;
        this.contentLength = j;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            this.out.flush();
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.closed) {
            throw new IOException("Attempted write to closed stream.");
        }
        long j = this.total;
        long j2 = this.contentLength;
        if (j < j2) {
            long j3 = j2 - j;
            if (i2 > j3) {
                i2 = (int) j3;
            }
            this.out.write(bArr, i, i2);
            this.total += i2;
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        if (this.closed) {
            throw new IOException("Attempted write to closed stream.");
        }
        if (this.total < this.contentLength) {
            this.out.write(i);
            this.total++;
        }
    }
}
