package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: BikingRouteLine.java */
/* renamed from: com.baidu.mapapi.search.route.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2824a implements Parcelable.Creator<BikingRouteLine> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public BikingRouteLine createFromParcel(Parcel parcel) {
        return new BikingRouteLine(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public BikingRouteLine[] newArray(int i) {
        return new BikingRouteLine[i];
    }
}
