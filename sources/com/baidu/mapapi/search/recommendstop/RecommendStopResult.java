package com.baidu.mapapi.search.recommendstop;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.RecommendStopInfo;
import com.baidu.mapapi.search.core.SearchResult;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RecommendStopResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<RecommendStopResult> CREATOR = new C2823a();

    /* renamed from: a */
    private List<RecommendStopInfo> f6794a;

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RecommendStopResult() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RecommendStopResult(Parcel parcel) {
        super(parcel);
        this.f6794a = parcel.createTypedArrayList(RecommendStopInfo.CREATOR);
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.f6794a);
    }

    public List<RecommendStopInfo> getRecommendStopInfoList() {
        return this.f6794a;
    }

    public void setRecommendStopInfoList(List<RecommendStopInfo> list) {
        this.f6794a = list;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("RecommendStopResult: ");
        List<RecommendStopInfo> list = this.f6794a;
        if (list == null || list.isEmpty()) {
            stringBuffer.append("null");
        } else {
            for (int i = 0; i < this.f6794a.size(); i++) {
                stringBuffer.append(" ");
                stringBuffer.append(i);
                stringBuffer.append(" ");
                RecommendStopInfo recommendStopInfo = this.f6794a.get(i);
                if (recommendStopInfo == null) {
                    stringBuffer.append("null");
                } else {
                    stringBuffer.append(recommendStopInfo.toString());
                }
            }
        }
        return stringBuffer.toString();
    }
}
