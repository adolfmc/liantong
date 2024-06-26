package com.liulishuo.okdownload.core.dispatcher;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.liulishuo.okdownload.DownloadListener;
import com.liulishuo.okdownload.DownloadMonitor;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CallbackDispatcher {
    private static final String TAG = "CallbackDispatcher";
    private final DownloadListener transmit;
    private final Handler uiHandler;

    CallbackDispatcher(@NonNull Handler handler, @NonNull DownloadListener downloadListener) {
        this.uiHandler = handler;
        this.transmit = downloadListener;
    }

    public CallbackDispatcher() {
        this.uiHandler = new Handler(Looper.getMainLooper());
        this.transmit = new DefaultTransmitListener(this.uiHandler);
    }

    public boolean isFetchProcessMoment(DownloadTask downloadTask) {
        long minIntervalMillisCallbackProcess = downloadTask.getMinIntervalMillisCallbackProcess();
        return minIntervalMillisCallbackProcess <= 0 || SystemClock.uptimeMillis() - DownloadTask.TaskHideWrapper.getLastCallbackProcessTs(downloadTask) >= minIntervalMillisCallbackProcess;
    }

    public void endTasksWithError(@NonNull final Collection<DownloadTask> collection, @NonNull final Exception exc) {
        if (collection.size() <= 0) {
            return;
        }
        Util.m13741d(TAG, "endTasksWithError error[" + collection.size() + "] realCause: " + exc);
        Iterator<DownloadTask> it = collection.iterator();
        while (it.hasNext()) {
            DownloadTask next = it.next();
            if (!next.isAutoCallbackToUIThread()) {
                next.getListener().taskEnd(next, EndCause.ERROR, exc);
                it.remove();
            }
        }
        this.uiHandler.post(new Runnable() { // from class: com.liulishuo.okdownload.core.dispatcher.CallbackDispatcher.1
            @Override // java.lang.Runnable
            public void run() {
                for (DownloadTask downloadTask : collection) {
                    downloadTask.getListener().taskEnd(downloadTask, EndCause.ERROR, exc);
                }
            }
        });
    }

    public void endTasks(@NonNull final Collection<DownloadTask> collection, @NonNull final Collection<DownloadTask> collection2, @NonNull final Collection<DownloadTask> collection3) {
        if (collection.size() == 0 && collection2.size() == 0 && collection3.size() == 0) {
            return;
        }
        Util.m13741d(TAG, "endTasks completed[" + collection.size() + "] sameTask[" + collection2.size() + "] fileBusy[" + collection3.size() + "]");
        if (collection.size() > 0) {
            Iterator<DownloadTask> it = collection.iterator();
            while (it.hasNext()) {
                DownloadTask next = it.next();
                if (!next.isAutoCallbackToUIThread()) {
                    next.getListener().taskEnd(next, EndCause.COMPLETED, null);
                    it.remove();
                }
            }
        }
        if (collection2.size() > 0) {
            Iterator<DownloadTask> it2 = collection2.iterator();
            while (it2.hasNext()) {
                DownloadTask next2 = it2.next();
                if (!next2.isAutoCallbackToUIThread()) {
                    next2.getListener().taskEnd(next2, EndCause.SAME_TASK_BUSY, null);
                    it2.remove();
                }
            }
        }
        if (collection3.size() > 0) {
            Iterator<DownloadTask> it3 = collection3.iterator();
            while (it3.hasNext()) {
                DownloadTask next3 = it3.next();
                if (!next3.isAutoCallbackToUIThread()) {
                    next3.getListener().taskEnd(next3, EndCause.FILE_BUSY, null);
                    it3.remove();
                }
            }
        }
        if (collection.size() == 0 && collection2.size() == 0 && collection3.size() == 0) {
            return;
        }
        this.uiHandler.post(new Runnable() { // from class: com.liulishuo.okdownload.core.dispatcher.CallbackDispatcher.2
            @Override // java.lang.Runnable
            public void run() {
                for (DownloadTask downloadTask : collection) {
                    downloadTask.getListener().taskEnd(downloadTask, EndCause.COMPLETED, null);
                }
                for (DownloadTask downloadTask2 : collection2) {
                    downloadTask2.getListener().taskEnd(downloadTask2, EndCause.SAME_TASK_BUSY, null);
                }
                for (DownloadTask downloadTask3 : collection3) {
                    downloadTask3.getListener().taskEnd(downloadTask3, EndCause.FILE_BUSY, null);
                }
            }
        });
    }

    public void endTasksWithCanceled(@NonNull final Collection<DownloadTask> collection) {
        if (collection.size() <= 0) {
            return;
        }
        Util.m13741d(TAG, "endTasksWithCanceled canceled[" + collection.size() + "]");
        Iterator<DownloadTask> it = collection.iterator();
        while (it.hasNext()) {
            DownloadTask next = it.next();
            if (!next.isAutoCallbackToUIThread()) {
                next.getListener().taskEnd(next, EndCause.CANCELED, null);
                it.remove();
            }
        }
        this.uiHandler.post(new Runnable() { // from class: com.liulishuo.okdownload.core.dispatcher.CallbackDispatcher.3
            @Override // java.lang.Runnable
            public void run() {
                for (DownloadTask downloadTask : collection) {
                    downloadTask.getListener().taskEnd(downloadTask, EndCause.CANCELED, null);
                }
            }
        });
    }

    public DownloadListener dispatch() {
        return this.transmit;
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class DefaultTransmitListener implements DownloadListener {
        @NonNull
        private final Handler uiHandler;

        DefaultTransmitListener(@NonNull Handler handler) {
            this.uiHandler = handler;
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void taskStart(@NonNull final DownloadTask downloadTask) {
            Util.m13741d(CallbackDispatcher.TAG, "taskStart: " + downloadTask.getId());
            inspectTaskStart(downloadTask);
            if (downloadTask.isAutoCallbackToUIThread()) {
                this.uiHandler.post(new Runnable() { // from class: com.liulishuo.okdownload.core.dispatcher.CallbackDispatcher.DefaultTransmitListener.1
                    @Override // java.lang.Runnable
                    public void run() {
                        downloadTask.getListener().taskStart(downloadTask);
                    }
                });
            } else {
                downloadTask.getListener().taskStart(downloadTask);
            }
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void connectTrialStart(@NonNull final DownloadTask downloadTask, @NonNull final Map<String, List<String>> map) {
            Util.m13741d(CallbackDispatcher.TAG, "-----> start trial task(" + downloadTask.getId() + ") " + map);
            if (downloadTask.isAutoCallbackToUIThread()) {
                this.uiHandler.post(new Runnable() { // from class: com.liulishuo.okdownload.core.dispatcher.CallbackDispatcher.DefaultTransmitListener.2
                    @Override // java.lang.Runnable
                    public void run() {
                        downloadTask.getListener().connectTrialStart(downloadTask, map);
                    }
                });
            } else {
                downloadTask.getListener().connectTrialStart(downloadTask, map);
            }
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void connectTrialEnd(@NonNull final DownloadTask downloadTask, final int i, @NonNull final Map<String, List<String>> map) {
            Util.m13741d(CallbackDispatcher.TAG, "<----- finish trial task(" + downloadTask.getId() + ") code[" + i + "]" + map);
            if (downloadTask.isAutoCallbackToUIThread()) {
                this.uiHandler.post(new Runnable() { // from class: com.liulishuo.okdownload.core.dispatcher.CallbackDispatcher.DefaultTransmitListener.3
                    @Override // java.lang.Runnable
                    public void run() {
                        downloadTask.getListener().connectTrialEnd(downloadTask, i, map);
                    }
                });
            } else {
                downloadTask.getListener().connectTrialEnd(downloadTask, i, map);
            }
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void downloadFromBeginning(@NonNull final DownloadTask downloadTask, @NonNull final BreakpointInfo breakpointInfo, @NonNull final ResumeFailedCause resumeFailedCause) {
            Util.m13741d(CallbackDispatcher.TAG, "downloadFromBeginning: " + downloadTask.getId());
            inspectDownloadFromBeginning(downloadTask, breakpointInfo, resumeFailedCause);
            if (downloadTask.isAutoCallbackToUIThread()) {
                this.uiHandler.post(new Runnable() { // from class: com.liulishuo.okdownload.core.dispatcher.CallbackDispatcher.DefaultTransmitListener.4
                    @Override // java.lang.Runnable
                    public void run() {
                        downloadTask.getListener().downloadFromBeginning(downloadTask, breakpointInfo, resumeFailedCause);
                    }
                });
            } else {
                downloadTask.getListener().downloadFromBeginning(downloadTask, breakpointInfo, resumeFailedCause);
            }
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void downloadFromBreakpoint(@NonNull final DownloadTask downloadTask, @NonNull final BreakpointInfo breakpointInfo) {
            Util.m13741d(CallbackDispatcher.TAG, "downloadFromBreakpoint: " + downloadTask.getId());
            inspectDownloadFromBreakpoint(downloadTask, breakpointInfo);
            if (downloadTask.isAutoCallbackToUIThread()) {
                this.uiHandler.post(new Runnable() { // from class: com.liulishuo.okdownload.core.dispatcher.CallbackDispatcher.DefaultTransmitListener.5
                    @Override // java.lang.Runnable
                    public void run() {
                        downloadTask.getListener().downloadFromBreakpoint(downloadTask, breakpointInfo);
                    }
                });
            } else {
                downloadTask.getListener().downloadFromBreakpoint(downloadTask, breakpointInfo);
            }
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void connectStart(@NonNull final DownloadTask downloadTask, final int i, @NonNull final Map<String, List<String>> map) {
            Util.m13741d(CallbackDispatcher.TAG, "-----> start connection task(" + downloadTask.getId() + ") block(" + i + ") " + map);
            if (downloadTask.isAutoCallbackToUIThread()) {
                this.uiHandler.post(new Runnable() { // from class: com.liulishuo.okdownload.core.dispatcher.CallbackDispatcher.DefaultTransmitListener.6
                    @Override // java.lang.Runnable
                    public void run() {
                        downloadTask.getListener().connectStart(downloadTask, i, map);
                    }
                });
            } else {
                downloadTask.getListener().connectStart(downloadTask, i, map);
            }
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void connectEnd(@NonNull final DownloadTask downloadTask, final int i, final int i2, @NonNull final Map<String, List<String>> map) {
            Util.m13741d(CallbackDispatcher.TAG, "<----- finish connection task(" + downloadTask.getId() + ") block(" + i + ") code[" + i2 + "]" + map);
            if (downloadTask.isAutoCallbackToUIThread()) {
                this.uiHandler.post(new Runnable() { // from class: com.liulishuo.okdownload.core.dispatcher.CallbackDispatcher.DefaultTransmitListener.7
                    @Override // java.lang.Runnable
                    public void run() {
                        downloadTask.getListener().connectEnd(downloadTask, i, i2, map);
                    }
                });
            } else {
                downloadTask.getListener().connectEnd(downloadTask, i, i2, map);
            }
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void fetchStart(@NonNull final DownloadTask downloadTask, final int i, final long j) {
            Util.m13741d(CallbackDispatcher.TAG, "fetchStart: " + downloadTask.getId());
            if (downloadTask.isAutoCallbackToUIThread()) {
                this.uiHandler.post(new Runnable() { // from class: com.liulishuo.okdownload.core.dispatcher.CallbackDispatcher.DefaultTransmitListener.8
                    @Override // java.lang.Runnable
                    public void run() {
                        downloadTask.getListener().fetchStart(downloadTask, i, j);
                    }
                });
            } else {
                downloadTask.getListener().fetchStart(downloadTask, i, j);
            }
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void fetchProgress(@NonNull final DownloadTask downloadTask, final int i, final long j) {
            if (downloadTask.getMinIntervalMillisCallbackProcess() > 0) {
                DownloadTask.TaskHideWrapper.setLastCallbackProcessTs(downloadTask, SystemClock.uptimeMillis());
            }
            if (downloadTask.isAutoCallbackToUIThread()) {
                this.uiHandler.post(new Runnable() { // from class: com.liulishuo.okdownload.core.dispatcher.CallbackDispatcher.DefaultTransmitListener.9
                    @Override // java.lang.Runnable
                    public void run() {
                        downloadTask.getListener().fetchProgress(downloadTask, i, j);
                    }
                });
            } else {
                downloadTask.getListener().fetchProgress(downloadTask, i, j);
            }
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void fetchEnd(@NonNull final DownloadTask downloadTask, final int i, final long j) {
            Util.m13741d(CallbackDispatcher.TAG, "fetchEnd: " + downloadTask.getId());
            if (downloadTask.isAutoCallbackToUIThread()) {
                this.uiHandler.post(new Runnable() { // from class: com.liulishuo.okdownload.core.dispatcher.CallbackDispatcher.DefaultTransmitListener.10
                    @Override // java.lang.Runnable
                    public void run() {
                        downloadTask.getListener().fetchEnd(downloadTask, i, j);
                    }
                });
            } else {
                downloadTask.getListener().fetchEnd(downloadTask, i, j);
            }
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void taskEnd(@NonNull final DownloadTask downloadTask, @NonNull final EndCause endCause, @Nullable final Exception exc) {
            if (endCause == EndCause.ERROR) {
                Util.m13741d(CallbackDispatcher.TAG, "taskEnd: " + downloadTask.getId() + " " + endCause + " " + exc);
            }
            inspectTaskEnd(downloadTask, endCause, exc);
            if (downloadTask.isAutoCallbackToUIThread()) {
                this.uiHandler.post(new Runnable() { // from class: com.liulishuo.okdownload.core.dispatcher.CallbackDispatcher.DefaultTransmitListener.11
                    @Override // java.lang.Runnable
                    public void run() {
                        downloadTask.getListener().taskEnd(downloadTask, endCause, exc);
                    }
                });
            } else {
                downloadTask.getListener().taskEnd(downloadTask, endCause, exc);
            }
        }

        void inspectDownloadFromBreakpoint(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo) {
            DownloadMonitor monitor = OkDownload.with().getMonitor();
            if (monitor != null) {
                monitor.taskDownloadFromBreakpoint(downloadTask, breakpointInfo);
            }
        }

        void inspectDownloadFromBeginning(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo, @NonNull ResumeFailedCause resumeFailedCause) {
            DownloadMonitor monitor = OkDownload.with().getMonitor();
            if (monitor != null) {
                monitor.taskDownloadFromBeginning(downloadTask, breakpointInfo, resumeFailedCause);
            }
        }

        void inspectTaskStart(DownloadTask downloadTask) {
            DownloadMonitor monitor = OkDownload.with().getMonitor();
            if (monitor != null) {
                monitor.taskStart(downloadTask);
            }
        }

        void inspectTaskEnd(DownloadTask downloadTask, EndCause endCause, @Nullable Exception exc) {
            DownloadMonitor monitor = OkDownload.with().getMonitor();
            if (monitor != null) {
                monitor.taskEnd(downloadTask, endCause, exc);
            }
        }
    }
}
