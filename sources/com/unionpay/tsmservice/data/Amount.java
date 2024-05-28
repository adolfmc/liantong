package com.unionpay.tsmservice.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class Amount implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.data.Amount.1
        @Override // android.os.Parcelable.Creator
        public final Amount createFromParcel(Parcel parcel) {
            return new Amount(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final Amount[] newArray(int i) {
            return new Amount[i];
        }
    };
    private String mCurrencyType;
    private String mProductPrice;

    public Amount() {
        this.mCurrencyType = "CNY";
        this.mProductPrice = "0.0";
    }

    public Amount(Parcel parcel) {
        this.mCurrencyType = "CNY";
        this.mProductPrice = "0.0";
        this.mCurrencyType = parcel.readString();
        this.mProductPrice = parcel.readString();
    }

    public Amount(String str, String str2) {
        this.mCurrencyType = "CNY";
        this.mProductPrice = "0.0";
        this.mCurrencyType = str;
        this.mProductPrice = str2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCurrencyType() {
        return this.mCurrencyType;
    }

    public String getProductPrice() {
        return this.mProductPrice;
    }

    public void setCurrencyType(String str) {
        this.mCurrencyType = str;
    }

    public void setProductPrice(String str) {
        this.mProductPrice = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mCurrencyType);
        parcel.writeString(this.mProductPrice);
    }
}
