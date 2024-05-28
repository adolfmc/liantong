package com.baidu.platform.core.p162f;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.IndoorRouteLine;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.platform.base.SearchParser;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.f.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class IndoorRouteParser extends SearchParser {
    @Override // com.baidu.platform.base.SearchParser
    /* renamed from: a */
    public SearchResult mo17483a(String str) {
        IndoorRouteResult indoorRouteResult = new IndoorRouteResult();
        if (str == null || str.equals("")) {
            indoorRouteResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            return indoorRouteResult;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("SDK_InnerError")) {
                JSONObject optJSONObject = jSONObject.optJSONObject("SDK_InnerError");
                if (optJSONObject.has("PermissionCheckError")) {
                    indoorRouteResult.error = SearchResult.ERRORNO.PERMISSION_UNFINISHED;
                    return indoorRouteResult;
                } else if (optJSONObject.has("httpStateError")) {
                    String optString = optJSONObject.optString("httpStateError");
                    if (optString.equals("NETWORK_ERROR")) {
                        indoorRouteResult.error = SearchResult.ERRORNO.NETWORK_ERROR;
                    } else if (optString.equals("REQUEST_ERROR")) {
                        indoorRouteResult.error = SearchResult.ERRORNO.REQUEST_ERROR;
                    } else {
                        indoorRouteResult.error = SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                    }
                    return indoorRouteResult;
                }
            }
            if (!m17544a(str, indoorRouteResult)) {
                indoorRouteResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            }
            return indoorRouteResult;
        } catch (Exception unused) {
            indoorRouteResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            return indoorRouteResult;
        }
    }

    /* renamed from: a */
    private boolean m17544a(String str, IndoorRouteResult indoorRouteResult) {
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        JSONArray jSONArray;
        int i;
        ArrayList arrayList;
        JSONArray jSONArray2;
        ArrayList arrayList2;
        int i2;
        JSONArray jSONArray3;
        int i3 = 0;
        if (str == null || "".equals(str)) {
            return false;
        }
        try {
            JSONObject optJSONObject3 = new JSONObject(str).optJSONObject("indoor_navi");
            if (optJSONObject3 == null || (optJSONObject = optJSONObject3.optJSONObject("option")) == null) {
                return false;
            }
            int optInt = optJSONObject.optInt("error");
            if (optInt != 0) {
                switch (optInt) {
                    case 6:
                        indoorRouteResult.error = SearchResult.ERRORNO.INDOOR_ROUTE_NO_IN_BUILDING;
                        return true;
                    case 7:
                        indoorRouteResult.error = SearchResult.ERRORNO.INDOOR_ROUTE_NO_IN_SAME_BUILDING;
                        return true;
                    default:
                        return false;
                }
            }
            JSONArray optJSONArray = optJSONObject3.optJSONArray("routes");
            if (optJSONArray == null || (optJSONObject2 = optJSONArray.optJSONObject(0)) == null) {
                return false;
            }
            ArrayList arrayList3 = new ArrayList();
            JSONArray optJSONArray2 = optJSONObject2.optJSONArray("legs");
            if (optJSONArray2 == null) {
                return false;
            }
            int i4 = 0;
            while (i4 < optJSONArray2.length()) {
                IndoorRouteLine indoorRouteLine = new IndoorRouteLine();
                JSONObject optJSONObject4 = optJSONArray2.optJSONObject(i4);
                if (optJSONObject4 == null) {
                    jSONArray = optJSONArray2;
                    arrayList = arrayList3;
                    i = i4;
                } else {
                    indoorRouteLine.setDistance(optJSONObject4.optInt("distance"));
                    indoorRouteLine.setDuration(optJSONObject4.optInt("duration"));
                    indoorRouteLine.setStarting(RouteNode.location(m17543a(optJSONObject4, "sstart_location")));
                    indoorRouteLine.setTerminal(RouteNode.location(m17543a(optJSONObject4, "send_location")));
                    JSONArray optJSONArray3 = optJSONObject4.optJSONArray("steps");
                    if (optJSONArray3 != null) {
                        ArrayList arrayList4 = new ArrayList();
                        int i5 = i3;
                        while (i5 < optJSONArray3.length()) {
                            IndoorRouteLine.IndoorRouteStep indoorRouteStep = new IndoorRouteLine.IndoorRouteStep();
                            JSONObject optJSONObject5 = optJSONArray3.optJSONObject(i5);
                            if (optJSONObject5 == null) {
                                jSONArray2 = optJSONArray2;
                                arrayList2 = arrayList3;
                                i2 = i4;
                                jSONArray3 = optJSONArray3;
                            } else {
                                indoorRouteStep.setDistance(optJSONObject5.optInt("distance"));
                                indoorRouteStep.setDuration(optJSONObject5.optInt("duration"));
                                indoorRouteStep.setBuildingId(optJSONObject5.optString("buildingid"));
                                indoorRouteStep.setFloorId(optJSONObject5.optString("floorid"));
                                indoorRouteStep.setEntrace(RouteNode.location(m17543a(optJSONObject5, "sstart_location")));
                                indoorRouteStep.setExit(RouteNode.location(m17543a(optJSONObject5, "send_location")));
                                JSONArray optJSONArray4 = optJSONObject5.optJSONArray("spath");
                                if (optJSONArray4 == null) {
                                    jSONArray2 = optJSONArray2;
                                    arrayList2 = arrayList3;
                                    i2 = i4;
                                    jSONArray3 = optJSONArray3;
                                } else {
                                    ArrayList arrayList5 = new ArrayList();
                                    int i6 = 5;
                                    double d = 0.0d;
                                    double d2 = 0.0d;
                                    while (i6 < optJSONArray4.length()) {
                                        double optDouble = d + optJSONArray4.optDouble(i6 + 1);
                                        double optDouble2 = d2 + optJSONArray4.optDouble(i6);
                                        JSONArray jSONArray4 = optJSONArray2;
                                        GeoPoint geoPoint = new GeoPoint(0, 0);
                                        geoPoint.setLatitudeE6((int) optDouble);
                                        geoPoint.setLongitudeE6((int) optDouble2);
                                        LatLng mc2ll = CoordUtil.mc2ll(geoPoint);
                                        arrayList5.add(Double.valueOf(mc2ll.latitude));
                                        arrayList5.add(Double.valueOf(mc2ll.longitude));
                                        i6 += 2;
                                        optJSONArray2 = jSONArray4;
                                        optJSONArray3 = optJSONArray3;
                                        d = optDouble;
                                        arrayList3 = arrayList3;
                                        d2 = optDouble2;
                                        i4 = i4;
                                    }
                                    jSONArray2 = optJSONArray2;
                                    arrayList2 = arrayList3;
                                    i2 = i4;
                                    jSONArray3 = optJSONArray3;
                                    indoorRouteStep.setPath(arrayList5);
                                    indoorRouteStep.setInstructions(optJSONObject5.optString("instructions"));
                                    JSONArray optJSONArray5 = optJSONObject5.optJSONArray("pois");
                                    if (optJSONArray5 != null) {
                                        ArrayList arrayList6 = new ArrayList();
                                        for (int i7 = 0; i7 < optJSONArray5.length(); i7++) {
                                            JSONObject optJSONObject6 = optJSONArray5.optJSONObject(i7);
                                            if (optJSONObject6 != null) {
                                                IndoorRouteLine.IndoorRouteStep.IndoorStepNode indoorStepNode = new IndoorRouteLine.IndoorRouteStep.IndoorStepNode();
                                                indoorStepNode.setDetail(optJSONObject6.optString("detail"));
                                                indoorStepNode.setName(optJSONObject6.optString("name"));
                                                indoorStepNode.setType(optJSONObject6.optInt("type"));
                                                indoorStepNode.setLocation(m17543a(optJSONObject6, "location"));
                                                arrayList6.add(indoorStepNode);
                                            }
                                        }
                                        indoorRouteStep.setStepNodes(arrayList6);
                                    }
                                    arrayList4.add(indoorRouteStep);
                                }
                            }
                            i5++;
                            optJSONArray2 = jSONArray2;
                            optJSONArray3 = jSONArray3;
                            i4 = i2;
                            arrayList3 = arrayList2;
                        }
                        jSONArray = optJSONArray2;
                        ArrayList arrayList7 = arrayList3;
                        i = i4;
                        if (arrayList4.size() > 0) {
                            indoorRouteLine.setSteps(arrayList4);
                            arrayList = arrayList7;
                        } else {
                            arrayList = arrayList7;
                        }
                    } else {
                        jSONArray = optJSONArray2;
                        i = i4;
                        arrayList = arrayList3;
                    }
                    arrayList.add(indoorRouteLine);
                }
                i4 = i + 1;
                arrayList3 = arrayList;
                optJSONArray2 = jSONArray;
                i3 = 0;
            }
            indoorRouteResult.setRouteLines(arrayList3);
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    private LatLng m17543a(JSONObject jSONObject, String str) {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray != null) {
            GeoPoint geoPoint = new GeoPoint(0, 0);
            geoPoint.setLatitudeE6((int) optJSONArray.optDouble(1));
            geoPoint.setLongitudeE6((int) optJSONArray.optDouble(0));
            return CoordUtil.mc2ll(geoPoint);
        }
        return null;
    }

    @Override // com.baidu.platform.base.SearchParser
    /* renamed from: a */
    public void mo17486a(SearchResult searchResult, Object obj) {
        if (obj == null || !(obj instanceof OnGetRoutePlanResultListener)) {
            return;
        }
        ((OnGetRoutePlanResultListener) obj).onGetIndoorRouteResult((IndoorRouteResult) searchResult);
    }
}
