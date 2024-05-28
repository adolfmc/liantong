package com.sinovatech.unicom.basic.p315ui.home.entity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeKuanDaiDateEntity */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HomeKuanDaiDateEntity {
    private long currentTime = -1;
    private long displayTime = -1;
    private long bdFailureTime = -1;
    private String linkUrl = "";
    private String imageUrl = "";
    private boolean isSuccess = false;

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setSuccess(boolean z) {
        this.isSuccess = z;
    }

    public long getCurrentTime() {
        return this.currentTime;
    }

    public void setCurrentTime(long j) {
        this.currentTime = j;
    }

    public long getDisplayTime() {
        return this.displayTime;
    }

    public void setDisplayTime(long j) {
        this.displayTime = j;
    }

    public long getBdFailureTime() {
        return this.bdFailureTime;
    }

    public void setBdFailureTime(long j) {
        this.bdFailureTime = j;
    }

    public String getLinkUrl() {
        return this.linkUrl;
    }

    public void setLinkUrl(String str) {
        this.linkUrl = str;
    }
}
