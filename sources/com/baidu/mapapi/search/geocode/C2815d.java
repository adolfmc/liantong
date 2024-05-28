package com.baidu.mapapi.search.geocode;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: ReverseGeoCodeResult.java */
/* renamed from: com.baidu.mapapi.search.geocode.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2815d implements Parcelable.Creator<ReverseGeoCodeResult.PoiRegionsInfo> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public ReverseGeoCodeResult.PoiRegionsInfo createFromParcel(Parcel parcel) {
        return new ReverseGeoCodeResult.PoiRegionsInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public ReverseGeoCodeResult.PoiRegionsInfo[] newArray(int i) {
        return new ReverseGeoCodeResult.PoiRegionsInfo[i];
    }
}
