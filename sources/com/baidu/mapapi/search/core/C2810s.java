package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: VehicleInfo.java */
/* renamed from: com.baidu.mapapi.search.core.s */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2810s implements Parcelable.Creator<VehicleInfo> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public VehicleInfo createFromParcel(Parcel parcel) {
        return new VehicleInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public VehicleInfo[] newArray(int i) {
        return new VehicleInfo[i];
    }
}
