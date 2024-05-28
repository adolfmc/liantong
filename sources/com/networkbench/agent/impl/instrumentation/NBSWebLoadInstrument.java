package com.networkbench.agent.impl.instrumentation;

import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.networkbench.agent.impl.harvest.p261b.C6459b;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p268n.C6520f;
import com.networkbench.agent.impl.util.C6638h;
import java.lang.reflect.Method;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSWebLoadInstrument {

    /* renamed from: a */
    protected static InterfaceC6393e f16382a = C6394f.m10150a();
    public static int WEBVIEW_TAG = 201609280;

    public static void setWebViewClient(WebView webView, WebViewClient webViewClient) {
        try {
        } catch (Exception e) {
            C6638h.f17124y.mo10121a("set user agent failed:", e);
        }
        if (Build.VERSION.SDK_INT < 19) {
            webView.setWebViewClient(webViewClient);
            return;
        }
        if (C6459b.m9934f()) {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.addJavascriptInterface(new NBSJavaScriptBridge(), "nbsJsBridge");
            webView.setTag(WEBVIEW_TAG, Integer.valueOf(WEBVIEW_TAG));
            C6638h.f17124y.mo10122a("add nbsJsBridge success");
        }
        if (C6459b.m9934f() && C6638h.m8963w().m9036aj() && C6638h.m8963w().m8970q()) {
            String userAgentString = webView.getSettings().getUserAgentString();
            if (!TextUtils.isEmpty(userAgentString) && !userAgentString.contains("X-Tingyun-Id")) {
                WebSettings settings = webView.getSettings();
                settings.setUserAgentString(userAgentString + " X-Tingyun-Id/" + crocessHeaderStr());
            }
        }
        webView.setWebViewClient(webViewClient);
    }

    private static String crocessHeaderStr() {
        if (C6638h.m8963w().m9036aj()) {
            String m9050a = C6638h.m9050a(C6638h.m8963w().m9034al(), C6638h.m9031ao());
            return !TextUtils.isEmpty(m9050a) ? m9050a : "";
        }
        return "";
    }

    public static void loadUrl(WebView webView, String str) {
        if (C6638h.m8963w().m8993j()) {
            try {
                if (C6459b.m9934f()) {
                    if (webView.getTag(WEBVIEW_TAG) == null) {
                        webView.addJavascriptInterface(new NBSJavaScriptBridge(), "nbsJsBridge");
                        webView.setTag(WEBVIEW_TAG, Integer.valueOf(WEBVIEW_TAG));
                    }
                    WebChromeClient defaultWebChromeClient = NBSWebChromeClient.getDefaultWebChromeClient(webView);
                    if (defaultWebChromeClient instanceof C6520f) {
                        C6638h.f17124y.mo10122a(" nbs webchromeclient already existed  ");
                    } else {
                        webView.setWebChromeClient(new C6520f(defaultWebChromeClient));
                        C6638h.f17124y.mo10122a("set nbs webchromeclient success");
                    }
                }
            } catch (Throwable th) {
                C6638h.f17124y.mo10121a("set nbs webchromeclient failed:", th);
            }
        }
        webView.loadUrl(str);
    }

    public static void loadUrl(WebView webView, String str, Map<String, String> map) {
        if (C6638h.m8963w().m8993j()) {
            try {
                if (C6459b.m9934f()) {
                    if (webView.getTag(WEBVIEW_TAG) == null) {
                        webView.addJavascriptInterface(new NBSJavaScriptBridge(), "nbsJsBridge");
                        webView.setTag(WEBVIEW_TAG, Integer.valueOf(WEBVIEW_TAG));
                    }
                    WebChromeClient defaultWebChromeClient = NBSWebChromeClient.getDefaultWebChromeClient(webView);
                    if (defaultWebChromeClient instanceof C6520f) {
                        C6638h.f17124y.mo10122a(" nbs webchromeclient already existed  ");
                    } else {
                        webView.setWebChromeClient(new C6520f(defaultWebChromeClient));
                        C6638h.f17124y.mo10122a("set nbs webchromeclient success");
                    }
                }
            } catch (Throwable th) {
                C6638h.f17124y.mo10121a("set nbs webchromeclient failed:", th);
            }
        }
        webView.loadUrl(str, map);
    }

    public static void loadUrl(Object obj, String str) {
        if (obj instanceof WebView) {
            C6638h.f17124y.mo10122a("webViewProxy instanceof WebView");
            loadUrl((WebView) obj, str);
            return;
        }
        C6638h.f17124y.mo10122a("webViewProxy not instanceof WebView or x5webview");
        try {
            Method findMethod = findMethod(obj, "loadUrl", String.class);
            findMethod.setAccessible(true);
            findMethod.invoke(obj, str);
        } catch (Throwable th) {
            C6638h.f17124y.mo10121a("webViewProxy loadUrl error", th);
        }
    }

    public static void loadUrl(Object obj, String str, Map<String, String> map) {
        if (obj instanceof WebView) {
            C6638h.f17124y.mo10122a("webViewProxy instanceof WebView");
            loadUrl((WebView) obj, str, map);
            return;
        }
        C6638h.f17124y.mo10122a("webViewProxy not instanceof WebView or x5webview");
        try {
            Method findMethod = findMethod(obj, "loadUrl", String.class, Map.class);
            findMethod.setAccessible(true);
            findMethod.invoke(obj, str, map);
        } catch (Throwable th) {
            C6638h.f17124y.mo10121a("webViewProxy loadUrl map error", th);
        }
    }

    public static void loadDataWithBaseURL(Object obj, String str, String str2, String str3, String str4, String str5) {
        if (obj instanceof WebView) {
            C6638h.f17124y.mo10122a("webViewProxy instanceof WebView");
            loadDataWithBaseURL((WebView) obj, str, str2, str3, str4, str5);
            return;
        }
        C6638h.f17124y.mo10122a("webViewProxy not instanceof WebView or x5webview");
        try {
            Method findMethod = findMethod(obj, "loadDataWithBaseURL", String.class, String.class, String.class, String.class, String.class);
            findMethod.setAccessible(true);
            findMethod.invoke(obj, str, str2, str3, str4, str5);
        } catch (Throwable th) {
            C6638h.f17124y.mo10121a("webViewProxy loadUrl map error", th);
        }
    }

    public static void loadDataWithBaseURL(WebView webView, String str, String str2, String str3, String str4, String str5) {
        if (C6638h.m8963w().m8993j()) {
            try {
                if (C6459b.m9934f()) {
                    if (webView.getTag(WEBVIEW_TAG) == null) {
                        webView.addJavascriptInterface(new NBSJavaScriptBridge(), "nbsJsBridge");
                        webView.setTag(WEBVIEW_TAG, Integer.valueOf(WEBVIEW_TAG));
                    }
                    WebChromeClient defaultWebChromeClient = NBSWebChromeClient.getDefaultWebChromeClient(webView);
                    if (defaultWebChromeClient instanceof C6520f) {
                        C6638h.f17124y.mo10122a(" nbs webchromeclient already existed  ");
                    } else {
                        webView.setWebChromeClient(new C6520f(defaultWebChromeClient));
                        C6638h.f17124y.mo10122a("set nbs webchromeclient success");
                    }
                }
            } catch (Throwable th) {
                C6638h.f17124y.mo10121a("set nbs webchromeclient failed:", th);
            }
        }
        webView.loadDataWithBaseURL(str, str2, str3, str4, str5);
    }

    private static Method findMethod(Object obj, String str, Class<?>... clsArr) {
        Method method = null;
        for (Class<?> cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            try {
                method = cls.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException e) {
                InterfaceC6393e interfaceC6393e = C6638h.f17124y;
                interfaceC6393e.mo10122a("NoSuchMethodException:" + e.getMessage());
            }
            if (method != null) {
                return method;
            }
        }
        return method;
    }
}
