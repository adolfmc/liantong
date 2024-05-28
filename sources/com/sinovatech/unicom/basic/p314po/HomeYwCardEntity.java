package com.sinovatech.unicom.basic.p314po;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.po.HomeYwCardEntity */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HomeYwCardEntity {
    private String imgUrl;
    private boolean isSuccess;
    private String linkUrl;
    private String number;
    private String type;

    public HomeYwCardEntity(boolean z) {
        this.isSuccess = z;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String str) {
        this.number = str;
    }

    public String getLinkUrl() {
        return this.linkUrl;
    }

    public void setLinkUrl(String str) {
        this.linkUrl = str;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setSuccess(boolean z) {
        this.isSuccess = z;
    }
}
