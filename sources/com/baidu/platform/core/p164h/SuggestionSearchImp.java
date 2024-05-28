package com.baidu.platform.core.p164h;

import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.baidu.platform.base.BaseSearch;
import com.baidu.platform.base.SearchType;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.h.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SuggestionSearchImp extends BaseSearch implements ISuggestionSearch {

    /* renamed from: b */
    private OnGetSuggestionResultListener f8153b = null;

    @Override // com.baidu.platform.core.p164h.ISuggestionSearch
    /* renamed from: a */
    public boolean mo17492a(SuggestionSearchOption suggestionSearchOption) {
        SuggestionSearchParser suggestionSearchParser = new SuggestionSearchParser();
        suggestionSearchParser.m18091a(SearchType.SUGGESTION_SEARCH_TYPE);
        return m18098a(new SuggestionSearchRequest(suggestionSearchOption), this.f8153b, suggestionSearchParser);
    }

    @Override // com.baidu.platform.core.p164h.ISuggestionSearch
    /* renamed from: a */
    public void mo17493a(OnGetSuggestionResultListener onGetSuggestionResultListener) {
        this.f7494a.lock();
        this.f8153b = onGetSuggestionResultListener;
        this.f7494a.unlock();
    }

    @Override // com.baidu.platform.core.p164h.ISuggestionSearch
    /* renamed from: a */
    public void mo17494a() {
        this.f7494a.lock();
        this.f8153b = null;
        this.f7494a.unlock();
    }
}
