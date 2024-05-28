package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: IndoorRouteLine.java */
/* renamed from: com.baidu.mapapi.search.route.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2830g implements Parcelable.Creator<IndoorRouteLine> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public IndoorRouteLine createFromParcel(Parcel parcel) {
        return new IndoorRouteLine(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public IndoorRouteLine[] newArray(int i) {
        return new IndoorRouteLine[i];
    }
}
