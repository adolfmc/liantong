package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PoiResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<PoiResult> CREATOR = new C2822f();

    /* renamed from: a */
    private int f6782a;

    /* renamed from: b */
    private int f6783b;

    /* renamed from: c */
    private int f6784c;

    /* renamed from: d */
    private int f6785d;

    /* renamed from: e */
    private List<PoiInfo> f6786e;

    /* renamed from: f */
    private boolean f6787f;

    /* renamed from: g */
    private List<PoiAddrInfo> f6788g;

    /* renamed from: h */
    private List<CityInfo> f6789h;

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PoiResult() {
        this.f6782a = 0;
        this.f6783b = 0;
        this.f6784c = 0;
        this.f6785d = 0;
        this.f6787f = false;
    }

    public PoiResult(SearchResult.ERRORNO errorno) {
        super(errorno);
        this.f6782a = 0;
        this.f6783b = 0;
        this.f6784c = 0;
        this.f6785d = 0;
        this.f6787f = false;
    }

    public int getCurrentPageNum() {
        return this.f6782a;
    }

    public void setCurrentPageNum(int i) {
        this.f6782a = i;
    }

    public int getTotalPageNum() {
        return this.f6783b;
    }

    public void setTotalPageNum(int i) {
        this.f6783b = i;
    }

    public int getCurrentPageCapacity() {
        return this.f6784c;
    }

    public void setCurrentPageCapacity(int i) {
        this.f6784c = i;
    }

    public int getTotalPoiNum() {
        return this.f6785d;
    }

    public void setTotalPoiNum(int i) {
        this.f6785d = i;
    }

    public List<PoiInfo> getAllPoi() {
        return this.f6786e;
    }

    public void setPoiInfo(List<PoiInfo> list) {
        this.f6786e = list;
    }

    public List<PoiAddrInfo> getAllAddr() {
        return this.f6788g;
    }

    public void setAddrInfo(List<PoiAddrInfo> list) {
        this.f6788g = list;
    }

    public boolean isHasAddrInfo() {
        return this.f6787f;
    }

    public void setHasAddrInfo(boolean z) {
        this.f6787f = z;
    }

    public List<CityInfo> getSuggestCityList() {
        return this.f6789h;
    }

    public void setSuggestCityList(List<CityInfo> list) {
        this.f6789h = list;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PoiResult(Parcel parcel) {
        super(parcel);
        this.f6782a = 0;
        this.f6783b = 0;
        this.f6784c = 0;
        this.f6785d = 0;
        this.f6787f = false;
        this.f6782a = parcel.readInt();
        this.f6783b = parcel.readInt();
        this.f6784c = parcel.readInt();
        this.f6785d = parcel.readInt();
        this.f6786e = parcel.createTypedArrayList(PoiInfo.CREATOR);
        this.f6787f = parcel.readByte() != 0;
        this.f6789h = parcel.createTypedArrayList(CityInfo.CREATOR);
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f6782a);
        parcel.writeInt(this.f6783b);
        parcel.writeInt(this.f6784c);
        parcel.writeInt(this.f6785d);
        parcel.writeTypedList(this.f6786e);
        parcel.writeByte(this.f6787f ? (byte) 1 : (byte) 0);
        parcel.writeTypedList(this.f6789h);
    }
}
