package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPPayBeforeBean implements Parcelable {
    public static final Parcelable.Creator<WPPayBeforeBean> CREATOR = new Parcelable.Creator<WPPayBeforeBean>() { // from class: com.unicom.pay.normal.order.bean.WPPayBeforeBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPPayBeforeBean createFromParcel(Parcel parcel) {
            return new WPPayBeforeBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPPayBeforeBean[] newArray(int i) {
            return new WPPayBeforeBean[i];
        }
    };
    private String fapAgrNo;
    private String fundChnCode;
    private String jumpUrl;
    private String phoneNo;
    private String uuid;

    public WPPayBeforeBean() {
    }

    public WPPayBeforeBean(Parcel parcel) {
        this.fapAgrNo = parcel.readString();
        this.uuid = parcel.readString();
        this.phoneNo = parcel.readString();
        this.fundChnCode = parcel.readString();
        this.jumpUrl = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getFapAgrNo() {
        return this.fapAgrNo;
    }

    public String getFundChnCode() {
        return this.fundChnCode;
    }

    public String getJumpUrl() {
        return this.jumpUrl;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setFapAgrNo(String str) {
        this.fapAgrNo = str;
    }

    public void setFundChnCode(String str) {
        this.fundChnCode = str;
    }

    public void setJumpUrl(String str) {
        this.jumpUrl = str;
    }

    public void setPhoneNo(String str) {
        this.phoneNo = str;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.fapAgrNo);
        parcel.writeString(this.uuid);
        parcel.writeString(this.phoneNo);
        parcel.writeString(this.fundChnCode);
        parcel.writeString(this.jumpUrl);
    }
}
