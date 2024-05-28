package com.baidu.platform.core.p159c;

import android.text.TextUtils;
import android.util.Log;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.baidu.platform.base.SearchParser;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.c.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GeoCoderParser extends SearchParser {

    /* renamed from: b */
    private static final String f8136b = "b";

    /* renamed from: c */
    private String f8137c;

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0072, code lost:
        if (r6.equals("NETWORK_ERROR") != false) goto L30;
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
            com.baidu.mapapi.search.geocode.GeoCodeResult r0 = new com.baidu.mapapi.search.geocode.GeoCodeResult
            r0.<init>()
            if (r6 == 0) goto L9a
            java.lang.String r1 = ""
            boolean r1 = r6.equals(r1)
            if (r1 == 0) goto L11
            goto L9a
        L11:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: org.json.JSONException -> L8d
            r1.<init>(r6)     // Catch: org.json.JSONException -> L8d
            java.lang.String r2 = "SDK_InnerError"
            boolean r2 = r1.has(r2)
            r3 = 0
            if (r2 != 0) goto L31
            boolean r1 = r5.m18090a(r6, r0, r3)
            if (r1 == 0) goto L26
            return r0
        L26:
            boolean r6 = r5.m17610a(r6, r0)
            if (r6 != 0) goto L30
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.RESULT_NOT_FOUND
            r0.error = r6
        L30:
            return r0
        L31:
            java.lang.String r6 = "SDK_InnerError"
            org.json.JSONObject r6 = r1.optJSONObject(r6)
            java.lang.String r1 = "PermissionCheckError"
            boolean r1 = r6.has(r1)
            if (r1 == 0) goto L44
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.PERMISSION_UNFINISHED
            r0.error = r6
            return r0
        L44:
            java.lang.String r1 = "httpStateError"
            boolean r1 = r6.has(r1)
            if (r1 == 0) goto L88
            java.lang.String r1 = "httpStateError"
            java.lang.String r6 = r6.optString(r1)
            r1 = -1
            int r2 = r6.hashCode()
            r4 = -879828873(0xffffffffcb8ee077, float:-1.872715E7)
            if (r2 == r4) goto L6c
            r3 = 1470557208(0x57a6ec18, float:3.6706589E14)
            if (r2 == r3) goto L62
            goto L75
        L62:
            java.lang.String r2 = "REQUEST_ERROR"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L75
            r3 = 1
            goto L76
        L6c:
            java.lang.String r2 = "NETWORK_ERROR"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L75
            goto L76
        L75:
            r3 = r1
        L76:
            switch(r3) {
                case 0: goto L83;
                case 1: goto L7e;
                default: goto L79;
            }
        L79:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR
            r0.error = r6
            goto L87
        L7e:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.REQUEST_ERROR
            r0.error = r6
            goto L87
        L83:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.NETWORK_ERROR
            r0.error = r6
        L87:
            return r0
        L88:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.RESULT_NOT_FOUND
            r0.error = r6
            return r0
        L8d:
            r6 = move-exception
            java.lang.String r1 = com.baidu.platform.core.p159c.GeoCoderParser.f8136b
            java.lang.String r2 = "JSONException caught"
            android.util.Log.e(r1, r2, r6)
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.RESULT_NOT_FOUND
            r0.error = r6
            return r0
        L9a:
            com.baidu.mapapi.search.core.SearchResult$ERRORNO r6 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.RESULT_NOT_FOUND
            r0.error = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.core.p159c.GeoCoderParser.mo17483a(java.lang.String):com.baidu.mapapi.search.core.SearchResult");
    }

    /* renamed from: a */
    private boolean m17610a(String str, GeoCodeResult geoCodeResult) {
        if (TextUtils.isEmpty(str) || geoCodeResult == null) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("status");
            if (optInt != 0) {
                switch (optInt) {
                    case 1:
                        geoCodeResult.error = SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                        break;
                    case 2:
                        geoCodeResult.error = SearchResult.ERRORNO.SEARCH_OPTION_ERROR;
                        break;
                    default:
                        geoCodeResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                        break;
                }
                return false;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("result");
            if (optJSONObject == null) {
                geoCodeResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                return false;
            }
            geoCodeResult.setLocation(m17609a(optJSONObject.optJSONObject("location")));
            geoCodeResult.setAddress(this.f8137c);
            geoCodeResult.setPrecise(optJSONObject.optInt("precise"));
            geoCodeResult.setConfidence(optJSONObject.optInt("confidence"));
            geoCodeResult.setLevel(optJSONObject.optString("level"));
            geoCodeResult.error = SearchResult.ERRORNO.NO_ERROR;
            return true;
        } catch (JSONException e) {
            geoCodeResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            Log.e(f8136b, "Parse GeoCodeResult catch JSONException", e);
            return true;
        }
    }

    /* renamed from: a */
    private LatLng m17609a(JSONObject jSONObject) {
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
        if (obj == null || !(obj instanceof OnGetGeoCoderResultListener)) {
            return;
        }
        ((OnGetGeoCoderResultListener) obj).onGetGeoCodeResult((GeoCodeResult) searchResult);
    }

    /* renamed from: b */
    public void m17608b(String str) {
        this.f8137c = str;
    }
}
