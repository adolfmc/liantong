package org.apache.http.impl.p452io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.p453io.SessionInputBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* renamed from: org.apache.http.impl.io.IdentityInputStream */
/* loaded from: E:\452516_dexfile_execute.dex */
public class IdentityInputStream extends InputStream {
    private boolean closed = false;

    /* renamed from: in */
    private final SessionInputBuffer f26275in;

    public IdentityInputStream(SessionInputBuffer sessionInputBuffer) {
        if (sessionInputBuffer == null) {
            throw new IllegalArgumentException("Session input buffer may not be null");
        }
        this.f26275in = sessionInputBuffer;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (!this.closed && this.f26275in.isDataAvailable(10)) {
            return 1;
        }
        return 0;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.closed = true;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.closed) {
            return -1;
        }
        return this.f26275in.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.closed) {
            return -1;
        }
        return this.f26275in.read(bArr, i, i2);
    }
}
