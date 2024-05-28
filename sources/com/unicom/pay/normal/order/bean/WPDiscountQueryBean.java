package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.unicom.pay.normal.discount.bean.WPDiscountInfoBean;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPDiscountQueryBean implements Parcelable {
    public static final String CHECKED = "1";
    public static final Parcelable.Creator<WPDiscountQueryBean> CREATOR = new Parcelable.Creator<WPDiscountQueryBean>() { // from class: com.unicom.pay.normal.order.bean.WPDiscountQueryBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPDiscountQueryBean createFromParcel(Parcel parcel) {
            return new WPDiscountQueryBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPDiscountQueryBean[] newArray(int i) {
            return new WPDiscountQueryBean[i];
        }
    };
    public static final String UNCHECKED = "0";
    private ArrayList<WPDiscountInfoBean> discountInfos;
    private String discountMsg;
    private String dzqDiscountExpand;

    public WPDiscountQueryBean(Parcel parcel) {
        this.discountMsg = parcel.readString();
        this.discountInfos = parcel.createTypedArrayList(WPDiscountInfoBean.CREATOR);
        this.dzqDiscountExpand = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ArrayList<WPDiscountInfoBean> getDiscountInfos() {
        return this.discountInfos;
    }

    public String getDiscountMsg() {
        return this.discountMsg;
    }

    public String getDzqDiscountExpand() {
        return this.dzqDiscountExpand;
    }

    public void setDiscountInfos(ArrayList<WPDiscountInfoBean> arrayList) {
        this.discountInfos = arrayList;
    }

    public void setDiscountMsg(String str) {
        this.discountMsg = str;
    }

    public void setDzqDiscountExpand(String str) {
        this.dzqDiscountExpand = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.discountMsg);
        parcel.writeTypedList(this.discountInfos);
        parcel.writeString(this.dzqDiscountExpand);
    }
}
