package com.baidu.mapapi.search.building;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.BuildingInfo;
import com.baidu.mapapi.search.core.SearchResult;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BuildingResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<BuildingResult> CREATOR = new C2791a();

    /* renamed from: a */
    private List<BuildingInfo> f6636a;

    /* renamed from: b */
    private int f6637b;

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BuildingResult() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BuildingResult(Parcel parcel) {
        this.f6636a = parcel.createTypedArrayList(BuildingInfo.CREATOR);
    }

    public int getRelation() {
        return this.f6637b;
    }

    public void setRelation(int i) {
        this.f6637b = i;
    }

    public void setBuildingList(List<BuildingInfo> list) {
        this.f6636a = list;
    }

    public List<BuildingInfo> getBuildingList() {
        return this.f6636a;
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f6636a);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("BuidingResult: ");
        List<BuildingInfo> list = this.f6636a;
        if (list == null || list.isEmpty()) {
            stringBuffer.append("null");
        } else {
            for (int i = 0; i < this.f6636a.size(); i++) {
                stringBuffer.append(" ");
                stringBuffer.append(i);
                stringBuffer.append(" ");
                BuildingInfo buildingInfo = this.f6636a.get(i);
                if (buildingInfo == null) {
                    stringBuffer.append("null");
                } else {
                    stringBuffer.append(buildingInfo.toString());
                }
            }
        }
        return stringBuffer.toString();
    }
}
