package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPFqNumInfoBean implements Parcelable {
    public static final Parcelable.Creator<WPFqNumInfoBean> CREATOR = new Parcelable.Creator<WPFqNumInfoBean>() { // from class: com.unicom.pay.normal.order.bean.WPFqNumInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPFqNumInfoBean createFromParcel(Parcel parcel) {
            return new WPFqNumInfoBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPFqNumInfoBean[] newArray(int i) {
            return new WPFqNumInfoBean[i];
        }
    };
    private boolean defaultChecked;
    private String expand;
    private String fqAmountMsg;
    private String fqFeeMsg;
    private String fqNum;
    private boolean recommend;

    public WPFqNumInfoBean() {
    }

    public WPFqNumInfoBean(Parcel parcel) {
        this.defaultChecked = parcel.readByte() != 0;
        this.recommend = parcel.readByte() != 0;
        this.expand = parcel.readString();
        this.fqAmountMsg = parcel.readString();
        this.fqFeeMsg = parcel.readString();
        this.fqNum = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getExpand() {
        return this.expand;
    }

    public String getFqAmountMsg() {
        return this.fqAmountMsg;
    }

    public String getFqFeeMsg() {
        return this.fqFeeMsg;
    }

    public String getFqNum() {
        return this.fqNum;
    }

    public boolean isDefaultChecked() {
        return this.defaultChecked;
    }

    public boolean isRecommend() {
        return this.recommend;
    }

    public void setDefaultChecked(boolean z) {
        this.defaultChecked = z;
    }

    public void setExpand(String str) {
        this.expand = str;
    }

    public void setFqAmountMsg(String str) {
        this.fqAmountMsg = str;
    }

    public void setFqFeeMsg(String str) {
        this.fqFeeMsg = str;
    }

    public void setFqNum(String str) {
        this.fqNum = str;
    }

    public void setRecommend(boolean z) {
        this.recommend = z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.defaultChecked ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.recommend ? (byte) 1 : (byte) 0);
        parcel.writeString(this.expand);
        parcel.writeString(this.fqAmountMsg);
        parcel.writeString(this.fqFeeMsg);
        parcel.writeString(this.fqNum);
    }
}
