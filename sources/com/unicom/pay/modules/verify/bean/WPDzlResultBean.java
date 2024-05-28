package com.unicom.pay.modules.verify.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPDzlResultBean implements Parcelable {
    public static final Parcelable.Creator<WPDzlResultBean> CREATOR = new Parcelable.Creator<WPDzlResultBean>() { // from class: com.unicom.pay.modules.verify.bean.WPDzlResultBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPDzlResultBean createFromParcel(Parcel parcel) {
            return new WPDzlResultBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPDzlResultBean[] newArray(int i) {
            return new WPDzlResultBean[i];
        }
    };
    private String bcmcCardSn;
    private String cdKey;
    private String paymentSn;

    public WPDzlResultBean(Parcel parcel) {
        this.bcmcCardSn = parcel.readString();
        this.paymentSn = parcel.readString();
        this.cdKey = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getBcmcCardSn() {
        return this.bcmcCardSn;
    }

    public String getCdKey() {
        return this.cdKey;
    }

    public String getPaymentSn() {
        return this.paymentSn;
    }

    public void setBcmcCardSn(String str) {
        this.bcmcCardSn = str;
    }

    public void setCdKey(String str) {
        this.cdKey = str;
    }

    public void setPaymentSn(String str) {
        this.paymentSn = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.bcmcCardSn);
        parcel.writeString(this.paymentSn);
        parcel.writeString(this.cdKey);
    }
}
