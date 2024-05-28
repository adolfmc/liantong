package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPToolFqInfoBean implements Parcelable {
    public static final Parcelable.Creator<WPToolFqInfoBean> CREATOR = new Parcelable.Creator<WPToolFqInfoBean>() { // from class: com.unicom.pay.normal.order.bean.WPToolFqInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPToolFqInfoBean createFromParcel(Parcel parcel) {
            return new WPToolFqInfoBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPToolFqInfoBean[] newArray(int i) {
            return new WPToolFqInfoBean[i];
        }
    };
    private WPToolFqHeaderBean fqHead;
    private List<WPToolFqNumInfoBean> fqNumInfos;
    private WPFqTailBean fqTail;

    public WPToolFqInfoBean() {
    }

    public WPToolFqInfoBean(Parcel parcel) {
        this.fqHead = (WPToolFqHeaderBean) parcel.readParcelable(WPToolFqHeaderBean.class.getClassLoader());
        this.fqNumInfos = parcel.createTypedArrayList(WPToolFqNumInfoBean.CREATOR);
        this.fqTail = (WPFqTailBean) parcel.readParcelable(WPFqTailBean.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WPToolFqHeaderBean getFqHead() {
        return this.fqHead;
    }

    public List<WPToolFqNumInfoBean> getFqNumInfos() {
        return this.fqNumInfos;
    }

    public WPFqTailBean getFqTail() {
        return this.fqTail;
    }

    public void setFqHead(WPToolFqHeaderBean wPToolFqHeaderBean) {
        this.fqHead = wPToolFqHeaderBean;
    }

    public void setFqNumInfos(List<WPToolFqNumInfoBean> list) {
        this.fqNumInfos = list;
    }

    public void setFqTail(WPFqTailBean wPFqTailBean) {
        this.fqTail = wPFqTailBean;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.fqHead, i);
        parcel.writeTypedList(this.fqNumInfos);
        parcel.writeParcelable(this.fqTail, i);
    }
}
