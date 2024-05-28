package net.lingala.zip4j.tasks;

import java.io.IOException;
import java.nio.charset.Charset;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderWriter;
import net.lingala.zip4j.model.EndOfCentralDirectoryRecord;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.p410io.outputstream.SplitOutputStream;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class SetCommentTask extends AsyncZipTask<SetCommentTaskTaskParameters> {
    private ZipModel zipModel;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public long calculateTotalWork(SetCommentTaskTaskParameters setCommentTaskTaskParameters) {
        return 0L;
    }

    public SetCommentTask(ZipModel zipModel, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(asyncTaskParameters);
        this.zipModel = zipModel;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public void executeTask(SetCommentTaskTaskParameters setCommentTaskTaskParameters, ProgressMonitor progressMonitor) throws IOException {
        if (setCommentTaskTaskParameters.comment == null) {
            throw new ZipException("comment is null, cannot update Zip file with comment");
        }
        EndOfCentralDirectoryRecord endOfCentralDirectoryRecord = this.zipModel.getEndOfCentralDirectoryRecord();
        endOfCentralDirectoryRecord.setComment(setCommentTaskTaskParameters.comment);
        SplitOutputStream splitOutputStream = new SplitOutputStream(this.zipModel.getZipFile());
        try {
            if (this.zipModel.isZip64Format()) {
                splitOutputStream.seek(this.zipModel.getZip64EndOfCentralDirectoryRecord().getOffsetStartCentralDirectoryWRTStartDiskNumber());
            } else {
                splitOutputStream.seek(endOfCentralDirectoryRecord.getOffsetOfStartOfCentralDirectory());
            }
            new HeaderWriter().finalizeZipFileWithoutValidations(this.zipModel, splitOutputStream, setCommentTaskTaskParameters.charset);
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

    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    protected ProgressMonitor.Task getTask() {
        return ProgressMonitor.Task.SET_COMMENT;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class SetCommentTaskTaskParameters extends AbstractZipTaskParameters {
        private String comment;

        public SetCommentTaskTaskParameters(String str, Charset charset) {
            super(charset);
            this.comment = str;
        }
    }
}
