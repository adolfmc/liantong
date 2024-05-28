package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: BuildingInfo.java */
/* renamed from: com.baidu.mapapi.search.core.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2793a implements Parcelable.Creator<BuildingInfo> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public BuildingInfo createFromParcel(Parcel parcel) {
        return new BuildingInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public BuildingInfo[] newArray(int i) {
        return new BuildingInfo[i];
    }
}
