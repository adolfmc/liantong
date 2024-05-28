package net.lingala.zip4j.tasks;

import java.io.IOException;
import java.nio.charset.Charset;
import net.lingala.zip4j.headers.HeaderUtil;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.p410io.inputstream.SplitInputStream;
import net.lingala.zip4j.p410io.inputstream.ZipInputStream;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;
import net.lingala.zip4j.util.UnzipUtil;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class ExtractAllFilesTask extends AbstractExtractFileTask<ExtractAllFilesTaskParameters> {
    private char[] password;
    private SplitInputStream splitInputStream;

    public ExtractAllFilesTask(ZipModel zipModel, char[] cArr, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(zipModel, asyncTaskParameters);
        this.password = cArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public void executeTask(ExtractAllFilesTaskParameters extractAllFilesTaskParameters, ProgressMonitor progressMonitor) throws IOException {
        try {
            ZipInputStream prepareZipInputStream = prepareZipInputStream(extractAllFilesTaskParameters.charset);
            for (FileHeader fileHeader : getZipModel().getCentralDirectory().getFileHeaders()) {
                if (fileHeader.getFileName().startsWith("__MACOSX")) {
                    progressMonitor.updateWorkCompleted(fileHeader.getUncompressedSize());
                } else {
                    this.splitInputStream.prepareExtractionForFileHeader(fileHeader);
                    extractFile(prepareZipInputStream, fileHeader, extractAllFilesTaskParameters.outputPath, null, progressMonitor);
                    verifyIfTaskIsCancelled();
                }
            }
            if (prepareZipInputStream != null) {
                prepareZipInputStream.close();
            }
        } finally {
            SplitInputStream splitInputStream = this.splitInputStream;
            if (splitInputStream != null) {
                splitInputStream.close();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public long calculateTotalWork(ExtractAllFilesTaskParameters extractAllFilesTaskParameters) {
        return HeaderUtil.getTotalUncompressedSizeOfAllFileHeaders(getZipModel().getCentralDirectory().getFileHeaders());
    }

    private ZipInputStream prepareZipInputStream(Charset charset) throws IOException {
        this.splitInputStream = UnzipUtil.createSplitInputStream(getZipModel());
        FileHeader firstFileHeader = getFirstFileHeader(getZipModel());
        if (firstFileHeader != null) {
            this.splitInputStream.prepareExtractionForFileHeader(firstFileHeader);
        }
        return new ZipInputStream(this.splitInputStream, this.password, charset);
    }

    private FileHeader getFirstFileHeader(ZipModel zipModel) {
        if (zipModel.getCentralDirectory() == null || zipModel.getCentralDirectory().getFileHeaders() == null || zipModel.getCentralDirectory().getFileHeaders().size() == 0) {
            return null;
        }
        return zipModel.getCentralDirectory().getFileHeaders().get(0);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class ExtractAllFilesTaskParameters extends AbstractZipTaskParameters {
        private String outputPath;

        public ExtractAllFilesTaskParameters(String str, Charset charset) {
            super(charset);
            this.outputPath = str;
        }
    }
}
