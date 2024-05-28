package com.baidu.mapapi.search.weather;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WeatherSearchForecastForHours implements Parcelable {
    public static final Parcelable.Creator<WeatherSearchForecastForHours> CREATOR = new C2850d();

    /* renamed from: a */
    private int f6934a;

    /* renamed from: b */
    private String f6935b;

    /* renamed from: c */
    private String f6936c;

    /* renamed from: d */
    private String f6937d;

    /* renamed from: e */
    private int f6938e;

    /* renamed from: f */
    private int f6939f;

    /* renamed from: g */
    private String f6940g;

    /* renamed from: h */
    private int f6941h;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WeatherSearchForecastForHours() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public WeatherSearchForecastForHours(Parcel parcel) {
        this.f6934a = parcel.readInt();
        this.f6935b = parcel.readString();
        this.f6936c = parcel.readString();
        this.f6937d = parcel.readString();
        this.f6938e = parcel.readInt();
        this.f6939f = parcel.readInt();
        this.f6940g = parcel.readString();
        this.f6941h = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f6934a);
        parcel.writeString(this.f6935b);
        parcel.writeString(this.f6936c);
        parcel.writeString(this.f6937d);
        parcel.writeInt(this.f6938e);
        parcel.writeInt(this.f6939f);
        parcel.writeString(this.f6940g);
        parcel.writeInt(this.f6941h);
    }

    public int getRelativeHumidity() {
        return this.f6934a;
    }

    public void setRelativeHumidity(int i) {
        this.f6934a = i;
    }

    public String getDataTime() {
        return this.f6935b;
    }

    public void setDataTime(String str) {
        this.f6935b = str;
    }

    public String getWindDirection() {
        return this.f6936c;
    }

    public void setWindDirection(String str) {
        this.f6936c = str;
    }

    public String getWindPower() {
        return this.f6937d;
    }

    public void setWindPower(String str) {
        this.f6937d = str;
    }

    public int getTemperature() {
        return this.f6938e;
    }

    public void setTemperature(int i) {
        this.f6938e = i;
    }

    public int getClouds() {
        return this.f6939f;
    }

    public void setClouds(int i) {
        this.f6939f = i;
    }

    public String getPhenomenon() {
        return this.f6940g;
    }

    public void setPhenomenon(String str) {
        this.f6940g = str;
    }

    public int getHourlyPrecipitation() {
        return this.f6941h;
    }

    public void setHourlyPrecipitation(int i) {
        this.f6941h = i;
    }
}
