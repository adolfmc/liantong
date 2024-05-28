package com.baidu.p120ar.detector;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.detector.DetectorParams */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DetectorParams {
    private DetectorType mDetectorType;
    private boolean mSync;

    public DetectorParams(DetectorType detectorType) {
        this.mDetectorType = detectorType;
    }

    public DetectorParams(DetectorType detectorType, boolean z) {
        this.mDetectorType = detectorType;
        this.mSync = z;
    }

    public DetectorType getDetectorType() {
        return this.mDetectorType;
    }

    public void setDetectorType(DetectorType detectorType) {
        this.mDetectorType = detectorType;
    }

    public boolean isSync() {
        return this.mSync;
    }

    public void setSync(boolean z) {
        this.mSync = z;
    }
}
