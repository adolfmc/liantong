package com.baidu.p120ar.face.detector;

import android.os.SystemClock;
import com.baidu.p120ar.armdl.ARMdlManager;
import com.baidu.p120ar.face.algo.FaceJniClient;
import com.baidu.p120ar.statistic.StatisticApi;
import com.baidu.p120ar.utils.ARLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.detector.AnimateJniExecutor */
/* loaded from: E:\10201592_dexfile_execute.dex */
class AnimateJniExecutor extends AbstractJniExecutor {
    private static final String TAG = "AnimateJniExecutor";
    private long animateHandle;
    private int currentFaceNum;

    @Override // com.baidu.p120ar.face.detector.AbstractJniExecutor
    void invokeJni() {
        ARMdlManager.getInstance().setMdlState(14, true);
        this.faceAlgoData = FaceJniClient.animateFace(this.animateHandle, this.faceAlgoData, this.faceHandle);
        if (this.faceAlgoData.getFaceFrame().getTriggersList() != null) {
            String str = TAG;
            ARLog.m20417i(str, "detect_frame animate task executing triggers size:" + this.faceAlgoData.getFaceFrame().getTriggersList().size());
        }
        ARMdlManager.getInstance().setMdlState(14, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.p120ar.face.detector.AbstractJniExecutor
    public void recordExeTime() {
        super.recordExeTime();
        if (this.faceAlgoData != null && this.faceAlgoData.getFaceFrame() != null) {
            if (this.faceAlgoData.getFaceFrame().getProcessResult() != 200) {
                this.currentFaceNum = 0;
            }
            if (this.faceAlgoData.getFaceFrame().getFaceBoxes() != null) {
                this.currentFaceNum = this.faceAlgoData.getFaceFrame().getFaceBoxes().size();
                if (this.currentFaceNum > 4) {
                    this.currentFaceNum = 4;
                }
            }
        }
        StatisticApi.getPerformanceApi().recordAlgoTimeCost("face", "animate", SystemClock.elapsedRealtime() - this.agloBeginTimestamp, 1);
        if (this.facePerfStaticUtil != null) {
            this.facePerfStaticUtil.calculateAnimateCostTime(this.costTime);
        }
        this.faceAlgoLoader.updateCallbackTimeStamp();
    }

    @Override // com.baidu.p120ar.face.detector.AbstractJniExecutor
    public void configure() {
        super.configure();
        this.animateHandle = this.faceAlgoLoader.getmAnimateHandle();
    }

    public int getCurrentFaceNum() {
        return this.currentFaceNum;
    }
}
