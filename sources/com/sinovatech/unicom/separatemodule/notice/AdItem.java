package com.sinovatech.unicom.separatemodule.notice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AdItem implements Parcelable {
    public static final Parcelable.Creator<AdItem> CREATOR = new Parcelable.Creator<AdItem>() { // from class: com.sinovatech.unicom.separatemodule.notice.AdItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AdItem createFromParcel(Parcel parcel) {
            return new AdItem(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AdItem[] newArray(int i) {
            return new AdItem[i];
        }
    };
    private String imgSrc;
    private int index;
    private String isSelected;
    private String outlink;
    private String targetUrl;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AdItem() {
    }

    public AdItem(String str, String str2, int i, String str3, String str4) {
        this.imgSrc = str;
        this.targetUrl = str2;
        this.index = i;
        this.outlink = str3;
        this.isSelected = str4;
    }

    public String getImgSrc() {
        return this.imgSrc;
    }

    public void setImgSrc(String str) {
        this.imgSrc = str;
    }

    public String getTargetUrl() {
        return this.targetUrl;
    }

    public void setTargetUrl(String str) {
        this.targetUrl = str;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public String getOutlink() {
        return this.outlink;
    }

    public void setOutlink(String str) {
        this.outlink = str;
    }

    public String getIsSelected() {
        return this.isSelected;
    }

    public void setIsSelected(String str) {
        this.isSelected = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.imgSrc);
        parcel.writeString(this.targetUrl);
        parcel.writeInt(this.index);
        parcel.writeString(this.outlink);
        parcel.writeString(this.isSelected);
    }
}
