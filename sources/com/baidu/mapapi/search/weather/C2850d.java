package com.baidu.mapapi.search.weather;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: WeatherSearchForecastForHours.java */
/* renamed from: com.baidu.mapapi.search.weather.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2850d implements Parcelable.Creator<WeatherSearchForecastForHours> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public WeatherSearchForecastForHours createFromParcel(Parcel parcel) {
        return new WeatherSearchForecastForHours(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public WeatherSearchForecastForHours[] newArray(int i) {
        return new WeatherSearchForecastForHours[i];
    }
}
