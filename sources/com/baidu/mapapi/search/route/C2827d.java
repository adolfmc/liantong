package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: DrivingRouteLine.java */
/* renamed from: com.baidu.mapapi.search.route.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2827d implements Parcelable.Creator<DrivingRouteLine> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public DrivingRouteLine createFromParcel(Parcel parcel) {
        return new DrivingRouteLine(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public DrivingRouteLine[] newArray(int i) {
        return new DrivingRouteLine[i];
    }
}
