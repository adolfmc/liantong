package com.baidu.platform.core.p161e;

import com.baidu.mapapi.search.recommendstop.OnGetRecommendStopResultListener;
import com.baidu.mapapi.search.recommendstop.RecommendStopSearchOption;
import com.baidu.platform.base.BaseSearch;
import com.baidu.platform.base.SearchType;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.core.e.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RecommendStopSearchImp extends BaseSearch implements IRecommendStop {

    /* renamed from: b */
    private OnGetRecommendStopResultListener f8145b = null;

    @Override // com.baidu.platform.core.p161e.IRecommendStop
    /* renamed from: a */
    public boolean mo17567a(RecommendStopSearchOption recommendStopSearchOption) {
        RecommendStopSearchParser recommendStopSearchParser = new RecommendStopSearchParser();
        recommendStopSearchParser.m18091a(SearchType.RECOMMEND_STOP);
        return m18098a(new RecommendStopSearchRequest(recommendStopSearchOption), this.f8145b, recommendStopSearchParser);
    }

    @Override // com.baidu.platform.core.p161e.IRecommendStop
    /* renamed from: a */
    public void mo17568a(OnGetRecommendStopResultListener onGetRecommendStopResultListener) {
        this.f7494a.lock();
        this.f8145b = onGetRecommendStopResultListener;
        this.f7494a.unlock();
    }

    @Override // com.baidu.platform.core.p161e.IRecommendStop
    /* renamed from: a */
    public void mo17569a() {
        this.f7494a.lock();
        this.f8145b = null;
        this.f7494a.unlock();
    }
}
