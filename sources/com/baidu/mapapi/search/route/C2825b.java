package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.route.BikingRouteLine;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: BikingRouteLine.java */
/* renamed from: com.baidu.mapapi.search.route.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2825b implements Parcelable.Creator<BikingRouteLine.BikingStep> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public BikingRouteLine.BikingStep createFromParcel(Parcel parcel) {
        return new BikingRouteLine.BikingStep(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public BikingRouteLine.BikingStep[] newArray(int i) {
        return new BikingRouteLine.BikingStep[i];
    }
}
