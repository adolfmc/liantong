package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.unicom.pay.modules.result.bean.WPCheckSignResult;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPOrderPayBeforeBean implements Parcelable {
    public static final Parcelable.Creator<WPOrderPayBeforeBean> CREATOR = new Parcelable.Creator<WPOrderPayBeforeBean>() { // from class: com.unicom.pay.normal.order.bean.WPOrderPayBeforeBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPOrderPayBeforeBean createFromParcel(Parcel parcel) {
            return new WPOrderPayBeforeBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPOrderPayBeforeBean[] newArray(int i) {
            return new WPOrderPayBeforeBean[i];
        }
    };
    private String payBeforeType;
    private WPCheckSignResult quickPayBeforeResp;
    private WPPayBeforeBean wappayPayBeforeResp;

    public WPOrderPayBeforeBean() {
    }

    public WPOrderPayBeforeBean(Parcel parcel) {
        this.payBeforeType = parcel.readString();
        this.wappayPayBeforeResp = (WPPayBeforeBean) parcel.readParcelable(WPPayBeforeBean.class.getClassLoader());
        this.quickPayBeforeResp = (WPCheckSignResult) parcel.readParcelable(WPCheckSignResult.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getPayBeforeType() {
        return this.payBeforeType;
    }

    public WPCheckSignResult getQuickPayBeforeResp() {
        return this.quickPayBeforeResp;
    }

    public WPPayBeforeBean getWappayPayBeforeResp() {
        return this.wappayPayBeforeResp;
    }

    public void setPayBeforeType(String str) {
        this.payBeforeType = str;
    }

    public void setQuickPayBeforeResp(WPCheckSignResult wPCheckSignResult) {
        this.quickPayBeforeResp = wPCheckSignResult;
    }

    public void setWappayPayBeforeResp(WPPayBeforeBean wPPayBeforeBean) {
        this.wappayPayBeforeResp = wPPayBeforeBean;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.payBeforeType);
        parcel.writeParcelable(this.wappayPayBeforeResp, i);
        parcel.writeParcelable(this.quickPayBeforeResp, i);
    }
}
