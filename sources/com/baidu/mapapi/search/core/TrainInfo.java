package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TrainInfo extends TransitBaseInfo {
    public static final Parcelable.Creator<TrainInfo> CREATOR = new C2807p();

    /* renamed from: a */
    private double f6717a;

    /* renamed from: b */
    private String f6718b;

    @Override // com.baidu.mapapi.search.core.TransitBaseInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TrainInfo() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TrainInfo(Parcel parcel) {
        super(parcel);
        this.f6717a = parcel.readDouble();
        this.f6718b = parcel.readString();
    }

    public double getPrice() {
        return this.f6717a;
    }

    public void setPrice(double d) {
        this.f6717a = d;
    }

    public String getBooking() {
        return this.f6718b;
    }

    public void setBooking(String str) {
        this.f6718b = str;
    }

    @Override // com.baidu.mapapi.search.core.TransitBaseInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeDouble(this.f6717a);
        parcel.writeString(this.f6718b);
    }
}
