package com.unicom.pay.modules.verify.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPPayEndInfoBean implements Parcelable {
    public static final Parcelable.Creator<WPPayEndInfoBean> CREATOR = new Parcelable.Creator<WPPayEndInfoBean>() { // from class: com.unicom.pay.modules.verify.bean.WPPayEndInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPPayEndInfoBean createFromParcel(Parcel parcel) {
            return new WPPayEndInfoBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPPayEndInfoBean[] newArray(int i) {
            return new WPPayEndInfoBean[i];
        }
    };
    private String endMsg;
    private String imageUrl;
    private String lineColor;
    private String startMsg;

    public WPPayEndInfoBean() {
    }

    public WPPayEndInfoBean(Parcel parcel) {
        this.startMsg = parcel.readString();
        this.imageUrl = parcel.readString();
        this.endMsg = parcel.readString();
        this.lineColor = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getEndMsg() {
        return this.endMsg;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getLineColor() {
        return this.lineColor;
    }

    public String getStartMsg() {
        return this.startMsg;
    }

    public void setEndMsg(String str) {
        this.endMsg = str;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public void setLineColor(String str) {
        this.lineColor = str;
    }

    public void setStartMsg(String str) {
        this.startMsg = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.startMsg);
        parcel.writeString(this.imageUrl);
        parcel.writeString(this.endMsg);
        parcel.writeString(this.lineColor);
    }
}
