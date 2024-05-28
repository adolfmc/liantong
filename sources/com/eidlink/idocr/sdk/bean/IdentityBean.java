package com.eidlink.idocr.sdk.bean;

import java.io.Serializable;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class IdentityBean implements Serializable {
    public String address;
    public String beginTime;
    public String birthDate;
    public String classify;
    public String endTime;
    public String idType;
    public String idnum;
    public String name;
    public String nation;
    public String picture;
    public String sex;
    public String signingOrganization;

    public String getAddress() {
        return this.address;
    }

    public String getBeginTime() {
        return this.beginTime;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public String getClassify() {
        return this.classify;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public String getIdType() {
        return this.idType;
    }

    public String getIdnum() {
        return this.idnum;
    }

    public String getName() {
        return this.name;
    }

    public String getNation() {
        return this.nation;
    }

    public String getPicture() {
        return this.picture;
    }

    public String getSex() {
        return this.sex;
    }

    public String getSigningOrganization() {
        return this.signingOrganization;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setBeginTime(String str) {
        this.beginTime = str;
    }

    public void setBirthDate(String str) {
        this.birthDate = str;
    }

    public void setClassify(String str) {
        this.classify = str;
    }

    public void setEndTime(String str) {
        this.endTime = str;
    }

    public void setIdType(String str) {
        this.idType = str;
    }

    public void setIdnum(String str) {
        this.idnum = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNation(String str) {
        this.nation = str;
    }

    public void setPicture(String str) {
        this.picture = str;
    }

    public void setSex(String str) {
        this.sex = str;
    }

    public void setSigningOrganization(String str) {
        this.signingOrganization = str;
    }

    public String toString() {
        return "IdentityBean{classify='" + this.classify + "', idType='" + this.idType + "', birthDate='" + this.birthDate + "', address='" + this.address + "', nation='" + this.nation + "', sex='" + this.sex + "', name='" + this.name + "', endTime='" + this.endTime + "', signingOrganization='" + this.signingOrganization + "', beginTime='" + this.beginTime + "', idnum='" + this.idnum + "', picture='" + this.picture + "'}";
    }
}
