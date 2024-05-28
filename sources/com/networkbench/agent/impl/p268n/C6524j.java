package com.networkbench.agent.impl.p268n;

import android.annotation.TargetApi;
import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.HarvestConfiguration;
import com.networkbench.agent.impl.harvest.RequestMethodType;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6648q;
import com.networkbench.agent.impl.util.C6652t;
import com.networkbench.agent.impl.util.NBSAndroidAgentImpl;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.n.j */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6524j {

    /* renamed from: b */
    public static final boolean f16632b = false;

    /* renamed from: c */
    private static InterfaceC6393e f16633c = C6394f.m10150a();

    /* renamed from: a */
    public static String f16631a = "webview-->";

    /* renamed from: d */
    private static int f16634d = -4;

    /* renamed from: e */
    private static int f16635e = -12;

    /* renamed from: f */
    private static int f16636f = -6;

    /* renamed from: g */
    private static int f16637g = -11;

    /* renamed from: h */
    private static int f16638h = -13;

    /* renamed from: i */
    private static int f16639i = -14;

    /* renamed from: j */
    private static int f16640j = -2;

    /* renamed from: k */
    private static int f16641k = -7;

    /* renamed from: l */
    private static int f16642l = -5;

    /* renamed from: m */
    private static int f16643m = -9;

    /* renamed from: n */
    private static int f16644n = -8;

    /* renamed from: o */
    private static int f16645o = -15;

    /* renamed from: p */
    private static int f16646p = -1;

    /* renamed from: q */
    private static int f16647q = -16;

    /* renamed from: r */
    private static int f16648r = -3;

    /* renamed from: s */
    private static int f16649s = -10;

    /* renamed from: t */
    private static int f16650t = 4;

    /* renamed from: u */
    private static int f16651u = 1;

    /* renamed from: v */
    private static int f16652v = 2;

    /* renamed from: w */
    private static int f16653w = 5;

    /* renamed from: x */
    private static int f16654x = 6;

    /* renamed from: y */
    private static int f16655y = 0;

    /* renamed from: z */
    private static int f16656z = 3;

    /* renamed from: A */
    private static boolean f16630A = true;

    /* renamed from: a */
    public static void m9538a(String str) {
    }

    /* renamed from: a */
    public static void m9536a(String str, Throwable th) {
    }

    /* renamed from: b */
    public static void m9534b(String str) {
    }

    /* renamed from: c */
    public static void m9533c(String str) {
    }

    /* renamed from: d */
    public static void m9532d(String str) {
    }

    /* renamed from: e */
    public static void m9531e(String str) {
    }

    /* renamed from: a */
    private static int m9543a(int i) {
        if (i == f16635e) {
            return 900;
        }
        if (i == f16634d || i == f16642l) {
            return 907;
        }
        if (i == f16636f) {
            return 902;
        }
        if (i == f16637g) {
            return 908;
        }
        if (i == f16640j) {
            return 901;
        }
        return i == f16644n ? 903 : -1;
    }

    /* renamed from: a */
    public static void m9535a(boolean z) {
        f16630A = z;
    }

    /* renamed from: a */
    public static boolean m9544a() {
        return f16630A;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.n.j$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static class C6525a {
        public C6525a() {
            if (Build.VERSION.SDK_INT < 14 || Build.VERSION.SDK_INT > 21) {
                C6524j.m9533c("SDK Version is" + Build.VERSION.SDK_INT + ",Webview (VERSION.SDK_INT < 14) || (VERSION.SDK_INT > 21) ,sdk maybe no support this version webview");
            }
        }

        /* renamed from: a */
        public static WebViewClient m9529a(WebView webView) {
            try {
                Object obj = C6526k.m9526a(WebView.class, Class.forName("android.webkit.CallbackProxy"), true).get(webView);
                return (WebViewClient) obj.getClass().getMethod("getWebViewClient", new Class[0]).invoke(obj, new Object[0]);
            } catch (ClassNotFoundException unused) {
                C6524j.m9531e("webview :a ClassNotFoundException");
                return null;
            } catch (IllegalAccessException unused2) {
                C6524j.m9531e("webview :a IllegalAccessException");
                return null;
            } catch (NoSuchMethodException unused3) {
                C6524j.m9531e("webview :a NoSuchMethodException");
                return null;
            } catch (SecurityException unused4) {
                C6524j.m9531e("webview :a SecurityException");
                return null;
            } catch (InvocationTargetException unused5) {
                C6524j.m9531e("webview :a InvocationTargetException");
                return null;
            }
        }

        /* renamed from: b */
        public static WebViewClient m9528b(WebView webView) {
            try {
                Object invoke = WebView.class.getMethod("getWebViewProvider", new Class[0]).invoke(webView, new Object[0]);
                return (WebViewClient) invoke.getClass().getMethod("getWebViewClient", new Class[0]).invoke(invoke, new Object[0]);
            } catch (IllegalAccessException unused) {
                C6524j.m9531e("webview :b IllegalAccessException");
                return null;
            } catch (NoSuchMethodException unused2) {
                C6524j.m9531e("webview :b NoSuchMethodException");
                return null;
            } catch (SecurityException unused3) {
                C6524j.m9531e("webview :b SecurityException");
                return null;
            } catch (InvocationTargetException e) {
                C6524j.m9531e("webview :b InvocationTargetException");
                e.printStackTrace();
                return null;
            }
        }

        /* renamed from: c */
        public static WebViewClient m9527c(WebView webView) {
            try {
                Object invoke = WebView.class.getMethod("getWebViewProvider", new Class[0]).invoke(webView, new Object[0]);
                Field declaredField = invoke.getClass().getDeclaredField("mContentsClientAdapter");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(invoke);
                return (WebViewClient) C6526k.m9526a(obj.getClass(), WebViewClient.class, true).get(obj);
            } catch (IllegalAccessException unused) {
                C6524j.m9531e("webview :c IllegalAccessException");
                return null;
            } catch (NoSuchFieldException unused2) {
                C6524j.m9531e("webview :c NoSuchFieldException");
                return null;
            } catch (NoSuchMethodException unused3) {
                C6524j.m9531e("webview :c NoSuchMethodException");
                return null;
            } catch (SecurityException unused4) {
                C6524j.m9531e("webview :c SecurityException");
                return null;
            } catch (InvocationTargetException unused5) {
                C6524j.m9531e("webview :c InvocationTargetException");
                return null;
            }
        }
    }

    /* renamed from: f */
    public static RequestMethodType m9530f(String str) {
        if (TextUtils.isEmpty(str)) {
            return RequestMethodType.GET;
        }
        if (str.toUpperCase().equals("OPTIONS")) {
            return RequestMethodType.OPTIONS;
        }
        if (str.toUpperCase().equals("GET")) {
            return RequestMethodType.GET;
        }
        if (str.toUpperCase().equals("HEAD")) {
            return RequestMethodType.HEAD;
        }
        if (str.toUpperCase().equals("POST")) {
            return RequestMethodType.POST;
        }
        if (str.toUpperCase().equals("PUT")) {
            return RequestMethodType.PUT;
        }
        if (str.toUpperCase().equals("DELETE")) {
            return RequestMethodType.DELETE;
        }
        if (str.toUpperCase().equals("TRACE")) {
            return RequestMethodType.TRACE;
        }
        return RequestMethodType.GET;
    }

    /* renamed from: a */
    public static void m9542a(WebView webView, int i, String str, String str2) {
        C6521g c6521g = new C6521g(webView, str2);
        c6521g.m9558a(m9543a(i));
        c6521g.m9557a(str);
        m9537a(str2, m9543a(i));
        InterfaceC6393e interfaceC6393e = C6638h.f17124y;
        interfaceC6393e.mo10115e("TaskQueue.webviewHttpError.put :url =  " + str2 + "====>code = " + m9543a(i));
        C6648q.m8781a(c6521g);
    }

    @TargetApi(23)
    /* renamed from: a */
    public static void m9540a(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError, String str) {
        try {
            InterfaceC6393e interfaceC6393e = C6638h.f17124y;
            interfaceC6393e.mo10122a("processErrorUp23 : " + str);
            int errorCode = webResourceError.getErrorCode();
            String charSequence = webResourceError.getDescription().toString();
            String method = webResourceRequest.getMethod();
            Map<String, String> requestHeaders = webResourceRequest.getRequestHeaders();
            String uri = webResourceRequest.getUrl().toString();
            webResourceRequest.hasGesture();
            boolean isForMainFrame = webResourceRequest.isForMainFrame();
            C6521g c6521g = new C6521g(webView, uri);
            c6521g.f16661d = isForMainFrame;
            c6521g.m9558a(m9543a(errorCode));
            c6521g.m9557a(charSequence);
            c6521g.m9556a(requestHeaders);
            c6521g.m9555b(method);
            InterfaceC6393e interfaceC6393e2 = C6638h.f17124y;
            interfaceC6393e2.mo10122a("url:" + uri + ", method:" + method + ", isForMainFrame:" + isForMainFrame);
            m9537a(uri, m9543a(errorCode));
            if (str != null && str.equals(uri)) {
                c6521g.f16661d = true;
            }
            C6648q.m8781a(c6521g);
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e3 = C6638h.f17124y;
            interfaceC6393e3.mo10115e("processErrorUp23 error:" + e.getMessage());
        }
    }

    /* renamed from: a */
    private static void m9537a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            NBSAndroidAgentImpl impl = NBSAgent.getImpl();
            HarvestConfiguration m9150m = impl != null ? impl.m9150m() : null;
            if (m9150m != null && C6652t.m8759a(str, i, m9150m.getIgnoreErrRules())) {
                i = 200;
            }
        }
        C6648q.f17233c.put(str, Integer.valueOf(i));
    }

    @TargetApi(23)
    /* renamed from: a */
    public static void m9539a(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse, String str) {
        try {
            String method = webResourceRequest.getMethod();
            Map<String, String> requestHeaders = webResourceRequest.getRequestHeaders();
            String uri = webResourceRequest.getUrl().toString();
            boolean hasGesture = webResourceRequest.hasGesture();
            boolean isForMainFrame = webResourceRequest.isForMainFrame();
            if (str != null && str.equals(uri) && !isForMainFrame) {
                InterfaceC6393e interfaceC6393e = f16633c;
                interfaceC6393e.mo10122a("filter main page: isForMainFrame: " + isForMainFrame + ",url:" + uri);
                return;
            }
            InterfaceC6393e interfaceC6393e2 = f16633c;
            interfaceC6393e2.mo10122a("processHttpErrorUp23 method:" + method + ",url:" + uri);
            InputStream data = webResourceResponse.getData();
            String encoding = webResourceResponse.getEncoding();
            String mimeType = webResourceResponse.getMimeType();
            String reasonPhrase = webResourceResponse.getReasonPhrase();
            Map<String, String> responseHeaders = webResourceResponse.getResponseHeaders();
            int statusCode = webResourceResponse.getStatusCode();
            C6522h c6522h = new C6522h(webView, uri);
            c6522h.m9552a(method);
            c6522h.m9551a(requestHeaders);
            c6522h.m9550a(hasGesture);
            c6522h.f16661d = isForMainFrame;
            c6522h.f16662e = true;
            c6522h.m9553a(data);
            c6522h.m9549b(encoding);
            c6522h.m9547c(mimeType);
            c6522h.m9546d(reasonPhrase);
            c6522h.m9548b(responseHeaders);
            c6522h.m9554a(statusCode);
            InterfaceC6393e interfaceC6393e3 = C6638h.f17124y;
            interfaceC6393e3.mo10115e("TaskQueue.webviewHttpError.put :url =  " + uri + "====>code = " + statusCode + ", isForMainFrame:" + isForMainFrame);
            m9537a(uri, statusCode);
            C6648q.m8781a(c6522h);
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e4 = C6638h.f17124y;
            interfaceC6393e4.mo10115e("processHttpErrorUp23 error:" + th.getMessage());
        }
    }

    @TargetApi(14)
    /* renamed from: a */
    public static void m9541a(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError, String str) {
        try {
            sslError.getCertificate();
            int primaryError = sslError.getPrimaryError();
            String url = sslError.getUrl();
            InterfaceC6393e interfaceC6393e = C6638h.f17124y;
            interfaceC6393e.mo10122a("processHttpErrorUp23 primaryError:" + primaryError + ",url:" + url);
            C6523i c6523i = new C6523i(webView, url);
            c6523i.m9545a(primaryError);
            if (str == null || !str.equals(url)) {
                c6523i.f16661d = false;
            }
            InterfaceC6393e interfaceC6393e2 = C6638h.f17124y;
            interfaceC6393e2.mo10115e("TaskQueue.webviewHttpError.put :url =  " + url + "====>code = 908");
            C6648q.m8781a(c6523i);
            m9537a(url, 908);
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e3 = C6638h.f17124y;
            interfaceC6393e3.mo10115e("processSslError error:" + th.getMessage());
        }
    }
}
