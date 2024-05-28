package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPOrderSubjectBean implements Parcelable {
    public static final Parcelable.Creator<WPOrderSubjectBean> CREATOR = new Parcelable.Creator<WPOrderSubjectBean>() { // from class: com.unicom.pay.normal.order.bean.WPOrderSubjectBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPOrderSubjectBean createFromParcel(Parcel parcel) {
            return new WPOrderSubjectBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPOrderSubjectBean[] newArray(int i) {
            return new WPOrderSubjectBean[i];
        }
    };
    private String amount;
    private String goodsName;

    public WPOrderSubjectBean(Parcel parcel) {
        this.amount = parcel.readString();
        this.goodsName = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAmount() {
        return this.amount;
    }

    public String getGoodsName() {
        return this.goodsName;
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    public void setGoodsName(String str) {
        this.goodsName = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.amount);
        parcel.writeString(this.goodsName);
    }
}
