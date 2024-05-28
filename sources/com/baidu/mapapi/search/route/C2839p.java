package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.route.TransitRouteLine;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: TransitRouteLine.java */
/* renamed from: com.baidu.mapapi.search.route.p */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2839p implements Parcelable.Creator<TransitRouteLine.TransitStep> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public TransitRouteLine.TransitStep createFromParcel(Parcel parcel) {
        return new TransitRouteLine.TransitStep(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public TransitRouteLine.TransitStep[] newArray(int i) {
        return new TransitRouteLine.TransitStep[i];
    }
}
