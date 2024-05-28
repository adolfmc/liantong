package com.megvii.lv5;

import com.networkbench.agent.impl.instrumentation.NBSInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLSocketFactory;

/* compiled from: Proguard */
@NBSInstrumented
/* renamed from: com.megvii.lv5.x4 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5655x4 implements InterfaceC5640w4 {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.x4$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC5656a {
    }

    public C5655x4() {
        this(null);
    }

    public C5655x4(InterfaceC5656a interfaceC5656a) {
        this(null, null);
    }

    public C5655x4(InterfaceC5656a interfaceC5656a, SSLSocketFactory sSLSocketFactory) {
    }

    /* renamed from: a */
    public static void m12894a(HttpURLConnection httpURLConnection, AbstractC5652x3<?> abstractC5652x3, byte[] bArr) {
        httpURLConnection.setDoOutput(true);
        httpURLConnection.addRequestProperty("Content-Type", abstractC5652x3.mo12870c());
        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
        dataOutputStream.write(bArr);
        dataOutputStream.close();
    }

    /* renamed from: a */
    public C5536p4 m12895a(AbstractC5652x3<?> abstractC5652x3, Map<String, String> map) {
        String str;
        InputStream errorStream;
        String str2 = abstractC5652x3.f13904c;
        HashMap hashMap = new HashMap();
        hashMap.putAll(abstractC5652x3.mo12896d());
        hashMap.putAll(map);
        URL url = new URL(str2);
        HttpURLConnection httpURLConnection = (HttpURLConnection) NBSInstrumentation.openConnection(url.openConnection());
        httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        int i = abstractC5652x3.f13911j.f13171a;
        httpURLConnection.setConnectTimeout(i);
        httpURLConnection.setReadTimeout(i);
        httpURLConnection.setUseCaches(false);
        boolean z = true;
        httpURLConnection.setDoInput(true);
        "https".equals(url.getProtocol());
        for (String str3 : hashMap.keySet()) {
            httpURLConnection.addRequestProperty(str3, (String) hashMap.get(str3));
        }
        switch (abstractC5652x3.f13903b) {
            case -1:
                byte[] mo12869e = abstractC5652x3.mo12869e();
                if (mo12869e != null) {
                    httpURLConnection.setRequestMethod("POST");
                    m12894a(httpURLConnection, abstractC5652x3, mo12869e);
                    break;
                }
                break;
            case 0:
                httpURLConnection.setRequestMethod("GET");
                break;
            case 1:
                httpURLConnection.setRequestMethod("POST");
                byte[] mo12871b = abstractC5652x3.mo12871b();
                if (mo12871b != null) {
                    m12894a(httpURLConnection, abstractC5652x3, mo12871b);
                    break;
                }
                break;
            case 2:
                httpURLConnection.setRequestMethod("PUT");
                byte[] mo12871b2 = abstractC5652x3.mo12871b();
                if (mo12871b2 != null) {
                    m12894a(httpURLConnection, abstractC5652x3, mo12871b2);
                    break;
                }
                break;
            case 3:
                str = "DELETE";
                httpURLConnection.setRequestMethod(str);
                break;
            case 4:
                str = "HEAD";
                httpURLConnection.setRequestMethod(str);
                break;
            case 5:
                str = "OPTIONS";
                httpURLConnection.setRequestMethod(str);
                break;
            case 6:
                str = "TRACE";
                httpURLConnection.setRequestMethod(str);
                break;
            case 7:
                httpURLConnection.setRequestMethod("PATCH");
                byte[] mo12871b3 = abstractC5652x3.mo12871b();
                if (mo12871b3 != null) {
                    m12894a(httpURLConnection, abstractC5652x3, mo12871b3);
                    break;
                }
                break;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
        C5491k4 c5491k4 = new C5491k4("HTTP", 1, 1);
        if (httpURLConnection.getResponseCode() != -1) {
            C5544q4 c5544q4 = new C5544q4(c5491k4, httpURLConnection.getResponseCode(), httpURLConnection.getResponseMessage());
            C5536p4 c5536p4 = new C5536p4(c5544q4);
            int i2 = abstractC5652x3.f13903b;
            int i3 = c5544q4.f13200b;
            if ((i2 == 4 || (100 <= i3 && i3 < 200) || i3 == 204 || i3 == 304) ? false : false) {
                C5520n4 c5520n4 = new C5520n4();
                try {
                    errorStream = httpURLConnection.getInputStream();
                } catch (IOException unused) {
                    errorStream = httpURLConnection.getErrorStream();
                }
                c5520n4.f13066c = errorStream;
                c5520n4.f13067d = httpURLConnection.getContentLength();
                String contentEncoding = httpURLConnection.getContentEncoding();
                c5520n4.f13065b = contentEncoding != null ? new C5529o4("Content-Encoding", contentEncoding) : null;
                String contentType = httpURLConnection.getContentType();
                c5520n4.f13064a = contentType != null ? new C5529o4("Content-Type", contentType) : null;
                c5536p4.f13179f = c5520n4;
            }
            for (Map.Entry<String, List<String>> entry : httpURLConnection.getHeaderFields().entrySet()) {
                if (entry.getKey() != null) {
                    C5529o4 c5529o4 = new C5529o4(entry.getKey(), entry.getValue().get(0));
                    C5550r4 c5550r4 = c5536p4.f13174a;
                    c5550r4.getClass();
                    c5550r4.f13247a.add(c5529o4);
                }
            }
            return c5536p4;
        }
        throw new IOException("Could not retrieve response code from HttpUrlConnection.");
    }
}
