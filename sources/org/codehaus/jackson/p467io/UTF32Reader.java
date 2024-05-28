package org.codehaus.jackson.p467io;

import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.codehaus.jackson.io.UTF32Reader */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class UTF32Reader extends BaseReader {
    final boolean mBigEndian;
    int mByteCount;
    int mCharCount;
    char mSurrogate;

    @Override // org.codehaus.jackson.p467io.BaseReader, java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public /* bridge */ /* synthetic */ void close() throws IOException {
        super.close();
    }

    @Override // org.codehaus.jackson.p467io.BaseReader, java.io.Reader
    public /* bridge */ /* synthetic */ int read() throws IOException {
        return super.read();
    }

    public UTF32Reader(IOContext iOContext, InputStream inputStream, byte[] bArr, int i, int i2, boolean z) {
        super(iOContext, inputStream, bArr, i, i2);
        this.mSurrogate = (char) 0;
        this.mCharCount = 0;
        this.mByteCount = 0;
        this.mBigEndian = z;
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        int i3;
        int i4;
        int i5;
        if (this._buffer == null) {
            return -1;
        }
        if (i2 < 1) {
            return i2;
        }
        if (i < 0 || i + i2 > cArr.length) {
            reportBounds(cArr, i, i2);
        }
        int i6 = i2 + i;
        char c = this.mSurrogate;
        if (c != 0) {
            i3 = i + 1;
            cArr[i] = c;
            this.mSurrogate = (char) 0;
        } else {
            int i7 = this._length - this._ptr;
            if (i7 < 4 && !loadMore(i7)) {
                return -1;
            }
            i3 = i;
        }
        while (true) {
            if (i3 >= i6) {
                i4 = i3;
                break;
            }
            int i8 = this._ptr;
            if (this.mBigEndian) {
                i5 = (this._buffer[i8 + 3] & 255) | (this._buffer[i8] << 24) | ((this._buffer[i8 + 1] & 255) << 16) | ((this._buffer[i8 + 2] & 255) << 8);
            } else {
                i5 = (this._buffer[i8 + 3] << 24) | (this._buffer[i8] & 255) | ((this._buffer[i8 + 1] & 255) << 8) | ((this._buffer[i8 + 2] & 255) << 16);
            }
            this._ptr += 4;
            if (i5 > 65535) {
                if (i5 > 1114111) {
                    reportInvalid(i5, i3 - i, "(above " + Integer.toHexString(1114111) + ") ");
                }
                int i9 = i5 - 65536;
                i4 = i3 + 1;
                cArr[i3] = (char) ((i9 >> 10) + 55296);
                i5 = (i9 & 1023) | 56320;
                if (i4 >= i6) {
                    this.mSurrogate = (char) i5;
                    break;
                }
                i3 = i4;
            }
            i4 = i3 + 1;
            cArr[i3] = (char) i5;
            if (this._ptr >= this._length) {
                break;
            }
            i3 = i4;
        }
        int i10 = i4 - i;
        this.mCharCount += i10;
        return i10;
    }

    private void reportUnexpectedEOF(int i, int i2) throws IOException {
        int i3 = this.mCharCount;
        throw new CharConversionException("Unexpected EOF in the middle of a 4-byte UTF-32 char: got " + i + ", needed " + i2 + ", at char #" + i3 + ", byte #" + (this.mByteCount + i) + ")");
    }

    private void reportInvalid(int i, int i2, String str) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid UTF-32 character 0x");
        sb.append(Integer.toHexString(i));
        sb.append(str);
        sb.append(" at char #");
        sb.append(this.mCharCount + i2);
        sb.append(", byte #");
        sb.append((this.mByteCount + this._ptr) - 1);
        sb.append(")");
        throw new CharConversionException(sb.toString());
    }

    private boolean loadMore(int i) throws IOException {
        this.mByteCount += this._length - i;
        if (i > 0) {
            if (this._ptr > 0) {
                for (int i2 = 0; i2 < i; i2++) {
                    this._buffer[i2] = this._buffer[this._ptr + i2];
                }
                this._ptr = 0;
            }
            this._length = i;
        } else {
            this._ptr = 0;
            int read = this._in.read(this._buffer);
            if (read < 1) {
                this._length = 0;
                if (read < 0) {
                    freeBuffers();
                    return false;
                }
                reportStrangeStream();
            }
            this._length = read;
        }
        while (this._length < 4) {
            int read2 = this._in.read(this._buffer, this._length, this._buffer.length - this._length);
            if (read2 < 1) {
                if (read2 < 0) {
                    freeBuffers();
                    reportUnexpectedEOF(this._length, 4);
                }
                reportStrangeStream();
            }
            this._length += read2;
        }
        return true;
    }
}
