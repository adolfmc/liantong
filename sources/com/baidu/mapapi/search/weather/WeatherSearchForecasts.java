package com.baidu.mapapi.search.weather;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WeatherSearchForecasts implements Parcelable {
    public static final Parcelable.Creator<WeatherSearchForecasts> CREATOR = new C2851e();

    /* renamed from: a */
    private String f6942a;

    /* renamed from: b */
    private String f6943b;

    /* renamed from: c */
    private int f6944c;

    /* renamed from: d */
    private int f6945d;

    /* renamed from: e */
    private String f6946e;

    /* renamed from: f */
    private String f6947f;

    /* renamed from: g */
    private String f6948g;

    /* renamed from: h */
    private String f6949h;

    /* renamed from: i */
    private String f6950i;

    /* renamed from: j */
    private String f6951j;

    /* renamed from: k */
    private int f6952k;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WeatherSearchForecasts() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public WeatherSearchForecasts(Parcel parcel) {
        this.f6942a = parcel.readString();
        this.f6943b = parcel.readString();
        this.f6944c = parcel.readInt();
        this.f6945d = parcel.readInt();
        this.f6946e = parcel.readString();
        this.f6947f = parcel.readString();
        this.f6948g = parcel.readString();
        this.f6949h = parcel.readString();
        this.f6950i = parcel.readString();
        this.f6951j = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f6942a);
        parcel.writeString(this.f6943b);
        parcel.writeInt(this.f6944c);
        parcel.writeInt(this.f6945d);
        parcel.writeString(this.f6946e);
        parcel.writeString(this.f6947f);
        parcel.writeString(this.f6948g);
        parcel.writeString(this.f6949h);
        parcel.writeString(this.f6950i);
        parcel.writeString(this.f6951j);
    }

    public String getDate() {
        return this.f6942a;
    }

    public void setDate(String str) {
        this.f6942a = str;
    }

    public String getWeek() {
        return this.f6943b;
    }

    public void setWeek(String str) {
        this.f6943b = str;
    }

    public int getLowestTemp() {
        return this.f6944c;
    }

    public void setLowestTemp(int i) {
        this.f6944c = i;
    }

    public int getHighestTemp() {
        return this.f6945d;
    }

    public void setHighestTemp(int i) {
        this.f6945d = i;
    }

    public String getWindPowerDay() {
        return this.f6946e;
    }

    public void setWindPowerDay(String str) {
        this.f6946e = str;
    }

    public String getWindPowerNight() {
        return this.f6947f;
    }

    public void setWindPowerNight(String str) {
        this.f6947f = str;
    }

    public String getWindDirectionDay() {
        return this.f6948g;
    }

    public void setWindDirectionDay(String str) {
        this.f6948g = str;
    }

    public String getWindDirectionNight() {
        return this.f6949h;
    }

    public void setWindDirectionNight(String str) {
        this.f6949h = str;
    }

    public String getPhenomenonDay() {
        return this.f6950i;
    }

    public void setPhenomenonDay(String str) {
        this.f6950i = str;
    }

    public String getPhenomenonNight() {
        return this.f6951j;
    }

    public void setPhenomenonNight(String str) {
        this.f6951j = str;
    }

    public int getAirQualityIndex() {
        return this.f6952k;
    }

    public void setAirQualityIndex(int i) {
        this.f6952k = i;
    }
}
