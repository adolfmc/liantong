package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.route.DrivingRouteLine;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: DrivingRouteLine.java */
/* renamed from: com.baidu.mapapi.search.route.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2828e implements Parcelable.Creator<DrivingRouteLine.DrivingStep> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public DrivingRouteLine.DrivingStep createFromParcel(Parcel parcel) {
        return new DrivingRouteLine.DrivingStep(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public DrivingRouteLine.DrivingStep[] newArray(int i) {
        return new DrivingRouteLine.DrivingStep[i];
    }
}
