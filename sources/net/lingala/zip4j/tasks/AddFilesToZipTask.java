package net.lingala.zip4j.tasks;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderWriter;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class AddFilesToZipTask extends AbstractAddFileToZipTask<AddFilesToZipTaskParameters> {
    public AddFilesToZipTask(ZipModel zipModel, char[] cArr, HeaderWriter headerWriter, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(zipModel, cArr, headerWriter, asyncTaskParameters);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public void executeTask(AddFilesToZipTaskParameters addFilesToZipTaskParameters, ProgressMonitor progressMonitor) throws IOException {
        verifyZipParameters(addFilesToZipTaskParameters.zipParameters);
        addFilesToZip(addFilesToZipTaskParameters.filesToAdd, progressMonitor, addFilesToZipTaskParameters.zipParameters, addFilesToZipTaskParameters.charset);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public long calculateTotalWork(AddFilesToZipTaskParameters addFilesToZipTaskParameters) throws ZipException {
        return calculateWorkForFiles(addFilesToZipTaskParameters.filesToAdd, addFilesToZipTaskParameters.zipParameters);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AbstractAddFileToZipTask, net.lingala.zip4j.tasks.AsyncZipTask
    public ProgressMonitor.Task getTask() {
        return super.getTask();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class AddFilesToZipTaskParameters extends AbstractZipTaskParameters {
        private List<File> filesToAdd;
        private ZipParameters zipParameters;

        public AddFilesToZipTaskParameters(List<File> list, ZipParameters zipParameters, Charset charset) {
            super(charset);
            this.filesToAdd = list;
            this.zipParameters = zipParameters;
        }
    }
}
