package com.baidu.p120ar.objdetect;

import com.baidu.p120ar.detector.DetectResult;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.objdetect.ObjDetectResult */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ObjDetectResult extends DetectResult {
    float[] objData;

    public ObjDetectResult(String str, float[] fArr, long j) {
        setDetectorName(str);
        setTimestamp(j);
        this.objData = fArr;
    }

    public float[] getObjData() {
        return this.objData;
    }
}
