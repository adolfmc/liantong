package com.sinovatech.unicom.separatemodule.skin.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BackgroundTongYongBean implements Parcelable {
    public static final Parcelable.Creator<BackgroundTongYongBean> CREATOR = new Parcelable.Creator<BackgroundTongYongBean>() { // from class: com.sinovatech.unicom.separatemodule.skin.entity.BackgroundTongYongBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BackgroundTongYongBean createFromParcel(Parcel parcel) {
            return new BackgroundTongYongBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BackgroundTongYongBean[] newArray(int i) {
            return new BackgroundTongYongBean[i];
        }
    };
    private String defaultPicLink;
    private String previewPicLink;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getDefaultPicLink() {
        return this.defaultPicLink;
    }

    public void setDefaultPicLink(String str) {
        this.defaultPicLink = str;
    }

    public String getPreviewPicLink() {
        return this.previewPicLink;
    }

    public void setPreviewPicLink(String str) {
        this.previewPicLink = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.defaultPicLink);
        parcel.writeString(this.previewPicLink);
    }

    public void readFromParcel(Parcel parcel) {
        this.defaultPicLink = parcel.readString();
        this.previewPicLink = parcel.readString();
    }

    public BackgroundTongYongBean() {
    }

    protected BackgroundTongYongBean(Parcel parcel) {
        this.defaultPicLink = parcel.readString();
        this.previewPicLink = parcel.readString();
    }
}
