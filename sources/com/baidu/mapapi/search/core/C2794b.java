package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: BusInfo.java */
/* renamed from: com.baidu.mapapi.search.core.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2794b implements Parcelable.Creator<BusInfo> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public BusInfo createFromParcel(Parcel parcel) {
        return new BusInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public BusInfo[] newArray(int i) {
        return new BusInfo[i];
    }
}
