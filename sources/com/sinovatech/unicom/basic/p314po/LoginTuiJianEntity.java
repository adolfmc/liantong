package com.sinovatech.unicom.basic.p314po;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* renamed from: com.sinovatech.unicom.basic.po.LoginTuiJianEntity */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LoginTuiJianEntity {
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18388id;
    private String productName;
    private String productRedirecturl;
    private String productUrl;
    private String showTab;
    private String tag;
    private int textColor;

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public long getId() {
        return this.f18388id;
    }

    public void setId(long j) {
        this.f18388id = j;
    }

    public LoginTuiJianEntity() {
    }

    public LoginTuiJianEntity(String str, String str2, String str3, int i, String str4, String str5) {
        this.productRedirecturl = str;
        this.productName = str2;
        this.productUrl = str3;
        this.textColor = i;
        this.showTab = str4;
        this.tag = str5;
    }

    public String getProductRedirecturl() {
        return this.productRedirecturl;
    }

    public void setProductRedirecturl(String str) {
        this.productRedirecturl = str;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public String getProductUrl() {
        return this.productUrl;
    }

    public void setProductUrl(String str) {
        this.productUrl = str;
    }

    public int getTextColor() {
        return this.textColor;
    }

    public void setTextColor(int i) {
        this.textColor = i;
    }

    public String getShowTab() {
        return this.showTab;
    }

    public void setShowTab(String str) {
        this.showTab = str;
    }
}
