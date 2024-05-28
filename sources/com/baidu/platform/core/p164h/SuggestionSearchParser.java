package com.baidu.platform.core.p164h;

import android.util.Log;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiChildrenInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
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
/* renamed from: com.baidu.platform.core.h.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SuggestionSearchParser extends SearchParser {

    /* renamed from: b */
    private static final String f8154b = "c";

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0069, code lost:
        if (r6.equals("NETWORK_ERROR") != false) goto L27;
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
            com.baidu.mapapi.search.sug.SuggestionResult r0 = new com.baidu.mapapi.search.sug.SuggestionResult
            r0.<init>()
            if (r6 == 0) goto L96
            boolean r1 = r6.isEmpty()
            if (r1 == 0) goto Lf
            goto L96
        Lf:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: org.json.JSONException -> L89
            r1.<init>(r6)     // Catch: org.json.JSONException -> L89
            int r2 = r1.length()
            if (r2 != 0) goto L1f
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.RESULT_NOT_FOUND
            r0.error = r6
            return r0
        L1f:
            java.lang.String r2 = "SDK_InnerError"
            boolean r2 = r1.has(r2)
            r3 = 0
            if (r2 == 0) goto L7f
            java.lang.String r2 = "SDK_InnerError"
            org.json.JSONObject r1 = r1.optJSONObject(r2)
            java.lang.String r2 = "PermissionCheckError"
            boolean r2 = r1.has(r2)
            if (r2 == 0) goto L3b
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.PERMISSION_UNFINISHED
            r0.error = r6
            return r0
        L3b:
            java.lang.String r2 = "httpStateError"
            boolean r2 = r1.has(r2)
            if (r2 == 0) goto L7f
            java.lang.String r6 = "httpStateError"
            java.lang.String r6 = r1.optString(r6)
            r1 = -1
            int r2 = r6.hashCode()
            r4 = -879828873(0xffffffffcb8ee077, float:-1.872715E7)
            if (r2 == r4) goto L63
            r3 = 1470557208(0x57a6ec18, float:3.6706589E14)
            if (r2 == r3) goto L59
            goto L6c
        L59:
            java.lang.String r2 = "REQUEST_ERROR"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L6c
            r3 = 1
            goto L6d
        L63:
            java.lang.String r2 = "NETWORK_ERROR"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L6c
            goto L6d
        L6c:
            r3 = r1
        L6d:
            switch(r3) {
                case 0: goto L7a;
                case 1: goto L75;
                default: goto L70;
            }
        L70:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR
            r0.error = r6
            goto L7e
        L75:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.REQUEST_ERROR
            r0.error = r6
            goto L7e
        L7a:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.NETWORK_ERROR
            r0.error = r6
        L7e:
            return r0
        L7f:
            boolean r1 = r5.m18090a(r6, r0, r3)
            if (r1 != 0) goto L88
            r5.m17491a(r6, r0)
        L88:
            return r0
        L89:
            r6 = move-exception
            java.lang.String r1 = com.baidu.platform.core.p164h.SuggestionSearchParser.f8154b
            java.lang.String r2 = "Parse suggestion search result error"
            android.util.Log.e(r1, r2, r6)
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.RESULT_NOT_FOUND
            r0.error = r6
            return r0
        L96:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.RESULT_NOT_FOUND
            r0.error = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.core.p164h.SuggestionSearchParser.mo17483a(java.lang.String):com.baidu.mapapi.search.core.SearchResult");
    }

    /* renamed from: a */
    private boolean m17491a(String str, SuggestionResult suggestionResult) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.length() == 0) {
                suggestionResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                return false;
            }
            int optInt = jSONObject.optInt("status");
            if (optInt == 0) {
                return m17488a(jSONObject, suggestionResult);
            }
            switch (optInt) {
                case 1:
                    suggestionResult.error = SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                    break;
                case 2:
                    suggestionResult.error = SearchResult.ERRORNO.SEARCH_OPTION_ERROR;
                    break;
                default:
                    suggestionResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                    break;
            }
            return false;
        } catch (JSONException e) {
            Log.e(f8154b, "Parse sug search error", e);
            suggestionResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            return false;
        }
    }

    /* renamed from: a */
    private boolean m17488a(JSONObject jSONObject, SuggestionResult suggestionResult) {
        if (jSONObject == null || jSONObject.length() == 0) {
            return false;
        }
        suggestionResult.error = SearchResult.ERRORNO.NO_ERROR;
        JSONArray optJSONArray = jSONObject.optJSONArray("result");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            suggestionResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            return false;
        }
        ArrayList<SuggestionResult.SuggestionInfo> arrayList = new ArrayList<>();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject jSONObject2 = (JSONObject) optJSONArray.opt(i);
            if (jSONObject2 != null && jSONObject2.length() != 0) {
                SuggestionResult.SuggestionInfo suggestionInfo = new SuggestionResult.SuggestionInfo();
                suggestionInfo.setKey(jSONObject2.optString("name"));
                suggestionInfo.setAdCode(jSONObject2.optInt("adcode"));
                suggestionInfo.setCity(jSONObject2.optString("city"));
                suggestionInfo.setDistrict(jSONObject2.optString("district"));
                suggestionInfo.setUid(jSONObject2.optString("uid"));
                suggestionInfo.setTag(jSONObject2.optString("tag"));
                suggestionInfo.setAddress(jSONObject2.optString("address"));
                suggestionInfo.setPt(m17489a(jSONObject2.optJSONObject("location")));
                JSONArray optJSONArray2 = jSONObject2.optJSONArray("children");
                if (optJSONArray2 != null && optJSONArray2.length() != 0) {
                    suggestionInfo.setPoiChildrenInfoList(m17490a(optJSONArray2));
                }
                arrayList.add(suggestionInfo);
            }
        }
        suggestionResult.setSuggestionInfo(arrayList);
        return true;
    }

    /* renamed from: a */
    private LatLng m17489a(JSONObject jSONObject) {
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

    /* renamed from: a */
    private List<PoiChildrenInfo> m17490a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null && optJSONObject.length() != 0) {
                PoiChildrenInfo poiChildrenInfo = new PoiChildrenInfo();
                poiChildrenInfo.setUid(optJSONObject.optString("uid"));
                poiChildrenInfo.setName(optJSONObject.optString("name"));
                poiChildrenInfo.setShowName(optJSONObject.optString("show_name"));
                poiChildrenInfo.setTag(optJSONObject.optString("tag"));
                poiChildrenInfo.setAddress(optJSONObject.optString("address"));
                arrayList.add(poiChildrenInfo);
            }
        }
        return arrayList;
    }

    @Override // com.baidu.platform.base.SearchParser
    /* renamed from: a */
    public void mo17486a(SearchResult searchResult, Object obj) {
        if (obj == null || !(obj instanceof OnGetSuggestionResultListener)) {
            return;
        }
        ((OnGetSuggestionResultListener) obj).onGetSuggestionResult((SuggestionResult) searchResult);
    }
}
