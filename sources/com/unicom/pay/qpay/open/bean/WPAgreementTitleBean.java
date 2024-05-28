package com.unicom.pay.qpay.open.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPAgreementTitleBean implements Parcelable {
    public static final Parcelable.Creator<WPAgreementTitleBean> CREATOR = new Parcelable.Creator<WPAgreementTitleBean>() { // from class: com.unicom.pay.qpay.open.bean.WPAgreementTitleBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPAgreementTitleBean createFromParcel(Parcel parcel) {
            return new WPAgreementTitleBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPAgreementTitleBean[] newArray(int i) {
            return new WPAgreementTitleBean[i];
        }
    };
    private String iconUrl;
    private String title;
    private String titleDesc;

    public WPAgreementTitleBean(Parcel parcel) {
        this.iconUrl = parcel.readString();
        this.title = parcel.readString();
        this.titleDesc = parcel.readString();
    }

    public WPAgreementTitleBean(String str, String str2, String str3) {
        this.iconUrl = str;
        this.title = str2;
        this.titleDesc = str3;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTitleDesc() {
        return this.titleDesc;
    }

    public void setIconUrl(String str) {
        this.iconUrl = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTitleDesc(String str) {
        this.titleDesc = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.iconUrl);
        parcel.writeString(this.title);
        parcel.writeString(this.titleDesc);
    }
}
