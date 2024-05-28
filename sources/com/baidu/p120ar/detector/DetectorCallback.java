package com.baidu.p120ar.detector;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.detector.DetectorCallback */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface DetectorCallback {
    void onDetected(DetectResult detectResult);

    void onRelease(ResultModel resultModel);

    void onSetup(ResultModel resultModel);
}
