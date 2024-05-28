package com.baidu.p120ar.face.detector;

import com.baidu.p120ar.detector.DetectResult;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.detector.FaceResult */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FaceResult extends DetectResult {
    private FaceResultModel faceResultModel;

    public FaceResult(String str, FaceResultModel faceResultModel) {
        this.faceResultModel = faceResultModel;
        setDetectorName(str);
        setTimestamp(faceResultModel.getTimestamp());
    }

    public FaceResultModel getFaceResultModel() {
        return this.faceResultModel;
    }

    public void setFaceResultModel(FaceResultModel faceResultModel) {
        this.faceResultModel = faceResultModel;
    }
}
