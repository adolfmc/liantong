package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps;

import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SuggestionSearchManager {
    private static SuggestionSearch suggestionSearch;

    public static void suggestionSearch(String str, String str2, boolean z, OnGetSuggestionResultListener onGetSuggestionResultListener) {
        suggestionSearch = SuggestionSearch.newInstance();
        suggestionSearch.setOnGetSuggestionResultListener(onGetSuggestionResultListener);
        suggestionSearch.requestSuggestion(new SuggestionSearchOption().city(str).keyword(str2).citylimit(Boolean.valueOf(z)));
    }

    public static void suggestionSearchStop() {
        SuggestionSearch suggestionSearch2 = suggestionSearch;
        if (suggestionSearch2 != null) {
            suggestionSearch2.destroy();
        }
    }
}
