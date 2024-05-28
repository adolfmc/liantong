package net.lingala.zip4j.tasks;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import net.lingala.zip4j.headers.HeaderUtil;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.p410io.inputstream.SplitInputStream;
import net.lingala.zip4j.p410io.inputstream.ZipInputStream;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;
import net.lingala.zip4j.util.UnzipUtil;
import net.lingala.zip4j.util.Zip4jUtil;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class ExtractFileTask extends AbstractExtractFileTask<ExtractFileTaskParameters> {
    private char[] password;
    private SplitInputStream splitInputStream;

    public ExtractFileTask(ZipModel zipModel, char[] cArr, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(zipModel, asyncTaskParameters);
        this.password = cArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public void executeTask(ExtractFileTaskParameters extractFileTaskParameters, ProgressMonitor progressMonitor) throws IOException {
        try {
            ZipInputStream createZipInputStream = createZipInputStream(extractFileTaskParameters.fileHeader, extractFileTaskParameters.charset);
            for (FileHeader fileHeader : getFileHeadersToExtract(extractFileTaskParameters.fileHeader)) {
                extractFile(createZipInputStream, fileHeader, extractFileTaskParameters.outputPath, determineNewFileName(extractFileTaskParameters.newFileName, extractFileTaskParameters.fileHeader, fileHeader), progressMonitor);
            }
            if (createZipInputStream != null) {
                createZipInputStream.close();
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
    public long calculateTotalWork(ExtractFileTaskParameters extractFileTaskParameters) {
        return HeaderUtil.getTotalUncompressedSizeOfAllFileHeaders(getFileHeadersToExtract(extractFileTaskParameters.fileHeader));
    }

    private List<FileHeader> getFileHeadersToExtract(FileHeader fileHeader) {
        if (!fileHeader.isDirectory()) {
            return Collections.singletonList(fileHeader);
        }
        return HeaderUtil.getFileHeadersUnderDirectory(getZipModel().getCentralDirectory().getFileHeaders(), fileHeader);
    }

    private ZipInputStream createZipInputStream(FileHeader fileHeader, Charset charset) throws IOException {
        this.splitInputStream = UnzipUtil.createSplitInputStream(getZipModel());
        this.splitInputStream.prepareExtractionForFileHeader(fileHeader);
        return new ZipInputStream(this.splitInputStream, this.password, charset);
    }

    private String determineNewFileName(String str, FileHeader fileHeader, FileHeader fileHeader2) {
        if (Zip4jUtil.isStringNotNullAndNotEmpty(str) && fileHeader.isDirectory()) {
            String str2 = str.endsWith("/") ? "" : "/";
            String fileName = fileHeader2.getFileName();
            String fileName2 = fileHeader.getFileName();
            return fileName.replaceFirst(fileName2, str + str2);
        }
        return str;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class ExtractFileTaskParameters extends AbstractZipTaskParameters {
        private FileHeader fileHeader;
        private String newFileName;
        private String outputPath;

        public ExtractFileTaskParameters(String str, FileHeader fileHeader, String str2, Charset charset) {
            super(charset);
            this.outputPath = str;
            this.fileHeader = fileHeader;
            this.newFileName = str2;
        }
    }
}
