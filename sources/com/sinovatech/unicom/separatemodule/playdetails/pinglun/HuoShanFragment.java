package com.sinovatech.unicom.separatemodule.playdetails.pinglun;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.p083v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.networkbench.agent.impl.instrumentation.NBSWebViewClient;
import com.sinovatech.unicom.basic.webview.PBWebView;
import com.sinovatech.unicom.common.UIUtils;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HuoShanFragment extends Fragment {
    public NBSTraceUnit _nbs_trace;
    private View fragmentCacheView;
    private PBWebView mWv;
    private String url;

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
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.HuoShanFragment");
        Tracker.onResume(this);
        super.onResume();
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.HuoShanFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.HuoShanFragment", this);
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.HuoShanFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
    }

    @Override // android.support.p083v4.app.Fragment
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.url = getArguments().getString("comment_url");
            UIUtils.logD("火山评论", "onCreate: " + this.url);
        }
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    @Override // android.support.p083v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.HuoShanFragment", viewGroup);
        View view = this.fragmentCacheView;
        if (view != null) {
            ViewGroup viewGroup2 = (ViewGroup) view.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.fragmentCacheView);
            }
            View view2 = this.fragmentCacheView;
            NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.HuoShanFragment");
            return view2;
        }
        View inflate = layoutInflater.inflate(2131493124, viewGroup, false);
        initView(inflate);
        this.fragmentCacheView = inflate;
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.playdetails.pinglun.HuoShanFragment");
        return inflate;
    }

    private void initView(View view) {
        this.mWv = (PBWebView) view.findViewById(2131297240);
        PBWebView pBWebView = this.mWv;
        String str = this.url;
        if (pBWebView instanceof Object) {
            NBSWebLoadInstrument.loadUrl((Object) pBWebView, str);
        } else {
            pBWebView.loadUrl(str);
        }
        this.mWv.getSettings().setCacheMode(2);
        this.mWv.getSettings().setSupportZoom(false);
        this.mWv.getSettings().setBuiltInZoomControls(false);
        this.mWv.getSettings().setDisplayZoomControls(true);
        this.mWv.setWebViewClient(new NBSWebViewClient() { // from class: com.sinovatech.unicom.separatemodule.playdetails.pinglun.HuoShanFragment.1
            @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
            public void onPageStarted(WebView webView, String str2, Bitmap bitmap) {
                Tracker.onPageStarted(this, webView, str2, bitmap);
                super.onPageStarted(webView, str2, bitmap);
                UIUtils.logD("火山评论", "开始加载: " + str2);
            }

            @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str2) {
                Tracker.onPageFinished(this, webView, str2);
                super.onPageFinished(webView, str2);
                UIUtils.logD("火山评论", "结束: " + str2);
            }

            @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
            public void onReceivedError(WebView webView, int i, String str2, String str3) {
                super.onReceivedError(webView, i, str2, str3);
                UIUtils.logD("火山评论", "错误: " + i);
            }
        });
    }

    @Override // android.support.p083v4.app.Fragment
    public void onDestroy() {
        PBWebView pBWebView = this.mWv;
        if (pBWebView != null) {
            if (pBWebView instanceof Object) {
                NBSWebLoadInstrument.loadDataWithBaseURL((Object) pBWebView, (String) null, "", "text/html", "utf-8", (String) null);
            } else {
                pBWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            }
            this.mWv.clearHistory();
            ((ViewGroup) this.mWv.getParent()).removeView(this.mWv);
            this.mWv.destroy();
            this.mWv = null;
        }
        super.onDestroy();
    }
}
