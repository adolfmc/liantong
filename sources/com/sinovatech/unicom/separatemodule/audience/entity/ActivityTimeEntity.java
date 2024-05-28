package com.sinovatech.unicom.separatemodule.audience.entity;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ActivityTimeEntity {
    private String acId;
    private String acName;
    private String acType;
    private Long beginTime;
    private String durationTime;
    private Long endTime;
    private String ifPartakeActivity;
    private String popupActivityHref;
    private String popupActivityImg;
    private String preheatImg;
    private Long preheatTime;
    private String respCode;

    public String getPopupActivityImg() {
        return this.popupActivityImg;
    }

    public void setPopupActivityImg(String str) {
        this.popupActivityImg = str;
    }

    public String getPopupActivityHref() {
        return this.popupActivityHref;
    }

    public void setPopupActivityHref(String str) {
        this.popupActivityHref = str;
    }

    public String getAcId() {
        return this.acId;
    }

    public void setAcId(String str) {
        this.acId = str;
    }

    public String getDurationTime() {
        return this.durationTime;
    }

    public void setDurationTime(String str) {
        this.durationTime = str;
    }

    public Long getPreheatTime() {
        return this.preheatTime;
    }

    public void setPreheatTime(Long l) {
        this.preheatTime = l;
    }

    public String getAcName() {
        return this.acName;
    }

    public void setAcName(String str) {
        this.acName = str;
    }

    public String getIfPartakeActivity() {
        return this.ifPartakeActivity;
    }

    public void setIfPartakeActivity(String str) {
        this.ifPartakeActivity = str;
    }

    public Long getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(Long l) {
        this.beginTime = l;
    }

    public Long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Long l) {
        this.endTime = l;
    }

    public String getAcType() {
        return this.acType;
    }

    public void setAcType(String str) {
        this.acType = str;
    }

    public String getRespCode() {
        return this.respCode;
    }

    public void setRespCode(String str) {
        this.respCode = str;
    }

    public String getPreheatImg() {
        return this.preheatImg;
    }

    public void setPreheatImg(String str) {
        this.preheatImg = str;
    }

    public String toString() {
        return "ActivityTimeEntity{acId='" + this.acId + "', durationTime='" + this.durationTime + "', preheatTime=" + this.preheatTime + ", acName='" + this.acName + "', ifPartakeActivity='" + this.ifPartakeActivity + "', beginTime=" + this.beginTime + ", endTime=" + this.endTime + ", acType='" + this.acType + "', respCode='" + this.respCode + "', preheatImg='" + this.preheatImg + "'}";
    }
}
