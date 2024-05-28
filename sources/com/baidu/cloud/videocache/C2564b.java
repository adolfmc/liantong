package com.baidu.cloud.videocache;

import android.text.TextUtils;
import com.baidu.cloud.videocache.headers.HeaderInjector;
import com.baidu.cloud.videocache.sourcestorage.SourceInfoStorage;
import com.networkbench.agent.impl.instrumentation.NBSInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.cloud.videocache.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2564b implements Source {

    /* renamed from: c */
    private static final Logger f4858c = LoggerFactory.getLogger("HttpUrlSource");

    /* renamed from: a */
    protected final SourceInfoStorage f4859a;

    /* renamed from: b */
    protected C2577m f4860b;

    /* renamed from: d */
    private final HeaderInjector f4861d;

    /* renamed from: e */
    private HttpURLConnection f4862e;

    /* renamed from: f */
    private InputStream f4863f;

    public C2564b(C2564b c2564b) {
        this.f4860b = c2564b.f4860b;
        this.f4859a = c2564b.f4859a;
        this.f4861d = c2564b.f4861d;
    }

    public C2564b(String str) {
        this(str, com.baidu.cloud.videocache.sourcestorage.rwd.m19727a());
    }

    public C2564b(String str, SourceInfoStorage sourceInfoStorage) {
        this(str, sourceInfoStorage, new com.baidu.cloud.videocache.headers.oia());
    }

    public C2564b(String str, SourceInfoStorage sourceInfoStorage, HeaderInjector headerInjector) {
        this.f4859a = (SourceInfoStorage) C2571g.m19807a(sourceInfoStorage);
        this.f4861d = (HeaderInjector) C2571g.m19807a(headerInjector);
        C2577m c2577m = sourceInfoStorage.get(str);
        this.f4860b = c2577m == null ? new C2577m(str, -2147483648L, C2576l.m19784a(str)) : c2577m;
    }

    /* renamed from: a */
    private long m19836a(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("Content-Length");
        if (headerField == null) {
            return -1L;
        }
        return Long.parseLong(headerField);
    }

    /* renamed from: a */
    private long m19835a(HttpURLConnection httpURLConnection, long j, int i) {
        long m19836a = m19836a(httpURLConnection);
        return i == 200 ? m19836a : i == 206 ? m19836a + j : this.f4860b.f4897b;
    }

    /* renamed from: a */
    private HttpURLConnection m19838a(long j, int i) {
        String str;
        HttpURLConnection httpURLConnection;
        boolean z;
        String str2 = this.f4860b.f4896a;
        int i2 = 0;
        do {
            Logger logger = f4858c;
            StringBuilder sb = new StringBuilder();
            sb.append("Open connection ");
            int i3 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i3 > 0) {
                str = " with offset " + j;
            } else {
                str = "";
            }
            sb.append(str);
            sb.append(" to ");
            sb.append(str2);
            logger.debug(sb.toString());
            httpURLConnection = (HttpURLConnection) NBSInstrumentation.openConnection(new URL(str2).openConnection());
            httpURLConnection.setUseCaches(false);
            m19834a(httpURLConnection, str2);
            if (i3 > 0) {
                httpURLConnection.setRequestProperty("Range", "bytes=" + j + "-");
            }
            if (i > 0) {
                httpURLConnection.setConnectTimeout(i);
                httpURLConnection.setReadTimeout(i);
            }
            int responseCode = httpURLConnection.getResponseCode();
            z = responseCode == 301 || responseCode == 302 || responseCode == 303;
            if (z) {
                str2 = httpURLConnection.getHeaderField("Location");
                i2++;
                httpURLConnection.disconnect();
            }
            if (i2 > 5) {
                throw new C2574j("Too many redirects: " + i2);
            }
        } while (z);
        return httpURLConnection;
    }

    /* renamed from: a */
    private void m19834a(HttpURLConnection httpURLConnection, String str) {
        for (Map.Entry entry : this.f4861d.addHeaders(str).entrySet()) {
            httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
        }
    }

    /* renamed from: b */
    private HttpURLConnection m19832b(long j, long j2, int i) {
        HttpURLConnection httpURLConnection;
        String str;
        boolean z;
        String str2 = this.f4860b.f4896a;
        long j3 = j;
        long j4 = j2;
        int i2 = 0;
        do {
            httpURLConnection = (HttpURLConnection) NBSInstrumentation.openConnection(new URL(str2).openConnection());
            StringBuilder sb = new StringBuilder();
            if (j3 > 0) {
                sb.append("bytes=");
                sb.append(j3);
                sb.append("-");
            }
            if (j4 != -1 && j4 > j3) {
                if (sb.length() <= 0) {
                    sb.append("bytes=0-");
                }
                sb.append(j4);
            }
            if (sb.length() > 0) {
                httpURLConnection.setRequestProperty("Range", sb.toString());
            }
            Logger logger = f4858c;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Open connection ");
            if (sb.length() > 0) {
                str = " with range " + sb.toString();
            } else {
                str = "";
            }
            sb2.append(str);
            sb2.append(" to ");
            sb2.append(str2);
            logger.debug(sb2.toString());
            if (i > 0) {
                httpURLConnection.setConnectTimeout(i);
                httpURLConnection.setReadTimeout(i);
            }
            int responseCode = httpURLConnection.getResponseCode();
            z = responseCode == 301 || responseCode == 302 || responseCode == 303;
            if (z) {
                str2 = httpURLConnection.getHeaderField("Location");
                i2++;
                httpURLConnection.disconnect();
            }
            if (responseCode == 416) {
                httpURLConnection.disconnect();
                z = true;
                j4 = -1;
                j3 = 0;
            }
            if (i2 > 5) {
                throw new C2574j("Too many redirects: " + i2);
            }
        } while (z);
        return httpURLConnection;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0094  */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m19831c() {
        /*
            r7 = this;
            org.slf4j.Logger r0 = com.baidu.cloud.videocache.C2564b.f4858c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Read content info from "
            r1.append(r2)
            com.baidu.cloud.videocache.m r2 = r7.f4860b
            java.lang.String r2 = r2.f4896a
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.debug(r1)
            r0 = 0
            r2 = 10000(0x2710, float:1.4013E-41)
            r3 = 0
            java.net.HttpURLConnection r0 = r7.m19838a(r0, r2)     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L6a
            long r1 = r7.m19836a(r0)     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            java.lang.String r4 = r0.getContentType()     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            java.io.InputStream r3 = r0.getInputStream()     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            com.baidu.cloud.videocache.m r5 = new com.baidu.cloud.videocache.m     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            com.baidu.cloud.videocache.m r6 = r7.f4860b     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            java.lang.String r6 = r6.f4896a     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            r5.<init>(r6, r1, r4)     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            r7.f4860b = r5     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            com.baidu.cloud.videocache.sourcestorage.SourceInfoStorage r1 = r7.f4859a     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            com.baidu.cloud.videocache.m r2 = r7.f4860b     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            java.lang.String r2 = r2.f4896a     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            com.baidu.cloud.videocache.m r4 = r7.f4860b     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            r1.put(r2, r4)     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            org.slf4j.Logger r1 = com.baidu.cloud.videocache.C2564b.f4858c     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            r2.<init>()     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            java.lang.String r4 = "Source info fetched: "
            r2.append(r4)     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            com.baidu.cloud.videocache.m r4 = r7.f4860b     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            r2.append(r4)     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            r1.debug(r2)     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            com.baidu.cloud.videocache.C2576l.m19785a(r3)
            if (r0 == 0) goto L8e
            goto L8b
        L63:
            r1 = move-exception
            goto L8f
        L65:
            r1 = move-exception
            goto L6c
        L67:
            r1 = move-exception
            r0 = r3
            goto L8f
        L6a:
            r1 = move-exception
            r0 = r3
        L6c:
            org.slf4j.Logger r2 = com.baidu.cloud.videocache.C2564b.f4858c     // Catch: java.lang.Throwable -> L63
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L63
            r4.<init>()     // Catch: java.lang.Throwable -> L63
            java.lang.String r5 = "Error fetching info from "
            r4.append(r5)     // Catch: java.lang.Throwable -> L63
            com.baidu.cloud.videocache.m r5 = r7.f4860b     // Catch: java.lang.Throwable -> L63
            java.lang.String r5 = r5.f4896a     // Catch: java.lang.Throwable -> L63
            r4.append(r5)     // Catch: java.lang.Throwable -> L63
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L63
            r2.error(r4, r1)     // Catch: java.lang.Throwable -> L63
            com.baidu.cloud.videocache.C2576l.m19785a(r3)
            if (r0 == 0) goto L8e
        L8b:
            r0.disconnect()
        L8e:
            return
        L8f:
            com.baidu.cloud.videocache.C2576l.m19785a(r3)
            if (r0 == 0) goto L97
            r0.disconnect()
        L97:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.cloud.videocache.C2564b.m19831c():void");
    }

    /* renamed from: a */
    public synchronized String m19839a() {
        if (TextUtils.isEmpty(this.f4860b.f4898c)) {
            m19831c();
        }
        return this.f4860b.f4898c;
    }

    /* renamed from: a */
    public void m19837a(long j, long j2, int i) {
        try {
            this.f4862e = m19832b(j, j2, i);
            String contentType = this.f4862e.getContentType();
            this.f4863f = new BufferedInputStream(this.f4862e.getInputStream(), 8192);
            this.f4860b = new C2577m(this.f4860b.f4896a, m19835a(this.f4862e, j, this.f4862e.getResponseCode()), contentType);
            this.f4859a.put(this.f4860b.f4896a, this.f4860b);
        } catch (IOException e) {
            throw new C2574j("Error opening connection for " + this.f4860b.f4896a + " with offset " + j, e);
        }
    }

    /* renamed from: b */
    public String m19833b() {
        return this.f4860b.f4896a;
    }

    @Override // com.baidu.cloud.videocache.Source
    public void close() {
        HttpURLConnection httpURLConnection = this.f4862e;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (ArrayIndexOutOfBoundsException e) {
                f4858c.error("Error closing connection correctly. Should happen only on Android L. ", (Throwable) e);
            } catch (IllegalArgumentException e2) {
                e = e2;
                throw new RuntimeException("Wait... but why? WTF!? ", e);
            } catch (NullPointerException e3) {
                e = e3;
                throw new RuntimeException("Wait... but why? WTF!? ", e);
            }
        }
    }

    @Override // com.baidu.cloud.videocache.Source
    public synchronized long length() {
        if (this.f4860b.f4897b == -2147483648L) {
            m19831c();
        }
        return this.f4860b.f4897b;
    }

    @Override // com.baidu.cloud.videocache.Source
    public void open(long j) {
        try {
            this.f4862e = m19838a(j, -1);
            String contentType = this.f4862e.getContentType();
            this.f4863f = new BufferedInputStream(this.f4862e.getInputStream(), 8192);
            this.f4860b = new C2577m(this.f4860b.f4896a, m19835a(this.f4862e, j, this.f4862e.getResponseCode()), contentType);
            this.f4859a.put(this.f4860b.f4896a, this.f4860b);
        } catch (IOException e) {
            throw new C2574j("Error opening connection for " + this.f4860b.f4896a + " with offset " + j, e);
        }
    }

    @Override // com.baidu.cloud.videocache.Source
    public int read(byte[] bArr) {
        InputStream inputStream = this.f4863f;
        if (inputStream == null) {
            throw new C2574j("Error reading data from " + this.f4860b.f4896a + ": connection is absent!");
        }
        try {
            return inputStream.read(bArr, 0, bArr.length);
        } catch (InterruptedIOException e) {
            throw new C2566d("Reading source " + this.f4860b.f4896a + " is interrupted", e);
        } catch (IOException e2) {
            throw new C2574j("Error reading data from " + this.f4860b.f4896a, e2);
        }
    }

    public String toString() {
        return "HttpUrlSource{sourceInfo='" + this.f4860b + "}";
    }
}
