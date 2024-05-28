package com.unicom.pay.normal.discount.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPDiscountDetailBean implements Parcelable {
    public static final Parcelable.Creator<WPDiscountDetailBean> CREATOR = new Parcelable.Creator<WPDiscountDetailBean>() { // from class: com.unicom.pay.normal.discount.bean.WPDiscountDetailBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPDiscountDetailBean createFromParcel(Parcel parcel) {
            return new WPDiscountDetailBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPDiscountDetailBean[] newArray(int i) {
            return new WPDiscountDetailBean[i];
        }
    };
    private String amountDesc;
    private String bkDzq;
    private String canCheck;
    private String checked;
    private String discountAmount;
    private String discountDesc;
    private String discountExpand;
    private String discountTitle;
    private String dzqDesc;
    private String dzqFaceAmt;
    private String marketSubscript;
    private String validityDate;

    public WPDiscountDetailBean() {
    }

    public WPDiscountDetailBean(Parcel parcel) {
        this.marketSubscript = parcel.readString();
        this.discountTitle = parcel.readString();
        this.discountDesc = parcel.readString();
        this.checked = parcel.readString();
        this.discountAmount = parcel.readString();
        this.discountExpand = parcel.readString();
        this.canCheck = parcel.readString();
        this.amountDesc = parcel.readString();
        this.validityDate = parcel.readString();
        this.bkDzq = parcel.readString();
        this.dzqFaceAmt = parcel.readString();
        this.dzqDesc = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAmountDesc() {
        return this.amountDesc;
    }

    public String getBkDzq() {
        return this.bkDzq;
    }

    public String getCanCheck() {
        return this.canCheck;
    }

    public String getChecked() {
        return this.checked;
    }

    public String getDiscountAmount() {
        return this.discountAmount;
    }

    public String getDiscountDesc() {
        return this.discountDesc;
    }

    public String getDiscountExpand() {
        return this.discountExpand;
    }

    public String getDiscountTitle() {
        return this.discountTitle;
    }

    public String getDzqDesc() {
        return this.dzqDesc;
    }

    public String getDzqFaceAmt() {
        return this.dzqFaceAmt;
    }

    public String getMarketSubscript() {
        return this.marketSubscript;
    }

    public String getValidityDate() {
        return this.validityDate;
    }

    public boolean isChecked() {
        return "1".equals(this.checked);
    }

    public void setAmountDesc(String str) {
        this.amountDesc = str;
    }

    public void setBkDzq(String str) {
        this.bkDzq = str;
    }

    public void setCanCheck(String str) {
        this.canCheck = str;
    }

    public void setChecked(String str) {
        this.checked = str;
    }

    public void setDiscountAmount(String str) {
        this.discountAmount = str;
    }

    public void setDiscountDesc(String str) {
        this.discountDesc = str;
    }

    public void setDiscountExpand(String str) {
        this.discountExpand = str;
    }

    public void setDiscountTitle(String str) {
        this.discountTitle = str;
    }

    public void setDzqDesc(String str) {
        this.dzqDesc = str;
    }

    public void setDzqFaceAmt(String str) {
        this.dzqFaceAmt = str;
    }

    public void setMarketSubscript(String str) {
        this.marketSubscript = str;
    }

    public void setValidityDate(String str) {
        this.validityDate = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.marketSubscript);
        parcel.writeString(this.discountTitle);
        parcel.writeString(this.discountDesc);
        parcel.writeString(this.checked);
        parcel.writeString(this.discountAmount);
        parcel.writeString(this.discountExpand);
        parcel.writeString(this.canCheck);
        parcel.writeString(this.amountDesc);
        parcel.writeString(this.validityDate);
        parcel.writeString(this.bkDzq);
        parcel.writeString(this.dzqFaceAmt);
        parcel.writeString(this.dzqDesc);
    }
}
