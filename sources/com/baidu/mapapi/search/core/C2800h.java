package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: PoiInfo.java */
/* renamed from: com.baidu.mapapi.search.core.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2800h implements Parcelable.Creator<PoiInfo> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PoiInfo[] newArray(int i) {
        return new PoiInfo[i];
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PoiInfo createFromParcel(Parcel parcel) {
        return new PoiInfo(parcel);
    }
}
