package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PlaneInfo extends TransitBaseInfo {
    public static final Parcelable.Creator<PlaneInfo> CREATOR = new C2797e();

    /* renamed from: a */
    private double f6665a;

    /* renamed from: b */
    private String f6666b;

    /* renamed from: c */
    private double f6667c;

    /* renamed from: d */
    private String f6668d;

    @Override // com.baidu.mapapi.search.core.TransitBaseInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PlaneInfo() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PlaneInfo(Parcel parcel) {
        super(parcel);
        this.f6665a = parcel.readDouble();
        this.f6666b = parcel.readString();
        this.f6667c = parcel.readDouble();
        this.f6668d = parcel.readString();
    }

    public double getPrice() {
        return this.f6667c;
    }

    public void setPrice(double d) {
        this.f6667c = d;
    }

    public double getDiscount() {
        return this.f6665a;
    }

    public void setDiscount(double d) {
        this.f6665a = d;
    }

    public String getAirlines() {
        return this.f6666b;
    }

    public void setAirlines(String str) {
        this.f6666b = str;
    }

    public String getBooking() {
        return this.f6668d;
    }

    public void setBooking(String str) {
        this.f6668d = str;
    }

    @Override // com.baidu.mapapi.search.core.TransitBaseInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeDouble(this.f6665a);
        parcel.writeString(this.f6666b);
        parcel.writeDouble(this.f6667c);
        parcel.writeString(this.f6668d);
    }
}
