package com.networkbench.agent.impl.instrumentation;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;
import com.networkbench.agent.impl.harvest.p261b.C6459b;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p268n.C6516c;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6642k;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebView;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSWebChromeX5Client {
    private static int injectMax = 0;
    public static int progressControl = 15;

    public static void initJSMonitorX5(WebView webView, int i) {
        if (Build.VERSION.SDK_INT >= 19 && i >= progressControl) {
            try {
                if (C6459b.m9934f() && !C6642k.m8903e(webView.getUrl())) {
                    C6396h.m10130l("webview  initJSMonitor gather  begin !!");
                    if (webView.getSettings().getJavaScriptEnabled()) {
                        C6396h.m10138d("javascript has enable!");
                    } else {
                        webView.getSettings().setJavaScriptEnabled(true);
                    }
                    if (injectMax < 10) {
                        injectScriptFile(webView);
                        injectMax++;
                    }
                    if (i >= 100) {
                        injectMax = 0;
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    @TargetApi(19)
    private static void injectScriptFile(WebView webView) {
        try {
            C6396h.m10138d("injectScriptFile begin");
            if (TextUtils.isEmpty(C6638h.m8963w().m8964v())) {
                webView.evaluateJavascript("javascript:(function() {var parent = document.getElementsByTagName('head').item(0);var script = document.createElement('script');script.type = 'text/javascript';script.innerHTML = " + getUrlRealAddress() + C6516c.f16608b + ";if(!parent) return;parent.appendChild(script)})()", new ValueCallback<String>() { // from class: com.networkbench.agent.impl.instrumentation.NBSWebChromeX5Client.1
                    /* renamed from: a */
                    public void onReceiveValue(String str) {
                    }
                });
            } else {
                webView.evaluateJavascript("javascript:(function() {var parent = document.getElementsByTagName('head').item(0);var script = document.createElement('script');script.type = 'text/javascript';script.innerHTML = " + getUrlRealAddress() + C6516c.f16608b + getUserIdString() + ";if(!parent) return;parent.appendChild(script)})()", new ValueCallback<String>() { // from class: com.networkbench.agent.impl.instrumentation.NBSWebChromeX5Client.2
                    /* renamed from: a */
                    public void onReceiveValue(String str) {
                    }
                });
            }
        } catch (Exception e) {
            C6396h.m10138d(e.getMessage());
        }
    }

    public static String getUserIdString() {
        try {
            return String.format("window.localStorage.setItem('TY_USER_ID', '%s');", C6638h.m8963w().m8964v());
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getUrlRealAddress() {
        try {
            String m8997i = C6638h.m8963w().m8997i();
            if (TextUtils.isEmpty(m8997i)) {
                return "";
            }
            return "(function(window){" + String.format("window.TY_REAL_ADDRESS = '%s'", m8997i) + "})(window);";
        } catch (Exception unused) {
            return "";
        }
    }

    public static void addWebViewBridge(WebView webView) {
        if (C6459b.m9934f() && Build.VERSION.SDK_INT >= 19) {
            webView.addJavascriptInterface(new NBSJavaScriptBridge(), "nbsJsBridge");
        }
    }
}
