package com.unicom.pay.qpay.setting.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPLimitBean implements Parcelable {
    public static final Parcelable.Creator<WPLimitBean> CREATOR = new Parcelable.Creator<WPLimitBean>() { // from class: com.unicom.pay.qpay.setting.bean.WPLimitBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPLimitBean createFromParcel(Parcel parcel) {
            return new WPLimitBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPLimitBean[] newArray(int i) {
            return new WPLimitBean[i];
        }
    };
    private String payLimit;
    private String settingFlag;

    public WPLimitBean() {
    }

    public WPLimitBean(Parcel parcel) {
        this.payLimit = parcel.readString();
        this.settingFlag = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getPayLimit() {
        return this.payLimit;
    }

    public String getSettingFlag() {
        return this.settingFlag;
    }

    public void setPayLimit(String str) {
        this.payLimit = str;
    }

    public void setSettingFlag(String str) {
        this.settingFlag = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.payLimit);
        parcel.writeString(this.settingFlag);
    }
}
