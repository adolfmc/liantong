package com.baidu.p120ar.detector;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.detector.DetectResult */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class DetectResult {
    private String mDetectorName;
    private Object mResultData = null;
    private long mResultHandle = 0;
    private long mTimestamp;

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public void setTimestamp(long j) {
        this.mTimestamp = j;
    }

    public String getDetectorName() {
        return this.mDetectorName;
    }

    public void setDetectorName(String str) {
        this.mDetectorName = str;
    }

    public Object getResultData() {
        return this.mResultData;
    }

    public void setResultData(Object obj) {
        this.mResultData = obj;
    }

    public long getResultHandle() {
        return this.mResultHandle;
    }

    public void setResultHandle(long j) {
        this.mResultHandle = j;
    }
}
