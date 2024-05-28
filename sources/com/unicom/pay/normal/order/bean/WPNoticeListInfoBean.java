package com.unicom.pay.normal.order.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPNoticeListInfoBean implements Parcelable {
    public static final Parcelable.Creator<WPNoticeListInfoBean> CREATOR = new Parcelable.Creator<WPNoticeListInfoBean>() { // from class: com.unicom.pay.normal.order.bean.WPNoticeListInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPNoticeListInfoBean createFromParcel(Parcel parcel) {
            return new WPNoticeListInfoBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WPNoticeListInfoBean[] newArray(int i) {
            return new WPNoticeListInfoBean[i];
        }
    };
    private List<WPNoticeInfoBean> noticeContents;

    public WPNoticeListInfoBean(Parcel parcel) {
    }

    public WPNoticeListInfoBean(List<WPNoticeInfoBean> list) {
        this.noticeContents = list;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<WPNoticeInfoBean> getNoticeContents() {
        return this.noticeContents;
    }

    public void setNoticeContents(List<WPNoticeInfoBean> list) {
        this.noticeContents = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }
}
