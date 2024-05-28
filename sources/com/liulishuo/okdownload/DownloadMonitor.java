package com.liulishuo.okdownload;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface DownloadMonitor {
    void taskDownloadFromBeginning(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo, @Nullable ResumeFailedCause resumeFailedCause);

    void taskDownloadFromBreakpoint(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo);

    void taskEnd(DownloadTask downloadTask, EndCause endCause, @Nullable Exception exc);

    void taskStart(DownloadTask downloadTask);
}
