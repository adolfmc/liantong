package com.baidu.mapapi.search.recommendstop;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.search.core.SearcherInternal;
import com.baidu.platform.core.p161e.IRecommendStop;
import com.baidu.platform.core.p161e.RecommendStopSearchImp;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RecommendStopSearch extends SearcherInternal {

    /* renamed from: b */
    private boolean f6796b = false;

    /* renamed from: a */
    private IRecommendStop f6795a = new RecommendStopSearchImp();

    private RecommendStopSearch() {
    }

    public static RecommendStopSearch newInstance() {
        BMapManager.init();
        return new RecommendStopSearch();
    }

    public boolean requestRecommendStop(RecommendStopSearchOption recommendStopSearchOption) {
        if (this.f6795a == null) {
            throw new IllegalStateException("BDMapSDKException: RecommendStopSearch is null, please call newInstance() first.");
        }
        if (recommendStopSearchOption == null || recommendStopSearchOption.getLocation() == null) {
            throw new IllegalStateException("BDMapSDKException: option or location can not be null");
        }
        return this.f6795a.mo17567a(recommendStopSearchOption);
    }

    public void setOnGetRecommendStopResultListener(OnGetRecommendStopResultListener onGetRecommendStopResultListener) {
        IRecommendStop iRecommendStop = this.f6795a;
        if (iRecommendStop == null) {
            throw new IllegalStateException("BDMapSDKException: RecommendStopSearch is null, please call newInstance() first.");
        }
        if (onGetRecommendStopResultListener == null) {
            throw new IllegalStateException("BDMapSDKException: OnGetRecommendStopResultListener can not be null");
        }
        iRecommendStop.mo17568a(onGetRecommendStopResultListener);
    }

    public void destroy() {
        if (this.f6796b) {
            return;
        }
        this.f6796b = true;
        IRecommendStop iRecommendStop = this.f6795a;
        if (iRecommendStop != null) {
            iRecommendStop.mo17569a();
        }
        BMapManager.destroy();
    }
}
