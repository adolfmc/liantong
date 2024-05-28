package com.baidu.mapapi.search.recommendstop;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: RecommendStopResult.java */
/* renamed from: com.baidu.mapapi.search.recommendstop.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2823a implements Parcelable.Creator<RecommendStopResult> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public RecommendStopResult createFromParcel(Parcel parcel) {
        return new RecommendStopResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public RecommendStopResult[] newArray(int i) {
        return new RecommendStopResult[i];
    }
}
