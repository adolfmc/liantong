package com.baidu.platform.core.p157a;

import android.util.Log;
import com.baidu.mapapi.search.building.BuildingResult;
import com.baidu.mapapi.search.building.OnGetBuildingSearchResultListener;
import com.baidu.mapapi.search.core.BuildingInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.platform.base.SearchParser;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.a.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BuildingParser extends SearchParser {

    /* renamed from: b */
    private static final String f8129b = "a";

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0063, code lost:
        if (r6.equals("REQUEST_ERROR") == false) goto L34;
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
            com.baidu.mapapi.search.building.BuildingResult r0 = new com.baidu.mapapi.search.building.BuildingResult
            r0.<init>()
            if (r6 == 0) goto L9a
            boolean r1 = r6.isEmpty()
            if (r1 == 0) goto Lf
            goto L9a
        Lf:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: org.json.JSONException -> L8d
            r1.<init>(r6)     // Catch: org.json.JSONException -> L8d
            int r2 = r1.length()
            if (r2 != 0) goto L1f
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.RESULT_NOT_FOUND
            r0.error = r6
            return r0
        L1f:
            java.lang.String r2 = "SDK_InnerError"
            boolean r2 = r1.has(r2)
            r3 = 1
            if (r2 == 0) goto L83
            java.lang.String r2 = "SDK_InnerError"
            org.json.JSONObject r1 = r1.optJSONObject(r2)
            if (r1 == 0) goto L3d
            java.lang.String r2 = "PermissionCheckError"
            boolean r2 = r1.has(r2)
            if (r2 == 0) goto L3d
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.PERMISSION_UNFINISHED
            r0.error = r6
            return r0
        L3d:
            if (r1 == 0) goto L83
            java.lang.String r2 = "httpStateError"
            boolean r2 = r1.has(r2)
            if (r2 == 0) goto L83
            java.lang.String r6 = "httpStateError"
            java.lang.String r6 = r1.optString(r6)
            r1 = -1
            int r2 = r6.hashCode()
            r4 = -879828873(0xffffffffcb8ee077, float:-1.872715E7)
            if (r2 == r4) goto L66
            r4 = 1470557208(0x57a6ec18, float:3.6706589E14)
            if (r2 == r4) goto L5d
            goto L70
        L5d:
            java.lang.String r2 = "REQUEST_ERROR"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L70
            goto L71
        L66:
            java.lang.String r2 = "NETWORK_ERROR"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L70
            r3 = 0
            goto L71
        L70:
            r3 = r1
        L71:
            switch(r3) {
                case 0: goto L7e;
                case 1: goto L79;
                default: goto L74;
            }
        L74:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR
            r0.error = r6
            goto L82
        L79:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.REQUEST_ERROR
            r0.error = r6
            goto L82
        L7e:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.NETWORK_ERROR
            r0.error = r6
        L82:
            return r0
        L83:
            boolean r1 = r5.m18090a(r6, r0, r3)
            if (r1 != 0) goto L8c
            r5.m17629a(r6, r0)
        L8c:
            return r0
        L8d:
            r6 = move-exception
            java.lang.String r1 = com.baidu.platform.core.p157a.BuildingParser.f8129b
            java.lang.String r2 = "ParseBuidingResult: "
            android.util.Log.e(r1, r2, r6)
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.RESULT_NOT_FOUND
            r0.error = r6
            return r0
        L9a:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.RESULT_NOT_FOUND
            r0.error = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.core.p157a.BuildingParser.mo17483a(java.lang.String):com.baidu.mapapi.search.core.SearchResult");
    }

    @Override // com.baidu.platform.base.SearchParser
    /* renamed from: a */
    public void mo17486a(SearchResult searchResult, Object obj) {
        if (obj instanceof OnGetBuildingSearchResultListener) {
            ((OnGetBuildingSearchResultListener) obj).onGetBuildingResult((BuildingResult) searchResult);
        }
    }

    /* renamed from: a */
    private boolean m17629a(String str, BuildingResult buildingResult) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.length() == 0) {
                buildingResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                return false;
            }
            int optInt = jSONObject.optInt("status");
            if (optInt == 0) {
                buildingResult.setRelation("in".equals(jSONObject.optString("relation")) ? 1 : 0);
                return m17628a(jSONObject, buildingResult);
            }
            switch (optInt) {
                case 1:
                    buildingResult.error = SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                    break;
                case 2:
                    buildingResult.error = SearchResult.ERRORNO.SEARCH_OPTION_ERROR;
                    break;
                default:
                    buildingResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                    break;
            }
            return false;
        } catch (JSONException e) {
            Log.e(f8129b, "ParseBuidingResult error: ", e);
            buildingResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            return false;
        }
    }

    /* renamed from: a */
    private boolean m17628a(JSONObject jSONObject, BuildingResult buildingResult) {
        JSONArray optJSONArray = jSONObject.optJSONArray("buildinginfo");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject jSONObject2 = (JSONObject) optJSONArray.opt(i);
            if (jSONObject2 != null) {
                BuildingInfo buildingInfo = new BuildingInfo();
                buildingInfo.m18763a(jSONObject2.optString("label").equals("main") ? 1 : 0);
                buildingInfo.m18762a(jSONObject2.optString("struct_id"));
                buildingInfo.setHeight((float) jSONObject2.optLong("height"));
                buildingInfo.m18761b(jSONObject2.optInt("accuracy"));
                buildingInfo.m18760b(jSONObject2.optString("geom"));
                buildingInfo.m18759c(jSONObject2.optString("center"));
                arrayList.add(buildingInfo);
            }
        }
        buildingResult.setBuildingList(arrayList);
        return true;
    }
}
