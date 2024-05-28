package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.unicom.pay.normal.discount.bean.WPDiscountDetailBean;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPDzqInfosBean implements Parcelable {
    public static final Parcelable.Creator<WPDzqInfosBean> CREATOR = new Parcelable.Creator<WPDzqInfosBean>() { // from class: com.unicom.pay.normal.order.bean.WPDzqInfosBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPDzqInfosBean createFromParcel(Parcel parcel) {
            return new WPDzqInfosBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPDzqInfosBean[] newArray(int i) {
            return new WPDzqInfosBean[i];
        }
    };
    public static final String DZQ_TYPE_MAN = "1";
    public static final String DZQ_TYPE_ZHI = "2";
    private String dzqAmount;
    private String dzqDesc;
    private ArrayList<WPDiscountDetailBean> dzqDetails;
    private String dzqIconUrl;
    private String dzqName;
    private String dzqTail;
    private String dzqType;

    public WPDzqInfosBean() {
    }

    public WPDzqInfosBean(Parcel parcel) {
        this.dzqIconUrl = parcel.readString();
        this.dzqName = parcel.readString();
        this.dzqType = parcel.readString();
        this.dzqDesc = parcel.readString();
        this.dzqAmount = parcel.readString();
        this.dzqDetails = parcel.createTypedArrayList(WPDiscountDetailBean.CREATOR);
        this.dzqTail = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getDzqAmount() {
        return this.dzqAmount;
    }

    public String getDzqDesc() {
        return this.dzqDesc;
    }

    public ArrayList<WPDiscountDetailBean> getDzqDetails() {
        return this.dzqDetails;
    }

    public String getDzqIconUrl() {
        return this.dzqIconUrl;
    }

    public String getDzqName() {
        return this.dzqName;
    }

    public String getDzqTail() {
        return this.dzqTail;
    }

    public String getDzqType() {
        return this.dzqType;
    }

    public void setDzqAmount(String str) {
        this.dzqAmount = str;
    }

    public void setDzqDesc(String str) {
        this.dzqDesc = str;
    }

    public void setDzqDetails(ArrayList<WPDiscountDetailBean> arrayList) {
        this.dzqDetails = arrayList;
    }

    public void setDzqIconUrl(String str) {
        this.dzqIconUrl = str;
    }

    public void setDzqName(String str) {
        this.dzqName = str;
    }

    public void setDzqTail(String str) {
        this.dzqTail = str;
    }

    public void setDzqType(String str) {
        this.dzqType = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.dzqIconUrl);
        parcel.writeString(this.dzqName);
        parcel.writeString(this.dzqType);
        parcel.writeString(this.dzqDesc);
        parcel.writeString(this.dzqAmount);
        parcel.writeTypedList(this.dzqDetails);
        parcel.writeString(this.dzqTail);
    }
}
