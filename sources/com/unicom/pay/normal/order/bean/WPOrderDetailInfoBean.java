package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPOrderDetailInfoBean implements Parcelable {
    public static final Parcelable.Creator<WPOrderDetailInfoBean> CREATOR = new Parcelable.Creator<WPOrderDetailInfoBean>() { // from class: com.unicom.pay.normal.order.bean.WPOrderDetailInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPOrderDetailInfoBean createFromParcel(Parcel parcel) {
            return new WPOrderDetailInfoBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPOrderDetailInfoBean[] newArray(int i) {
            return new WPOrderDetailInfoBean[i];
        }
    };
    private String countdownTime;
    private String goodName;
    private String orderAmout;
    private String orderAmoutSubscript;
    private String pid;
    private String realPayAmout;
    private String topDiscountNext;
    private String topMethodMsg;

    public WPOrderDetailInfoBean(Parcel parcel) {
        this.goodName = parcel.readString();
        this.orderAmout = parcel.readString();
        this.pid = parcel.readString();
        this.realPayAmout = parcel.readString();
        this.countdownTime = parcel.readString();
        this.orderAmoutSubscript = parcel.readString();
        this.topMethodMsg = parcel.readString();
        this.topDiscountNext = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCountdownTime() {
        return this.countdownTime;
    }

    public String getGoodName() {
        return this.goodName;
    }

    public String getOrderAmout() {
        return this.orderAmout;
    }

    public String getOrderAmoutSubscript() {
        return this.orderAmoutSubscript;
    }

    public String getPid() {
        return this.pid;
    }

    public String getRealPayAmout() {
        return this.realPayAmout;
    }

    public String getTopDiscountNext() {
        return this.topDiscountNext;
    }

    public String getTopMethodMsg() {
        return this.topMethodMsg;
    }

    public void setCountdownTime(String str) {
        this.countdownTime = str;
    }

    public void setGoodName(String str) {
        this.goodName = str;
    }

    public void setOrderAmout(String str) {
        this.orderAmout = str;
    }

    public void setOrderAmoutSubscript(String str) {
        this.orderAmoutSubscript = str;
    }

    public void setPid(String str) {
        this.pid = str;
    }

    public void setRealPayAmout(String str) {
        this.realPayAmout = str;
    }

    public void setTopDiscountNext(String str) {
        this.topDiscountNext = str;
    }

    public void setTopMethodMsg(String str) {
        this.topMethodMsg = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.goodName);
        parcel.writeString(this.orderAmout);
        parcel.writeString(this.pid);
        parcel.writeString(this.realPayAmout);
        parcel.writeString(this.countdownTime);
        parcel.writeString(this.orderAmoutSubscript);
        parcel.writeString(this.topMethodMsg);
        parcel.writeString(this.topDiscountNext);
    }
}
