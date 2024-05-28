package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: TransitResultNode.java */
/* renamed from: com.baidu.mapapi.search.core.r */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2809r implements Parcelable.Creator<TransitResultNode> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public TransitResultNode createFromParcel(Parcel parcel) {
        return new TransitResultNode(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public TransitResultNode[] newArray(int i) {
        return new TransitResultNode[i];
    }
}
