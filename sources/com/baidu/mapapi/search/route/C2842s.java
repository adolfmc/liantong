package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.route.WalkingRouteLine;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: WalkingRouteLine.java */
/* renamed from: com.baidu.mapapi.search.route.s */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2842s implements Parcelable.Creator<WalkingRouteLine.WalkingStep> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public WalkingRouteLine.WalkingStep createFromParcel(Parcel parcel) {
        return new WalkingRouteLine.WalkingStep(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public WalkingRouteLine.WalkingStep[] newArray(int i) {
        return new WalkingRouteLine.WalkingStep[i];
    }
}
