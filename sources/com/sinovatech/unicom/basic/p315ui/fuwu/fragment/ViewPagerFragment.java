package com.sinovatech.unicom.basic.p315ui.fuwu.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.p083v4.app.Fragment;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.basic.p315ui.fuwu.adapter.SearchGridAdapter;
import com.sinovatech.unicom.basic.p315ui.fuwu.utils.FuWuTopVerticalSpacingItemDecoration;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.fuwu.fragment.ViewPagerFragment */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ViewPagerFragment extends Fragment {
    public NBSTraceUnit _nbs_trace;
    private Activity activityContext;
    private List<MenuEntity> fuWuItemBeanList;
    private RecyclerView rl_search;
    private SearchGridAdapter searchAdapter;

    @Override // android.support.p083v4.app.Fragment
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    @Override // android.support.p083v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        Tracker.onHiddenChanged(this, z);
        super.onHiddenChanged(z);
    }

    @Override // android.support.p083v4.app.Fragment
    public void onPause() {
        NBSFragmentSession.getInstance().fragmentSessionPause(getClass().getName(), this);
        Tracker.onPause(this);
        super.onPause();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onResume() {
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.sinovatech.unicom.basic.ui.fuwu.fragment.ViewPagerFragment");
        Tracker.onResume(this);
        super.onResume();
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.sinovatech.unicom.basic.ui.fuwu.fragment.ViewPagerFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.basic.ui.fuwu.fragment.ViewPagerFragment", this);
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.basic.ui.fuwu.fragment.ViewPagerFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
    }

    public ViewPagerFragment newInstance(Activity activity, List<MenuEntity> list) {
        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
        viewPagerFragment.activityContext = activity;
        viewPagerFragment.fuWuItemBeanList = new ArrayList(list);
        MsLogUtil.m7979d("服务页面", "服务页面的newInstance = " + list.size());
        return viewPagerFragment;
    }

    @Override // android.support.p083v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.basic.ui.fuwu.fragment.ViewPagerFragment", viewGroup);
        View inflate = layoutInflater.inflate(2131493132, viewGroup, false);
        this.rl_search = (RecyclerView) inflate.findViewById(2131298382);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.activityContext, 5);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.sinovatech.unicom.basic.ui.fuwu.fragment.ViewPagerFragment.1
            @Override // android.support.p086v7.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i) {
                return 1;
            }
        });
        this.rl_search.setLayoutManager(gridLayoutManager);
        this.rl_search.addItemDecoration(new FuWuTopVerticalSpacingItemDecoration(5, getResources().getDimensionPixelSize(2131166079), true, getResources().getDimensionPixelSize(2131166075)));
        this.searchAdapter = new SearchGridAdapter(this.activityContext, new ArrayList(this.fuWuItemBeanList));
        this.rl_search.setAdapter(this.searchAdapter);
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.basic.ui.fuwu.fragment.ViewPagerFragment");
        return inflate;
    }
}
