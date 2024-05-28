package com.baidu.platform.core.p161e;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.recommendstop.RecommendStopSearchOption;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.baidu.platform.base.SearchRequest;
import com.baidu.platform.domain.UrlProvider;
import com.baidu.platform.util.BaseParamBuilder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.e.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RecommendStopSearchRequest extends SearchRequest {
    public RecommendStopSearchRequest(RecommendStopSearchOption recommendStopSearchOption) {
        m17563a(recommendStopSearchOption);
    }

    /* renamed from: a */
    private void m17563a(RecommendStopSearchOption recommendStopSearchOption) {
        if (recommendStopSearchOption != null && recommendStopSearchOption.mLocation != null) {
            LatLng latLng = new LatLng(recommendStopSearchOption.getLocation().latitude, recommendStopSearchOption.getLocation().longitude);
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                latLng = CoordTrans.gcjToBaidu(latLng);
            }
            BaseParamBuilder baseParamBuilder = this.f7508a;
            baseParamBuilder.m17443a("location", latLng.longitude + "," + latLng.latitude);
        }
        this.f7508a.m17443a("coordtype", "bd09ll");
        this.f7508a.m17443a("from", "android_map_sdk");
        this.f7508a.m17443a("output", "json");
    }

    @Override // com.baidu.platform.base.SearchRequest
    /* renamed from: a */
    public String mo17471a(UrlProvider urlProvider) {
        return urlProvider.mo17447u();
    }
}
