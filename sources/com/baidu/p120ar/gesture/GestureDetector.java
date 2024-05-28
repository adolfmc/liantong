package com.baidu.p120ar.gesture;

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
/* renamed from: com.baidu.ar.gesture.GestureDetector */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GestureDetector extends MdlDetector {
    private static final int GESTURE_INPUT_HEIGHT = 180;
    private static final int GESTURE_INPUT_WIDTH = 320;
    private static final String TAG = "GestureDetector";
    private int mMdlType = 1;

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

    public GestureDetector() {
        AsyncTaskManager.getInstance().registerCallBack(this);
        this.mReadParams = new PixelReadParams(PixelType.BGR);
        this.mReadParams.setOutputWidth(320);
        this.mReadParams.setOutputHeight(180);
    }

    @CallBack
    public void onMdlResult(GestureResult gestureResult) {
        if (this.mDetectorCallback != null) {
            this.mDetectorCallback.onDetected(gestureResult);
        }
    }

    @Override // com.baidu.p120ar.detector.IDetector
    public String getName() {
        return TAG;
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public int getMdlType() {
        return this.mMdlType;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getGestureFuntype(Bundle bundle) {
        if (bundle == null) {
            return 1;
        }
        String string = bundle.getString("function_type", "gesture");
        if ("gesture".equals(string)) {
            return 1;
        }
        return "fingertip".equals(string) ? 2 : 0;
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlInitTask onCreateInitTask(final Bundle bundle) {
        return new MdlInitTask(this.mMdlType) { // from class: com.baidu.ar.gesture.GestureDetector.1
            @Override // com.baidu.p120ar.armdl.task.MdlInitTask, com.baidu.p120ar.async.ARTask
            public String getTag() {
                return "GestureDetector";
            }

            @Override // com.baidu.p120ar.armdl.task.MdlInitTask
            public int executeInit(MdlConfig mdlConfig) {
                String[] strArr = mdlConfig.modelPaths;
                if (strArr == null || strArr.length == 0) {
                    return -1;
                }
                int i = bundle.getInt("force_ft_pose_flag", 0);
                float f = bundle.getFloat("det_thresh", 0.25f);
                float f2 = bundle.getFloat("first_cls_thresh", 0.75f);
                float f3 = bundle.getFloat("second_cls_thresh", 0.5f);
                if (mdlConfig.isFromAsset) {
                    return ARMdlInterfaceJNI.initGestureFromAsset(strArr[0], strArr[1], strArr[2], GestureDetector.this.getGestureFuntype(bundle), i, f, f2, f3, 1);
                }
                return ARMdlInterfaceJNI.initGesture(strArr[0], strArr[1], strArr[2], GestureDetector.this.getGestureFuntype(bundle), i, f, f2, f3, 1);
            }
        };
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlDestroyTask onCreateDestroyTask() {
        return new MdlDestroyTask(this.mMdlType) { // from class: com.baidu.ar.gesture.GestureDetector.2
            @Override // com.baidu.p120ar.async.ARTask
            public String getTag() {
                return "GestureDetector";
            }

            @Override // com.baidu.p120ar.armdl.task.MdlDestroyTask
            public int executeDestroy() {
                return ARMdlInterfaceJNI.releaseGesture();
            }
        };
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlPredictTask onMdlExecute(FramePixels framePixels) {
        return new MdlPredictTask<GestureResult>(this.mMdlType, framePixels) { // from class: com.baidu.ar.gesture.GestureDetector.3
            @Override // com.baidu.p120ar.async.ARTask
            public String getTag() {
                return "GestureDetector";
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.baidu.p120ar.armdl.task.MdlPredictTask
            public GestureResult predict(FramePixels framePixels2) {
                int orientation2Degree = GestureDetector.this.orientation2Degree(framePixels2.getOrientation().getDegree(), framePixels2.isFrontCamera());
                long timestamp = framePixels2.getTimestamp();
                float[] fArr = new float[13];
                long elapsedRealtime = SystemClock.elapsedRealtime();
                ARMdlInterfaceJNI.predictGesture(framePixels2.getPixelsAddress(), framePixels2.getWidth(), framePixels2.getHeight(), orientation2Degree, framePixels2.isFrontCamera(), fArr);
                StatisticApi.getPerformanceApi().recordAlgoTimeCost("finger_gesture", "predict", SystemClock.elapsedRealtime() - elapsedRealtime, 0);
                return new GestureResult(GestureDetector.this.getName(), fArr, timestamp);
            }
        };
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector, com.baidu.p120ar.detector.FrameDetector
    public void releaseFrameDetector() {
        super.releaseFrameDetector();
        AsyncTaskManager.getInstance().unRegisterCallBack(this);
    }
}
