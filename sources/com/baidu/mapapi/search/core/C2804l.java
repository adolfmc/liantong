package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: RouteNode.java */
/* renamed from: com.baidu.mapapi.search.core.l */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2804l implements Parcelable.Creator<RouteNode> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public RouteNode createFromParcel(Parcel parcel) {
        return new RouteNode(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public RouteNode[] newArray(int i) {
        return new RouteNode[i];
    }
}
