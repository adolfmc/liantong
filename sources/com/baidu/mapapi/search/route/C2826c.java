package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: BikingRouteResult.java */
/* renamed from: com.baidu.mapapi.search.route.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2826c implements Parcelable.Creator<BikingRouteResult> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public BikingRouteResult createFromParcel(Parcel parcel) {
        return new BikingRouteResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public BikingRouteResult[] newArray(int i) {
        return new BikingRouteResult[i];
    }
}
