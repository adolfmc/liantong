package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: RouteStep.java */
/* renamed from: com.baidu.mapapi.search.core.m */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2805m implements Parcelable.Creator<RouteStep> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public RouteStep createFromParcel(Parcel parcel) {
        return new RouteStep(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public RouteStep[] newArray(int i) {
        return new RouteStep[i];
    }
}
