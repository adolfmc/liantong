package com.baidu.mapapi.search.sug;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.sug.SuggestionResult;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: SuggestionResult.java */
/* renamed from: com.baidu.mapapi.search.sug.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2846b implements Parcelable.Creator<SuggestionResult.SuggestionInfo> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public SuggestionResult.SuggestionInfo createFromParcel(Parcel parcel) {
        return new SuggestionResult.SuggestionInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a */
    public SuggestionResult.SuggestionInfo[] newArray(int i) {
        return new SuggestionResult.SuggestionInfo[i];
    }
}
