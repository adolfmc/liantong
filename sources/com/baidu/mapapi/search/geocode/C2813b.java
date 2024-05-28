package com.baidu.mapapi.search.geocode;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: ReverseGeoCodeResult.java */
/* renamed from: com.baidu.mapapi.search.geocode.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2813b implements Parcelable.Creator<ReverseGeoCodeResult> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public ReverseGeoCodeResult createFromParcel(Parcel parcel) {
        return new ReverseGeoCodeResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public ReverseGeoCodeResult[] newArray(int i) {
        return new ReverseGeoCodeResult[i];
    }
}
