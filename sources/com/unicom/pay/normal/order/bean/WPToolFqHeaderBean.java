package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPToolFqHeaderBean implements Parcelable {
    public static final Parcelable.Creator<WPToolFqHeaderBean> CREATOR = new Parcelable.Creator<WPToolFqHeaderBean>() { // from class: com.unicom.pay.normal.order.bean.WPToolFqHeaderBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPToolFqHeaderBean createFromParcel(Parcel parcel) {
            return new WPToolFqHeaderBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPToolFqHeaderBean[] newArray(int i) {
            return new WPToolFqHeaderBean[i];
        }
    };
    private String fqHeadMsg;

    public WPToolFqHeaderBean() {
    }

    public WPToolFqHeaderBean(Parcel parcel) {
        this.fqHeadMsg = parcel.readString();
    }

    public WPToolFqHeaderBean(String str) {
        this.fqHeadMsg = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getFqHeadMsg() {
        return this.fqHeadMsg;
    }

    public void setFqHeadMsg(String str) {
        this.fqHeadMsg = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.fqHeadMsg);
    }
}
