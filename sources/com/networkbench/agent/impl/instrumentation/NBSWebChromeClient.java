package com.networkbench.agent.impl.instrumentation;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.networkbench.agent.impl.harvest.p261b.C6459b;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p268n.C6516c;
import com.networkbench.agent.impl.util.C6632b;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6642k;
import java.lang.reflect.Field;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSWebChromeClient {

    /* renamed from: a */
    protected static InterfaceC6393e f16381a = C6394f.m10150a();
    private static int injectMax = 0;
    public static int progressControl = 15;

    public static void initJSMonitor(WebView webView, int i) {
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
                webView.evaluateJavascript("javascript:(function() {var parent = document.getElementsByTagName('head').item(0);var script = document.createElement('script');script.type = 'text/javascript';script.innerHTML = " + getUrlRealAddress() + C6516c.f16608b + ";if(!parent) return;parent.appendChild(script)})()", new ValueCallback<String>() { // from class: com.networkbench.agent.impl.instrumentation.NBSWebChromeClient.1
                    @Override // android.webkit.ValueCallback
                    /* renamed from: a */
                    public void onReceiveValue(String str) {
                    }
                });
            } else {
                webView.evaluateJavascript("javascript:(function() {var parent = document.getElementsByTagName('head').item(0);var script = document.createElement('script');script.type = 'text/javascript';script.innerHTML = " + getUrlRealAddress() + C6516c.f16608b + getUserIdString() + ";if(!parent) return;parent.appendChild(script)})()", new ValueCallback<String>() { // from class: com.networkbench.agent.impl.instrumentation.NBSWebChromeClient.2
                    @Override // android.webkit.ValueCallback
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

    public static WebChromeClient getDefaultWebChromeClient(WebView webView) {
        if (Build.VERSION.SDK_INT >= 26) {
            return webView.getWebChromeClient();
        }
        return getWebChromeClientReflect(webView);
    }

    public static WebChromeClient getWebChromeClientReflect(WebView webView) {
        Object obj;
        try {
            Field declaredField = WebView.class.getDeclaredField("mProvider");
            declaredField.setAccessible(true);
            Object obj2 = declaredField.get(webView);
            String name = obj2.getClass().getName();
            InterfaceC6393e interfaceC6393e = f16381a;
            interfaceC6393e.mo10122a("mProviderRealClass:" + name);
            Field declaredField2 = obj2.getClass().getDeclaredField("mContentsClientAdapter");
            declaredField2.setAccessible(true);
            Object obj3 = declaredField2.get(obj2);
            Field declaredField3 = obj3.getClass().getDeclaredField("mWebChromeClient");
            declaredField3.setAccessible(true);
            obj = declaredField3.get(obj3);
        } catch (C6632b unused) {
            throw new RuntimeException("webchromeclient type is error");
        } catch (Exception e) {
            f16381a.mo10121a("getWebChromeClientReflect error:", e);
        }
        if (obj != null) {
            InterfaceC6393e interfaceC6393e2 = f16381a;
            interfaceC6393e2.mo10122a("mWebChromeClient class name:" + obj.getClass().getName());
            if (obj instanceof WebChromeClient) {
                return (WebChromeClient) obj;
            }
            f16381a.mo10115e("webchromeclient type is error");
            throw new C6632b("webchromeclient type is error");
        }
        f16381a.mo10122a("mWebChromeClient == null");
        f16381a.mo10116d("getWebChromeClientReflect not find webchromeclient");
        return null;
    }
}
