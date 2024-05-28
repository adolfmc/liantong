package com.sinovatech.unicom.basic.p315ui.home.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeMergeFuChuangEntity */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HomeMergeFuChuangEntity {
    private String actId;
    private String actType;
    private String advJson;
    private String advertiseBackMode;
    public String advertiseImageURL;
    private String advertiseIndex;
    public String advertiseTargetType;
    public String advertiseTargetURL;
    public String advertiseTitle;
    private String cityCode;
    private String goodsId;
    private String handleNumber;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18417id;
    public String idx;
    private String imageSrcVII;
    private String imageSrcVIIChecked;
    public boolean isNeedLogin;
    private String provinceCode;
    private String rightImgSrc;
    public int titleColor;
    private String viceTitle;
    private String ywCode;

    public int getTitleColor() {
        return this.titleColor;
    }

    public void setTitleColor(int i) {
        this.titleColor = i;
    }

    public String getAdvertiseTitle() {
        return this.advertiseTitle;
    }

    public void setAdvertiseTitle(String str) {
        this.advertiseTitle = str;
    }

    public String getAdvertiseTargetURL() {
        return this.advertiseTargetURL;
    }

    public void setAdvertiseTargetURL(String str) {
        this.advertiseTargetURL = str;
    }

    public String getAdvertiseImageURL() {
        return this.advertiseImageURL;
    }

    public void setAdvertiseImageURL(String str) {
        this.advertiseImageURL = str;
    }

    public String getAdvertiseTargetType() {
        return this.advertiseTargetType;
    }

    public void setAdvertiseTargetType(String str) {
        this.advertiseTargetType = str;
    }

    public String getIdx() {
        return this.idx;
    }

    public void setIdx(String str) {
        this.idx = str;
    }

    public String getAdvertiseIndex() {
        return this.advertiseIndex;
    }

    public void setAdvertiseIndex(String str) {
        this.advertiseIndex = str;
    }

    public boolean isNeedLogin() {
        return this.isNeedLogin;
    }

    public void setNeedLogin(boolean z) {
        this.isNeedLogin = z;
    }

    public String getAdvertiseBackMode() {
        return this.advertiseBackMode;
    }

    public void setAdvertiseBackMode(String str) {
        this.advertiseBackMode = str;
    }

    public String getAdvJson() {
        return this.advJson;
    }

    public void setAdvJson(String str) {
        this.advJson = str;
    }

    public String getViceTitle() {
        return this.viceTitle;
    }

    public void setViceTitle(String str) {
        this.viceTitle = str;
    }

    public String getHandleNumber() {
        return this.handleNumber;
    }

    public void setHandleNumber(String str) {
        this.handleNumber = str;
    }

    public String getRightImgSrc() {
        return this.rightImgSrc;
    }

    public void setRightImgSrc(String str) {
        this.rightImgSrc = str;
    }

    public String getImageSrcVII() {
        return this.imageSrcVII;
    }

    public void setImageSrcVII(String str) {
        this.imageSrcVII = str;
    }

    public String getImageSrcVIIChecked() {
        return this.imageSrcVIIChecked;
    }

    public void setImageSrcVIIChecked(String str) {
        this.imageSrcVIIChecked = str;
    }

    public long getId() {
        return this.f18417id;
    }

    public void setId(long j) {
        this.f18417id = j;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String str) {
        this.cityCode = str;
    }

    public String getProvinceCode() {
        return this.provinceCode;
    }

    public void setProvinceCode(String str) {
        this.provinceCode = str;
    }

    public String getYwCode() {
        return this.ywCode;
    }

    public void setYwCode(String str) {
        this.ywCode = str;
    }

    public String getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(String str) {
        this.goodsId = str;
    }

    public String getActType() {
        return this.actType;
    }

    public void setActType(String str) {
        this.actType = str;
    }

    public String getActId() {
        return this.actId;
    }

    public void setActId(String str) {
        this.actId = str;
    }
}
