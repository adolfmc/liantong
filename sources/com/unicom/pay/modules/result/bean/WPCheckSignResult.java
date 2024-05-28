package com.unicom.pay.modules.result.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPCheckSignResult implements Parcelable {
    public static final Parcelable.Creator<WPCheckSignResult> CREATOR = new Parcelable.Creator<WPCheckSignResult>() { // from class: com.unicom.pay.modules.result.bean.WPCheckSignResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPCheckSignResult createFromParcel(Parcel parcel) {
            return new WPCheckSignResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPCheckSignResult[] newArray(int i) {
            return new WPCheckSignResult[i];
        }
    };
    public static final String NOT = "0";
    public static final String NOT_SAME = "1";
    public static final String ORDER_FINISHED = "2";
    public static final String ORDER_PAYED = "3";
    public static final String UN_PAY = "4";
    public static final String UN_PWD = "6";
    private String marketText;
    private int payLimit;
    private String phoneNo;
    private String pwdUrl;
    private String status;
    private String userNo;

    public WPCheckSignResult() {
    }

    public WPCheckSignResult(Parcel parcel) {
        this.status = parcel.readString();
        this.payLimit = parcel.readInt();
        this.marketText = parcel.readString();
        this.userNo = parcel.readString();
        this.phoneNo = parcel.readString();
        this.pwdUrl = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getMarketText() {
        return this.marketText;
    }

    public int getPayLimit() {
        return this.payLimit;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public String getPwdUrl() {
        return this.pwdUrl;
    }

    public String getStatus() {
        return this.status;
    }

    public String getUserNo() {
        return this.userNo;
    }

    public void setMarketText(String str) {
        this.marketText = str;
    }

    public void setPayLimit(int i) {
        this.payLimit = i;
    }

    public void setPhoneNo(String str) {
        this.phoneNo = str;
    }

    public void setPwdUrl(String str) {
        this.pwdUrl = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setUserNo(String str) {
        this.userNo = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.status);
        parcel.writeInt(this.payLimit);
        parcel.writeString(this.marketText);
        parcel.writeString(this.userNo);
        parcel.writeString(this.phoneNo);
        parcel.writeString(this.pwdUrl);
    }
}
