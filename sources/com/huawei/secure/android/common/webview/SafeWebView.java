package com.huawei.secure.android.common.webview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SafeBrowsingResponse;
import android.webkit.SslErrorHandler;
import android.webkit.URLUtil;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.bytedance.applog.tracker.Tracker;
import com.huawei.secure.android.common.util.LogsUtil;
import com.huawei.secure.android.common.webview.WebViewLoadCallBack;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.networkbench.agent.impl.instrumentation.NBSWebViewClient;
import java.util.Arrays;
import java.util.Map;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SafeWebView extends WebView {

    /* renamed from: f */
    private static final String f12177f = "SafeWebView";

    /* renamed from: a */
    private String f12178a;

    /* renamed from: b */
    private String[] f12179b;

    /* renamed from: c */
    private String[] f12180c;

    /* renamed from: d */
    private String[] f12181d;

    /* renamed from: e */
    private WebViewLoadCallBack f12182e;

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.huawei.secure.android.common.webview.SafeWebView$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public final class C5130b extends NBSWebViewClient {

        /* renamed from: a */
        private WebViewClient f12183a;

        /* renamed from: b */
        private boolean f12184b;

        @Override // android.webkit.WebViewClient
        public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
            WebViewClient webViewClient = this.f12183a;
            if (webViewClient != null) {
                webViewClient.doUpdateVisitedHistory(webView, str, z);
            } else {
                super.doUpdateVisitedHistory(webView, str, z);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onFormResubmission(WebView webView, Message message, Message message2) {
            WebViewClient webViewClient = this.f12183a;
            if (webViewClient != null) {
                webViewClient.onFormResubmission(webView, message, message2);
            } else {
                super.onFormResubmission(webView, message, message2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onLoadResource(WebView webView, String str) {
            WebViewClient webViewClient = this.f12183a;
            if (webViewClient != null) {
                webViewClient.onLoadResource(webView, str);
            } else {
                super.onLoadResource(webView, str);
            }
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(23)
        public void onPageCommitVisible(WebView webView, String str) {
            WebViewClient webViewClient = this.f12183a;
            if (webViewClient != null) {
                webViewClient.onPageCommitVisible(webView, str);
            } else {
                super.onPageCommitVisible(webView, str);
            }
        }

        @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            Tracker.onPageFinished(this, webView, str);
            WebViewClient webViewClient = this.f12183a;
            if (webViewClient != null) {
                webViewClient.onPageFinished(webView, str);
            } else {
                super.onPageFinished(webView, str);
            }
        }

        @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Tracker.onPageStarted(this, webView, str, bitmap);
            WebViewClient webViewClient = this.f12183a;
            if (webViewClient != null && !this.f12184b) {
                webViewClient.onPageStarted(webView, str, bitmap);
                super.onPageStarted(webView, str, bitmap);
            } else if (!SafeWebView.this.isWhiteListUrl(str)) {
                SafeWebView.this.onCheckError(webView, str);
            } else {
                super.onPageStarted(webView, str, bitmap);
            }
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(21)
        public void onReceivedClientCertRequest(WebView webView, ClientCertRequest clientCertRequest) {
            WebViewClient webViewClient = this.f12183a;
            if (webViewClient != null) {
                webViewClient.onReceivedClientCertRequest(webView, clientCertRequest);
            } else {
                super.onReceivedClientCertRequest(webView, clientCertRequest);
            }
        }

        @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            WebViewClient webViewClient = this.f12183a;
            if (webViewClient != null) {
                webViewClient.onReceivedError(webView, i, str, str2);
            } else {
                super.onReceivedError(webView, i, str, str2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
            WebViewClient webViewClient = this.f12183a;
            if (webViewClient != null) {
                webViewClient.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
            } else {
                super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
            }
        }

        @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
        @TargetApi(23)
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            WebViewClient webViewClient = this.f12183a;
            if (webViewClient != null) {
                webViewClient.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            } else {
                super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            }
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(12)
        public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
            WebViewClient webViewClient = this.f12183a;
            if (webViewClient != null) {
                webViewClient.onReceivedLoginRequest(webView, str, str2, str3);
            } else {
                super.onReceivedLoginRequest(webView, str, str2, str3);
            }
        }

        @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
        @TargetApi(8)
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            WebViewClient webViewClient = this.f12183a;
            if (webViewClient != null) {
                webViewClient.onReceivedSslError(webView, sslErrorHandler, sslError);
            } else {
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
            }
        }

        @Override // android.webkit.WebViewClient
        @SuppressLint({"NewApi"})
        public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
            WebViewClient webViewClient = this.f12183a;
            if (webViewClient != null) {
                return webViewClient.onRenderProcessGone(webView, renderProcessGoneDetail);
            }
            return super.onRenderProcessGone(webView, renderProcessGoneDetail);
        }

        @Override // android.webkit.WebViewClient
        @SuppressLint({"NewApi"})
        public void onSafeBrowsingHit(WebView webView, WebResourceRequest webResourceRequest, int i, SafeBrowsingResponse safeBrowsingResponse) {
            WebViewClient webViewClient = this.f12183a;
            if (webViewClient != null) {
                webViewClient.onSafeBrowsingHit(webView, webResourceRequest, i, safeBrowsingResponse);
            } else {
                super.onSafeBrowsingHit(webView, webResourceRequest, i, safeBrowsingResponse);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onScaleChanged(WebView webView, float f, float f2) {
            WebViewClient webViewClient = this.f12183a;
            if (webViewClient != null) {
                webViewClient.onScaleChanged(webView, f, f2);
            } else {
                super.onScaleChanged(webView, f, f2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onTooManyRedirects(WebView webView, Message message, Message message2) {
            WebViewClient webViewClient = this.f12183a;
            if (webViewClient != null) {
                webViewClient.onTooManyRedirects(webView, message, message2);
            } else {
                super.onTooManyRedirects(webView, message, message2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
            WebViewClient webViewClient = this.f12183a;
            if (webViewClient != null) {
                webViewClient.onUnhandledKeyEvent(webView, keyEvent);
            } else {
                super.onUnhandledKeyEvent(webView, keyEvent);
            }
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(21)
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            WebViewClient webViewClient = this.f12183a;
            if (webViewClient != null) {
                return webViewClient.shouldInterceptRequest(webView, webResourceRequest);
            }
            return super.shouldInterceptRequest(webView, webResourceRequest);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
            WebViewClient webViewClient = this.f12183a;
            if (webViewClient != null) {
                return webViewClient.shouldOverrideKeyEvent(webView, keyEvent);
            }
            return super.shouldOverrideKeyEvent(webView, keyEvent);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            WebViewClient webViewClient = this.f12183a;
            if (webViewClient != null) {
                return webViewClient.shouldOverrideUrlLoading(webView, str);
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }

        private C5130b(WebViewClient webViewClient, boolean z) {
            this.f12183a = webViewClient;
            this.f12184b = z;
        }

        @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
        @TargetApi(23)
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            WebViewClient webViewClient = this.f12183a;
            if (webViewClient != null) {
                webViewClient.onReceivedError(webView, webResourceRequest, webResourceError);
            } else {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
            }
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(11)
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            WebViewClient webViewClient = this.f12183a;
            if (webViewClient != null) {
                return webViewClient.shouldInterceptRequest(webView, str);
            }
            return super.shouldInterceptRequest(webView, str);
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(24)
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            WebViewClient webViewClient = this.f12183a;
            if (webViewClient != null) {
                return webViewClient.shouldOverrideUrlLoading(webView, webResourceRequest);
            }
            return super.shouldOverrideUrlLoading(webView, webResourceRequest);
        }
    }

    public SafeWebView(Context context) {
        super(context);
        m13764a();
    }

    /* renamed from: a */
    private boolean m13763a(String str) {
        return URLUtil.isHttpUrl(str);
    }

    public String getDefaultErrorPage() {
        return this.f12178a;
    }

    public WebViewLoadCallBack getWebViewLoadCallBack() {
        return this.f12182e;
    }

    @TargetApi(9)
    @Deprecated
    public String[] getWhitelist() {
        String[] strArr = this.f12179b;
        if (strArr == null) {
            return null;
        }
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public String[] getWhitelistNotMatchSubDomain() {
        String[] strArr = this.f12180c;
        if (strArr == null) {
            return null;
        }
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    @Deprecated
    public String[] getWhitelistNotMathcSubDomain() {
        String[] strArr = this.f12180c;
        if (strArr == null) {
            return null;
        }
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    @TargetApi(9)
    public String[] getWhitelistWithPath() {
        String[] strArr = this.f12181d;
        if (strArr == null) {
            return null;
        }
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    @TargetApi(9)
    public boolean isWhiteListUrl(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            LogsUtil.m13819e(f12177f, "url is null");
            return false;
        } else if (URLUtil.isNetworkUrl(str)) {
            String[] whitelistWithPath = getWhitelistWithPath();
            String[] whitelistNotMatchSubDomain = getWhitelistNotMatchSubDomain();
            String[] whitelist = getWhitelist();
            boolean z2 = whitelistWithPath == null || whitelistWithPath.length == 0;
            boolean z3 = whitelistNotMatchSubDomain == null || whitelistNotMatchSubDomain.length == 0;
            if (whitelist == null || whitelist.length == 0) {
                z = true;
            }
            if (z2 && z3 && z) {
                return true;
            }
            if (whitelistWithPath != null && whitelistWithPath.length != 0) {
                return UriUtil.isUrlHostAndPathInWhitelist(str, whitelistWithPath);
            }
            if (whitelistNotMatchSubDomain != null && whitelistNotMatchSubDomain.length != 0) {
                return UriUtil.isUrlHostSameWhitelist(str, whitelistNotMatchSubDomain);
            }
            return UriUtil.isUrlHostInWhitelist(str, whitelist);
        } else {
            return true;
        }
    }

    @Override // android.webkit.WebView
    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        if (m13763a(str)) {
            Log.e(f12177f, "loadDataWithBaseURL: http url , not safe");
            if (!TextUtils.isEmpty(this.f12178a)) {
                super.loadDataWithBaseURL(this.f12178a, str2, str3, str4, str5);
                return;
            } else if (getWebViewLoadCallBack() != null) {
                Log.e(f12177f, "WebViewLoadCallBack");
                getWebViewLoadCallBack().onCheckError(str, WebViewLoadCallBack.ErrorCode.HTTP_URL);
                return;
            } else {
                return;
            }
        }
        super.loadDataWithBaseURL(str, str2, str3, str4, str5);
    }

    @Override // android.webkit.WebView
    public void loadUrl(String str) {
        if (isWhiteListUrl(str) && !m13763a(str)) {
            super.loadUrl(str);
            return;
        }
        Log.e(f12177f, "loadUrl: url is not in white list or http url not safe");
        if (!TextUtils.isEmpty(this.f12178a)) {
            super.loadUrl(this.f12178a);
        } else if (getWebViewLoadCallBack() != null) {
            Log.e(f12177f, "WebViewLoadCallBack");
            getWebViewLoadCallBack().onCheckError(str, WebViewLoadCallBack.ErrorCode.HTTP_URL);
        }
    }

    public final void onCheckError(WebView webView, String str) {
        LogsUtil.m13818e(f12177f, "onCheckError url is not in white list ", str);
        webView.stopLoading();
        String defaultErrorPage = getDefaultErrorPage();
        if (!TextUtils.isEmpty(defaultErrorPage)) {
            if (webView instanceof Object) {
                NBSWebLoadInstrument.loadUrl((Object) webView, defaultErrorPage);
            } else {
                webView.loadUrl(defaultErrorPage);
            }
        } else if (getWebViewLoadCallBack() != null) {
            Log.e(f12177f, "onPageStarted WebViewLoadCallBack");
            getWebViewLoadCallBack().onCheckError(str, WebViewLoadCallBack.ErrorCode.URL_NOT_IN_WHITE_LIST);
        }
    }

    @Override // android.webkit.WebView
    public void postUrl(String str, byte[] bArr) {
        if (isWhiteListUrl(str) && !m13763a(str)) {
            super.postUrl(str, bArr);
            return;
        }
        Log.e(f12177f, "postUrl: url is not in white list or http url not safe");
        if (!TextUtils.isEmpty(this.f12178a)) {
            super.postUrl(this.f12178a, bArr);
        } else if (getWebViewLoadCallBack() != null) {
            Log.e(f12177f, "WebViewLoadCallBack");
            getWebViewLoadCallBack().onCheckError(str, WebViewLoadCallBack.ErrorCode.HTTP_URL);
        }
    }

    public void setDefaultErrorPage(String str) {
        this.f12178a = str;
    }

    @Override // android.webkit.WebView
    public void setWebViewClient(WebViewClient webViewClient) {
        super.setWebViewClient(new C5130b(webViewClient, true));
    }

    public void setWebViewLoadCallBack(WebViewLoadCallBack webViewLoadCallBack) {
        this.f12182e = webViewLoadCallBack;
    }

    @TargetApi(9)
    @Deprecated
    public void setWhitelist(String[] strArr) {
        this.f12179b = strArr == null ? null : (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public void setWhitelistNotMatchSubDomain(String[] strArr) {
        this.f12180c = strArr == null ? null : (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    @Deprecated
    public void setWhitelistNotMathcSubDomain(String[] strArr) {
        this.f12180c = strArr == null ? null : (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    @TargetApi(9)
    public void setWhitelistWithPath(String[] strArr) {
        this.f12181d = strArr == null ? null : (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    /* renamed from: a */
    private void m13764a() {
        SafeWebSettings.initWebviewAndSettings(this);
        setWebViewClient(null);
    }

    public void setWebViewClient(WebViewClient webViewClient, boolean z) {
        super.setWebViewClient(new C5130b(webViewClient, z));
    }

    public SafeWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m13764a();
    }

    public SafeWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m13764a();
    }

    @TargetApi(21)
    public SafeWebView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        m13764a();
    }

    @Override // android.webkit.WebView
    public void loadUrl(String str, Map<String, String> map) {
        if (isWhiteListUrl(str) && !m13763a(str)) {
            super.loadUrl(str, map);
            return;
        }
        Log.e(f12177f, "loadUrl: url is not in white list or http url not safe");
        if (!TextUtils.isEmpty(this.f12178a)) {
            super.loadUrl(this.f12178a, map);
        } else if (getWebViewLoadCallBack() != null) {
            Log.e(f12177f, "WebViewLoadCallBack");
            getWebViewLoadCallBack().onCheckError(str, WebViewLoadCallBack.ErrorCode.HTTP_URL);
        }
    }
}
