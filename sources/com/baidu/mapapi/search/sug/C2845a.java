package com.baidu.mapapi.search.sug;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: SuggestionResult.java */
/* renamed from: com.baidu.mapapi.search.sug.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2845a implements Parcelable.Creator<SuggestionResult> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public SuggestionResult[] newArray(int i) {
        return new SuggestionResult[i];
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public SuggestionResult createFromParcel(Parcel parcel) {
        return new SuggestionResult(parcel);
    }
}
