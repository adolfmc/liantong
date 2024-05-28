package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: WalkingRouteLine.java */
/* renamed from: com.baidu.mapapi.search.route.r */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2841r implements Parcelable.Creator<WalkingRouteLine> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public WalkingRouteLine createFromParcel(Parcel parcel) {
        return new WalkingRouteLine(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public WalkingRouteLine[] newArray(int i) {
        return new WalkingRouteLine[i];
    }
}
