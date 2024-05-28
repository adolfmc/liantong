package com.baidu.platform.core.busline;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.busline.BusLineResult;
import com.baidu.mapapi.search.busline.OnGetBusLineSearchResultListener;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.platform.base.SearchParser;
import com.chinaunicon.jtwifilib.core.utils.JtDateUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.busline.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BusLineParser extends SearchParser {
    @Override // com.baidu.platform.base.SearchParser
    /* renamed from: a */
    public SearchResult mo17483a(String str) {
        BusLineResult busLineResult = new BusLineResult();
        if (str == null || str.equals("")) {
            busLineResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            return busLineResult;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("SDK_InnerError")) {
                JSONObject optJSONObject = jSONObject.optJSONObject("SDK_InnerError");
                if (optJSONObject.has("PermissionCheckError")) {
                    busLineResult.error = SearchResult.ERRORNO.PERMISSION_UNFINISHED;
                    return busLineResult;
                } else if (optJSONObject.has("httpStateError")) {
                    String optString = optJSONObject.optString("httpStateError");
                    if (optString.equals("NETWORK_ERROR")) {
                        busLineResult.error = SearchResult.ERRORNO.NETWORK_ERROR;
                    } else if (optString.equals("REQUEST_ERROR")) {
                        busLineResult.error = SearchResult.ERRORNO.REQUEST_ERROR;
                    } else {
                        busLineResult.error = SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                    }
                    return busLineResult;
                }
            }
            if (!m18090a(str, busLineResult, false) && !m17615a(str, busLineResult)) {
                busLineResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            }
            return busLineResult;
        } catch (Exception unused) {
            busLineResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            return busLineResult;
        }
    }

    /* renamed from: a */
    public boolean m17615a(String str, BusLineResult busLineResult) {
        JSONArray optJSONArray;
        if (str == null || "".equals(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject optJSONObject = jSONObject.optJSONObject("result");
            JSONObject optJSONObject2 = jSONObject.optJSONObject("bsl");
            if (optJSONObject == null || optJSONObject2 == null || (optJSONArray = optJSONObject2.optJSONArray("content")) == null || optJSONArray.length() <= 0) {
                return false;
            }
            JSONObject optJSONObject3 = optJSONArray.optJSONObject(0);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(JtDateUtil.dateFormatHM);
            try {
                busLineResult.setStartTime(simpleDateFormat.parse(optJSONObject3.optString("startTime")));
                busLineResult.setEndTime(simpleDateFormat.parse(optJSONObject3.optString("endTime")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            busLineResult.setBusLineName(optJSONObject3.optString("name"));
            busLineResult.setMonthTicket(optJSONObject3.optInt("isMonTicket") == 1);
            busLineResult.setUid(optJSONObject3.optString("uid"));
            busLineResult.setBasePrice(optJSONObject3.optInt("ticketPrice") / 100.0f);
            busLineResult.setLineDirection(optJSONObject3.optString("line_direction"));
            busLineResult.setMaxPrice(optJSONObject3.optInt("maxPrice") / 100.0f);
            ArrayList arrayList = new ArrayList();
            List<List<LatLng>> decodeLocationList2D = CoordUtil.decodeLocationList2D(optJSONObject3.optString("geo"));
            if (decodeLocationList2D != null) {
                for (List<LatLng> list : decodeLocationList2D) {
                    BusLineResult.BusStep busStep = new BusLineResult.BusStep();
                    busStep.setWayPoints(list);
                    arrayList.add(busStep);
                }
            }
            if (arrayList.size() > 0) {
                busLineResult.setSteps(arrayList);
            }
            JSONArray optJSONArray2 = optJSONObject3.optJSONArray("stations");
            if (optJSONArray2 != null) {
                ArrayList arrayList2 = new ArrayList();
                for (int i = 0; i < optJSONArray2.length(); i++) {
                    JSONObject optJSONObject4 = optJSONArray2.optJSONObject(i);
                    if (optJSONObject4 != null) {
                        BusLineResult.BusStation busStation = new BusLineResult.BusStation();
                        busStation.setTitle(optJSONObject4.optString("name"));
                        busStation.setLocation(CoordUtil.decodeLocation(optJSONObject4.optString("geo")));
                        busStation.setUid(optJSONObject4.optString("uid"));
                        arrayList2.add(busStation);
                    }
                }
                if (arrayList2.size() > 0) {
                    busLineResult.setStations(arrayList2);
                }
            }
            busLineResult.error = SearchResult.ERRORNO.NO_ERROR;
            return true;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @Override // com.baidu.platform.base.SearchParser
    /* renamed from: a */
    public void mo17486a(SearchResult searchResult, Object obj) {
        if (obj == null || !(obj instanceof OnGetBusLineSearchResultListener)) {
            return;
        }
        ((OnGetBusLineSearchResultListener) obj).onGetBusLineResult((BusLineResult) searchResult);
    }
}
