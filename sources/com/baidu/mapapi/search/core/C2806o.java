package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: TaxiInfo.java */
/* renamed from: com.baidu.mapapi.search.core.o */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2806o implements Parcelable.Creator<TaxiInfo> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public TaxiInfo[] newArray(int i) {
        return new TaxiInfo[i];
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public TaxiInfo createFromParcel(Parcel parcel) {
        return new TaxiInfo(parcel);
    }
}
