package com.sinovatech.unicom.separatemodule.webrtc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.server.UnicomCookieManager;
import com.sinovatech.unicom.basic.webview.PBWebView;
import com.sinovatech.unicom.basic.webview.WebViewStatusListener;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RtcWebFragment extends BaseWebFragment {
    public NBSTraceUnit _nbs_trace;
    private String entranceURL;
    private PBWebView pbWebView;

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment, com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onPause() {
        NBSFragmentSession.getInstance().fragmentSessionPause(getClass().getName(), this);
        super.onPause();
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment, com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onResume() {
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.webrtc.RtcWebFragment");
        super.onResume();
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.webrtc.RtcWebFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.separatemodule.webrtc.RtcWebFragment", this);
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.webrtc.RtcWebFragment");
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        super.setUserVisibleHint(z);
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment, com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.entranceURL = TextUtils.isEmpty(getArguments().getString("entranceURL")) ? "" : getArguments().getString("entranceURL");
        }
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    @Override // android.support.p083v4.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.webrtc.RtcWebFragment", viewGroup);
        View inflate = layoutInflater.inflate(2131493313, viewGroup, false);
        this.pbWebView = (PBWebView) inflate.findViewById(2131298190);
        registerJSPluginService(this.pbWebView);
        UnicomCookieManager.addLoginCookie();
        this.pbWebView.loadURL(this.entranceURL);
        this.pbWebView.setStatusListener(new WebViewStatusListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.RtcWebFragment.1
        });
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.webrtc.RtcWebFragment");
        return inflate;
    }

    public static RtcWebFragment newInstance(String str) {
        RtcWebFragment rtcWebFragment = new RtcWebFragment();
        Bundle bundle = new Bundle();
        bundle.putString("entranceURL", str);
        rtcWebFragment.setArguments(bundle);
        return rtcWebFragment;
    }
}
