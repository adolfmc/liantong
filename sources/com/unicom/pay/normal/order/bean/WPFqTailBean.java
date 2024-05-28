package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPFqTailBean implements Parcelable {
    public static final Parcelable.Creator<WPFqTailBean> CREATOR = new Parcelable.Creator<WPFqTailBean>() { // from class: com.unicom.pay.normal.order.bean.WPFqTailBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPFqTailBean createFromParcel(Parcel parcel) {
            return new WPFqTailBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPFqTailBean[] newArray(int i) {
            return new WPFqTailBean[i];
        }
    };
    private String apr;
    private String fqAgree;
    private String fqAgreeUrl;

    public WPFqTailBean() {
    }

    public WPFqTailBean(Parcel parcel) {
        this.fqAgree = parcel.readString();
        this.fqAgreeUrl = parcel.readString();
        this.apr = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getApr() {
        return this.apr;
    }

    public String getFqAgree() {
        return this.fqAgree;
    }

    public String getFqAgreeUrl() {
        return this.fqAgreeUrl;
    }

    public void setApr(String str) {
        this.apr = str;
    }

    public void setFqAgree(String str) {
        this.fqAgree = str;
    }

    public void setFqAgreeUrl(String str) {
        this.fqAgreeUrl = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.fqAgree);
        parcel.writeString(this.fqAgreeUrl);
        parcel.writeString(this.apr);
    }
}
