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
public final class TransitRouteResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<TransitRouteResult> CREATOR = new C2840q();

    /* renamed from: a */
    private TaxiInfo f6895a;

    /* renamed from: b */
    private List<TransitRouteLine> f6896b;

    /* renamed from: c */
    private SuggestAddrInfo f6897c;

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TransitRouteResult() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TransitRouteResult(Parcel parcel) {
        this.f6895a = (TaxiInfo) parcel.readParcelable(TaxiInfo.class.getClassLoader());
        this.f6896b = new ArrayList();
        parcel.readList(this.f6896b, TransitRouteLine.class.getClassLoader());
        this.f6897c = (SuggestAddrInfo) parcel.readParcelable(SuggestAddrInfo.class.getClassLoader());
    }

    public TaxiInfo getTaxiInfo() {
        return this.f6895a;
    }

    public void setTaxiInfo(TaxiInfo taxiInfo) {
        this.f6895a = taxiInfo;
    }

    public void setRoutelines(List<TransitRouteLine> list) {
        this.f6896b = list;
    }

    public List<TransitRouteLine> getRouteLines() {
        return this.f6896b;
    }

    public SuggestAddrInfo getSuggestAddrInfo() {
        return this.f6897c;
    }

    public void setSuggestAddrInfo(SuggestAddrInfo suggestAddrInfo) {
        this.f6897c = suggestAddrInfo;
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f6895a, 1);
        parcel.writeList(this.f6896b);
        parcel.writeParcelable(this.f6897c, 1);
    }
}
