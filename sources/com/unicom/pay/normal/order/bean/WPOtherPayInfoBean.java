package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPOtherPayInfoBean implements Parcelable {
    public static final Parcelable.Creator<WPOtherPayInfoBean> CREATOR = new Parcelable.Creator<WPOtherPayInfoBean>() { // from class: com.unicom.pay.normal.order.bean.WPOtherPayInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPOtherPayInfoBean createFromParcel(Parcel parcel) {
            return new WPOtherPayInfoBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPOtherPayInfoBean[] newArray(int i) {
            return new WPOtherPayInfoBean[i];
        }
    };
    private String otherPayIcon;
    private List<WPPayInfoBean> otherPayList;
    private String otherPayMsg;
    private String otherPayShowSize;

    public WPOtherPayInfoBean() {
    }

    public WPOtherPayInfoBean(Parcel parcel) {
        this.otherPayShowSize = parcel.readString();
        this.otherPayIcon = parcel.readString();
        this.otherPayList = parcel.createTypedArrayList(WPPayInfoBean.CREATOR);
        this.otherPayMsg = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getOtherPayIcon() {
        return this.otherPayIcon;
    }

    public List<WPPayInfoBean> getOtherPayList() {
        return this.otherPayList;
    }

    public String getOtherPayMsg() {
        return this.otherPayMsg;
    }

    public String getOtherPayShowSize() {
        return this.otherPayShowSize;
    }

    public void setOtherPayIcon(String str) {
        this.otherPayIcon = str;
    }

    public void setOtherPayList(List<WPPayInfoBean> list) {
        this.otherPayList = list;
    }

    public void setOtherPayMsg(String str) {
        this.otherPayMsg = str;
    }

    public void setOtherPayShowSize(String str) {
        this.otherPayShowSize = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.otherPayShowSize);
        parcel.writeString(this.otherPayIcon);
        parcel.writeTypedList(this.otherPayList);
        parcel.writeString(this.otherPayMsg);
    }
}
