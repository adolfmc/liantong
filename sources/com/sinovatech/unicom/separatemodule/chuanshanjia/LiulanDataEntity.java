package com.sinovatech.unicom.separatemodule.chuanshanjia;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LiulanDataEntity {
    private String buttonLinkUrl;
    private String buttonText;
    private String code;
    private String deviceNum;
    private String message;
    private String mobileNum;
    private String uploadImageA;
    private String uploadImageB;
    private boolean upperLimit;

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getDeviceNum() {
        return this.deviceNum;
    }

    public void setDeviceNum(String str) {
        this.deviceNum = str;
    }

    public String getMobileNum() {
        return this.mobileNum;
    }

    public void setMobileNum(String str) {
        this.mobileNum = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getUploadImageA() {
        return this.uploadImageA;
    }

    public void setUploadImageA(String str) {
        if ("null".equals(str)) {
            str = "";
        }
        this.uploadImageA = str;
    }

    public String getUploadImageB() {
        return this.uploadImageB;
    }

    public void setUploadImageB(String str) {
        if ("null".equals(str)) {
            str = "";
        }
        this.uploadImageB = str;
    }

    public String getButtonText() {
        return this.buttonText;
    }

    public void setButtonText(String str) {
        this.buttonText = str;
    }

    public String getButtonLinkUrl() {
        return this.buttonLinkUrl;
    }

    public void setButtonLinkUrl(String str) {
        this.buttonLinkUrl = str;
    }

    public boolean isUpperLimit() {
        return this.upperLimit;
    }

    public void setUpperLimit(boolean z) {
        this.upperLimit = z;
    }
}
