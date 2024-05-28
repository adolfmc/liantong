package com.baidu.mapapi.search.geocode;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: ReverseGeoCodeResult.java */
/* renamed from: com.baidu.mapapi.search.geocode.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2814c implements Parcelable.Creator<ReverseGeoCodeResult.AddressComponent> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public ReverseGeoCodeResult.AddressComponent createFromParcel(Parcel parcel) {
        return new ReverseGeoCodeResult.AddressComponent(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public ReverseGeoCodeResult.AddressComponent[] newArray(int i) {
        return new ReverseGeoCodeResult.AddressComponent[i];
    }
}
