package com.baidu.p120ar.child;

import com.baidu.p120ar.child.detector.ChildCameraDetectResult;
import com.baidu.p120ar.child.detector.FaceResultModel;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.child.ChildCropModel */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ChildCropModel {
    private ChildCameraDetectResult childCameraDetectResult;
    private FaceResultModel faceResultModel;
    private float frameSecret;

    public ChildCropModel(ChildCameraDetectResult childCameraDetectResult, float f) {
        this.childCameraDetectResult = childCameraDetectResult;
        this.frameSecret = f;
    }

    public ChildCameraDetectResult getChildCameraDetectResult() {
        return this.childCameraDetectResult;
    }

    public float getFrameSecret() {
        return this.frameSecret;
    }

    public FaceResultModel getFaceResultModel() {
        return this.faceResultModel;
    }

    public void setFaceResultModel(FaceResultModel faceResultModel) {
        this.faceResultModel = faceResultModel;
    }
}
