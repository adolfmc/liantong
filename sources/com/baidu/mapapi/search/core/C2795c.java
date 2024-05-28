package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: CityInfo.java */
/* renamed from: com.baidu.mapapi.search.core.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2795c implements Parcelable.Creator<CityInfo> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public CityInfo[] newArray(int i) {
        return new CityInfo[i];
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public CityInfo createFromParcel(Parcel parcel) {
        return new CityInfo(parcel);
    }
}
