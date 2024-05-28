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
public final class DrivingRouteResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<DrivingRouteResult> CREATOR = new C2829f();

    /* renamed from: a */
    private List<DrivingRouteLine> f6828a;

    /* renamed from: b */
    private List<TaxiInfo> f6829b;

    /* renamed from: c */
    private TaxiInfo f6830c;

    /* renamed from: d */
    private SuggestAddrInfo f6831d;

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DrivingRouteResult() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DrivingRouteResult(Parcel parcel) {
        this.f6828a = new ArrayList();
        parcel.readTypedList(this.f6828a, DrivingRouteLine.CREATOR);
        this.f6829b = new ArrayList();
        parcel.readTypedList(this.f6829b, TaxiInfo.CREATOR);
        this.f6831d = (SuggestAddrInfo) parcel.readParcelable(SuggestAddrInfo.class.getClassLoader());
    }

    @Deprecated
    public TaxiInfo getTaxiInfo() {
        return this.f6830c;
    }

    public List<TaxiInfo> getTaxiInfos() {
        return this.f6829b;
    }

    public void setTaxiInfos(List<TaxiInfo> list) {
        this.f6829b = list;
    }

    public void setRouteLines(List<DrivingRouteLine> list) {
        this.f6828a = list;
    }

    public List<DrivingRouteLine> getRouteLines() {
        return this.f6828a;
    }

    public SuggestAddrInfo getSuggestAddrInfo() {
        return this.f6831d;
    }

    public void setSuggestAddrInfo(SuggestAddrInfo suggestAddrInfo) {
        this.f6831d = suggestAddrInfo;
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f6828a);
        parcel.writeTypedList(this.f6829b);
        parcel.writeParcelable(this.f6831d, 1);
    }
}
