package com.baidu.platform.core.p160d;

import android.util.Log;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiChildrenInfo;
import com.baidu.mapapi.search.core.PoiDetailInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
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
/* renamed from: com.baidu.platform.core.d.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PoiSearchParser extends SearchParser {

    /* renamed from: b */
    private static final String f8141b = "g";

    /* renamed from: c */
    private int f8142c;

    /* renamed from: d */
    private int f8143d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PoiSearchParser(int i, int i2) {
        this.f8142c = i;
        this.f8143d = i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0066, code lost:
        if (r6.equals("NETWORK_ERROR") != false) goto L25;
     */
    @Override // com.baidu.platform.base.SearchParser
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.baidu.mapapi.search.core.SearchResult mo17483a(java.lang.String r6) {
        /*
            r5 = this;
            com.baidu.mapapi.search.poi.PoiResult r0 = new com.baidu.mapapi.search.poi.PoiResult
            r0.<init>()
            if (r6 == 0) goto L9e
            java.lang.String r1 = ""
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L9e
            boolean r1 = r6.isEmpty()
            if (r1 == 0) goto L17
            goto L9e
        L17:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: org.json.JSONException -> L91
            r1.<init>(r6)     // Catch: org.json.JSONException -> L91
            java.lang.String r2 = "SDK_InnerError"
            boolean r2 = r1.has(r2)
            r3 = 0
            if (r2 == 0) goto L7c
            java.lang.String r2 = "SDK_InnerError"
            org.json.JSONObject r1 = r1.optJSONObject(r2)
            java.lang.String r2 = "PermissionCheckError"
            boolean r2 = r1.has(r2)
            if (r2 == 0) goto L38
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.PERMISSION_UNFINISHED
            r0.error = r6
            return r0
        L38:
            java.lang.String r2 = "httpStateError"
            boolean r2 = r1.has(r2)
            if (r2 == 0) goto L7c
            java.lang.String r6 = "httpStateError"
            java.lang.String r6 = r1.optString(r6)
            r1 = -1
            int r2 = r6.hashCode()
            r4 = -879828873(0xffffffffcb8ee077, float:-1.872715E7)
            if (r2 == r4) goto L60
            r3 = 1470557208(0x57a6ec18, float:3.6706589E14)
            if (r2 == r3) goto L56
            goto L69
        L56:
            java.lang.String r2 = "REQUEST_ERROR"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L69
            r3 = 1
            goto L6a
        L60:
            java.lang.String r2 = "NETWORK_ERROR"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L69
            goto L6a
        L69:
            r3 = r1
        L6a:
            switch(r3) {
                case 0: goto L77;
                case 1: goto L72;
                default: goto L6d;
            }
        L6d:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR
            r0.error = r6
            goto L7b
        L72:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.REQUEST_ERROR
            r0.error = r6
            goto L7b
        L77:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.NETWORK_ERROR
            r0.error = r6
        L7b:
            return r0
        L7c:
            boolean r1 = r5.m18090a(r6, r0, r3)
            if (r1 == 0) goto L83
            return r0
        L83:
            boolean r6 = r5.m17577a(r6, r0)
            if (r6 == 0) goto L8c
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.NO_ERROR
            goto L8e
        L8c:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.RESULT_NOT_FOUND
        L8e:
            r0.error = r6
            return r0
        L91:
            r6 = move-exception
            java.lang.String r1 = com.baidu.platform.core.p160d.PoiSearchParser.f8141b
            java.lang.String r2 = "Parse poi search error"
            android.util.Log.e(r1, r2, r6)
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.RESULT_NOT_FOUND
            r0.error = r6
            return r0
        L9e:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.RESULT_NOT_FOUND
            r0.error = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.core.p160d.PoiSearchParser.mo17483a(java.lang.String):com.baidu.mapapi.search.core.SearchResult");
    }

    /* renamed from: a */
    private boolean m17577a(String str, PoiResult poiResult) {
        if (str == null || str.equals("") || str.isEmpty()) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("status");
            if (optInt == 0) {
                return m17575a(jSONObject, poiResult);
            }
            switch (optInt) {
                case 1:
                    poiResult.error = SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                    break;
                case 2:
                    poiResult.error = SearchResult.ERRORNO.SEARCH_OPTION_ERROR;
                    break;
                default:
                    poiResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                    break;
            }
            return false;
        } catch (JSONException e) {
            Log.e(f8141b, "Parse poi search failed", e);
            poiResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            return false;
        }
    }

    /* renamed from: a */
    private boolean m17575a(JSONObject jSONObject, PoiResult poiResult) {
        if (jSONObject == null || jSONObject.length() == 0) {
            return false;
        }
        poiResult.error = SearchResult.ERRORNO.NO_ERROR;
        JSONArray optJSONArray = jSONObject.optJSONArray("results");
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            poiResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            return false;
        }
        int optInt = jSONObject.optInt("total");
        poiResult.setTotalPoiNum(optInt);
        int length = optJSONArray.length();
        poiResult.setCurrentPageCapacity(length);
        poiResult.setCurrentPageNum(this.f8142c);
        if (length != 0) {
            int i = this.f8143d;
            poiResult.setTotalPageNum((optInt / i) + (optInt % i > 0 ? 1 : 0));
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
            JSONObject jSONObject2 = (JSONObject) optJSONArray.opt(i2);
            if (jSONObject2 != null && jSONObject2.length() != 0) {
                PoiInfo poiInfo = new PoiInfo();
                poiInfo.setName(jSONObject2.optString("name"));
                poiInfo.setAddress(jSONObject2.optString("address"));
                poiInfo.setProvince(jSONObject2.optString("province"));
                poiInfo.setCity(jSONObject2.optString("city"));
                poiInfo.setArea(jSONObject2.optString("area"));
                poiInfo.setStreetId(jSONObject2.optString("street_id"));
                poiInfo.setUid(jSONObject2.optString("uid"));
                poiInfo.setPhoneNum(jSONObject2.optString("telephone"));
                poiInfo.setDetail(jSONObject2.optInt("detail"));
                poiInfo.setAdCode(jSONObject2.optInt("adcode"));
                poiInfo.setLocation(m17576a(jSONObject2.optJSONObject("location")));
                String optString = jSONObject2.optString("detail_info");
                if (optString != null && optString.length() != 0) {
                    poiInfo.setPoiDetailInfo(m17574b(optString));
                }
                arrayList.add(poiInfo);
            }
        }
        poiResult.setPoiInfo(arrayList);
        return true;
    }

    /* renamed from: b */
    private PoiDetailInfo m17574b(String str) {
        PoiDetailInfo poiDetailInfo = new PoiDetailInfo();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.length() == 0) {
                return null;
            }
            poiDetailInfo.setDistance(jSONObject.optInt("distance", 0));
            poiDetailInfo.setTag(jSONObject.optString("tag"));
            poiDetailInfo.setDetailUrl(jSONObject.optString("detail_url"));
            poiDetailInfo.setType(jSONObject.optString("type"));
            poiDetailInfo.setPrice(jSONObject.optDouble("price", 0.0d));
            poiDetailInfo.setOverallRating(jSONObject.optDouble("overall_rating", 0.0d));
            poiDetailInfo.setTasteRating(jSONObject.optDouble("taste_rating", 0.0d));
            poiDetailInfo.setServiceRating(jSONObject.optDouble("service_rating", 0.0d));
            poiDetailInfo.setEnvironmentRating(jSONObject.optDouble("environment_rating", 0.0d));
            poiDetailInfo.setFacilityRating(jSONObject.optDouble("facility_rating", 0.0d));
            poiDetailInfo.setHygieneRating(jSONObject.optDouble("hygiene_rating", 0.0d));
            poiDetailInfo.setTechnologyRating(jSONObject.optDouble("technology_rating", 0.0d));
            poiDetailInfo.setImageNum(jSONObject.optInt("image_num"));
            poiDetailInfo.setGrouponNum(jSONObject.optInt("groupon_num"));
            poiDetailInfo.setCommentNum(jSONObject.optInt("comment_num"));
            poiDetailInfo.setDiscountNum(jSONObject.optInt("discount_num"));
            poiDetailInfo.setFavoriteNum(jSONObject.optInt("favorite_num"));
            poiDetailInfo.setCheckinNum(jSONObject.optInt("checkin_num"));
            poiDetailInfo.setShopHours(jSONObject.optString("shop_hours"));
            poiDetailInfo.naviLocation = m17576a(jSONObject.optJSONObject("navi_location"));
            SearchType a = m18092a();
            if (SearchType.POI_IN_CITY_SEARCH == a || SearchType.POI_NEAR_BY_SEARCH == a) {
                poiDetailInfo.setPoiChildrenInfoList(m17573b(jSONObject));
            }
            return poiDetailInfo;
        } catch (JSONException e) {
            Log.e(f8141b, "Parse poi search detail info failed", e);
            return null;
        }
    }

    /* renamed from: a */
    private LatLng m17576a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        double optDouble = jSONObject.optDouble("lat");
        double optDouble2 = jSONObject.optDouble("lng");
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            return CoordTrans.baiduToGcj(new LatLng(optDouble, optDouble2));
        }
        return new LatLng(optDouble, optDouble2);
    }

    /* renamed from: b */
    private List<PoiChildrenInfo> m17573b(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("children");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null && optJSONObject.length() != 0) {
                PoiChildrenInfo poiChildrenInfo = new PoiChildrenInfo();
                poiChildrenInfo.setUid(optJSONObject.optString("uid"));
                poiChildrenInfo.setName(optJSONObject.optString("name"));
                poiChildrenInfo.setShowName(optJSONObject.optString("show_name"));
                poiChildrenInfo.setTag(optJSONObject.optString("tag"));
                poiChildrenInfo.setLocation(m17576a(optJSONObject.optJSONObject("location")));
                poiChildrenInfo.setAddress(optJSONObject.optString("address"));
                arrayList.add(poiChildrenInfo);
            }
        }
        return arrayList;
    }

    @Override // com.baidu.platform.base.SearchParser
    /* renamed from: a */
    public void mo17486a(SearchResult searchResult, Object obj) {
        if (obj == null || !(obj instanceof OnGetPoiSearchResultListener)) {
            return;
        }
        switch (m18092a()) {
            case POI_NEAR_BY_SEARCH:
            case POI_IN_CITY_SEARCH:
            case POI_IN_BOUND_SEARCH:
                ((OnGetPoiSearchResultListener) obj).onGetPoiResult((PoiResult) searchResult);
                return;
            default:
                return;
        }
    }
}
