package com.sinovatech.unicom.basic.p315ui.home.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeBusinessEntity */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HomeBusinessEntity {
    private int defImg;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18411id;
    private String isLogin;
    private String productCode;
    private String productDefault;
    private String productDesc;
    private String productName;
    private String productRedirecturl;
    private String productUrl;
    private String productUrlType;
    private String ruleId;
    private String specialType;
    private String strageId;
    private String year;

    public int getDefImg() {
        return this.defImg;
    }

    public void setDefImg(int i) {
        this.defImg = i;
    }

    public long getId() {
        return this.f18411id;
    }

    public void setId(long j) {
        this.f18411id = j;
    }

    public String getIsLogin() {
        return this.isLogin;
    }

    public void setIsLogin(String str) {
        this.isLogin = str;
    }

    public String getProductDefault() {
        return this.productDefault;
    }

    public void setProductDefault(String str) {
        this.productDefault = str;
    }

    public String getProductDesc() {
        return this.productDesc;
    }

    public void setProductDesc(String str) {
        this.productDesc = str;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public String getProductRedirecturl() {
        return this.productRedirecturl;
    }

    public void setProductRedirecturl(String str) {
        this.productRedirecturl = str;
    }

    public String getProductUrl() {
        return this.productUrl;
    }

    public void setProductUrl(String str) {
        this.productUrl = str;
    }

    public String getProductUrlType() {
        return this.productUrlType;
    }

    public void setProductUrlType(String str) {
        this.productUrlType = str;
    }

    public String getRuleId() {
        return this.ruleId;
    }

    public void setRuleId(String str) {
        this.ruleId = str;
    }

    public String getSpecialType() {
        return this.specialType;
    }

    public void setSpecialType(String str) {
        this.specialType = str;
    }

    public String getStrageId() {
        return this.strageId;
    }

    public void setStrageId(String str) {
        this.strageId = str;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String str) {
        this.year = str;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public void setProductCode(String str) {
        this.productCode = str;
    }
}
