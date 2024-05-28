package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: CoachInfo.java */
/* renamed from: com.baidu.mapapi.search.core.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2796d implements Parcelable.Creator<CoachInfo> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public CoachInfo createFromParcel(Parcel parcel) {
        return new CoachInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public CoachInfo[] newArray(int i) {
        return new CoachInfo[i];
    }
}
