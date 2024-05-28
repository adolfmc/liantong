package com.tydic.tydic_tracker.tyWebview;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSWebViewClient;
import com.tydic.tydic_tracker.app.TYApplication;
import com.tydic.tydic_tracker.app.TYUtil;
import com.tydic.tydic_tracker.tydicDB.TyResourceEntity;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;

@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class TyWebViewClient extends NBSWebViewClient {
    public String domain;

    /* renamed from: ft */
    public long f20032ft;

    /* renamed from: le */
    public long f20033le;
    public String link_url;
    public String request_id;
    public String resource_time;

    /* renamed from: rs */
    public long f20034rs;
    public String ses_id;
    public long timestamp;
    public String uri;
    private final String MARKER = "AJAXINTERCEPT";
    private JSONArray resourceArray = new JSONArray();
    private JSONArray httpArray = new JSONArray();
    private Map<String, String> ajaxRequestContents = new HashMap();

    public TyWebViewClient(WebView webView) {
        webView.addJavascriptInterface(new AjaxInterceptJavascriptInterface(this), "interception");
    }

    @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        Tracker.onPageFinished(this, webView, str);
        this.f20033le = TYUtil.getCurrTime();
        this.f20032ft = this.f20033le - this.f20034rs;
        Log.i("tyLog", "页面渲染耗时：" + String.valueOf(this.f20032ft));
        StringBuilder sb = new StringBuilder();
        sb.append("请求资源花费：");
        JSONArray jSONArray = this.resourceArray;
        sb.append(!(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray));
        Log.i("tyLog", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("请求HTTP花费：");
        JSONArray jSONArray2 = this.httpArray;
        sb2.append(!(jSONArray2 instanceof JSONArray) ? jSONArray2.toString() : NBSJSONArrayInstrumentation.toString(jSONArray2));
        Log.i("tyLog", sb2.toString());
        TYApplication.WebviewResource(new TyResourceEntity(TYApplication.ses_id, TYUtil.getCurrTime(), TYUtil.getRandom(), str, this.domain, this.f20032ft, this.uri, this.f20034rs, this.f20033le, this.resource_time));
        super.onPageFinished(webView, str);
    }

    @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        Tracker.onPageStarted(this, webView, str, bitmap);
        this.link_url = str;
        this.f20034rs = TYUtil.getCurrTime();
        super.onPageStarted(webView, str, bitmap);
    }

    @RequiresApi(api = 21)
    public WebResourceResponse shouldInterceptRequest(WebView webView, WriteHandlingWebResourceRequest writeHandlingWebResourceRequest) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) NBSInstrumentation.openConnection(new URL(writeHandlingWebResourceRequest.getUrl().toString()).openConnection());
            httpURLConnection.setRequestMethod(writeHandlingWebResourceRequest.getMethod());
            if ("POST".equals(writeHandlingWebResourceRequest.getMethod())) {
                OutputStream outputStream = httpURLConnection.getOutputStream();
                try {
                    outputStream.write(writeHandlingWebResourceRequest.getAjaxData().getBytes("UTF-8"));
                    outputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return new WebResourceResponse(httpURLConnection.getContentType(), httpURLConnection.getContentEncoding() != null ? httpURLConnection.getContentEncoding() : Charset.defaultCharset().displayName(), new ByteArrayInputStream(Utils.consumeInputStream(httpURLConnection.getInputStream())));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0104 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0105  */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v9 */
    @Override // android.webkit.WebViewClient
    @android.support.annotation.RequiresApi(api = 21)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.webkit.WebResourceResponse shouldInterceptRequest(android.webkit.WebView r29, android.webkit.WebResourceRequest r30) {
        /*
            Method dump skipped, instructions count: 270
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tydic.tydic_tracker.tyWebview.TyWebViewClient.shouldInterceptRequest(android.webkit.WebView, android.webkit.WebResourceRequest):android.webkit.WebResourceResponse");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addAjaxRequest(String str, String str2) {
        this.ajaxRequestContents.put(str, str2);
    }

    @RequiresApi(api = 21)
    private String getRequestBody(WebResourceRequest webResourceRequest) {
        return getAjaxRequestBodyByID(getAjaxRequestID(webResourceRequest));
    }

    @RequiresApi(api = 21)
    private boolean isAjaxRequest(WebResourceRequest webResourceRequest) {
        return webResourceRequest.getUrl().toString().contains("AJAXINTERCEPT");
    }

    @RequiresApi(api = 21)
    private String[] getUrlSegments(WebResourceRequest webResourceRequest, String str) {
        return webResourceRequest.getUrl().toString().split(str);
    }

    @RequiresApi(api = 21)
    private String getAjaxRequestID(WebResourceRequest webResourceRequest) {
        return getUrlSegments(webResourceRequest, "AJAXINTERCEPT")[1];
    }

    @RequiresApi(api = 21)
    private Uri getOriginalRequestUri(WebResourceRequest webResourceRequest, String str) {
        return Uri.parse(getUrlSegments(webResourceRequest, str)[0]);
    }

    private String getAjaxRequestBodyByID(String str) {
        String str2 = this.ajaxRequestContents.get(str);
        this.ajaxRequestContents.remove(str);
        return str2;
    }

    private WebResourceResponse injectIntercept(WebResourceResponse webResourceResponse, Context context) {
        String encoding = webResourceResponse.getEncoding();
        String mimeType = webResourceResponse.getMimeType();
        if (mimeType.contains("text/html")) {
            mimeType = "text/html";
        }
        return new WebResourceResponse(mimeType, encoding, injectInterceptToStream(context, webResourceResponse.getData(), mimeType, encoding));
    }

    private InputStream injectInterceptToStream(Context context, InputStream inputStream, String str, String str2) {
        try {
            byte[] consumeInputStream = Utils.consumeInputStream(inputStream);
            if (str.contains("text/html")) {
                consumeInputStream = AjaxInterceptJavascriptInterface.enableIntercept(context, consumeInputStream).getBytes(str2);
            }
            return new ByteArrayInputStream(consumeInputStream);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
