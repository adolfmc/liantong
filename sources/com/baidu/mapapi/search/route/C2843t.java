package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: WalkingRouteResult.java */
/* renamed from: com.baidu.mapapi.search.route.t */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2843t implements Parcelable.Creator<WalkingRouteResult> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public WalkingRouteResult createFromParcel(Parcel parcel) {
        return new WalkingRouteResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public WalkingRouteResult[] newArray(int i) {
        return new WalkingRouteResult[i];
    }
}
