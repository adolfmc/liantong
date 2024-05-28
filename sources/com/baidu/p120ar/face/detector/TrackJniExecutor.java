package com.baidu.p120ar.face.detector;

import android.os.SystemClock;
import android.util.Log;
import com.baidu.p120ar.armdl.ARMdlManager;
import com.baidu.p120ar.face.algo.FaceJniClient;
import com.baidu.p120ar.statistic.StatisticApi;
import com.baidu.p120ar.utils.ARLog;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.detector.TrackJniExecutor */
/* loaded from: E:\10201592_dexfile_execute.dex */
class TrackJniExecutor extends AbstractJniExecutor {
    private static final String TAG = "TrackJniExecutor";
    private long time;
    private long trackHandle;

    public TrackJniExecutor(ByteBuffer byteBuffer) {
        super(byteBuffer);
    }

    @Override // com.baidu.p120ar.face.detector.AbstractJniExecutor
    void invokeJni() {
        ARMdlManager.getInstance().setMdlState(13, true);
        this.time = System.currentTimeMillis();
        this.faceAlgoData = FaceJniClient.trackFace(this.trackHandle, this.faceAlgoData, this.faceHandle);
        String str = TAG;
        ARLog.m20417i(str, "track-time= " + (System.currentTimeMillis() - this.time));
        ARLog.m20421d(TAG, "detect_frame track task executing");
        Log.d("vvv", "face track: " + this.faceHandle + "   ,  " + this.trackHandle);
        ARMdlManager.getInstance().setMdlState(13, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.p120ar.face.detector.AbstractJniExecutor
    public void recordExeTime() {
        super.recordExeTime();
        StatisticApi.getPerformanceApi().recordAlgoTimeCost("face", "track", SystemClock.elapsedRealtime() - this.agloBeginTimestamp, (this.faceAlgoData == null || this.faceAlgoData.getFaceFrame() == null || this.faceAlgoData.getFaceFrame().getFaceBoxes() == null) ? 0 : this.faceAlgoData.getFaceFrame().getFaceBoxes().size());
        if (this.facePerfStaticUtil != null) {
            this.facePerfStaticUtil.calculateTrackCostTime(this.costTime);
        }
    }

    @Override // com.baidu.p120ar.face.detector.AbstractJniExecutor
    public void configure() {
        super.configure();
        this.trackHandle = this.faceAlgoLoader.getmTrackHandle();
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public void setFrontCamera(boolean z) {
        this.frontCamera = z;
    }

    public void setDataHandle(long j) {
        this.dataHandle = j;
    }
}
