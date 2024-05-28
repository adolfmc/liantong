package com.baidu.mapapi.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: LatLngBounds.java */
/* renamed from: com.baidu.mapapi.model.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2788b implements Parcelable.Creator<LatLngBounds> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public LatLngBounds createFromParcel(Parcel parcel) {
        return new LatLngBounds(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public LatLngBounds[] newArray(int i) {
        return new LatLngBounds[i];
    }
}
