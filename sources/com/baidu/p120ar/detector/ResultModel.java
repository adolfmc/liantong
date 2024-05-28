package com.baidu.p120ar.detector;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.detector.ResultModel */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ResultModel {
    private int mAlgoType;
    private String mDetectorName;
    private boolean mSuccess;

    public ResultModel(String str, boolean z) {
        this.mAlgoType = -1;
        this.mDetectorName = str;
        this.mSuccess = z;
    }

    public ResultModel(String str, boolean z, int i) {
        this.mAlgoType = -1;
        this.mDetectorName = str;
        this.mSuccess = z;
        this.mAlgoType = i;
    }

    public String getDetectorName() {
        return this.mDetectorName;
    }

    public void setDetectorName(String str) {
        this.mDetectorName = str;
    }

    public int getAlgoType() {
        return this.mAlgoType;
    }

    public void setAlgoType(int i) {
        this.mAlgoType = i;
    }

    public boolean isSuccess() {
        return this.mSuccess;
    }

    public void setSuccess(boolean z) {
        this.mSuccess = z;
    }
}
