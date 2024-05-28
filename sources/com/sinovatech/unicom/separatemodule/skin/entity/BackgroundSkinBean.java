package com.sinovatech.unicom.separatemodule.skin.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

@Entity
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BackgroundSkinBean implements Parcelable {
    public static final Parcelable.Creator<BackgroundSkinBean> CREATOR = new Parcelable.Creator<BackgroundSkinBean>() { // from class: com.sinovatech.unicom.separatemodule.skin.entity.BackgroundSkinBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BackgroundSkinBean createFromParcel(Parcel parcel) {
            return new BackgroundSkinBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BackgroundSkinBean[] newArray(int i) {
            return new BackgroundSkinBean[i];
        }
    };
    private String bottomIcon;
    private String groupName;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18611id;
    private String isCustom;
    private String isDefault;
    private String isSelect;
    private String mobile;
    private String productDesc;
    private String productId;
    private String productImgUrl;
    private String productLinkUrl;
    private String productName;
    private String productSubtitle;
    private String showType;
    private String textPictureColor;
    private String titleColor;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getTextPictureColor() {
        return this.textPictureColor;
    }

    public void setTextPictureColor(String str) {
        this.textPictureColor = str;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public String getShowType() {
        return this.showType;
    }

    public void setShowType(String str) {
        this.showType = str;
    }

    public String getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(String str) {
        this.isDefault = str;
    }

    public String getIsCustom() {
        return this.isCustom;
    }

    public void setIsCustom(String str) {
        this.isCustom = str;
    }

    public long getId() {
        return this.f18611id;
    }

    public void setId(long j) {
        this.f18611id = j;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public String getProductImgUrl() {
        return this.productImgUrl;
    }

    public void setProductImgUrl(String str) {
        this.productImgUrl = str;
    }

    public String getProductSubtitle() {
        return this.productSubtitle;
    }

    public void setProductSubtitle(String str) {
        this.productSubtitle = str;
    }

    public String getProductDesc() {
        return this.productDesc;
    }

    public void setProductDesc(String str) {
        this.productDesc = str;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getProductLinkUrl() {
        return this.productLinkUrl;
    }

    public void setProductLinkUrl(String str) {
        this.productLinkUrl = str;
    }

    public String getBottomIcon() {
        return TextUtils.isEmpty(this.bottomIcon) ? "" : this.bottomIcon;
    }

    public void setBottomIcon(String str) {
        this.bottomIcon = str;
    }

    public String getTitleColor() {
        return TextUtils.isEmpty(this.titleColor) ? "" : this.titleColor;
    }

    public void setTitleColor(String str) {
        this.titleColor = str;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String str) {
        this.groupName = str;
    }

    public String getIsSelect() {
        return this.isSelect;
    }

    public void setIsSelect(String str) {
        this.isSelect = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f18611id);
        parcel.writeString(this.productName);
        parcel.writeString(this.productImgUrl);
        parcel.writeString(this.productSubtitle);
        parcel.writeString(this.productDesc);
        parcel.writeString(this.productId);
        parcel.writeString(this.productLinkUrl);
        parcel.writeString(this.bottomIcon);
        parcel.writeString(this.titleColor);
        parcel.writeString(this.isCustom);
        parcel.writeString(this.isDefault);
        parcel.writeString(this.showType);
        parcel.writeString(this.groupName);
        parcel.writeString(this.mobile);
        parcel.writeString(this.isSelect);
    }

    public void readFromParcel(Parcel parcel) {
        this.f18611id = parcel.readLong();
        this.productName = parcel.readString();
        this.productImgUrl = parcel.readString();
        this.productSubtitle = parcel.readString();
        this.productDesc = parcel.readString();
        this.productId = parcel.readString();
        this.productLinkUrl = parcel.readString();
        this.bottomIcon = parcel.readString();
        this.titleColor = parcel.readString();
        this.isCustom = parcel.readString();
        this.isDefault = parcel.readString();
        this.showType = parcel.readString();
        this.groupName = parcel.readString();
        this.mobile = parcel.readString();
        this.isSelect = parcel.readString();
    }

    public BackgroundSkinBean() {
    }

    protected BackgroundSkinBean(Parcel parcel) {
        this.f18611id = parcel.readLong();
        this.productName = parcel.readString();
        this.productImgUrl = parcel.readString();
        this.productSubtitle = parcel.readString();
        this.productDesc = parcel.readString();
        this.productId = parcel.readString();
        this.productLinkUrl = parcel.readString();
        this.bottomIcon = parcel.readString();
        this.titleColor = parcel.readString();
        this.isCustom = parcel.readString();
        this.isDefault = parcel.readString();
        this.showType = parcel.readString();
        this.groupName = parcel.readString();
        this.mobile = parcel.readString();
        this.isSelect = parcel.readString();
    }
}
