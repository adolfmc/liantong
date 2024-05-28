package com.baidu.p120ar.pose;

import com.baidu.p120ar.detector.DetectResult;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.pose.PoseResult */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PoseResult extends DetectResult {
    private float[] mPoses;

    public PoseResult(String str, float[] fArr, long j) {
        this.mPoses = fArr;
        setDetectorName(str);
        setTimestamp(j);
    }

    public float[] getPoses() {
        return this.mPoses;
    }

    public void setPoses(float[] fArr) {
        this.mPoses = fArr;
    }
}
