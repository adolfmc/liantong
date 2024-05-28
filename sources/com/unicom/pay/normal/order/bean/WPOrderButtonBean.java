package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPOrderButtonBean implements Parcelable {
    public static final String BTN_EVENT_TYPE_H5 = "h5";
    public static final String BTN_EVENT_TYPE_OPEN = "open";
    public static final String BTN_EVENT_TYPE_REQUEST = "request";
    public static final String BTN_OPEN_TYPE_BK = "TOBK";
    public static final String BTN_OPEN_TYPE_HBBK = "TOHBBK";
    public static final String BTN_OPEN_TYPE_PWD = "TOPWD";
    public static final String BTN_OPEN_TYPE_TOOLBK = "TOTOOLBK";
    public static final String BTN_OPEN_TYPE_TYBK = "TOTYBK";
    public static final String BTN_REQ_TYPE_OTHER_PAY = "1";
    public static final String BTN_REQ_TYPE_PAY_BEFORE = "0";
    public static final Parcelable.Creator<WPOrderButtonBean> CREATOR = new Parcelable.Creator<WPOrderButtonBean>() { // from class: com.unicom.pay.normal.order.bean.WPOrderButtonBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPOrderButtonBean createFromParcel(Parcel parcel) {
            return new WPOrderButtonBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPOrderButtonBean[] newArray(int i) {
            return new WPOrderButtonBean[i];
        }
    };
    private String buttonEventType;
    private String buttonInfo;
    private String buttonJumpUrl;
    private String buttonOpenType;
    private String buttonRequestType;
    private String buttonSDKType;

    public WPOrderButtonBean() {
    }

    public WPOrderButtonBean(Parcel parcel) {
        this.buttonInfo = parcel.readString();
        this.buttonEventType = parcel.readString();
        this.buttonRequestType = parcel.readString();
        this.buttonOpenType = parcel.readString();
        this.buttonSDKType = parcel.readString();
        this.buttonJumpUrl = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getButtonEventType() {
        return this.buttonEventType;
    }

    public String getButtonInfo() {
        return this.buttonInfo;
    }

    public String getButtonJumpUrl() {
        return this.buttonJumpUrl;
    }

    public String getButtonOpenType() {
        return this.buttonOpenType;
    }

    public String getButtonRequestType() {
        return this.buttonRequestType;
    }

    public String getButtonSDKType() {
        return this.buttonSDKType;
    }

    public void setButtonEventType(String str) {
        this.buttonEventType = str;
    }

    public void setButtonInfo(String str) {
        this.buttonInfo = str;
    }

    public void setButtonJumpUrl(String str) {
        this.buttonJumpUrl = str;
    }

    public void setButtonOpenType(String str) {
        this.buttonOpenType = str;
    }

    public void setButtonRequestType(String str) {
        this.buttonRequestType = str;
    }

    public void setButtonSDKType(String str) {
        this.buttonSDKType = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.buttonInfo);
        parcel.writeString(this.buttonEventType);
        parcel.writeString(this.buttonRequestType);
        parcel.writeString(this.buttonOpenType);
        parcel.writeString(this.buttonSDKType);
        parcel.writeString(this.buttonJumpUrl);
    }
}
