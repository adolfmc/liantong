package net.lingala.zip4j.tasks;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderUtil;
import net.lingala.zip4j.headers.HeaderWriter;
import net.lingala.zip4j.model.EndOfCentralDirectoryRecord;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.enums.RandomAccessFileMode;
import net.lingala.zip4j.p410io.outputstream.SplitOutputStream;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class RemoveFilesFromZipTask extends AbstractModifyFileTask<RemoveFilesFromZipTaskParameters> {
    private HeaderWriter headerWriter;
    private ZipModel zipModel;

    public RemoveFilesFromZipTask(ZipModel zipModel, HeaderWriter headerWriter, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(asyncTaskParameters);
        this.zipModel = zipModel;
        this.headerWriter = headerWriter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public void executeTask(RemoveFilesFromZipTaskParameters removeFilesFromZipTaskParameters, ProgressMonitor progressMonitor) throws IOException {
        if (this.zipModel.isSplitArchive()) {
            throw new ZipException("This is a split archive. Zip file format does not allow updating split/spanned files");
        }
        List<String> filterNonExistingEntries = filterNonExistingEntries(removeFilesFromZipTaskParameters.filesToRemove);
        if (filterNonExistingEntries.isEmpty()) {
            return;
        }
        File temporaryFile = getTemporaryFile(this.zipModel.getZipFile().getPath());
        try {
            SplitOutputStream splitOutputStream = new SplitOutputStream(temporaryFile);
            RandomAccessFile randomAccessFile = new RandomAccessFile(this.zipModel.getZipFile(), RandomAccessFileMode.READ.getValue());
            try {
                long j = 0;
                for (FileHeader fileHeader : new ArrayList(this.zipModel.getCentralDirectory().getFileHeaders())) {
                    long offsetOfNextEntry = HeaderUtil.getOffsetOfNextEntry(this.zipModel, fileHeader) - splitOutputStream.getFilePointer();
                    if (shouldEntryBeRemoved(fileHeader, filterNonExistingEntries)) {
                        updateHeaders(fileHeader, offsetOfNextEntry);
                        if (!this.zipModel.getCentralDirectory().getFileHeaders().remove(fileHeader)) {
                            throw new ZipException("Could not remove entry from list of central directory headers");
                        }
                        j += offsetOfNextEntry;
                    } else {
                        j += super.copyFile(randomAccessFile, splitOutputStream, j, offsetOfNextEntry, progressMonitor);
                    }
                    verifyIfTaskIsCancelled();
                }
                this.headerWriter.finalizeZipFile(this.zipModel, splitOutputStream, removeFilesFromZipTaskParameters.charset);
                randomAccessFile.close();
                splitOutputStream.close();
                cleanupFile(true, this.zipModel.getZipFile(), temporaryFile);
            } finally {
            }
        } catch (Throwable th) {
            cleanupFile(false, this.zipModel.getZipFile(), temporaryFile);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public long calculateTotalWork(RemoveFilesFromZipTaskParameters removeFilesFromZipTaskParameters) {
        return this.zipModel.getZipFile().length();
    }

    private List<String> filterNonExistingEntries(List<String> list) throws ZipException {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (HeaderUtil.getFileHeader(this.zipModel, str) != null) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private boolean shouldEntryBeRemoved(FileHeader fileHeader, List<String> list) {
        for (String str : list) {
            if (fileHeader.getFileName().startsWith(str)) {
                return true;
            }
        }
        return false;
    }

    private void updateHeaders(FileHeader fileHeader, long j) throws ZipException {
        updateOffsetsForAllSubsequentFileHeaders(this.zipModel, fileHeader, negate(j));
        EndOfCentralDirectoryRecord endOfCentralDirectoryRecord = this.zipModel.getEndOfCentralDirectoryRecord();
        endOfCentralDirectoryRecord.setOffsetOfStartOfCentralDirectory(endOfCentralDirectoryRecord.getOffsetOfStartOfCentralDirectory() - j);
        endOfCentralDirectoryRecord.setTotalNumberOfEntriesInCentralDirectory(endOfCentralDirectoryRecord.getTotalNumberOfEntriesInCentralDirectory() - 1);
        if (endOfCentralDirectoryRecord.getTotalNumberOfEntriesInCentralDirectoryOnThisDisk() > 0) {
            endOfCentralDirectoryRecord.setTotalNumberOfEntriesInCentralDirectoryOnThisDisk(endOfCentralDirectoryRecord.getTotalNumberOfEntriesInCentralDirectoryOnThisDisk() - 1);
        }
        if (this.zipModel.isZip64Format()) {
            this.zipModel.getZip64EndOfCentralDirectoryRecord().setOffsetStartCentralDirectoryWRTStartDiskNumber(this.zipModel.getZip64EndOfCentralDirectoryRecord().getOffsetStartCentralDirectoryWRTStartDiskNumber() - j);
            this.zipModel.getZip64EndOfCentralDirectoryRecord().setTotalNumberOfEntriesInCentralDirectoryOnThisDisk(this.zipModel.getZip64EndOfCentralDirectoryRecord().getTotalNumberOfEntriesInCentralDirectory() - 1);
            this.zipModel.getZip64EndOfCentralDirectoryLocator().setOffsetZip64EndOfCentralDirectoryRecord(this.zipModel.getZip64EndOfCentralDirectoryLocator().getOffsetZip64EndOfCentralDirectoryRecord() - j);
        }
    }

    private long negate(long j) {
        if (j != Long.MIN_VALUE) {
            return -j;
        }
        throw new ArithmeticException("long overflow");
    }

    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    protected ProgressMonitor.Task getTask() {
        return ProgressMonitor.Task.REMOVE_ENTRY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class RemoveFilesFromZipTaskParameters extends AbstractZipTaskParameters {
        private List<String> filesToRemove;

        public RemoveFilesFromZipTaskParameters(List<String> list, Charset charset) {
            super(charset);
            this.filesToRemove = list;
        }
    }
}
