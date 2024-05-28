package com.baidu.mapapi.search.share;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.SearchResult;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ShareUrlResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<ShareUrlResult> CREATOR = new C2844a();

    /* renamed from: a */
    private String f6910a;

    /* renamed from: b */
    private int f6911b;

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ShareUrlResult() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ShareUrlResult(Parcel parcel) {
        this.f6910a = parcel.readString();
        this.f6911b = parcel.readInt();
    }

    public String getUrl() {
        return this.f6910a;
    }

    public void setUrl(String str) {
        this.f6910a = str;
    }

    public void setType(int i) {
        this.f6911b = i;
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f6910a);
        parcel.writeInt(this.f6911b);
    }
}
