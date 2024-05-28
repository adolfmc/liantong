package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: MassTransitRouteLine.java */
/* renamed from: com.baidu.mapapi.search.route.i */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2832i implements Parcelable.Creator<MassTransitRouteLine> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public MassTransitRouteLine createFromParcel(Parcel parcel) {
        return new MassTransitRouteLine(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public MassTransitRouteLine[] newArray(int i) {
        return new MassTransitRouteLine[i];
    }
}
