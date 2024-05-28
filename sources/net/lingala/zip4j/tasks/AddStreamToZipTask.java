package net.lingala.zip4j.tasks;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderUtil;
import net.lingala.zip4j.headers.HeaderWriter;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.p410io.outputstream.SplitOutputStream;
import net.lingala.zip4j.p410io.outputstream.ZipOutputStream;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;
import net.lingala.zip4j.util.Zip4jUtil;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class AddStreamToZipTask extends AbstractAddFileToZipTask<AddStreamToZipTaskParameters> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public long calculateTotalWork(AddStreamToZipTaskParameters addStreamToZipTaskParameters) {
        return 0L;
    }

    public AddStreamToZipTask(ZipModel zipModel, char[] cArr, HeaderWriter headerWriter, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(zipModel, cArr, headerWriter, asyncTaskParameters);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public void executeTask(AddStreamToZipTaskParameters addStreamToZipTaskParameters, ProgressMonitor progressMonitor) throws IOException {
        verifyZipParameters(addStreamToZipTaskParameters.zipParameters);
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(addStreamToZipTaskParameters.zipParameters.getFileNameInZip())) {
            throw new ZipException("fileNameInZip has to be set in zipParameters when adding stream");
        }
        removeFileIfExists(getZipModel(), addStreamToZipTaskParameters.charset, addStreamToZipTaskParameters.zipParameters.getFileNameInZip(), progressMonitor);
        addStreamToZipTaskParameters.zipParameters.setWriteExtendedLocalFileHeader(true);
        if (addStreamToZipTaskParameters.zipParameters.getCompressionMethod().equals(CompressionMethod.STORE)) {
            addStreamToZipTaskParameters.zipParameters.setEntrySize(0L);
        }
        SplitOutputStream splitOutputStream = new SplitOutputStream(getZipModel().getZipFile(), getZipModel().getSplitLength());
        try {
            ZipOutputStream initializeOutputStream = initializeOutputStream(splitOutputStream, addStreamToZipTaskParameters.charset);
            byte[] bArr = new byte[4096];
            ZipParameters zipParameters = addStreamToZipTaskParameters.zipParameters;
            initializeOutputStream.putNextEntry(zipParameters);
            if (!zipParameters.getFileNameInZip().endsWith("/") && !zipParameters.getFileNameInZip().endsWith("\\")) {
                while (true) {
                    int read = addStreamToZipTaskParameters.inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    initializeOutputStream.write(bArr, 0, read);
                }
            }
            FileHeader closeEntry = initializeOutputStream.closeEntry();
            if (closeEntry.getCompressionMethod().equals(CompressionMethod.STORE)) {
                updateLocalFileHeader(closeEntry, splitOutputStream);
            }
            if (initializeOutputStream != null) {
                initializeOutputStream.close();
            }
            splitOutputStream.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (th != null) {
                    try {
                        splitOutputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                } else {
                    splitOutputStream.close();
                }
                throw th2;
            }
        }
    }

    private void removeFileIfExists(ZipModel zipModel, Charset charset, String str, ProgressMonitor progressMonitor) throws ZipException {
        FileHeader fileHeader = HeaderUtil.getFileHeader(zipModel, str);
        if (fileHeader != null) {
            removeFile(fileHeader, progressMonitor, charset);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class AddStreamToZipTaskParameters extends AbstractZipTaskParameters {
        private InputStream inputStream;
        private ZipParameters zipParameters;

        public AddStreamToZipTaskParameters(InputStream inputStream, ZipParameters zipParameters, Charset charset) {
            super(charset);
            this.inputStream = inputStream;
            this.zipParameters = zipParameters;
        }
    }
}
