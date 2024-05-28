package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: RecommendStopInfo.java */
/* renamed from: com.baidu.mapapi.search.core.k */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2803k implements Parcelable.Creator<RecommendStopInfo> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public RecommendStopInfo createFromParcel(Parcel parcel) {
        return new RecommendStopInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public RecommendStopInfo[] newArray(int i) {
        return new RecommendStopInfo[i];
    }
}
