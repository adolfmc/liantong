package com.baidu.p120ar.face.detector;

import android.os.SystemClock;
import com.baidu.p120ar.armdl.ARMdlManager;
import com.baidu.p120ar.face.algo.FaceJniClient;
import com.baidu.p120ar.statistic.StatisticApi;
import com.baidu.p120ar.utils.ARLog;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.detector.DetectJniExecutor */
/* loaded from: E:\10201592_dexfile_execute.dex */
class DetectJniExecutor extends AbstractJniExecutor {
    private static final String TAG = "DetectJniExecutor";
    protected long detectHandle;
    private long time;

    public DetectJniExecutor(ByteBuffer byteBuffer) {
        super(byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.p120ar.face.detector.AbstractJniExecutor
    public void defineRecordVar() {
        super.defineRecordVar();
    }

    @Override // com.baidu.p120ar.face.detector.AbstractJniExecutor
    void invokeJni() {
        ARMdlManager.getInstance().setMdlState(12, true);
        this.time = System.currentTimeMillis();
        this.faceAlgoData = FaceJniClient.detectFace(this.detectHandle, this.faceAlgoData, this.faceHandle);
        String str = TAG;
        ARLog.m20417i(str, "detect-time= " + (System.currentTimeMillis() - this.time));
        String str2 = TAG;
        ARLog.m20413w(str2, "detect_frame detect task executing faceboxes：" + this.faceAlgoData.getFaceFrame().getFaceBoxes());
        ARMdlManager.getInstance().setMdlState(12, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.p120ar.face.detector.AbstractJniExecutor
    public void recordExeTime() {
        super.recordExeTime();
        StatisticApi.getPerformanceApi().recordAlgoTimeCost("face", "detect", SystemClock.elapsedRealtime() - this.agloBeginTimestamp, (this.faceAlgoData == null || this.faceAlgoData.getFaceFrame() == null || this.faceAlgoData.getFaceFrame().getFaceBoxes() == null) ? 0 : this.faceAlgoData.getFaceFrame().getFaceBoxes().size());
        if (this.facePerfStaticUtil != null) {
            this.facePerfStaticUtil.calculateDetectCostTime(this.costTime);
        }
    }

    @Override // com.baidu.p120ar.face.detector.AbstractJniExecutor
    public void configure() {
        super.configure();
        this.detectHandle = this.faceAlgoLoader.getmDetectHandle();
    }

    public void setDataHandle(long j) {
        this.dataHandle = j;
    }

    public void setFrontCamera(boolean z) {
        this.frontCamera = z;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }
}
