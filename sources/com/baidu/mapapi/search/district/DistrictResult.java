package com.baidu.mapapi.search.district;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DistrictResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<DistrictResult> CREATOR = new C2811a();
    public LatLng centerPt;
    public int cityCode;
    public String cityName;
    public List<List<LatLng>> polylines;

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setCityName(String str) {
        this.cityName = str;
    }

    public void setCenterPt(LatLng latLng) {
        this.centerPt = latLng;
    }

    public void setPolylines(List<List<LatLng>> list) {
        this.polylines = list;
    }

    public void setCityCode(int i) {
        this.cityCode = i;
    }

    public LatLng getCenterPt() {
        return this.centerPt;
    }

    public List<List<LatLng>> getPolylines() {
        return this.polylines;
    }

    public int getCityCode() {
        return this.cityCode;
    }

    public String getCityName() {
        return this.cityName;
    }

    public DistrictResult() {
        this.centerPt = null;
        this.polylines = null;
        this.cityName = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DistrictResult(Parcel parcel) {
        super(parcel);
        this.centerPt = null;
        this.polylines = null;
        this.cityName = null;
        this.centerPt = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        int readInt = parcel.readInt();
        if (readInt > 0) {
            this.polylines = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                this.polylines.add(parcel.createTypedArrayList(LatLng.CREATOR));
            }
        }
        this.cityCode = parcel.readInt();
        this.cityName = parcel.readString();
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.centerPt, i);
        List<List<LatLng>> list = this.polylines;
        parcel.writeInt(list == null ? 0 : list.size());
        for (List<LatLng> list2 : this.polylines) {
            parcel.writeTypedList(list2);
        }
        parcel.writeInt(this.cityCode);
        parcel.writeString(this.cityName);
    }
}
