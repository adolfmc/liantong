package com.unicom.pay.modules.verify.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.unicom.pay.modules.result.bean.WPBannerInfoBean;
import com.unicom.pay.qpay.open.bean.WPAgreementBean;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPUnionPayResultBean implements Parcelable {
    public static final Parcelable.Creator<WPUnionPayResultBean> CREATOR = new Parcelable.Creator<WPUnionPayResultBean>() { // from class: com.unicom.pay.modules.verify.bean.WPUnionPayResultBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPUnionPayResultBean createFromParcel(Parcel parcel) {
            return new WPUnionPayResultBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPUnionPayResultBean[] newArray(int i) {
            return new WPUnionPayResultBean[i];
        }
    };
    public static final String SHOW_QPAY = "1";
    private WPAgreementBean agreement;
    private String amount;
    private ArrayList<WPBannerInfoBean> bannerList;
    private String callBackUrl;
    private String discountMsg;
    private String fqNum;
    private String hbAmount;
    private String merNo;
    private List<WPPayEndInfoBean> payEndInfos;
    private String payToolYxAmount;
    private String projectType;
    private String qpqyDiscountMsg;
    private String riskPhoneNo;
    private String riskUuid;
    private String showQpayRecommend;
    private String storeIndex;

    public WPUnionPayResultBean() {
    }

    public WPUnionPayResultBean(Parcel parcel) {
        this.amount = parcel.readString();
        this.showQpayRecommend = parcel.readString();
        this.qpqyDiscountMsg = parcel.readString();
        this.callBackUrl = parcel.readString();
        this.riskUuid = parcel.readString();
        this.riskPhoneNo = parcel.readString();
        this.storeIndex = parcel.readString();
        this.payEndInfos = parcel.createTypedArrayList(WPPayEndInfoBean.CREATOR);
        this.merNo = parcel.readString();
        this.discountMsg = parcel.readString();
        this.payToolYxAmount = parcel.readString();
        this.hbAmount = parcel.readString();
        this.agreement = (WPAgreementBean) parcel.readParcelable(WPAgreementBean.class.getClassLoader());
        this.bannerList = parcel.createTypedArrayList(WPBannerInfoBean.CREATOR);
        this.fqNum = parcel.readString();
        this.projectType = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WPAgreementBean getAgreement() {
        return this.agreement;
    }

    public String getAmount() {
        return this.amount;
    }

    public ArrayList<WPBannerInfoBean> getBannerList() {
        return this.bannerList;
    }

    public String getCallBackUrl() {
        return this.callBackUrl;
    }

    public String getDiscountMsg() {
        return this.discountMsg;
    }

    public String getFqNum() {
        return this.fqNum;
    }

    public String getHbAmount() {
        return this.hbAmount;
    }

    public String getMerNo() {
        return this.merNo;
    }

    public List<WPPayEndInfoBean> getPayEndInfos() {
        return this.payEndInfos;
    }

    public String getPayToolYxAmount() {
        return this.payToolYxAmount;
    }

    public String getProjectType() {
        return this.projectType;
    }

    public String getQpqyDiscountMsg() {
        return this.qpqyDiscountMsg;
    }

    public String getRiskPhoneNo() {
        return this.riskPhoneNo;
    }

    public String getRiskUuid() {
        return this.riskUuid;
    }

    public String getShowQpayRecommend() {
        return this.showQpayRecommend;
    }

    public String getStoreIndex() {
        return this.storeIndex;
    }

    public void setAgreement(WPAgreementBean wPAgreementBean) {
        this.agreement = wPAgreementBean;
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    public void setBannerList(ArrayList<WPBannerInfoBean> arrayList) {
        this.bannerList = arrayList;
    }

    public void setCallBackUrl(String str) {
        this.callBackUrl = str;
    }

    public void setDiscountMsg(String str) {
        this.discountMsg = str;
    }

    public void setFqNum(String str) {
        this.fqNum = str;
    }

    public void setHbAmount(String str) {
        this.hbAmount = str;
    }

    public void setMerNo(String str) {
        this.merNo = str;
    }

    public void setPayEndInfos(List<WPPayEndInfoBean> list) {
        this.payEndInfos = list;
    }

    public void setPayToolYxAmount(String str) {
        this.payToolYxAmount = str;
    }

    public void setProjectType(String str) {
        this.projectType = str;
    }

    public void setQpqyDiscountMsg(String str) {
        this.qpqyDiscountMsg = str;
    }

    public void setRiskPhoneNo(String str) {
        this.riskPhoneNo = str;
    }

    public void setRiskUuid(String str) {
        this.riskUuid = str;
    }

    public void setShowQpayRecommend(String str) {
        this.showQpayRecommend = str;
    }

    public void setStoreIndex(String str) {
        this.storeIndex = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.amount);
        parcel.writeString(this.showQpayRecommend);
        parcel.writeString(this.qpqyDiscountMsg);
        parcel.writeString(this.callBackUrl);
        parcel.writeString(this.riskUuid);
        parcel.writeString(this.riskPhoneNo);
        parcel.writeString(this.storeIndex);
        parcel.writeTypedList(this.payEndInfos);
        parcel.writeString(this.merNo);
        parcel.writeString(this.discountMsg);
        parcel.writeString(this.payToolYxAmount);
        parcel.writeString(this.hbAmount);
        parcel.writeParcelable(this.agreement, i);
        parcel.writeTypedList(this.bannerList);
        parcel.writeString(this.fqNum);
        parcel.writeString(this.projectType);
    }
}
