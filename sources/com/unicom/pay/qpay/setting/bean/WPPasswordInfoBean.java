package com.unicom.pay.qpay.setting.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPPasswordInfoBean implements Parcelable {
    public static final Parcelable.Creator<WPPasswordInfoBean> CREATOR = new Parcelable.Creator<WPPasswordInfoBean>() { // from class: com.unicom.pay.qpay.setting.bean.WPPasswordInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPPasswordInfoBean createFromParcel(Parcel parcel) {
            return new WPPasswordInfoBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPPasswordInfoBean[] newArray(int i) {
            return new WPPasswordInfoBean[i];
        }
    };
    private String pwdType;
    private String pwdUrl;

    public WPPasswordInfoBean() {
    }

    public WPPasswordInfoBean(Parcel parcel) {
        this.pwdType = parcel.readString();
        this.pwdUrl = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getPwdType() {
        return this.pwdType;
    }

    public String getPwdUrl() {
        return this.pwdUrl;
    }

    public void setPwdType(String str) {
        this.pwdType = str;
    }

    public void setPwdUrl(String str) {
        this.pwdUrl = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.pwdType);
        parcel.writeString(this.pwdUrl);
    }
}
