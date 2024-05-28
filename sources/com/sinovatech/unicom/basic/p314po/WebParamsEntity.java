package com.sinovatech.unicom.basic.p314po;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.po.WebParamsEntity */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class WebParamsEntity implements Parcelable {
    public static final Parcelable.Creator<WebParamsEntity> CREATOR = new Parcelable.Creator<WebParamsEntity>() { // from class: com.sinovatech.unicom.basic.po.WebParamsEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WebParamsEntity createFromParcel(Parcel parcel) {
            return new WebParamsEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WebParamsEntity[] newArray(int i) {
            return new WebParamsEntity[i];
        }
    };
    private String acId;
    private String backMode;
    private String coinAdUUID;
    private int indexHeadTabTimeout;
    private boolean isNeedTitle;
    private boolean isYule;
    private boolean miniProgramOpenUrlFlag = true;
    private String miniProgramUrl;
    private String navCode;
    private String navigateParamsUUID;
    private boolean noAppendParams;
    private String referer;
    private String requestType;
    private String taskId;
    private String title;
    private String type;
    private String url;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getReferer() {
        return this.referer;
    }

    public void setReferer(String str) {
        this.referer = str;
    }

    public String getMiniProgramUrl() {
        return this.miniProgramUrl;
    }

    public void setMiniProgramUrl(String str) {
        this.miniProgramUrl = str;
    }

    public String getNavCode() {
        return this.navCode;
    }

    public void setNavCode(String str) {
        this.navCode = str;
    }

    public boolean isMiniProgramOpenUrlFlag() {
        return this.miniProgramOpenUrlFlag;
    }

    public void setMiniProgramOpenUrlFlag(boolean z) {
        this.miniProgramOpenUrlFlag = z;
    }

    public int getIndexHeadTabTimeout() {
        return this.indexHeadTabTimeout;
    }

    public void setIndexHeadTabTimeout(int i) {
        this.indexHeadTabTimeout = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getBackMode() {
        return this.backMode;
    }

    public void setBackMode(String str) {
        this.backMode = str;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public void setRequestType(String str) {
        this.requestType = str;
    }

    public boolean isNeedTitle() {
        return this.isNeedTitle;
    }

    public void setNeedTitle(boolean z) {
        this.isNeedTitle = z;
    }

    public boolean isYule() {
        return this.isYule;
    }

    public void setYule(boolean z) {
        this.isYule = z;
    }

    public boolean isNoAppendParams() {
        return this.noAppendParams;
    }

    public void setNoAppendParams(boolean z) {
        this.noAppendParams = z;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getCoinAdUUID() {
        return this.coinAdUUID;
    }

    public void setCoinAdUUID(String str) {
        this.coinAdUUID = str;
    }

    public String getNavigateParamsUUID() {
        return this.navigateParamsUUID;
    }

    public void setNavigateParamsUUID(String str) {
        this.navigateParamsUUID = str;
    }

    public String getAcId() {
        return this.acId;
    }

    public void setAcId(String str) {
        this.acId = str;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public WebParamsEntity() {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.url);
        parcel.writeString(this.backMode);
        parcel.writeString(this.requestType);
        parcel.writeString(this.type);
        parcel.writeByte(this.isNeedTitle ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isYule ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.noAppendParams ? (byte) 1 : (byte) 0);
        parcel.writeString(this.coinAdUUID);
        parcel.writeString(this.acId);
        parcel.writeString(this.taskId);
        parcel.writeString(this.navigateParamsUUID);
        parcel.writeString(this.referer);
    }

    protected WebParamsEntity(Parcel parcel) {
        this.title = parcel.readString();
        this.url = parcel.readString();
        this.backMode = parcel.readString();
        this.requestType = parcel.readString();
        this.type = parcel.readString();
        this.isNeedTitle = parcel.readByte() != 0;
        this.isYule = parcel.readByte() != 0;
        this.noAppendParams = parcel.readByte() != 0;
        this.coinAdUUID = parcel.readString();
        this.acId = parcel.readString();
        this.taskId = parcel.readString();
        this.navigateParamsUUID = parcel.readString();
        this.referer = parcel.readString();
    }
}
