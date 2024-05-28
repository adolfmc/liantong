package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.route.MassTransitRouteLine;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: MassTransitRouteLine.java */
/* renamed from: com.baidu.mapapi.search.route.j */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2833j implements Parcelable.Creator<MassTransitRouteLine.TransitStep> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public MassTransitRouteLine.TransitStep createFromParcel(Parcel parcel) {
        return new MassTransitRouteLine.TransitStep(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public MassTransitRouteLine.TransitStep[] newArray(int i) {
        return new MassTransitRouteLine.TransitStep[i];
    }
}
