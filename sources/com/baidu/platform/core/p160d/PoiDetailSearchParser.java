package com.baidu.platform.core.p160d;

import android.util.Log;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiDetailInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.baidu.platform.base.SearchParser;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.d.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PoiDetailSearchParser extends SearchParser {

    /* renamed from: b */
    private static final String f8138b = "d";

    /* renamed from: c */
    private boolean f8139c = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m17586a(boolean z) {
        this.f8139c = z;
    }

    @Override // com.baidu.platform.base.SearchParser
    /* renamed from: a */
    public SearchResult mo17483a(String str) {
        SearchResult poiDetailResult;
        if (this.f8139c) {
            poiDetailResult = new PoiDetailSearchResult();
        } else {
            poiDetailResult = new PoiDetailResult();
        }
        if (str == null || str.isEmpty()) {
            poiDetailResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            return poiDetailResult;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.length() == 0) {
                poiDetailResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                return poiDetailResult;
            } else if (!jSONObject.has("SDK_InnerError")) {
                if (!m17590a(str, poiDetailResult)) {
                    poiDetailResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                }
                return poiDetailResult;
            } else {
                JSONObject optJSONObject = jSONObject.optJSONObject("SDK_InnerError");
                if (optJSONObject == null || optJSONObject.length() == 0) {
                    poiDetailResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                    return poiDetailResult;
                } else if (optJSONObject.has("PermissionCheckError")) {
                    poiDetailResult.error = SearchResult.ERRORNO.PERMISSION_UNFINISHED;
                    return poiDetailResult;
                } else {
                    if (optJSONObject.has("httpStateError")) {
                        String optString = optJSONObject.optString("httpStateError");
                        char c = 65535;
                        int hashCode = optString.hashCode();
                        if (hashCode != -879828873) {
                            if (hashCode == 1470557208 && optString.equals("REQUEST_ERROR")) {
                                c = 1;
                            }
                        } else if (optString.equals("NETWORK_ERROR")) {
                            c = 0;
                        }
                        switch (c) {
                            case 0:
                                poiDetailResult.error = SearchResult.ERRORNO.NETWORK_ERROR;
                                break;
                            case 1:
                                poiDetailResult.error = SearchResult.ERRORNO.REQUEST_ERROR;
                                break;
                            default:
                                poiDetailResult.error = SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                                break;
                        }
                    }
                    return poiDetailResult;
                }
            }
        } catch (JSONException e) {
            Log.e(f8138b, "Parse detail search result failed", e);
            poiDetailResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            return poiDetailResult;
        }
    }

    /* renamed from: a */
    private boolean m17590a(String str, SearchResult searchResult) {
        JSONArray optJSONArray;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.length() == 0 || jSONObject.optInt("status") != 0 || (optJSONArray = jSONObject.optJSONArray("result")) == null || optJSONArray.length() == 0) {
                return false;
            }
            if (this.f8139c) {
                return m17588a(optJSONArray, (PoiDetailSearchResult) searchResult);
            }
            return m17589a(optJSONArray, (PoiDetailResult) searchResult);
        } catch (JSONException e) {
            Log.e(f8138b, "Parse detail search result error", e);
            return false;
        }
    }

    /* renamed from: a */
    private boolean m17588a(JSONArray jSONArray, PoiDetailSearchResult poiDetailSearchResult) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = (JSONObject) jSONArray.opt(i);
            if (jSONObject != null && jSONObject.length() != 0) {
                PoiDetailInfo poiDetailInfo = new PoiDetailInfo();
                poiDetailInfo.setName(jSONObject.optString("name"));
                poiDetailInfo.setLocation(m17587a(jSONObject.optJSONObject("location")));
                poiDetailInfo.setAddress(jSONObject.optString("address"));
                poiDetailInfo.setAdCode(jSONObject.optInt("adcode"));
                poiDetailInfo.setProvince(jSONObject.optString("province"));
                poiDetailInfo.setCity(jSONObject.optString("city"));
                poiDetailInfo.setArea(jSONObject.optString("area"));
                poiDetailInfo.setTelephone(jSONObject.optString("telephone"));
                poiDetailInfo.setUid(jSONObject.optString("uid"));
                poiDetailInfo.setStreetId(jSONObject.optString("setStreetId"));
                poiDetailInfo.setDetail(jSONObject.optString("detail"));
                JSONObject optJSONObject = jSONObject.optJSONObject("detail_info");
                if (optJSONObject != null && optJSONObject.length() != 0) {
                    poiDetailInfo.setDistance(optJSONObject.optInt("distance", 0));
                    poiDetailInfo.setType(optJSONObject.optString("type"));
                    poiDetailInfo.setTag(optJSONObject.optString("tag"));
                    poiDetailInfo.setDetailUrl(optJSONObject.optString("detail_url"));
                    poiDetailInfo.setPrice(optJSONObject.optDouble("price", 0.0d));
                    poiDetailInfo.setShopHours(optJSONObject.optString("shop_hours"));
                    poiDetailInfo.setOverallRating(optJSONObject.optDouble("overall_rating", 0.0d));
                    poiDetailInfo.setTasteRating(optJSONObject.optDouble("taste_rating", 0.0d));
                    poiDetailInfo.setServiceRating(optJSONObject.optDouble("service_rating", 0.0d));
                    poiDetailInfo.setEnvironmentRating(optJSONObject.optDouble("environment_rating", 0.0d));
                    poiDetailInfo.setFacilityRating(optJSONObject.optDouble("facility_rating", 0.0d));
                    poiDetailInfo.setHygieneRating(optJSONObject.optDouble("hygiene_rating", 0.0d));
                    poiDetailInfo.setTechnologyRating(optJSONObject.optDouble("technology_rating", 0.0d));
                    poiDetailInfo.setImageNum(optJSONObject.optInt("image_num"));
                    poiDetailInfo.setGrouponNum(optJSONObject.optInt("groupon_num", 0));
                    poiDetailInfo.setCommentNum(optJSONObject.optInt("comment_num", 0));
                    poiDetailInfo.setDiscountNum(optJSONObject.optInt("discount_num", 0));
                    poiDetailInfo.setFavoriteNum(optJSONObject.optInt("favorite_num", 0));
                    poiDetailInfo.setCheckinNum(optJSONObject.optInt("checkin_num", 0));
                }
                arrayList.add(poiDetailInfo);
            }
        }
        poiDetailSearchResult.setPoiDetailInfoList(arrayList);
        poiDetailSearchResult.error = SearchResult.ERRORNO.NO_ERROR;
        return true;
    }

    /* renamed from: a */
    private boolean m17589a(JSONArray jSONArray, PoiDetailResult poiDetailResult) {
        JSONObject jSONObject = (JSONObject) jSONArray.opt(0);
        if (jSONObject == null || jSONObject.length() == 0) {
            return false;
        }
        poiDetailResult.setName(jSONObject.optString("name"));
        poiDetailResult.setLocation(m17587a(jSONObject.optJSONObject("location")));
        poiDetailResult.setAddress(jSONObject.optString("address"));
        poiDetailResult.setTelephone(jSONObject.optString("telephone"));
        poiDetailResult.setUid(jSONObject.optString("uid"));
        JSONObject optJSONObject = jSONObject.optJSONObject("detail_info");
        if (optJSONObject != null && optJSONObject.length() != 0) {
            poiDetailResult.setTag(optJSONObject.optString("tag"));
            poiDetailResult.setDetailUrl(optJSONObject.optString("detail_url"));
            poiDetailResult.setType(optJSONObject.optString("type"));
            poiDetailResult.setPrice(optJSONObject.optDouble("price", 0.0d));
            poiDetailResult.setOverallRating(optJSONObject.optDouble("overall_rating", 0.0d));
            poiDetailResult.setTasteRating(optJSONObject.optDouble("taste_rating", 0.0d));
            poiDetailResult.setServiceRating(optJSONObject.optDouble("service_rating", 0.0d));
            poiDetailResult.setEnvironmentRating(optJSONObject.optDouble("environment_rating", 0.0d));
            poiDetailResult.setFacilityRating(optJSONObject.optDouble("facility_rating", 0.0d));
            poiDetailResult.setHygieneRating(optJSONObject.optDouble("hygiene_rating", 0.0d));
            poiDetailResult.setTechnologyRating(optJSONObject.optDouble("technology_rating", 0.0d));
            poiDetailResult.setImageNum(optJSONObject.optInt("image_num"));
            poiDetailResult.setGrouponNum(optJSONObject.optInt("groupon_num", 0));
            poiDetailResult.setCommentNum(optJSONObject.optInt("comment_num", 0));
            poiDetailResult.setDiscountNum(optJSONObject.optInt("discount_num", 0));
            poiDetailResult.setFavoriteNum(optJSONObject.optInt("favorite_num", 0));
            poiDetailResult.setCheckinNum(optJSONObject.optInt("checkin_num", 0));
            poiDetailResult.setShopHours(optJSONObject.optString("shop_hours"));
        }
        poiDetailResult.error = SearchResult.ERRORNO.NO_ERROR;
        return true;
    }

    /* renamed from: a */
    private LatLng m17587a(JSONObject jSONObject) {
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

    @Override // com.baidu.platform.base.SearchParser
    /* renamed from: a */
    public void mo17486a(SearchResult searchResult, Object obj) {
        if (obj == null || !(obj instanceof OnGetPoiSearchResultListener)) {
            return;
        }
        if (this.f8139c) {
            ((OnGetPoiSearchResultListener) obj).onGetPoiDetailResult((PoiDetailSearchResult) searchResult);
        } else {
            ((OnGetPoiSearchResultListener) obj).onGetPoiDetailResult((PoiDetailResult) searchResult);
        }
    }
}
