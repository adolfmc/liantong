package com.baidu.mapapi.search.weather;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WeatherLifeIndexes implements Parcelable {
    public static final Parcelable.Creator<WeatherLifeIndexes> CREATOR = new C2847a();

    /* renamed from: a */
    private String f6920a;

    /* renamed from: b */
    private String f6921b;

    /* renamed from: c */
    private String f6922c;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WeatherLifeIndexes() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public WeatherLifeIndexes(Parcel parcel) {
        setName(parcel.readString());
        setBrief(parcel.readString());
        setDetail(parcel.readString());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getName());
        parcel.writeString(getBrief());
        parcel.writeString(getDetail());
    }

    public String getName() {
        return this.f6920a;
    }

    public void setName(String str) {
        this.f6920a = str;
    }

    public String getBrief() {
        return this.f6921b;
    }

    public void setBrief(String str) {
        this.f6921b = str;
    }

    public String getDetail() {
        return this.f6922c;
    }

    public void setDetail(String str) {
        this.f6922c = str;
    }
}
