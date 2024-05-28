package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.PoiDetailInfo;
import com.baidu.mapapi.search.core.SearchResult;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PoiDetailSearchResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<PoiDetailSearchResult> CREATOR = new C2818b();

    /* renamed from: a */
    private List<PoiDetailInfo> f6764a;

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PoiDetailSearchResult() {
    }

    public PoiDetailSearchResult(SearchResult.ERRORNO errorno) {
        super(errorno);
    }

    public List<PoiDetailInfo> getPoiDetailInfoList() {
        return this.f6764a;
    }

    public void setPoiDetailInfoList(List<PoiDetailInfo> list) {
        this.f6764a = list;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PoiDetailSearchResult(Parcel parcel) {
        super(parcel);
        this.f6764a = parcel.createTypedArrayList(PoiDetailInfo.CREATOR);
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.f6764a);
    }

    public String toString() {
        List<PoiDetailInfo> list = this.f6764a;
        if (list == null || list.isEmpty()) {
            return "PoiDetailSearchResult is null";
        }
        StringBuffer stringBuffer = new StringBuffer("PoiDetailSearchResult:");
        for (int i = 0; i < this.f6764a.size(); i++) {
            stringBuffer.append(" ");
            stringBuffer.append(i);
            stringBuffer.append(" ");
            PoiDetailInfo poiDetailInfo = this.f6764a.get(i);
            if (poiDetailInfo != null) {
                stringBuffer.append(poiDetailInfo.toString());
            } else {
                stringBuffer.append("null");
            }
        }
        return stringBuffer.toString();
    }
}
