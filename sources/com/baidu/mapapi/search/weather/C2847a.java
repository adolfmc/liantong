package com.baidu.mapapi.search.weather;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: WeatherLifeIndexes.java */
/* renamed from: com.baidu.mapapi.search.weather.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2847a implements Parcelable.Creator<WeatherLifeIndexes> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public WeatherLifeIndexes createFromParcel(Parcel parcel) {
        return new WeatherLifeIndexes(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public WeatherLifeIndexes[] newArray(int i) {
        return new WeatherLifeIndexes[i];
    }
}
