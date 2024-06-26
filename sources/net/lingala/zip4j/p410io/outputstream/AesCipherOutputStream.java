package net.lingala.zip4j.p410io.outputstream;

import java.io.IOException;
import java.io.OutputStream;
import net.lingala.zip4j.crypto.AESEncrpyter;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;

/* renamed from: net.lingala.zip4j.io.outputstream.AesCipherOutputStream */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
class AesCipherOutputStream extends CipherOutputStream<AESEncrpyter> {
    private byte[] pendingBuffer;
    private int pendingBufferLength;

    public AesCipherOutputStream(ZipEntryOutputStream zipEntryOutputStream, ZipParameters zipParameters, char[] cArr) throws IOException, ZipException {
        super(zipEntryOutputStream, zipParameters, cArr);
        this.pendingBuffer = new byte[16];
        this.pendingBufferLength = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // net.lingala.zip4j.p410io.outputstream.CipherOutputStream
    public AESEncrpyter initializeEncrypter(OutputStream outputStream, ZipParameters zipParameters, char[] cArr) throws IOException, ZipException {
        AESEncrpyter aESEncrpyter = new AESEncrpyter(cArr, zipParameters.getAesKeyStrength());
        writeAesEncryptionHeaderData(aESEncrpyter);
        return aESEncrpyter;
    }

    private void writeAesEncryptionHeaderData(AESEncrpyter aESEncrpyter) throws IOException {
        writeHeaders(aESEncrpyter.getSaltBytes());
        writeHeaders(aESEncrpyter.getDerivedPasswordVerifier());
    }

    @Override // net.lingala.zip4j.p410io.outputstream.CipherOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        write(new byte[]{(byte) i});
    }

    @Override // net.lingala.zip4j.p410io.outputstream.CipherOutputStream, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // net.lingala.zip4j.p410io.outputstream.CipherOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        int i4 = this.pendingBufferLength;
        if (i2 >= 16 - i4) {
            System.arraycopy(bArr, i, this.pendingBuffer, i4, 16 - i4);
            byte[] bArr2 = this.pendingBuffer;
            super.write(bArr2, 0, bArr2.length);
            int i5 = 16 - this.pendingBufferLength;
            int i6 = i2 - i5;
            this.pendingBufferLength = 0;
            if (i6 != 0 && (i3 = i6 % 16) != 0) {
                System.arraycopy(bArr, (i6 + i5) - i3, this.pendingBuffer, 0, i3);
                this.pendingBufferLength = i3;
                i6 -= this.pendingBufferLength;
            }
            super.write(bArr, i5, i6);
            return;
        }
        System.arraycopy(bArr, i, this.pendingBuffer, i4, i2);
        this.pendingBufferLength += i2;
    }

    @Override // net.lingala.zip4j.p410io.outputstream.CipherOutputStream
    public void closeEntry() throws IOException {
        int i = this.pendingBufferLength;
        if (i != 0) {
            super.write(this.pendingBuffer, 0, i);
            this.pendingBufferLength = 0;
        }
        writeHeaders(getEncrypter().getFinalMac());
        super.closeEntry();
    }
}
