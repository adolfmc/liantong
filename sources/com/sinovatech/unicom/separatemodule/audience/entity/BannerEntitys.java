package com.sinovatech.unicom.separatemodule.audience.entity;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class BannerEntitys implements Serializable {
    public String bgImg;
    public String isLogin;
    public String url;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getBgImg() {
        return this.bgImg;
    }

    public void setBgImg(String str) {
        this.bgImg = str;
    }

    public String getIsLogin() {
        return this.isLogin;
    }

    public void setIsLogin(String str) {
        this.isLogin = str;
    }
}
