package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: PoiDetailSearchResult.java */
/* renamed from: com.baidu.mapapi.search.poi.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2818b implements Parcelable.Creator<PoiDetailSearchResult> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PoiDetailSearchResult createFromParcel(Parcel parcel) {
        return new PoiDetailSearchResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PoiDetailSearchResult[] newArray(int i) {
        return new PoiDetailSearchResult[i];
    }
}
