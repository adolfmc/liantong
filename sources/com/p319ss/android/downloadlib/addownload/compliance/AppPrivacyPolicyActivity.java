package com.p319ss.android.downloadlib.addownload.compliance;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import com.bytedance.applog.tracker.Tracker;
import com.bytedance.sdk.openadsdk.C3958R;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.networkbench.agent.impl.instrumentation.NBSWebViewClient;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.addownload.model.C9922ox;
import com.p319ss.android.downloadlib.utils.C10070x;
import com.p319ss.android.socialbase.appdownloader.C10085b;

@NBSInstrumented
/* renamed from: com.ss.android.downloadlib.addownload.compliance.AppPrivacyPolicyActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AppPrivacyPolicyActivity extends Activity {
    public NBSTraceUnit _nbs_trace;

    /* renamed from: b */
    private long f18935b;

    /* renamed from: h */
    private String f18936h;

    /* renamed from: hj */
    private long f18937hj;

    /* renamed from: mb */
    private ImageView f18938mb;

    /* renamed from: ox */
    private WebView f18939ox;

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        setContentView(C3958R.C3963layout.ttdownloader_activity_app_privacy_policy);
        if (m7683mb()) {
            m7679ox();
        } else {
            C10085b.m6920mb((Activity) this);
        }
        NBSAppInstrumentation.activityCreateEndIns();
    }

    /* renamed from: mb */
    public static void m7682mb(Activity activity, long j) {
        Intent intent = new Intent(activity, AppPrivacyPolicyActivity.class);
        intent.putExtra("app_info_id", j);
        activity.startActivity(intent);
    }

    /* renamed from: mb */
    private boolean m7683mb() {
        this.f18935b = getIntent().getLongExtra("app_info_id", 0L);
        C9922ox m7676mb = C9857b.m7677mb().m7676mb(this.f18935b);
        if (m7676mb == null) {
            return false;
        }
        this.f18937hj = m7676mb.f19128ox;
        this.f18936h = m7676mb.f19126lz;
        if (TextUtils.isEmpty(this.f18936h)) {
            this.f18936h = C9940x.m7364lz().optString("ad_privacy_backup_url", "https://sf6-ttcdn-tos.pstatp.com/obj/ad-tetris-site/personal-privacy-page.html");
            return true;
        }
        return true;
    }

    /* renamed from: ox */
    private void m7679ox() {
        this.f18938mb = (ImageView) findViewById(C3958R.C3961id.iv_privacy_back);
        this.f18939ox = (WebView) findViewById(C3958R.C3961id.privacy_webview);
        this.f18938mb.setOnClickListener(new View.OnClickListener() { // from class: com.ss.android.downloadlib.addownload.compliance.AppPrivacyPolicyActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                C9860h.m7670mb("lp_app_privacy_click_close", AppPrivacyPolicyActivity.this.f18937hj);
                AppPrivacyPolicyActivity.this.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        WebSettings settings = this.f18939ox.getSettings();
        settings.setDefaultFontSize(16);
        settings.setCacheMode(-1);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setSavePassword(false);
        settings.setAllowFileAccess(false);
        WebView webView = this.f18939ox;
        NBSWebViewClient nBSWebViewClient = new NBSWebViewClient() { // from class: com.ss.android.downloadlib.addownload.compliance.AppPrivacyPolicyActivity.2
            @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
            public void onPageFinished(WebView webView2, String str) {
                Tracker.onPageFinished(this, webView2, str);
                super.onPageFinished(webView2, str);
            }

            @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
            public void onPageStarted(WebView webView2, String str, Bitmap bitmap) {
                Tracker.onPageStarted(this, webView2, str, bitmap);
                super.onPageStarted(webView2, str, bitmap);
            }

            @Override // android.webkit.WebViewClient
            @TargetApi(21)
            public boolean shouldOverrideUrlLoading(WebView webView2, WebResourceRequest webResourceRequest) {
                return m7678mb(webResourceRequest.getUrl());
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, String str) {
                return m7678mb(Uri.parse(str));
            }

            @Override // android.webkit.WebViewClient
            public boolean onRenderProcessGone(WebView webView2, RenderProcessGoneDetail renderProcessGoneDetail) {
                if (Build.VERSION.SDK_INT < 26) {
                    return super.onRenderProcessGone(webView2, renderProcessGoneDetail);
                }
                if (!renderProcessGoneDetail.didCrash()) {
                    C10070x.m6969mb("System killed the WebView rendering process to reclaim memory. Recreating...");
                    if (webView2 != null) {
                        ((ViewGroup) webView2.getParent()).removeView(webView2);
                        webView2.destroy();
                    }
                    return true;
                }
                C10070x.m6969mb("The WebView rendering process crashed!");
                if (webView2 != null) {
                    ((ViewGroup) webView2.getParent()).removeView(webView2);
                    webView2.destroy();
                }
                return true;
            }

            /* renamed from: mb */
            private boolean m7678mb(Uri uri) {
                String scheme = uri.getScheme();
                return ("http".equals(scheme) || "https".equals(scheme)) ? false : true;
            }
        };
        if (webView instanceof WebView) {
            NBSWebLoadInstrument.setWebViewClient(webView, nBSWebViewClient);
        } else {
            webView.setWebViewClient(nBSWebViewClient);
        }
        m7681mb(this.f18939ox);
        this.f18939ox.setScrollBarStyle(0);
        WebView webView2 = this.f18939ox;
        String str = this.f18936h;
        if (webView2 instanceof Object) {
            NBSWebLoadInstrument.loadUrl((Object) webView2, str);
        } else {
            webView2.loadUrl(str);
        }
    }

    /* renamed from: mb */
    private void m7681mb(WebView webView) {
        try {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        C9860h.m7670mb("lp_app_privacy_click_close", this.f18937hj);
        super.onBackPressed();
    }
}
