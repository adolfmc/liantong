package com.baidu.platform.core.p162f;

import com.baidu.mapapi.common.Logger;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.TaxiInfo;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.f.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DriveRouteParser extends RoutePlaneParser {
    /* renamed from: a */
    public void m17554a(String str, DrivingRouteResult drivingRouteResult) {
        if (str == null || str.equals("")) {
            drivingRouteResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("SDK_InnerError")) {
                JSONObject optJSONObject = jSONObject.optJSONObject("SDK_InnerError");
                if (optJSONObject.has("PermissionCheckError")) {
                    drivingRouteResult.error = SearchResult.ERRORNO.PERMISSION_UNFINISHED;
                    return;
                } else if (optJSONObject.has("httpStateError")) {
                    String optString = optJSONObject.optString("httpStateError");
                    if (optString.equals("NETWORK_ERROR")) {
                        drivingRouteResult.error = SearchResult.ERRORNO.NETWORK_ERROR;
                        return;
                    } else if (optString.equals("REQUEST_ERROR")) {
                        drivingRouteResult.error = SearchResult.ERRORNO.REQUEST_ERROR;
                        return;
                    } else {
                        drivingRouteResult.error = SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                        return;
                    }
                }
            }
            if (m18090a(str, drivingRouteResult, false) || m17548b(str, drivingRouteResult)) {
                return;
            }
            drivingRouteResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
        } catch (Exception unused) {
            drivingRouteResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
        }
    }

    /* renamed from: b */
    private boolean m17548b(String str, DrivingRouteResult drivingRouteResult) {
        JSONObject jSONObject;
        JSONArray jSONArray;
        JSONArray jSONArray2;
        boolean z = false;
        if (str == null || "".equals(str)) {
            return false;
        }
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            JSONObject optJSONObject = jSONObject2.optJSONObject("result");
            if (optJSONObject == null) {
                return false;
            }
            int optInt = optJSONObject.optInt("error");
            if (optInt != 0) {
                if (optInt != 4) {
                    return false;
                }
                drivingRouteResult.error = SearchResult.ERRORNO.ST_EN_TOO_NEAR;
                return true;
            }
            JSONObject optJSONObject2 = jSONObject2.optJSONObject("cars");
            if (optJSONObject2 == null) {
                return false;
            }
            JSONObject optJSONObject3 = optJSONObject2.optJSONObject("option");
            JSONObject optJSONObject4 = optJSONObject2.optJSONObject("content");
            if (optJSONObject3 == null || optJSONObject4 == null) {
                return false;
            }
            RouteNode m17550a = m17550a(optJSONObject3.optJSONObject("start"));
            ArrayList arrayList = new ArrayList();
            RouteNode m17552a = m17552a(optJSONObject3.optJSONArray("end"), arrayList);
            List<DrivingRouteLine.DrivingStep> m17551a = m17551a(optJSONObject4.optJSONArray("steps"), optJSONObject4.optJSONArray("stepts"));
            ArrayList arrayList2 = new ArrayList();
            JSONArray optJSONArray = optJSONObject4.optJSONArray("routes");
            if (optJSONArray == null) {
                return false;
            }
            int i = 0;
            while (i < optJSONArray.length()) {
                DrivingRouteLine drivingRouteLine = new DrivingRouteLine();
                JSONObject optJSONObject5 = optJSONArray.optJSONObject(i);
                if (optJSONObject5 == null) {
                    jSONObject = optJSONObject4;
                    jSONArray = optJSONArray;
                } else {
                    JSONArray optJSONArray2 = optJSONObject5.optJSONArray("legs");
                    if (optJSONArray2 == null) {
                        return z;
                    }
                    int length = optJSONArray2.length();
                    ArrayList arrayList3 = new ArrayList();
                    jSONObject = optJSONObject4;
                    jSONArray = optJSONArray;
                    int i2 = 0;
                    int i3 = 0;
                    int i4 = 0;
                    while (i3 < length) {
                        int i5 = length;
                        JSONObject optJSONObject6 = optJSONArray2.optJSONObject(i3);
                        if (optJSONObject6 == null) {
                            jSONArray2 = optJSONArray2;
                        } else {
                            jSONArray2 = optJSONArray2;
                            i4 += optJSONObject6.optInt("distance");
                            i2 += optJSONObject6.optInt("duration");
                            List<DrivingRouteLine.DrivingStep> m17547b = m17547b(optJSONObject6.optJSONArray("stepis"), m17551a);
                            if (m17547b != null) {
                                arrayList3.addAll(m17547b);
                            }
                        }
                        i3++;
                        length = i5;
                        optJSONArray2 = jSONArray2;
                    }
                    drivingRouteLine.setStarting(m17550a);
                    drivingRouteLine.setTerminal(m17552a);
                    if (arrayList.size() == 0) {
                        drivingRouteLine.setWayPoints(null);
                    } else {
                        drivingRouteLine.setWayPoints(arrayList);
                    }
                    drivingRouteLine.setDistance(i4);
                    drivingRouteLine.setDuration(i2);
                    drivingRouteLine.setCongestionDistance(optJSONObject5.optInt("congestion_length"));
                    drivingRouteLine.setLightNum(optJSONObject5.optInt("light_num"));
                    drivingRouteLine.setToll(optJSONObject5.optInt("toll"));
                    if (arrayList3.size() == 0) {
                        drivingRouteLine.setSteps(null);
                    } else {
                        drivingRouteLine.setSteps(arrayList3);
                    }
                    arrayList2.add(drivingRouteLine);
                }
                i++;
                optJSONArray = jSONArray;
                optJSONObject4 = jSONObject;
                z = false;
            }
            drivingRouteResult.setRouteLines(arrayList2);
            drivingRouteResult.setTaxiInfos(m17549b(optJSONObject4.optString("taxis")));
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    private RouteNode m17550a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        RouteNode routeNode = new RouteNode();
        routeNode.setTitle(jSONObject.optString("wd"));
        routeNode.setUid(jSONObject.optString("uid"));
        GeoPoint geoPoint = new GeoPoint(0, 0);
        JSONArray optJSONArray = jSONObject.optJSONArray("spt");
        if (optJSONArray != null) {
            geoPoint.setLongitudeE6(optJSONArray.optInt(0));
            geoPoint.setLatitudeE6(optJSONArray.optInt(1));
        }
        routeNode.setLocation(CoordUtil.mc2ll(geoPoint));
        return routeNode;
    }

    /* renamed from: a */
    private RouteNode m17552a(JSONArray jSONArray, List<RouteNode> list) {
        int length;
        if (jSONArray == null || (length = jSONArray.length()) <= 0) {
            return null;
        }
        for (int i = 0; i < length; i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                RouteNode m17550a = m17550a(optJSONObject);
                if (i == length - 1) {
                    return m17550a;
                }
                list.add(m17550a);
            }
        }
        return null;
    }

    /* renamed from: b */
    private List<TaxiInfo> m17549b(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject != null) {
                    TaxiInfo taxiInfo = new TaxiInfo();
                    String optString = jSONObject.optString("total_price");
                    if (optString != null && !optString.equals("")) {
                        taxiInfo.setTotalPrice(Float.parseFloat(optString));
                        arrayList.add(taxiInfo);
                    }
                    taxiInfo.setTotalPrice(0.0f);
                    arrayList.add(taxiInfo);
                }
            }
            return arrayList;
        } catch (JSONException e) {
            if (Logger.debugEnable()) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0029  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.List<com.baidu.mapapi.search.route.DrivingRouteLine.DrivingStep> m17551a(org.json.JSONArray r20, org.json.JSONArray r21) {
        /*
            Method dump skipped, instructions count: 359
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.core.p162f.DriveRouteParser.m17551a(org.json.JSONArray, org.json.JSONArray):java.util.List");
    }

    /* renamed from: b */
    private int[] m17546b(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("end");
        JSONArray optJSONArray2 = jSONObject.optJSONArray("status");
        if (optJSONArray == null || optJSONArray2 == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int length = optJSONArray.length();
        int length2 = optJSONArray2.length();
        int i = 0;
        while (i < length) {
            int optInt = optJSONArray.optInt(i);
            int optInt2 = i < length2 ? optJSONArray2.optInt(i) : 0;
            for (int i2 = 0; i2 < optInt; i2++) {
                arrayList.add(Integer.valueOf(optInt2));
            }
            i++;
        }
        int[] iArr = new int[arrayList.size()];
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            iArr[i3] = ((Integer) arrayList.get(i3)).intValue();
        }
        return iArr;
    }

    /* renamed from: a */
    private List<LatLng> m17553a(JSONArray jSONArray) {
        int length;
        if (jSONArray == null || (length = jSONArray.length()) < 6) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        double d = 0.0d;
        double d2 = 0.0d;
        for (int i = 5; i < length; i++) {
            if (i % 2 != 0) {
                d2 += jSONArray.optInt(i);
            } else {
                d += jSONArray.optInt(i);
                arrayList.add(CoordUtil.mc2ll(new GeoPoint(d, d2)));
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    private List<DrivingRouteLine.DrivingStep> m17547b(JSONArray jSONArray, List<DrivingRouteLine.DrivingStep> list) {
        int length;
        if (jSONArray == null || (length = jSONArray.length()) <= 0 || list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                int optInt = optJSONObject.optInt("n");
                int optInt2 = optJSONObject.optInt("s");
                for (int i2 = 0; i2 < optInt; i2++) {
                    int i3 = optInt2 + i2;
                    if (i3 < list.size()) {
                        arrayList.add(list.get(i3));
                    }
                }
            }
        }
        return arrayList;
    }
}
