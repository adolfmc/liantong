package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPFqSignResultBean implements Parcelable {
    public static final Parcelable.Creator<WPFqSignResultBean> CREATOR = new Parcelable.Creator<WPFqSignResultBean>() { // from class: com.unicom.pay.normal.order.bean.WPFqSignResultBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPFqSignResultBean createFromParcel(Parcel parcel) {
            return new WPFqSignResultBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPFqSignResultBean[] newArray(int i) {
            return new WPFqSignResultBean[i];
        }
    };
    private String fapAgrNo;
    private String uuid;

    public WPFqSignResultBean() {
    }

    public WPFqSignResultBean(Parcel parcel) {
        this.uuid = parcel.readString();
        this.fapAgrNo = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getFapAgrNo() {
        return this.fapAgrNo;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setFapAgrNo(String str) {
        this.fapAgrNo = str;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.uuid);
        parcel.writeString(this.fapAgrNo);
    }
}
