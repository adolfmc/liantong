package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: TransitRouteResult.java */
/* renamed from: com.baidu.mapapi.search.route.q */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2840q implements Parcelable.Creator<TransitRouteResult> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public TransitRouteResult createFromParcel(Parcel parcel) {
        return new TransitRouteResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public TransitRouteResult[] newArray(int i) {
        return new TransitRouteResult[i];
    }
}
