package com.baidu.p120ar.hand;

import com.baidu.p120ar.AbstractAR;
import com.baidu.p120ar.armdl.ARMdlManager;
import com.baidu.p120ar.arrender.IARRenderer;
import com.baidu.p120ar.databasic.AlgoHandleController;
import com.baidu.p120ar.detector.DetectResult;
import com.baidu.p120ar.detector.DetectorCallback;
import com.baidu.p120ar.detector.ResultModel;
import com.baidu.p120ar.utils.ARLog;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.hand.HandAR */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HandAR extends AbstractAR {
    private static final String TAG = "HandAR";
    private AlgoHandleController mAlgoHandleController = null;
    private DetectorCallback mDetectorCallback;
    private HandDetector mHandDetector;

    @Override // com.baidu.p120ar.AbstractAR
    public void setup(HashMap<String, Object> hashMap) {
        super.setup(hashMap);
        if (this.mAlgoHandleController == null) {
            this.mAlgoHandleController = new AlgoHandleController();
        }
        this.mHandDetector = new HandDetector();
        this.mHandDetector.setAlgoHandleController(this.mAlgoHandleController);
        this.mDetectorCallback = new DetectorCallback() { // from class: com.baidu.ar.hand.HandAR.1
            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onSetup(ResultModel resultModel) {
                String str = HandAR.TAG;
                ARLog.m20421d(str, "onSetup result = " + resultModel.isSuccess());
                IARRenderer renderer = HandAR.this.getRenderer();
                if (renderer == null || HandAR.this.mHandDetector == null) {
                    return;
                }
                renderer.addAlgoCache(resultModel.getAlgoType(), HandAR.this.mHandDetector.isDetectSync());
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onDetected(DetectResult detectResult) {
                long resultHandle = detectResult.getResultHandle();
                IARRenderer renderer = HandAR.this.getRenderer();
                if (renderer == null || resultHandle <= 0) {
                    return;
                }
                renderer.setAlgoHandleData(resultHandle, "ability_hand_skeleton");
                HandAR.this.onAlgoHandleDestory(resultHandle);
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onRelease(ResultModel resultModel) {
                String str = HandAR.TAG;
                ARLog.m20421d(str, "onRelease result = " + resultModel.isSuccess());
            }
        };
        addDetector(this.mHandDetector, this.mDetectorCallback);
        ARMdlManager.getInstance().setConfigs(getContext(), getMdlConfigs());
        this.mHandDetector.enableMdl(null);
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void onAlgoHandleDestory(long j) {
        AlgoHandleController algoHandleController;
        super.onAlgoHandleDestory(j);
        if (j <= 0 || (algoHandleController = this.mAlgoHandleController) == null || algoHandleController.getHandleType(j) != 19) {
            return;
        }
        destroyHandle(j);
    }

    private void destroyHandle(long j) {
        AlgoHandleController algoHandleController = this.mAlgoHandleController;
        if (algoHandleController == null || j <= 0) {
            return;
        }
        long handleType = algoHandleController.getHandleType(j);
        HandDetector handDetector = this.mHandDetector;
        if (handDetector == null || handleType != 19) {
            return;
        }
        handDetector.destroyHandle(j);
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void release() {
        HandDetector handDetector = this.mHandDetector;
        if (handDetector != null) {
            handDetector.setAlgoHandleController(null);
            this.mHandDetector.disableMdl();
            removeDetector(this.mHandDetector);
        }
        AlgoHandleController algoHandleController = this.mAlgoHandleController;
        if (algoHandleController != null) {
            algoHandleController.release();
            this.mAlgoHandleController = null;
        }
        IARRenderer renderer = getRenderer();
        if (renderer != null) {
            renderer.removeAlgoCache(19);
        }
        super.release();
    }
}
