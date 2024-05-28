package com.baidu.platform.base;

import com.baidu.mapapi.search.core.SearchResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.base.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class SearchParser {

    /* renamed from: a */
    protected SearchType f7507a;

    /* renamed from: a */
    public abstract SearchResult mo17483a(String str);

    /* renamed from: a */
    public abstract void mo17486a(SearchResult searchResult, Object obj);

    /* renamed from: a */
    public void m18091a(SearchType searchType) {
        this.f7507a = searchType;
    }

    /* renamed from: a */
    public SearchType m18092a() {
        return this.f7507a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public boolean m18090a(String str, SearchResult searchResult, boolean z) {
        int optInt;
        if (str != null) {
            try {
                if (str.length() > 0) {
                    JSONObject jSONObject = new JSONObject(str);
                    if (z) {
                        optInt = jSONObject.optInt("status");
                    } else {
                        optInt = jSONObject.optInt("status_sp");
                    }
                    if (optInt != 0) {
                        switch (optInt) {
                            case 2:
                            case 45:
                                searchResult.error = SearchResult.ERRORNO.NO_ADVANCED_PERMISSION;
                                break;
                            case 10:
                            case 11:
                                searchResult.error = SearchResult.ERRORNO.PARAMER_ERROR;
                                break;
                            case 40:
                                searchResult.error = SearchResult.ERRORNO.INVALID_DISTRICT_ID;
                                break;
                            case 41:
                            case 44:
                                searchResult.error = SearchResult.ERRORNO.NO_DATA_FOR_LATLNG;
                                break;
                            case 104:
                            case 105:
                            case 106:
                            case 107:
                            case 108:
                                searchResult.error = SearchResult.ERRORNO.PERMISSION_UNFINISHED;
                                break;
                            case 200:
                            case 230:
                                searchResult.error = SearchResult.ERRORNO.KEY_ERROR;
                                break;
                            default:
                                searchResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                                break;
                        }
                        return true;
                    }
                    return false;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                searchResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                return true;
            }
        }
        searchResult.error = SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
        return true;
    }
}
