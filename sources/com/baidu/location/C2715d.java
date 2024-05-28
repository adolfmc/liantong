package com.baidu.location;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2715d implements Parcelable.Creator<Poi> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public Poi createFromParcel(Parcel parcel) {
        return new Poi(parcel.readString(), parcel.readString(), parcel.readDouble(), parcel.readString(), parcel.readString());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public Poi[] newArray(int i) {
        return new Poi[i];
    }
}
