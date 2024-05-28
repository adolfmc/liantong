package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.TaxiInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WalkingRouteResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<WalkingRouteResult> CREATOR = new C2843t();

    /* renamed from: a */
    private List<WalkingRouteLine> f6905a;

    /* renamed from: b */
    private TaxiInfo f6906b;

    /* renamed from: c */
    private SuggestAddrInfo f6907c;

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WalkingRouteResult() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public WalkingRouteResult(Parcel parcel) {
        this.f6905a = new ArrayList();
        parcel.readList(this.f6905a, WalkingRouteLine.class.getClassLoader());
        this.f6906b = (TaxiInfo) parcel.readParcelable(TaxiInfo.class.getClassLoader());
        this.f6907c = (SuggestAddrInfo) parcel.readParcelable(SuggestAddrInfo.class.getClassLoader());
    }

    public List<WalkingRouteLine> getRouteLines() {
        return this.f6905a;
    }

    public void setRouteLines(List<WalkingRouteLine> list) {
        this.f6905a = list;
    }

    public TaxiInfo getTaxiInfo() {
        return this.f6906b;
    }

    public void setTaxiInfo(TaxiInfo taxiInfo) {
        this.f6906b = taxiInfo;
    }

    public SuggestAddrInfo getSuggestAddrInfo() {
        return this.f6907c;
    }

    public void setSuggestAddrInfo(SuggestAddrInfo suggestAddrInfo) {
        this.f6907c = suggestAddrInfo;
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.f6905a);
        parcel.writeParcelable(this.f6906b, 1);
        parcel.writeParcelable(this.f6907c, 1);
    }
}
