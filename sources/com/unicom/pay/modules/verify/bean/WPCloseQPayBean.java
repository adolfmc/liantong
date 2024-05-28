package com.unicom.pay.modules.verify.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPCloseQPayBean implements Parcelable {
    public static final Parcelable.Creator<WPCloseQPayBean> CREATOR = new Parcelable.Creator<WPCloseQPayBean>() { // from class: com.unicom.pay.modules.verify.bean.WPCloseQPayBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPCloseQPayBean createFromParcel(Parcel parcel) {
            return new WPCloseQPayBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPCloseQPayBean[] newArray(int i) {
            return new WPCloseQPayBean[i];
        }
    };
    private String userNo;

    public WPCloseQPayBean() {
    }

    public WPCloseQPayBean(Parcel parcel) {
        this.userNo = parcel.readString();
    }

    public WPCloseQPayBean(String str) {
        this.userNo = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getUserNo() {
        return this.userNo;
    }

    public void setUserNo(String str) {
        this.userNo = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.userNo);
    }
}
