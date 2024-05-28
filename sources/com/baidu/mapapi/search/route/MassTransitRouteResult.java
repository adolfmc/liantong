package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.TaxiInfo;
import com.baidu.mapapi.search.core.TransitResultNode;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class MassTransitRouteResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<MassTransitRouteResult> CREATOR = new C2835l();

    /* renamed from: a */
    private TransitResultNode f6869a;

    /* renamed from: b */
    private TransitResultNode f6870b;

    /* renamed from: c */
    private TaxiInfo f6871c;

    /* renamed from: d */
    private int f6872d;

    /* renamed from: e */
    private List<MassTransitRouteLine> f6873e;

    /* renamed from: f */
    private SuggestAddrInfo f6874f;

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MassTransitRouteResult() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MassTransitRouteResult(Parcel parcel) {
        this.f6869a = (TransitResultNode) parcel.readParcelable(TransitResultNode.class.getClassLoader());
        this.f6870b = (TransitResultNode) parcel.readParcelable(TransitResultNode.class.getClassLoader());
        this.f6871c = (TaxiInfo) parcel.readParcelable(TaxiInfo.class.getClassLoader());
        this.f6872d = parcel.readInt();
        this.f6873e = new ArrayList();
        parcel.readList(this.f6873e, MassTransitRouteLine.class.getClassLoader());
        this.f6874f = (SuggestAddrInfo) parcel.readParcelable(SuggestAddrInfo.class.getClassLoader());
    }

    public TaxiInfo getTaxiInfo() {
        return this.f6871c;
    }

    public void setTaxiInfo(TaxiInfo taxiInfo) {
        this.f6871c = taxiInfo;
    }

    public TransitResultNode getOrigin() {
        return this.f6869a;
    }

    public void setOrigin(TransitResultNode transitResultNode) {
        this.f6869a = transitResultNode;
    }

    public TransitResultNode getDestination() {
        return this.f6870b;
    }

    public void setDestination(TransitResultNode transitResultNode) {
        this.f6870b = transitResultNode;
    }

    public int getTotal() {
        return this.f6872d;
    }

    public void setTotal(int i) {
        this.f6872d = i;
    }

    public List<MassTransitRouteLine> getRouteLines() {
        return this.f6873e;
    }

    public void setRoutelines(List<MassTransitRouteLine> list) {
        this.f6873e = list;
    }

    public SuggestAddrInfo getSuggestAddrInfo() {
        return this.f6874f;
    }

    public void setSuggestAddrInfo(SuggestAddrInfo suggestAddrInfo) {
        this.f6874f = suggestAddrInfo;
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f6869a, 1);
        parcel.writeParcelable(this.f6870b, 1);
        parcel.writeParcelable(this.f6871c, 1);
        parcel.writeInt(this.f6872d);
        parcel.writeList(this.f6873e);
        parcel.writeParcelable(this.f6874f, 1);
    }
}
