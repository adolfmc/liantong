package com.sinovatech.unicom.separatemodule.advtise.bean;

import com.bytedance.sdk.openadsdk.TTRewardVideoAd;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class VideoCacheEntity {
    private String orderId;
    private TTRewardVideoAd rewardVideoAd;
    private long time;

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public TTRewardVideoAd getRewardVideoAd() {
        return this.rewardVideoAd;
    }

    public void setRewardVideoAd(TTRewardVideoAd tTRewardVideoAd) {
        this.rewardVideoAd = tTRewardVideoAd;
    }
}
