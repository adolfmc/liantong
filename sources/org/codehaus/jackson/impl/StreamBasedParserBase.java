package org.codehaus.jackson.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.p467io.IOContext;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class StreamBasedParserBase extends JsonParserBase {
    protected boolean _bufferRecyclable;
    protected byte[] _inputBuffer;
    protected InputStream _inputStream;

    protected StreamBasedParserBase(IOContext iOContext, int i, InputStream inputStream, byte[] bArr, int i2, int i3, boolean z) {
        super(iOContext, i);
        this._inputStream = inputStream;
        this._inputBuffer = bArr;
        this._inputPtr = i2;
        this._inputEnd = i3;
        this._bufferRecyclable = z;
    }

    @Override // org.codehaus.jackson.JsonParser
    public int releaseBuffered(OutputStream outputStream) throws IOException {
        int i = this._inputEnd - this._inputPtr;
        if (i < 1) {
            return 0;
        }
        outputStream.write(this._inputBuffer, this._inputPtr, i);
        return i;
    }

    @Override // org.codehaus.jackson.JsonParser
    public Object getInputSource() {
        return this._inputStream;
    }

    @Override // org.codehaus.jackson.impl.JsonParserBase
    protected final boolean loadMore() throws IOException {
        this._currInputProcessed += this._inputEnd;
        this._currInputRowStart -= this._inputEnd;
        InputStream inputStream = this._inputStream;
        if (inputStream != null) {
            byte[] bArr = this._inputBuffer;
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read > 0) {
                this._inputPtr = 0;
                this._inputEnd = read;
                return true;
            }
            _closeInput();
            if (read == 0) {
                throw new IOException("InputStream.read() returned 0 characters when trying to read " + this._inputBuffer.length + " bytes");
            }
        }
        return false;
    }

    protected final boolean _loadToHaveAtLeast(int i) throws IOException {
        if (this._inputStream == null) {
            return false;
        }
        int i2 = this._inputEnd - this._inputPtr;
        if (i2 > 0 && this._inputPtr > 0) {
            this._currInputProcessed += this._inputPtr;
            this._currInputRowStart -= this._inputPtr;
            System.arraycopy(this._inputBuffer, this._inputPtr, this._inputBuffer, 0, i2);
            this._inputEnd = i2;
        } else {
            this._inputEnd = 0;
        }
        this._inputPtr = 0;
        while (this._inputEnd < i) {
            int read = this._inputStream.read(this._inputBuffer, this._inputEnd, this._inputBuffer.length - this._inputEnd);
            if (read < 1) {
                _closeInput();
                if (read != 0) {
                    return false;
                }
                throw new IOException("InputStream.read() returned 0 characters when trying to read " + i2 + " bytes");
            }
            this._inputEnd += read;
        }
        return true;
    }

    @Override // org.codehaus.jackson.impl.JsonParserBase
    protected void _closeInput() throws IOException {
        if (this._inputStream != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                this._inputStream.close();
            }
            this._inputStream = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.codehaus.jackson.impl.JsonParserBase
    public void _releaseBuffers() throws IOException {
        byte[] bArr;
        super._releaseBuffers();
        if (!this._bufferRecyclable || (bArr = this._inputBuffer) == null) {
            return;
        }
        this._inputBuffer = null;
        this._ioContext.releaseReadIOBuffer(bArr);
    }
}
