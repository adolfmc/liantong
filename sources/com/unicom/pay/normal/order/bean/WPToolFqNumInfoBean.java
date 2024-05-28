package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPToolFqNumInfoBean implements Parcelable {
    public static final Parcelable.Creator<WPToolFqNumInfoBean> CREATOR = new Parcelable.Creator<WPToolFqNumInfoBean>() { // from class: com.unicom.pay.normal.order.bean.WPToolFqNumInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPToolFqNumInfoBean createFromParcel(Parcel parcel) {
            return new WPToolFqNumInfoBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPToolFqNumInfoBean[] newArray(int i) {
            return new WPToolFqNumInfoBean[i];
        }
    };
    private String canUsed;
    private String checked;
    private String fqAmountMsg;
    private String fqFeeInfo;
    private String fqNum;
    private String fqNumExpand;

    public WPToolFqNumInfoBean() {
    }

    public WPToolFqNumInfoBean(Parcel parcel) {
        this.fqNum = parcel.readString();
        this.fqAmountMsg = parcel.readString();
        this.fqFeeInfo = parcel.readString();
        this.checked = parcel.readString();
        this.fqNumExpand = parcel.readString();
        this.canUsed = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCanUsed() {
        return this.canUsed;
    }

    public String getChecked() {
        return this.checked;
    }

    public String getFqAmountMsg() {
        return this.fqAmountMsg;
    }

    public String getFqFeeInfo() {
        return this.fqFeeInfo;
    }

    public String getFqNum() {
        return this.fqNum;
    }

    public String getFqNumExpand() {
        return this.fqNumExpand;
    }

    public boolean isChecked() {
        return "1".equals(this.checked);
    }

    public void setCanUsed(String str) {
        this.canUsed = str;
    }

    public void setChecked(String str) {
        this.checked = str;
    }

    public void setFqAmountMsg(String str) {
        this.fqAmountMsg = str;
    }

    public void setFqFeeInfo(String str) {
        this.fqFeeInfo = str;
    }

    public void setFqNum(String str) {
        this.fqNum = str;
    }

    public void setFqNumExpand(String str) {
        this.fqNumExpand = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.fqNum);
        parcel.writeString(this.fqAmountMsg);
        parcel.writeString(this.fqFeeInfo);
        parcel.writeString(this.checked);
        parcel.writeString(this.fqNumExpand);
        parcel.writeString(this.canUsed);
    }
}
