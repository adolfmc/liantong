package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPPayInfoUpdateBean implements Parcelable {
    public static final Parcelable.Creator<WPPayInfoUpdateBean> CREATOR = new Parcelable.Creator<WPPayInfoUpdateBean>() { // from class: com.unicom.pay.normal.order.bean.WPPayInfoUpdateBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPPayInfoUpdateBean createFromParcel(Parcel parcel) {
            return new WPPayInfoUpdateBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPPayInfoUpdateBean[] newArray(int i) {
            return new WPPayInfoUpdateBean[i];
        }
    };
    private String toolExpand;

    public WPPayInfoUpdateBean() {
    }

    public WPPayInfoUpdateBean(Parcel parcel) {
        this.toolExpand = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getToolExpand() {
        return this.toolExpand;
    }

    public void setToolExpand(String str) {
        this.toolExpand = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.toolExpand);
    }
}
