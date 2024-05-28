package com.sinovatech.unicom.separatemodule.quanxianshuoming.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.p315ui.JSInvokeHandler;
import com.sinovatech.unicom.basic.webview.PBWebView;
import com.sinovatech.unicom.basic.webview.WebViewStatusListener;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LeftFragment extends BaseWebFragment {
    public NBSTraceUnit _nbs_trace;
    private PBWebView pbWebView;

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment, com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment, com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onPause() {
        NBSFragmentSession.getInstance().fragmentSessionPause(getClass().getName(), this);
        super.onPause();
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment, com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onResume() {
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.quanxianshuoming.fragment.LeftFragment");
        super.onResume();
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.quanxianshuoming.fragment.LeftFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.separatemodule.quanxianshuoming.fragment.LeftFragment", this);
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.quanxianshuoming.fragment.LeftFragment");
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        super.setUserVisibleHint(z);
    }

    @Override // android.support.p083v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.quanxianshuoming.fragment.LeftFragment", viewGroup);
        View inflate = layoutInflater.inflate(2131493314, viewGroup, false);
        this.pbWebView = (PBWebView) inflate.findViewById(2131297592);
        registerJSPluginService(this.pbWebView);
        try {
            this.pbWebView.requestFocus();
            this.pbWebView.setBackgroundColor(0);
            this.pbWebView.getSettings().setCacheMode(-1);
            this.pbWebView.addJavascriptInterface(new JSInvokeHandler((AppCompatActivity) getContext(), this.pbWebView, new Handler()), "js_invoke");
            this.pbWebView.setStatusListener(new WebViewStatusListener() { // from class: com.sinovatech.unicom.separatemodule.quanxianshuoming.fragment.LeftFragment.1
                @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
                public boolean onShouldOverrideUrlLoading(String str) {
                    return false;
                }

                @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
                public boolean onReceivedTitle(String str) {
                    if (TextUtils.isEmpty(str)) {
                        return true;
                    }
                    if (str.contains("受理结果") || str.contains("405 Not Allowed") || str.contains("找不到网页")) {
                        LeftFragment.this.pbWebView.destroy();
                        LeftFragment.this.pbWebView.setVisibility(8);
                        return true;
                    }
                    return true;
                }
            });
            this.pbWebView.loadURL("https://img.client.10010.com/stprototype/quanxian/xitong.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.quanxianshuoming.fragment.LeftFragment");
        return inflate;
    }

    public PBWebView getPbWebView() {
        return this.pbWebView;
    }
}
