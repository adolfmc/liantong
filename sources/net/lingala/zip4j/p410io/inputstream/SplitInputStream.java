package net.lingala.zip4j.p410io.inputstream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.enums.RandomAccessFileMode;

/* renamed from: net.lingala.zip4j.io.inputstream.SplitInputStream */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class SplitInputStream extends InputStream {
    private int currentSplitFileCounter;
    private boolean isSplitZipArchive;
    protected RandomAccessFile randomAccessFile;
    private byte[] singleByteArray = new byte[1];
    protected File zipFile;

    protected abstract File getNextSplitFile(int i) throws IOException;

    public SplitInputStream(File file, boolean z, int i) throws FileNotFoundException {
        this.currentSplitFileCounter = 0;
        this.randomAccessFile = new RandomAccessFile(file, RandomAccessFileMode.READ.getValue());
        this.zipFile = file;
        this.isSplitZipArchive = z;
        if (z) {
            this.currentSplitFileCounter = i;
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.singleByteArray) == -1) {
            return -1;
        }
        return this.singleByteArray[0];
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.randomAccessFile.read(bArr, i, i2);
        if ((read != i2 || read == -1) && this.isSplitZipArchive) {
            openRandomAccessFileForIndex(this.currentSplitFileCounter + 1);
            this.currentSplitFileCounter++;
            if (read < 0) {
                read = 0;
            }
            int read2 = this.randomAccessFile.read(bArr, read, i2 - read);
            return read2 > 0 ? read + read2 : read;
        }
        return read;
    }

    public void prepareExtractionForFileHeader(FileHeader fileHeader) throws IOException {
        if (this.isSplitZipArchive && this.currentSplitFileCounter != fileHeader.getDiskNumberStart()) {
            openRandomAccessFileForIndex(fileHeader.getDiskNumberStart());
            this.currentSplitFileCounter = fileHeader.getDiskNumberStart();
        }
        this.randomAccessFile.seek(fileHeader.getOffsetLocalHeader());
    }

    protected void openRandomAccessFileForIndex(int i) throws IOException {
        File nextSplitFile = getNextSplitFile(i);
        if (!nextSplitFile.exists()) {
            throw new FileNotFoundException("zip split file does not exist: " + nextSplitFile);
        }
        this.randomAccessFile.close();
        this.randomAccessFile = new RandomAccessFile(nextSplitFile, RandomAccessFileMode.READ.getValue());
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        RandomAccessFile randomAccessFile = this.randomAccessFile;
        if (randomAccessFile != null) {
            randomAccessFile.close();
        }
    }
}
