package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SuggestAddrInfo implements Parcelable {
    public static final Parcelable.Creator<SuggestAddrInfo> CREATOR = new C2837n();

    /* renamed from: a */
    private List<PoiInfo> f6880a;

    /* renamed from: b */
    private List<PoiInfo> f6881b;

    /* renamed from: c */
    private List<List<PoiInfo>> f6882c;

    /* renamed from: d */
    private List<CityInfo> f6883d;

    /* renamed from: e */
    private List<CityInfo> f6884e;

    /* renamed from: f */
    private List<List<CityInfo>> f6885f;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SuggestAddrInfo() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SuggestAddrInfo(Parcel parcel) {
        this.f6880a = parcel.readArrayList(PoiInfo.class.getClassLoader());
        this.f6881b = parcel.readArrayList(PoiInfo.class.getClassLoader());
        this.f6882c = parcel.readArrayList(PoiInfo.class.getClassLoader());
        this.f6883d = parcel.readArrayList(CityInfo.class.getClassLoader());
        this.f6884e = parcel.readArrayList(CityInfo.class.getClassLoader());
        this.f6885f = parcel.readArrayList(CityInfo.class.getClassLoader());
    }

    public List<PoiInfo> getSuggestStartNode() {
        return this.f6880a;
    }

    public void setSuggestStartNode(List<PoiInfo> list) {
        this.f6880a = list;
    }

    public List<PoiInfo> getSuggestEndNode() {
        return this.f6881b;
    }

    public void setSuggestEndNode(List<PoiInfo> list) {
        this.f6881b = list;
    }

    public List<List<PoiInfo>> getSuggestWpNode() {
        return this.f6882c;
    }

    public void setSuggestWpNode(List<List<PoiInfo>> list) {
        this.f6882c = list;
    }

    public List<CityInfo> getSuggestStartCity() {
        return this.f6883d;
    }

    public void setSuggestStartCity(List<CityInfo> list) {
        this.f6883d = list;
    }

    public List<CityInfo> getSuggestEndCity() {
        return this.f6884e;
    }

    public void setSuggestEndCity(List<CityInfo> list) {
        this.f6884e = list;
    }

    public List<List<CityInfo>> getSuggestWpCity() {
        return this.f6885f;
    }

    public void setSuggestWpCity(List<List<CityInfo>> list) {
        this.f6885f = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.f6880a);
        parcel.writeList(this.f6881b);
        parcel.writeList(this.f6882c);
        parcel.writeList(this.f6883d);
        parcel.writeList(this.f6884e);
        parcel.writeList(this.f6885f);
    }
}
