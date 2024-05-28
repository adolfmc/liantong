package com.baidu.mapapi.search.sug;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.search.core.SearcherInternal;
import com.baidu.platform.core.p164h.ISuggestionSearch;
import com.baidu.platform.core.p164h.SuggestionSearchImp;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SuggestionSearch extends SearcherInternal {

    /* renamed from: b */
    private boolean f6918b = false;

    /* renamed from: a */
    ISuggestionSearch f6917a = new SuggestionSearchImp();

    private SuggestionSearch() {
    }

    public static SuggestionSearch newInstance() {
        BMapManager.init();
        return new SuggestionSearch();
    }

    public boolean requestSuggestion(SuggestionSearchOption suggestionSearchOption) {
        if (this.f6917a == null) {
            throw new IllegalStateException("BDMapSDKException: suggestionsearch is null, please call newInstance() first.");
        }
        if (suggestionSearchOption == null || suggestionSearchOption.mKeyword == null || suggestionSearchOption.mCity == null) {
            throw new IllegalArgumentException("BDMapSDKException: option or keyword or city can not be null");
        }
        return this.f6917a.mo17492a(suggestionSearchOption);
    }

    public void setOnGetSuggestionResultListener(OnGetSuggestionResultListener onGetSuggestionResultListener) {
        ISuggestionSearch iSuggestionSearch = this.f6917a;
        if (iSuggestionSearch == null) {
            throw new IllegalStateException("BDMapSDKException: suggestionsearch is null, please call newInstance() first.");
        }
        if (onGetSuggestionResultListener == null) {
            throw new IllegalArgumentException("BDMapSDKException: listener can not be null");
        }
        iSuggestionSearch.mo17493a(onGetSuggestionResultListener);
    }

    public void destroy() {
        if (this.f6918b) {
            return;
        }
        this.f6918b = true;
        this.f6917a.mo17494a();
        BMapManager.destroy();
    }
}
