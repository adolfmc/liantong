package org.apache.http.impl.p452io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.p453io.HttpTransportMetrics;
import org.apache.http.p453io.SessionInputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.CharArrayBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* renamed from: org.apache.http.impl.io.AbstractSessionInputBuffer */
/* loaded from: E:\452516_dexfile_execute.dex */
public abstract class AbstractSessionInputBuffer implements SessionInputBuffer {
    private byte[] buffer;
    private int bufferlen;
    private int bufferpos;
    private InputStream instream;
    private HttpTransportMetricsImpl metrics;
    private ByteArrayBuffer linebuffer = null;
    private String charset = "US-ASCII";
    private boolean ascii = true;
    private int maxLineLen = -1;

    /* JADX INFO: Access modifiers changed from: protected */
    public void init(InputStream inputStream, int i, HttpParams httpParams) {
        if (inputStream == null) {
            throw new IllegalArgumentException("Input stream may not be null");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("Buffer size may not be negative or zero");
        }
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        this.instream = inputStream;
        this.buffer = new byte[i];
        boolean z = false;
        this.bufferpos = 0;
        this.bufferlen = 0;
        this.linebuffer = new ByteArrayBuffer(i);
        String httpElementCharset = HttpProtocolParams.getHttpElementCharset(httpParams);
        this.charset = httpElementCharset;
        this.ascii = (httpElementCharset.equalsIgnoreCase("US-ASCII") || this.charset.equalsIgnoreCase("ASCII")) ? true : true;
        this.maxLineLen = httpParams.getIntParameter("http.connection.max-line-length", -1);
        this.metrics = new HttpTransportMetricsImpl();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int fillBuffer() throws IOException {
        int i = this.bufferpos;
        if (i > 0) {
            int i2 = this.bufferlen - i;
            if (i2 > 0) {
                byte[] bArr = this.buffer;
                System.arraycopy(bArr, i, bArr, 0, i2);
            }
            this.bufferpos = 0;
            this.bufferlen = i2;
        }
        int i3 = this.bufferlen;
        byte[] bArr2 = this.buffer;
        int read = this.instream.read(bArr2, i3, bArr2.length - i3);
        if (read == -1) {
            return -1;
        }
        this.bufferlen = i3 + read;
        this.metrics.incrementBytesTransferred(read);
        return read;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasBufferedData() {
        return this.bufferpos < this.bufferlen;
    }

    @Override // org.apache.http.p453io.SessionInputBuffer
    public int read() throws IOException {
        while (!hasBufferedData()) {
            if (fillBuffer() == -1) {
                return -1;
            }
        }
        byte[] bArr = this.buffer;
        int i = this.bufferpos;
        this.bufferpos = i + 1;
        return bArr[i] & 255;
    }

    @Override // org.apache.http.p453io.SessionInputBuffer
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            return 0;
        }
        while (!hasBufferedData()) {
            if (fillBuffer() == -1) {
                return -1;
            }
        }
        int i3 = this.bufferlen;
        int i4 = this.bufferpos;
        int i5 = i3 - i4;
        if (i5 <= i2) {
            i2 = i5;
        }
        System.arraycopy(this.buffer, i4, bArr, i, i2);
        this.bufferpos += i2;
        return i2;
    }

    @Override // org.apache.http.p453io.SessionInputBuffer
    public int read(byte[] bArr) throws IOException {
        if (bArr == null) {
            return 0;
        }
        return read(bArr, 0, bArr.length);
    }

    private int locateLF() {
        for (int i = this.bufferpos; i < this.bufferlen; i++) {
            if (this.buffer[i] == 10) {
                return i;
            }
        }
        return -1;
    }

    @Override // org.apache.http.p453io.SessionInputBuffer
    public int readLine(CharArrayBuffer charArrayBuffer) throws IOException {
        if (charArrayBuffer == null) {
            throw new IllegalArgumentException("Char array buffer may not be null");
        }
        this.linebuffer.clear();
        boolean z = true;
        int i = 0;
        while (z) {
            int locateLF = locateLF();
            if (locateLF != -1) {
                if (this.linebuffer.isEmpty()) {
                    return lineFromReadBuffer(charArrayBuffer, locateLF);
                }
                int i2 = locateLF + 1;
                int i3 = this.bufferpos;
                this.linebuffer.append(this.buffer, i3, i2 - i3);
                this.bufferpos = i2;
                z = false;
            } else {
                if (hasBufferedData()) {
                    int i4 = this.bufferlen;
                    int i5 = this.bufferpos;
                    this.linebuffer.append(this.buffer, i5, i4 - i5);
                    this.bufferpos = this.bufferlen;
                }
                i = fillBuffer();
                if (i == -1) {
                    z = false;
                }
            }
            if (this.maxLineLen > 0 && this.linebuffer.length() >= this.maxLineLen) {
                throw new IOException("Maximum line length limit exceeded");
            }
        }
        if (i == -1 && this.linebuffer.isEmpty()) {
            return -1;
        }
        return lineFromLineBuffer(charArrayBuffer);
    }

    private int lineFromLineBuffer(CharArrayBuffer charArrayBuffer) throws IOException {
        int length = this.linebuffer.length();
        if (length > 0) {
            if (this.linebuffer.byteAt(length - 1) == 10) {
                length--;
                this.linebuffer.setLength(length);
            }
            if (length > 0 && this.linebuffer.byteAt(length - 1) == 13) {
                this.linebuffer.setLength(length - 1);
            }
        }
        int length2 = this.linebuffer.length();
        if (this.ascii) {
            charArrayBuffer.append(this.linebuffer, 0, length2);
        } else {
            charArrayBuffer.append(new String(this.linebuffer.buffer(), 0, length2, this.charset));
        }
        return length2;
    }

    private int lineFromReadBuffer(CharArrayBuffer charArrayBuffer, int i) throws IOException {
        int i2 = this.bufferpos;
        this.bufferpos = i + 1;
        if (i > i2 && this.buffer[i - 1] == 13) {
            i--;
        }
        int i3 = i - i2;
        if (this.ascii) {
            charArrayBuffer.append(this.buffer, i2, i3);
        } else {
            charArrayBuffer.append(new String(this.buffer, i2, i3, this.charset));
        }
        return i3;
    }

    @Override // org.apache.http.p453io.SessionInputBuffer
    public String readLine() throws IOException {
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(64);
        if (readLine(charArrayBuffer) != -1) {
            return charArrayBuffer.toString();
        }
        return null;
    }

    @Override // org.apache.http.p453io.SessionInputBuffer
    public HttpTransportMetrics getMetrics() {
        return this.metrics;
    }
}
