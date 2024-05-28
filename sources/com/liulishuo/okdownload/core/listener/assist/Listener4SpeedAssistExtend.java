package com.liulishuo.okdownload.core.listener.assist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.SpeedCalculator;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.assist.Listener4Assist;
import com.liulishuo.okdownload.core.listener.assist.ListenerModelHandler;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings({"BC"})
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class Listener4SpeedAssistExtend implements Listener4Assist.AssistExtend, ListenerModelHandler.ModelCreator<Listener4SpeedModel> {
    private Listener4SpeedCallback callback;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface Listener4SpeedCallback {
        void blockEnd(@NonNull DownloadTask downloadTask, int i, BlockInfo blockInfo, @NonNull SpeedCalculator speedCalculator);

        void infoReady(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo, boolean z, @NonNull Listener4SpeedModel listener4SpeedModel);

        void progress(@NonNull DownloadTask downloadTask, long j, @NonNull SpeedCalculator speedCalculator);

        void progressBlock(@NonNull DownloadTask downloadTask, int i, long j, @NonNull SpeedCalculator speedCalculator);

        void taskEnd(@NonNull DownloadTask downloadTask, @NonNull EndCause endCause, @Nullable Exception exc, @NonNull SpeedCalculator speedCalculator);
    }

    public void setCallback(Listener4SpeedCallback listener4SpeedCallback) {
        this.callback = listener4SpeedCallback;
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener4Assist.AssistExtend
    public boolean dispatchInfoReady(DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo, boolean z, @NonNull Listener4Assist.Listener4Model listener4Model) {
        Listener4SpeedCallback listener4SpeedCallback = this.callback;
        if (listener4SpeedCallback != null) {
            listener4SpeedCallback.infoReady(downloadTask, breakpointInfo, z, (Listener4SpeedModel) listener4Model);
            return true;
        }
        return true;
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener4Assist.AssistExtend
    public boolean dispatchFetchProgress(@NonNull DownloadTask downloadTask, int i, long j, @NonNull Listener4Assist.Listener4Model listener4Model) {
        Listener4SpeedModel listener4SpeedModel = (Listener4SpeedModel) listener4Model;
        listener4SpeedModel.blockSpeeds.get(i).downloading(j);
        listener4SpeedModel.taskSpeed.downloading(j);
        Listener4SpeedCallback listener4SpeedCallback = this.callback;
        if (listener4SpeedCallback != null) {
            listener4SpeedCallback.progressBlock(downloadTask, i, listener4Model.blockCurrentOffsetMap.get(i).longValue(), listener4SpeedModel.getBlockSpeed(i));
            this.callback.progress(downloadTask, listener4Model.currentOffset, listener4SpeedModel.taskSpeed);
            return true;
        }
        return true;
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener4Assist.AssistExtend
    public boolean dispatchBlockEnd(DownloadTask downloadTask, int i, Listener4Assist.Listener4Model listener4Model) {
        Listener4SpeedModel listener4SpeedModel = (Listener4SpeedModel) listener4Model;
        listener4SpeedModel.blockSpeeds.get(i).endTask();
        Listener4SpeedCallback listener4SpeedCallback = this.callback;
        if (listener4SpeedCallback != null) {
            listener4SpeedCallback.blockEnd(downloadTask, i, listener4Model.info.getBlock(i), listener4SpeedModel.getBlockSpeed(i));
            return true;
        }
        return true;
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener4Assist.AssistExtend
    public boolean dispatchTaskEnd(DownloadTask downloadTask, EndCause endCause, @Nullable Exception exc, @NonNull Listener4Assist.Listener4Model listener4Model) {
        SpeedCalculator speedCalculator;
        Listener4SpeedModel listener4SpeedModel = (Listener4SpeedModel) listener4Model;
        if (listener4SpeedModel.taskSpeed != null) {
            speedCalculator = listener4SpeedModel.taskSpeed;
            speedCalculator.endTask();
        } else {
            speedCalculator = new SpeedCalculator();
        }
        Listener4SpeedCallback listener4SpeedCallback = this.callback;
        if (listener4SpeedCallback != null) {
            listener4SpeedCallback.taskEnd(downloadTask, endCause, exc, speedCalculator);
            return true;
        }
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.liulishuo.okdownload.core.listener.assist.ListenerModelHandler.ModelCreator
    public Listener4SpeedModel create(int i) {
        return new Listener4SpeedModel(i);
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class Listener4SpeedModel extends Listener4Assist.Listener4Model {
        SparseArray<SpeedCalculator> blockSpeeds;
        SpeedCalculator taskSpeed;

        public SpeedCalculator getTaskSpeed() {
            return this.taskSpeed;
        }

        public SpeedCalculator getBlockSpeed(int i) {
            return this.blockSpeeds.get(i);
        }

        public Listener4SpeedModel(int i) {
            super(i);
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4Assist.Listener4Model, com.liulishuo.okdownload.core.listener.assist.ListenerModelHandler.ListenerModel
        public void onInfoValid(@NonNull BreakpointInfo breakpointInfo) {
            super.onInfoValid(breakpointInfo);
            this.taskSpeed = new SpeedCalculator();
            this.blockSpeeds = new SparseArray<>();
            int blockCount = breakpointInfo.getBlockCount();
            for (int i = 0; i < blockCount; i++) {
                this.blockSpeeds.put(i, new SpeedCalculator());
            }
        }
    }
}
