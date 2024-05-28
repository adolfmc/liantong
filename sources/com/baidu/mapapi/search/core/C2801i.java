package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.PoiInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: PoiInfo.java */
/* renamed from: com.baidu.mapapi.search.core.i */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2801i implements Parcelable.Creator<PoiInfo.ParentPoiInfo> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PoiInfo.ParentPoiInfo createFromParcel(Parcel parcel) {
        return new PoiInfo.ParentPoiInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PoiInfo.ParentPoiInfo[] newArray(int i) {
        return new PoiInfo.ParentPoiInfo[i];
    }
}
