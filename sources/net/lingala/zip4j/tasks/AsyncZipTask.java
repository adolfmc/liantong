package net.lingala.zip4j.tasks;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.progress.ProgressMonitor;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class AsyncZipTask<T> {
    private ExecutorService executorService;
    private ProgressMonitor progressMonitor;
    private boolean runInThread;

    protected abstract long calculateTotalWork(T t) throws ZipException;

    protected abstract void executeTask(T t, ProgressMonitor progressMonitor) throws IOException;

    protected abstract ProgressMonitor.Task getTask();

    public AsyncZipTask(AsyncTaskParameters asyncTaskParameters) {
        this.progressMonitor = asyncTaskParameters.progressMonitor;
        this.runInThread = asyncTaskParameters.runInThread;
        this.executorService = asyncTaskParameters.executorService;
    }

    public void execute(final T t) throws ZipException {
        this.progressMonitor.fullReset();
        this.progressMonitor.setState(ProgressMonitor.State.BUSY);
        this.progressMonitor.setCurrentTask(getTask());
        if (this.runInThread) {
            this.progressMonitor.setTotalWork(calculateTotalWork(t));
            this.executorService.execute(new Runnable() { // from class: net.lingala.zip4j.tasks.-$$Lambda$AsyncZipTask$00PZPFKWMmsJj6KwYmBoLe8dU4o
                @Override // java.lang.Runnable
                public final void run() {
                    r0.performTaskWithErrorHandling(t, AsyncZipTask.this.progressMonitor);
                }
            });
            return;
        }
        performTaskWithErrorHandling(t, this.progressMonitor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performTaskWithErrorHandling(T t, ProgressMonitor progressMonitor) throws ZipException {
        try {
            executeTask(t, progressMonitor);
            progressMonitor.endProgressMonitor();
        } catch (ZipException e) {
            progressMonitor.endProgressMonitor(e);
            throw e;
        } catch (Exception e2) {
            progressMonitor.endProgressMonitor(e2);
            throw new ZipException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void verifyIfTaskIsCancelled() throws ZipException {
        if (this.progressMonitor.isCancelAllTasks()) {
            this.progressMonitor.setResult(ProgressMonitor.Result.CANCELLED);
            this.progressMonitor.setState(ProgressMonitor.State.READY);
            throw new ZipException("Task cancelled", ZipException.Type.TASK_CANCELLED_EXCEPTION);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class AsyncTaskParameters {
        private ExecutorService executorService;
        private ProgressMonitor progressMonitor;
        private boolean runInThread;

        public AsyncTaskParameters(ExecutorService executorService, boolean z, ProgressMonitor progressMonitor) {
            this.executorService = executorService;
            this.runInThread = z;
            this.progressMonitor = progressMonitor;
        }
    }
}
