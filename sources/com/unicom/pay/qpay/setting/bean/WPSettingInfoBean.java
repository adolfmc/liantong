package com.unicom.pay.qpay.setting.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPSettingInfoBean implements Parcelable {
    public static final Parcelable.Creator<WPSettingInfoBean> CREATOR = new Parcelable.Creator<WPSettingInfoBean>() { // from class: com.unicom.pay.qpay.setting.bean.WPSettingInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPSettingInfoBean createFromParcel(Parcel parcel) {
            return new WPSettingInfoBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPSettingInfoBean[] newArray(int i) {
            return new WPSettingInfoBean[i];
        }
    };
    private String agreementName;
    private String agreementUrl;
    private List<WPBankBean> defaultPaymentList;
    private String isDefault;
    private String payLimit;
    private List<WPLimitBean> payLimitList;
    private String phoneNo;
    private String retentionUrl;
    private List<WPBankBean> userPaymentList;

    public WPSettingInfoBean() {
    }

    public WPSettingInfoBean(Parcel parcel) {
        this.isDefault = parcel.readString();
        Parcelable.Creator<WPBankBean> creator = WPBankBean.CREATOR;
        this.defaultPaymentList = parcel.createTypedArrayList(creator);
        this.userPaymentList = parcel.createTypedArrayList(creator);
        this.payLimit = parcel.readString();
        this.payLimitList = parcel.createTypedArrayList(WPLimitBean.CREATOR);
        this.agreementUrl = parcel.readString();
        this.agreementName = parcel.readString();
        this.phoneNo = parcel.readString();
        this.retentionUrl = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAgreementName() {
        return this.agreementName;
    }

    public String getAgreementUrl() {
        return this.agreementUrl;
    }

    public List<WPBankBean> getDefaultPaymentList() {
        return this.defaultPaymentList;
    }

    public String getIsDefault() {
        return this.isDefault;
    }

    public String getPayLimit() {
        return this.payLimit;
    }

    public List<WPLimitBean> getPayLimitList() {
        return this.payLimitList;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public String getRetentionUrl() {
        return this.retentionUrl;
    }

    public List<WPBankBean> getUserPaymentList() {
        return this.userPaymentList;
    }

    public void setAgreementName(String str) {
        this.agreementName = str;
    }

    public void setAgreementUrl(String str) {
        this.agreementUrl = str;
    }

    public void setDefaultPaymentList(List<WPBankBean> list) {
        this.defaultPaymentList = list;
    }

    public void setIsDefault(String str) {
        this.isDefault = str;
    }

    public void setPayLimit(String str) {
        this.payLimit = str;
    }

    public void setPayLimitList(List<WPLimitBean> list) {
        this.payLimitList = list;
    }

    public void setPhoneNo(String str) {
        this.phoneNo = str;
    }

    public void setRetentionUrl(String str) {
        this.retentionUrl = str;
    }

    public void setUserPaymentList(List<WPBankBean> list) {
        this.userPaymentList = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.isDefault);
        parcel.writeTypedList(this.defaultPaymentList);
        parcel.writeTypedList(this.userPaymentList);
        parcel.writeString(this.payLimit);
        parcel.writeTypedList(this.payLimitList);
        parcel.writeString(this.agreementUrl);
        parcel.writeString(this.agreementName);
        parcel.writeString(this.phoneNo);
        parcel.writeString(this.retentionUrl);
    }
}
