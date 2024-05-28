package com.unicom.pay.qpay.open.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPAgreementQPayTitleBean implements Parcelable {
    public static final Parcelable.Creator<WPAgreementQPayTitleBean> CREATOR = new Parcelable.Creator<WPAgreementQPayTitleBean>() { // from class: com.unicom.pay.qpay.open.bean.WPAgreementQPayTitleBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPAgreementQPayTitleBean createFromParcel(Parcel parcel) {
            return new WPAgreementQPayTitleBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPAgreementQPayTitleBean[] newArray(int i) {
            return new WPAgreementQPayTitleBean[i];
        }
    };
    private String firstTitleIcon;
    private String secondTitleIcon;
    private String title;

    public WPAgreementQPayTitleBean(Parcel parcel) {
        this.title = parcel.readString();
        this.firstTitleIcon = parcel.readString();
        this.secondTitleIcon = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getFirstTitleIcon() {
        return this.firstTitleIcon;
    }

    public String getSecondTitleIcon() {
        return this.secondTitleIcon;
    }

    public String getTitle() {
        return this.title;
    }

    public void setFirstTitleIcon(String str) {
        this.firstTitleIcon = str;
    }

    public void setSecondTitleIcon(String str) {
        this.secondTitleIcon = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.firstTitleIcon);
        parcel.writeString(this.secondTitleIcon);
    }
}
