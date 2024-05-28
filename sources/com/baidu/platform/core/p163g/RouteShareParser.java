package com.baidu.platform.core.p163g;

import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.share.OnGetShareUrlResultListener;
import com.baidu.mapapi.search.share.ShareUrlResult;
import com.baidu.platform.base.SearchParser;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.g.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RouteShareParser extends SearchParser {
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x009b -> B:41:0x00a2). Please submit an issue!!! */
    @Override // com.baidu.platform.base.SearchParser
    /* renamed from: a */
    public SearchResult mo17483a(String str) {
        ShareUrlResult shareUrlResult = new ShareUrlResult();
        if (str == null || str.equals("")) {
            shareUrlResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            return shareUrlResult;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("SDK_InnerError")) {
                JSONObject optJSONObject = jSONObject.optJSONObject("SDK_InnerError");
                if (optJSONObject.has("PermissionCheckError")) {
                    shareUrlResult.error = SearchResult.ERRORNO.PERMISSION_UNFINISHED;
                    return shareUrlResult;
                } else if (optJSONObject.has("httpStateError")) {
                    String optString = optJSONObject.optString("httpStateError");
                    if (optString.equals("NETWORK_ERROR")) {
                        shareUrlResult.error = SearchResult.ERRORNO.NETWORK_ERROR;
                    } else if (optString.equals("REQUEST_ERROR")) {
                        shareUrlResult.error = SearchResult.ERRORNO.REQUEST_ERROR;
                    } else {
                        shareUrlResult.error = SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                    }
                    return shareUrlResult;
                }
            }
            if (!m18090a(str, shareUrlResult, false)) {
                if (str == null) {
                    shareUrlResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                }
                try {
                    JSONObject jSONObject2 = new JSONObject(str);
                    if (str != null) {
                        if (jSONObject2.optInt("status_sdk") != 0) {
                            shareUrlResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                        } else {
                            shareUrlResult.setUrl(jSONObject2.optString("shorturl"));
                            shareUrlResult.setType(m18092a().ordinal());
                            shareUrlResult.error = SearchResult.ERRORNO.NO_ERROR;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    shareUrlResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                }
            }
            return shareUrlResult;
        } catch (Exception unused) {
            shareUrlResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            return shareUrlResult;
        }
    }

    @Override // com.baidu.platform.base.SearchParser
    /* renamed from: a */
    public void mo17486a(SearchResult searchResult, Object obj) {
        if (obj == null || !(obj instanceof OnGetShareUrlResultListener)) {
            return;
        }
        ((OnGetShareUrlResultListener) obj).onGetRouteShareUrlResult((ShareUrlResult) searchResult);
    }
}
