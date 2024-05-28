package com.baidu.p120ar.stretch;

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
/* renamed from: com.baidu.ar.stretch.StretchDetector */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class StretchDetector extends MdlDetector {
    private static final int INPUT_HEIGHT = 360;
    private static final int INPUT_WIDTH = 640;
    private static final String TAG = "StretchDetector";
    private int mMdlType = -1;

    /* JADX INFO: Access modifiers changed from: private */
    public int getBeautyOrientation(int i, boolean z) {
        return i != -90 ? i != 0 ? i != 90 ? (i == 180 && !z) ? 3 : 1 : z ? 2 : 0 : z ? 3 : 1 : z ? 0 : 2;
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public int getMdlType() {
        return -1;
    }

    public StretchDetector() {
        AsyncTaskManager.getInstance().registerCallBack(this);
        this.mReadParams = new PixelReadParams(PixelType.BGR);
        this.mReadParams.setOutputWidth(640);
        this.mReadParams.setOutputHeight(360);
    }

    @Override // com.baidu.p120ar.detector.IDetector
    public String getName() {
        return TAG;
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlInitTask onCreateInitTask(Bundle bundle) {
        return new MdlInitTask(this.mMdlType) { // from class: com.baidu.ar.stretch.StretchDetector.1
            @Override // com.baidu.p120ar.armdl.task.MdlInitTask, com.baidu.p120ar.async.ARTask
            public String getTag() {
                return "stretch";
            }

            @Override // com.baidu.p120ar.armdl.task.MdlInitTask
            public int executeInit(MdlConfig mdlConfig) {
                String[] strArr = mdlConfig.modelPaths;
                if (strArr == null || strArr.length < 1) {
                    return -1;
                }
                if (mdlConfig.isFromAsset) {
                    return ARMdlInterfaceJNI.initBodyKeyPointFromAssetDir(strArr[0], strArr[1]);
                }
                return ARMdlInterfaceJNI.initBodyKeyPoint(strArr[0], strArr[1]);
            }
        };
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlDestroyTask onCreateDestroyTask() {
        return new MdlDestroyTask(this.mMdlType) { // from class: com.baidu.ar.stretch.StretchDetector.2
            @Override // com.baidu.p120ar.async.ARTask
            public String getTag() {
                return "stretch";
            }

            @Override // com.baidu.p120ar.armdl.task.MdlDestroyTask
            public int executeDestroy() {
                return ARMdlInterfaceJNI.releaseBodyKeyPoint();
            }
        };
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlPredictTask onMdlExecute(FramePixels framePixels) {
        return new MdlPredictTask(this.mMdlType, framePixels) { // from class: com.baidu.ar.stretch.StretchDetector.3
            @Override // com.baidu.p120ar.async.ARTask
            public String getTag() {
                return "stretch";
            }

            @Override // com.baidu.p120ar.armdl.task.MdlPredictTask
            public StretchResult predict(FramePixels framePixels2) {
                int beautyOrientation = StretchDetector.this.getBeautyOrientation(framePixels2.getOrientation().getDegree(), framePixels2.isFrontCamera());
                StretchResult stretchResult = new StretchResult();
                stretchResult.setTimestamp(framePixels2.getTimestamp());
                stretchResult.setDetectorName(StretchDetector.this.getName());
                float[] fArr = new float[189];
                long elapsedRealtime = SystemClock.elapsedRealtime();
                ARMdlInterfaceJNI.predictBodyKeyPoint(framePixels2.getPixelsAddress(), framePixels2.getWidth(), framePixels2.getHeight(), beautyOrientation, fArr);
                StatisticApi.getPerformanceApi().recordAlgoTimeCost("body_beauty", "predict", SystemClock.elapsedRealtime() - elapsedRealtime, 0);
                stretchResult.setPoses(fArr);
                return stretchResult;
            }
        };
    }

    @CallBack
    public void onMdlResult(StretchResult stretchResult) {
        if (this.mDetectorCallback != null) {
            this.mDetectorCallback.onDetected(stretchResult);
        }
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector, com.baidu.p120ar.detector.FrameDetector
    public void releaseFrameDetector() {
        super.releaseFrameDetector();
        AsyncTaskManager.getInstance().unRegisterCallBack(this);
    }
}
