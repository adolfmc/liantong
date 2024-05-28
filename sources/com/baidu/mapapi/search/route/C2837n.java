package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: SuggestAddrInfo.java */
/* renamed from: com.baidu.mapapi.search.route.n */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2837n implements Parcelable.Creator<SuggestAddrInfo> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public SuggestAddrInfo[] newArray(int i) {
        return new SuggestAddrInfo[i];
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public SuggestAddrInfo createFromParcel(Parcel parcel) {
        return new SuggestAddrInfo(parcel);
    }
}
