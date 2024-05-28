package com.baidu.mapapi.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: ParcelItem.java */
/* renamed from: com.baidu.mapapi.model.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2789c implements Parcelable.Creator<ParcelItem> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public ParcelItem createFromParcel(Parcel parcel) {
        ParcelItem parcelItem = new ParcelItem();
        parcelItem.setBundle(parcel.readBundle());
        return parcelItem;
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public ParcelItem[] newArray(int i) {
        return new ParcelItem[i];
    }
}
