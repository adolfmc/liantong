package com.baidu.mapapi.search.weather;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: WeatherSearchLocation.java */
/* renamed from: com.baidu.mapapi.search.weather.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2852f implements Parcelable.Creator<WeatherSearchLocation> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public WeatherSearchLocation createFromParcel(Parcel parcel) {
        return new WeatherSearchLocation(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public WeatherSearchLocation[] newArray(int i) {
        return new WeatherSearchLocation[i];
    }
}
