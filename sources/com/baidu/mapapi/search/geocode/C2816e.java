package com.baidu.mapapi.search.geocode;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: ReverseGeoCodeResult.java */
/* renamed from: com.baidu.mapapi.search.geocode.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2816e implements Parcelable.Creator<ReverseGeoCodeResult.RoadInfo> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public ReverseGeoCodeResult.RoadInfo createFromParcel(Parcel parcel) {
        return new ReverseGeoCodeResult.RoadInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public ReverseGeoCodeResult.RoadInfo[] newArray(int i) {
        return new ReverseGeoCodeResult.RoadInfo[i];
    }
}
