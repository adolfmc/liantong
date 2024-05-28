package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPCompleteUserInfoNotifyBean implements Parcelable {
    public static final Parcelable.Creator<WPCompleteUserInfoNotifyBean> CREATOR = new Parcelable.Creator<WPCompleteUserInfoNotifyBean>() { // from class: com.unicom.pay.normal.order.bean.WPCompleteUserInfoNotifyBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPCompleteUserInfoNotifyBean createFromParcel(Parcel parcel) {
            return new WPCompleteUserInfoNotifyBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPCompleteUserInfoNotifyBean[] newArray(int i) {
            return new WPCompleteUserInfoNotifyBean[i];
        }
    };
    private String scene;
    private String serialNo;

    public WPCompleteUserInfoNotifyBean(Parcel parcel) {
        this.serialNo = parcel.readString();
        this.scene = parcel.readString();
    }

    public WPCompleteUserInfoNotifyBean(String str, String str2) {
        this.serialNo = str;
        this.scene = str2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getScene() {
        return this.scene;
    }

    public String getSerialNo() {
        return this.serialNo;
    }

    public void setScene(String str) {
        this.scene = str;
    }

    public void setSerialNo(String str) {
        this.serialNo = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.serialNo);
        parcel.writeString(this.scene);
    }
}
