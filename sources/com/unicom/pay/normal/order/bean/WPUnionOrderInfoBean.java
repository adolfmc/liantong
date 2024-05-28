package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPUnionOrderInfoBean implements Parcelable {
    public static final Parcelable.Creator<WPUnionOrderInfoBean> CREATOR = new Parcelable.Creator<WPUnionOrderInfoBean>() { // from class: com.unicom.pay.normal.order.bean.WPUnionOrderInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPUnionOrderInfoBean createFromParcel(Parcel parcel) {
            return new WPUnionOrderInfoBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPUnionOrderInfoBean[] newArray(int i) {
            return new WPUnionOrderInfoBean[i];
        }
    };
    private WPOrderButtonBean orderButton;
    private List<WPOrderDetailNoticeBean> orderDetailNotices;
    private WPOrderInfoBean orderInfo;
    private WPOtherPayInfoBean otherPayInfo;
    private String payUid;
    private WPWapPayInfoBean wapPayInfo;

    public WPUnionOrderInfoBean() {
    }

    public WPUnionOrderInfoBean(Parcel parcel) {
        this.orderDetailNotices = parcel.createTypedArrayList(WPOrderDetailNoticeBean.CREATOR);
        this.orderInfo = (WPOrderInfoBean) parcel.readParcelable(WPOrderInfoBean.class.getClassLoader());
        this.wapPayInfo = (WPWapPayInfoBean) parcel.readParcelable(WPWapPayInfoBean.class.getClassLoader());
        this.otherPayInfo = (WPOtherPayInfoBean) parcel.readParcelable(WPOtherPayInfoBean.class.getClassLoader());
        this.orderButton = (WPOrderButtonBean) parcel.readParcelable(WPOrderButtonBean.class.getClassLoader());
        this.payUid = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WPOrderButtonBean getOrderButton() {
        return this.orderButton;
    }

    public List<WPOrderDetailNoticeBean> getOrderDetailNotices() {
        return this.orderDetailNotices;
    }

    public WPOrderInfoBean getOrderInfo() {
        return this.orderInfo;
    }

    public WPOtherPayInfoBean getOtherPayInfo() {
        return this.otherPayInfo;
    }

    public String getPayUid() {
        return this.payUid;
    }

    public WPWapPayInfoBean getWapPayInfo() {
        return this.wapPayInfo;
    }

    public void setOrderButton(WPOrderButtonBean wPOrderButtonBean) {
        this.orderButton = wPOrderButtonBean;
    }

    public void setOrderDetailNotices(List<WPOrderDetailNoticeBean> list) {
        this.orderDetailNotices = list;
    }

    public void setOrderInfo(WPOrderInfoBean wPOrderInfoBean) {
        this.orderInfo = wPOrderInfoBean;
    }

    public void setOtherPayInfo(WPOtherPayInfoBean wPOtherPayInfoBean) {
        this.otherPayInfo = wPOtherPayInfoBean;
    }

    public void setPayUid(String str) {
        this.payUid = str;
    }

    public void setWapPayInfo(WPWapPayInfoBean wPWapPayInfoBean) {
        this.wapPayInfo = wPWapPayInfoBean;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.orderDetailNotices);
        parcel.writeParcelable(this.orderInfo, i);
        parcel.writeParcelable(this.wapPayInfo, i);
        parcel.writeParcelable(this.otherPayInfo, i);
        parcel.writeParcelable(this.orderButton, i);
        parcel.writeString(this.payUid);
    }
}
