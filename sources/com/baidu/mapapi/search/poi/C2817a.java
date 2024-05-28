package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: PoiDetailResult.java */
/* renamed from: com.baidu.mapapi.search.poi.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2817a implements Parcelable.Creator<PoiDetailResult> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PoiDetailResult createFromParcel(Parcel parcel) {
        return new PoiDetailResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PoiDetailResult[] newArray(int i) {
        return new PoiDetailResult[i];
    }
}
