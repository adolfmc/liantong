package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CityInfo implements Parcelable {
    public static final Parcelable.Creator<CityInfo> CREATOR = new C2795c();
    public String city;
    public int num;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CityInfo() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CityInfo(Parcel parcel) {
        this.city = parcel.readString();
        this.num = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.city);
        parcel.writeInt(this.num);
    }
}
