package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.SearchResult;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BikingRouteResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<BikingRouteResult> CREATOR = new C2826c();

    /* renamed from: a */
    private List<BikingRouteLine> f6805a;

    /* renamed from: b */
    private SuggestAddrInfo f6806b;

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BikingRouteResult() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BikingRouteResult(Parcel parcel) {
        this.f6805a = new ArrayList();
        parcel.readList(this.f6805a, BikingRouteLine.class.getClassLoader());
        this.f6806b = (SuggestAddrInfo) parcel.readParcelable(SuggestAddrInfo.class.getClassLoader());
    }

    public List<BikingRouteLine> getRouteLines() {
        return this.f6805a;
    }

    public void setRouteLines(List<BikingRouteLine> list) {
        this.f6805a = list;
    }

    public SuggestAddrInfo getSuggestAddrInfo() {
        return this.f6806b;
    }

    public void setSuggestAddrInfo(SuggestAddrInfo suggestAddrInfo) {
        this.f6806b = suggestAddrInfo;
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.f6805a);
        parcel.writeParcelable(this.f6806b, 1);
    }
}
