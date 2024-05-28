package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: TransitBaseInfo.java */
/* renamed from: com.baidu.mapapi.search.core.q */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2808q implements Parcelable.Creator<TransitBaseInfo> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public TransitBaseInfo createFromParcel(Parcel parcel) {
        return new TransitBaseInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public TransitBaseInfo[] newArray(int i) {
        return new TransitBaseInfo[i];
    }
}
