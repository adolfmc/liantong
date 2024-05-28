package com.sinovatech.unicom.basic.p314po;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

@Entity
/* renamed from: com.sinovatech.unicom.basic.po.CustomSkinChoseEntity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CustomSkinChoseEntity {
    private String homeSmall;

    /* renamed from: id */
    private long f18379id;
    private String imgCode;
    private String mineUrl;
    @InterfaceC12072Id
    private long positionId;
    private String skinid;
    private String status;
    private String thumbUrl;
    private String type;
    private String url;

    public CustomSkinChoseEntity() {
    }

    public CustomSkinChoseEntity(String str, String str2, String str3) {
        this.skinid = str;
        this.url = str3;
        this.type = str2;
        this.status = "0";
    }

    public CustomSkinChoseEntity(long j, String str, String str2, String str3) {
        this(str, str2, str3);
        this.f18379id = j;
    }

    public CustomSkinChoseEntity(long j, String str, String str2, String str3, String str4) {
        this(j, str, str2, str3);
        this.status = str4;
    }

    public String getSkinid() {
        return this.skinid;
    }

    public void setSkinid(String str) {
        this.skinid = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getThumbUrl() {
        return this.thumbUrl;
    }

    public void setThumbUrl(String str) {
        this.thumbUrl = str;
    }

    public long getId() {
        return this.f18379id;
    }

    public void setId(long j) {
        this.f18379id = j;
    }

    public boolean equals(Object obj) {
        return (obj instanceof CustomSkinChoseEntity) && this.f18379id == ((CustomSkinChoseEntity) obj).f18379id;
    }

    public int hashCode() {
        return String.valueOf(this.f18379id).hashCode();
    }

    public long getPositionId() {
        return this.positionId;
    }

    public void setPositionId(long j) {
        this.positionId = j;
    }

    public String getMineUrl() {
        return this.mineUrl;
    }

    public void setMineUrl(String str) {
        this.mineUrl = str;
    }

    public String getHomeSmall() {
        return this.homeSmall;
    }

    public void setHomeSmall(String str) {
        this.homeSmall = str;
    }

    public String getImgCode() {
        return this.imgCode;
    }

    public void setImgCode(String str) {
        this.imgCode = str;
    }
}
