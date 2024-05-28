package org.apache.http.impl.p452io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.MalformedChunkCodingException;
import org.apache.http.p453io.SessionInputBuffer;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.ExceptionUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* renamed from: org.apache.http.impl.io.ChunkedInputStream */
/* loaded from: E:\452516_dexfile_execute.dex */
public class ChunkedInputStream extends InputStream {
    private final CharArrayBuffer buffer;
    private int chunkSize;

    /* renamed from: in */
    private SessionInputBuffer f26273in;
    private int pos;
    private boolean bof = true;
    private boolean eof = false;
    private boolean closed = false;
    private Header[] footers = new Header[0];

    public ChunkedInputStream(SessionInputBuffer sessionInputBuffer) {
        if (sessionInputBuffer == null) {
            throw new IllegalArgumentException("Session input buffer may not be null");
        }
        this.f26273in = sessionInputBuffer;
        this.pos = 0;
        this.buffer = new CharArrayBuffer(16);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        }
        if (this.eof) {
            return -1;
        }
        if (this.pos >= this.chunkSize) {
            nextChunk();
            if (this.eof) {
                return -1;
            }
        }
        this.pos++;
        return this.f26273in.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        }
        if (this.eof) {
            return -1;
        }
        if (this.pos >= this.chunkSize) {
            nextChunk();
            if (this.eof) {
                return -1;
            }
        }
        int read = this.f26273in.read(bArr, i, Math.min(i2, this.chunkSize - this.pos));
        this.pos += read;
        return read;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    private void nextChunk() throws IOException {
        int chunkSize = getChunkSize();
        this.chunkSize = chunkSize;
        if (chunkSize < 0) {
            throw new MalformedChunkCodingException("Negative chunk size");
        }
        this.bof = false;
        this.pos = 0;
        if (chunkSize == 0) {
            this.eof = true;
            parseTrailerHeaders();
        }
    }

    private int getChunkSize() throws IOException {
        if (!this.bof) {
            int read = this.f26273in.read();
            int read2 = this.f26273in.read();
            if (read != 13 || read2 != 10) {
                throw new MalformedChunkCodingException("CRLF expected at end of chunk");
            }
        }
        this.buffer.clear();
        if (this.f26273in.readLine(this.buffer) == -1) {
            throw new MalformedChunkCodingException("Chunked stream ended unexpectedly");
        }
        int indexOf = this.buffer.indexOf(59);
        if (indexOf < 0) {
            indexOf = this.buffer.length();
        }
        try {
            return Integer.parseInt(this.buffer.substringTrimmed(0, indexOf), 16);
        } catch (NumberFormatException e) {
            throw new MalformedChunkCodingException("Bad chunk header");
        }
    }

    private void parseTrailerHeaders() throws IOException {
        try {
            this.footers = AbstractMessageParser.parseHeaders(this.f26273in, -1, -1, null);
        } catch (HttpException e) {
            MalformedChunkCodingException malformedChunkCodingException = new MalformedChunkCodingException("Invalid footer: " + e.getMessage());
            ExceptionUtils.initCause(malformedChunkCodingException, e);
            throw malformedChunkCodingException;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.closed) {
            try {
                if (!this.eof) {
                    exhaustInputStream(this);
                }
            } finally {
                this.eof = true;
                this.closed = true;
            }
        }
    }

    public Header[] getFooters() {
        return (Header[]) this.footers.clone();
    }

    static void exhaustInputStream(InputStream inputStream) throws IOException {
        do {
        } while (inputStream.read(new byte[1024]) >= 0);
    }
}
