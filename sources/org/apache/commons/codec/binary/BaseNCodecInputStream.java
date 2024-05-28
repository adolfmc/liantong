package org.apache.commons.codec.binary;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class BaseNCodecInputStream extends FilterInputStream {
    private final BaseNCodec baseNCodec;
    private final boolean doEncode;
    private final byte[] singleByte;

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseNCodecInputStream(InputStream inputStream, BaseNCodec baseNCodec, boolean z) {
        super(inputStream);
        this.singleByte = new byte[1];
        this.doEncode = z;
        this.baseNCodec = baseNCodec;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int read = read(this.singleByte, 0, 1);
        while (read == 0) {
            read = read(this.singleByte, 0, 1);
        }
        if (read > 0) {
            byte[] bArr = this.singleByte;
            return bArr[0] < 0 ? bArr[0] + 256 : bArr[0];
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (bArr != null) {
            if (i < 0 || i2 < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (i > bArr.length || i + i2 > bArr.length) {
                throw new IndexOutOfBoundsException();
            }
            if (i2 == 0) {
                return 0;
            }
            int i3 = 0;
            while (i3 == 0) {
                if (!this.baseNCodec.hasData()) {
                    byte[] bArr2 = new byte[this.doEncode ? 4096 : 8192];
                    int read = this.in.read(bArr2);
                    if (this.doEncode) {
                        this.baseNCodec.encode(bArr2, 0, read);
                    } else {
                        this.baseNCodec.decode(bArr2, 0, read);
                    }
                }
                i3 = this.baseNCodec.readResults(bArr, i, i2);
            }
            return i3;
        }
        throw new NullPointerException();
    }
}
