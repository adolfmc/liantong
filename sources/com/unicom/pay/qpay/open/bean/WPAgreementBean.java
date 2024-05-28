package com.unicom.pay.qpay.open.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPAgreementBean implements Parcelable {
    public static final Parcelable.Creator<WPAgreementBean> CREATOR = new Parcelable.Creator<WPAgreementBean>() { // from class: com.unicom.pay.qpay.open.bean.WPAgreementBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPAgreementBean createFromParcel(Parcel parcel) {
            return new WPAgreementBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPAgreementBean[] newArray(int i) {
            return new WPAgreementBean[i];
        }
    };
    private String agreementName;
    private String agreementUrl;
    private String marketText;
    private String phoneNo;
    private String qpayHelpUrl;
    private WPAgreementQPayTitleBean qpayTitle;
    private String setPrompt;
    private WPAgreementTitleBean setTitle1;
    private WPAgreementTitleBean setTitle2;
    private WPAgreementTitleBean setTitle3;
    private String userNo;

    public WPAgreementBean() {
    }

    public WPAgreementBean(Parcel parcel) {
        this.agreementName = parcel.readString();
        this.agreementUrl = parcel.readString();
        this.qpayHelpUrl = parcel.readString();
        this.phoneNo = parcel.readString();
        this.userNo = parcel.readString();
        this.qpayTitle = (WPAgreementQPayTitleBean) parcel.readParcelable(WPAgreementQPayTitleBean.class.getClassLoader());
        this.setPrompt = parcel.readString();
        this.setTitle1 = (WPAgreementTitleBean) parcel.readParcelable(WPAgreementTitleBean.class.getClassLoader());
        this.setTitle2 = (WPAgreementTitleBean) parcel.readParcelable(WPAgreementTitleBean.class.getClassLoader());
        this.setTitle3 = (WPAgreementTitleBean) parcel.readParcelable(WPAgreementTitleBean.class.getClassLoader());
        this.marketText = parcel.readString();
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

    public String getMarketText() {
        return this.marketText;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public String getQpayHelpUrl() {
        return this.qpayHelpUrl;
    }

    public WPAgreementQPayTitleBean getQpayTitle() {
        return this.qpayTitle;
    }

    public String getSetPrompt() {
        return this.setPrompt;
    }

    public WPAgreementTitleBean getSetTitle1() {
        return this.setTitle1;
    }

    public WPAgreementTitleBean getSetTitle2() {
        return this.setTitle2;
    }

    public WPAgreementTitleBean getSetTitle3() {
        return this.setTitle3;
    }

    public String getUserNo() {
        return this.userNo;
    }

    public void setAgreementName(String str) {
        this.agreementName = str;
    }

    public void setAgreementUrl(String str) {
        this.agreementUrl = str;
    }

    public void setMarketText(String str) {
        this.marketText = str;
    }

    public void setPhoneNo(String str) {
        this.phoneNo = str;
    }

    public void setQpayHelpUrl(String str) {
        this.qpayHelpUrl = str;
    }

    public void setQpayTitle(WPAgreementQPayTitleBean wPAgreementQPayTitleBean) {
        this.qpayTitle = wPAgreementQPayTitleBean;
    }

    public void setSetPrompt(String str) {
        this.setPrompt = str;
    }

    public void setSetTitle1(WPAgreementTitleBean wPAgreementTitleBean) {
        this.setTitle1 = wPAgreementTitleBean;
    }

    public void setSetTitle2(WPAgreementTitleBean wPAgreementTitleBean) {
        this.setTitle2 = wPAgreementTitleBean;
    }

    public void setSetTitle3(WPAgreementTitleBean wPAgreementTitleBean) {
        this.setTitle3 = wPAgreementTitleBean;
    }

    public void setUserNo(String str) {
        this.userNo = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.agreementName);
        parcel.writeString(this.agreementUrl);
        parcel.writeString(this.qpayHelpUrl);
        parcel.writeString(this.phoneNo);
        parcel.writeString(this.userNo);
        parcel.writeParcelable(this.qpayTitle, i);
        parcel.writeString(this.setPrompt);
        parcel.writeParcelable(this.setTitle1, i);
        parcel.writeParcelable(this.setTitle2, i);
        parcel.writeParcelable(this.setTitle3, i);
        parcel.writeString(this.marketText);
    }
}
