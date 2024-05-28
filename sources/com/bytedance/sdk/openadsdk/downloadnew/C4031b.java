package com.bytedance.sdk.openadsdk.downloadnew;

import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.p319ss.android.socialbase.downloader.model.HttpHeader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.bytedance.sdk.openadsdk.downloadnew.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4031b {
    /* renamed from: mb */
    public static HttpURLConnection m16419mb(String str, Map<String, String> map) {
        HttpURLConnection httpURLConnection;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) NBSInstrumentation.openConnection(new URL(str).openConnection());
            try {
                httpURLConnection2.setInstanceFollowRedirects(false);
                httpURLConnection2.setRequestProperty("accept", "*/*");
                httpURLConnection2.setRequestProperty("connection", "Keep-Alive");
                if (map != null && !map.isEmpty()) {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        httpURLConnection2.setRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                httpURLConnection2.connect();
                int responseCode = httpURLConnection2.getResponseCode();
                return ((responseCode < 200 || responseCode >= 300) && responseCode >= 300 && responseCode < 400) ? m16419mb(httpURLConnection2.getHeaderField("Location"), map) : httpURLConnection2;
            } catch (Exception unused) {
                return httpURLConnection;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    /* renamed from: mb */
    public static Map<String, String> m16418mb(HttpURLConnection httpURLConnection) {
        HashMap hashMap = new HashMap();
        int size = httpURLConnection.getHeaderFields().size();
        for (int i = 0; i < size; i++) {
            hashMap.put(httpURLConnection.getHeaderFieldKey(i), httpURLConnection.getHeaderField(i));
        }
        return hashMap;
    }

    /* renamed from: mb */
    public static C4032mb m16420mb(String str, List<HttpHeader> list) throws IOException {
        int responseCode;
        HashMap hashMap = new HashMap();
        if (list != null && !list.isEmpty()) {
            for (HttpHeader httpHeader : list) {
                hashMap.put(httpHeader.getName(), httpHeader.getValue());
            }
        }
        HttpURLConnection m16419mb = m16419mb(str, hashMap);
        if (m16419mb != null && (responseCode = m16419mb.getResponseCode()) >= 200 && responseCode < 300) {
            Map<String, String> m16418mb = m16418mb(m16419mb);
            InputStream inputStream = m16419mb.getInputStream();
            String contentEncoding = m16419mb.getContentEncoding();
            if (!TextUtils.isEmpty(contentEncoding) && contentEncoding.contains("gzip")) {
                inputStream = new GZIPInputStream(inputStream);
            }
            return new C4032mb(inputStream, m16418mb, responseCode, m16419mb);
        }
        return null;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.sdk.openadsdk.downloadnew.b$mb */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C4032mb {

        /* renamed from: b */
        public int f9626b;

        /* renamed from: hj */
        public HttpURLConnection f9627hj;

        /* renamed from: mb */
        public InputStream f9628mb;

        /* renamed from: ox */
        public Map<String, String> f9629ox;

        public C4032mb(InputStream inputStream, Map<String, String> map, int i, HttpURLConnection httpURLConnection) {
            this.f9628mb = inputStream;
            this.f9629ox = map;
            this.f9626b = i;
            this.f9627hj = httpURLConnection;
        }
    }
}
