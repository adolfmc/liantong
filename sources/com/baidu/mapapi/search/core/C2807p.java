package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: TrainInfo.java */
/* renamed from: com.baidu.mapapi.search.core.p */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2807p implements Parcelable.Creator<TrainInfo> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public TrainInfo createFromParcel(Parcel parcel) {
        return new TrainInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public TrainInfo[] newArray(int i) {
        return new TrainInfo[i];
    }
}
