package com.sinovatech.unicom.separatemodule.playdetails.pinglun;

import android.os.Bundle;
import android.support.p083v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaseFragment extends Fragment {
    public NBSTraceUnit _nbs_trace;
    protected boolean isVisible;
    public String mPageTab = "";
    public String mPageTag = "";
    public String mPrePageTab = "";
    public String mPrePageTag = "";
    public boolean mUseLifeTime = true;
    private boolean isShow = false;

    protected int getContentResId() {
        return 0;
    }

    protected View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    protected void onApplyData() {
    }

    protected void onBindListener() {
    }

    protected void onFindView(View view) {
    }

    @Override // android.support.p083v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        Tracker.onHiddenChanged(this, z);
        super.onHiddenChanged(z);
    }

    protected void onQueryArguments() {
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.BaseFragment", this);
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.BaseFragment");
    }

    public boolean isShow() {
        return this.isShow;
    }

    @Override // android.support.p083v4.app.Fragment
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        this.mPageTab = getClass().getSimpleName();
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    @Override // android.support.p083v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.BaseFragment", viewGroup);
        int contentResId = getContentResId();
        if (contentResId != 0) {
            View inflate = layoutInflater.inflate(contentResId, viewGroup, false);
            NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.BaseFragment");
            return inflate;
        }
        View contentView = getContentView(layoutInflater, viewGroup, bundle);
        if (contentView == null) {
            IllegalStateException illegalStateException = new IllegalStateException("you should override getContentResId or getContentView method");
            NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.BaseFragment");
            throw illegalStateException;
        }
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.BaseFragment");
        return contentView;
    }

    @Override // android.support.p083v4.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        initContentView();
    }

    @Override // android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
        if (getUserVisibleHint()) {
            this.isVisible = true;
        } else {
            this.isVisible = false;
        }
    }

    private void initContentView() {
        onQueryArguments();
        onFindView(getView());
        onBindListener();
        onApplyData();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onResume() {
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.BaseFragment");
        Tracker.onResume(this);
        super.onResume();
        this.isShow = true;
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.BaseFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onPause() {
        NBSFragmentSession.getInstance().fragmentSessionPause(getClass().getName(), this);
        Tracker.onPause(this);
        super.onPause();
        this.isShow = false;
    }
}
