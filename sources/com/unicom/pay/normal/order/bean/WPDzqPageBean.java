package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPDzqPageBean implements Parcelable {
    public static final Parcelable.Creator<WPDzqPageBean> CREATOR = new Parcelable.Creator<WPDzqPageBean>() { // from class: com.unicom.pay.normal.order.bean.WPDzqPageBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPDzqPageBean createFromParcel(Parcel parcel) {
            return new WPDzqPageBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPDzqPageBean[] newArray(int i) {
            return new WPDzqPageBean[i];
        }
    };
    private String dzqAmt;
    private ArrayList<WPDzqInfosBean> dzqInfos;
    private String dzqMsg;
    private String isEnable;
    private String userCheckAmt;

    public WPDzqPageBean() {
    }

    public WPDzqPageBean(Parcel parcel) {
        this.dzqMsg = parcel.readString();
        this.dzqAmt = parcel.readString();
        this.isEnable = parcel.readString();
        this.userCheckAmt = parcel.readString();
        this.dzqInfos = parcel.createTypedArrayList(WPDzqInfosBean.CREATOR);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getDzqAmt() {
        return this.dzqAmt;
    }

    public ArrayList<WPDzqInfosBean> getDzqInfos() {
        return this.dzqInfos;
    }

    public String getDzqMsg() {
        return this.dzqMsg;
    }

    public String getIsEnable() {
        return this.isEnable;
    }

    public String getUserCheckAmt() {
        return this.userCheckAmt;
    }

    public void setDzqAmt(String str) {
        this.dzqAmt = str;
    }

    public void setDzqInfos(ArrayList<WPDzqInfosBean> arrayList) {
        this.dzqInfos = arrayList;
    }

    public void setDzqMsg(String str) {
        this.dzqMsg = str;
    }

    public void setIsEnable(String str) {
        this.isEnable = str;
    }

    public void setUserCheckAmt(String str) {
        this.userCheckAmt = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.dzqMsg);
        parcel.writeString(this.dzqAmt);
        parcel.writeString(this.isEnable);
        parcel.writeString(this.userCheckAmt);
        parcel.writeTypedList(this.dzqInfos);
    }
}
