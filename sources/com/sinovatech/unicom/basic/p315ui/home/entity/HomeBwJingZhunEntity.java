package com.sinovatech.unicom.basic.p315ui.home.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeBwJingZhunEntity */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HomeBwJingZhunEntity {
    private String actId;
    private String actType;
    private String basemap;
    private String basemapColor;
    private String beanId;
    private String businessType;
    private String createTime;
    private String createUser;
    private int defImg;
    private String defaultRecommend;
    private String fontNumber;
    private String fontNumberMark;
    private String fontNumberUnit;
    private String goodsId;
    private String goodsStatus;
    private String goodsType;
    private String goodsUrl;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18413id;
    private String imgSrc;
    private String imgType;
    private boolean isCustomData = false;
    private String isMarketLineFlag;
    private String isOtherNet;
    private String marketCapsuleImgSrc;
    private String marketCapsuleTitle;
    private String mobile;
    private String needLogin;
    private String position;
    private String productActualType;
    private String ruleId;
    private String source;
    private String speed;
    private String title;
    private String updateTime;
    private String updateUser;
    private String viceTitle;
    private String wxId;

    public String getSpeed() {
        return this.speed;
    }

    public void setSpeed(String str) {
        this.speed = str;
    }

    public String getImgType() {
        return this.imgType;
    }

    public void setImgType(String str) {
        this.imgType = str;
    }

    public String getFontNumberMark() {
        return this.fontNumberMark;
    }

    public void setFontNumberMark(String str) {
        this.fontNumberMark = str;
    }

    public String getFontNumber() {
        return this.fontNumber;
    }

    public void setFontNumber(String str) {
        this.fontNumber = str;
    }

    public String getFontNumberUnit() {
        return this.fontNumberUnit;
    }

    public void setFontNumberUnit(String str) {
        this.fontNumberUnit = str;
    }

    public String getBasemap() {
        return this.basemap;
    }

    public void setBasemap(String str) {
        this.basemap = str;
    }

    public String getBasemapColor() {
        return this.basemapColor;
    }

    public void setBasemapColor(String str) {
        this.basemapColor = str;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String str) {
        this.position = str;
    }

    public boolean isCustomData() {
        return this.isCustomData;
    }

    public void setCustomData(boolean z) {
        this.isCustomData = z;
    }

    public int getDefImg() {
        return this.defImg;
    }

    public void setDefImg(int i) {
        this.defImg = i;
    }

    public long getId() {
        return this.f18413id;
    }

    public void setId(long j) {
        this.f18413id = j;
    }

    public String getBeanId() {
        return this.beanId;
    }

    public void setBeanId(String str) {
        this.beanId = str;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public String getProductActualType() {
        return this.productActualType;
    }

    public void setProductActualType(String str) {
        this.productActualType = str;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public String getDefaultRecommend() {
        return this.defaultRecommend;
    }

    public void setDefaultRecommend(String str) {
        this.defaultRecommend = str;
    }

    public String getViceTitle() {
        return this.viceTitle;
    }

    public void setViceTitle(String str) {
        this.viceTitle = str;
    }

    public String getRuleId() {
        return this.ruleId;
    }

    public void setRuleId(String str) {
        this.ruleId = str;
    }

    public String getGoodsType() {
        return this.goodsType;
    }

    public void setGoodsType(String str) {
        this.goodsType = str;
    }

    public String getGoodsUrl() {
        return this.goodsUrl;
    }

    public void setGoodsUrl(String str) {
        this.goodsUrl = str;
    }

    public String getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(String str) {
        this.goodsId = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getActType() {
        return this.actType;
    }

    public void setActType(String str) {
        this.actType = str;
    }

    public String getIsOtherNet() {
        return this.isOtherNet;
    }

    public void setIsOtherNet(String str) {
        this.isOtherNet = str;
    }

    public String getNeedLogin() {
        return this.needLogin;
    }

    public void setNeedLogin(String str) {
        this.needLogin = str;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(String str) {
        this.updateUser = str;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String str) {
        this.updateTime = str;
    }

    public String getGoodsStatus() {
        return this.goodsStatus;
    }

    public void setGoodsStatus(String str) {
        this.goodsStatus = str;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String str) {
        this.createUser = str;
    }

    public String getBusinessType() {
        return this.businessType;
    }

    public void setBusinessType(String str) {
        this.businessType = str;
    }

    public String getImgSrc() {
        return this.imgSrc;
    }

    public void setImgSrc(String str) {
        this.imgSrc = str;
    }

    public String getActId() {
        return this.actId;
    }

    public void setActId(String str) {
        this.actId = str;
    }

    public String getWxId() {
        return this.wxId;
    }

    public void setWxId(String str) {
        this.wxId = str;
    }

    public String getIsMarketLineFlag() {
        return this.isMarketLineFlag;
    }

    public void setIsMarketLineFlag(String str) {
        this.isMarketLineFlag = str;
    }

    public String getMarketCapsuleTitle() {
        return this.marketCapsuleTitle;
    }

    public void setMarketCapsuleTitle(String str) {
        this.marketCapsuleTitle = str;
    }

    public String getMarketCapsuleImgSrc() {
        return this.marketCapsuleImgSrc;
    }

    public void setMarketCapsuleImgSrc(String str) {
        this.marketCapsuleImgSrc = str;
    }
}
