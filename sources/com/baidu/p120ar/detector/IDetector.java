package com.baidu.p120ar.detector;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.detector.IDetector */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IDetector {
    void detect(DetectParams detectParams);

    String getName();

    void release();

    void setup(DetectorCallback detectorCallback);
}
