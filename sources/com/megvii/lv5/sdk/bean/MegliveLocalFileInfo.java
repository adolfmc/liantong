package com.megvii.lv5.sdk.bean;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class MegliveLocalFileInfo implements Serializable {
    private String filePath;
    private String scrrenFilePath;

    public String getFilePath() {
        return this.filePath;
    }

    public String getScrrenFilePath() {
        return this.scrrenFilePath;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public void setScrrenFilePath(String str) {
        this.scrrenFilePath = str;
    }
}
