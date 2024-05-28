package net.lingala.zip4j.tasks;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Random;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderUtil;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;
import net.lingala.zip4j.util.FileUtils;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
abstract class AbstractModifyFileTask<T> extends AsyncZipTask<T> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractModifyFileTask(AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(asyncTaskParameters);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public File getTemporaryFile(String str) {
        Random random = new Random();
        File file = new File(str + random.nextInt(10000));
        while (file.exists()) {
            file = new File(str + random.nextInt(10000));
        }
        return file;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateOffsetsForAllSubsequentFileHeaders(ZipModel zipModel, FileHeader fileHeader, long j) throws ZipException {
        int indexOfFileHeader = HeaderUtil.getIndexOfFileHeader(zipModel, fileHeader);
        if (indexOfFileHeader == -1) {
            throw new ZipException("Could not locate modified file header in zipModel");
        }
        List<FileHeader> fileHeaders = zipModel.getCentralDirectory().getFileHeaders();
        while (true) {
            indexOfFileHeader++;
            if (indexOfFileHeader >= fileHeaders.size()) {
                return;
            }
            FileHeader fileHeader2 = fileHeaders.get(indexOfFileHeader);
            fileHeader2.setOffsetLocalHeader(fileHeader2.getOffsetLocalHeader() + j);
            if (zipModel.isZip64Format() && fileHeader2.getZip64ExtendedInfo() != null && fileHeader2.getZip64ExtendedInfo().getOffsetLocalHeader() != -1) {
                fileHeader2.getZip64ExtendedInfo().setOffsetLocalHeader(fileHeader2.getZip64ExtendedInfo().getOffsetLocalHeader() + j);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cleanupFile(boolean z, File file, File file2) throws ZipException {
        if (z) {
            restoreFileName(file, file2);
        } else if (!file2.delete()) {
            throw new ZipException("Could not delete temporary file");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long copyFile(RandomAccessFile randomAccessFile, OutputStream outputStream, long j, long j2, ProgressMonitor progressMonitor) throws IOException {
        FileUtils.copyFile(randomAccessFile, outputStream, j, j + j2, progressMonitor);
        return j2;
    }

    private void restoreFileName(File file, File file2) throws ZipException {
        if (file.delete()) {
            if (!file2.renameTo(file)) {
                throw new ZipException("cannot rename modified zip file");
            }
            return;
        }
        throw new ZipException("cannot delete old zip file");
    }
}
