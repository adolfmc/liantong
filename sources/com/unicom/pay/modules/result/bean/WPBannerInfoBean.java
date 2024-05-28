package com.unicom.pay.modules.result.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPBannerInfoBean implements Parcelable {
    public static final Parcelable.Creator<WPBannerInfoBean> CREATOR = new Parcelable.Creator<WPBannerInfoBean>() { // from class: com.unicom.pay.modules.result.bean.WPBannerInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPBannerInfoBean createFromParcel(Parcel parcel) {
            return new WPBannerInfoBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPBannerInfoBean[] newArray(int i) {
            return new WPBannerInfoBean[i];
        }
    };
    public static final String ChangeBack = "changeBackGround";
    public static final String ChangeText = "changeText";
    public static final String H5Jump = "h5Jump";
    public static final String SpeedPayOpen = "speedPayOpen";
    private String bannerDiscountMsg;
    private String bannerType;
    private String buttonInfo1;
    private String buttonInfo2;
    private String discountMsg;
    private String imgUrl;
    private String imgUrl2;
    private boolean isChange;
    private String ruleId;
    private String url;

    public WPBannerInfoBean() {
    }

    public WPBannerInfoBean(Parcel parcel) {
        this.imgUrl = parcel.readString();
        this.imgUrl2 = parcel.readString();
        this.url = parcel.readString();
        this.bannerType = parcel.readString();
        this.discountMsg = parcel.readString();
        this.buttonInfo1 = parcel.readString();
        this.buttonInfo2 = parcel.readString();
        this.ruleId = parcel.readString();
        this.bannerDiscountMsg = parcel.readString();
        this.isChange = parcel.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getBannerDiscountMsg() {
        return this.bannerDiscountMsg;
    }

    public String getBannerType() {
        return this.bannerType;
    }

    public String getButtonInfo1() {
        return this.buttonInfo1;
    }

    public String getButtonInfo2() {
        return this.buttonInfo2;
    }

    public String getDiscountMsg() {
        return this.discountMsg;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public String getImgUrl2() {
        return this.imgUrl2;
    }

    public String getRuleId() {
        return this.ruleId;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isChange() {
        return this.isChange;
    }

    public void setBannerDiscountMsg(String str) {
        this.bannerDiscountMsg = str;
    }

    public void setBannerType(String str) {
        this.bannerType = str;
    }

    public void setButtonInfo1(String str) {
        this.buttonInfo1 = str;
    }

    public void setButtonInfo2(String str) {
        this.buttonInfo2 = str;
    }

    public void setChange(boolean z) {
        this.isChange = z;
    }

    public void setDiscountMsg(String str) {
        this.discountMsg = str;
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
    }

    public void setImgUrl2(String str) {
        this.imgUrl2 = str;
    }

    public void setRuleId(String str) {
        this.ruleId = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.imgUrl);
        parcel.writeString(this.imgUrl2);
        parcel.writeString(this.url);
        parcel.writeString(this.bannerType);
        parcel.writeString(this.discountMsg);
        parcel.writeString(this.buttonInfo1);
        parcel.writeString(this.buttonInfo2);
        parcel.writeString(this.ruleId);
        parcel.writeString(this.bannerDiscountMsg);
        parcel.writeByte(this.isChange ? (byte) 1 : (byte) 0);
    }
}
