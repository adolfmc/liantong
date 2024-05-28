package com.baidu.mapapi.search.weather;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WeatherSearchRealTime implements Parcelable {
    public static final Parcelable.Creator<WeatherSearchRealTime> CREATOR = new C2853g();

    /* renamed from: a */
    private int f6963a;

    /* renamed from: b */
    private int f6964b;

    /* renamed from: c */
    private String f6965c;

    /* renamed from: d */
    private String f6966d;

    /* renamed from: e */
    private String f6967e;

    /* renamed from: f */
    private int f6968f;

    /* renamed from: g */
    private String f6969g;

    /* renamed from: h */
    private int f6970h;

    /* renamed from: i */
    private float f6971i;

    /* renamed from: j */
    private int f6972j;

    /* renamed from: k */
    private int f6973k;

    /* renamed from: l */
    private int f6974l;

    /* renamed from: m */
    private int f6975m;

    /* renamed from: n */
    private int f6976n;

    /* renamed from: o */
    private int f6977o;

    /* renamed from: p */
    private int f6978p;

    /* renamed from: q */
    private float f6979q;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WeatherSearchRealTime() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public WeatherSearchRealTime(Parcel parcel) {
        this.f6963a = parcel.readInt();
        this.f6964b = parcel.readInt();
        this.f6965c = parcel.readString();
        this.f6966d = parcel.readString();
        this.f6967e = parcel.readString();
        this.f6968f = parcel.readInt();
        this.f6969g = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f6963a);
        parcel.writeInt(this.f6964b);
        parcel.writeString(this.f6965c);
        parcel.writeString(this.f6966d);
        parcel.writeString(this.f6967e);
        parcel.writeInt(this.f6968f);
        parcel.writeString(this.f6969g);
    }

    public int getRelativeHumidity() {
        return this.f6963a;
    }

    public void setRelativeHumidity(int i) {
        this.f6963a = i;
    }

    public int getSensoryTemp() {
        return this.f6964b;
    }

    public void setSensoryTemp(int i) {
        this.f6964b = i;
    }

    public String getPhenomenon() {
        return this.f6965c;
    }

    public void setPhenomenon(String str) {
        this.f6965c = str;
    }

    public String getWindDirection() {
        return this.f6966d;
    }

    public void setWindDirection(String str) {
        this.f6966d = str;
    }

    public String getUpdateTime() {
        return this.f6967e;
    }

    public void setUpdateTime(String str) {
        this.f6967e = str;
    }

    public int getTemperature() {
        return this.f6968f;
    }

    public void setTemperature(int i) {
        this.f6968f = i;
    }

    public String getWindPower() {
        return this.f6969g;
    }

    public void setWindPower(String str) {
        this.f6969g = str;
    }

    public int getClouds() {
        return this.f6970h;
    }

    public void setClouds(int i) {
        this.f6970h = i;
    }

    public float getHourlyPrecipitation() {
        return this.f6971i;
    }

    public void setHourlyPrecipitation(float f) {
        this.f6971i = f;
    }

    public int getVisibility() {
        return this.f6972j;
    }

    public void setVisibility(int i) {
        this.f6972j = i;
    }

    public int getO3() {
        return this.f6973k;
    }

    public void setO3(int i) {
        this.f6973k = i;
    }

    public int getPM2_5() {
        return this.f6974l;
    }

    public void setPM2_5(int i) {
        this.f6974l = i;
    }

    public int getNO2() {
        return this.f6975m;
    }

    public void setNO2(int i) {
        this.f6975m = i;
    }

    public int getSO2() {
        return this.f6976n;
    }

    public void setSO2(int i) {
        this.f6976n = i;
    }

    public int getAirQualityIndex() {
        return this.f6977o;
    }

    public void setAirQualityIndex(int i) {
        this.f6977o = i;
    }

    public int getPM10() {
        return this.f6978p;
    }

    public void setPM10(int i) {
        this.f6978p = i;
    }

    public float getCO() {
        return this.f6979q;
    }

    public void setCO(float f) {
        this.f6979q = f;
    }
}
