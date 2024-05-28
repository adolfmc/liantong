package com.sinovatech.unicom.basic.p314po;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* renamed from: com.sinovatech.unicom.basic.po.BindNumberActivityEntity */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class BindNumberActivityEntity {
    private String clickTimeForCheckUpdated;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18373id;
    private String mobile;
    private String productName;
    private String productUrl;
    private String updateTime;
    private String userIdType;
    private String userNumColor;
    private String userNumPriture;

    public long getId() {
        return this.f18373id;
    }

    public void setId(long j) {
        this.f18373id = j;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public String getUserNumPriture() {
        return this.userNumPriture;
    }

    public void setUserNumPriture(String str) {
        this.userNumPriture = str;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String str) {
        this.updateTime = str;
    }

    public String getProductUrl() {
        return this.productUrl;
    }

    public void setProductUrl(String str) {
        this.productUrl = str;
    }

    public String getClickTimeForCheckUpdated() {
        return this.clickTimeForCheckUpdated;
    }

    public void setClickTimeForCheckUpdated(String str) {
        this.clickTimeForCheckUpdated = str;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public String getUserIdType() {
        return this.userIdType;
    }

    public void setUserIdType(String str) {
        this.userIdType = str;
    }

    public String getUserNumColor() {
        return this.userNumColor;
    }

    public void setUserNumColor(String str) {
        this.userNumColor = str;
    }
}
