package com.sinovatech.unicom.separatemodule.baidumap.entity;

import android.text.TextUtils;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

@Entity
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaiduSearchEntity {
    public String backMode;
    private String cityCode;
    private String desc;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18500id;
    private String imageURL;
    private String mobile;
    private String needLogin;
    private String price;
    private String proviceCode;
    private String searchId;
    public int sectionsIndex;
    public String sectionsTitle;
    private String title;
    private String type;
    private String url;
    public boolean isGroup = false;
    private boolean isFooter = false;

    public long getId() {
        return this.f18500id;
    }

    public void setId(long j) {
        this.f18500id = j;
    }

    public String getType() {
        if (TextUtils.isEmpty(this.type)) {
            this.type = "";
        }
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getSearchId() {
        return this.searchId;
    }

    public void setSearchId(String str) {
        this.searchId = str;
    }

    public String getTitle() {
        if (TextUtils.isEmpty(this.title)) {
            this.title = "";
        }
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getUrl() {
        if (TextUtils.isEmpty(this.url)) {
            this.url = "";
        }
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getImageURL() {
        if (TextUtils.isEmpty(this.imageURL)) {
            this.imageURL = "";
        }
        return this.imageURL;
    }

    public void setImageURL(String str) {
        this.imageURL = str;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public String getNeedLogin() {
        return this.needLogin;
    }

    public void setNeedLogin(String str) {
        this.needLogin = str;
    }

    public String getBackMode() {
        if (TextUtils.isEmpty(this.backMode)) {
            this.backMode = "1";
        }
        return this.backMode;
    }

    public void setBackMode(String str) {
        this.backMode = str;
    }

    public boolean isGroup() {
        return this.isGroup;
    }

    public void setGroup(boolean z) {
        this.isGroup = z;
    }

    public String getSectionsTitle() {
        if (TextUtils.isEmpty(this.sectionsTitle)) {
            this.sectionsTitle = "";
        }
        return this.sectionsTitle;
    }

    public void setSectionsTitle(String str) {
        this.sectionsTitle = str;
    }

    public int getSectionsIndex() {
        return this.sectionsIndex;
    }

    public void setSectionsIndex(int i) {
        this.sectionsIndex = i;
    }

    public boolean isFooter() {
        return this.isFooter;
    }

    public void setFooter(boolean z) {
        this.isFooter = z;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public String getProviceCode() {
        return this.proviceCode;
    }

    public void setProviceCode(String str) {
        this.proviceCode = str;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String str) {
        this.cityCode = str;
    }
}
