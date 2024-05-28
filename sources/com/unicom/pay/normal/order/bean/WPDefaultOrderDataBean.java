package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPDefaultOrderDataBean implements Parcelable {
    public static final Parcelable.Creator<WPDefaultOrderDataBean> CREATOR = new Parcelable.Creator<WPDefaultOrderDataBean>() { // from class: com.unicom.pay.normal.order.bean.WPDefaultOrderDataBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPDefaultOrderDataBean createFromParcel(Parcel parcel) {
            return new WPDefaultOrderDataBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPDefaultOrderDataBean[] newArray(int i) {
            return new WPDefaultOrderDataBean[i];
        }
    };
    public static final String TYPE_H5 = "2";
    public static final String TYPE_SDK = "1";
    private String h5_url;
    private String orderAmount;
    private String orderSubject;
    private String orderType;
    private String otherPayIcon;
    private String otherPayMsg;
    private String otherPayShowSize;
    private String timeStamp;
    private List<WPPayInfoBean> tools;
    private String tradeOrderNo;

    public WPDefaultOrderDataBean() {
        this.tradeOrderNo = "";
        this.orderSubject = "";
        this.orderAmount = "0";
        this.timeStamp = "";
        this.otherPayShowSize = "";
        this.h5_url = "";
        this.orderType = "";
        this.otherPayIcon = "";
        this.otherPayMsg = "";
    }

    public WPDefaultOrderDataBean(Parcel parcel) {
        this.tradeOrderNo = "";
        this.orderSubject = "";
        this.orderAmount = "0";
        this.timeStamp = "";
        this.otherPayShowSize = "";
        this.h5_url = "";
        this.orderType = "";
        this.otherPayIcon = "";
        this.otherPayMsg = "";
        this.tradeOrderNo = parcel.readString();
        this.orderSubject = parcel.readString();
        this.orderAmount = parcel.readString();
        this.tools = parcel.createTypedArrayList(WPPayInfoBean.CREATOR);
        this.timeStamp = parcel.readString();
        this.otherPayShowSize = parcel.readString();
        this.h5_url = parcel.readString();
        this.orderType = parcel.readString();
        this.otherPayIcon = parcel.readString();
        this.otherPayMsg = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getH5_url() {
        return this.h5_url;
    }

    public String getOrderAmount() {
        return this.orderAmount;
    }

    public String getOrderSubject() {
        return this.orderSubject;
    }

    public String getOrderType() {
        return this.orderType;
    }

    public String getOtherPayIcon() {
        return this.otherPayIcon;
    }

    public String getOtherPayMsg() {
        return this.otherPayMsg;
    }

    public String getOtherPayShowSize() {
        return this.otherPayShowSize;
    }

    public String getTimeStamp() {
        return this.timeStamp;
    }

    public List<WPPayInfoBean> getTools() {
        return this.tools;
    }

    public String getTradeOrderNo() {
        return this.tradeOrderNo;
    }

    public void setH5_url(String str) {
        this.h5_url = str;
    }

    public void setOrderAmount(String str) {
        this.orderAmount = str;
    }

    public void setOrderSubject(String str) {
        this.orderSubject = str;
    }

    public void setOrderType(String str) {
        this.orderType = str;
    }

    public void setOtherPayIcon(String str) {
        this.otherPayIcon = str;
    }

    public void setOtherPayMsg(String str) {
        this.otherPayMsg = str;
    }

    public void setOtherPayShowSize(String str) {
        this.otherPayShowSize = str;
    }

    public void setTimeStamp(String str) {
        this.timeStamp = str;
    }

    public void setTools(List<WPPayInfoBean> list) {
        this.tools = list;
    }

    public void setTradeOrderNo(String str) {
        this.tradeOrderNo = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.tradeOrderNo);
        parcel.writeString(this.orderSubject);
        parcel.writeString(this.orderAmount);
        parcel.writeTypedList(this.tools);
        parcel.writeString(this.timeStamp);
        parcel.writeString(this.otherPayShowSize);
        parcel.writeString(this.h5_url);
        parcel.writeString(this.orderType);
        parcel.writeString(this.otherPayIcon);
        parcel.writeString(this.otherPayMsg);
    }
}
