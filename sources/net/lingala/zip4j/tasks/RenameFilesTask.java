package net.lingala.zip4j.tasks;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderUtil;
import net.lingala.zip4j.headers.HeaderWriter;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;
import net.lingala.zip4j.util.RawIO;
import net.lingala.zip4j.util.Zip4jUtil;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class RenameFilesTask extends AbstractModifyFileTask<RenameFilesTaskParameters> {
    private Charset charset;
    private HeaderWriter headerWriter;
    private RawIO rawIO;
    private ZipModel zipModel;

    public RenameFilesTask(ZipModel zipModel, HeaderWriter headerWriter, RawIO rawIO, Charset charset, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(asyncTaskParameters);
        this.zipModel = zipModel;
        this.headerWriter = headerWriter;
        this.rawIO = rawIO;
        this.charset = charset;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public void executeTask(net.lingala.zip4j.tasks.RenameFilesTask.RenameFilesTaskParameters r21, net.lingala.zip4j.progress.ProgressMonitor r22) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 399
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.tasks.RenameFilesTask.executeTask(net.lingala.zip4j.tasks.RenameFilesTask$RenameFilesTaskParameters, net.lingala.zip4j.progress.ProgressMonitor):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public long calculateTotalWork(RenameFilesTaskParameters renameFilesTaskParameters) {
        return this.zipModel.getZipFile().length();
    }

    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    protected ProgressMonitor.Task getTask() {
        return ProgressMonitor.Task.RENAME_FILE;
    }

    private long copyEntryAndChangeFileName(byte[] bArr, FileHeader fileHeader, long j, long j2, RandomAccessFile randomAccessFile, OutputStream outputStream, ProgressMonitor progressMonitor) throws IOException {
        this.rawIO.writeShortLittleEndian(outputStream, bArr.length);
        long copyFile = j + copyFile(randomAccessFile, outputStream, j, 26L, progressMonitor) + 2;
        outputStream.write(bArr);
        long copyFile2 = copyFile + copyFile(randomAccessFile, outputStream, copyFile, 2L, progressMonitor) + fileHeader.getFileNameLength();
        return copyFile2 + copyFile(randomAccessFile, outputStream, copyFile2, j2 - (copyFile2 - j), progressMonitor);
    }

    private Map.Entry<String, String> getCorrespondingEntryFromMap(FileHeader fileHeader, Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (fileHeader.getFileName().startsWith(entry.getKey())) {
                return entry;
            }
        }
        return null;
    }

    private void updateHeadersInZipModel(FileHeader fileHeader, String str, byte[] bArr, int i) throws ZipException {
        FileHeader fileHeader2 = HeaderUtil.getFileHeader(this.zipModel, fileHeader.getFileName());
        if (fileHeader2 == null) {
            throw new ZipException("could not find any header with name: " + fileHeader.getFileName());
        }
        fileHeader2.setFileName(str);
        fileHeader2.setFileNameLength(bArr.length);
        long j = i;
        updateOffsetsForAllSubsequentFileHeaders(this.zipModel, fileHeader2, j);
        this.zipModel.getEndOfCentralDirectoryRecord().setOffsetOfStartOfCentralDirectory(this.zipModel.getEndOfCentralDirectoryRecord().getOffsetOfStartOfCentralDirectory() + j);
        if (this.zipModel.isZip64Format()) {
            this.zipModel.getZip64EndOfCentralDirectoryRecord().setOffsetStartCentralDirectoryWRTStartDiskNumber(this.zipModel.getZip64EndOfCentralDirectoryRecord().getOffsetStartCentralDirectoryWRTStartDiskNumber() + j);
            this.zipModel.getZip64EndOfCentralDirectoryLocator().setOffsetZip64EndOfCentralDirectoryRecord(this.zipModel.getZip64EndOfCentralDirectoryLocator().getOffsetZip64EndOfCentralDirectoryRecord() + j);
        }
    }

    private Map<String, String> filterNonExistingEntriesAndAddSeparatorIfNeeded(Map<String, String> map) throws ZipException {
        FileHeader fileHeader;
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (Zip4jUtil.isStringNotNullAndNotEmpty(entry.getKey()) && (fileHeader = HeaderUtil.getFileHeader(this.zipModel, entry.getKey())) != null) {
                if (fileHeader.isDirectory() && !entry.getValue().endsWith("/")) {
                    String key = entry.getKey();
                    hashMap.put(key, entry.getValue() + "/");
                } else {
                    hashMap.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return hashMap;
    }

    private String getNewFileName(String str, String str2, String str3) throws ZipException {
        if (str3.equals(str2)) {
            return str;
        }
        if (str3.startsWith(str2)) {
            String substring = str3.substring(str2.length());
            return str + substring;
        }
        throw new ZipException("old file name was neither an exact match nor a partial match");
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class RenameFilesTaskParameters {
        private Map<String, String> fileNamesMap;

        static /* synthetic */ Map access$000(RenameFilesTaskParameters renameFilesTaskParameters) {
            return renameFilesTaskParameters.fileNamesMap;
        }

        public RenameFilesTaskParameters(Map<String, String> map) {
            this.fileNamesMap = map;
        }
    }
}
