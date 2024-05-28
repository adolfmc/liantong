package com.baidu.mapapi.search.weather;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: WeatherResult.java */
/* renamed from: com.baidu.mapapi.search.weather.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2848b implements Parcelable.Creator<WeatherResult> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public WeatherResult createFromParcel(Parcel parcel) {
        return new WeatherResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public WeatherResult[] newArray(int i) {
        return new WeatherResult[i];
    }
}
