package org.codehaus.jackson.p467io;

import java.io.Writer;
import org.codehaus.jackson.util.BufferRecycler;
import org.codehaus.jackson.util.TextBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.codehaus.jackson.io.SegmentedStringWriter */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class SegmentedStringWriter extends Writer {
    protected final TextBuffer _buffer;

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
    }

    public SegmentedStringWriter(BufferRecycler bufferRecycler) {
        this._buffer = new TextBuffer(bufferRecycler);
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(char c) {
        write(c);
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence charSequence) {
        String obj = charSequence.toString();
        this._buffer.append(obj, 0, obj.length());
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence charSequence, int i, int i2) {
        String obj = charSequence.subSequence(i, i2).toString();
        this._buffer.append(obj, 0, obj.length());
        return this;
    }

    @Override // java.io.Writer
    public void write(char[] cArr) {
        this._buffer.append(cArr, 0, cArr.length);
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        this._buffer.append(cArr, i, i2);
    }

    @Override // java.io.Writer
    public void write(int i) {
        this._buffer.append((char) i);
    }

    @Override // java.io.Writer
    public void write(String str) {
        this._buffer.append(str, 0, str.length());
    }

    @Override // java.io.Writer
    public void write(String str, int i, int i2) {
        this._buffer.append(str, i, i2);
    }

    public String getAndClear() {
        String contentsAsString = this._buffer.contentsAsString();
        this._buffer.releaseBuffers();
        return contentsAsString;
    }
}
