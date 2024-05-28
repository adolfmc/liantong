package com.sinovatech.unicom.separatemodule.advtise.bean;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SplashAdConfigEntity {
    private AdConfigEntity failConfigEntity;
    private AdConfigEntity successConfigEntity;

    public AdConfigEntity getSuccessConfigEntity() {
        return this.successConfigEntity;
    }

    public void setSuccessConfigEntity(AdConfigEntity adConfigEntity) {
        this.successConfigEntity = adConfigEntity;
    }

    public AdConfigEntity getFailConfigEntity() {
        return this.failConfigEntity;
    }

    public void setFailConfigEntity(AdConfigEntity adConfigEntity) {
        this.failConfigEntity = adConfigEntity;
    }
}
