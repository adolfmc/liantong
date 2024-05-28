package com.baidu.p120ar.objdetect;

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
/* renamed from: com.baidu.ar.objdetect.ObjDetectDetector */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ObjDetectDetector extends MdlDetector {
    private static final int INPUT_HEIGHT = 360;
    private static final int INPUT_WIDTH = 640;
    private static final String TAG = "ObjDetectDetector";
    private int mMdlType = 6;

    /* JADX INFO: Access modifiers changed from: private */
    public int getInputOrientation(int i, boolean z) {
        return i != -90 ? i != 90 ? i != 180 ? z ? 3 : 1 : z ? 1 : 3 : z ? 0 : 2 : z ? 2 : 0;
    }

    public ObjDetectDetector() {
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
    public int getMdlType() {
        return this.mMdlType;
    }

    @CallBack
    public void onMdlResult(ObjDetectResult objDetectResult) {
        if (this.mDetectorCallback != null) {
            this.mDetectorCallback.onDetected(objDetectResult);
        }
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlInitTask onCreateInitTask(Bundle bundle) {
        return new MdlInitTask(this.mMdlType) { // from class: com.baidu.ar.objdetect.ObjDetectDetector.1
            @Override // com.baidu.p120ar.armdl.task.MdlInitTask, com.baidu.p120ar.async.ARTask
            public String getTag() {
                return "obj";
            }

            @Override // com.baidu.p120ar.armdl.task.MdlInitTask
            public int executeInit(MdlConfig mdlConfig) {
                String[] strArr = mdlConfig.modelPaths;
                if (strArr == null || strArr.length == 0) {
                    return -1;
                }
                if (mdlConfig.isFromAsset) {
                    return ARMdlInterfaceJNI.initObjDetectFromAsset(strArr[0], strArr[1]);
                }
                return ARMdlInterfaceJNI.initObjDetect(strArr[0], strArr[1]);
            }
        };
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlDestroyTask onCreateDestroyTask() {
        return new MdlDestroyTask(this.mMdlType) { // from class: com.baidu.ar.objdetect.ObjDetectDetector.2
            @Override // com.baidu.p120ar.async.ARTask
            public String getTag() {
                return "obj";
            }

            @Override // com.baidu.p120ar.armdl.task.MdlDestroyTask
            public int executeDestroy() {
                return ARMdlInterfaceJNI.releaseObjDetect();
            }
        };
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlPredictTask onMdlExecute(FramePixels framePixels) {
        return new MdlPredictTask(this.mMdlType, framePixels) { // from class: com.baidu.ar.objdetect.ObjDetectDetector.3
            @Override // com.baidu.p120ar.async.ARTask
            public String getTag() {
                return "obj";
            }

            @Override // com.baidu.p120ar.armdl.task.MdlPredictTask
            public ObjDetectResult predict(FramePixels framePixels2) {
                int inputOrientation = ObjDetectDetector.this.getInputOrientation(framePixels2.getOrientation().getDegree(), framePixels2.isFrontCamera());
                long timestamp = framePixels2.getTimestamp();
                float[] fArr = new float[36];
                long elapsedRealtime = SystemClock.elapsedRealtime();
                ARMdlInterfaceJNI.predictObjDetect(framePixels2.getPixelsAddress(), framePixels2.getWidth(), framePixels2.getHeight(), inputOrientation, framePixels2.isFrontCamera(), fArr);
                StatisticApi.getPerformanceApi().recordAlgoTimeCost("obj_detect", "predict", SystemClock.elapsedRealtime() - elapsedRealtime, 0);
                return new ObjDetectResult(ObjDetectDetector.this.getName(), fArr, timestamp);
            }
        };
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector, com.baidu.p120ar.detector.FrameDetector
    public void releaseFrameDetector() {
        super.releaseFrameDetector();
        AsyncTaskManager.getInstance().unRegisterCallBack(this);
    }
}
