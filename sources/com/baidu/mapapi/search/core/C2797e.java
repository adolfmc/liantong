package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: PlaneInfo.java */
/* renamed from: com.baidu.mapapi.search.core.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2797e implements Parcelable.Creator<PlaneInfo> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PlaneInfo createFromParcel(Parcel parcel) {
        return new PlaneInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PlaneInfo[] newArray(int i) {
        return new PlaneInfo[i];
    }
}
