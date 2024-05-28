package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.SearchResult;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PoiIndoorResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<PoiIndoorResult> CREATOR = new C2821e();

    /* renamed from: a */
    private List<PoiIndoorInfo> f6781a;
    public int pageNum;
    public int poiNum;

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PoiIndoorResult() {
    }

    public PoiIndoorResult(SearchResult.ERRORNO errorno) {
        super(errorno);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PoiIndoorResult(Parcel parcel) {
        super(parcel);
        this.poiNum = parcel.readInt();
        this.pageNum = parcel.readInt();
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.poiNum);
        parcel.writeInt(this.pageNum);
    }

    @Deprecated
    public List<PoiIndoorInfo> getmArrayPoiInfo() {
        return this.f6781a;
    }

    @Deprecated
    public void setmArrayPoiInfo(List<PoiIndoorInfo> list) {
        this.f6781a = list;
    }

    public List<PoiIndoorInfo> getArrayPoiInfo() {
        return this.f6781a;
    }

    public void setArrayPoiInfo(List<PoiIndoorInfo> list) {
        this.f6781a = list;
    }

    public int getPoiNum() {
        return this.poiNum;
    }

    public void setPoiNum(int i) {
        this.poiNum = i;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(int i) {
        this.pageNum = i;
    }
}
