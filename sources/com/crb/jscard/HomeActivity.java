package com.crb.jscard;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.Toast;
import com.crb.jscard.p192js.CustomWebviewClient;
import com.crb.jscard.p192js.MyJavascriptInterface;
import com.crb.jscard.p192js.WebViewSettingsInitializer;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class HomeActivity extends BaseActivity {

    /* renamed from: a */
    public static WebView f9727a;

    /* renamed from: b */
    public static Context f9728b;
    public NBSTraceUnit _nbs_trace;

    /* renamed from: a */
    public void m16299a(String str) {
        Toast.makeText(f9728b, str, 0).show();
    }

    /* renamed from: e */
    public void m16298e() {
        SmartCard.m1867c();
        ActManager.m116a();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (f9727a.canGoBack()) {
            f9727a.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.crb.jscard.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        setContentView(C4182R.C4186layout.activity_main_web);
        ActManager.m115a(this);
        f9728b = this;
        f9727a = (WebView) findViewById(C4182R.C4185id.webView);
        WebView createWebView = WebViewSettingsInitializer.getInstance().createWebView(f9727a);
        f9727a = createWebView;
        CustomWebviewClient customWebviewClient = new CustomWebviewClient(f9728b);
        if (createWebView instanceof WebView) {
            NBSWebLoadInstrument.setWebViewClient(createWebView, customWebviewClient);
        } else {
            createWebView.setWebViewClient(customWebviewClient);
        }
        WebView webView = f9727a;
        String str = JSCardConfig.f0a;
        if (webView instanceof Object) {
            NBSWebLoadInstrument.loadUrl((Object) webView, str);
        } else {
            webView.loadUrl(str);
        }
        f9727a.addJavascriptInterface(new MyJavascriptInterface(), "jscard");
        f9727a.setLayerType(1, null);
        NBSAppInstrumentation.activityCreateEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }
}
