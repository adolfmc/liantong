package com.baidu.mapapi.search.building;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: BuildingResult.java */
/* renamed from: com.baidu.mapapi.search.building.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2791a implements Parcelable.Creator<BuildingResult> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public BuildingResult createFromParcel(Parcel parcel) {
        return new BuildingResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public BuildingResult[] newArray(int i) {
        return new BuildingResult[i];
    }
}
