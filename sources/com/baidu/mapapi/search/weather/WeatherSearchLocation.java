package com.baidu.mapapi.search.weather;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WeatherSearchLocation implements Parcelable {
    public static final Parcelable.Creator<WeatherSearchLocation> CREATOR = new C2852f();

    /* renamed from: a */
    private String f6953a;

    /* renamed from: b */
    private String f6954b;

    /* renamed from: c */
    private String f6955c;

    /* renamed from: d */
    private String f6956d;

    /* renamed from: e */
    private String f6957e;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WeatherSearchLocation() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public WeatherSearchLocation(Parcel parcel) {
        this.f6953a = parcel.readString();
        this.f6954b = parcel.readString();
        this.f6955c = parcel.readString();
        this.f6956d = parcel.readString();
        this.f6957e = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f6953a);
        parcel.writeString(this.f6954b);
        parcel.writeString(this.f6955c);
        parcel.writeString(this.f6956d);
        parcel.writeString(this.f6957e);
    }

    public String getCountry() {
        return this.f6953a;
    }

    public void setCountry(String str) {
        this.f6953a = str;
    }

    public String getProvince() {
        return this.f6954b;
    }

    public void setProvince(String str) {
        this.f6954b = str;
    }

    public String getCity() {
        return this.f6955c;
    }

    public void setCity(String str) {
        this.f6955c = str;
    }

    public String getDistrictName() {
        return this.f6956d;
    }

    public void setDistrictName(String str) {
        this.f6956d = str;
    }

    public String getDistrictID() {
        return this.f6957e;
    }

    public void setDistrictID(String str) {
        this.f6957e = str;
    }
}
