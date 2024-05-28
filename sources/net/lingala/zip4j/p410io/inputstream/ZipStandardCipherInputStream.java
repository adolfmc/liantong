package net.lingala.zip4j.p410io.inputstream;

import java.io.IOException;
import net.lingala.zip4j.crypto.StandardDecrypter;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.LocalFileHeader;

/* renamed from: net.lingala.zip4j.io.inputstream.ZipStandardCipherInputStream */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
class ZipStandardCipherInputStream extends CipherInputStream<StandardDecrypter> {
    public ZipStandardCipherInputStream(ZipEntryInputStream zipEntryInputStream, LocalFileHeader localFileHeader, char[] cArr) throws IOException, ZipException {
        super(zipEntryInputStream, localFileHeader, cArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.p410io.inputstream.CipherInputStream
    public StandardDecrypter initializeDecrypter(LocalFileHeader localFileHeader, char[] cArr) throws ZipException, IOException {
        return new StandardDecrypter(cArr, localFileHeader.getCrcRawData(), getStandardDecrypterHeaderBytes());
    }

    private byte[] getStandardDecrypterHeaderBytes() throws IOException {
        byte[] bArr = new byte[12];
        readRaw(bArr);
        return bArr;
    }
}
