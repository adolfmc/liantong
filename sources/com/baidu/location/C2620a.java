package com.baidu.location;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2620a implements Parcelable.Creator<BDLocation> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public BDLocation createFromParcel(Parcel parcel) {
        return new BDLocation(parcel, null);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public BDLocation[] newArray(int i) {
        return new BDLocation[i];
    }
}
