package com.baidu.p120ar.face.algo;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.algo.FaceAlgoData */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FaceAlgoData {
    FaceAlgoConfig mAlgoConfig;
    FaceFrame mFaceFrame;

    public void setFaceFrame(FaceFrame faceFrame) {
        this.mFaceFrame = faceFrame;
    }

    public FaceFrame getFaceFrame() {
        return this.mFaceFrame;
    }

    public void setAlgoConfig(FaceAlgoConfig faceAlgoConfig) {
        this.mAlgoConfig = faceAlgoConfig;
    }

    public FaceAlgoConfig getAlgoConfig() {
        return this.mAlgoConfig;
    }
}
