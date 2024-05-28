package com.baidu.mapapi.search.weather;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: WeatherSearchAlerts.java */
/* renamed from: com.baidu.mapapi.search.weather.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2849c implements Parcelable.Creator<WeatherSearchAlerts> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public WeatherSearchAlerts createFromParcel(Parcel parcel) {
        return new WeatherSearchAlerts(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public WeatherSearchAlerts[] newArray(int i) {
        return new WeatherSearchAlerts[i];
    }
}
