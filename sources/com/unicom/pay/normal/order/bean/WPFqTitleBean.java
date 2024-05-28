package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPFqTitleBean implements Parcelable {
    public static final Parcelable.Creator<WPFqTitleBean> CREATOR = new Parcelable.Creator<WPFqTitleBean>() { // from class: com.unicom.pay.normal.order.bean.WPFqTitleBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPFqTitleBean createFromParcel(Parcel parcel) {
            return new WPFqTitleBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPFqTitleBean[] newArray(int i) {
            return new WPFqTitleBean[i];
        }
    };
    private String discountNext;
    private String iconUrl;
    private String methodDesc;
    private String methodDiscount;
    private String methodMsg;
    private String methodName;

    public WPFqTitleBean() {
    }

    public WPFqTitleBean(Parcel parcel) {
        this.iconUrl = parcel.readString();
        this.methodName = parcel.readString();
        this.methodDesc = parcel.readString();
        this.methodDiscount = parcel.readString();
        this.methodMsg = parcel.readString();
        this.discountNext = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getDiscountNext() {
        return this.discountNext;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public String getMethodDesc() {
        return this.methodDesc;
    }

    public String getMethodDiscount() {
        return this.methodDiscount;
    }

    public String getMethodMsg() {
        return this.methodMsg;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setDiscountNext(String str) {
        this.discountNext = str;
    }

    public void setIconUrl(String str) {
        this.iconUrl = str;
    }

    public void setMethodDesc(String str) {
        this.methodDesc = str;
    }

    public void setMethodDiscount(String str) {
        this.methodDiscount = str;
    }

    public void setMethodMsg(String str) {
        this.methodMsg = str;
    }

    public void setMethodName(String str) {
        this.methodName = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.iconUrl);
        parcel.writeString(this.methodName);
        parcel.writeString(this.methodDesc);
        parcel.writeString(this.methodDiscount);
        parcel.writeString(this.methodMsg);
        parcel.writeString(this.discountNext);
    }
}
