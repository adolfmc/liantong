package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: PlanNode.java */
/* renamed from: com.baidu.mapapi.search.route.m */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2836m implements Parcelable.Creator<PlanNode> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PlanNode createFromParcel(Parcel parcel) {
        return new PlanNode(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public PlanNode[] newArray(int i) {
        return new PlanNode[i];
    }
}
