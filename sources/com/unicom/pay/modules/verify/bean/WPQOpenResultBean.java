package com.unicom.pay.modules.verify.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPQOpenResultBean implements Parcelable {
    public static final Parcelable.Creator<WPQOpenResultBean> CREATOR = new Parcelable.Creator<WPQOpenResultBean>() { // from class: com.unicom.pay.modules.verify.bean.WPQOpenResultBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPQOpenResultBean createFromParcel(Parcel parcel) {
            return new WPQOpenResultBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPQOpenResultBean[] newArray(int i) {
            return new WPQOpenResultBean[i];
        }
    };
    public static final String RISK_SMS = "001";
    private String payToken;
    private String phoneNo;
    private String signResultCode;
    private String signResultMsg;
    private String signTokenId;
    private String userNo;

    public WPQOpenResultBean() {
    }

    public WPQOpenResultBean(Parcel parcel) {
        this.payToken = parcel.readString();
        this.signResultCode = parcel.readString();
        this.signResultMsg = parcel.readString();
        this.signTokenId = parcel.readString();
        this.userNo = parcel.readString();
        this.phoneNo = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getPayToken() {
        return this.payToken;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public String getSignResultCode() {
        return this.signResultCode;
    }

    public String getSignResultMsg() {
        return this.signResultMsg;
    }

    public String getSignTokenId() {
        return this.signTokenId;
    }

    public String getUserNo() {
        return this.userNo;
    }

    public boolean isSignSuccess() {
        return "004".equals(this.signResultCode);
    }

    public void setPayToken(String str) {
        this.payToken = str;
    }

    public void setPhoneNo(String str) {
        this.phoneNo = str;
    }

    public void setSignResultCode(String str) {
        this.signResultCode = str;
    }

    public void setSignResultMsg(String str) {
        this.signResultMsg = str;
    }

    public void setSignTokenId(String str) {
        this.signTokenId = str;
    }

    public void setUserNo(String str) {
        this.userNo = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.payToken);
        parcel.writeString(this.signResultCode);
        parcel.writeString(this.signResultMsg);
        parcel.writeString(this.signTokenId);
        parcel.writeString(this.userNo);
        parcel.writeString(this.phoneNo);
    }
}
