package com.sinovatech.unicom.basic.p314po;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* renamed from: com.sinovatech.unicom.basic.po.SkinEntity */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SkinEntity {
    private String homePicture;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18397id;
    private String skinId;
    private String updateTime;
    private String userNumPriture;

    public long getId() {
        return this.f18397id;
    }

    public void setId(long j) {
        this.f18397id = j;
    }

    public String getSkinId() {
        return this.skinId;
    }

    public void setSkinId(String str) {
        this.skinId = str;
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

    public String getHomePicture() {
        return this.homePicture;
    }

    public void setHomePicture(String str) {
        this.homePicture = str;
    }
}
