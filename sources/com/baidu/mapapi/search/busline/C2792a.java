package com.baidu.mapapi.search.busline;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: BusLineResult.java */
/* renamed from: com.baidu.mapapi.search.busline.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2792a implements Parcelable.Creator<BusLineResult> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public BusLineResult[] newArray(int i) {
        return new BusLineResult[i];
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public BusLineResult createFromParcel(Parcel parcel) {
        return new BusLineResult(parcel);
    }
}
