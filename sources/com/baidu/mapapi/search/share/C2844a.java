package com.baidu.mapapi.search.share;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: ShareUrlResult.java */
/* renamed from: com.baidu.mapapi.search.share.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2844a implements Parcelable.Creator<ShareUrlResult> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public ShareUrlResult createFromParcel(Parcel parcel) {
        return new ShareUrlResult(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public ShareUrlResult[] newArray(int i) {
        return new ShareUrlResult[i];
    }
}
