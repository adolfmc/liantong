package com.baidu.platform.core.p162f;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.SuggestAddrInfo;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.platform.base.SearchParser;
import com.baidu.platform.base.SearchType;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.f.k */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RoutePlaneParser extends SearchParser {

    /* renamed from: b */
    SuggestAddrInfo f8148b = null;

    /* renamed from: c */
    protected boolean f8149c;

    @Override // com.baidu.platform.base.SearchParser
    /* renamed from: a */
    public SearchResult mo17483a(String str) {
        SearchType a = m18092a();
        if (m17519b(str)) {
            this.f8149c = true;
        } else {
            this.f8149c = false;
        }
        switch (a) {
            case TRANSIT_ROUTE:
                TransitRouteResult transitRouteResult = new TransitRouteResult();
                if (!this.f8149c) {
                    ((TransitRouteParser) this).m17517a(str, transitRouteResult);
                    return transitRouteResult;
                }
                transitRouteResult.setSuggestAddrInfo(this.f8148b);
                transitRouteResult.error = SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR;
                return transitRouteResult;
            case DRIVE_ROUTE:
                DrivingRouteResult drivingRouteResult = new DrivingRouteResult();
                if (!this.f8149c) {
                    ((DriveRouteParser) this).m17554a(str, drivingRouteResult);
                    return drivingRouteResult;
                }
                drivingRouteResult.setSuggestAddrInfo(this.f8148b);
                drivingRouteResult.error = SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR;
                return drivingRouteResult;
            case WALK_ROUTE:
                WalkingRouteResult walkingRouteResult = new WalkingRouteResult();
                if (!this.f8149c) {
                    ((WalkRouteParser) this).m17510a(str, walkingRouteResult);
                    return walkingRouteResult;
                }
                walkingRouteResult.setSuggestAddrInfo(this.f8148b);
                walkingRouteResult.error = SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR;
                return walkingRouteResult;
            default:
                return null;
        }
    }

    /* renamed from: b */
    private boolean m17519b(String str) {
        if (str == null || str.length() <= 0) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject optJSONObject = jSONObject.optJSONObject("result");
            if (optJSONObject != null && optJSONObject.optInt("type") == 23 && optJSONObject.optInt("error") == 0) {
                this.f8148b = m17521a(jSONObject);
                return this.f8148b != null;
            }
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    private SuggestAddrInfo m17521a(JSONObject jSONObject) {
        JSONObject optJSONObject;
        JSONObject jSONObject2;
        if (jSONObject == null || (optJSONObject = jSONObject.optJSONObject("traffic_pois")) == null) {
            return null;
        }
        JSONObject optJSONObject2 = optJSONObject.optJSONObject("option");
        JSONObject optJSONObject3 = optJSONObject.optJSONObject("content");
        if (optJSONObject2 == null || optJSONObject3 == null) {
            return null;
        }
        JSONObject optJSONObject4 = optJSONObject2.optJSONObject("start_city");
        String optString = optJSONObject4 != null ? optJSONObject4.optString("cname") : null;
        JSONArray optJSONArray = optJSONObject2.optJSONArray("end_city");
        String optString2 = (optJSONArray == null || (jSONObject2 = (JSONObject) optJSONArray.opt(0)) == null) ? null : jSONObject2.optString("cname");
        JSONArray optJSONArray2 = optJSONObject2.optJSONArray("city_list");
        JSONArray optJSONArray3 = optJSONObject2.optJSONArray("prio_flag");
        if (optJSONArray2 == null || optJSONArray3 == null) {
            return null;
        }
        int length = optJSONArray2.length();
        boolean[] zArr = new boolean[length];
        boolean[] zArr2 = new boolean[length];
        for (int i = 0; i < length; i++) {
            int parseInt = Integer.parseInt(optJSONArray2.optString(i));
            int parseInt2 = Integer.parseInt(optJSONArray3.optString(i));
            boolean z = true;
            zArr[i] = parseInt == 1;
            if (parseInt2 != 1) {
                z = false;
            }
            zArr2[i] = z;
        }
        SuggestAddrInfo suggestAddrInfo = new SuggestAddrInfo();
        for (int i2 = 0; i2 < length; i2++) {
            if (!zArr2[i2]) {
                if (zArr[i2]) {
                    if (i2 == 0) {
                        suggestAddrInfo.setSuggestStartCity(m17523a(optJSONObject3.optJSONArray("start")));
                    } else if (i2 == length - 1 && i2 > 0) {
                        suggestAddrInfo.setSuggestEndCity(m17523a(optJSONObject3.optJSONArray("end")));
                    } else {
                        suggestAddrInfo.setSuggestWpCity(m17520a(optJSONObject3, "multi_waypoints"));
                    }
                } else if (i2 == 0) {
                    suggestAddrInfo.setSuggestStartNode(m17522a(optJSONObject3.optJSONArray("start"), optString));
                } else if (i2 == length - 1 && i2 > 0) {
                    suggestAddrInfo.setSuggestEndNode(m17522a(optJSONObject3.optJSONArray("end"), optString2));
                } else {
                    suggestAddrInfo.setSuggestWpNode(m17518b(optJSONObject3, "multi_waypoints"));
                }
            }
        }
        return suggestAddrInfo;
    }

    /* renamed from: a */
    private List<CityInfo> m17523a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = (JSONObject) jSONArray.opt(i);
            if (jSONObject != null) {
                CityInfo cityInfo = new CityInfo();
                cityInfo.num = jSONObject.optInt("num");
                cityInfo.city = jSONObject.optString("name");
                arrayList.add(cityInfo);
            }
        }
        arrayList.trimToSize();
        return arrayList;
    }

    /* renamed from: a */
    private List<PoiInfo> m17522a(JSONArray jSONArray, String str) {
        if (jSONArray != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = (JSONObject) jSONArray.opt(i);
                if (jSONObject != null) {
                    PoiInfo poiInfo = new PoiInfo();
                    poiInfo.address = jSONObject.optString("addr");
                    poiInfo.uid = jSONObject.optString("uid");
                    poiInfo.name = jSONObject.optString("name");
                    poiInfo.location = CoordUtil.decodeLocation(jSONObject.optString("geo"));
                    poiInfo.city = str;
                    arrayList.add(poiInfo);
                }
            }
            if (arrayList.size() > 0) {
                return arrayList;
            }
            return null;
        }
        return null;
    }

    /* renamed from: a */
    private List<List<CityInfo>> m17520a(JSONObject jSONObject, String str) {
        JSONArray optJSONArray;
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null || (optJSONArray = jSONObject.optJSONArray(str)) == null) {
            return null;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            List<CityInfo> m17523a = m17523a((JSONArray) optJSONArray.opt(i));
            if (m17523a != null) {
                arrayList.add(m17523a);
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    private List<List<PoiInfo>> m17518b(JSONObject jSONObject, String str) {
        JSONArray optJSONArray;
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null || (optJSONArray = jSONObject.optJSONArray(str)) == null) {
            return null;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            List<PoiInfo> m17522a = m17522a(((JSONObject) optJSONArray.opt(i)).optJSONArray("way_ponits"), "");
            if (m17522a != null) {
                arrayList.add(m17522a);
            }
        }
        return arrayList;
    }

    @Override // com.baidu.platform.base.SearchParser
    /* renamed from: a */
    public void mo17486a(SearchResult searchResult, Object obj) {
        if (obj == null || !(obj instanceof OnGetRoutePlanResultListener)) {
            return;
        }
        OnGetRoutePlanResultListener onGetRoutePlanResultListener = (OnGetRoutePlanResultListener) obj;
        switch (m18092a()) {
            case TRANSIT_ROUTE:
                onGetRoutePlanResultListener.onGetTransitRouteResult((TransitRouteResult) searchResult);
                return;
            case DRIVE_ROUTE:
                onGetRoutePlanResultListener.onGetDrivingRouteResult((DrivingRouteResult) searchResult);
                return;
            case WALK_ROUTE:
                onGetRoutePlanResultListener.onGetWalkingRouteResult((WalkingRouteResult) searchResult);
                return;
            default:
                return;
        }
    }
}
