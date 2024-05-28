package com.baidu.p120ar.face.detector;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.detector.FaceCallback */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface FaceCallback {
    void onRelease(boolean z);

    void onSetup(boolean z);

    void onTrack(FaceResultModel faceResultModel);
}
