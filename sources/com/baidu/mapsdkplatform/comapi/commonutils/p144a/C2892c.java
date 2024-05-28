package com.baidu.mapsdkplatform.comapi.commonutils.p144a;

import android.text.TextUtils;
import com.baidu.mapapi.http.HttpClient;
import com.networkbench.agent.impl.instrumentation.NBSInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.mapsdkplatform.comapi.commonutils.a.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2892c {

    /* renamed from: d */
    private InterfaceC2895c f7151d;

    /* renamed from: b */
    private int f7149b = 1;

    /* renamed from: c */
    private List<RunnableC2890b> f7150c = new LinkedList();

    /* renamed from: a */
    private ExecutorService f7148a = Executors.newCachedThreadPool();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapsdkplatform.comapi.commonutils.a.c$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2893a implements InterfaceC2894b {

        /* renamed from: b */
        private File f7153b;

        private C2893a(File file) {
            this.f7153b = file;
        }

        @Override // com.baidu.mapsdkplatform.comapi.commonutils.p144a.C2892c.InterfaceC2894b
        /* renamed from: a */
        public void mo18461a(RunnableC2890b runnableC2890b) {
            if (C2892c.this.f7150c == null || C2892c.this.f7150c.size() == 0 || runnableC2890b == null || !runnableC2890b.m18468a() || C2892c.this.f7150c == null) {
                return;
            }
            C2892c.this.f7150c.remove(runnableC2890b);
            if (C2892c.this.f7150c.size() == 0) {
                C2892c.this.f7151d.mo18236a(this.f7153b);
            }
        }

        @Override // com.baidu.mapsdkplatform.comapi.commonutils.p144a.C2892c.InterfaceC2894b
        /* renamed from: b */
        public void mo18460b(RunnableC2890b runnableC2890b) {
            if (C2892c.this.f7150c == null || C2892c.this.f7150c.size() == 0 || runnableC2890b == null) {
                return;
            }
            C2892c.this.f7150c.clear();
            if (C2892c.this.f7151d != null) {
                C2892c.this.f7151d.mo18237a();
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapsdkplatform.comapi.commonutils.a.c$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC2894b {
        /* renamed from: a */
        void mo18461a(RunnableC2890b runnableC2890b);

        /* renamed from: b */
        void mo18460b(RunnableC2890b runnableC2890b);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapsdkplatform.comapi.commonutils.a.c$c */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC2895c {
        /* renamed from: a */
        void mo18237a();

        /* renamed from: a */
        void mo18236a(File file);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapsdkplatform.comapi.commonutils.a.c$d */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2896d implements HostnameVerifier {
        C2896d() {
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return HttpsURLConnection.getDefaultHostnameVerifier().verify(str, sSLSession);
        }
    }

    /* renamed from: a */
    private HttpURLConnection m18466a(String str) {
        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(str);
            if (HttpClient.isHttpsEnable) {
                httpURLConnection = (HttpsURLConnection) NBSInstrumentation.openConnection(url.openConnection());
                ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(new C2896d());
            } else {
                httpURLConnection = (HttpURLConnection) NBSInstrumentation.openConnection(url.openConnection());
            }
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            return httpURLConnection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private void m18465a(String str, File file, int i, int i2) {
        if (TextUtils.isEmpty(str) || file == null || i <= 0 || i2 <= 0) {
            return;
        }
        int i3 = 0;
        while (i3 < this.f7149b) {
            int i4 = i3 + 1;
            C2889a c2889a = new C2889a(i3, i3 * i, (i4 * i) - 1);
            if (i3 == this.f7149b - 1) {
                c2889a.m18470a(i2);
            }
            RunnableC2890b runnableC2890b = new RunnableC2890b(str, file, c2889a, new C2893a(file));
            List<RunnableC2890b> list = this.f7150c;
            if (list != null) {
                list.add(runnableC2890b);
            }
            ExecutorService executorService = this.f7148a;
            if (executorService != null && !executorService.isShutdown()) {
                this.f7148a.submit(runnableC2890b);
            }
            i3 = i4;
        }
    }

    /* renamed from: a */
    private void m18464a(String str, String str2, String str3) {
        String headerField;
        File file = new File(str2);
        if (file.exists() || file.mkdirs()) {
            HttpURLConnection m18466a = m18466a(str);
            if (m18466a != null) {
                try {
                    if (m18466a.getResponseCode() == 200) {
                        int contentLength = m18466a.getContentLength();
                        if (contentLength <= 0) {
                            throw new RuntimeException("unKnow file length");
                        }
                        if (str3 == null && ((headerField = m18466a.getHeaderField("Content-Disposition")) == null || headerField.length() == 0 || (str3 = URLDecoder.decode(headerField.substring(headerField.indexOf("filename=") + 9), "UTF-8")) == null || str3.length() == 0)) {
                            return;
                        }
                        File file2 = new File(file, str3);
                        RandomAccessFile randomAccessFile = new RandomAccessFile(file2, "rw");
                        randomAccessFile.setLength(contentLength);
                        randomAccessFile.close();
                        m18466a.disconnect();
                        int i = this.f7149b;
                        m18465a(str, file2, contentLength % i == 0 ? contentLength / i : (contentLength / i) + 1, contentLength);
                        return;
                    }
                } catch (Exception unused) {
                    return;
                }
            }
            throw new RuntimeException("server no response.");
        }
    }

    /* renamed from: a */
    public void m18463a(String str, String str2, String str3, int i, InterfaceC2895c interfaceC2895c) {
        if (i <= 0 || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        this.f7149b = i;
        this.f7151d = interfaceC2895c;
        m18464a(str, str2, str3);
    }
}
