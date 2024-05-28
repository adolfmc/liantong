package com.baidu.mapapi.map;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.p */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2775p implements Parcelable.Creator<MapStatus> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public MapStatus createFromParcel(Parcel parcel) {
        return new MapStatus(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public MapStatus[] newArray(int i) {
        return new MapStatus[i];
    }
}
