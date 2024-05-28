package com.baidu.p120ar.pose;

import android.os.Bundle;
import android.os.SystemClock;
import com.baidu.p120ar.armdl.detector.MdlDetector;
import com.baidu.p120ar.armdl.task.MdlDestroyTask;
import com.baidu.p120ar.armdl.task.MdlInitTask;
import com.baidu.p120ar.armdl.task.MdlPredictTask;
import com.baidu.p120ar.arplay.core.pixel.FramePixels;
import com.baidu.p120ar.arplay.core.pixel.PixelReadParams;
import com.baidu.p120ar.arplay.core.pixel.PixelType;
import com.baidu.p120ar.async.AsyncTaskManager;
import com.baidu.p120ar.bus.CallBack;
import com.baidu.p120ar.mdl.ARMdlInterfaceJNI;
import com.baidu.p120ar.mdl.MdlConfig;
import com.baidu.p120ar.statistic.StatisticApi;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.pose.PoseDetector */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PoseDetector extends MdlDetector {
    private static final String TAG = "PoseDetector";
    static int[] rindex = {2, 3, 4, 8, 9, 10, 14, 16};
    static int[] lindex = {5, 6, 7, 11, 12, 13, 15, 17};
    private int mMdlType = 0;
    private boolean isCameraData = true;

    /* JADX INFO: Access modifiers changed from: private */
    public int orientation2Degree(int i, boolean z) {
        if (i != -90) {
            if (i == 0) {
                return z ? -90 : 90;
            } else if (i != 90) {
                return i != 180 ? z ? -90 : 90 : z ? 90 : -90;
            } else {
                return 180;
            }
        }
        return 0;
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public int getMdlType() {
        return 0;
    }

    public void setReadParams(int i, int i2) {
        AsyncTaskManager.getInstance().registerCallBack(this);
        this.mReadParams = new PixelReadParams(PixelType.BGR);
        this.mReadParams.setOutputWidth(i);
        this.mReadParams.setOutputHeight(i2);
    }

    public void setInputType(boolean z) {
        this.isCameraData = z;
    }

    @Override // com.baidu.p120ar.detector.IDetector
    public String getName() {
        return TAG;
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlInitTask onCreateInitTask(final Bundle bundle) {
        return new MdlInitTask(this.mMdlType) { // from class: com.baidu.ar.pose.PoseDetector.1
            @Override // com.baidu.p120ar.armdl.task.MdlInitTask, com.baidu.p120ar.async.ARTask
            public String getTag() {
                return "pose";
            }

            @Override // com.baidu.p120ar.armdl.task.MdlInitTask
            public int executeInit(MdlConfig mdlConfig) {
                String[] strArr = mdlConfig.modelPaths;
                if (strArr == null || strArr.length == 0) {
                    return -1;
                }
                float f = bundle.getFloat("cutoffSlope", 1.0f);
                float[] floatArray = bundle.getFloatArray("min_cutofffreq");
                if (mdlConfig.isFromAsset) {
                    return ARMdlInterfaceJNI.initPoseFromAsset(strArr[0], strArr[1], strArr[2], 1, f, floatArray, floatArray);
                }
                return ARMdlInterfaceJNI.initPose(strArr[0], strArr[1], strArr[2], 1, f, floatArray, floatArray);
            }
        };
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlDestroyTask onCreateDestroyTask() {
        return new MdlDestroyTask(this.mMdlType) { // from class: com.baidu.ar.pose.PoseDetector.2
            @Override // com.baidu.p120ar.async.ARTask
            public String getTag() {
                return "pose";
            }

            @Override // com.baidu.p120ar.armdl.task.MdlDestroyTask
            public int executeDestroy() {
                return ARMdlInterfaceJNI.releasePose();
            }
        };
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlPredictTask onMdlExecute(FramePixels framePixels) {
        return new MdlPredictTask(this.mMdlType, framePixels) { // from class: com.baidu.ar.pose.PoseDetector.3
            @Override // com.baidu.p120ar.async.ARTask
            public String getTag() {
                return "pose";
            }

            @Override // com.baidu.p120ar.armdl.task.MdlPredictTask
            public PoseResult predict(FramePixels framePixels2) {
                int orientation2Degree = framePixels2.isCameraFrame() ? PoseDetector.this.orientation2Degree(framePixels2.getOrientation().getDegree(), framePixels2.isFrontCamera()) : 180;
                long timestamp = framePixels2.getTimestamp();
                float[] fArr = new float[54];
                long elapsedRealtime = SystemClock.elapsedRealtime();
                ARMdlInterfaceJNI.predictPose(framePixels2.getPixelsAddress(), framePixels2.getWidth(), framePixels2.getHeight(), orientation2Degree, framePixels2.isFrontCamera(), fArr);
                StatisticApi.getPerformanceApi().recordAlgoTimeCost("body_pose", "predict", SystemClock.elapsedRealtime() - elapsedRealtime, 0);
                if (framePixels2.isFrontCamera()) {
                    PoseDetector.operatorFrontData(fArr);
                    for (int i = 0; i < 18; i++) {
                        int i2 = (i * 3) + 1;
                        fArr[i2] = 1.0f - fArr[i2];
                    }
                }
                return new PoseResult(PoseDetector.this.getName(), fArr, timestamp);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void operatorFrontData(float[] fArr) {
        for (int i = 0; i < 8; i++) {
            int[] iArr = rindex;
            float f = fArr[iArr[i] * 3];
            float f2 = fArr[(iArr[i] * 3) + 1];
            float f3 = fArr[(iArr[i] * 3) + 2];
            int[] iArr2 = lindex;
            fArr[iArr[i] * 3] = fArr[iArr2[i] * 3];
            fArr[(iArr[i] * 3) + 1] = fArr[(iArr2[i] * 3) + 1];
            fArr[(iArr[i] * 3) + 2] = fArr[(iArr2[i] * 3) + 2];
            fArr[iArr2[i] * 3] = f;
            fArr[(iArr2[i] * 3) + 1] = f2;
            fArr[(iArr2[i] * 3) + 2] = f3;
        }
    }

    @CallBack
    public void onMdlResult(PoseResult poseResult) {
        if (this.mDetectorCallback != null) {
            this.mDetectorCallback.onDetected(poseResult);
        }
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector, com.baidu.p120ar.detector.FrameDetector
    public void releaseFrameDetector() {
        super.releaseFrameDetector();
        AsyncTaskManager.getInstance().unRegisterCallBack(this);
    }
}
