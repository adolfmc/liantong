package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: IndoorRouteResult.java */
/* renamed from: com.baidu.mapapi.search.route.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2831h implements Parcelable.Creator<IndoorRouteResult> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public IndoorRouteResult createFromParcel(Parcel parcel) {
        return new IndoorRouteResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public IndoorRouteResult[] newArray(int i) {
        return new IndoorRouteResult[i];
    }
}
