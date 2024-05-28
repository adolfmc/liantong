package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: PoiFilter.java */
/* renamed from: com.baidu.mapapi.search.poi.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2819c implements Parcelable.Creator<PoiFilter> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PoiFilter createFromParcel(Parcel parcel) {
        return new PoiFilter(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PoiFilter[] newArray(int i) {
        return new PoiFilter[i];
    }
}
