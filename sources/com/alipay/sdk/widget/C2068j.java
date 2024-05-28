package com.alipay.sdk.widget;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.JsPromptResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.alipay.sdk.app.C1998j;
import com.alipay.sdk.app.statistic.C2000a;
import com.alipay.sdk.util.C2052n;
import com.alipay.sdk.widget.WebViewWindow;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.widget.j */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2068j extends AbstractC2065g implements WebViewWindow.InterfaceC2055a, WebViewWindow.InterfaceC2056b, WebViewWindow.InterfaceC2057c {

    /* renamed from: b */
    public static final String f3944b = "alipayjsbridge://";

    /* renamed from: c */
    public static final String f3945c = "onBack";

    /* renamed from: d */
    public static final String f3946d = "setTitle";

    /* renamed from: e */
    public static final String f3947e = "onRefresh";

    /* renamed from: f */
    public static final String f3948f = "showBackButton";

    /* renamed from: g */
    public static final String f3949g = "onExit";

    /* renamed from: h */
    public static final String f3950h = "onLoadJs";

    /* renamed from: i */
    public static final String f3951i = "callNativeFunc";

    /* renamed from: j */
    public static final String f3952j = "back";

    /* renamed from: k */
    public static final String f3953k = "title";

    /* renamed from: l */
    public static final String f3954l = "refresh";

    /* renamed from: m */
    public static final String f3955m = "backButton";

    /* renamed from: n */
    public static final String f3956n = "refreshButton";

    /* renamed from: o */
    public static final String f3957o = "exit";

    /* renamed from: p */
    public static final String f3958p = "action";

    /* renamed from: q */
    public static final String f3959q = "pushWindow";

    /* renamed from: r */
    public static final String f3960r = "h5JsFuncCallback";

    /* renamed from: s */
    private static final String f3961s = "sdk_result_code:";

    /* renamed from: t */
    private boolean f3962t;

    /* renamed from: u */
    private String f3963u;

    /* renamed from: v */
    private boolean f3964v;

    /* renamed from: w */
    private boolean f3965w;

    /* renamed from: x */
    private WebViewWindow f3966x;

    /* renamed from: y */
    private C2080u f3967y;

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f3964v) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public C2068j(Activity activity) {
        super(activity);
        this.f3962t = true;
        this.f3963u = "GET";
        this.f3964v = false;
        this.f3966x = null;
        this.f3967y = new C2080u();
        m20587c();
    }

    /* renamed from: c */
    private boolean m20587c() {
        try {
            this.f3966x = new WebViewWindow(this.f3940a);
            this.f3966x.setChromeProxy(this);
            this.f3966x.setWebClientProxy(this);
            this.f3966x.setWebEventProxy(this);
            addView(this.f3966x);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: d */
    private void m20585d() {
        if (this.f3962t) {
            this.f3940a.finish();
        } else {
            this.f3966x.m20634a("javascript:window.AlipayJSBridge.callListener('h5BackAction');");
        }
    }

    /* renamed from: e */
    private void m20584e() {
        WebView webView = this.f3966x.getWebView();
        if (webView.canGoBack()) {
            webView.goBack();
            return;
        }
        C2080u c2080u = this.f3967y;
        if (c2080u != null && !c2080u.m20580b()) {
            m20583f();
        } else {
            m20594a(false);
        }
    }

    /* renamed from: a */
    public void m20595a(String str, String str2, boolean z) {
        this.f3963u = str2;
        this.f3966x.getTitle().setText(str);
        this.f3962t = z;
    }

    /* renamed from: a */
    private void m20594a(boolean z) {
        C1998j.m20912a(z);
        this.f3940a.finish();
    }

    @Override // com.alipay.sdk.widget.AbstractC2065g
    /* renamed from: a */
    public void mo20597a(String str) {
        if ("POST".equals(this.f3963u)) {
            this.f3966x.m20633a(str, (byte[]) null);
        } else {
            this.f3966x.m20634a(str);
        }
    }

    @Override // com.alipay.sdk.widget.AbstractC2065g
    /* renamed from: a */
    public void mo20605a() {
        this.f3966x.m20639a();
        this.f3967y.m20579c();
    }

    @Override // com.alipay.sdk.widget.AbstractC2065g
    /* renamed from: b */
    public boolean mo20593b() {
        if (this.f3964v) {
            return true;
        }
        m20585d();
        return true;
    }

    @Override // com.alipay.sdk.widget.WebViewWindow.InterfaceC2055a
    /* renamed from: a */
    public boolean mo20600a(WebViewWindow webViewWindow, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        if (str2.startsWith("<head>") && str2.contains("sdk_result_code:")) {
            this.f3940a.runOnUiThread(new RunnableC2070k(this));
        }
        jsPromptResult.cancel();
        return true;
    }

    @Override // com.alipay.sdk.widget.WebViewWindow.InterfaceC2055a
    /* renamed from: a */
    public void mo20601a(WebViewWindow webViewWindow, String str) {
        if (str.startsWith("http") || webViewWindow.getUrl().endsWith(str)) {
            return;
        }
        this.f3966x.getTitle().setText(str);
    }

    /* renamed from: f */
    private boolean m20583f() {
        if (this.f3967y.m20580b()) {
            this.f3940a.finish();
        } else {
            this.f3964v = true;
            WebViewWindow webViewWindow = this.f3966x;
            this.f3966x = this.f3967y.m20582a();
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 1.0f, 1, 0.0f, 1, 0.0f);
            translateAnimation.setDuration(400L);
            translateAnimation.setFillAfter(false);
            translateAnimation.setAnimationListener(new C2071l(this, webViewWindow));
            webViewWindow.setAnimation(translateAnimation);
            removeView(webViewWindow);
            addView(this.f3966x);
        }
        return true;
    }

    /* renamed from: b */
    private boolean m20588b(String str, String str2) {
        WebViewWindow webViewWindow = this.f3966x;
        try {
            this.f3966x = new WebViewWindow(this.f3940a);
            this.f3966x.setChromeProxy(this);
            this.f3966x.setWebClientProxy(this);
            this.f3966x.setWebEventProxy(this);
            if (!TextUtils.isEmpty(str2)) {
                this.f3966x.getTitle().setText(str2);
            }
            this.f3964v = true;
            this.f3967y.m20581a(webViewWindow);
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 1.0f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
            translateAnimation.setDuration(400L);
            translateAnimation.setFillAfter(false);
            translateAnimation.setAnimationListener(new C2072m(this, webViewWindow, str));
            this.f3966x.setAnimation(translateAnimation);
            addView(this.f3966x);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.alipay.sdk.widget.WebViewWindow.InterfaceC2056b
    /* renamed from: b */
    public boolean mo20591b(WebViewWindow webViewWindow, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.startsWith("alipayjsbridge://")) {
            m20589b(str.substring(17));
            return true;
        } else if (TextUtils.equals(str, "sdklite://h5quit")) {
            m20594a(false);
            return true;
        } else if (str.startsWith("http://") || str.startsWith("https://")) {
            this.f3966x.m20634a(str);
            return true;
        } else {
            try {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                this.f3940a.startActivity(intent);
                return true;
            } catch (Throwable th) {
                C2000a.m20896a("biz", th);
                return true;
            }
        }
    }

    @Override // com.alipay.sdk.widget.WebViewWindow.InterfaceC2056b
    /* renamed from: c */
    public boolean mo20586c(WebViewWindow webViewWindow, String str) {
        webViewWindow.m20634a("javascript:window.prompt('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');(function() {\n    if (window.AlipayJSBridge) {\n        return\n    }\n\n    function alipayjsbridgeFunc(url) {\n        var iframe = document.createElement(\"iframe\");\n        iframe.style.width = \"1px\";\n        iframe.style.height = \"1px\";\n        iframe.style.display = \"none\";\n        iframe.src = url;\n        document.body.appendChild(iframe);\n        setTimeout(function() {\n            document.body.removeChild(iframe)\n        }, 100)\n    }\n    window.alipayjsbridgeSetTitle = function(title) {\n        document.title = title;\n        alipayjsbridgeFunc(\"alipayjsbridge://setTitle?title=\" + encodeURIComponent(title))\n    };\n    window.alipayjsbridgeRefresh = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onRefresh?\")\n    };\n    window.alipayjsbridgeBack = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onBack?\")\n    };\n    window.alipayjsbridgeExit = function(bsucc) {\n        alipayjsbridgeFunc(\"alipayjsbridge://onExit?bsucc=\" + bsucc)\n    };\n    window.alipayjsbridgeShowBackButton = function(bshow) {\n        alipayjsbridgeFunc(\"alipayjsbridge://showBackButton?bshow=\" + bshow)\n    };\n    window.AlipayJSBridge = {\n        version: \"2.0\",\n        addListener: addListener,\n        hasListener: hasListener,\n        callListener: callListener,\n        callNativeFunc: callNativeFunc,\n        callBackFromNativeFunc: callBackFromNativeFunc\n    };\n    var uniqueId = 1;\n    var h5JsCallbackMap = {};\n\n    function iframeCall(paramStr) {\n        setTimeout(function() {\n        \tvar iframe = document.createElement(\"iframe\");\n        \tiframe.style.width = \"1px\";\n        \tiframe.style.height = \"1px\";\n        \tiframe.style.display = \"none\";\n        \tiframe.src = \"alipayjsbridge://callNativeFunc?\" + paramStr;\n        \tvar parent = document.body || document.documentElement;\n        \tparent.appendChild(iframe);\n        \tsetTimeout(function() {\n            \tparent.removeChild(iframe)\n        \t}, 0)\n        }, 0)\n    }\n\n    function callNativeFunc(nativeFuncName, data, h5JsCallback) {\n        var h5JsCallbackId = \"\";\n        if (h5JsCallback) {\n            h5JsCallbackId = \"cb_\" + (uniqueId++) + \"_\" + new Date().getTime();\n            h5JsCallbackMap[h5JsCallbackId] = h5JsCallback\n        }\n        var dataStr = \"\";\n        if (data) {\n            dataStr = encodeURIComponent(JSON.stringify(data))\n        }\n        var paramStr = \"func=\" + nativeFuncName + \"&cbId=\" + h5JsCallbackId + \"&data=\" + dataStr;\n        iframeCall(paramStr)\n    }\n\n    function callBackFromNativeFunc(h5JsCallbackId, data) {\n        var h5JsCallback = h5JsCallbackMap[h5JsCallbackId];\n        if (h5JsCallback) {\n            h5JsCallback(data);\n            delete h5JsCallbackMap[h5JsCallbackId]\n        }\n    }\n    var h5ListenerMap = {};\n\n    function addListener(jsFuncName, jsFunc) {\n        h5ListenerMap[jsFuncName] = jsFunc\n    }\n\n    function hasListener(jsFuncName) {\n        var jsFunc = h5ListenerMap[jsFuncName];\n        if (!jsFunc) {\n            return false\n        }\n        return true\n    }\n\n    function callListener(h5JsFuncName, data, nativeCallbackId) {\n        var responseCallback;\n        if (nativeCallbackId) {\n            responseCallback = function(responseData) {\n                var dataStr = \"\";\n                if (responseData) {\n                    dataStr = encodeURIComponent(JSON.stringify(responseData))\n                }\n                var paramStr = \"func=h5JsFuncCallback\" + \"&cbId=\" + nativeCallbackId + \"&data=\" + dataStr;\n                iframeCall(paramStr)\n            }\n        }\n        var h5JsFunc = h5ListenerMap[h5JsFuncName];\n        if (h5JsFunc) {\n            h5JsFunc(data, responseCallback)\n        } else if (h5JsFuncName == \"h5BackAction\") {\n            if (!window.alipayjsbridgeH5BackAction || !alipayjsbridgeH5BackAction()) {\n                var paramStr = \"func=back\";\n                iframeCall(paramStr)\n            }\n        } else {\n            console.log(\"AlipayJSBridge: no h5JsFunc \" + h5JsFuncName + data)\n        }\n    }\n    var event;\n    if (window.CustomEvent) {\n        event = new CustomEvent(\"alipayjsbridgeready\")\n    } else {\n        event = document.createEvent(\"Event\");\n        event.initEvent(\"alipayjsbridgeready\", true, true)\n    }\n    document.dispatchEvent(event);\n    setTimeout(excuteH5InitFuncs, 0);\n\n    function excuteH5InitFuncs() {\n        if (window.AlipayJSBridgeInitArray) {\n            var h5InitFuncs = window.AlipayJSBridgeInitArray;\n            delete window.AlipayJSBridgeInitArray;\n            for (var i = 0; i < h5InitFuncs.length; i++) {\n                try {\n                    h5InitFuncs[i](AlipayJSBridge)\n                } catch (e) {\n                    setTimeout(function() {\n                        throw e\n                    })\n                }\n            }\n        }\n    }\n})();\n;window.AlipayJSBridge.callListener('h5PageFinished');");
        webViewWindow.getRefreshButton().setVisibility(0);
        return true;
    }

    @Override // com.alipay.sdk.widget.WebViewWindow.InterfaceC2056b
    /* renamed from: a */
    public boolean mo20603a(WebViewWindow webViewWindow, int i, String str, String str2) {
        C2000a.m20899a("net", "SSLError", "onReceivedError:" + str2);
        webViewWindow.getRefreshButton().setVisibility(0);
        return false;
    }

    @Override // com.alipay.sdk.widget.WebViewWindow.InterfaceC2056b
    /* renamed from: a */
    public boolean mo20602a(WebViewWindow webViewWindow, SslErrorHandler sslErrorHandler, SslError sslError) {
        C2000a.m20899a("net", "SSLError", "2-" + sslError);
        if (this.f3965w) {
            sslErrorHandler.proceed();
            this.f3965w = false;
            return true;
        }
        this.f3940a.runOnUiThread(new RunnableC2073n(this, sslErrorHandler));
        return true;
    }

    /* renamed from: b */
    private void m20589b(String str) {
        Map<String, String> m20652c = C2052n.m20652c(str);
        if (str.startsWith("callNativeFunc")) {
            m20596a(m20652c.get("func"), m20652c.get("cbId"), m20652c.get("data"));
        } else if (str.startsWith("onBack")) {
            m20584e();
        } else if (str.startsWith("setTitle") && m20652c.containsKey("title")) {
            this.f3966x.getTitle().setText(m20652c.get("title"));
        } else if (str.startsWith("onRefresh")) {
            this.f3966x.getWebView().reload();
        } else if (str.startsWith("showBackButton") && m20652c.containsKey("bshow")) {
            this.f3966x.getBackButton().setVisibility(TextUtils.equals("true", m20652c.get("bshow")) ? 0 : 4);
        } else if (str.startsWith("onExit")) {
            C1998j.m20913a(m20652c.get("result"));
            m20594a(TextUtils.equals("true", m20652c.get("bsucc")));
        } else if (str.startsWith("onLoadJs")) {
            this.f3966x.m20634a("javascript:(function() {\n    if (window.AlipayJSBridge) {\n        return\n    }\n\n    function alipayjsbridgeFunc(url) {\n        var iframe = document.createElement(\"iframe\");\n        iframe.style.width = \"1px\";\n        iframe.style.height = \"1px\";\n        iframe.style.display = \"none\";\n        iframe.src = url;\n        document.body.appendChild(iframe);\n        setTimeout(function() {\n            document.body.removeChild(iframe)\n        }, 100)\n    }\n    window.alipayjsbridgeSetTitle = function(title) {\n        document.title = title;\n        alipayjsbridgeFunc(\"alipayjsbridge://setTitle?title=\" + encodeURIComponent(title))\n    };\n    window.alipayjsbridgeRefresh = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onRefresh?\")\n    };\n    window.alipayjsbridgeBack = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onBack?\")\n    };\n    window.alipayjsbridgeExit = function(bsucc) {\n        alipayjsbridgeFunc(\"alipayjsbridge://onExit?bsucc=\" + bsucc)\n    };\n    window.alipayjsbridgeShowBackButton = function(bshow) {\n        alipayjsbridgeFunc(\"alipayjsbridge://showBackButton?bshow=\" + bshow)\n    };\n    window.AlipayJSBridge = {\n        version: \"2.0\",\n        addListener: addListener,\n        hasListener: hasListener,\n        callListener: callListener,\n        callNativeFunc: callNativeFunc,\n        callBackFromNativeFunc: callBackFromNativeFunc\n    };\n    var uniqueId = 1;\n    var h5JsCallbackMap = {};\n\n    function iframeCall(paramStr) {\n        setTimeout(function() {\n        \tvar iframe = document.createElement(\"iframe\");\n        \tiframe.style.width = \"1px\";\n        \tiframe.style.height = \"1px\";\n        \tiframe.style.display = \"none\";\n        \tiframe.src = \"alipayjsbridge://callNativeFunc?\" + paramStr;\n        \tvar parent = document.body || document.documentElement;\n        \tparent.appendChild(iframe);\n        \tsetTimeout(function() {\n            \tparent.removeChild(iframe)\n        \t}, 0)\n        }, 0)\n    }\n\n    function callNativeFunc(nativeFuncName, data, h5JsCallback) {\n        var h5JsCallbackId = \"\";\n        if (h5JsCallback) {\n            h5JsCallbackId = \"cb_\" + (uniqueId++) + \"_\" + new Date().getTime();\n            h5JsCallbackMap[h5JsCallbackId] = h5JsCallback\n        }\n        var dataStr = \"\";\n        if (data) {\n            dataStr = encodeURIComponent(JSON.stringify(data))\n        }\n        var paramStr = \"func=\" + nativeFuncName + \"&cbId=\" + h5JsCallbackId + \"&data=\" + dataStr;\n        iframeCall(paramStr)\n    }\n\n    function callBackFromNativeFunc(h5JsCallbackId, data) {\n        var h5JsCallback = h5JsCallbackMap[h5JsCallbackId];\n        if (h5JsCallback) {\n            h5JsCallback(data);\n            delete h5JsCallbackMap[h5JsCallbackId]\n        }\n    }\n    var h5ListenerMap = {};\n\n    function addListener(jsFuncName, jsFunc) {\n        h5ListenerMap[jsFuncName] = jsFunc\n    }\n\n    function hasListener(jsFuncName) {\n        var jsFunc = h5ListenerMap[jsFuncName];\n        if (!jsFunc) {\n            return false\n        }\n        return true\n    }\n\n    function callListener(h5JsFuncName, data, nativeCallbackId) {\n        var responseCallback;\n        if (nativeCallbackId) {\n            responseCallback = function(responseData) {\n                var dataStr = \"\";\n                if (responseData) {\n                    dataStr = encodeURIComponent(JSON.stringify(responseData))\n                }\n                var paramStr = \"func=h5JsFuncCallback\" + \"&cbId=\" + nativeCallbackId + \"&data=\" + dataStr;\n                iframeCall(paramStr)\n            }\n        }\n        var h5JsFunc = h5ListenerMap[h5JsFuncName];\n        if (h5JsFunc) {\n            h5JsFunc(data, responseCallback)\n        } else if (h5JsFuncName == \"h5BackAction\") {\n            if (!window.alipayjsbridgeH5BackAction || !alipayjsbridgeH5BackAction()) {\n                var paramStr = \"func=back\";\n                iframeCall(paramStr)\n            }\n        } else {\n            console.log(\"AlipayJSBridge: no h5JsFunc \" + h5JsFuncName + data)\n        }\n    }\n    var event;\n    if (window.CustomEvent) {\n        event = new CustomEvent(\"alipayjsbridgeready\")\n    } else {\n        event = document.createEvent(\"Event\");\n        event.initEvent(\"alipayjsbridgeready\", true, true)\n    }\n    document.dispatchEvent(event);\n    setTimeout(excuteH5InitFuncs, 0);\n\n    function excuteH5InitFuncs() {\n        if (window.AlipayJSBridgeInitArray) {\n            var h5InitFuncs = window.AlipayJSBridgeInitArray;\n            delete window.AlipayJSBridgeInitArray;\n            for (var i = 0; i < h5InitFuncs.length; i++) {\n                try {\n                    h5InitFuncs[i](AlipayJSBridge)\n                } catch (e) {\n                    setTimeout(function() {\n                        throw e\n                    })\n                }\n            }\n        }\n    }\n})();\n");
        }
    }

    /* renamed from: a */
    private void m20596a(String str, String str2, String str3) {
        JSONObject m20649d = C2052n.m20649d(str3);
        if ("title".equals(str) && m20649d.has("title")) {
            this.f3966x.getTitle().setText(m20649d.optString("title", ""));
        } else if ("refresh".equals(str)) {
            this.f3966x.getWebView().reload();
        } else if ("back".equals(str)) {
            m20584e();
        } else {
            if ("exit".equals(str)) {
                C1998j.m20913a(m20649d.optString("result", null));
                m20594a(m20649d.optBoolean("success", false));
            } else if ("backButton".equals(str)) {
                this.f3966x.getBackButton().setVisibility(m20649d.optBoolean("show", true) ? 0 : 4);
            } else if ("refreshButton".equals(str)) {
                this.f3966x.getRefreshButton().setVisibility(m20649d.optBoolean("show", true) ? 0 : 4);
            } else if (!"pushWindow".equals(str) || m20649d.optString("url", null) == null) {
            } else {
                m20588b(m20649d.optString("url"), m20649d.optString("title", ""));
            }
        }
    }

    @Override // com.alipay.sdk.widget.WebViewWindow.InterfaceC2057c
    /* renamed from: a */
    public void mo20604a(WebViewWindow webViewWindow) {
        m20585d();
    }

    @Override // com.alipay.sdk.widget.WebViewWindow.InterfaceC2057c
    /* renamed from: b */
    public void mo20592b(WebViewWindow webViewWindow) {
        webViewWindow.getWebView().reload();
        webViewWindow.getRefreshButton().setVisibility(4);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.alipay.sdk.widget.j$a  reason: invalid class name */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    abstract class AbstractanimationAnimation$AnimationListenerC2069a implements Animation.AnimationListener {
        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        private AbstractanimationAnimation$AnimationListenerC2069a() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public /* synthetic */ AbstractanimationAnimation$AnimationListenerC2069a(C2068j c2068j, RunnableC2070k runnableC2070k) {
            this();
        }
    }
}
