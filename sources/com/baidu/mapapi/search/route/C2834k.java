package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.route.MassTransitRouteLine;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: MassTransitRouteLine.java */
/* renamed from: com.baidu.mapapi.search.route.k */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2834k implements Parcelable.Creator<MassTransitRouteLine.TransitStep.TrafficCondition> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public MassTransitRouteLine.TransitStep.TrafficCondition createFromParcel(Parcel parcel) {
        return new MassTransitRouteLine.TransitStep.TrafficCondition(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public MassTransitRouteLine.TransitStep.TrafficCondition[] newArray(int i) {
        return new MassTransitRouteLine.TransitStep.TrafficCondition[i];
    }
}
