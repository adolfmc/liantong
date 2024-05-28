package com.sinovatech.unicom.separatemodule.notice.utils;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1;
    private String billingType;
    private String brand;
    private String cityCode;
    private String cityName;
    private String netType;
    private String openDate;
    private String payType;
    private String provinceCode;
    private String provinceName;
    private String userMobile;
    private String userType;
    private String vipLev;
    private String ocsflag = "1";
    private boolean woisflag = false;
    private boolean familyNumber = false;

    public String getNetType() {
        return this.netType;
    }

    public void setNetType(String str) {
        this.netType = str;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String str) {
        this.brand = str;
    }

    public String getUserMobile() {
        return this.userMobile;
    }

    public String getBillingType() {
        return this.billingType;
    }

    public void setUserMobile(String str) {
        this.userMobile = str;
    }

    public String getProvinceCode() {
        return this.provinceCode;
    }

    public void setProvinceCode(String str) {
        this.provinceCode = str;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String str) {
        this.cityCode = str;
    }

    public String getOpenDate() {
        return this.openDate;
    }

    public void setOpenDate(String str) {
        this.openDate = str;
    }

    public String getProvinceName() {
        return this.provinceName;
    }

    public void setProvinceName(String str) {
        this.provinceName = str;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String str) {
        this.cityName = str;
    }

    public void setBillingType(String str) {
        this.billingType = str;
    }

    public String getVipLev() {
        return this.vipLev;
    }

    public void setVipLev(String str) {
        this.vipLev = str;
    }

    public String getOcsflag() {
        return this.ocsflag;
    }

    public void setOcsflag(String str) {
        this.ocsflag = str;
    }

    public String getPayType() {
        return this.payType;
    }

    public void setPayType(String str) {
        this.payType = str;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String str) {
        this.userType = str;
    }

    public boolean getWoisflag() {
        return this.woisflag;
    }

    public void setWoisflag(boolean z) {
        this.woisflag = z;
    }

    public boolean getFamilyNumber() {
        return this.familyNumber;
    }

    public void setFamilyNumber(boolean z) {
        this.familyNumber = z;
    }
}
