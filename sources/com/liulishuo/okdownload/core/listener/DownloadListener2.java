package com.liulishuo.okdownload.core.listener;

import android.support.annotation.NonNull;
import com.liulishuo.okdownload.DownloadListener;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class DownloadListener2 implements DownloadListener {
    @Override // com.liulishuo.okdownload.DownloadListener
    public void connectEnd(@NonNull DownloadTask downloadTask, int i, int i2, @NonNull Map<String, List<String>> map) {
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void connectStart(@NonNull DownloadTask downloadTask, int i, @NonNull Map<String, List<String>> map) {
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void connectTrialEnd(@NonNull DownloadTask downloadTask, int i, @NonNull Map<String, List<String>> map) {
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void connectTrialStart(@NonNull DownloadTask downloadTask, @NonNull Map<String, List<String>> map) {
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void downloadFromBeginning(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo, @NonNull ResumeFailedCause resumeFailedCause) {
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void downloadFromBreakpoint(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo) {
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void fetchEnd(@NonNull DownloadTask downloadTask, int i, long j) {
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void fetchProgress(@NonNull DownloadTask downloadTask, int i, long j) {
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void fetchStart(@NonNull DownloadTask downloadTask, int i, long j) {
    }
}
