package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.SearchResult;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class IndoorRouteResult extends SearchResult {
    public static final Parcelable.Creator<IndoorRouteResult> CREATOR = new C2831h();

    /* renamed from: a */
    private List<IndoorRouteLine> f6845a;

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IndoorRouteResult() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IndoorRouteResult(Parcel parcel) {
        super(parcel);
        this.f6845a = parcel.createTypedArrayList(IndoorRouteLine.CREATOR);
    }

    public List<IndoorRouteLine> getRouteLines() {
        return this.f6845a;
    }

    public void setRouteLines(List<IndoorRouteLine> list) {
        this.f6845a = list;
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.f6845a);
    }
}
