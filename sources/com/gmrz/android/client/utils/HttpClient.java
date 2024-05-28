package com.gmrz.android.client.utils;

import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLSocketFactory;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class HttpClient {

    /* renamed from: a */
    private static final String f10170a = "HttpClient";

    /* renamed from: b */
    private URL f10171b;

    /* renamed from: c */
    private Map<String, String> f10172c;

    /* renamed from: d */
    private Map<String, List<String>> f10173d;

    /* renamed from: e */
    private SSLSocketFactory f10174e;

    /* renamed from: f */
    private HttpMethod f10175f;

    /* renamed from: g */
    private String f10176g;

    /* renamed from: i */
    private int f10178i;

    /* renamed from: h */
    private String f10177h = "";

    /* renamed from: j */
    private boolean f10179j = true;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum HttpMethod {
        GET,
        POST,
        DELETE
    }

    public HttpClient(String str, HttpMethod httpMethod) throws MalformedURLException {
        this.f10171b = new URL(str);
        this.f10175f = httpMethod;
    }

    public HttpClient setAllowedSSLProtocols(String[] strArr) {
        if (strArr != null) {
            this.f10174e = new TlsSocketFactory(strArr);
        }
        return this;
    }

    public HttpClient addHeader(String str, String str2) {
        if (this.f10172c == null) {
            this.f10172c = new HashMap();
        }
        this.f10172c.put(str, str2);
        return this;
    }

    public HttpClient setCookies(String str) {
        return addHeader("Cookie", str);
    }

    public HttpClient setMessage(String str) {
        this.f10176g = str;
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x016e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.gmrz.android.client.utils.HttpClient sendRequest() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 370
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gmrz.android.client.utils.HttpClient.sendRequest():com.gmrz.android.client.utils.HttpClient");
    }

    public int getStatusCode() {
        return this.f10178i;
    }

    public String getHeader(String str, int i) {
        List<String> list;
        Map<String, List<String>> map = this.f10173d;
        if (map == null || (list = map.get(str)) == null || list.size() <= i) {
            return null;
        }
        return list.get(i);
    }

    public List<String> getHeader(String str) {
        Map<String, List<String>> map = this.f10173d;
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    public String getResponse() {
        return this.f10177h;
    }

    public HttpClient setFollowRedirects(boolean z) {
        this.f10179j = z;
        return this;
    }
}
