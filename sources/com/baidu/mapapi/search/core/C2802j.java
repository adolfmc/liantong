package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: PriceInfo.java */
/* renamed from: com.baidu.mapapi.search.core.j */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2802j implements Parcelable.Creator<PriceInfo> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PriceInfo createFromParcel(Parcel parcel) {
        return new PriceInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PriceInfo[] newArray(int i) {
        return new PriceInfo[i];
    }
}
