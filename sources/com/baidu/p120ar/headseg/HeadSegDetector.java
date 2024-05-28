package com.baidu.p120ar.headseg;

import android.os.Bundle;
import com.baidu.p120ar.armdl.detector.MdlDetector;
import com.baidu.p120ar.armdl.task.MdlDestroyTask;
import com.baidu.p120ar.armdl.task.MdlInitTask;
import com.baidu.p120ar.armdl.task.MdlPredictTask;
import com.baidu.p120ar.arplay.core.pixel.FramePixels;
import com.baidu.p120ar.arplay.core.pixel.PixelReadParams;
import com.baidu.p120ar.arplay.core.pixel.PixelType;
import com.baidu.p120ar.async.AsyncTaskManager;
import com.baidu.p120ar.bus.CallBack;
import com.baidu.p120ar.detector.ResultModel;
import com.baidu.p120ar.mdl.ARMdlInterfaceJNI;
import com.baidu.p120ar.mdl.MdlConfig;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.headseg.HeadSegDetector */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HeadSegDetector extends MdlDetector {
    private static final String TAG = "HeadSegDetector";
    private int mHeadSegInputWidth = 256;
    private int mHeadSegInputHeight = 144;
    private int mMdlType = 8;
    private float mMaskThreshold = 0.5f;

    public HeadSegDetector() {
        AsyncTaskManager.getInstance().registerCallBack(this);
        this.mReadParams = new PixelReadParams(PixelType.NV21);
        this.mReadParams.setOutputWidth(this.mHeadSegInputWidth);
        this.mReadParams.setOutputHeight(this.mHeadSegInputHeight);
    }

    public void setMaskThreshold(float f) {
        this.mMaskThreshold = f;
    }

    @CallBack
    public void onMdlResult(HeadSegResult headSegResult) {
        if (this.mDetectorCallback != null) {
            this.mDetectorCallback.onDetected(headSegResult);
        }
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector, com.baidu.p120ar.detector.FrameDetector
    public void setupFrameDetector() {
        if (this.mDetectorCallback != null) {
            this.mDetectorCallback.onSetup(new ResultModel(getName(), true, 22));
        }
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector, com.baidu.p120ar.detector.FrameDetector
    public void releaseFrameDetector() {
        if (this.mDetectorCallback != null) {
            this.mDetectorCallback.onRelease(new ResultModel(getName(), true, 22));
        }
        AsyncTaskManager.getInstance().unRegisterCallBack(this);
    }

    @Override // com.baidu.p120ar.detector.IDetector
    public String getName() {
        return TAG;
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public int getMdlType() {
        return this.mMdlType;
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlInitTask onCreateInitTask(Bundle bundle) {
        return new MdlInitTask(this.mMdlType) { // from class: com.baidu.ar.headseg.HeadSegDetector.1
            @Override // com.baidu.p120ar.armdl.task.MdlInitTask, com.baidu.p120ar.async.ARTask
            public String getTag() {
                return "headseg";
            }

            @Override // com.baidu.p120ar.armdl.task.MdlInitTask
            public int executeInit(MdlConfig mdlConfig) {
                String str = mdlConfig.modelPaths[0];
                if (mdlConfig.isFromAsset) {
                    return ARMdlInterfaceJNI.initHeadSegFromAsset(str);
                }
                return ARMdlInterfaceJNI.initHeadSeg(str);
            }
        };
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlDestroyTask onCreateDestroyTask() {
        return new MdlDestroyTask(this.mMdlType) { // from class: com.baidu.ar.headseg.HeadSegDetector.2
            @Override // com.baidu.p120ar.async.ARTask
            public String getTag() {
                return "headseg";
            }

            @Override // com.baidu.p120ar.armdl.task.MdlDestroyTask
            public int executeDestroy() {
                return ARMdlInterfaceJNI.releaseHeadSeg();
            }
        };
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlPredictTask onMdlExecute(FramePixels framePixels) {
        return new MdlPredictTask(this.mMdlType, framePixels) { // from class: com.baidu.ar.headseg.HeadSegDetector.3
            @Override // com.baidu.p120ar.async.ARTask
            public String getTag() {
                return "headseg";
            }

            /* JADX WARN: Removed duplicated region for block: B:20:0x00a5  */
            @Override // com.baidu.p120ar.armdl.task.MdlPredictTask
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public com.baidu.p120ar.headseg.HeadSegResult predict(com.baidu.p120ar.arplay.core.pixel.FramePixels r22) {
                /*
                    r21 = this;
                    r0 = r21
                    com.baidu.ar.headseg.HeadSegResult r1 = new com.baidu.ar.headseg.HeadSegResult
                    r1.<init>()
                    long r2 = r22.getTimestamp()
                    r1.setTimestamp(r2)
                    com.baidu.ar.headseg.HeadSegDetector r2 = com.baidu.p120ar.headseg.HeadSegDetector.this
                    java.lang.String r2 = r2.getName()
                    r1.setDetectorName(r2)
                    long r2 = android.os.SystemClock.elapsedRealtime()
                    com.baidu.ar.headseg.HeadSegDetector r6 = com.baidu.p120ar.headseg.HeadSegDetector.this     // Catch: java.lang.Exception -> L9d
                    com.baidu.ar.databasic.AlgoHandleController r6 = com.baidu.p120ar.headseg.HeadSegDetector.access$000(r6)     // Catch: java.lang.Exception -> L9d
                    long r14 = r6.createHandle()     // Catch: java.lang.Exception -> L9d
                    com.baidu.ar.headseg.HeadSegDetector r6 = com.baidu.p120ar.headseg.HeadSegDetector.this     // Catch: java.lang.Exception -> L99
                    com.baidu.ar.databasic.AlgoHandleController r6 = com.baidu.p120ar.headseg.HeadSegDetector.access$100(r6)     // Catch: java.lang.Exception -> L99
                    r6.setUsingHandle(r14)     // Catch: java.lang.Exception -> L99
                    boolean r6 = r22.isFrontCamera()     // Catch: java.lang.Exception -> L99
                    if (r6 == 0) goto L36
                    r6 = 4
                    goto L37
                L36:
                    r6 = 7
                L37:
                    r17 = r6
                    com.baidu.ar.headseg.HeadSegDetector r6 = com.baidu.p120ar.headseg.HeadSegDetector.this     // Catch: java.lang.Exception -> L99
                    com.baidu.ar.databasic.AlgoHandleController r7 = com.baidu.p120ar.headseg.HeadSegDetector.access$200(r6)     // Catch: java.lang.Exception -> L99
                    r10 = 22
                    long r11 = r22.getTimestamp()     // Catch: java.lang.Exception -> L99
                    r13 = 0
                    int r6 = r22.getWidth()     // Catch: java.lang.Exception -> L99
                    int r16 = r22.getHeight()     // Catch: java.lang.Exception -> L99
                    boolean r18 = r22.isFrontCamera()     // Catch: java.lang.Exception -> L99
                    r19 = 1
                    java.nio.ByteBuffer r20 = r22.getPixelsAddress()     // Catch: java.lang.Exception -> L99
                    r8 = r14
                    r4 = r14
                    r14 = r6
                    r15 = r16
                    r16 = r18
                    r18 = r19
                    r19 = r20
                    r7.setHandleInput(r8, r10, r11, r13, r14, r15, r16, r17, r18, r19)     // Catch: java.lang.Exception -> L9a
                    com.baidu.ar.headseg.HeadSegDetector r6 = com.baidu.p120ar.headseg.HeadSegDetector.this     // Catch: java.lang.Exception -> L9a
                    com.baidu.ar.databasic.AlgoHandleController r6 = com.baidu.p120ar.headseg.HeadSegDetector.access$400(r6)     // Catch: java.lang.Exception -> L9a
                    com.baidu.ar.headseg.HeadSegDetector r7 = com.baidu.p120ar.headseg.HeadSegDetector.this     // Catch: java.lang.Exception -> L9a
                    float r7 = com.baidu.p120ar.headseg.HeadSegDetector.access$300(r7)     // Catch: java.lang.Exception -> L9a
                    r6.setHandleMaskThreshold(r4, r7)     // Catch: java.lang.Exception -> L9a
                    r6 = 1
                    com.baidu.p120ar.mdl.ARMdlInterfaceJNI.predictHeadSeg(r4, r6)     // Catch: java.lang.Exception -> L9a
                    com.baidu.ar.statistic.IPerformanceStatisticApi r7 = com.baidu.p120ar.statistic.StatisticApi.getPerformanceApi()     // Catch: java.lang.Exception -> L9a
                    java.lang.String r8 = "head_seg"
                    java.lang.String r9 = "predict"
                    long r10 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Exception -> L9a
                    long r10 = r10 - r2
                    r12 = 0
                    r7.recordAlgoTimeCost(r8, r9, r10, r12)     // Catch: java.lang.Exception -> L9a
                    com.baidu.ar.headseg.HeadSegDetector r2 = com.baidu.p120ar.headseg.HeadSegDetector.this     // Catch: java.lang.Exception -> L9a
                    com.baidu.ar.databasic.AlgoHandleController r2 = com.baidu.p120ar.headseg.HeadSegDetector.access$500(r2)     // Catch: java.lang.Exception -> L9a
                    r6 = 0
                    r2.setUsingHandle(r6)     // Catch: java.lang.Exception -> L97
                    r2 = r4
                    goto La8
                L97:
                    r2 = r6
                    goto La1
                L99:
                    r4 = r14
                L9a:
                    r2 = 0
                    goto La1
                L9d:
                    r2 = 0
                    r4 = 0
                La1:
                    int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
                    if (r6 <= 0) goto La8
                    com.baidu.p120ar.databasic.AlgoHandleAdapter.destroyHandle(r4)
                La8:
                    r1.setResultHandle(r2)
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.baidu.p120ar.headseg.HeadSegDetector.C23003.predict(com.baidu.ar.arplay.core.pixel.FramePixels):com.baidu.ar.headseg.HeadSegResult");
            }
        };
    }
}
