package com.unionpay.tsmservice.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\11617560_dexfile_execute.dex */
public class SeAppListItem implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.data.SeAppListItem.1
        @Override // android.os.Parcelable.Creator
        public final SeAppListItem createFromParcel(Parcel parcel) {
            return new SeAppListItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final SeAppListItem[] newArray(int i) {
            return new SeAppListItem[i];
        }
    };
    private AppDetail mAppDetail;

    public SeAppListItem() {
    }

    public SeAppListItem(Parcel parcel) {
        this.mAppDetail = (AppDetail) parcel.readParcelable(AppDetail.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AppDetail getAppDetail() {
        return this.mAppDetail;
    }

    public void setAppDetail(AppDetail appDetail) {
        this.mAppDetail = appDetail;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mAppDetail, i);
    }
}
