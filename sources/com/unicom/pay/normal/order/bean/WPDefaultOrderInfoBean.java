package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPDefaultOrderInfoBean implements Parcelable {
    public static final Parcelable.Creator<WPDefaultOrderInfoBean> CREATOR = new Parcelable.Creator<WPDefaultOrderInfoBean>() { // from class: com.unicom.pay.normal.order.bean.WPDefaultOrderInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPDefaultOrderInfoBean createFromParcel(Parcel parcel) {
            return new WPDefaultOrderInfoBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPDefaultOrderInfoBean[] newArray(int i) {
            return new WPDefaultOrderInfoBean[i];
        }
    };
    private WPDefaultOrderDataBean data;
    private String sign;

    public WPDefaultOrderInfoBean() {
        this.sign = "";
    }

    public WPDefaultOrderInfoBean(Parcel parcel) {
        this.sign = "";
        this.data = (WPDefaultOrderDataBean) parcel.readParcelable(WPDefaultOrderDataBean.class.getClassLoader());
        this.sign = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WPDefaultOrderDataBean getData() {
        return this.data;
    }

    public String getSign() {
        return this.sign;
    }

    public void setData(WPDefaultOrderDataBean wPDefaultOrderDataBean) {
        this.data = wPDefaultOrderDataBean;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.data, i);
        parcel.writeString(this.sign);
    }
}
