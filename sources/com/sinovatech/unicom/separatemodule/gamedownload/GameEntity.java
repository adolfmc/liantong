package com.sinovatech.unicom.separatemodule.gamedownload;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class GameEntity implements Serializable {
    private String downloadUrl;
    private String iconUrl;

    /* renamed from: id */
    private String f18537id;
    private String mainTitle;
    private String packageName;
    private String subTitle;

    public GameEntity(String str, String str2, String str3, String str4, String str5, String str6) {
        this.f18537id = str;
        this.mainTitle = str2;
        this.subTitle = str3;
        this.iconUrl = str4;
        this.downloadUrl = str5;
        this.packageName = str6;
    }

    public String getId() {
        return this.f18537id;
    }

    public void setId(String str) {
        this.f18537id = str;
    }

    public String getMainTitle() {
        return this.mainTitle;
    }

    public void setMainTitle(String str) {
        this.mainTitle = str;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public void setIconUrl(String str) {
        this.iconUrl = str;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public void setDownloadUrl(String str) {
        this.downloadUrl = str;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }
}
