package net.lingala.zip4j.progress;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class ProgressMonitor {
    private boolean cancelAllTasks;
    private Task currentTask;
    private Exception exception;
    private String fileName;
    private boolean pause;
    private int percentDone;
    private Result result;
    private State state;
    private long totalWork;
    private long workCompleted;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum Result {
        SUCCESS,
        WORK_IN_PROGRESS,
        ERROR,
        CANCELLED
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum State {
        READY,
        BUSY
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum Task {
        NONE,
        ADD_ENTRY,
        REMOVE_ENTRY,
        CALCULATE_CRC,
        EXTRACT_ENTRY,
        MERGE_ZIP_FILES,
        SET_COMMENT,
        RENAME_FILE
    }

    public ProgressMonitor() {
        reset();
    }

    public void updateWorkCompleted(long j) {
        this.workCompleted += j;
        long j2 = this.totalWork;
        if (j2 > 0) {
            this.percentDone = (int) ((this.workCompleted * 100) / j2);
            if (this.percentDone > 100) {
                this.percentDone = 100;
            }
        }
        while (this.pause) {
            try {
                Thread.sleep(150L);
            } catch (InterruptedException unused) {
            }
        }
    }

    public void endProgressMonitor() {
        this.result = Result.SUCCESS;
        this.percentDone = 100;
        reset();
    }

    public void endProgressMonitor(Exception exc) {
        this.result = Result.ERROR;
        this.exception = exc;
        reset();
    }

    public void fullReset() {
        reset();
        this.fileName = null;
        this.totalWork = 0L;
        this.workCompleted = 0L;
        this.percentDone = 0;
    }

    private void reset() {
        this.currentTask = Task.NONE;
        this.state = State.READY;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public long getTotalWork() {
        return this.totalWork;
    }

    public void setTotalWork(long j) {
        this.totalWork = j;
    }

    public long getWorkCompleted() {
        return this.workCompleted;
    }

    public int getPercentDone() {
        return this.percentDone;
    }

    public void setPercentDone(int i) {
        this.percentDone = i;
    }

    public Task getCurrentTask() {
        return this.currentTask;
    }

    public void setCurrentTask(Task task) {
        this.currentTask = task;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public Result getResult() {
        return this.result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Exception getException() {
        return this.exception;
    }

    public void setException(Exception exc) {
        this.exception = exc;
    }

    public boolean isCancelAllTasks() {
        return this.cancelAllTasks;
    }

    public void setCancelAllTasks(boolean z) {
        this.cancelAllTasks = z;
    }

    public boolean isPause() {
        return this.pause;
    }

    public void setPause(boolean z) {
        this.pause = z;
    }
}
