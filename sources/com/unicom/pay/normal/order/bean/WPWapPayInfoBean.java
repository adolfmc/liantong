package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPWapPayInfoBean implements Parcelable {
    public static final Parcelable.Creator<WPWapPayInfoBean> CREATOR = new Parcelable.Creator<WPWapPayInfoBean>() { // from class: com.unicom.pay.normal.order.bean.WPWapPayInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPWapPayInfoBean createFromParcel(Parcel parcel) {
            return new WPWapPayInfoBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPWapPayInfoBean[] newArray(int i) {
            return new WPWapPayInfoBean[i];
        }
    };
    private List<WPPayInfoBean> activityList;
    private String activityTitle;
    private String secondTitle;
    private String secondTitleIcon;
    private String title;
    private String titleIcon;
    private List<WPPayInfoBean> wapPayList;
    private String wapPayTitle;

    public WPWapPayInfoBean() {
    }

    public WPWapPayInfoBean(Parcel parcel) {
        this.title = parcel.readString();
        this.titleIcon = parcel.readString();
        this.secondTitle = parcel.readString();
        this.secondTitleIcon = parcel.readString();
        this.wapPayTitle = parcel.readString();
        Parcelable.Creator<WPPayInfoBean> creator = WPPayInfoBean.CREATOR;
        this.wapPayList = parcel.createTypedArrayList(creator);
        this.activityTitle = parcel.readString();
        this.activityList = parcel.createTypedArrayList(creator);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<WPPayInfoBean> getActivityList() {
        return this.activityList;
    }

    public String getActivityTitle() {
        return this.activityTitle;
    }

    public String getSecondTitle() {
        return this.secondTitle;
    }

    public String getSecondTitleIcon() {
        return this.secondTitleIcon;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTitleIcon() {
        return this.titleIcon;
    }

    public List<WPPayInfoBean> getWapPayList() {
        return this.wapPayList;
    }

    public String getWapPayTitle() {
        return this.wapPayTitle;
    }

    public void setActivityList(List<WPPayInfoBean> list) {
        this.activityList = list;
    }

    public void setActivityTitle(String str) {
        this.activityTitle = str;
    }

    public void setSecondTitle(String str) {
        this.secondTitle = str;
    }

    public void setSecondTitleIcon(String str) {
        this.secondTitleIcon = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTitleIcon(String str) {
        this.titleIcon = str;
    }

    public void setWapPayList(List<WPPayInfoBean> list) {
        this.wapPayList = list;
    }

    public void setWapPayTitle(String str) {
        this.wapPayTitle = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.titleIcon);
        parcel.writeString(this.secondTitle);
        parcel.writeString(this.secondTitleIcon);
        parcel.writeString(this.wapPayTitle);
        parcel.writeTypedList(this.wapPayList);
        parcel.writeString(this.activityTitle);
        parcel.writeTypedList(this.activityList);
    }
}
