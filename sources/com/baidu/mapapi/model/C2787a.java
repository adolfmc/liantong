package com.baidu.mapapi.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: LatLng.java */
/* renamed from: com.baidu.mapapi.model.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2787a implements Parcelable.Creator<LatLng> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public LatLng createFromParcel(Parcel parcel) {
        return new LatLng(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public LatLng[] newArray(int i) {
        return new LatLng[i];
    }
}
