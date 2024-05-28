package com.baidu.platform.core.p160d;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiIndoorInfo;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.platform.base.SearchParser;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.d.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class IndoorPoiSearchParser extends SearchParser {
    @Override // com.baidu.platform.base.SearchParser
    /* renamed from: a */
    public SearchResult mo17483a(String str) {
        PoiIndoorResult poiIndoorResult = new PoiIndoorResult();
        if (str == null || str.equals("")) {
            poiIndoorResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            return poiIndoorResult;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("SDK_InnerError")) {
                JSONObject optJSONObject = jSONObject.optJSONObject("SDK_InnerError");
                if (optJSONObject.has("PermissionCheckError")) {
                    poiIndoorResult.error = SearchResult.ERRORNO.PERMISSION_UNFINISHED;
                    return poiIndoorResult;
                } else if (optJSONObject.has("httpStateError")) {
                    String optString = optJSONObject.optString("httpStateError");
                    if (optString.equals("NETWORK_ERROR")) {
                        poiIndoorResult.error = SearchResult.ERRORNO.NETWORK_ERROR;
                    } else if (optString.equals("REQUEST_ERROR")) {
                        poiIndoorResult.error = SearchResult.ERRORNO.REQUEST_ERROR;
                    } else {
                        poiIndoorResult.error = SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                    }
                    return poiIndoorResult;
                }
            }
            if (!m18090a(str, poiIndoorResult, false) && !m17592a(str, poiIndoorResult)) {
                poiIndoorResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            }
            return poiIndoorResult;
        } catch (Exception unused) {
            poiIndoorResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            return poiIndoorResult;
        }
    }

    /* renamed from: a */
    private boolean m17592a(String str, PoiIndoorResult poiIndoorResult) {
        if (str == null || "".equals(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("errNo");
            if (optInt != 5) {
                switch (optInt) {
                    case 0:
                        JSONObject optJSONObject = jSONObject.optJSONObject("data");
                        if (optJSONObject == null) {
                            return false;
                        }
                        JSONArray optJSONArray = optJSONObject.optJSONArray("poi_list");
                        if (optJSONArray != null && optJSONArray.length() > 0) {
                            ArrayList arrayList = new ArrayList();
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                JSONObject jSONObject2 = (JSONObject) optJSONArray.opt(i);
                                if (jSONObject2 != null) {
                                    PoiIndoorInfo poiIndoorInfo = new PoiIndoorInfo();
                                    poiIndoorInfo.address = jSONObject2.optString("address");
                                    poiIndoorInfo.bid = jSONObject2.optString("bd_id");
                                    poiIndoorInfo.cid = jSONObject2.optInt("cid");
                                    poiIndoorInfo.discount = jSONObject2.optInt("discount");
                                    poiIndoorInfo.floor = jSONObject2.optString("floor");
                                    poiIndoorInfo.name = jSONObject2.optString("name");
                                    poiIndoorInfo.phone = jSONObject2.optString("phone");
                                    poiIndoorInfo.price = jSONObject2.optInt("price");
                                    poiIndoorInfo.starLevel = jSONObject2.optInt("star_level");
                                    poiIndoorInfo.tag = jSONObject2.optString("tag");
                                    poiIndoorInfo.uid = jSONObject2.optString("uid");
                                    poiIndoorInfo.groupNum = jSONObject2.optInt("tuan_nums");
                                    int parseInt = Integer.parseInt(jSONObject2.optString("twp"));
                                    if ((parseInt & 1) == 1) {
                                        poiIndoorInfo.isGroup = true;
                                    }
                                    if ((parseInt & 2) == 1) {
                                        poiIndoorInfo.isTakeOut = true;
                                    }
                                    if ((parseInt & 4) == 1) {
                                        poiIndoorInfo.isWaited = true;
                                    }
                                    poiIndoorInfo.latLng = CoordUtil.mc2ll(new GeoPoint(jSONObject2.optDouble("pt_y"), jSONObject2.optDouble("pt_x")));
                                    arrayList.add(poiIndoorInfo);
                                }
                            }
                            poiIndoorResult.error = SearchResult.ERRORNO.NO_ERROR;
                            poiIndoorResult.setmArrayPoiInfo(arrayList);
                        } else {
                            poiIndoorResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                        }
                        poiIndoorResult.pageNum = optJSONObject.optInt("page_num");
                        poiIndoorResult.poiNum = optJSONObject.optInt("poi_num");
                        poiIndoorResult.error = SearchResult.ERRORNO.NO_ERROR;
                        return true;
                    case 1:
                        String optString = jSONObject.optString("Msg");
                        if (optString.contains("bid")) {
                            poiIndoorResult.error = SearchResult.ERRORNO.POIINDOOR_BID_ERROR;
                            return true;
                        } else if (optString.contains("floor")) {
                            poiIndoorResult.error = SearchResult.ERRORNO.POIINDOOR_FLOOR_ERROR;
                            return true;
                        }
                        break;
                    default:
                        poiIndoorResult.error = SearchResult.ERRORNO.POIINDOOR_SERVER_ERROR;
                        return true;
                }
            }
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.baidu.platform.base.SearchParser
    /* renamed from: a */
    public void mo17486a(SearchResult searchResult, Object obj) {
        if (obj == null || !(obj instanceof OnGetPoiSearchResultListener)) {
            return;
        }
        ((OnGetPoiSearchResultListener) obj).onGetPoiIndoorResult((PoiIndoorResult) searchResult);
    }
}
