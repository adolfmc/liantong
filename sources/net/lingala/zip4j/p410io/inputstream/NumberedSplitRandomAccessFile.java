package net.lingala.zip4j.p410io.inputstream;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import net.lingala.zip4j.model.enums.RandomAccessFileMode;
import net.lingala.zip4j.util.FileUtils;

/* renamed from: net.lingala.zip4j.io.inputstream.NumberedSplitRandomAccessFile */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class NumberedSplitRandomAccessFile extends RandomAccessFile {
    private File[] allSortedSplitFiles;
    private int currentOpenSplitFileCounter;
    private RandomAccessFile randomAccessFile;
    private String rwMode;
    private byte[] singleByteBuffer;
    private long splitLength;

    public NumberedSplitRandomAccessFile(String str, String str2) throws IOException {
        this(new File(str), str2);
    }

    public NumberedSplitRandomAccessFile(File file, String str) throws IOException {
        this(file, str, FileUtils.getAllSortedNumberedSplitFiles(file));
    }

    public NumberedSplitRandomAccessFile(File file, String str, File[] fileArr) throws IOException {
        super(file, str);
        this.singleByteBuffer = new byte[1];
        this.currentOpenSplitFileCounter = 0;
        super.close();
        if (RandomAccessFileMode.WRITE.getValue().equals(str)) {
            throw new IllegalArgumentException("write mode is not allowed for NumberedSplitRandomAccessFile");
        }
        assertAllSplitFilesExist(fileArr);
        this.randomAccessFile = new RandomAccessFile(file, str);
        this.allSortedSplitFiles = fileArr;
        this.splitLength = file.length();
        this.rwMode = str;
    }

    @Override // java.io.RandomAccessFile
    public int read() throws IOException {
        if (read(this.singleByteBuffer) == -1) {
            return -1;
        }
        return this.singleByteBuffer[0] & 255;
    }

    @Override // java.io.RandomAccessFile
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.RandomAccessFile
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.randomAccessFile.read(bArr, i, i2);
        if (read == -1) {
            int i3 = this.currentOpenSplitFileCounter;
            if (i3 == this.allSortedSplitFiles.length - 1) {
                return -1;
            }
            openRandomAccessFileForIndex(i3 + 1);
            return read(bArr, i, i2);
        }
        return read;
    }

    @Override // java.io.RandomAccessFile, java.io.DataOutput
    public void write(int i) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.RandomAccessFile, java.io.DataOutput
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.RandomAccessFile, java.io.DataOutput
    public void write(byte[] bArr, int i, int i2) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.RandomAccessFile
    public void seek(long j) throws IOException {
        int i = (int) (j / this.splitLength);
        if (i != this.currentOpenSplitFileCounter) {
            openRandomAccessFileForIndex(i);
        }
        this.randomAccessFile.seek(j - (i * this.splitLength));
    }

    @Override // java.io.RandomAccessFile
    public long getFilePointer() throws IOException {
        return this.randomAccessFile.getFilePointer();
    }

    @Override // java.io.RandomAccessFile
    public long length() throws IOException {
        return this.randomAccessFile.length();
    }

    public void seekInCurrentPart(long j) throws IOException {
        this.randomAccessFile.seek(j);
    }

    public void openLastSplitFileForReading() throws IOException {
        openRandomAccessFileForIndex(this.allSortedSplitFiles.length - 1);
    }

    private void openRandomAccessFileForIndex(int i) throws IOException {
        if (this.currentOpenSplitFileCounter == i) {
            return;
        }
        if (i > this.allSortedSplitFiles.length - 1) {
            throw new IOException("split counter greater than number of split files");
        }
        RandomAccessFile randomAccessFile = this.randomAccessFile;
        if (randomAccessFile != null) {
            randomAccessFile.close();
        }
        this.randomAccessFile = new RandomAccessFile(this.allSortedSplitFiles[i], this.rwMode);
        this.currentOpenSplitFileCounter = i;
    }

    private void assertAllSplitFilesExist(File[] fileArr) throws IOException {
        int i = 1;
        for (File file : fileArr) {
            String fileExtension = FileUtils.getFileExtension(file);
            try {
                if (i != Integer.parseInt(fileExtension)) {
                    throw new IOException("Split file number " + i + " does not exist");
                }
                i++;
            } catch (NumberFormatException unused) {
                throw new IOException("Split file extension not in expected format. Found: " + fileExtension + " expected of format: .001, .002, etc");
            }
        }
    }
}
