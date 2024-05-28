package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPQueryPayInfoListBean implements Parcelable {
    public static final Parcelable.Creator<WPQueryPayInfoListBean> CREATOR = new Parcelable.Creator<WPQueryPayInfoListBean>() { // from class: com.unicom.pay.normal.order.bean.WPQueryPayInfoListBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPQueryPayInfoListBean createFromParcel(Parcel parcel) {
            return new WPQueryPayInfoListBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPQueryPayInfoListBean[] newArray(int i) {
            return new WPQueryPayInfoListBean[i];
        }
    };
    private ArrayList<WPPayInfoBean> wapPayList;

    public WPQueryPayInfoListBean() {
    }

    public WPQueryPayInfoListBean(Parcel parcel) {
        this.wapPayList = parcel.createTypedArrayList(WPPayInfoBean.CREATOR);
    }

    public WPQueryPayInfoListBean(ArrayList<WPPayInfoBean> arrayList) {
        this.wapPayList = arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ArrayList<WPPayInfoBean> getWapPayList() {
        return this.wapPayList;
    }

    public void setWapPayList(ArrayList<WPPayInfoBean> arrayList) {
        this.wapPayList = arrayList;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.wapPayList);
    }
}
