package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CoachInfo extends TransitBaseInfo {
    public static final Parcelable.Creator<CoachInfo> CREATOR = new C2796d();

    /* renamed from: a */
    private double f6661a;

    /* renamed from: b */
    private String f6662b;

    /* renamed from: c */
    private String f6663c;

    /* renamed from: d */
    private String f6664d;

    @Override // com.baidu.mapapi.search.core.TransitBaseInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CoachInfo() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CoachInfo(Parcel parcel) {
        super(parcel);
        this.f6661a = parcel.readDouble();
        this.f6662b = parcel.readString();
        this.f6663c = parcel.readString();
        this.f6664d = parcel.readString();
    }

    public String getProviderName() {
        return this.f6663c;
    }

    public void setProviderName(String str) {
        this.f6663c = str;
    }

    public String getProviderUrl() {
        return this.f6664d;
    }

    public void setProviderUrl(String str) {
        this.f6664d = str;
    }

    public double getPrice() {
        return this.f6661a;
    }

    public void setPrice(double d) {
        this.f6661a = d;
    }

    public String getBooking() {
        return this.f6662b;
    }

    public void setBooking(String str) {
        this.f6662b = str;
    }

    @Override // com.baidu.mapapi.search.core.TransitBaseInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeDouble(this.f6661a);
        parcel.writeString(this.f6662b);
        parcel.writeString(this.f6663c);
        parcel.writeString(this.f6664d);
    }
}
