package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: PoiIndoorResult.java */
/* renamed from: com.baidu.mapapi.search.poi.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2821e implements Parcelable.Creator<PoiIndoorResult> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PoiIndoorResult createFromParcel(Parcel parcel) {
        return new PoiIndoorResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PoiIndoorResult[] newArray(int i) {
        return new PoiIndoorResult[i];
    }
}
