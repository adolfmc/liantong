package com.sinovatech.unicom.basic.p314po;

import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.po.HeaderEntity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HeaderEntity {
    private String NetNumberLogin;
    private String arrowUrl;
    private String askUrl;
    private String backImg;
    private String backImgSmall;
    private String backLinkdAddress;
    private String backLinkedTitle;
    private String backgroundImage;
    private String code;
    private String downButtonImageUrl;
    private String flushUpdateTime;
    private int flush_date_time_color;
    private HeaderConfigData headerConfigData;
    private List<HeaderChildEntity> headerEntityList;
    private String levelLinkedAddress;
    private String levelLinkedTitle;
    private String levelPic;
    private String mailUrl;
    private String mineImg;
    private String modifyTime;
    private String setButtonImageUrl;
    private String showFlower;
    private String showUserPicFlag;
    private String signinState;
    private String startLevel;
    private String subsidiaryNumberImages;
    private String subsidiaryNumberUrl;
    private int tone;
    private String upButtonImageUrl;

    public List<HeaderChildEntity> getHeaderEntityList() {
        if (this.headerEntityList == null) {
            this.headerEntityList = new ArrayList();
        }
        return this.headerEntityList;
    }

    public void setHeaderEntityList(List<HeaderChildEntity> list) {
        this.headerEntityList = list;
    }

    public String getAskUrl() {
        return this.askUrl;
    }

    public void setAskUrl(String str) {
        this.askUrl = str;
    }

    public String getFlushUpdateTime() {
        return this.flushUpdateTime;
    }

    public void setFlushUpdateTime(String str) {
        this.flushUpdateTime = str;
    }

    public String getLevelPic() {
        return this.levelPic;
    }

    public void setLevelPic(String str) {
        this.levelPic = str;
    }

    public String getShowUserPicFlag() {
        return this.showUserPicFlag;
    }

    public void setShowUserPicFlag(String str) {
        this.showUserPicFlag = str;
    }

    public String getBackLinkdAddress() {
        return this.backLinkdAddress;
    }

    public void setBackLinkdAddress(String str) {
        this.backLinkdAddress = str;
    }

    public String getBackImg() {
        return this.backImg;
    }

    public void setBackImg(String str) {
        this.backImg = str;
    }

    public String getBackImgSmall() {
        return this.backImgSmall;
    }

    public void setBackImgSmall(String str) {
        this.backImgSmall = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getBackLinkedTitle() {
        return this.backLinkedTitle;
    }

    public void setBackLinkedTitle(String str) {
        this.backLinkedTitle = str;
    }

    public String getLevelLinkedTitle() {
        return this.levelLinkedTitle;
    }

    public void setLevelLinkedTitle(String str) {
        this.levelLinkedTitle = str;
    }

    public String getLevelLinkedAddress() {
        return this.levelLinkedAddress;
    }

    public void setLevelLinkedAddress(String str) {
        this.levelLinkedAddress = str;
    }

    public String getShowFlower() {
        return this.showFlower;
    }

    public void setShowFlower(String str) {
        this.showFlower = str;
    }

    public String getSetButtonImageUrl() {
        return this.setButtonImageUrl;
    }

    public void setSetButtonImageUrl(String str) {
        this.setButtonImageUrl = str;
    }

    public String getUpButtonImageUrl() {
        return this.upButtonImageUrl;
    }

    public void setUpButtonImageUrl(String str) {
        this.upButtonImageUrl = str;
    }

    public String getDownButtonImageUrl() {
        return this.downButtonImageUrl;
    }

    public void setDownButtonImageUrl(String str) {
        this.downButtonImageUrl = str;
    }

    public int getFlush_date_time_color() {
        return this.flush_date_time_color;
    }

    public void setFlush_date_time_color(int i) {
        this.flush_date_time_color = i;
    }

    public HeaderConfigData getHeaderConfigData() {
        if (this.headerConfigData == null) {
            this.headerConfigData = new HeaderConfigData();
        }
        return this.headerConfigData;
    }

    public void setHeaderConfigData(HeaderConfigData headerConfigData) {
        this.headerConfigData = headerConfigData;
    }

    public String getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(String str) {
        this.modifyTime = str;
    }

    public String getSubsidiaryNumberImages() {
        return this.subsidiaryNumberImages;
    }

    public void setSubsidiaryNumberImages(String str) {
        this.subsidiaryNumberImages = str;
    }

    public String getSubsidiaryNumberUrl() {
        return this.subsidiaryNumberUrl;
    }

    public void setSubsidiaryNumberUrl(String str) {
        this.subsidiaryNumberUrl = str;
    }

    public String getMailUrl() {
        return this.mailUrl;
    }

    public void setMailUrl(String str) {
        this.mailUrl = str;
    }

    public String getArrowUrl() {
        return this.arrowUrl;
    }

    public void setArrowUrl(String str) {
        this.arrowUrl = str;
    }

    public String getMineImg() {
        return this.mineImg;
    }

    public void setMineImg(String str) {
        this.mineImg = str;
    }

    public String getNetNumberLogin() {
        return this.NetNumberLogin;
    }

    public void setNetNumberLogin(String str) {
        this.NetNumberLogin = str;
    }

    public String getSigninState() {
        return this.signinState;
    }

    public void setSigninState(String str) {
        this.signinState = str;
    }

    public int getTone() {
        return this.tone;
    }

    public void setTone(int i) {
        this.tone = i;
    }

    public String getBackgroundImage() {
        return this.backgroundImage;
    }

    public void setBackgroundImage(String str) {
        this.backgroundImage = str;
    }

    public String getStartLevel() {
        return this.startLevel;
    }

    public void setStartLevel(String str) {
        this.startLevel = str;
    }
}
