package com.sinovatech.unicom.separatemodule.advtise.bean;

import android.text.TextUtils;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AdConfigEntity {
    private String acId;
    private String accountChannel;
    private String accountPassword;
    private String accountToken;
    private String accountUserName;
    private String action;
    private String adAction;
    private int adIntervals;
    private String adType;
    private String advCode;
    private String advertiseId;
    private String advertiseImageURL;
    private String advertiseTargetURL;
    private String advertiseTitle;
    private int bannerHeight;
    private int bannerWidth;
    private String channel;
    private String channelName;
    private String code;
    private String codeId;
    private String codeId2;
    private String detailTextString;
    private int direction;
    private String errorMessage;
    private String exposureUrl;
    private String gameId;
    private String gameSceneId;
    private int height;
    private String imageIconSrc;
    private String imageIconType;
    private boolean isOldVersion;
    private boolean isPrepareLoad;
    private String noCallUrl;
    private String num;
    private String orderId;
    private int percentNum;
    private String platId;
    private String remark;
    private boolean rewards;
    private double scale;
    private String screenId;
    private String subAdType;
    private String subCodeId;
    private String subFlag;
    private String taskId;
    private String taskType;
    private int timeOutSecond;
    private String timeflag;
    private boolean unWantedToast;
    private boolean unWantedToast2;
    private int waitTime;
    private String welcomeType;

    public String getChannel() {
        return "GGPD";
    }

    public int getWaitTime() {
        if (this.waitTime == 0) {
            this.waitTime = 500;
        }
        return this.waitTime;
    }

    public void setWaitTime(int i) {
        this.waitTime = i;
    }

    public boolean isOldVersion() {
        return this.isOldVersion;
    }

    public void setOldVersion(boolean z) {
        this.isOldVersion = z;
    }

    public boolean isPrepareLoad() {
        return this.isPrepareLoad;
    }

    public void setPrepareLoad(boolean z) {
        this.isPrepareLoad = z;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public String getAdAction() {
        return this.adAction;
    }

    public void setAdAction(String str) {
        this.adAction = str;
    }

    public String getAdType() {
        return this.adType;
    }

    public void setAdType(String str) {
        this.adType = str;
    }

    public String getAdvertiseTitle() {
        return this.advertiseTitle;
    }

    public void setAdvertiseTitle(String str) {
        this.advertiseTitle = str;
    }

    public String getAdvertiseImageURL() {
        return this.advertiseImageURL;
    }

    public void setAdvertiseImageURL(String str) {
        this.advertiseImageURL = str;
    }

    public String getAdvertiseId() {
        return this.advertiseId;
    }

    public void setAdvertiseId(String str) {
        this.advertiseId = str;
    }

    public String getWelcomeType() {
        return this.welcomeType;
    }

    public void setWelcomeType(String str) {
        this.welcomeType = str;
    }

    public String getAdvCode() {
        return this.advCode;
    }

    public void setAdvCode(String str) {
        this.advCode = str;
    }

    public String getAdvertiseTargetURL() {
        return this.advertiseTargetURL;
    }

    public void setAdvertiseTargetURL(String str) {
        this.advertiseTargetURL = str;
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

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public void setTaskType(String str) {
        this.taskType = str;
    }

    public void setChannel(String str) {
        this.channel = str;
    }

    public String getScreenId() {
        return this.screenId;
    }

    public void setScreenId(String str) {
        this.screenId = str;
    }

    public String getGameId() {
        return this.gameId;
    }

    public void setGameId(String str) {
        this.gameId = str;
    }

    public String getGameSceneId() {
        return this.gameSceneId;
    }

    public void setGameSceneId(String str) {
        this.gameSceneId = str;
    }

    public String getCodeId() {
        return this.codeId;
    }

    public void setCodeId(String str) {
        this.codeId = str;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public String getAcId() {
        return this.acId;
    }

    public void setAcId(String str) {
        this.acId = str;
    }

    public String getPlatId() {
        return this.platId;
    }

    public void setPlatId(String str) {
        this.platId = str;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String str) {
        this.remark = str;
    }

    public boolean isRewards() {
        return this.rewards;
    }

    public void setRewards(boolean z) {
        this.rewards = z;
    }

    public String getNum() {
        if (TextUtils.isEmpty(this.num)) {
            this.num = "0";
        }
        return this.num;
    }

    public int getTimeOutSecond() {
        return this.timeOutSecond;
    }

    public void setTimeOutSecond(int i) {
        this.timeOutSecond = i;
    }

    public void setNum(String str) {
        this.num = str;
    }

    public int getAdIntervals() {
        return this.adIntervals;
    }

    public void setAdIntervals(int i) {
        this.adIntervals = i;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public void setChannelName(String str) {
        this.channelName = str;
    }

    public boolean isUnWantedToast() {
        return this.unWantedToast;
    }

    public void setUnWantedToast(boolean z) {
        this.unWantedToast = z;
    }

    public int getBannerHeight() {
        return this.bannerHeight;
    }

    public void setBannerHeight(int i) {
        this.bannerHeight = i;
    }

    public int getBannerWidth() {
        return this.bannerWidth;
    }

    public void setBannerWidth(int i) {
        this.bannerWidth = i;
    }

    public boolean isUnWantedToast2() {
        return this.unWantedToast2;
    }

    public void setUnWantedToast2(boolean z) {
        this.unWantedToast2 = z;
    }

    public String getTimeflag() {
        return this.timeflag;
    }

    public void setTimeflag(String str) {
        this.timeflag = str;
    }

    public double getScale() {
        if (Double.isNaN(this.scale)) {
            return 1.0d;
        }
        if (this.scale < 0.5d) {
            this.scale = 1.0d;
        }
        return this.scale;
    }

    public void setScale(double d) {
        this.scale = d;
    }

    public String getAccountChannel() {
        return this.accountChannel;
    }

    public void setAccountChannel(String str) {
        this.accountChannel = str;
    }

    public String getAccountUserName() {
        return this.accountUserName;
    }

    public void setAccountUserName(String str) {
        this.accountUserName = str;
    }

    public String getAccountPassword() {
        return this.accountPassword;
    }

    public void setAccountPassword(String str) {
        this.accountPassword = str;
    }

    public String getAccountToken() {
        return this.accountToken;
    }

    public void setAccountToken(String str) {
        this.accountToken = str;
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirection(int i) {
        this.direction = i;
    }

    public String getMessage() {
        return this.errorMessage;
    }

    public void setMessage(String str) {
        this.errorMessage = str;
    }

    public String getNoCallUrl() {
        return this.noCallUrl;
    }

    public void setNoCallUrl(String str) {
        this.noCallUrl = str;
    }

    public String getExposureUrl() {
        return this.exposureUrl;
    }

    public void setExposureUrl(String str) {
        this.exposureUrl = str;
    }

    public String getImageIconSrc() {
        return this.imageIconSrc;
    }

    public void setImageIconSrc(String str) {
        this.imageIconSrc = str;
    }

    public String getImageIconType() {
        return this.imageIconType;
    }

    public void setImageIconType(String str) {
        this.imageIconType = str;
    }

    public String getDetailTextString() {
        return this.detailTextString;
    }

    public void setDetailTextString(String str) {
        this.detailTextString = str;
    }

    public String getSubCodeId() {
        return this.subCodeId;
    }

    public void setSubCodeId(String str) {
        this.subCodeId = str;
    }

    public String getSubAdType() {
        return this.subAdType;
    }

    public void setSubAdType(String str) {
        this.subAdType = str;
    }

    public String getSubFlag() {
        return this.subFlag;
    }

    public void setSubFlag(String str) {
        this.subFlag = str;
    }

    public String toString() {
        return "AdConfigEntity{advertiseTitle='" + this.advertiseTitle + "', advertiseImageURL='" + this.advertiseImageURL + "', advertiseId='" + this.advertiseId + "', welcomeType='" + this.welcomeType + "', advCode='" + this.advCode + "', advertiseTargetURL='" + this.advertiseTargetURL + "', codeId2='" + this.codeId2 + "', percentNum=" + this.percentNum + ", waitTime=" + this.waitTime + ", noCallUrl='" + this.noCallUrl + "', exposureUrl='" + this.exposureUrl + "', acId='" + this.acId + "', taskId='" + this.taskId + "', platId='" + this.platId + "', taskType='" + this.taskType + "', channel='" + this.channel + "', channelName='" + this.channelName + "', screenId='" + this.screenId + "', gameId='" + this.gameId + "', gameSceneId='" + this.gameSceneId + "', codeId='" + this.codeId + "', rewards=" + this.rewards + ", action='" + this.action + "', height=" + this.height + ", bannerHeight=" + this.bannerHeight + ", bannerWidth=" + this.bannerWidth + ", code='" + this.code + "', remark='" + this.remark + "', num='" + this.num + "', adIntervals=" + this.adIntervals + ", unWantedToast=" + this.unWantedToast + ", unWantedToast2=" + this.unWantedToast2 + ", timeflag='" + this.timeflag + "', scale=" + this.scale + ", timeOutSecond=" + this.timeOutSecond + ", errorMessage='" + this.errorMessage + "', accountChannel='" + this.accountChannel + "', accountUserName='" + this.accountUserName + "', accountPassword='" + this.accountPassword + "', accountToken='" + this.accountToken + "', adType='" + this.adType + "', adAction='" + this.adAction + "', isPrepareLoad=" + this.isPrepareLoad + ", orderId='" + this.orderId + "', direction=" + this.direction + ", isOldVersion=" + this.isOldVersion + ", imageIconSrc='" + this.imageIconSrc + "', detailTextString='" + this.detailTextString + "', imageIconType='" + this.imageIconType + "'}";
    }
}
