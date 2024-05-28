package com.baidu.mapapi.map;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.l */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2770l implements Parcelable.Creator<BaiduMapOptions> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public BaiduMapOptions createFromParcel(Parcel parcel) {
        return new BaiduMapOptions(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public BaiduMapOptions[] newArray(int i) {
        return new BaiduMapOptions[i];
    }
}
