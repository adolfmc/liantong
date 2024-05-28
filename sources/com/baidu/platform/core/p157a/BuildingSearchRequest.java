package com.baidu.platform.core.p157a;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.building.BuildingSearchOption;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.baidu.platform.base.SearchRequest;
import com.baidu.platform.domain.UrlProvider;
import com.baidu.platform.util.BaseParamBuilder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.a.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BuildingSearchRequest extends SearchRequest {
    public BuildingSearchRequest(BuildingSearchOption buildingSearchOption) {
        m17624a(buildingSearchOption);
    }

    /* renamed from: a */
    private void m17624a(BuildingSearchOption buildingSearchOption) {
        if (buildingSearchOption == null) {
            return;
        }
        LatLng latLng = buildingSearchOption.getLatLng();
        if (latLng != null) {
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                latLng = CoordTrans.gcjToBaidu(latLng);
            }
            BaseParamBuilder baseParamBuilder = this.f7508a;
            baseParamBuilder.m17443a("latitude", latLng.latitude + "");
            BaseParamBuilder baseParamBuilder2 = this.f7508a;
            baseParamBuilder2.m17443a("longitude", latLng.longitude + "");
        }
        this.f7508a.m17443a("coord_type", "bd09ll");
        this.f7508a.m17443a("from", "android_map_sdk");
        this.f7508a.m17443a("output", "json");
        this.f7508a.m17443a("data_set", "building");
    }

    @Override // com.baidu.platform.base.SearchRequest
    /* renamed from: a */
    public String mo17471a(UrlProvider urlProvider) {
        return urlProvider.mo17446v();
    }
}
