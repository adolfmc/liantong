package com.megvii.livenesslib;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FaceEntity implements Parcelable {
    public static final Parcelable.Creator<FaceEntity> CREATOR = new Parcelable.Creator<FaceEntity>() { // from class: com.megvii.livenesslib.FaceEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FaceEntity createFromParcel(Parcel parcel) {
            return new FaceEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FaceEntity[] newArray(int i) {
            return new FaceEntity[i];
        }
    };
    private String actionCount;
    private String actionNum;
    private String otherEnvImgCount;
    private String pageTitle;
    private String showFailToast;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getPageTitle() {
        return this.pageTitle;
    }

    public void setPageTitle(String str) {
        this.pageTitle = str;
    }

    public String getActionCount() {
        return this.actionCount;
    }

    public void setActionCount(String str) {
        this.actionCount = str;
    }

    public String getOtherEnvImgCount() {
        return this.otherEnvImgCount;
    }

    public void setOtherEnvImgCount(String str) {
        this.otherEnvImgCount = str;
    }

    public String getShowFailToast() {
        return this.showFailToast;
    }

    public void setShowFailToast(String str) {
        this.showFailToast = str;
    }

    public String getActionNum() {
        return this.actionNum;
    }

    public void setActionNum(String str) {
        this.actionNum = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.pageTitle);
        parcel.writeString(this.actionCount);
        parcel.writeString(this.otherEnvImgCount);
        parcel.writeString(this.showFailToast);
        parcel.writeString(this.actionNum);
    }

    public void readFromParcel(Parcel parcel) {
        this.pageTitle = parcel.readString();
        this.actionCount = parcel.readString();
        this.otherEnvImgCount = parcel.readString();
        this.showFailToast = parcel.readString();
        this.actionNum = parcel.readString();
    }

    public FaceEntity() {
    }

    protected FaceEntity(Parcel parcel) {
        this.pageTitle = parcel.readString();
        this.actionCount = parcel.readString();
        this.otherEnvImgCount = parcel.readString();
        this.showFailToast = parcel.readString();
        this.actionNum = parcel.readString();
    }
}
