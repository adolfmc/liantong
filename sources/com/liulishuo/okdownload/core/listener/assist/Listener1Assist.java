package com.liulishuo.okdownload.core.listener.assist;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.assist.ListenerModelHandler;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class Listener1Assist implements ListenerAssist, ListenerModelHandler.ModelCreator<Listener1Model> {
    private Listener1Callback callback;
    private final ListenerModelHandler<Listener1Model> modelHandler;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface Listener1Callback {
        void connected(@NonNull DownloadTask downloadTask, @IntRange(from = 0) int i, @IntRange(from = 0) long j, @IntRange(from = 0) long j2);

        void progress(@NonNull DownloadTask downloadTask, @IntRange(from = 0) long j, @IntRange(from = 0) long j2);

        void retry(@NonNull DownloadTask downloadTask, @NonNull ResumeFailedCause resumeFailedCause);

        void taskEnd(@NonNull DownloadTask downloadTask, @NonNull EndCause endCause, @Nullable Exception exc, @NonNull Listener1Model listener1Model);

        void taskStart(@NonNull DownloadTask downloadTask, @NonNull Listener1Model listener1Model);
    }

    public Listener1Assist() {
        this.modelHandler = new ListenerModelHandler<>(this);
    }

    Listener1Assist(ListenerModelHandler<Listener1Model> listenerModelHandler) {
        this.modelHandler = listenerModelHandler;
    }

    public void setCallback(@NonNull Listener1Callback listener1Callback) {
        this.callback = listener1Callback;
    }

    public void taskStart(DownloadTask downloadTask) {
        Listener1Model addAndGetModel = this.modelHandler.addAndGetModel(downloadTask, null);
        Listener1Callback listener1Callback = this.callback;
        if (listener1Callback != null) {
            listener1Callback.taskStart(downloadTask, addAndGetModel);
        }
    }

    public void taskEnd(DownloadTask downloadTask, EndCause endCause, @Nullable Exception exc) {
        Listener1Model removeOrCreate = this.modelHandler.removeOrCreate(downloadTask, downloadTask.getInfo());
        Listener1Callback listener1Callback = this.callback;
        if (listener1Callback != null) {
            listener1Callback.taskEnd(downloadTask, endCause, exc, removeOrCreate);
        }
    }

    public void downloadFromBeginning(DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo, ResumeFailedCause resumeFailedCause) {
        Listener1Callback listener1Callback;
        Listener1Model orRecoverModel = this.modelHandler.getOrRecoverModel(downloadTask, breakpointInfo);
        if (orRecoverModel == null) {
            return;
        }
        orRecoverModel.onInfoValid(breakpointInfo);
        if (orRecoverModel.isStarted.booleanValue() && (listener1Callback = this.callback) != null) {
            listener1Callback.retry(downloadTask, resumeFailedCause);
        }
        orRecoverModel.isStarted = true;
        orRecoverModel.isFromResumed = false;
        orRecoverModel.isFirstConnect = true;
    }

    public void downloadFromBreakpoint(DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo) {
        Listener1Model orRecoverModel = this.modelHandler.getOrRecoverModel(downloadTask, breakpointInfo);
        if (orRecoverModel == null) {
            return;
        }
        orRecoverModel.onInfoValid(breakpointInfo);
        orRecoverModel.isStarted = true;
        orRecoverModel.isFromResumed = true;
        orRecoverModel.isFirstConnect = true;
    }

    public void connectEnd(DownloadTask downloadTask) {
        Listener1Model orRecoverModel = this.modelHandler.getOrRecoverModel(downloadTask, downloadTask.getInfo());
        if (orRecoverModel == null) {
            return;
        }
        if (orRecoverModel.isFromResumed.booleanValue() && orRecoverModel.isFirstConnect.booleanValue()) {
            orRecoverModel.isFirstConnect = false;
        }
        Listener1Callback listener1Callback = this.callback;
        if (listener1Callback != null) {
            listener1Callback.connected(downloadTask, orRecoverModel.blockCount, orRecoverModel.currentOffset.get(), orRecoverModel.totalLength);
        }
    }

    public void fetchProgress(DownloadTask downloadTask, long j) {
        Listener1Model orRecoverModel = this.modelHandler.getOrRecoverModel(downloadTask, downloadTask.getInfo());
        if (orRecoverModel == null) {
            return;
        }
        orRecoverModel.currentOffset.addAndGet(j);
        Listener1Callback listener1Callback = this.callback;
        if (listener1Callback != null) {
            listener1Callback.progress(downloadTask, orRecoverModel.currentOffset.get(), orRecoverModel.totalLength);
        }
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.ListenerAssist
    public boolean isAlwaysRecoverAssistModel() {
        return this.modelHandler.isAlwaysRecoverAssistModel();
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.ListenerAssist
    public void setAlwaysRecoverAssistModel(boolean z) {
        this.modelHandler.setAlwaysRecoverAssistModel(z);
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.ListenerAssist
    public void setAlwaysRecoverAssistModelIfNotSet(boolean z) {
        this.modelHandler.setAlwaysRecoverAssistModelIfNotSet(z);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.liulishuo.okdownload.core.listener.assist.ListenerModelHandler.ModelCreator
    public Listener1Model create(int i) {
        return new Listener1Model(i);
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class Listener1Model implements ListenerModelHandler.ListenerModel {
        int blockCount;
        final AtomicLong currentOffset = new AtomicLong();

        /* renamed from: id */
        final int f12217id;
        volatile Boolean isFirstConnect;
        Boolean isFromResumed;
        Boolean isStarted;
        long totalLength;

        Listener1Model(int i) {
            this.f12217id = i;
        }

        public long getTotalLength() {
            return this.totalLength;
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.ListenerModelHandler.ListenerModel
        public int getId() {
            return this.f12217id;
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.ListenerModelHandler.ListenerModel
        public void onInfoValid(@NonNull BreakpointInfo breakpointInfo) {
            this.blockCount = breakpointInfo.getBlockCount();
            this.totalLength = breakpointInfo.getTotalLength();
            this.currentOffset.set(breakpointInfo.getTotalOffset());
            if (this.isStarted == null) {
                this.isStarted = false;
            }
            if (this.isFromResumed == null) {
                this.isFromResumed = Boolean.valueOf(this.currentOffset.get() > 0);
            }
            if (this.isFirstConnect == null) {
                this.isFirstConnect = true;
            }
        }
    }
}
