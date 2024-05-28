package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPOrderDetailNoticeBean implements Parcelable {
    public static final Parcelable.Creator<WPOrderDetailNoticeBean> CREATOR = new Parcelable.Creator<WPOrderDetailNoticeBean>() { // from class: com.unicom.pay.normal.order.bean.WPOrderDetailNoticeBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPOrderDetailNoticeBean createFromParcel(Parcel parcel) {
            return new WPOrderDetailNoticeBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPOrderDetailNoticeBean[] newArray(int i) {
            return new WPOrderDetailNoticeBean[i];
        }
    };
    public static final String EVENT_TYPE_H5 = "h5";
    public static final String EVENT_TYPE_NONE = "none";
    public static final String EVENT_TYPE_OPEN = "open";
    private String noticeEventType;
    private String noticeH5Url;
    private String noticeIcon;
    private String noticeShowType;
    private String noticeTitle;
    private String noticeType;

    public WPOrderDetailNoticeBean() {
    }

    public WPOrderDetailNoticeBean(Parcel parcel) {
        this.noticeTitle = parcel.readString();
        this.noticeIcon = parcel.readString();
        this.noticeShowType = parcel.readString();
        this.noticeEventType = parcel.readString();
        this.noticeH5Url = parcel.readString();
        this.noticeType = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getNoticeEventType() {
        return this.noticeEventType;
    }

    public String getNoticeH5Url() {
        return this.noticeH5Url;
    }

    public String getNoticeIcon() {
        return this.noticeIcon;
    }

    public String getNoticeShowType() {
        return this.noticeShowType;
    }

    public String getNoticeTitle() {
        return this.noticeTitle;
    }

    public String getNoticeType() {
        return this.noticeType;
    }

    public void setNoticeEventType(String str) {
        this.noticeEventType = str;
    }

    public void setNoticeH5Url(String str) {
        this.noticeH5Url = str;
    }

    public void setNoticeIcon(String str) {
        this.noticeIcon = str;
    }

    public void setNoticeShowType(String str) {
        this.noticeShowType = str;
    }

    public void setNoticeTitle(String str) {
        this.noticeTitle = str;
    }

    public void setNoticeType(String str) {
        this.noticeType = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.noticeTitle);
        parcel.writeString(this.noticeIcon);
        parcel.writeString(this.noticeShowType);
        parcel.writeString(this.noticeEventType);
        parcel.writeString(this.noticeH5Url);
        parcel.writeString(this.noticeType);
    }
}
