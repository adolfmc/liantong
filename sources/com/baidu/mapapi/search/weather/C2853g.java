package com.baidu.mapapi.search.weather;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: WeatherSearchRealTime.java */
/* renamed from: com.baidu.mapapi.search.weather.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2853g implements Parcelable.Creator<WeatherSearchRealTime> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public WeatherSearchRealTime createFromParcel(Parcel parcel) {
        return new WeatherSearchRealTime(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public WeatherSearchRealTime[] newArray(int i) {
        return new WeatherSearchRealTime[i];
    }
}
