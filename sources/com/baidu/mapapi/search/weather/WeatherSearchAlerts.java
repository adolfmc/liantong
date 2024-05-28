package com.baidu.mapapi.search.weather;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WeatherSearchAlerts implements Parcelable {
    public static final Parcelable.Creator<WeatherSearchAlerts> CREATOR = new C2849c();

    /* renamed from: a */
    private String f6930a;

    /* renamed from: b */
    private String f6931b;

    /* renamed from: c */
    private String f6932c;

    /* renamed from: d */
    private String f6933d;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WeatherSearchAlerts() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public WeatherSearchAlerts(Parcel parcel) {
        this.f6930a = parcel.readString();
        this.f6931b = parcel.readString();
        this.f6932c = parcel.readString();
        this.f6933d = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f6930a);
        parcel.writeString(this.f6931b);
        parcel.writeString(this.f6932c);
        parcel.writeString(this.f6933d);
    }

    public String getType() {
        return this.f6930a;
    }

    public void setType(String str) {
        this.f6930a = str;
    }

    public String getLevel() {
        return this.f6931b;
    }

    public void setLevel(String str) {
        this.f6931b = str;
    }

    public String getTitle() {
        return this.f6932c;
    }

    public void setTitle(String str) {
        this.f6932c = str;
    }

    public String getDesc() {
        return this.f6933d;
    }

    public void setDesc(String str) {
        this.f6933d = str;
    }
}
