package com.baidu.p120ar.hand;

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
import com.baidu.p120ar.detector.ResultModel;
import com.baidu.p120ar.mdl.ARMdlInterfaceJNI;
import com.baidu.p120ar.mdl.MdlConfig;
import com.baidu.p120ar.statistic.StatisticApi;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.hand.HandDetector */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HandDetector extends MdlDetector {
    private static final int INPUT_HEIGHT = 360;
    private static final int INPUT_WIDTH = 640;
    private static final String TAG = "HandDetector";
    private int mMdlType = 7;

    /* JADX INFO: Access modifiers changed from: private */
    public int getInputOrientation(int i, boolean z) {
        if (i != -90) {
            if (i != 90) {
                return i != 180 ? z ? 3 : 1 : z ? 1 : 3;
            }
            return 2;
        }
        return 0;
    }

    public HandDetector() {
        AsyncTaskManager.getInstance().registerCallBack(this);
        this.mReadParams = new PixelReadParams(PixelType.NV21);
        this.mReadParams.setOutputWidth(640);
        this.mReadParams.setOutputHeight(360);
    }

    @CallBack
    public void onMdlResult(HandResult handResult) {
        if (this.mDetectorCallback != null) {
            this.mDetectorCallback.onDetected(handResult);
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

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlInitTask onCreateInitTask(Bundle bundle) {
        return new MdlInitTask(this.mMdlType) { // from class: com.baidu.ar.hand.HandDetector.1
            @Override // com.baidu.p120ar.armdl.task.MdlInitTask, com.baidu.p120ar.async.ARTask
            public String getTag() {
                return "hand";
            }

            @Override // com.baidu.p120ar.armdl.task.MdlInitTask
            public int executeInit(MdlConfig mdlConfig) {
                if (mdlConfig.isFromAsset) {
                    return ARMdlInterfaceJNI.initHandSkeletonFromAsset(mdlConfig.modelPaths[0], mdlConfig.modelPaths[1]);
                }
                return ARMdlInterfaceJNI.initHandSkeleton(mdlConfig.modelPaths[0], mdlConfig.modelPaths[1]);
            }
        };
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlDestroyTask onCreateDestroyTask() {
        return new MdlDestroyTask(this.mMdlType) { // from class: com.baidu.ar.hand.HandDetector.2
            @Override // com.baidu.p120ar.async.ARTask
            public String getTag() {
                return "hand";
            }

            @Override // com.baidu.p120ar.armdl.task.MdlDestroyTask
            public int executeDestroy() {
                return ARMdlInterfaceJNI.releaseHandSkeleton();
            }
        };
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector
    public MdlPredictTask onMdlExecute(final FramePixels framePixels) {
        return new MdlPredictTask(this.mMdlType, framePixels) { // from class: com.baidu.ar.hand.HandDetector.3
            @Override // com.baidu.p120ar.async.ARTask
            public String getTag() {
                return "hand";
            }

            @Override // com.baidu.p120ar.armdl.task.MdlPredictTask
            public HandResult predict(FramePixels framePixels2) {
                long timestamp = framePixels2.getTimestamp();
                int inputOrientation = HandDetector.this.getInputOrientation(framePixels2.getOrientation().getDegree(), framePixels2.isFrontCamera());
                long elapsedRealtime = SystemClock.elapsedRealtime();
                try {
                    long createHandle = HandDetector.this.mAlgoHandleController.createHandle();
                    HandDetector.this.mAlgoHandleController.setHandleInput(createHandle, 19, framePixels2.getTimestamp(), 0, framePixels2.getWidth(), framePixels2.getHeight(), framePixels2.isFrontCamera(), framePixels2.getSegOrientation().getValue(), false, framePixels2.getPixelsAddress());
                    ARMdlInterfaceJNI.predictHandSkeletonByHandle(createHandle, inputOrientation);
                    StatisticApi.getPerformanceApi().recordAlgoTimeCost("finger_gesture", "predict", SystemClock.elapsedRealtime() - elapsedRealtime, 0);
                    return new HandResult(HandDetector.this.getName(), createHandle, timestamp);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            public HashMap executeMdl() {
                int inputOrientation = HandDetector.this.getInputOrientation(framePixels.getOrientation().getDegree(), framePixels.isFrontCamera());
                HashMap hashMap = new HashMap();
                hashMap.put("timestamp", Long.valueOf(framePixels.getTimestamp()));
                long elapsedRealtime = SystemClock.elapsedRealtime();
                try {
                    long createHandle = HandDetector.this.mAlgoHandleController.createHandle();
                    HandDetector.this.mAlgoHandleController.setHandleInput(createHandle, 19, framePixels.getTimestamp(), 0, framePixels.getWidth(), framePixels.getHeight(), framePixels.isFrontCamera(), framePixels.getSegOrientation().getValue(), false, framePixels.getPixelsAddress());
                    ARMdlInterfaceJNI.predictHandSkeletonByHandle(createHandle, inputOrientation);
                    StatisticApi.getPerformanceApi().recordAlgoTimeCost("finger_gesture", "predict", SystemClock.elapsedRealtime() - elapsedRealtime, 0);
                    hashMap.put("handle", Long.valueOf(createHandle));
                    return hashMap;
                } catch (Exception e) {
                    e.printStackTrace();
                    return hashMap;
                }
            }
        };
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector, com.baidu.p120ar.detector.FrameDetector
    public void setupFrameDetector() {
        if (this.mDetectorCallback != null) {
            this.mDetectorCallback.onSetup(new ResultModel(getName(), true, 19));
        }
    }

    @Override // com.baidu.p120ar.armdl.detector.MdlDetector, com.baidu.p120ar.detector.FrameDetector
    public void releaseFrameDetector() {
        if (this.mDetectorCallback != null) {
            this.mDetectorCallback.onRelease(new ResultModel(getName(), true, 19));
        }
        AsyncTaskManager.getInstance().unRegisterCallBack(this);
    }
}
