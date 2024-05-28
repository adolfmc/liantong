package com.sinovatech.unicom.separatemodule.audience.entity;

import android.text.TextUtils;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PlayBackPayInfoEntity {
    private String freeTime;
    private int freeTime4MillisSeconds;
    private String paidAd;
    private String paidId;
    private String paidLiveBackPlay;
    private String payUrl;
    private String promptText;
    private String tryLook;
    private String userIsPaid;

    public int getFreeTime4MillisSeconds() {
        return this.freeTime4MillisSeconds;
    }

    public void setFreeTime4MillisSeconds(int i) {
        this.freeTime4MillisSeconds = i;
    }

    public String getPaidAd() {
        return TextUtils.isEmpty(this.paidAd) ? "应版权方要求，本内容需购买观看" : this.paidAd;
    }

    public void setPaidAd(String str) {
        this.paidAd = str;
    }

    public String getPaidId() {
        return this.paidId;
    }

    public void setPaidId(String str) {
        this.paidId = str;
    }

    public String getPayUrl() {
        return this.payUrl;
    }

    public void setPayUrl(String str) {
        this.payUrl = str;
    }

    public String getPaidLiveBackPlay() {
        return this.paidLiveBackPlay;
    }

    public void setPaidLiveBackPlay(String str) {
        this.paidLiveBackPlay = str;
    }

    public String getTryLook() {
        return this.tryLook;
    }

    public void setTryLook(String str) {
        this.tryLook = str;
    }

    public String getFreeTime() {
        return this.freeTime;
    }

    public void setFreeTime(String str) {
        this.freeTime = str;
        try {
            setFreeTime4MillisSeconds(Integer.parseInt(str) * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUserIsPaid() {
        return this.userIsPaid;
    }

    public void setUserIsPaid(String str) {
        this.userIsPaid = str;
    }

    public String getPromptText() {
        return this.promptText;
    }

    public void setPromptText(String str) {
        this.promptText = str;
    }
}
