package p001a.p058b.p062b.p063a.p064a.p067c.p070c;

import com.baidu.uaq.agent.android.UAQ;
import com.baidu.uaq.agent.android.customtransmission.APMUploadConfigure;
import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import com.networkbench.agent.impl.instrumentation.NBSInstrumentationHttpClient;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import p001a.p002a.p003a.p004a.outline;
import p001a.p058b.p062b.p063a.p064a.C0719b;
import p001a.p058b.p062b.p063a.p064a.p067c.C0734b;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0726c;
import p001a.p058b.p062b.p063a.p064a.p067c.p069b.C0735a;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;
import p001a.p058b.p062b.p063a.p064a.p075g.C0760a;
import p001a.p058b.p062b.p063a.p064a.p075g.EnumC0761b;
import p001a.p058b.p062b.p063a.p064a.p081k.C0768a;
import p001a.p058b.p062b.p063a.p064a.p081k.C0771c;
import p001a.p058b.p062b.p063a.p064a.p081k.C0773e;
import p001a.p058b.p062b.p063a.p064a.p081k.C0775g;

@NBSInstrumented
/* renamed from: a.b.b.a.a.c.c.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0739b {

    /* renamed from: a */
    public static final InterfaceC3321a f2259a = C0749a.f2299a;

    /* renamed from: b */
    public static final UAQ f2260b = UAQ.getInstance();

    /* renamed from: c */
    public HttpClient f2261c;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.lang.String] */
    /* renamed from: a */
    public static String m22292a(HttpResponse httpResponse) {
        char[] cArr = new char[8192];
        StringBuilder sb = new StringBuilder();
        if (httpResponse == null || httpResponse.getEntity() == null) {
            return null;
        }
        ?? content = httpResponse.getEntity().getContent();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content));
        while (true) {
            try {
                int read = bufferedReader.read(cArr);
                if (read < 0) {
                    content.close();
                    content = sb.toString();
                    return content;
                }
                sb.append(cArr, 0, read);
            } catch (Exception unused) {
                content.close();
                return sb.toString();
            }
        }
    }

    /* renamed from: a */
    public C0734b m22293a(String str, APMUploadConfigure aPMUploadConfigure) {
        if (str != null) {
            C0775g.m22231a("MultiHarvest sendData = " + str);
            String replace = str.replace("\\/", "/");
            String url = aPMUploadConfigure.getUrl();
            InterfaceC3321a interfaceC3321a = f2259a;
            interfaceC3321a.mo17428D("MultiHarvest POST <uri> = " + url);
            HttpPost httpPost = new HttpPost(url);
            HashMap<String, String> headerMap = aPMUploadConfigure.getHeaderMap();
            if (headerMap.size() <= 0) {
                f2259a.error("Http header is null");
            }
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
            if (f2260b.getConfig().getAPIKey() == null) {
                f2259a.error("Cannot create POST without an Application Token.");
                httpPost = null;
            } else {
                httpPost.setEntity(new ByteArrayEntity(C0768a.m22244a(replace)));
            }
            if (httpPost != null) {
                long contentLength = httpPost.getEntity().getContentLength();
                InterfaceC3321a interfaceC3321a2 = f2259a;
                interfaceC3321a2.mo17428D("HarvestSize = " + contentLength + "bytes");
                C0760a.f2350a.m22251a("Supportability/AgentHealth/Collector/HarvestSize", ((float) contentLength) / 1024.0f);
            }
            return m22291a(httpPost);
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: a */
    public final void m22295a() {
        int convert = (int) TimeUnit.MILLISECONDS.convert(20L, TimeUnit.SECONDS);
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, convert);
        HttpConnectionParams.setSoTimeout(basicHttpParams, convert);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, 8192);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        this.f2261c = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
    }

    /* renamed from: a */
    public final C0734b m22291a(HttpPost httpPost) {
        long j;
        if (httpPost == null) {
            f2259a.error("Failed to send POST to collector");
            return null;
        }
        C0734b c0734b = new C0734b();
        try {
            EnumC0761b enumC0761b = EnumC0761b.STARTED;
            long currentTimeMillis = System.currentTimeMillis();
            if (this.f2261c == null) {
                m22295a();
            }
            HttpClient httpClient = this.f2261c;
            HttpResponse execute = !(httpClient instanceof HttpClient) ? httpClient.execute(httpPost) : NBSInstrumentationHttpClient.execute(httpClient, httpPost);
            long currentTimeMillis2 = System.currentTimeMillis();
            if (enumC0761b == EnumC0761b.STARTED) {
                EnumC0761b enumC0761b2 = EnumC0761b.STOPPED;
                j = currentTimeMillis2 - currentTimeMillis;
            } else {
                j = -1;
            }
            c0734b.f2243c = j;
            c0734b.f2241a = execute.getStatusLine().getStatusCode();
            try {
                c0734b.f2242b = m22292a(execute);
            } catch (IOException e) {
                f2259a.mo17426a("Failed to retrieve collector response: ", e);
            }
            return c0734b;
        } catch (Exception e2) {
            f2259a.mo17426a("Failed to send POST to collector: ", e2);
            C0760a c0760a = C0760a.f2350a;
            StringBuilder m24437a = outline.m24437a("Supportability/AgentHealth/Collector/ResponseErrorCodes/");
            m24437a.append(C0773e.m22239a(e2));
            c0760a.m22252a(m24437a.toString());
            return null;
        }
    }

    /* renamed from: a */
    public C0734b m22294a(String str) {
        String str2;
        String m22240a;
        if (str != null) {
            C0775g.m22231a("MultiHarvest sendData = " + str);
            String str3 = str.replace("\\/", "/") + "\n";
            String collectorPath = f2260b.getConfig().getCollectorPath();
            String str4 = f2260b.getConfig().isUseSsl() ? "https://" : "http://";
            StringBuilder m24437a = outline.m24437a(":");
            m24437a.append(f2260b.getConfig().getCollectorPort());
            String sb = m24437a.toString();
            StringBuilder m24437a2 = outline.m24437a(str4);
            m24437a2.append(f2260b.getConfig().getCollectorHost());
            m24437a2.append(sb);
            m24437a2.append(collectorPath);
            String sb2 = m24437a2.toString();
            f2259a.mo17428D("MultiHarvest POST <uri> = " + sb2);
            HttpPost httpPost = new HttpPost(sb2);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Content-Encoding", "gzip");
            if (System.getProperty("http.agent") != null) {
                httpPost.addHeader("User-Agent", System.getProperty("http.agent").trim());
            }
            httpPost.addHeader("X-UAQ-WanType", C0719b.m22326a().mo17440g());
            try {
                String cuid = f2260b.getConfig().getCuid();
                if (cuid.equals("null")) {
                    C0726c mo17442e = C0719b.m22326a().mo17442e();
                    if (mo17442e.f2221o) {
                        m22240a = mo17442e.f2215i;
                    } else {
                        m22240a = C0771c.m22240a(mo17442e.f2215i);
                    }
                    str2 = "X-UAQ-UUID";
                } else {
                    str2 = "X-UAQ-UUID";
                    m22240a = C0771c.m22240a(cuid);
                }
                httpPost.addHeader(str2, m22240a);
                httpPost.addHeader("X-UAQ-Channel", C0771c.m22240a(f2260b.getConfig().getChannel()));
            } catch (Exception e) {
                f2259a.mo17426a("Caught error while createPost AES: ", e);
                C0735a.m22302a(e);
            }
            if (f2260b.getConfig().getAPIKey() == null) {
                f2259a.error("Cannot create POST without an Application Token.");
                httpPost = null;
            } else {
                httpPost.addHeader("X-App-License-Key", f2260b.getConfig().getAPIKey());
                httpPost.setEntity(new ByteArrayEntity(C0768a.m22243a(str3, "utf-8")));
            }
            if (httpPost != null) {
                long contentLength = httpPost.getEntity().getContentLength();
                f2259a.mo17428D("HarvestSize = " + contentLength + "bytes");
                C0760a.f2350a.m22251a("Supportability/AgentHealth/Collector/HarvestSize", ((float) contentLength) / 1024.0f);
            }
            return m22291a(httpPost);
        }
        throw new IllegalArgumentException();
    }
}
