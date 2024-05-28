package net.lingala.zip4j.p410io.inputstream;

import java.io.IOException;
import java.io.InputStream;
import net.lingala.zip4j.crypto.Decrypter;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.util.Zip4jUtil;

/* renamed from: net.lingala.zip4j.io.inputstream.CipherInputStream */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
abstract class CipherInputStream<T extends Decrypter> extends InputStream {
    private T decrypter;
    private byte[] lastReadRawDataCache;
    private LocalFileHeader localFileHeader;
    private byte[] singleByteBuffer = new byte[1];
    private ZipEntryInputStream zipEntryInputStream;

    /* JADX INFO: Access modifiers changed from: protected */
    public void endOfEntryReached(InputStream inputStream) throws IOException {
    }

    protected abstract T initializeDecrypter(LocalFileHeader localFileHeader, char[] cArr) throws IOException, ZipException;

    public CipherInputStream(ZipEntryInputStream zipEntryInputStream, LocalFileHeader localFileHeader, char[] cArr) throws IOException, ZipException {
        this.zipEntryInputStream = zipEntryInputStream;
        this.decrypter = initializeDecrypter(localFileHeader, cArr);
        this.localFileHeader = localFileHeader;
        if (getCompressionMethod(localFileHeader) == CompressionMethod.DEFLATE) {
            this.lastReadRawDataCache = new byte[4096];
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.singleByteBuffer) == -1) {
            return -1;
        }
        return this.singleByteBuffer[0] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int readFully = Zip4jUtil.readFully(this.zipEntryInputStream, bArr, i, i2);
        if (readFully > 0) {
            cacheRawData(bArr, readFully);
            this.decrypter.decryptData(bArr, i, readFully);
        }
        return readFully;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.zipEntryInputStream.close();
    }

    public byte[] getLastReadRawDataCache() {
        return this.lastReadRawDataCache;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int readRaw(byte[] bArr) throws IOException {
        return this.zipEntryInputStream.readRawFully(bArr);
    }

    private void cacheRawData(byte[] bArr, int i) {
        byte[] bArr2 = this.lastReadRawDataCache;
        if (bArr2 != null) {
            System.arraycopy(bArr, 0, bArr2, 0, i);
        }
    }

    private CompressionMethod getCompressionMethod(LocalFileHeader localFileHeader) throws ZipException {
        if (localFileHeader.getCompressionMethod() != CompressionMethod.AES_INTERNAL_ONLY) {
            return localFileHeader.getCompressionMethod();
        }
        if (localFileHeader.getAesExtraDataRecord() == null) {
            throw new ZipException("AesExtraDataRecord not present in localheader for aes encrypted data");
        }
        return localFileHeader.getAesExtraDataRecord().getCompressionMethod();
    }

    public T getDecrypter() {
        return this.decrypter;
    }

    protected long getNumberOfBytesReadForThisEntry() {
        return this.zipEntryInputStream.getNumberOfBytesRead();
    }

    public LocalFileHeader getLocalFileHeader() {
        return this.localFileHeader;
    }
}
