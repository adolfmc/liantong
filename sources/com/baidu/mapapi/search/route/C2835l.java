package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: MassTransitRouteResult.java */
/* renamed from: com.baidu.mapapi.search.route.l */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2835l implements Parcelable.Creator<MassTransitRouteResult> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public MassTransitRouteResult createFromParcel(Parcel parcel) {
        return new MassTransitRouteResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public MassTransitRouteResult[] newArray(int i) {
        return new MassTransitRouteResult[i];
    }
}
