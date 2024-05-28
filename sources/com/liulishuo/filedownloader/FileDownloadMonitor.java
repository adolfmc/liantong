package com.liulishuo.filedownloader;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import com.liulishuo.okdownload.DownloadMonitor;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FileDownloadMonitor {
    private static DownloadMonitorAdapter monitor;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface IMonitor {
        void onRequestStart(int i, boolean z, FileDownloadListener fileDownloadListener);

        void onRequestStart(BaseDownloadTask baseDownloadTask);

        void onTaskBegin(BaseDownloadTask baseDownloadTask);

        void onTaskOver(BaseDownloadTask baseDownloadTask);

        void onTaskStarted(BaseDownloadTask baseDownloadTask);
    }

    public static void setGlobalMonitor(@NonNull IMonitor iMonitor) {
        monitor = new DownloadMonitorAdapter(iMonitor);
    }

    public static void releaseGlobalMonitor() {
        monitor = null;
    }

    public static IMonitor getMonitor() {
        return monitor.callbackMonitor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DownloadMonitor getDownloadMonitor() {
        return monitor;
    }

    public static boolean isValid() {
        return (getDownloadMonitor() == null || getMonitor() == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class DownloadMonitorAdapter implements IMonitor, DownloadMonitor {
        @NonNull
        final IMonitor callbackMonitor;

        DownloadMonitorAdapter(IMonitor iMonitor) {
            this.callbackMonitor = iMonitor;
        }

        @Override // com.liulishuo.okdownload.DownloadMonitor
        public void taskStart(DownloadTask downloadTask) {
            DownloadTaskAdapter findDownloadTaskAdapter = FileDownloadUtils.findDownloadTaskAdapter(downloadTask);
            if (findDownloadTaskAdapter != null) {
                onTaskBegin(findDownloadTaskAdapter);
            }
        }

        @Override // com.liulishuo.okdownload.DownloadMonitor
        public void taskDownloadFromBreakpoint(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo) {
            taskDownloadFromBeginning(downloadTask, breakpointInfo, null);
        }

        @Override // com.liulishuo.okdownload.DownloadMonitor
        public void taskDownloadFromBeginning(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo, @Nullable ResumeFailedCause resumeFailedCause) {
            DownloadTaskAdapter findDownloadTaskAdapter = FileDownloadUtils.findDownloadTaskAdapter(downloadTask);
            if (findDownloadTaskAdapter != null) {
                onRequestStart(findDownloadTaskAdapter);
                onTaskStarted(findDownloadTaskAdapter);
            }
        }

        @Override // com.liulishuo.okdownload.DownloadMonitor
        public void taskEnd(DownloadTask downloadTask, EndCause endCause, @Nullable Exception exc) {
            DownloadTaskAdapter findDownloadTaskAdapter = FileDownloadUtils.findDownloadTaskAdapter(downloadTask);
            if (findDownloadTaskAdapter != null) {
                onTaskOver(findDownloadTaskAdapter);
            }
        }

        @Override // com.liulishuo.filedownloader.FileDownloadMonitor.IMonitor
        public void onRequestStart(int i, boolean z, FileDownloadListener fileDownloadListener) {
            this.callbackMonitor.onRequestStart(i, z, fileDownloadListener);
        }

        @Override // com.liulishuo.filedownloader.FileDownloadMonitor.IMonitor
        public void onRequestStart(BaseDownloadTask baseDownloadTask) {
            this.callbackMonitor.onRequestStart(baseDownloadTask);
        }

        @Override // com.liulishuo.filedownloader.FileDownloadMonitor.IMonitor
        public void onTaskBegin(BaseDownloadTask baseDownloadTask) {
            this.callbackMonitor.onTaskBegin(baseDownloadTask);
        }

        @Override // com.liulishuo.filedownloader.FileDownloadMonitor.IMonitor
        public void onTaskStarted(BaseDownloadTask baseDownloadTask) {
            this.callbackMonitor.onTaskStarted(baseDownloadTask);
        }

        @Override // com.liulishuo.filedownloader.FileDownloadMonitor.IMonitor
        public void onTaskOver(BaseDownloadTask baseDownloadTask) {
            this.callbackMonitor.onTaskOver(baseDownloadTask);
        }
    }
}
