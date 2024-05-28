package com.sinovatech.unicom.separatemodule.quanxianshuoming.fragment;

import android.os.Bundle;
import android.support.p083v4.app.Fragment;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.separatemodule.miniprogram.h5auth.H5AuthManager;
import com.sinovatech.unicom.separatemodule.miniprogram.h5auth.H5AuthRecord;
import com.sinovatech.unicom.separatemodule.quanxianshuoming.adapter.YeWuAdapter;
import com.sinovatech.unicom.separatemodule.quanxianshuoming.utils.Utils;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RightFragment extends Fragment {
    public NBSTraceUnit _nbs_trace;
    private YeWuAdapter adapter;
    private LinearLayout lin_data;
    private LinearLayout lin_no_data;
    private RecyclerView rl_view;
    private List<H5AuthRecord> yeWuModelList = new ArrayList();

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
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.quanxianshuoming.fragment.RightFragment");
        Tracker.onResume(this);
        super.onResume();
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.quanxianshuoming.fragment.RightFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.separatemodule.quanxianshuoming.fragment.RightFragment", this);
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.quanxianshuoming.fragment.RightFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
    }

    @Override // android.support.p083v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.quanxianshuoming.fragment.RightFragment", viewGroup);
        View inflate = layoutInflater.inflate(2131493406, viewGroup, false);
        this.rl_view = (RecyclerView) inflate.findViewById(2131298393);
        this.lin_data = (LinearLayout) inflate.findViewById(2131297603);
        this.lin_no_data = (LinearLayout) inflate.findViewById(2131297610);
        this.rl_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.adapter = new YeWuAdapter(getActivity(), this.yeWuModelList);
        this.rl_view.setAdapter(this.adapter);
        this.lin_no_data.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.quanxianshuoming.fragment.RightFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                RightFragment.this.initData();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        initData();
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.quanxianshuoming.fragment.RightFragment");
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initData() {
        this.yeWuModelList.clear();
        List<H5AuthRecord> h5AuthRecord = H5AuthManager.getInstance(getActivity()).getH5AuthRecord();
        if (h5AuthRecord != null && h5AuthRecord.size() > 0) {
            this.lin_no_data.setVisibility(8);
            this.lin_data.setVisibility(0);
            Log.d("这是获取到的业务数据：", "个数：" + h5AuthRecord.size());
            this.yeWuModelList.addAll(Utils.resultList(h5AuthRecord));
            YeWuAdapter yeWuAdapter = this.adapter;
            if (yeWuAdapter != null) {
                yeWuAdapter.notifyDataSetChanged();
                return;
            }
            return;
        }
        this.lin_no_data.setVisibility(0);
        this.lin_data.setVisibility(8);
    }
}
