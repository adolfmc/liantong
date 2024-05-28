package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: PoiDetailInfo.java */
/* renamed from: com.baidu.mapapi.search.core.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2799g implements Parcelable.Creator<PoiDetailInfo> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PoiDetailInfo createFromParcel(Parcel parcel) {
        return new PoiDetailInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PoiDetailInfo[] newArray(int i) {
        return new PoiDetailInfo[i];
    }
}
