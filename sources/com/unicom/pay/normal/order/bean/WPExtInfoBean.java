package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import cn.ltzf.passguard.C1730a;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPExtInfoBean implements Parcelable {
    public static final Parcelable.Creator<WPExtInfoBean> CREATOR = new Parcelable.Creator<WPExtInfoBean>() { // from class: com.unicom.pay.normal.order.bean.WPExtInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPExtInfoBean createFromParcel(Parcel parcel) {
            return new WPExtInfoBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPExtInfoBean[] newArray(int i) {
            return new WPExtInfoBean[i];
        }
    };
    private String discountAmount;
    private String discountMsg;
    private String discountNext;
    private String jumpUrl;
    private String methodMsg;
    private String moreTips;
    private String orderAmoutSubscript;
    private String realPayAmout;
    private String topDiscountNext;
    private String topMethodMsg;

    public WPExtInfoBean() {
    }

    public WPExtInfoBean(Parcel parcel) {
        this.discountAmount = parcel.readString();
        this.discountMsg = parcel.readString();
        this.moreTips = parcel.readString();
        this.realPayAmout = parcel.readString();
        this.jumpUrl = parcel.readString();
        this.methodMsg = parcel.readString();
        this.discountNext = parcel.readString();
        this.topMethodMsg = parcel.readString();
        this.topDiscountNext = parcel.readString();
        this.orderAmoutSubscript = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getDiscountAmount() {
        if (!"¥0.00".equals(this.discountAmount) && !TextUtils.isEmpty(this.discountAmount) && TextUtils.isEmpty(this.jumpUrl)) {
            StringBuilder m22016a = C1730a.m22016a("-");
            m22016a.append(this.discountAmount);
            return m22016a.toString();
        }
        return this.discountAmount;
    }

    public String getDiscountMsg() {
        return this.discountMsg;
    }

    public String getDiscountNext() {
        return this.discountNext;
    }

    public String getJumpUrl() {
        return this.jumpUrl;
    }

    public String getMethodMsg() {
        return this.methodMsg;
    }

    public String getMoreTips() {
        return this.moreTips;
    }

    public String getOrderAmoutSubscript() {
        return this.orderAmoutSubscript;
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

    public void setDiscountAmount(String str) {
        this.discountAmount = str;
    }

    public void setDiscountMsg(String str) {
        this.discountMsg = str;
    }

    public void setDiscountNext(String str) {
        this.discountNext = str;
    }

    public void setJumpUrl(String str) {
        this.jumpUrl = str;
    }

    public void setMethodMsg(String str) {
        this.methodMsg = str;
    }

    public void setMoreTips(String str) {
        this.moreTips = str;
    }

    public void setOrderAmoutSubscript(String str) {
        this.orderAmoutSubscript = str;
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
        parcel.writeString(this.discountAmount);
        parcel.writeString(this.discountMsg);
        parcel.writeString(this.moreTips);
        parcel.writeString(this.realPayAmout);
        parcel.writeString(this.jumpUrl);
        parcel.writeString(this.methodMsg);
        parcel.writeString(this.discountNext);
        parcel.writeString(this.topMethodMsg);
        parcel.writeString(this.topDiscountNext);
        parcel.writeString(this.orderAmoutSubscript);
    }
}
