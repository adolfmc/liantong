package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.unicom.pay.normal.discount.bean.WPDiscountListBean;
import java.util.List;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPUnionFqInfoBean implements Parcelable {
    public static final Parcelable.Creator<WPUnionFqInfoBean> CREATOR = new Parcelable.Creator<WPUnionFqInfoBean>() { // from class: com.unicom.pay.normal.order.bean.WPUnionFqInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPUnionFqInfoBean createFromParcel(Parcel parcel) {
            return new WPUnionFqInfoBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPUnionFqInfoBean[] newArray(int i) {
            return new WPUnionFqInfoBean[i];
        }
    };
    private String buttonInfo;
    private WPExtInfoBean extInfo;
    private WPDiscountListBean fqDiscountInfos;
    private List<WPFqInfoBean> fqInfos;
    private WPOrderDetailInfoBean orderInfo;

    public WPUnionFqInfoBean() {
    }

    public WPUnionFqInfoBean(Parcel parcel) {
        this.buttonInfo = parcel.readString();
        this.extInfo = (WPExtInfoBean) parcel.readParcelable(WPExtInfoBean.class.getClassLoader());
        this.orderInfo = (WPOrderDetailInfoBean) parcel.readParcelable(WPOrderDetailInfoBean.class.getClassLoader());
        this.fqInfos = parcel.createTypedArrayList(WPFqInfoBean.CREATOR);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getButtonInfo() {
        return this.buttonInfo;
    }

    public WPExtInfoBean getExtInfo() {
        return this.extInfo;
    }

    public WPDiscountListBean getFqDiscountInfos() {
        return this.fqDiscountInfos;
    }

    public List<WPFqInfoBean> getFqInfos() {
        return this.fqInfos;
    }

    public WPOrderDetailInfoBean getOrderInfo() {
        return this.orderInfo;
    }

    public void setButtonInfo(String str) {
        this.buttonInfo = str;
    }

    public void setExtInfo(WPExtInfoBean wPExtInfoBean) {
        this.extInfo = wPExtInfoBean;
    }

    public void setFqDiscountInfos(WPDiscountListBean wPDiscountListBean) {
        this.fqDiscountInfos = wPDiscountListBean;
    }

    public void setFqInfos(List<WPFqInfoBean> list) {
        this.fqInfos = list;
    }

    public void setOrderInfo(WPOrderDetailInfoBean wPOrderDetailInfoBean) {
        this.orderInfo = wPOrderDetailInfoBean;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.buttonInfo);
        parcel.writeParcelable(this.extInfo, i);
        parcel.writeParcelable(this.orderInfo, i);
        parcel.writeTypedList(this.fqInfos);
    }
}
