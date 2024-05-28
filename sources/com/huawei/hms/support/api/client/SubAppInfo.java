package com.huawei.hms.support.api.client;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SubAppInfo {
    private String subAppID;

    public SubAppInfo(SubAppInfo subAppInfo) {
        if (subAppInfo != null) {
            this.subAppID = subAppInfo.getSubAppID();
        }
    }

    public String getSubAppID() {
        return this.subAppID;
    }

    public void setSubAppInfoID(String str) {
        this.subAppID = str;
    }

    public SubAppInfo(String str) {
        this.subAppID = str;
    }
}
