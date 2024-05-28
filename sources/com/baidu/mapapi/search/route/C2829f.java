package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: DrivingRouteResult.java */
/* renamed from: com.baidu.mapapi.search.route.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2829f implements Parcelable.Creator<DrivingRouteResult> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public DrivingRouteResult createFromParcel(Parcel parcel) {
        return new DrivingRouteResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public DrivingRouteResult[] newArray(int i) {
        return new DrivingRouteResult[i];
    }
}
