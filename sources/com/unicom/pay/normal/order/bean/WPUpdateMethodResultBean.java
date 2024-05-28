package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPUpdateMethodResultBean implements Parcelable {
    public static final Parcelable.Creator<WPUpdateMethodResultBean> CREATOR = new Parcelable.Creator<WPUpdateMethodResultBean>() { // from class: com.unicom.pay.normal.order.bean.WPUpdateMethodResultBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPUpdateMethodResultBean createFromParcel(Parcel parcel) {
            return new WPUpdateMethodResultBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPUpdateMethodResultBean[] newArray(int i) {
            return new WPUpdateMethodResultBean[i];
        }
    };
    private String isShowOrderAmount;
    private String orderAmount;
    private String orderAmountSubscript;
    private WPOrderButtonBean orderButton;
    private String orderDiscountIconState;
    private String orderDiscountIconType;
    private String orderDiscountMsg;
    private String orderDiscountNext;
    private WPPayInfoBean payToolInfo;
    private String realPayAmount;
    private String toastMsg;

    public WPUpdateMethodResultBean() {
    }

    public WPUpdateMethodResultBean(Parcel parcel) {
        this.orderAmount = parcel.readString();
        this.realPayAmount = parcel.readString();
        this.isShowOrderAmount = parcel.readString();
        this.orderDiscountMsg = parcel.readString();
        this.orderDiscountIconType = parcel.readString();
        this.orderDiscountNext = parcel.readString();
        this.orderAmountSubscript = parcel.readString();
        this.orderDiscountIconState = parcel.readString();
        this.orderButton = (WPOrderButtonBean) parcel.readParcelable(WPOrderButtonBean.class.getClassLoader());
        this.payToolInfo = (WPPayInfoBean) parcel.readParcelable(WPPayInfoBean.class.getClassLoader());
        this.toastMsg = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getIsShowOrderAmount() {
        return this.isShowOrderAmount;
    }

    public String getOrderAmount() {
        return this.orderAmount;
    }

    public String getOrderAmountSubscript() {
        return this.orderAmountSubscript;
    }

    public WPOrderButtonBean getOrderButton() {
        return this.orderButton;
    }

    public String getOrderDiscountIconState() {
        return this.orderDiscountIconState;
    }

    public String getOrderDiscountIconType() {
        return this.orderDiscountIconType;
    }

    public String getOrderDiscountMsg() {
        return this.orderDiscountMsg;
    }

    public String getOrderDiscountNext() {
        return this.orderDiscountNext;
    }

    public WPPayInfoBean getPayToolInfo() {
        return this.payToolInfo;
    }

    public String getRealPayAmount() {
        return this.realPayAmount;
    }

    public String getToastMsg() {
        return this.toastMsg;
    }

    public void setIsShowOrderAmount(String str) {
        this.isShowOrderAmount = str;
    }

    public void setOrderAmount(String str) {
        this.orderAmount = str;
    }

    public void setOrderAmountSubscript(String str) {
        this.orderAmountSubscript = str;
    }

    public void setOrderButton(WPOrderButtonBean wPOrderButtonBean) {
        this.orderButton = wPOrderButtonBean;
    }

    public void setOrderDiscountIconState(String str) {
        this.orderDiscountIconState = str;
    }

    public void setOrderDiscountIconType(String str) {
        this.orderDiscountIconType = str;
    }

    public void setOrderDiscountMsg(String str) {
        this.orderDiscountMsg = str;
    }

    public void setOrderDiscountNext(String str) {
        this.orderDiscountNext = str;
    }

    public void setPayToolInfo(WPPayInfoBean wPPayInfoBean) {
        this.payToolInfo = wPPayInfoBean;
    }

    public void setRealPayAmount(String str) {
        this.realPayAmount = str;
    }

    public void setToastMsg(String str) {
        this.toastMsg = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.orderAmount);
        parcel.writeString(this.realPayAmount);
        parcel.writeString(this.isShowOrderAmount);
        parcel.writeString(this.orderDiscountMsg);
        parcel.writeString(this.orderDiscountIconType);
        parcel.writeString(this.orderDiscountNext);
        parcel.writeString(this.orderAmountSubscript);
        parcel.writeString(this.orderDiscountIconState);
        parcel.writeParcelable(this.orderButton, i);
        parcel.writeParcelable(this.payToolInfo, i);
        parcel.writeString(this.toastMsg);
    }
}
