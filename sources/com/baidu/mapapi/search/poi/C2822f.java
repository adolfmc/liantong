package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: PoiResult.java */
/* renamed from: com.baidu.mapapi.search.poi.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2822f implements Parcelable.Creator<PoiResult> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PoiResult createFromParcel(Parcel parcel) {
        return new PoiResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PoiResult[] newArray(int i) {
        return new PoiResult[i];
    }
}
