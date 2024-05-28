package com.megvii.lv5.sdk.result;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class LivenessFileResult {
    private LivenessFile[] livenessFiles;
    private String livenessType;
    private int resultCode;

    public LivenessFileResult() {
    }

    public LivenessFileResult(int i) {
        this.resultCode = i;
    }

    public LivenessFile[] getLivenessFiles() {
        return this.livenessFiles;
    }

    public String getLivenessType() {
        return this.livenessType;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setLivenessFiles(LivenessFile[] livenessFileArr) {
        this.livenessFiles = livenessFileArr;
    }

    public void setLivenessType(String str) {
        this.livenessType = str;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }
}
