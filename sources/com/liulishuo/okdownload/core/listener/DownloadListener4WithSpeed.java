package com.liulishuo.okdownload.core.listener;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.assist.Listener4Assist;
import com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend;
import com.liulishuo.okdownload.core.listener.assist.ListenerModelHandler;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class DownloadListener4WithSpeed extends DownloadListener4 implements Listener4SpeedAssistExtend.Listener4SpeedCallback {
    @Override // com.liulishuo.okdownload.core.listener.assist.Listener4Assist.Listener4Callback
    public final void blockEnd(DownloadTask downloadTask, int i, BlockInfo blockInfo) {
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener4Assist.Listener4Callback
    public final void infoReady(DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo, boolean z, @NonNull Listener4Assist.Listener4Model listener4Model) {
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener4Assist.Listener4Callback
    public final void progress(DownloadTask downloadTask, long j) {
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener4Assist.Listener4Callback
    public final void progressBlock(DownloadTask downloadTask, int i, long j) {
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener4Assist.Listener4Callback
    public final void taskEnd(DownloadTask downloadTask, EndCause endCause, @Nullable Exception exc, @NonNull Listener4Assist.Listener4Model listener4Model) {
    }

    private DownloadListener4WithSpeed(Listener4SpeedAssistExtend listener4SpeedAssistExtend) {
        super(new Listener4Assist(new Listener4WithSpeedModelCreator()));
        listener4SpeedAssistExtend.setCallback(this);
        setAssistExtend(listener4SpeedAssistExtend);
    }

    public DownloadListener4WithSpeed() {
        this(new Listener4SpeedAssistExtend());
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class Listener4WithSpeedModelCreator implements ListenerModelHandler.ModelCreator<Listener4SpeedAssistExtend.Listener4SpeedModel> {
        private Listener4WithSpeedModelCreator() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.liulishuo.okdownload.core.listener.assist.ListenerModelHandler.ModelCreator
        public Listener4SpeedAssistExtend.Listener4SpeedModel create(int i) {
            return new Listener4SpeedAssistExtend.Listener4SpeedModel(i);
        }
    }
}
