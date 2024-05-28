package net.lingala.zip4j.p410io.inputstream;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* renamed from: net.lingala.zip4j.io.inputstream.InflaterInputStream */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class InflaterInputStream extends DecompressedInputStream {
    private byte[] buff;
    private Inflater inflater;
    private int len;
    private byte[] singleByteBuffer;

    public InflaterInputStream(CipherInputStream cipherInputStream) {
        super(cipherInputStream);
        this.singleByteBuffer = new byte[1];
        this.inflater = new Inflater(true);
        this.buff = new byte[4096];
    }

    @Override // net.lingala.zip4j.p410io.inputstream.DecompressedInputStream, java.io.InputStream
    public int read() throws IOException {
        if (read(this.singleByteBuffer) == -1) {
            return -1;
        }
        return this.singleByteBuffer[0];
    }

    @Override // net.lingala.zip4j.p410io.inputstream.DecompressedInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // net.lingala.zip4j.p410io.inputstream.DecompressedInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        while (true) {
            try {
                int inflate = this.inflater.inflate(bArr, i, i2);
                if (inflate == 0) {
                    if (!this.inflater.finished() && !this.inflater.needsDictionary()) {
                        if (this.inflater.needsInput()) {
                            fill();
                        }
                    }
                    return -1;
                }
                return inflate;
            } catch (DataFormatException e) {
                throw new IOException(e);
            }
        }
    }

    @Override // net.lingala.zip4j.p410io.inputstream.DecompressedInputStream
    public void endOfEntryReached(InputStream inputStream) throws IOException {
        Inflater inflater = this.inflater;
        if (inflater != null) {
            inflater.end();
            this.inflater = null;
        }
        super.endOfEntryReached(inputStream);
    }

    @Override // net.lingala.zip4j.p410io.inputstream.DecompressedInputStream
    public void pushBackInputStreamIfNecessary(PushbackInputStream pushbackInputStream) throws IOException {
        int remaining = this.inflater.getRemaining();
        if (remaining > 0) {
            pushbackInputStream.unread(getLastReadRawDataCache(), this.len - remaining, remaining);
        }
    }

    @Override // net.lingala.zip4j.p410io.inputstream.DecompressedInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Inflater inflater = this.inflater;
        if (inflater != null) {
            inflater.end();
        }
        super.close();
    }

    private void fill() throws IOException {
        byte[] bArr = this.buff;
        this.len = super.read(bArr, 0, bArr.length);
        int i = this.len;
        if (i == -1) {
            throw new EOFException("Unexpected end of input stream");
        }
        this.inflater.setInput(this.buff, 0, i);
    }
}
