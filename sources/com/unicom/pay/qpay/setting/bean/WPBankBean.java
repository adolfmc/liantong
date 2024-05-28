package com.unicom.pay.qpay.setting.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPBankBean implements Parcelable {
    public static final Parcelable.Creator<WPBankBean> CREATOR = new Parcelable.Creator<WPBankBean>() { // from class: com.unicom.pay.qpay.setting.bean.WPBankBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPBankBean createFromParcel(Parcel parcel) {
            return new WPBankBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPBankBean[] newArray(int i) {
            return new WPBankBean[i];
        }
    };
    private String bankCode;
    private String bankName;
    private String bindingAgreementNo;
    private String bindingTime;

    /* renamed from: dc */
    private String f20415dc;
    private String expressAgreementNo;
    private String iconUrl;
    private String maskNum;
    private String payType;
    private String primary;

    public WPBankBean() {
    }

    public WPBankBean(Parcel parcel) {
        this.iconUrl = parcel.readString();
        this.bankCode = parcel.readString();
        this.bankName = parcel.readString();
        this.bindingAgreementNo = parcel.readString();
        this.bindingTime = parcel.readString();
        this.f20415dc = parcel.readString();
        this.expressAgreementNo = parcel.readString();
        this.maskNum = parcel.readString();
        this.payType = parcel.readString();
        this.primary = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getBankCode() {
        return this.bankCode;
    }

    public String getBankName() {
        return this.bankName;
    }

    public String getBindingAgreementNo() {
        return this.bindingAgreementNo;
    }

    public String getBindingTime() {
        return this.bindingTime;
    }

    public String getDc() {
        return this.f20415dc;
    }

    public String getExpressAgreementNo() {
        return this.expressAgreementNo;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public String getMaskNum() {
        return this.maskNum;
    }

    public String getPayType() {
        return this.payType;
    }

    public String getPrimary() {
        return this.primary;
    }

    public void setBankCode(String str) {
        this.bankCode = str;
    }

    public void setBankName(String str) {
        this.bankName = str;
    }

    public void setBindingAgreementNo(String str) {
        this.bindingAgreementNo = str;
    }

    public void setBindingTime(String str) {
        this.bindingTime = str;
    }

    public void setDc(String str) {
        this.f20415dc = str;
    }

    public void setExpressAgreementNo(String str) {
        this.expressAgreementNo = str;
    }

    public void setIconUrl(String str) {
        this.iconUrl = str;
    }

    public void setMaskNum(String str) {
        this.maskNum = str;
    }

    public void setPayType(String str) {
        this.payType = str;
    }

    public void setPrimary(String str) {
        this.primary = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.iconUrl);
        parcel.writeString(this.bankCode);
        parcel.writeString(this.bankName);
        parcel.writeString(this.bindingAgreementNo);
        parcel.writeString(this.bindingTime);
        parcel.writeString(this.f20415dc);
        parcel.writeString(this.expressAgreementNo);
        parcel.writeString(this.maskNum);
        parcel.writeString(this.payType);
        parcel.writeString(this.primary);
    }
}
