package com.sinovatech.unicom.separatemodule.skin.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class BackgroundEntity {
    private List<BackgroundBannerEntity> bannerEntities;
    private List<BackgroundSkinEntity> skinEntities;
    private BackgroundTongYongBean tongYongBean;

    public BackgroundTongYongBean getTongYongBean() {
        return this.tongYongBean;
    }

    public void setTongYongBean(BackgroundTongYongBean backgroundTongYongBean) {
        this.tongYongBean = backgroundTongYongBean;
    }

    public List<BackgroundBannerEntity> getBannerEntities() {
        return this.bannerEntities;
    }

    public void setBannerEntities(List<BackgroundBannerEntity> list) {
        this.bannerEntities = list;
    }

    public List<BackgroundSkinEntity> getSkinEntities() {
        return this.skinEntities;
    }

    public void setSkinEntities(List<BackgroundSkinEntity> list) {
        this.skinEntities = list;
    }
}
