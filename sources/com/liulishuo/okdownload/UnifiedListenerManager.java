package com.liulishuo.okdownload;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.assist.ListenerAssist;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UnifiedListenerManager {
    final List<Integer> autoRemoveListenerIdList = new ArrayList();
    final DownloadListener hostListener = new DownloadListener() { // from class: com.liulishuo.okdownload.UnifiedListenerManager.1
        @Override // com.liulishuo.okdownload.DownloadListener
        public void taskStart(@NonNull DownloadTask downloadTask) {
            DownloadListener[] threadSafeArray = UnifiedListenerManager.getThreadSafeArray(downloadTask, UnifiedListenerManager.this.realListenerMap);
            if (threadSafeArray == null) {
                return;
            }
            for (DownloadListener downloadListener : threadSafeArray) {
                if (downloadListener != null) {
                    downloadListener.taskStart(downloadTask);
                }
            }
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void connectTrialStart(@NonNull DownloadTask downloadTask, @NonNull Map<String, List<String>> map) {
            DownloadListener[] threadSafeArray = UnifiedListenerManager.getThreadSafeArray(downloadTask, UnifiedListenerManager.this.realListenerMap);
            if (threadSafeArray == null) {
                return;
            }
            for (DownloadListener downloadListener : threadSafeArray) {
                if (downloadListener != null) {
                    downloadListener.connectTrialStart(downloadTask, map);
                }
            }
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void connectTrialEnd(@NonNull DownloadTask downloadTask, int i, @NonNull Map<String, List<String>> map) {
            DownloadListener[] threadSafeArray = UnifiedListenerManager.getThreadSafeArray(downloadTask, UnifiedListenerManager.this.realListenerMap);
            if (threadSafeArray == null) {
                return;
            }
            for (DownloadListener downloadListener : threadSafeArray) {
                if (downloadListener != null) {
                    downloadListener.connectTrialEnd(downloadTask, i, map);
                }
            }
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void downloadFromBeginning(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo, @NonNull ResumeFailedCause resumeFailedCause) {
            DownloadListener[] threadSafeArray = UnifiedListenerManager.getThreadSafeArray(downloadTask, UnifiedListenerManager.this.realListenerMap);
            if (threadSafeArray == null) {
                return;
            }
            for (DownloadListener downloadListener : threadSafeArray) {
                if (downloadListener != null) {
                    downloadListener.downloadFromBeginning(downloadTask, breakpointInfo, resumeFailedCause);
                }
            }
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void downloadFromBreakpoint(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo) {
            DownloadListener[] threadSafeArray = UnifiedListenerManager.getThreadSafeArray(downloadTask, UnifiedListenerManager.this.realListenerMap);
            if (threadSafeArray == null) {
                return;
            }
            for (DownloadListener downloadListener : threadSafeArray) {
                if (downloadListener != null) {
                    downloadListener.downloadFromBreakpoint(downloadTask, breakpointInfo);
                }
            }
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void connectStart(@NonNull DownloadTask downloadTask, int i, @NonNull Map<String, List<String>> map) {
            DownloadListener[] threadSafeArray = UnifiedListenerManager.getThreadSafeArray(downloadTask, UnifiedListenerManager.this.realListenerMap);
            if (threadSafeArray == null) {
                return;
            }
            for (DownloadListener downloadListener : threadSafeArray) {
                if (downloadListener != null) {
                    downloadListener.connectStart(downloadTask, i, map);
                }
            }
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void connectEnd(@NonNull DownloadTask downloadTask, int i, int i2, @NonNull Map<String, List<String>> map) {
            DownloadListener[] threadSafeArray = UnifiedListenerManager.getThreadSafeArray(downloadTask, UnifiedListenerManager.this.realListenerMap);
            if (threadSafeArray == null) {
                return;
            }
            for (DownloadListener downloadListener : threadSafeArray) {
                if (downloadListener != null) {
                    downloadListener.connectEnd(downloadTask, i, i2, map);
                }
            }
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void fetchStart(@NonNull DownloadTask downloadTask, int i, long j) {
            DownloadListener[] threadSafeArray = UnifiedListenerManager.getThreadSafeArray(downloadTask, UnifiedListenerManager.this.realListenerMap);
            if (threadSafeArray == null) {
                return;
            }
            for (DownloadListener downloadListener : threadSafeArray) {
                if (downloadListener != null) {
                    downloadListener.fetchStart(downloadTask, i, j);
                }
            }
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void fetchProgress(@NonNull DownloadTask downloadTask, int i, long j) {
            DownloadListener[] threadSafeArray = UnifiedListenerManager.getThreadSafeArray(downloadTask, UnifiedListenerManager.this.realListenerMap);
            if (threadSafeArray == null) {
                return;
            }
            for (DownloadListener downloadListener : threadSafeArray) {
                if (downloadListener != null) {
                    downloadListener.fetchProgress(downloadTask, i, j);
                }
            }
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void fetchEnd(@NonNull DownloadTask downloadTask, int i, long j) {
            DownloadListener[] threadSafeArray = UnifiedListenerManager.getThreadSafeArray(downloadTask, UnifiedListenerManager.this.realListenerMap);
            if (threadSafeArray == null) {
                return;
            }
            for (DownloadListener downloadListener : threadSafeArray) {
                if (downloadListener != null) {
                    downloadListener.fetchEnd(downloadTask, i, j);
                }
            }
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void taskEnd(@NonNull DownloadTask downloadTask, @NonNull EndCause endCause, @Nullable Exception exc) {
            DownloadListener[] threadSafeArray = UnifiedListenerManager.getThreadSafeArray(downloadTask, UnifiedListenerManager.this.realListenerMap);
            if (threadSafeArray == null) {
                return;
            }
            for (DownloadListener downloadListener : threadSafeArray) {
                if (downloadListener != null) {
                    downloadListener.taskEnd(downloadTask, endCause, exc);
                }
            }
            if (UnifiedListenerManager.this.autoRemoveListenerIdList.contains(Integer.valueOf(downloadTask.getId()))) {
                UnifiedListenerManager.this.detachListener(downloadTask.getId());
            }
        }
    };
    final SparseArray<ArrayList<DownloadListener>> realListenerMap = new SparseArray<>();

    public synchronized void detachListener(int i) {
        this.realListenerMap.remove(i);
    }

    public synchronized void addAutoRemoveListenersWhenTaskEnd(int i) {
        if (this.autoRemoveListenerIdList.contains(Integer.valueOf(i))) {
            return;
        }
        this.autoRemoveListenerIdList.add(Integer.valueOf(i));
    }

    public synchronized void removeAutoRemoveListenersWhenTaskEnd(int i) {
        this.autoRemoveListenerIdList.remove(Integer.valueOf(i));
    }

    public synchronized void detachListener(DownloadListener downloadListener) {
        int size = this.realListenerMap.size();
        ArrayList<Integer> arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            ArrayList<DownloadListener> valueAt = this.realListenerMap.valueAt(i);
            if (valueAt != null) {
                valueAt.remove(downloadListener);
                if (valueAt.isEmpty()) {
                    arrayList.add(Integer.valueOf(this.realListenerMap.keyAt(i)));
                }
            }
        }
        for (Integer num : arrayList) {
            this.realListenerMap.remove(num.intValue());
        }
    }

    public synchronized boolean detachListener(@NonNull DownloadTask downloadTask, DownloadListener downloadListener) {
        int id = downloadTask.getId();
        ArrayList<DownloadListener> arrayList = this.realListenerMap.get(id);
        if (arrayList == null) {
            return false;
        }
        boolean remove = arrayList.remove(downloadListener);
        if (arrayList.isEmpty()) {
            this.realListenerMap.remove(id);
        }
        return remove;
    }

    public synchronized void attachListener(@NonNull DownloadTask downloadTask, @NonNull DownloadListener downloadListener) {
        int id = downloadTask.getId();
        ArrayList<DownloadListener> arrayList = this.realListenerMap.get(id);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.realListenerMap.put(id, arrayList);
        }
        if (!arrayList.contains(downloadListener)) {
            arrayList.add(downloadListener);
            if (downloadListener instanceof ListenerAssist) {
                ((ListenerAssist) downloadListener).setAlwaysRecoverAssistModelIfNotSet(true);
            }
        }
    }

    public synchronized void attachAndEnqueueIfNotRun(@NonNull DownloadTask downloadTask, @NonNull DownloadListener downloadListener) {
        attachListener(downloadTask, downloadListener);
        if (!isTaskPendingOrRunning(downloadTask)) {
            downloadTask.enqueue(this.hostListener);
        }
    }

    public synchronized void enqueueTaskWithUnifiedListener(@NonNull DownloadTask downloadTask, @NonNull DownloadListener downloadListener) {
        attachListener(downloadTask, downloadListener);
        downloadTask.enqueue(this.hostListener);
    }

    public synchronized void executeTaskWithUnifiedListener(@NonNull DownloadTask downloadTask, @NonNull DownloadListener downloadListener) {
        attachListener(downloadTask, downloadListener);
        downloadTask.execute(this.hostListener);
    }

    @NonNull
    public DownloadListener getHostListener() {
        return this.hostListener;
    }

    boolean isTaskPendingOrRunning(@NonNull DownloadTask downloadTask) {
        return StatusUtil.isSameTaskPendingOrRunning(downloadTask);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static DownloadListener[] getThreadSafeArray(DownloadTask downloadTask, SparseArray<ArrayList<DownloadListener>> sparseArray) {
        ArrayList<DownloadListener> arrayList = sparseArray.get(downloadTask.getId());
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        DownloadListener[] downloadListenerArr = new DownloadListener[arrayList.size()];
        arrayList.toArray(downloadListenerArr);
        return downloadListenerArr;
    }
}
