package com.sinovatech.unicom.basic.p314po;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.po.AdvertiseEntity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AdvertiseEntity implements Serializable {
    private String actualType;
    private String adType;
    public String advCode;
    private String advJson;
    private String advertiseBackMode;
    public String advertiseContentType;
    public String advertiseId;
    public String advertiseImageURL;
    private String advertiseIndex;
    public String advertiseTargetType;
    public String advertiseTargetURL;
    public String advertiseTitle;
    public String advertiseType;
    private String codeId;
    private String codeId2;
    private List<DeitychopDataEntity> deitychopDataEntityList;
    private String diffType;
    private String discountPrice;
    private String h5Url;
    private String handleNumber;
    private String highlight;
    public String idx;
    private String imageSrcVII;
    private String imageSrcVIIChecked;
    public String intervalTime;
    public boolean isNeedLogin;
    private int percentNum;
    public String playCount;
    private String rightImgSrc;
    private String slogan;
    private String tabCode;
    private String tabDesc;
    private String tabTitle;
    private String tabUrl;
    public int titleColor;
    private String viceTitle;
    private String welcomeType;

    public String getAdvertiseBackMode() {
        return TextUtils.isEmpty(this.advertiseBackMode) ? "1" : this.advertiseBackMode;
    }

    public void setAdvertiseBackMode(String str) {
        this.advertiseBackMode = str;
    }

    public String getAdvertiseContentType() {
        return this.advertiseContentType;
    }

    public void setAdvertiseContentType(String str) {
        this.advertiseContentType = str;
    }

    public String getCodeId() {
        return this.codeId;
    }

    public void setCodeId(String str) {
        this.codeId = str;
    }

    public boolean isNeedLogin() {
        return this.isNeedLogin;
    }

    public void setNeedLogin(boolean z) {
        this.isNeedLogin = z;
    }

    public String getAdvertiseId() {
        return this.advertiseId;
    }

    public void setAdvertiseId(String str) {
        this.advertiseId = str;
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

    public String getAdvertiseType() {
        return this.advertiseType;
    }

    public void setAdvertiseType(String str) {
        this.advertiseType = str;
    }

    public String getAdvertiseTargetType() {
        return this.advertiseTargetType;
    }

    public void setAdvertiseTargetType(String str) {
        this.advertiseTargetType = str;
    }

    public String getIntervalTime() {
        return this.intervalTime;
    }

    public void setIntervalTime(String str) {
        this.intervalTime = str;
    }

    public String getAdvertiseIndex() {
        return this.advertiseIndex;
    }

    public void setAdvertiseIndex(String str) {
        this.advertiseIndex = str;
    }

    public int getTitleColor() {
        return this.titleColor;
    }

    public void setTitleColor(int i) {
        this.titleColor = i;
    }

    public String getIdx() {
        return this.idx;
    }

    public void setIdx(String str) {
        this.idx = str;
    }

    public String getPlayCount() {
        return this.playCount;
    }

    public void setPlayCount(String str) {
        this.playCount = str;
    }

    public String getAdvCode() {
        return this.advCode;
    }

    public void setAdvCode(String str) {
        this.advCode = str;
    }

    public String getHighlight() {
        return this.highlight;
    }

    public void setHighlight(String str) {
        this.highlight = str;
    }

    public String getAdvJson() {
        return this.advJson;
    }

    public void setAdvJson(String str) {
        this.advJson = str;
    }

    public String getViceTitle() {
        if ("null".equals(this.viceTitle)) {
            this.viceTitle = "";
        }
        return this.viceTitle;
    }

    public void setViceTitle(String str) {
        this.viceTitle = str;
    }

    public String getRightImgSrc() {
        return this.rightImgSrc;
    }

    public void setRightImgSrc(String str) {
        this.rightImgSrc = str;
    }

    public String getHandleNumber() {
        return this.handleNumber;
    }

    public void setHandleNumber(String str) {
        this.handleNumber = str;
    }

    public List<DeitychopDataEntity> getDeitychopDataEntityList() {
        return this.deitychopDataEntityList;
    }

    public void setDeitychopDataEntityList(List<DeitychopDataEntity> list) {
        this.deitychopDataEntityList = list;
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

    public String getSlogan() {
        return this.slogan;
    }

    public void setSlogan(String str) {
        this.slogan = str;
    }

    public String getDiffType() {
        return this.diffType;
    }

    public void setDiffType(String str) {
        this.diffType = str;
    }

    public String getDiscountPrice() {
        return this.discountPrice;
    }

    public void setDiscountPrice(String str) {
        this.discountPrice = str;
    }

    public String getWelcomeType() {
        return this.welcomeType;
    }

    public void setWelcomeType(String str) {
        this.welcomeType = str;
    }

    public String getActualType() {
        return this.actualType;
    }

    public void setActualType(String str) {
        this.actualType = str;
    }

    public String getTabCode() {
        return this.tabCode;
    }

    public void setTabCode(String str) {
        this.tabCode = str;
    }

    public String getTabTitle() {
        return this.tabTitle;
    }

    public void setTabTitle(String str) {
        this.tabTitle = str;
    }

    public String getTabDesc() {
        return this.tabDesc;
    }

    public void setTabDesc(String str) {
        this.tabDesc = str;
    }

    public String getTabUrl() {
        return this.tabUrl;
    }

    public void setTabUrl(String str) {
        this.tabUrl = str;
    }

    public String getCodeId2() {
        return this.codeId2;
    }

    public void setCodeId2(String str) {
        this.codeId2 = str;
    }

    public int getPercentNum() {
        return this.percentNum;
    }

    public void setPercentNum(int i) {
        this.percentNum = i;
    }

    public void setAdType(String str) {
        this.adType = str;
    }

    public String getAdType() {
        return this.adType;
    }

    public String getH5Url() {
        return this.h5Url;
    }

    public void setH5Url(String str) {
        this.h5Url = str;
    }
}
