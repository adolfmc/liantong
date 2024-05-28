package com.sinovatech.unicom.separatemodule.audience.entity;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SaoMaQuHaoEntity {
    private String callNumber;
    private String code;
    private String createTime;
    private String epId;
    private String epName;
    private String moreLink;
    private String msg;
    private String typeName;
    private String waitContant;

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getMoreLink() {
        return this.moreLink;
    }

    public void setMoreLink(String str) {
        this.moreLink = str;
    }

    public String getCallNumber() {
        return this.callNumber;
    }

    public void setCallNumber(String str) {
        this.callNumber = str;
    }

    public String getWaitContant() {
        return this.waitContant;
    }

    public void setWaitContant(String str) {
        this.waitContant = str;
    }

    public String getEpName() {
        return this.epName;
    }

    public void setEpName(String str) {
        this.epName = str;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String str) {
        this.typeName = str;
    }

    public String getEpId() {
        return this.epId;
    }

    public void setEpId(String str) {
        this.epId = str;
    }

    public String toString() {
        return "SaoMaQuHaoEntity{moreLink='" + this.moreLink + "', callNumber='" + this.callNumber + "', waitContant='" + this.waitContant + "', epName='" + this.epName + "', typeName='" + this.typeName + "', epId='" + this.epId + "', code='" + this.code + "', msg='" + this.msg + "'}";
    }
}
