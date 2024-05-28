package com.sinovatech.unicom.basic.webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions;
import android.webkit.HttpAuthHandler;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.alipay.sdk.app.PayTask;
import com.bytedance.applog.tracker.Tracker;
import com.example.wxpaydemo.NativeWXPay;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSWebChromeClient;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.networkbench.agent.impl.instrumentation.NBSWebViewClient;
import com.sinovatech.unicom.basic.p314po.PayResult;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerWebViewFullScreen;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.baidumap.BaiduH5BusinessDetails;
import com.sinovatech.unicom.separatemodule.esim.JSWhiteUtil;
import com.sinovatech.unicom.separatemodule.huidu.ManagerHuiDu;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.util.EncodingUtils;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PBWebView extends WebView {
    private static String[] appWhiteList = {"weixin://", "alipay://", "openapp.jdmobile://", "tmall://", "tenvideo://", "tenvideo2://", "tenvideo3://", "taobao://", "tencentlaunch1104466820://", "youku://", "qiyi-iphone://", "kugouURL://", "QQmusic://", "weibo://", "sinaweibo://", "imeituan://", "ofoapp://", "ctrip://", "mobike://", "snssdk141://", "fleamarket://", "diditaxi://", "qqwalletdemoscheme://", "tbopen://", "woshipin://", "weishi://", "sms:", "tel:", "imeituan"};
    public final String PATH_SCHEMA;
    private final int SDK_PAY_FLAG;
    private final String TAG;
    public final String alipayAppCallActiom;
    public final String alipayAppCallBackUrl;
    private String callbackUrl;
    public Activity context;
    private boolean isDailiDialog;
    private Handler mHandler;
    private String orignalUa;
    private Map<String, String> params;
    public WebViewStatusListener statusListener;

    public PBWebView(Context context) {
        super(context);
        this.TAG = "PBWebView";
        this.statusListener = new WebViewStatusListener();
        this.PATH_SCHEMA = "unipay://alipay/";
        this.alipayAppCallBackUrl = "alipayAppCallBackUrl";
        this.alipayAppCallActiom = "alipayAppCallback.action";
        this.SDK_PAY_FLAG = 1;
        this.mHandler = new Handler() { // from class: com.sinovatech.unicom.basic.webview.PBWebView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    return;
                }
                PayResult payResult = new PayResult((String) message.obj);
                PBWebView pBWebView = PBWebView.this;
                String str = PBWebView.this.callbackUrl.trim() + PBWebView.this.getAlipayResult(payResult.getResultStatus(), payResult.getMemo(), payResult.getResult());
                if (pBWebView instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) pBWebView, str);
                } else {
                    pBWebView.loadUrl(str);
                }
            }
        };
        this.isDailiDialog = false;
        this.context = (Activity) context;
        init(this.context);
    }

    public PBWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "PBWebView";
        this.statusListener = new WebViewStatusListener();
        this.PATH_SCHEMA = "unipay://alipay/";
        this.alipayAppCallBackUrl = "alipayAppCallBackUrl";
        this.alipayAppCallActiom = "alipayAppCallback.action";
        this.SDK_PAY_FLAG = 1;
        this.mHandler = new Handler() { // from class: com.sinovatech.unicom.basic.webview.PBWebView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    return;
                }
                PayResult payResult = new PayResult((String) message.obj);
                PBWebView pBWebView = PBWebView.this;
                String str = PBWebView.this.callbackUrl.trim() + PBWebView.this.getAlipayResult(payResult.getResultStatus(), payResult.getMemo(), payResult.getResult());
                if (pBWebView instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) pBWebView, str);
                } else {
                    pBWebView.loadUrl(str);
                }
            }
        };
        this.isDailiDialog = false;
        this.context = (Activity) context;
        init(this.context);
    }

    public void setStatusListener(WebViewStatusListener webViewStatusListener) {
        if (webViewStatusListener != null) {
            this.statusListener = webViewStatusListener;
        }
    }

    @SuppressLint({"NewApi"})
    private void init(final Activity activity) {
        String whiteList = new ConfigManager(activity).getWhiteList();
        if (!TextUtils.isEmpty(whiteList)) {
            appWhiteList = whiteList.split(",");
        }
        if (URLSet.Debug_mode && Build.VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        JSWhiteUtil.initVpnCheck();
        setScrollBarStyle(0);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        setDrawingCacheEnabled(true);
        buildDrawingCache();
        requestFocus();
        setBackgroundColor(Color.parseColor("#fff2f1ef"));
        WebSettings settings = getSettings();
        String userAgentString = settings.getUserAgentString();
        setOrignalUa(userAgentString);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (TextUtils.isEmpty(userAgentString) || !userAgentString.contains("unicom")) {
            settings.setUserAgentString(userAgentString + "; unicom{version:" + activity.getString(2131886969) + ",desmobile:0};devicetype{deviceBrand:" + DeviceHelper.getDeviceBrand() + ",deviceModel:" + DeviceHelper.getDeviceModel() + "}");
        }
        settings.setJavaScriptEnabled(true);
        settings.setTextZoom(100);
        settings.setCacheMode(2);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setSavePassword(false);
        settings.setBuiltInZoomControls(false);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT > 16) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        }
        if (Build.VERSION.SDK_INT >= 27) {
            try {
                settings.setSafeBrowsingEnabled(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (Build.VERSION.SDK_INT > 11) {
            settings.setDisplayZoomControls(false);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        if (Build.VERSION.SDK_INT > 7) {
            settings.setDatabaseEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setAppCacheMaxSize(Long.MAX_VALUE);
            settings.setAppCachePath(activity.getApplicationContext().getFilesDir().getAbsolutePath());
            settings.setAllowFileAccess(true);
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
            settings.setAllowContentAccess(true);
            settings.setAppCacheEnabled(true);
            settings.setPluginState(WebSettings.PluginState.ON);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(this, true);
        }
        setWebViewClient(new NBSWebViewClient() { // from class: com.sinovatech.unicom.basic.webview.PBWebView.2
            @Override // android.webkit.WebViewClient
            public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
                httpAuthHandler.proceed("mobileService", "QJk2%s#@Ejr");
            }

            @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
            public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
                super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
                MsLogUtil.m7977e("WEB-ERROR", webResourceResponse.getStatusCode() + "");
                try {
                    if (PBWebView.this.statusListener != null) {
                        if (PBWebView.this.statusListener.onReceivedHttpError(webView, webResourceRequest, webResourceResponse)) {
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                super.onReceivedError(webView, i, str, str2);
                MsLogUtil.m7977e("WEB-ERROR", "errorCode = [" + i + "], description = [" + str + "], failingUrl = [" + str2 + "]");
                try {
                    if (PBWebView.this.statusListener == null || !PBWebView.this.statusListener.onRecevieError(i, str, str2)) {
                        webView.getSettings().setDefaultTextEncodingName("UTF-8");
                        if (webView instanceof Object) {
                            NBSWebLoadInstrument.loadDataWithBaseURL((Object) webView, "", "<html><head><meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\"></head><body></body></html>", "text/html", "UTF-8", "");
                        } else {
                            webView.loadDataWithBaseURL("", "<html><head><meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\"></head><body></body></html>", "text/html", "UTF-8", "");
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                try {
                    MsLogUtil.m7979d("PBWebView", "shouldOverrideUrlLoading " + str);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (PBWebView.this.statusListener == null || !PBWebView.this.statusListener.onShouldOverrideUrlLoading(str)) {
                    if (str.startsWith(NativeWXPay.PATH_SCHEMA)) {
                        new NativeWXPay((Activity) webView.getContext()).pay(str);
                        return true;
                    }
                    if (str.startsWith("weixin://wap/pay")) {
                        try {
                            Intent intent = new Intent();
                            intent.setAction("android.intent.action.VIEW");
                            intent.setData(Uri.parse(str));
                            activity.startActivity(intent);
                        } catch (ActivityNotFoundException unused) {
                            UIUtils.toast("请安装最新版微信！");
                        }
                        return true;
                    } else if (str.startsWith("unipay://alipay/")) {
                        PBWebView.this.pay(str);
                        return true;
                    } else if (str.startsWith("alipays://") && !App.isAliPay) {
                        try {
                            Intent intent2 = new Intent();
                            intent2.setAction("android.intent.action.VIEW");
                            intent2.setData(Uri.parse(str));
                            activity.startActivity(intent2);
                        } catch (ActivityNotFoundException unused2) {
                            UIUtils.toast("请安装最新版支付宝！");
                        }
                        return true;
                    } else if (!str.startsWith("dcep://")) {
                        for (String str2 : PBWebView.appWhiteList) {
                            if (str.startsWith(str2) || str.contains("mno=ChinaUnicom")) {
                                try {
                                } catch (ActivityNotFoundException unused3) {
                                    UIUtils.toast("请安装最新版应用！");
                                }
                                if (str.startsWith("alipays://") && App.isAliPay) {
                                    return true;
                                }
                                Intent intent3 = new Intent();
                                intent3.setAction("android.intent.action.VIEW");
                                intent3.setData(Uri.parse(str));
                                activity.startActivity(intent3);
                                return true;
                            }
                        }
                        if (!str.startsWith("pinduoduo") && !str.startsWith("appmarket") && !str.startsWith("market")) {
                            if (str.startsWith("androidamap://route")) {
                                if (BaiduH5BusinessDetails.isInstallByread("com.autonavi.minimap")) {
                                    webView.goBack();
                                    Intent intent4 = new Intent();
                                    intent4.setAction("android.intent.action.VIEW");
                                    intent4.setData(Uri.parse(str));
                                    activity.startActivity(intent4);
                                } else {
                                    UIUtils.toastLong("未安装高德的客户端");
                                }
                                return true;
                            }
                            if (ManagerCdnCacheTime.checkCdnCacheTime(str)) {
                                String replaceCdnCacheTime = ManagerCdnCacheTime.replaceCdnCacheTime(str);
                                MsLogUtil.m7979d(ManagerHuiDu.TAG, "shouldOverrideUrlLoading场景 替换CDN缓存时间戳 " + replaceCdnCacheTime);
                                PBWebView pBWebView = PBWebView.this;
                                if (pBWebView instanceof Object) {
                                    NBSWebLoadInstrument.loadUrl((Object) pBWebView, replaceCdnCacheTime);
                                } else {
                                    pBWebView.loadUrl(replaceCdnCacheTime);
                                }
                                return true;
                            }
                            return false;
                        }
                        return true;
                    } else {
                        try {
                            Intent intent5 = new Intent();
                            intent5.setAction("android.intent.action.VIEW");
                            intent5.setData(Uri.parse(str));
                            activity.startActivity(intent5);
                        } catch (ActivityNotFoundException unused4) {
                            UIUtils.toast("请安装最新版数字人民币！");
                        }
                        return true;
                    }
                    e2.printStackTrace();
                    return false;
                }
                return true;
            }

            @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                Tracker.onPageStarted(this, webView, str, bitmap);
                super.onPageStarted(webView, str, bitmap);
                try {
                    MsLogUtil.m7979d("PBWebView", "onPageStarted " + str);
                    if (PBWebView.this.statusListener != null) {
                        if (PBWebView.this.statusListener.onPageStarted(str)) {
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                Tracker.onPageFinished(this, webView, str);
                super.onPageFinished(webView, str);
                try {
                    MsLogUtil.m7979d("PBWebView", "onPageFinished " + str);
                    if (PBWebView.this.statusListener != null) {
                        PBWebView.this.statusListener.onReceivedTitle(webView.getTitle());
                    }
                    if (PBWebView.this.statusListener != null) {
                        if (PBWebView.this.statusListener.onPageFinished()) {
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                MsLogUtil.m7977e("WEB-ERROR", sslError.getUrl() + "");
                sslErrorHandler.proceed();
            }

            /* JADX WARN: Can't wrap try/catch for region: R(14:1|(11:43|44|4|(5:32|33|34|35|36)|6|(2:27|28)|8|(2:22|23)|12|13|(2:15|16)(1:18))|3|4|(0)|6|(0)|8|(1:10)|22|23|12|13|(0)(0)) */
            /* JADX WARN: Code restructure failed: missing block: B:30:0x00db, code lost:
                r1 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:31:0x00dc, code lost:
                r1.printStackTrace();
             */
            /* JADX WARN: Code restructure failed: missing block: B:34:0x00f3, code lost:
                r1 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:35:0x00f4, code lost:
                r1.printStackTrace();
             */
            /* JADX WARN: Removed duplicated region for block: B:26:0x00bf  */
            /* JADX WARN: Removed duplicated region for block: B:37:0x00f9  */
            /* JADX WARN: Removed duplicated region for block: B:45:0x009f A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:49:0x004b A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
            @Override // android.webkit.WebViewClient
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public android.webkit.WebResourceResponse shouldInterceptRequest(android.webkit.WebView r8, android.webkit.WebResourceRequest r9) {
                /*
                    r7 = this;
                    java.lang.String r0 = "shouldInterceptRequest"
                    android.net.Uri r1 = r9.getUrl()
                    java.lang.String r1 = r1.toString()
                    com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7979d(r0, r1)
                    android.net.Uri r0 = r9.getUrl()
                    java.lang.String r0 = r0.toString()
                    java.lang.String r1 = "yhxx_jsbridge.js"
                    boolean r0 = r0.endsWith(r1)
                    if (r0 == 0) goto L39
                    android.webkit.WebResourceResponse r0 = new android.webkit.WebResourceResponse     // Catch: java.io.IOException -> L35
                    java.lang.String r1 = "text/javascript"
                    java.lang.String r2 = "UTF-8"
                    android.app.Activity r3 = r2     // Catch: java.io.IOException -> L35
                    android.content.res.AssetManager r3 = r3.getAssets()     // Catch: java.io.IOException -> L35
                    java.lang.String r4 = "bridge.js"
                    java.io.InputStream r3 = r3.open(r4)     // Catch: java.io.IOException -> L35
                    r0.<init>(r1, r2, r3)     // Catch: java.io.IOException -> L35
                    goto L3a
                L35:
                    r0 = move-exception
                    r0.printStackTrace()
                L39:
                    r0 = 0
                L3a:
                    android.net.Uri r1 = r9.getUrl()
                    java.lang.String r1 = r1.toString()
                    java.lang.String r2 = "unicomfile://"
                    boolean r1 = r1.startsWith(r2)
                    if (r1 == 0) goto L8f
                    android.net.Uri r1 = r9.getUrl()     // Catch: java.io.IOException -> L8b
                    java.lang.String r1 = r1.toString()     // Catch: java.io.IOException -> L8b
                    java.lang.String r2 = "unicomfile://"
                    java.lang.String r3 = ""
                    java.lang.String r1 = r1.replace(r2, r3)     // Catch: java.io.IOException -> L8b
                    java.lang.String r2 = "UTF-8"
                    java.lang.String r1 = java.net.URLDecoder.decode(r1, r2)     // Catch: java.io.IOException -> L8b
                    java.io.File r2 = new java.io.File     // Catch: java.io.IOException -> L8b
                    r2.<init>(r1)     // Catch: java.io.IOException -> L8b
                    android.webkit.WebResourceResponse r1 = new android.webkit.WebResourceResponse     // Catch: java.io.IOException -> L8b
                */
                //  java.lang.String r3 = "*/*"
                /*
                    java.lang.String r4 = "UTF-8"
                    java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.io.IOException -> L8b
                    r5.<init>(r2)     // Catch: java.io.IOException -> L8b
                    r1.<init>(r3, r4, r5)     // Catch: java.io.IOException -> L8b
                    java.util.HashMap r0 = new java.util.HashMap     // Catch: java.io.IOException -> L86
                    r0.<init>()     // Catch: java.io.IOException -> L86
                    java.lang.String r2 = "access-control-allow-origin"
                    java.lang.String r3 = "*"
                    r0.put(r2, r3)     // Catch: java.io.IOException -> L86
                    r1.setResponseHeaders(r0)     // Catch: java.io.IOException -> L86
                    r0 = r1
                    goto L8f
                L86:
                    r0 = move-exception
                    r6 = r1
                    r1 = r0
                    r0 = r6
                    goto L8c
                L8b:
                    r1 = move-exception
                L8c:
                    r1.printStackTrace()
                L8f:
                    android.net.Uri r1 = r9.getUrl()
                    java.lang.String r1 = r1.toString()
                    java.lang.String r2 = "edoplowcode://"
                    boolean r1 = r1.startsWith(r2)
                    if (r1 == 0) goto Lae
                    android.app.Activity r1 = r2     // Catch: java.lang.Exception -> Laa
                    com.sinovatech.unicom.separatemodule.miniprogram.lowcode.CumpLowcodeResourceIntercepter r1 = com.sinovatech.unicom.separatemodule.miniprogram.lowcode.CumpLowcodeResourceIntercepter.getInstance(r1)     // Catch: java.lang.Exception -> Laa
                    android.webkit.WebResourceResponse r0 = r1.interceptLowcodeResource(r8, r9)     // Catch: java.lang.Exception -> Laa
                    goto Lae
                Laa:
                    r1 = move-exception
                    r1.printStackTrace()
                Lae:
                    android.net.Uri r1 = r9.getUrl()
                    java.lang.String r1 = r1.toString()
                    java.lang.String r2 = "unicomsrcs://"
                    boolean r1 = r1.startsWith(r2)
                    if (r1 != 0) goto Ld0
                    android.net.Uri r1 = r9.getUrl()
                    java.lang.String r1 = r1.toString()
                    java.lang.String r2 = "unicomsrc://"
                    boolean r1 = r1.startsWith(r2)
                    if (r1 == 0) goto Ldf
                Ld0:
                    android.app.Activity r1 = r2     // Catch: java.lang.Exception -> Ldb
                    com.sinovatech.unicom.basic.webview.UnicomSrcResourceIntercepter r1 = com.sinovatech.unicom.basic.webview.UnicomSrcResourceIntercepter.getInstance(r1)     // Catch: java.lang.Exception -> Ldb
                    android.webkit.WebResourceResponse r0 = r1.interceptSrcResource(r8, r9)     // Catch: java.lang.Exception -> Ldb
                    goto Ldf
                Ldb:
                    r1 = move-exception
                    r1.printStackTrace()
                Ldf:
                    android.app.Activity r1 = r2     // Catch: java.lang.Exception -> Lf3
                    android.net.Uri r2 = r9.getUrl()     // Catch: java.lang.Exception -> Lf3
                    java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> Lf3
                    com.sinovatech.unicom.basic.webview.PBWebView r3 = com.sinovatech.unicom.basic.webview.PBWebView.this     // Catch: java.lang.Exception -> Lf3
                    android.os.Handler r3 = com.sinovatech.unicom.basic.webview.PBWebView.access$300(r3)     // Catch: java.lang.Exception -> Lf3
                    com.sinovatech.unicom.separatemodule.esim.JSWhiteUtil.checkVpn(r1, r2, r3)     // Catch: java.lang.Exception -> Lf3
                    goto Lf7
                Lf3:
                    r1 = move-exception
                    r1.printStackTrace()
                Lf7:
                    if (r0 != 0) goto Lfd
                    android.webkit.WebResourceResponse r0 = super.shouldInterceptRequest(r8, r9)
                Lfd:
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.webview.PBWebView.C80892.shouldInterceptRequest(android.webkit.WebView, android.webkit.WebResourceRequest):android.webkit.WebResourceResponse");
            }
        });
        setDownloadListener(new DownloadListener() { // from class: com.sinovatech.unicom.basic.webview.PBWebView.3
            @Override // android.webkit.DownloadListener
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                try {
                    activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                } catch (Exception e2) {
                    e2.printStackTrace();
                    UIUtils.toastLong("系统没有安装浏览器！");
                }
            }
        });
        setWebChromeClient(new WebChromeClient() { // from class: com.sinovatech.unicom.basic.webview.PBWebView.4
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView, int i) {
                try {
                    NBSWebChromeClient.initJSMonitor(webView, i);
                    super.onProgressChanged(webView, i);
                    if (PBWebView.this.statusListener != null) {
                        PBWebView.this.statusListener.onProgressChanged(i);
                    }
                } catch (Exception e2) {
                    MsLogUtil.m7977e("PBWebView", "PBWebview onProgressChanged()方法异常:" + e2.getMessage());
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onReceivedTitle(WebView webView, String str) {
                super.onReceivedTitle(webView, str);
            }

            @Override // android.webkit.WebChromeClient
            public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
                callback.invoke(str, true, false);
                super.onGeolocationPermissionsShowPrompt(str, callback);
            }

            @Override // android.webkit.WebChromeClient
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                PBWebView.this.statusListener.openFileChooserFor5(valueCallback, fileChooserParams);
                return true;
            }

            @Override // android.webkit.WebChromeClient
            public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
                super.onShowCustomView(view, customViewCallback);
                ManagerWebViewFullScreen.showCustomView(activity, PBWebView.this, view, customViewCallback);
            }

            @Override // android.webkit.WebChromeClient
            public void onShowCustomView(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
                super.onShowCustomView(view, i, customViewCallback);
                ManagerWebViewFullScreen.showCustomView(activity, PBWebView.this, view, customViewCallback);
            }

            @Override // android.webkit.WebChromeClient
            public void onHideCustomView() {
                super.onHideCustomView();
                ManagerWebViewFullScreen.hideCustomView(activity, PBWebView.this);
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
                jsResult.confirm();
                return true;
            }

            @Override // android.webkit.WebChromeClient
            @RequiresApi(api = 21)
            public void onPermissionRequest(final PermissionRequest permissionRequest) {
                new Handler().post(new Runnable() { // from class: com.sinovatech.unicom.basic.webview.PBWebView.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        PermissionRequest permissionRequest2 = permissionRequest;
                        permissionRequest2.grant(permissionRequest2.getResources());
                    }
                });
            }
        });
        addJavascriptInterface(new AndroidChinaUnicomAppPhoneInformation(), "AndroidChinaUnicomAppPhoneInformation");
    }

    public String getOrignalUa() {
        return this.orignalUa;
    }

    public void setOrignalUa(String str) {
        this.orignalUa = str;
    }

    public void get(String str, Map<String, String> map, String str2) {
        this.params = map;
        if (!str.startsWith("http")) {
            str = URLSet.getHttpPrefix() + str;
        }
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!str.contains(entry.getKey())) {
                    str = str.contains("?") ? str + "&" + entry.getKey() + "=" + entry.getValue() : str + "?" + entry.getKey() + "=" + entry.getValue();
                }
            }
        }
        if (!TextUtils.isEmpty(WebFragment.findder)) {
            str = str.contains("?") ? str + "&" + WebFragment.findder : str + "?" + WebFragment.findder;
        }
        if (TextUtils.isEmpty(str2)) {
            if (this instanceof Object) {
                NBSWebLoadInstrument.loadUrl((Object) this, str);
            } else {
                loadUrl(str);
            }
        } else {
            HashMap hashMap = new HashMap();
            hashMap.put("Referer", str2);
            if (this instanceof Object) {
                NBSWebLoadInstrument.loadUrl((Object) this, str, (Map<String, String>) hashMap);
            } else {
                loadUrl(str, hashMap);
            }
        }
        WebFragment.findder = "";
    }

    public void get(String str, Map<String, String> map) {
        this.params = map;
        if (!str.startsWith("http")) {
            str = URLSet.getHttpPrefix() + str;
        }
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!str.contains(entry.getKey())) {
                    str = str.contains("?") ? str + "&" + entry.getKey() + "=" + entry.getValue() : str + "?" + entry.getKey() + "=" + entry.getValue();
                }
            }
        }
        if (!TextUtils.isEmpty(WebFragment.findder)) {
            str = str.contains("?") ? str + "&" + WebFragment.findder : str + "?" + WebFragment.findder;
        }
        if (this instanceof Object) {
            NBSWebLoadInstrument.loadUrl((Object) this, str);
        } else {
            loadUrl(str);
        }
        WebFragment.findder = "";
    }

    public void get(String str, Map<String, String> map, Map<String, String> map2) {
        this.params = map;
        if (!str.startsWith("http")) {
            str = URLSet.getHttpPrefix() + str;
        }
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!str.contains(entry.getKey())) {
                    str = str.contains("?") ? str + "&" + entry.getKey() + "=" + entry.getValue() : str + "?" + entry.getKey() + "=" + entry.getValue();
                }
            }
        }
        if (!TextUtils.isEmpty(WebFragment.findder)) {
            str = str.contains("?") ? str + "&" + WebFragment.findder : str + "?" + WebFragment.findder;
        }
        if (this instanceof Object) {
            NBSWebLoadInstrument.loadUrl((Object) this, str, map2);
        } else {
            loadUrl(str, map2);
        }
        WebFragment.findder = "";
    }

    public void post(String str, Map<String, String> map) {
        this.params = map;
        if (!str.startsWith("http")) {
            str = URLSet.getHttpPrefix() + str;
        }
        String str2 = "";
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                str2 = str2 + entry.getKey() + "=" + entry.getValue() + "&";
            }
        }
        if (!TextUtils.isEmpty(str2)) {
            str2 = str2.substring(0, str2.length() - 1);
        }
        postUrl(str, EncodingUtils.getBytes(str2, "BASE64"));
    }

    public void loadURL(String str) {
        if (this instanceof Object) {
            NBSWebLoadInstrument.loadUrl((Object) this, str);
        } else {
            loadUrl(str);
        }
    }

    public void pay(String str) {
        try {
            String paramInfo = getParamInfo(str);
            final String substring = paramInfo.substring(0, paramInfo.indexOf("alipayAppCallBackUrl") - 1);
            this.callbackUrl = getCallbackUrl(paramInfo);
            new Thread(new Runnable() { // from class: com.sinovatech.unicom.basic.webview.PBWebView.5
                @Override // java.lang.Runnable
                public void run() {
                    String pay = new PayTask(PBWebView.this.context).pay(substring, true);
                    Message message = new Message();
                    message.what = 1;
                    message.obj = pay;
                    PBWebView.this.mHandler.sendMessage(message);
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    private String getParamInfo(String str) {
        try {
            return URLDecoder.decode(str.substring(16), "utf-8").replaceAll("\\s", "+");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String getCallbackUrl(String str) {
        try {
            String str2 = str.substring(str.indexOf("alipayAppCallBackUrl"), str.length()).replace("\"", "").split("=")[1];
            if (str2.contains("alipayAppCallback.action")) {
                return str2.substring(0, str2.indexOf("alipayAppCallback.action")) + "alipayAppCallback.action";
            }
            return str2;
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7980d(e.getMessage());
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getAlipayResult(String str, String str2, String str3) {
        try {
            return "?recallback=" + URLEncoder.encode(URLEncoder.encode("resultStatus={" + str + "};memo={" + str2 + "};result={" + str3 + "}", "UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class AndroidChinaUnicomAppPhoneInformation {
        AndroidChinaUnicomAppPhoneInformation() {
        }

        @JavascriptInterface
        public String load() {
            String str = "";
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("CUVersion", PBWebView.this.context.getString(2131886969));
                str = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
