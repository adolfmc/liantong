package org.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class DERGenerator extends ASN1Generator {
    private boolean _isExplicit;
    private int _tagNo;
    private boolean _tagged;

    /* JADX INFO: Access modifiers changed from: protected */
    public DERGenerator(OutputStream outputStream) {
        super(outputStream);
        this._tagged = false;
    }

    public DERGenerator(OutputStream outputStream, int i, boolean z) {
        super(outputStream);
        this._tagged = false;
        this._tagged = true;
        this._isExplicit = z;
        this._tagNo = i;
    }

    private void writeLength(OutputStream outputStream, int i) throws IOException {
        if (i <= 127) {
            outputStream.write((byte) i);
            return;
        }
        int i2 = i;
        int i3 = 1;
        while (true) {
            i2 >>>= 8;
            if (i2 == 0) {
                break;
            }
            i3++;
        }
        outputStream.write((byte) (i3 | 128));
        for (int i4 = (i3 - 1) * 8; i4 >= 0; i4 -= 8) {
            outputStream.write((byte) (i >> i4));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeDEREncoded(int i, byte[] bArr) throws IOException {
        OutputStream outputStream;
        int i2;
        if (!this._tagged) {
            writeDEREncoded(this._out, i, bArr);
            return;
        }
        int i3 = this._tagNo;
        int i4 = i3 | 128;
        if (this._isExplicit) {
            i2 = i3 | 32 | 128;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            writeDEREncoded(byteArrayOutputStream, i, bArr);
            outputStream = this._out;
            bArr = byteArrayOutputStream.toByteArray();
        } else if ((i & 32) == 0) {
            writeDEREncoded(this._out, i4, bArr);
            return;
        } else {
            outputStream = this._out;
            i2 = i4 | 32;
        }
        writeDEREncoded(outputStream, i2, bArr);
    }

    void writeDEREncoded(OutputStream outputStream, int i, byte[] bArr) throws IOException {
        outputStream.write(i);
        writeLength(outputStream, bArr.length);
        outputStream.write(bArr);
    }
}
