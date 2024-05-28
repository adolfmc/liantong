package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPOrderInfoBean implements Parcelable {
    public static final Parcelable.Creator<WPOrderInfoBean> CREATOR = new Parcelable.Creator<WPOrderInfoBean>() { // from class: com.unicom.pay.normal.order.bean.WPOrderInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPOrderInfoBean createFromParcel(Parcel parcel) {
            return new WPOrderInfoBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPOrderInfoBean[] newArray(int i) {
            return new WPOrderInfoBean[i];
        }
    };
    public static final String SUB_EVENT_H5 = "h5";
    public static final String SUB_EVENT_OPEN = "open";
    private List<WPAgreementMsg> agreementMsgs;
    private String businessChannel;
    private String businessType;
    private String closeMsg;
    private String countdownTime;
    private String isShowOrderAmount;
    private String maskPhoneNo;
    private String merNo;
    private String orderAmount;
    private String orderAmountSubscript;
    private String orderCache;
    private String orderDiscountIconState;
    private String orderDiscountIconType;
    private String orderDiscountMsg;
    private String orderDiscountNext;
    private String orderSubject;
    private String realPayAmount;
    private String subSubjectEventType;
    private String subSubjectH5Url;
    private String subSubjectIconType;
    private String subSubjectNext;
    private String userNo;

    public WPOrderInfoBean(Parcel parcel) {
        this.orderSubject = parcel.readString();
        this.subSubjectNext = parcel.readString();
        this.subSubjectIconType = parcel.readString();
        this.subSubjectEventType = parcel.readString();
        this.subSubjectH5Url = parcel.readString();
        this.countdownTime = parcel.readString();
        this.orderAmount = parcel.readString();
        this.realPayAmount = parcel.readString();
        this.isShowOrderAmount = parcel.readString();
        this.subSubjectNext = parcel.readString();
        this.orderDiscountMsg = parcel.readString();
        this.orderDiscountIconType = parcel.readString();
        this.orderDiscountNext = parcel.readString();
        this.orderAmountSubscript = parcel.readString();
        this.orderDiscountIconState = parcel.readString();
        this.orderCache = parcel.readString();
        this.agreementMsgs = parcel.createTypedArrayList(WPAgreementMsg.CREATOR);
        this.maskPhoneNo = parcel.readString();
        this.businessType = parcel.readString();
        this.businessChannel = parcel.readString();
        this.userNo = parcel.readString();
        this.merNo = parcel.readString();
        this.closeMsg = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<WPAgreementMsg> getAgreementMsgs() {
        return this.agreementMsgs;
    }

    public String getBusinessChannel() {
        return this.businessChannel;
    }

    public String getBusinessType() {
        return this.businessType;
    }

    public String getCloseMsg() {
        return this.closeMsg;
    }

    public String getCountdownTime() {
        return this.countdownTime;
    }

    public String getIsShowOrderAmount() {
        return this.isShowOrderAmount;
    }

    public String getMaskPhoneNo() {
        return this.maskPhoneNo;
    }

    public String getMerNo() {
        return this.merNo;
    }

    public String getOrderAmount() {
        return this.orderAmount;
    }

    public String getOrderAmountSubscript() {
        return this.orderAmountSubscript;
    }

    public String getOrderCache() {
        return this.orderCache;
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

    public String getOrderSubject() {
        return this.orderSubject;
    }

    public String getRealPayAmount() {
        return this.realPayAmount;
    }

    public String getSubSubjectEventType() {
        return this.subSubjectEventType;
    }

    public String getSubSubjectH5Url() {
        return this.subSubjectH5Url;
    }

    public String getSubSubjectIconType() {
        return this.subSubjectIconType;
    }

    public String getSubSubjectNext() {
        return this.subSubjectNext;
    }

    public String getUserNo() {
        return this.userNo;
    }

    public void setAgreementMsgs(List<WPAgreementMsg> list) {
        this.agreementMsgs = list;
    }

    public void setBusinessChannel(String str) {
        this.businessChannel = str;
    }

    public void setBusinessType(String str) {
        this.businessType = str;
    }

    public void setCloseMsg(String str) {
        this.closeMsg = str;
    }

    public void setCountdownTime(String str) {
        this.countdownTime = str;
    }

    public void setIsShowOrderAmount(String str) {
        this.isShowOrderAmount = str;
    }

    public void setMaskPhoneNo(String str) {
        this.maskPhoneNo = str;
    }

    public void setMerNo(String str) {
        this.merNo = str;
    }

    public void setOrderAmount(String str) {
        this.orderAmount = str;
    }

    public void setOrderAmountSubscript(String str) {
        this.orderAmountSubscript = str;
    }

    public void setOrderCache(String str) {
        this.orderCache = str;
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

    public void setOrderSubject(String str) {
        this.orderSubject = str;
    }

    public void setRealPayAmount(String str) {
        this.realPayAmount = str;
    }

    public void setSubSubjectEventType(String str) {
        this.subSubjectEventType = str;
    }

    public void setSubSubjectH5Url(String str) {
        this.subSubjectH5Url = str;
    }

    public void setSubSubjectIconType(String str) {
        this.subSubjectIconType = str;
    }

    public void setSubSubjectNext(String str) {
        this.subSubjectNext = str;
    }

    public void setUserNo(String str) {
        this.userNo = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.orderSubject);
        parcel.writeString(this.subSubjectNext);
        parcel.writeString(this.subSubjectIconType);
        parcel.writeString(this.subSubjectEventType);
        parcel.writeString(this.subSubjectH5Url);
        parcel.writeString(this.countdownTime);
        parcel.writeString(this.orderAmount);
        parcel.writeString(this.realPayAmount);
        parcel.writeString(this.isShowOrderAmount);
        parcel.writeString(this.subSubjectNext);
        parcel.writeString(this.orderDiscountMsg);
        parcel.writeString(this.orderDiscountIconType);
        parcel.writeString(this.orderDiscountNext);
        parcel.writeString(this.orderAmountSubscript);
        parcel.writeString(this.orderDiscountIconState);
        parcel.writeString(this.orderCache);
        parcel.writeTypedList(this.agreementMsgs);
        parcel.writeString(this.maskPhoneNo);
        parcel.writeString(this.businessType);
        parcel.writeString(this.businessChannel);
        parcel.writeString(this.userNo);
        parcel.writeString(this.merNo);
        parcel.writeString(this.closeMsg);
    }
}
