package com.baidu.mapapi.search.district;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: DistrictResult.java */
/* renamed from: com.baidu.mapapi.search.district.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2811a implements Parcelable.Creator<DistrictResult> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public DistrictResult createFromParcel(Parcel parcel) {
        return new DistrictResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public DistrictResult[] newArray(int i) {
        return new DistrictResult[i];
    }
}
