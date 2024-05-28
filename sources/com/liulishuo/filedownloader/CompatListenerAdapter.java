package com.liulishuo.filedownloader;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.liulishuo.okdownload.DownloadListener;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import java.util.List;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CompatListenerAdapter implements DownloadListener {
    @NonNull
    private final CompatListenerAssist listenerAssist;

    @Override // com.liulishuo.okdownload.DownloadListener
    public void connectEnd(@NonNull DownloadTask downloadTask, int i, int i2, @NonNull Map<String, List<String>> map) {
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void connectTrialEnd(@NonNull DownloadTask downloadTask, int i, @NonNull Map<String, List<String>> map) {
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void connectTrialStart(@NonNull DownloadTask downloadTask, @NonNull Map<String, List<String>> map) {
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void fetchEnd(@NonNull DownloadTask downloadTask, int i, long j) {
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void fetchStart(@NonNull DownloadTask downloadTask, int i, long j) {
    }

    public CompatListenerAdapter(@NonNull CompatListenerAssist compatListenerAssist) {
        this.listenerAssist = compatListenerAssist;
    }

    @NonNull
    public CompatListenerAssist getListenerAssist() {
        return this.listenerAssist;
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void taskStart(@NonNull DownloadTask downloadTask) {
        this.listenerAssist.taskStart(downloadTask);
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void downloadFromBeginning(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo, @NonNull ResumeFailedCause resumeFailedCause) {
        this.listenerAssist.setResumable(false);
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void downloadFromBreakpoint(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo) {
        this.listenerAssist.setResumable(true);
        this.listenerAssist.setEtag(breakpointInfo.getEtag());
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void connectStart(@NonNull DownloadTask downloadTask, int i, @NonNull Map<String, List<String>> map) {
        this.listenerAssist.connectStart(downloadTask);
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void fetchProgress(@NonNull DownloadTask downloadTask, int i, long j) {
        this.listenerAssist.fetchProgress(downloadTask, j);
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void taskEnd(@NonNull DownloadTask downloadTask, @NonNull EndCause endCause, @Nullable Exception exc) {
        this.listenerAssist.taskEnd(downloadTask, endCause, exc);
    }

    public static CompatListenerAdapter create(@NonNull FileDownloadListener fileDownloadListener) {
        return new CompatListenerAdapter(new CompatListenerAssist(new CompatListenerAdaptee(fileDownloadListener)));
    }
}
