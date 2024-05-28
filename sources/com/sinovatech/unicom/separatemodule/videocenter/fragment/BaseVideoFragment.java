package com.sinovatech.unicom.separatemodule.videocenter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment;
import com.sinovatech.unicom.common.UIUtils;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class BaseVideoFragment extends BaseFragment {
    public NBSTraceUnit _nbs_trace;
    protected boolean isInit = false;
    protected boolean isLoad = false;
    private View view;

    protected abstract void initView();

    protected abstract void lazyLoad();

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onPause() {
        NBSFragmentSession.getInstance().fragmentSessionPause(getClass().getName(), this);
        super.onPause();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.separatemodule.videocenter.fragment.BaseVideoFragment", this);
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.videocenter.fragment.BaseVideoFragment");
    }

    protected abstract int setContentView();

    protected void stopLoad() {
    }

    @Override // android.support.p083v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.videocenter.fragment.BaseVideoFragment", viewGroup);
        this.view = layoutInflater.inflate(setContentView(), viewGroup, false);
        this.isInit = true;
        initView();
        isCanLoadData();
        View view = this.view;
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.videocenter.fragment.BaseVideoFragment");
        return view;
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        super.setUserVisibleHint(z);
        isCanLoadData();
    }

    private void isCanLoadData() {
        if (this.isInit) {
            if (getUserVisibleHint()) {
                lazyLoad();
                this.isLoad = true;
            } else if (this.isLoad) {
                stopLoad();
            }
        }
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onResume() {
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.videocenter.fragment.BaseVideoFragment");
        super.onResume();
        isCanLoadData();
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.videocenter.fragment.BaseVideoFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onDestroyView() {
        this.isInit = false;
        this.isLoad = false;
        super.onDestroyView();
    }

    protected void showToast(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        UIUtils.toast(str);
    }

    protected View getContentView() {
        return this.view;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T extends View> T findViewById(int i) {
        return (T) getContentView().findViewById(i);
    }
}
