package com.baidu.platform.core.p159c;

import android.text.TextUtils;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.baidu.platform.base.SearchParser;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.c.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ReverseGeoCoderParser extends SearchParser {
    @Override // com.baidu.platform.base.SearchParser
    /* renamed from: a */
    public SearchResult mo17483a(String str) {
        ReverseGeoCodeResult reverseGeoCodeResult = new ReverseGeoCodeResult();
        if (str == null || str.equals("")) {
            reverseGeoCodeResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            return reverseGeoCodeResult;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("SDK_InnerError")) {
                JSONObject optJSONObject = jSONObject.optJSONObject("SDK_InnerError");
                if (optJSONObject.has("PermissionCheckError")) {
                    reverseGeoCodeResult.error = SearchResult.ERRORNO.PERMISSION_UNFINISHED;
                    return reverseGeoCodeResult;
                } else if (optJSONObject.has("httpStateError")) {
                    String optString = optJSONObject.optString("httpStateError");
                    if (optString.equals("NETWORK_ERROR")) {
                        reverseGeoCodeResult.error = SearchResult.ERRORNO.NETWORK_ERROR;
                    } else if (optString.equals("REQUEST_ERROR")) {
                        reverseGeoCodeResult.error = SearchResult.ERRORNO.REQUEST_ERROR;
                    } else {
                        reverseGeoCodeResult.error = SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                    }
                    return reverseGeoCodeResult;
                }
            }
            if (!m18090a(str, (SearchResult) reverseGeoCodeResult, false)) {
                m17602a(str, reverseGeoCodeResult);
            }
            return reverseGeoCodeResult;
        } catch (Exception unused) {
            reverseGeoCodeResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            return reverseGeoCodeResult;
        }
    }

    /* renamed from: a */
    private boolean m17602a(String str, ReverseGeoCodeResult reverseGeoCodeResult) {
        if (str != null) {
            try {
                if (str.length() > 0) {
                    JSONObject jSONObject = new JSONObject(str);
                    int optInt = jSONObject.optInt("status");
                    if (optInt != 0) {
                        switch (optInt) {
                            case 1:
                                reverseGeoCodeResult.error = SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                                break;
                            case 2:
                                reverseGeoCodeResult.error = SearchResult.ERRORNO.SEARCH_OPTION_ERROR;
                                break;
                            default:
                                reverseGeoCodeResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                                break;
                        }
                        return false;
                    } else if (m17600a(jSONObject, reverseGeoCodeResult)) {
                        return true;
                    } else {
                        reverseGeoCodeResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                        return false;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                reverseGeoCodeResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                return false;
            }
        }
        reverseGeoCodeResult.error = SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
        return false;
    }

    /* renamed from: a */
    private boolean m17600a(JSONObject jSONObject, ReverseGeoCodeResult reverseGeoCodeResult) {
        JSONObject optJSONObject;
        if (jSONObject == null || (optJSONObject = jSONObject.optJSONObject("result")) == null) {
            return false;
        }
        reverseGeoCodeResult.setCityCode(optJSONObject.optInt("cityCode"));
        reverseGeoCodeResult.setAddress(optJSONObject.optString("formatted_address"));
        reverseGeoCodeResult.setBusinessCircle(optJSONObject.optString("business"));
        ReverseGeoCodeResult.AddressComponent m17597b = m17597b(optJSONObject, "addressComponent");
        reverseGeoCodeResult.setAddressDetail(m17597b);
        reverseGeoCodeResult.setLocation(m17594e(optJSONObject, "location"));
        String str = "";
        if (m17597b != null) {
            str = m17597b.city;
            reverseGeoCodeResult.setAdcode(m17597b.adcode);
        }
        reverseGeoCodeResult.setPoiList(m17598a(optJSONObject, "pois", str));
        reverseGeoCodeResult.setSematicDescription(optJSONObject.optString("sematic_description"));
        reverseGeoCodeResult.setPoiRegionsInfoList(m17596c(optJSONObject, "poiRegions"));
        reverseGeoCodeResult.setRoadInfoList(m17599a(optJSONObject, "roads"));
        reverseGeoCodeResult.error = SearchResult.ERRORNO.NO_ERROR;
        return true;
    }

    /* renamed from: a */
    private List<ReverseGeoCodeResult.RoadInfo> m17599a(JSONObject jSONObject, String str) {
        JSONArray optJSONArray;
        if (jSONObject == null || TextUtils.isEmpty(str) || (optJSONArray = jSONObject.optJSONArray(str)) == null || optJSONArray.length() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                ReverseGeoCodeResult.RoadInfo roadInfo = new ReverseGeoCodeResult.RoadInfo();
                roadInfo.name = optJSONObject.optString("name");
                roadInfo.distance = optJSONObject.optString("distance");
                arrayList.add(roadInfo);
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    private ReverseGeoCodeResult.AddressComponent m17597b(JSONObject jSONObject, String str) {
        JSONObject optJSONObject;
        if (jSONObject == null || TextUtils.isEmpty(str) || (optJSONObject = jSONObject.optJSONObject(str)) == null) {
            return null;
        }
        ReverseGeoCodeResult.AddressComponent addressComponent = new ReverseGeoCodeResult.AddressComponent();
        addressComponent.city = optJSONObject.optString("city");
        addressComponent.setTown(optJSONObject.optString("town"));
        addressComponent.district = optJSONObject.optString("district");
        addressComponent.province = optJSONObject.optString("province");
        addressComponent.adcode = optJSONObject.optInt("adcode");
        addressComponent.street = optJSONObject.optString("street");
        addressComponent.streetNumber = optJSONObject.optString("street_number");
        addressComponent.countryName = optJSONObject.optString("country");
        addressComponent.countryCode = optJSONObject.optInt("country_code");
        addressComponent.setDirection(optJSONObject.optString("direction"));
        addressComponent.setDistance(optJSONObject.optString("distance"));
        addressComponent.countryCodeIso = optJSONObject.optString("country_code_iso");
        addressComponent.countryCodeIso2 = optJSONObject.optString("country_code_iso2");
        addressComponent.townCode = optJSONObject.optString("town_code");
        addressComponent.cityLevel = optJSONObject.optInt("cityLevel");
        return addressComponent;
    }

    /* renamed from: c */
    private List<ReverseGeoCodeResult.PoiRegionsInfo> m17596c(JSONObject jSONObject, String str) {
        JSONArray optJSONArray;
        if (jSONObject == null || TextUtils.isEmpty(str) || (optJSONArray = jSONObject.optJSONArray(str)) == null || optJSONArray.length() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                ReverseGeoCodeResult.PoiRegionsInfo poiRegionsInfo = new ReverseGeoCodeResult.PoiRegionsInfo();
                poiRegionsInfo.setDirectionDesc(optJSONObject.optString("direction_desc"));
                poiRegionsInfo.setRegionName(optJSONObject.optString("name"));
                poiRegionsInfo.setRegionTag(optJSONObject.optString("tag"));
                arrayList.add(poiRegionsInfo);
            }
        }
        return arrayList;
    }

    /* renamed from: d */
    private LatLng m17595d(JSONObject jSONObject, String str) {
        JSONObject optJSONObject;
        if (jSONObject == null || str == null || "".equals(str) || (optJSONObject = jSONObject.optJSONObject(str)) == null) {
            return null;
        }
        LatLng latLng = new LatLng(optJSONObject.optDouble("y"), optJSONObject.optDouble("x"));
        return SDKInitializer.getCoordType() == CoordType.GCJ02 ? CoordTrans.baiduToGcj(latLng) : latLng;
    }

    /* renamed from: e */
    private LatLng m17594e(JSONObject jSONObject, String str) {
        JSONObject optJSONObject;
        if (jSONObject == null || str == null || "".equals(str) || (optJSONObject = jSONObject.optJSONObject(str)) == null) {
            return null;
        }
        LatLng latLng = new LatLng(optJSONObject.optDouble("lat"), optJSONObject.optDouble("lng"));
        return SDKInitializer.getCoordType() == CoordType.GCJ02 ? CoordTrans.baiduToGcj(latLng) : latLng;
    }

    /* renamed from: a */
    private List<PoiInfo> m17598a(JSONObject jSONObject, String str, String str2) {
        JSONArray optJSONArray;
        if (jSONObject == null || str == null || "".equals(str) || (optJSONArray = jSONObject.optJSONArray(str)) == null || optJSONArray.length() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                PoiInfo poiInfo = new PoiInfo();
                poiInfo.setAddress(optJSONObject.optString("addr"));
                poiInfo.setPhoneNum(optJSONObject.optString("tel"));
                poiInfo.setUid(optJSONObject.optString("uid"));
                poiInfo.setPostCode(optJSONObject.optString("zip"));
                poiInfo.setName(optJSONObject.optString("name"));
                poiInfo.setLocation(m17595d(optJSONObject, "point"));
                poiInfo.setCity(str2);
                poiInfo.setDirection(optJSONObject.optString("direction"));
                poiInfo.setDistance(optJSONObject.optInt("distance"));
                poiInfo.setTag(optJSONObject.optString("tag"));
                poiInfo.setParentPoi(m17601a(optJSONObject.optJSONObject("parent_poi")));
                arrayList.add(poiInfo);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private PoiInfo.ParentPoiInfo m17601a(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
            return null;
        }
        PoiInfo.ParentPoiInfo parentPoiInfo = new PoiInfo.ParentPoiInfo();
        parentPoiInfo.setParentPoiAddress(jSONObject.optString("addr"));
        parentPoiInfo.setParentPoiDirection(jSONObject.optString("direction"));
        parentPoiInfo.setParentPoiDistance(jSONObject.optInt("distance"));
        parentPoiInfo.setParentPoiName(jSONObject.optString("name"));
        parentPoiInfo.setParentPoiTag(jSONObject.optString("tag"));
        parentPoiInfo.setParentPoiUid(jSONObject.optString("uid"));
        parentPoiInfo.setParentPoiLocation(m17595d(jSONObject, "point"));
        return parentPoiInfo;
    }

    @Override // com.baidu.platform.base.SearchParser
    /* renamed from: a */
    public void mo17486a(SearchResult searchResult, Object obj) {
        if (obj == null || !(obj instanceof OnGetGeoCoderResultListener)) {
            return;
        }
        ((OnGetGeoCoderResultListener) obj).onGetReverseGeoCodeResult((ReverseGeoCodeResult) searchResult);
    }
}
