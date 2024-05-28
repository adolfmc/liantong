package org.apache.http.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class InputStreamEntity extends AbstractHttpEntity {
    private static final int BUFFER_SIZE = 2048;
    private boolean consumed = false;
    private final InputStream content;
    private final long length;

    public InputStreamEntity(InputStream inputStream, long j) {
        if (inputStream == null) {
            throw new IllegalArgumentException("Source input stream may not be null");
        }
        this.content = inputStream;
        this.length = j;
    }

    @Override // org.apache.http.HttpEntity
    public boolean isRepeatable() {
        return false;
    }

    @Override // org.apache.http.HttpEntity
    public long getContentLength() {
        return this.length;
    }

    @Override // org.apache.http.HttpEntity
    public InputStream getContent() throws IOException {
        return this.content;
    }

    @Override // org.apache.http.HttpEntity
    public void writeTo(OutputStream outputStream) throws IOException {
        int read;
        if (outputStream == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        InputStream inputStream = this.content;
        byte[] bArr = new byte[2048];
        long j = this.length;
        if (j < 0) {
            while (true) {
                int read2 = inputStream.read(bArr);
                if (read2 == -1) {
                    break;
                }
                outputStream.write(bArr, 0, read2);
            }
        } else {
            while (j > 0 && (read = inputStream.read(bArr, 0, (int) Math.min(2048L, j))) != -1) {
                outputStream.write(bArr, 0, read);
                j -= read;
            }
        }
        this.consumed = true;
    }

    @Override // org.apache.http.HttpEntity
    public boolean isStreaming() {
        return !this.consumed;
    }

    @Override // org.apache.http.entity.AbstractHttpEntity, org.apache.http.HttpEntity
    public void consumeContent() throws IOException {
        this.consumed = true;
        this.content.close();
    }
}
