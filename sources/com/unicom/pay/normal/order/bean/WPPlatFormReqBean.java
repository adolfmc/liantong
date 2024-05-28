package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPPlatFormReqBean implements Parcelable {
    public static final Parcelable.Creator<WPPlatFormReqBean> CREATOR = new Parcelable.Creator<WPPlatFormReqBean>() { // from class: com.unicom.pay.normal.order.bean.WPPlatFormReqBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPPlatFormReqBean createFromParcel(Parcel parcel) {
            return new WPPlatFormReqBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPPlatFormReqBean[] newArray(int i) {
            return new WPPlatFormReqBean[i];
        }
    };
    private String accessChannel;
    private String businessChannel;
    private String businessType;
    private String cityCode;
    private String functionCode;
    private String merNo;
    private String provinceCode;
    private String qsBizOrderNo;
    private String serviceOrderId;
    private String servicePlatId;
    private String userNo;

    public WPPlatFormReqBean(Parcel parcel) {
        this.servicePlatId = parcel.readString();
        this.functionCode = parcel.readString();
        this.accessChannel = parcel.readString();
        this.qsBizOrderNo = parcel.readString();
        this.serviceOrderId = parcel.readString();
        this.provinceCode = parcel.readString();
        this.cityCode = parcel.readString();
        this.businessType = parcel.readString();
        this.businessChannel = parcel.readString();
        this.userNo = parcel.readString();
        this.merNo = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAccessChannel() {
        return this.accessChannel;
    }

    public String getBusinessChannel() {
        return this.businessChannel;
    }

    public String getBusinessType() {
        return this.businessType;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public String getFunctionCode() {
        return this.functionCode;
    }

    public String getMerNo() {
        return this.merNo;
    }

    public String getProvinceCode() {
        return this.provinceCode;
    }

    public String getQsBizOrderNo() {
        return this.qsBizOrderNo;
    }

    public String getServiceOrderId() {
        return this.serviceOrderId;
    }

    public String getServicePlatId() {
        return this.servicePlatId;
    }

    public String getUserNo() {
        return this.userNo;
    }

    public void setAccessChannel(String str) {
        this.accessChannel = str;
    }

    public void setBusinessChannel(String str) {
        this.businessChannel = str;
    }

    public void setBusinessType(String str) {
        this.businessType = str;
    }

    public void setCityCode(String str) {
        this.cityCode = str;
    }

    public void setFunctionCode(String str) {
        this.functionCode = str;
    }

    public void setMerNo(String str) {
        this.merNo = str;
    }

    public void setProvinceCode(String str) {
        this.provinceCode = str;
    }

    public void setQsBizOrderNo(String str) {
        this.qsBizOrderNo = str;
    }

    public void setServiceOrderId(String str) {
        this.serviceOrderId = str;
    }

    public void setServicePlatId(String str) {
        this.servicePlatId = str;
    }

    public void setUserNo(String str) {
        this.userNo = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.servicePlatId);
        parcel.writeString(this.functionCode);
        parcel.writeString(this.accessChannel);
        parcel.writeString(this.qsBizOrderNo);
        parcel.writeString(this.serviceOrderId);
        parcel.writeString(this.provinceCode);
        parcel.writeString(this.cityCode);
        parcel.writeString(this.businessType);
        parcel.writeString(this.businessChannel);
        parcel.writeString(this.userNo);
        parcel.writeString(this.merNo);
    }
}
