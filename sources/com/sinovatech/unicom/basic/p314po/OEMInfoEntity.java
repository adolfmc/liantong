package com.sinovatech.unicom.basic.p314po;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.po.OEMInfoEntity */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class OEMInfoEntity {
    private String imgUrl;
    private String interfaceVersion;
    private boolean isApkInstall;
    private String ishuawei;
    private String oemName;
    private String pakeageName;

    public String getPakeageName() {
        return this.pakeageName;
    }

    public void setPakeageName(String str) {
        this.pakeageName = str;
    }

    public String getInterfaceVersion() {
        return this.interfaceVersion;
    }

    public void setInterfaceVersion(String str) {
        this.interfaceVersion = str;
    }

    public String getOemName() {
        return this.oemName;
    }

    public void setOemName(String str) {
        this.oemName = str;
    }

    public String getIshuawei() {
        return this.ishuawei;
    }

    public void setIshuawei(String str) {
        this.ishuawei = str;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
    }

    public boolean isApkInstall() {
        return this.isApkInstall;
    }

    public void setApkInstall(boolean z) {
        this.isApkInstall = z;
    }
}
