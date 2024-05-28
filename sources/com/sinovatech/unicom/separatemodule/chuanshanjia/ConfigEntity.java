package com.sinovatech.unicom.separatemodule.chuanshanjia;

import android.text.TextUtils;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ConfigEntity {
    private String acId;
    private String accountChannel;
    private String accountPassword;
    private String accountToken;
    private String accountUserName;
    private String action;
    private int adIntervals;
    private int bannerHeight;
    private int bannerWidth;
    private String channel;
    private String channelName;
    private String code;
    private String codeId;
    private int direction;
    private String errorMessage;
    private String gameId;
    private String gameSceneId;
    private int height;
    private boolean isPrepareLoad;
    private String num;
    private String orderId;
    private String platId;
    private String remark;
    private String requestConfig;
    private boolean rewards;
    private double scale;
    private String screenId;
    private String taskId;
    private String taskType;
    private int timeOutSecond;
    private String timeflag;
    private boolean unWantedToast;
    private boolean unWantedToast2;

    public String getChannel() {
        return "GGPD";
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public boolean isPrepareLoad() {
        return this.isPrepareLoad;
    }

    public void setPrepareLoad(boolean z) {
        this.isPrepareLoad = z;
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

    public String getRequestConfig() {
        return this.requestConfig;
    }

    public void setRequestConfig(String str) {
        this.requestConfig = str;
    }

    public String toString() {
        return "ConfigEntity{codeId='" + this.codeId + "', height=" + this.height + ", bannerHeight=" + this.bannerHeight + ", bannerWidth=" + this.bannerWidth + '}';
    }
}
