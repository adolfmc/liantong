package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: TransitRouteLine.java */
/* renamed from: com.baidu.mapapi.search.route.o */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2838o implements Parcelable.Creator<TransitRouteLine> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public TransitRouteLine createFromParcel(Parcel parcel) {
        return new TransitRouteLine(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public TransitRouteLine[] newArray(int i) {
        return new TransitRouteLine[i];
    }
}
