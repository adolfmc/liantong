package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: PoiChildrenInfo.java */
/* renamed from: com.baidu.mapapi.search.core.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2798f implements Parcelable.Creator<PoiChildrenInfo> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PoiChildrenInfo createFromParcel(Parcel parcel) {
        return new PoiChildrenInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PoiChildrenInfo[] newArray(int i) {
        return new PoiChildrenInfo[i];
    }
}
