package com.sinovatech.unicom.separatemodule.audience.entity;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LuckyDrawResultEntity {
    private String prizeImg;
    private String prizeName;
    private String prizeType;
    private String receiveHref;
    private String receivePrompt;
    private String respCode;

    public String getReceiveHref() {
        return this.receiveHref;
    }

    public void setReceiveHref(String str) {
        this.receiveHref = str;
    }

    public String getPrizeName() {
        return this.prizeName;
    }

    public void setPrizeName(String str) {
        this.prizeName = str;
    }

    public String getPrizeType() {
        return this.prizeType;
    }

    public void setPrizeType(String str) {
        this.prizeType = str;
    }

    public String getPrizeImg() {
        return this.prizeImg;
    }

    public void setPrizeImg(String str) {
        this.prizeImg = str;
    }

    public String getRespCode() {
        return this.respCode;
    }

    public void setRespCode(String str) {
        this.respCode = str;
    }

    public String getReceivePrompt() {
        return this.receivePrompt;
    }

    public void setReceivePrompt(String str) {
        this.receivePrompt = str;
    }

    public String toString() {
        return "LuckyDrawResultEntity{receiveHref='" + this.receiveHref + "', prizeName='" + this.prizeName + "', prizeType='" + this.prizeType + "', prizeImg='" + this.prizeImg + "', respCode='" + this.respCode + "', receivePrompt='" + this.receivePrompt + "'}";
    }
}
