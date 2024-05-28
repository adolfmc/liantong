package com.baidu.p120ar.headseg;

import com.baidu.p120ar.AbstractAR;
import com.baidu.p120ar.armdl.ARMdlManager;
import com.baidu.p120ar.arrender.IARRenderer;
import com.baidu.p120ar.databasic.AlgoHandleAdapter;
import com.baidu.p120ar.databasic.AlgoHandleController;
import com.baidu.p120ar.detector.DetectResult;
import com.baidu.p120ar.detector.DetectorCallback;
import com.baidu.p120ar.detector.ResultModel;
import com.baidu.p120ar.mdl.ARMdlInterfaceJNI;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.headseg.HeadSegAR */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HeadSegAR extends AbstractAR {
    private static final String TAG = "HeadSegAR";
    private HeadSegDetector mSegDetector;
    private String mAbilityName = "ability_head_segmentation";
    private AlgoHandleController mAlgoHandleController = null;
    private DetectorCallback mSegDetectorCallback = new DetectorCallback() { // from class: com.baidu.ar.headseg.HeadSegAR.1
        @Override // com.baidu.p120ar.detector.DetectorCallback
        public void onRelease(ResultModel resultModel) {
        }

        @Override // com.baidu.p120ar.detector.DetectorCallback
        public void onSetup(ResultModel resultModel) {
            IARRenderer renderer = HeadSegAR.this.getRenderer();
            if (renderer == null || HeadSegAR.this.mSegDetector == null || resultModel == null) {
                return;
            }
            renderer.addAlgoCache(resultModel.getAlgoType(), HeadSegAR.this.mSegDetector.isDetectSync());
        }

        @Override // com.baidu.p120ar.detector.DetectorCallback
        public void onDetected(DetectResult detectResult) {
            long resultHandle = detectResult.getResultHandle();
            IARRenderer renderer = HeadSegAR.this.getRenderer();
            if (renderer == null || resultHandle <= 0 || HeadSegAR.this.mAlgoHandleController == null) {
                return;
            }
            HeadSegAR.this.mAlgoHandleController.sendHandleToRenderer(resultHandle, renderer, "ability_head_segmentation");
            HeadSegAR.this.onAlgoHandleDestory(resultHandle);
        }
    };
    private DetectorCallback mFaceDetectorCallback = new DetectorCallback() { // from class: com.baidu.ar.headseg.HeadSegAR.2
        @Override // com.baidu.p120ar.detector.DetectorCallback
        public void onRelease(ResultModel resultModel) {
        }

        @Override // com.baidu.p120ar.detector.DetectorCallback
        public void onSetup(ResultModel resultModel) {
        }

        @Override // com.baidu.p120ar.detector.DetectorCallback
        public void onDetected(DetectResult detectResult) {
            long resultHandle = detectResult.getResultHandle();
            if (HeadSegAR.this.mAlgoHandleController != null) {
                if (HeadSegAR.this.mAlgoHandleController.getHandleType(resultHandle) == 10) {
                    ARMdlInterfaceJNI.updateLastFaceInfo(resultHandle);
                }
            } else {
                ARMdlInterfaceJNI.updateLastFaceInfo(0L);
            }
            AlgoHandleAdapter.destroyHandle(resultHandle);
        }
    };

    @Override // com.baidu.p120ar.AbstractAR
    public void setup(HashMap<String, Object> hashMap) {
        super.setup(hashMap);
        if (this.mAlgoHandleController == null) {
            this.mAlgoHandleController = new AlgoHandleController();
        }
        this.mSegDetector = new HeadSegDetector();
        this.mSegDetector.setAlgoHandleController(this.mAlgoHandleController);
        HashMap<String, Object> hashMap2 = new HashMap<>();
        hashMap2.put("ability_name", this.mAbilityName);
        requireStartDetector("FaceDetector", this.mFaceDetectorCallback, hashMap2);
        setDetectSync(true);
        addDetector(this.mSegDetector, this.mSegDetectorCallback);
        ARMdlManager.getInstance().setConfigs(getContext(), getMdlConfigs());
        this.mSegDetector.enableMdl(null);
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void onAlgoHandleDestory(long j) {
        AlgoHandleController algoHandleController;
        super.onAlgoHandleDestory(j);
        if (j <= 0 || (algoHandleController = this.mAlgoHandleController) == null || algoHandleController.getHandleType(j) != 22) {
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
        HeadSegDetector headSegDetector = this.mSegDetector;
        if (headSegDetector == null || handleType != 22) {
            return;
        }
        headSegDetector.destroyHandle(j);
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void release() {
        setDetectSync(false);
        HeadSegDetector headSegDetector = this.mSegDetector;
        if (headSegDetector != null) {
            headSegDetector.setAlgoHandleController(null);
            this.mSegDetector.disableMdl();
            removeDetector(this.mSegDetector);
        }
        AlgoHandleController algoHandleController = this.mAlgoHandleController;
        if (algoHandleController != null) {
            algoHandleController.release();
            this.mAlgoHandleController = null;
        }
        IARRenderer renderer = getRenderer();
        if (renderer != null) {
            renderer.removeAlgoCache(22);
        }
        super.release();
    }
}
