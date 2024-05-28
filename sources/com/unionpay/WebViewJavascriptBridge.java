package com.unionpay;

import android.app.Activity;
import android.os.Build;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.unionpay.utils.C10923j;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WebViewJavascriptBridge implements Serializable {
    InterfaceC10740aa _messageHandler;
    Activity mContext;
    WebView mWebView;
    private boolean mAllowScheme = false;
    Map _messageHandlers = new HashMap();
    Map _responseCallbacks = new HashMap();
    long _uniqueId = 0;

    public WebViewJavascriptBridge(Activity activity, WebView webView, InterfaceC10740aa interfaceC10740aa) {
        this.mContext = activity;
        this.mWebView = webView;
        this._messageHandler = interfaceC10740aa;
        WebSettings settings = this.mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setAllowFileAccess(false);
        settings.setDomStorageEnabled(true);
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                this.mWebView.removeJavascriptInterface("accessibility");
                this.mWebView.removeJavascriptInterface("accessibilityTraversal");
                this.mWebView.removeJavascriptInterface("searchBoxJavaBridge_");
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.mWebView.addJavascriptInterface(this, "_WebViewJavascriptBridge");
        WebView webView2 = this.mWebView;
        C10931z c10931z = new C10931z(this, (byte) 0);
        if (webView2 instanceof WebView) {
            NBSWebLoadInstrument.setWebViewClient(webView2, c10931z);
        } else {
            webView2.setWebViewClient(c10931z);
        }
        this.mWebView.setWebChromeClient(new C10930y(this, (byte) 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _callbackJs(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("responseId", str);
        hashMap.put("responseData", str2);
        _dispatchMessage(hashMap);
    }

    private void _dispatchMessage(Map map) {
        JSONObject jSONObject = new JSONObject(map);
        String jSONObject2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        C10923j.m5830a("test", "sending:" + jSONObject2);
        this.mContext.runOnUiThread(new RunnableC10928w(this, String.format("javascript:WebViewJavascriptBridge._handleMessageFromJava('%s');", doubleEscapeString(jSONObject2))));
    }

    private void _sendData(String str, InterfaceC10741ab interfaceC10741ab, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("data", str);
        if (interfaceC10741ab != null) {
            StringBuilder sb = new StringBuilder("java_cb_");
            long j = this._uniqueId + 1;
            this._uniqueId = j;
            sb.append(j);
            String sb2 = sb.toString();
            this._responseCallbacks.put(sb2, interfaceC10741ab);
            hashMap.put("callbackId", sb2);
        }
        if (str2 != null) {
            hashMap.put("handlerName", str2);
        }
        _dispatchMessage(hashMap);
    }

    public static String convertStreamToString(InputStream inputStream) {
        String str;
        str = "";
        try {
            Scanner useDelimiter = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
            str = useDelimiter.hasNext() ? useDelimiter.next() : "";
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    private String doubleEscapeString(String str) {
        return str.replace("\\", "\\\\").replace("\"", "\\\"").replace("'", "\\'").replace("\n", "\\n").replace("\r", "\\r").replace("\f", "\\f");
    }

    private void loadWebViewJavascriptBridgeJs(WebView webView) {
        String str = "javascript:" + convertStreamToString(getClass().getResourceAsStream("res/webviewjavascriptbridge.js"));
        if (webView instanceof Object) {
            NBSWebLoadInstrument.loadUrl((Object) webView, str);
        } else {
            webView.loadUrl(str);
        }
    }

    @JavascriptInterface
    public void _handleMessageFromJs(String str, String str2, String str3, String str4, String str5) {
        InterfaceC10740aa interfaceC10740aa;
        if (str2 != null) {
            ((InterfaceC10741ab) this._responseCallbacks.get(str2)).mo5826a(str3);
            this._responseCallbacks.remove(str2);
            return;
        }
        C10929x c10929x = str4 != null ? new C10929x(this, str4) : null;
        if (str5 != null) {
            interfaceC10740aa = (InterfaceC10740aa) this._messageHandlers.get(str5);
            if (interfaceC10740aa == null) {
                C10923j.m5828c("test", "WVJB Warning: No handler for " + str5);
                return;
            }
        } else {
            interfaceC10740aa = this._messageHandler;
        }
        try {
            this.mContext.runOnUiThread(new RunnableC10927v(this, interfaceC10740aa, str, c10929x));
        } catch (Exception e) {
            C10923j.m5828c("test", "WebViewJavascriptBridge: WARNING: java handler threw. " + e.getMessage());
        }
    }

    public void callHandler(String str) {
        callHandler(str, null, null);
    }

    public void callHandler(String str, String str2) {
        callHandler(str, str2, null);
    }

    public void callHandler(String str, String str2, InterfaceC10741ab interfaceC10741ab) {
        _sendData(str2, interfaceC10741ab, str);
    }

    public void registerHandler(String str, InterfaceC10740aa interfaceC10740aa) {
        this._messageHandlers.put(str, interfaceC10740aa);
    }

    public void send(String str) {
        send(str, null);
    }

    public void send(String str, InterfaceC10741ab interfaceC10741ab) {
        _sendData(str, interfaceC10741ab, null);
    }

    public void setAllowScheme(boolean z) {
        this.mAllowScheme = z;
    }
}
