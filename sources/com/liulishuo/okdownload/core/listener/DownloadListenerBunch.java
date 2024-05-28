package com.liulishuo.okdownload.core.listener;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.liulishuo.okdownload.DownloadListener;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DownloadListenerBunch implements DownloadListener {
    @NonNull
    final DownloadListener[] listenerList;

    DownloadListenerBunch(@NonNull DownloadListener[] downloadListenerArr) {
        this.listenerList = downloadListenerArr;
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void taskStart(@NonNull DownloadTask downloadTask) {
        for (DownloadListener downloadListener : this.listenerList) {
            downloadListener.taskStart(downloadTask);
        }
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void connectTrialStart(@NonNull DownloadTask downloadTask, @NonNull Map<String, List<String>> map) {
        for (DownloadListener downloadListener : this.listenerList) {
            downloadListener.connectTrialStart(downloadTask, map);
        }
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void connectTrialEnd(@NonNull DownloadTask downloadTask, int i, @NonNull Map<String, List<String>> map) {
        for (DownloadListener downloadListener : this.listenerList) {
            downloadListener.connectTrialEnd(downloadTask, i, map);
        }
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void downloadFromBeginning(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo, @NonNull ResumeFailedCause resumeFailedCause) {
        for (DownloadListener downloadListener : this.listenerList) {
            downloadListener.downloadFromBeginning(downloadTask, breakpointInfo, resumeFailedCause);
        }
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void downloadFromBreakpoint(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo) {
        for (DownloadListener downloadListener : this.listenerList) {
            downloadListener.downloadFromBreakpoint(downloadTask, breakpointInfo);
        }
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void connectStart(@NonNull DownloadTask downloadTask, int i, @NonNull Map<String, List<String>> map) {
        for (DownloadListener downloadListener : this.listenerList) {
            downloadListener.connectStart(downloadTask, i, map);
        }
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void connectEnd(@NonNull DownloadTask downloadTask, int i, int i2, @NonNull Map<String, List<String>> map) {
        for (DownloadListener downloadListener : this.listenerList) {
            downloadListener.connectEnd(downloadTask, i, i2, map);
        }
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void fetchStart(@NonNull DownloadTask downloadTask, int i, long j) {
        for (DownloadListener downloadListener : this.listenerList) {
            downloadListener.fetchStart(downloadTask, i, j);
        }
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void fetchProgress(@NonNull DownloadTask downloadTask, int i, long j) {
        for (DownloadListener downloadListener : this.listenerList) {
            downloadListener.fetchProgress(downloadTask, i, j);
        }
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void fetchEnd(@NonNull DownloadTask downloadTask, int i, long j) {
        for (DownloadListener downloadListener : this.listenerList) {
            downloadListener.fetchEnd(downloadTask, i, j);
        }
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void taskEnd(@NonNull DownloadTask downloadTask, @NonNull EndCause endCause, @Nullable Exception exc) {
        for (DownloadListener downloadListener : this.listenerList) {
            downloadListener.taskEnd(downloadTask, endCause, exc);
        }
    }

    public boolean contain(DownloadListener downloadListener) {
        for (DownloadListener downloadListener2 : this.listenerList) {
            if (downloadListener2 == downloadListener) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(DownloadListener downloadListener) {
        int i = 0;
        while (true) {
            DownloadListener[] downloadListenerArr = this.listenerList;
            if (i >= downloadListenerArr.length) {
                return -1;
            }
            if (downloadListenerArr[i] == downloadListener) {
                return i;
            }
            i++;
        }
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class Builder {
        private List<DownloadListener> listenerList = new ArrayList();

        public DownloadListenerBunch build() {
            List<DownloadListener> list = this.listenerList;
            return new DownloadListenerBunch((DownloadListener[]) list.toArray(new DownloadListener[list.size()]));
        }

        public Builder append(@Nullable DownloadListener downloadListener) {
            if (downloadListener != null && !this.listenerList.contains(downloadListener)) {
                this.listenerList.add(downloadListener);
            }
            return this;
        }

        public boolean remove(DownloadListener downloadListener) {
            return this.listenerList.remove(downloadListener);
        }
    }
}
