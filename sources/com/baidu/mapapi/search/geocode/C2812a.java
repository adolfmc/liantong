package com.baidu.mapapi.search.geocode;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: GeoCodeResult.java */
/* renamed from: com.baidu.mapapi.search.geocode.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2812a implements Parcelable.Creator<GeoCodeResult> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public GeoCodeResult createFromParcel(Parcel parcel) {
        return new GeoCodeResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public GeoCodeResult[] newArray(int i) {
        return new GeoCodeResult[i];
    }
}
