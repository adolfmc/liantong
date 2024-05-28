package com.baidu.p120ar.face.detector;

import android.os.SystemClock;
import com.baidu.p120ar.databasic.AlgoHandleController;
import com.baidu.p120ar.face.algo.FAUImage;
import com.baidu.p120ar.face.algo.FaceAlgoConfig;
import com.baidu.p120ar.face.algo.FaceAlgoData;
import com.baidu.p120ar.face.algo.FaceFrame;
import com.baidu.p120ar.face.algo.FaceJniClient;
import com.baidu.p120ar.statistic.StatisticApi;
import com.baidu.p120ar.utils.ARLog;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.detector.CreateJniExecutor */
/* loaded from: E:\10201592_dexfile_execute.dex */
class CreateJniExecutor extends AbstractJniExecutor {
    private static final boolean DEBUG = false;
    private static final String TAG = "CreateJniExecutor";
    private AlgoHandleController algoHandleController;
    private FaceAlgoConfig faceAlgoConfig;
    private FAUImage fauImage;

    public CreateJniExecutor(ByteBuffer byteBuffer) {
        super(byteBuffer);
    }

    public void setDataHandle(long j) {
        this.dataHandle = j;
    }

    public long getDataHandle() {
        return this.dataHandle;
    }

    @Override // com.baidu.p120ar.face.detector.AbstractJniExecutor
    void invokeJni() {
        ARLog.m20413w(TAG, "detect_frame track task executing before createFrame");
        this.faceHandle = FaceJniClient.createFrame(this.fauImage);
        this.faceAlgoData = new FaceAlgoData();
        this.faceAlgoData.setAlgoConfig(this.faceAlgoConfig);
        this.faceAlgoData.setFaceFrame(new FaceFrame());
        if (this.algoHandleController == null || this.dataHandle <= 0) {
            return;
        }
        this.algoHandleController.setHandleFaceHandle(this.dataHandle, this.faceHandle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.p120ar.face.detector.AbstractJniExecutor
    public void recordExeTime() {
        super.recordExeTime();
        StatisticApi.getPerformanceApi().recordAlgoTimeCost("face", "create", SystemClock.elapsedRealtime() - this.agloBeginTimestamp, 1);
        if (this.facePerfStaticUtil != null) {
            this.facePerfStaticUtil.calculateCreateCostTime(this.costTime);
        }
    }

    @Override // com.baidu.p120ar.face.detector.AbstractJniExecutor
    public void configure() {
        super.configure();
        this.faceAlgoConfig = this.faceAlgoLoader.getmFaceAlgoConfig().cloneInstance();
    }

    public void configure(int i, int i2, boolean z, int i3, boolean z2, long j) {
        AlgoHandleController algoHandleController;
        configure();
        this.algoHandleController = this.faceAlgoLoader.getmAlgoHandleController();
        if (this.faceAlgoLoader.ismUsePaasHandle() && (algoHandleController = this.algoHandleController) != null) {
            this.dataHandle = algoHandleController.createHandle();
            this.faceAlgoLoader.getmAlgoHandleController().setHandleInput(this.dataHandle, 10, j, 2, i, i2, z, i3, z2, this.cameraData);
        }
        this.fauImage = new FAUImage(this.cameraData, i, i2, 2);
    }
}
