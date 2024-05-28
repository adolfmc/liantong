package com.baidu.p120ar.child;

import android.text.TextUtils;
import com.baidu.p120ar.AbstractAR;
import com.baidu.p120ar.arrender.IARRenderer;
import com.baidu.p120ar.child.ChildAlgoController;
import com.baidu.p120ar.child.detector.ChildCameraDetectResult;
import com.baidu.p120ar.child.detector.ChildCameraDetector;
import com.baidu.p120ar.child.detector.ChildFilterDetectResult;
import com.baidu.p120ar.child.detector.ChildFilterDetector;
import com.baidu.p120ar.child.detector.FaceResultModel;
import com.baidu.p120ar.child.http.ChildRequestController;
import com.baidu.p120ar.databasic.AlgoHandleController;
import com.baidu.p120ar.detector.DetectResult;
import com.baidu.p120ar.detector.DetectorCallback;
import com.baidu.p120ar.detector.IDetector;
import com.baidu.p120ar.detector.ResultModel;
import com.baidu.p120ar.lua.LuaMsgListener;
import com.baidu.p120ar.statistic.StatisticApi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.child.ChildAR */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ChildAR extends AbstractAR implements LuaMsgListener {
    private ChildAlgoController mChildAlgoController;
    private ChildCameraDetector mChildCameraDetector;
    private ChildRequestController mChildRequestController;
    private boolean mDetectorIsReleased;
    private FaceResultModel mFaceResultModel;
    private String mAbilityName = null;
    private AlgoHandleController mAlgoHandleController = null;
    private DetectorCallback mFaceDetectorCallback = new DetectorCallback() { // from class: com.baidu.ar.child.ChildAR.1
        @Override // com.baidu.p120ar.detector.DetectorCallback
        public void onRelease(ResultModel resultModel) {
        }

        @Override // com.baidu.p120ar.detector.DetectorCallback
        public void onSetup(ResultModel resultModel) {
        }

        @Override // com.baidu.p120ar.detector.DetectorCallback
        public void onDetected(DetectResult detectResult) {
            if (ChildAR.this.mChildAlgoController == null || ChildAR.this.mDetectorIsReleased) {
                if (ChildAR.this.mAlgoHandleController != null) {
                    ChildAR.this.mAlgoHandleController.destroyHandle(detectResult.getResultHandle());
                    return;
                }
                return;
            }
            if (ChildAR.this.mAlgoHandleController.getHandleType(detectResult.getResultHandle()) == 10) {
                long createHandle = ChildAR.this.mAlgoHandleController.createHandle();
                ChildAR.this.mFaceResultModel = new FaceResultModel();
                if (ChildAR.this.mChildAlgoController != null) {
                    ChildAR.this.mFaceResultModel.setFaceList(ChildAR.this.mChildAlgoController.getFaceBoxList(detectResult.getResultHandle()));
                    ChildAR.this.mFaceResultModel.setTrackingPoints(ChildAR.this.mChildAlgoController.getTrackingPoints(detectResult.getResultHandle()));
                    ChildAR.this.mFaceResultModel.setFaceHandle(createHandle);
                }
            }
            if (ChildAR.this.mAlgoHandleController != null && !ChildAR.this.mDetectorIsReleased) {
                ChildAR.this.mAlgoHandleController.destroyHandle(detectResult.getResultHandle());
            }
            ChildAR.this.stopFaceDetector();
        }
    };

    @Override // com.baidu.p120ar.AbstractAR
    public void setup(HashMap<String, Object> hashMap) {
        if (this.mAlgoHandleController == null) {
            this.mAlgoHandleController = new AlgoHandleController();
        }
        this.mAbilityName = (String) hashMap.get("ability_name");
        this.mChildAlgoController = new ChildAlgoController(this.mInputWidth, this.mInputHeight);
        this.mChildRequestController = new ChildRequestController();
        this.mChildAlgoController.setChildAlgoCallback(new ChildAlgoController.ChildAlgoCallback() { // from class: com.baidu.ar.child.ChildAR.2
            @Override // com.baidu.p120ar.child.ChildAlgoController.ChildAlgoCallback
            public void onChildAlgoResult(final long j, byte[] bArr, int i) {
                if (bArr == null || bArr.length <= 0) {
                    return;
                }
                ChildAR.this.mChildRequestController.requestCropFaceResult(bArr, i, new ChildRequestController.ChildRequestCallback() { // from class: com.baidu.ar.child.ChildAR.2.1
                    @Override // com.baidu.p120ar.child.http.ChildRequestController.ChildRequestCallback
                    public void onRequestCallbak(byte[] bArr2) {
                        if (bArr2 == null) {
                            return;
                        }
                        StatisticApi.onEvent("event_face2kid_caseuse");
                        ChildAR.this.wirteChildFaceToHandle(bArr2, j);
                    }
                });
            }
        });
        addLuaMsgListener(this);
        IARRenderer renderer = getRenderer();
        if (renderer != null) {
            renderer.addAlgoCache(20, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void wirteChildFaceToHandle(byte[] bArr, long j) {
        int i;
        IARRenderer renderer;
        AlgoHandleController algoHandleController;
        ChildAlgoController childAlgoController = this.mChildAlgoController;
        if (childAlgoController == null || this.mDetectorIsReleased || j <= 0) {
            return;
        }
        childAlgoController.writeFaceToHandle(j, bArr);
        if (TextUtils.isEmpty(this.mAbilityName) || (renderer = getRenderer()) == null || i <= 0 || (algoHandleController = this.mAlgoHandleController) == null) {
            return;
        }
        algoHandleController.sendHandleToRenderer(j, renderer, "ability_face_child");
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void adjust(HashMap<String, Object> hashMap) {
        super.adjust(hashMap);
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void resume() {
        super.resume();
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void pause() {
        super.pause();
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void release() {
        super.release();
        this.mDetectorIsReleased = !this.mDetectorIsReleased;
        if (this.mChildCameraDetector != null) {
            this.mChildCameraDetector = null;
        }
        AlgoHandleController algoHandleController = this.mAlgoHandleController;
        if (algoHandleController != null) {
            algoHandleController.release();
            this.mAlgoHandleController = null;
        }
        ChildAlgoController childAlgoController = this.mChildAlgoController;
        if (childAlgoController != null && !this.mDetectorIsReleased) {
            childAlgoController.setChildAlgoCallback(null);
            this.mChildAlgoController.requestClear();
            this.mChildAlgoController = null;
        }
        IARRenderer renderer = getRenderer();
        if (renderer != null) {
            renderer.removeAlgoCache(20);
        }
    }

    @Override // com.baidu.p120ar.lua.LuaMsgListener
    public List<String> getMsgKeyListened() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("event_name");
        return arrayList;
    }

    @Override // com.baidu.p120ar.lua.LuaMsgListener
    public void onLuaMessage(HashMap<String, Object> hashMap) {
        if (this.mDetectorIsReleased || hashMap == null || hashMap.keySet().size() < 1) {
            return;
        }
        requestChildFrame(hashMap);
    }

    private void requestChildFrame(HashMap<String, Object> hashMap) {
        if (hashMap.containsKey("case_texture")) {
            String str = (String) hashMap.get("case_texture");
            if (hashMap.containsKey("excute_frame")) {
                float floatValue = ((Float) hashMap.get("excute_frame")).floatValue();
                if (hashMap.containsKey("index")) {
                    float floatValue2 = ((Float) hashMap.get("index")).floatValue();
                    if (floatValue != 1.0f || TextUtils.isEmpty(str)) {
                        return;
                    }
                    requestChildFilterFrame(str, floatValue2);
                }
            }
        }
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void onAlgoHandleDestory(long j) {
        AlgoHandleController algoHandleController;
        super.onAlgoHandleDestory(j);
        if (j <= 0 || (algoHandleController = this.mAlgoHandleController) == null || algoHandleController.getHandleType(j) != 20) {
            return;
        }
        this.mAlgoHandleController.destroyHandle(j);
    }

    private void requestChildFilterFrame(String str, float f) {
        ChildFilterDetector childFilterDetector = new ChildFilterDetector();
        childFilterDetector.setReadParamFilterId(str);
        startFaceDetector();
        startCameraDetector(f);
        startFilterDetector(f, childFilterDetector);
    }

    private void startFilterDetector(final float f, final ChildFilterDetector childFilterDetector) {
        addDetector(childFilterDetector, new DetectorCallback() { // from class: com.baidu.ar.child.ChildAR.3
            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onRelease(ResultModel resultModel) {
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onSetup(ResultModel resultModel) {
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onDetected(DetectResult detectResult) {
                if (detectResult != null && (detectResult instanceof ChildFilterDetectResult)) {
                    ChildFilterDetectResult childFilterDetectResult = (ChildFilterDetectResult) detectResult;
                    if (childFilterDetectResult == null || childFilterDetectResult.getDatas() == null) {
                        return;
                    }
                    long createHandle = ChildAR.this.mAlgoHandleController.createHandle();
                    ChildAR.this.mChildAlgoController.writeTypeToHandle(createHandle);
                    ChildAR.this.mChildAlgoController.writeFilterDataToHandle(createHandle, childFilterDetectResult.getDatas(), ChildAR.this.mInputHeight, ChildAR.this.mInputWidth, f);
                    IARRenderer renderer = ChildAR.this.getRenderer();
                    if (renderer != null && createHandle > 0 && ChildAR.this.mAlgoHandleController != null) {
                        if (ChildAR.this.mDetectorIsReleased) {
                            return;
                        }
                        ChildAR.this.mAlgoHandleController.sendHandleToRenderer(createHandle, renderer, "ability_face_child");
                    }
                }
                ChildAR.this.removeFrameDetectorInThread(childFilterDetector);
            }
        });
    }

    private void startCameraDetector(final float f) {
        this.mChildCameraDetector = new ChildCameraDetector();
        addDetector(this.mChildCameraDetector, new DetectorCallback() { // from class: com.baidu.ar.child.ChildAR.4
            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onRelease(ResultModel resultModel) {
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onSetup(ResultModel resultModel) {
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onDetected(DetectResult detectResult) {
                if (ChildAR.this.mDetectorIsReleased || ChildAR.this.mChildAlgoController == null || ChildAR.this.mFaceResultModel == null || !(detectResult instanceof ChildCameraDetectResult)) {
                    return;
                }
                ChildAR.this.mChildAlgoController.writeTypeToHandle(ChildAR.this.mFaceResultModel.getFaceHandle());
                ChildCropModel childCropModel = new ChildCropModel((ChildCameraDetectResult) detectResult, f);
                childCropModel.setFaceResultModel(ChildAR.this.mFaceResultModel);
                ChildAR.this.mChildAlgoController.requestCropFace(childCropModel);
                ChildAR childAR = ChildAR.this;
                childAR.removeFrameDetectorInThread(childAR.mChildCameraDetector);
            }
        });
    }

    private void startFaceDetector() {
        requireStartDetector("FaceDetector", this.mFaceDetectorCallback, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeFrameDetectorInThread(final IDetector iDetector) {
        if (iDetector == null) {
            return;
        }
        new Thread(new Runnable() { // from class: com.baidu.ar.child.ChildAR.5
            @Override // java.lang.Runnable
            public void run() {
                ChildAR.this.removeDetector(iDetector);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopFaceDetector() {
        DetectorCallback detectorCallback = this.mFaceDetectorCallback;
        if (detectorCallback != null) {
            requireStopDetector("FaceDetector", detectorCallback);
        }
    }
}
