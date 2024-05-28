package com.baidu.mapapi.search.weather;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: WeatherSearchForecasts.java */
/* renamed from: com.baidu.mapapi.search.weather.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2851e implements Parcelable.Creator<WeatherSearchForecasts> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public WeatherSearchForecasts createFromParcel(Parcel parcel) {
        return new WeatherSearchForecasts(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public WeatherSearchForecasts[] newArray(int i) {
        return new WeatherSearchForecasts[i];
    }
}
