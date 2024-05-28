package com.liulishuo.okdownload;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface DownloadListener {
    void connectEnd(@NonNull DownloadTask downloadTask, @IntRange(from = 0) int i, int i2, @NonNull Map<String, List<String>> map);

    void connectStart(@NonNull DownloadTask downloadTask, @IntRange(from = 0) int i, @NonNull Map<String, List<String>> map);

    void connectTrialEnd(@NonNull DownloadTask downloadTask, int i, @NonNull Map<String, List<String>> map);

    void connectTrialStart(@NonNull DownloadTask downloadTask, @NonNull Map<String, List<String>> map);

    void downloadFromBeginning(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo, @NonNull ResumeFailedCause resumeFailedCause);

    void downloadFromBreakpoint(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo);

    void fetchEnd(@NonNull DownloadTask downloadTask, @IntRange(from = 0) int i, @IntRange(from = 0) long j);

    void fetchProgress(@NonNull DownloadTask downloadTask, @IntRange(from = 0) int i, @IntRange(from = 0) long j);

    void fetchStart(@NonNull DownloadTask downloadTask, @IntRange(from = 0) int i, @IntRange(from = 0) long j);

    void taskEnd(@NonNull DownloadTask downloadTask, @NonNull EndCause endCause, @Nullable Exception exc);

    void taskStart(@NonNull DownloadTask downloadTask);
}
