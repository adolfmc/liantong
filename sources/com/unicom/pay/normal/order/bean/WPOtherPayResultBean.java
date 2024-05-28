package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPOtherPayResultBean implements Parcelable {
    public static final Parcelable.Creator<WPOtherPayResultBean> CREATOR = new Parcelable.Creator<WPOtherPayResultBean>() { // from class: com.unicom.pay.normal.order.bean.WPOtherPayResultBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPOtherPayResultBean createFromParcel(Parcel parcel) {
            return new WPOtherPayResultBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPOtherPayResultBean[] newArray(int i) {
            return new WPOtherPayResultBean[i];
        }
    };
    public static final String H5_TYPE = "0";
    public static final String SDK_TYPE = "1";
    private String otherPayEnv;
    private String payUrl;
    private String referer;

    public WPOtherPayResultBean() {
    }

    public WPOtherPayResultBean(Parcel parcel) {
        this.payUrl = parcel.readString();
        this.referer = parcel.readString();
        this.otherPayEnv = parcel.readString();
    }

    public WPOtherPayResultBean(String str, String str2, String str3) {
        this.payUrl = str;
        this.referer = str2;
        this.otherPayEnv = str3;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getOtherPayEnv() {
        return this.otherPayEnv;
    }

    public String getPayUrl() {
        return this.payUrl;
    }

    public String getReferer() {
        return this.referer;
    }

    public void setOtherPayEnv(String str) {
        this.otherPayEnv = str;
    }

    public void setPayUrl(String str) {
        this.payUrl = str;
    }

    public void setReferer(String str) {
        this.referer = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.payUrl);
        parcel.writeString(this.referer);
        parcel.writeString(this.otherPayEnv);
    }
}
