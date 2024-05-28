package com.megvii.lv5.sdk.result;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class LivenessFile {
    private String actionType;
    private String fileType;
    private boolean isScreenRecord;
    private String path;

    public LivenessFile(String str, String str2, String str3) {
        this(str, str2, str3, false);
    }

    public LivenessFile(String str, String str2, String str3, boolean z) {
        this.path = str;
        this.fileType = str2;
        this.actionType = str3;
        this.isScreenRecord = z;
    }

    public String getActionType() {
        return this.actionType;
    }

    public String getFileType() {
        return this.fileType;
    }

    public String getPath() {
        return this.path;
    }

    public boolean isScreenRecord() {
        return this.isScreenRecord;
    }

    public void setActionType(String str) {
        this.actionType = str;
    }

    public void setFileType(String str) {
        this.fileType = str;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public void setScreenRecord(boolean z) {
        this.isScreenRecord = z;
    }
}
