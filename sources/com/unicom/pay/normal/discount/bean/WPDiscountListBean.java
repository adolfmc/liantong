package com.unicom.pay.normal.discount.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.unicom.pay.normal.order.bean.WPDiscountQueryBean;
import com.unicom.pay.normal.order.bean.WPOrderButtonBean;
import com.unicom.pay.normal.order.bean.WPPayInfoBean;
import java.util.List;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPDiscountListBean implements Parcelable {
    public static final Parcelable.Creator<WPDiscountListBean> CREATOR = new Parcelable.Creator<WPDiscountListBean>() { // from class: com.unicom.pay.normal.discount.bean.WPDiscountListBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPDiscountListBean createFromParcel(Parcel parcel) {
            return new WPDiscountListBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPDiscountListBean[] newArray(int i) {
            return new WPDiscountListBean[i];
        }
    };
    private List<WPPayInfoBean> activityList;
    private String isShowOrderAmount;
    private String orderAmount;
    private String orderAmountSubscript;
    private WPOrderButtonBean orderButton;
    private String orderCache;
    private String orderDiscountIconState;
    private String orderDiscountIconType;
    private String orderDiscountMsg;
    private String orderDiscountNext;
    private WPDiscountQueryBean queryDisCountListV2Resp;
    private String realPayAmount;
    private List<WPPayInfoBean> updatePayInfos;

    public WPDiscountListBean() {
    }

    public WPDiscountListBean(Parcel parcel) {
        this.orderAmount = parcel.readString();
        this.realPayAmount = parcel.readString();
        this.isShowOrderAmount = parcel.readString();
        this.orderDiscountMsg = parcel.readString();
        this.orderDiscountIconType = parcel.readString();
        this.orderDiscountNext = parcel.readString();
        this.orderAmountSubscript = parcel.readString();
        this.orderDiscountIconState = parcel.readString();
        Parcelable.Creator<WPPayInfoBean> creator = WPPayInfoBean.CREATOR;
        this.updatePayInfos = parcel.createTypedArrayList(creator);
        this.orderButton = (WPOrderButtonBean) parcel.readParcelable(WPOrderButtonBean.class.getClassLoader());
        this.activityList = parcel.createTypedArrayList(creator);
        this.queryDisCountListV2Resp = (WPDiscountQueryBean) parcel.readParcelable(WPDiscountQueryBean.class.getClassLoader());
        this.orderCache = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<WPPayInfoBean> getActivityList() {
        return this.activityList;
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

    public WPDiscountQueryBean getQueryDisCountListV2Resp() {
        return this.queryDisCountListV2Resp;
    }

    public String getRealPayAmount() {
        return this.realPayAmount;
    }

    public List<WPPayInfoBean> getUpdatePayInfos() {
        return this.updatePayInfos;
    }

    public void setActivityList(List<WPPayInfoBean> list) {
        this.activityList = list;
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

    public void setQueryDisCountListV2Resp(WPDiscountQueryBean wPDiscountQueryBean) {
        this.queryDisCountListV2Resp = wPDiscountQueryBean;
    }

    public void setRealPayAmount(String str) {
        this.realPayAmount = str;
    }

    public void setUpdatePayInfos(List<WPPayInfoBean> list) {
        this.updatePayInfos = list;
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
        parcel.writeTypedList(this.updatePayInfos);
        parcel.writeParcelable(this.orderButton, i);
        parcel.writeTypedList(this.activityList);
        parcel.writeParcelable(this.queryDisCountListV2Resp, i);
        parcel.writeString(this.orderCache);
    }
}
