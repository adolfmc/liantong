package com.danikula.videocache;

import android.text.TextUtils;
import com.danikula.videocache.headers.EmptyHeadersInjector;
import com.danikula.videocache.headers.HeaderInjector;
import com.danikula.videocache.sourcestorage.SourceInfoStorage;
import com.danikula.videocache.sourcestorage.SourceInfoStorageFactory;
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

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class HttpUrlSource implements Source {
    private static final Logger LOG = LoggerFactory.getLogger("HttpUrlSource");
    private static final int MAX_REDIRECTS = 5;
    private HttpURLConnection connection;
    private final HeaderInjector headerInjector;
    private InputStream inputStream;
    private SourceInfo sourceInfo;
    private final SourceInfoStorage sourceInfoStorage;

    public HttpUrlSource(String str) {
        this(str, SourceInfoStorageFactory.newEmptySourceInfoStorage());
    }

    public HttpUrlSource(String str, SourceInfoStorage sourceInfoStorage) {
        this(str, sourceInfoStorage, new EmptyHeadersInjector());
    }

    public HttpUrlSource(String str, SourceInfoStorage sourceInfoStorage, HeaderInjector headerInjector) {
        this.sourceInfoStorage = (SourceInfoStorage) Preconditions.checkNotNull(sourceInfoStorage);
        this.headerInjector = (HeaderInjector) Preconditions.checkNotNull(headerInjector);
        SourceInfo sourceInfo = sourceInfoStorage.get(str);
        this.sourceInfo = sourceInfo == null ? new SourceInfo(str, -2147483648L, ProxyCacheUtils.getSupposablyMime(str)) : sourceInfo;
    }

    public HttpUrlSource(HttpUrlSource httpUrlSource) {
        this.sourceInfo = httpUrlSource.sourceInfo;
        this.sourceInfoStorage = httpUrlSource.sourceInfoStorage;
        this.headerInjector = httpUrlSource.headerInjector;
    }

    @Override // com.danikula.videocache.Source
    public synchronized long length() throws ProxyCacheException {
        if (this.sourceInfo.length == -2147483648L) {
            fetchContentInfo();
        }
        return this.sourceInfo.length;
    }

    @Override // com.danikula.videocache.Source
    public void open(long j) throws ProxyCacheException {
        try {
            this.connection = openConnection(j, -1);
            String contentType = this.connection.getContentType();
            this.inputStream = new BufferedInputStream(this.connection.getInputStream(), 8192);
            this.sourceInfo = new SourceInfo(this.sourceInfo.url, readSourceAvailableBytes(this.connection, j, this.connection.getResponseCode()), contentType);
            this.sourceInfoStorage.put(this.sourceInfo.url, this.sourceInfo);
        } catch (IOException e) {
            throw new ProxyCacheException("Error opening connection for " + this.sourceInfo.url + " with offset " + j, e);
        }
    }

    private long readSourceAvailableBytes(HttpURLConnection httpURLConnection, long j, int i) throws IOException {
        long contentLength = getContentLength(httpURLConnection);
        return i == 200 ? contentLength : i == 206 ? contentLength + j : this.sourceInfo.length;
    }

    private long getContentLength(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("Content-Length");
        if (headerField == null) {
            return -1L;
        }
        return Long.parseLong(headerField);
    }

    @Override // com.danikula.videocache.Source
    public void close() throws ProxyCacheException {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (ArrayIndexOutOfBoundsException e) {
                LOG.error("Error closing connection correctly. Should happen only on Android L. If anybody know how to fix it, please visit https://github.com/danikula/AndroidVideoCache/issues/88. Until good solution is not know, just ignore this issue :(", (Throwable) e);
            } catch (IllegalArgumentException e2) {
                e = e2;
                throw new RuntimeException("Wait... but why? WTF!? Really shouldn't happen any more after fixing https://github.com/danikula/AndroidVideoCache/issues/43. If you read it on your device log, please, notify me danikula@gmail.com or create issue here https://github.com/danikula/AndroidVideoCache/issues.", e);
            } catch (NullPointerException e3) {
                e = e3;
                throw new RuntimeException("Wait... but why? WTF!? Really shouldn't happen any more after fixing https://github.com/danikula/AndroidVideoCache/issues/43. If you read it on your device log, please, notify me danikula@gmail.com or create issue here https://github.com/danikula/AndroidVideoCache/issues.", e);
            }
        }
    }

    @Override // com.danikula.videocache.Source
    public int read(byte[] bArr) throws ProxyCacheException {
        InputStream inputStream = this.inputStream;
        if (inputStream == null) {
            throw new ProxyCacheException("Error reading data from " + this.sourceInfo.url + ": connection is absent!");
        }
        try {
            return inputStream.read(bArr, 0, bArr.length);
        } catch (InterruptedIOException e) {
            throw new InterruptedProxyCacheException("Reading source " + this.sourceInfo.url + " is interrupted", e);
        } catch (IOException e2) {
            throw new ProxyCacheException("Error reading data from " + this.sourceInfo.url, e2);
        }
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
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void fetchContentInfo() throws com.danikula.videocache.ProxyCacheException {
        /*
            r7 = this;
            org.slf4j.Logger r0 = com.danikula.videocache.HttpUrlSource.LOG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Read content info from "
            r1.append(r2)
            com.danikula.videocache.SourceInfo r2 = r7.sourceInfo
            java.lang.String r2 = r2.url
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.debug(r1)
            r0 = 0
            r2 = 10000(0x2710, float:1.4013E-41)
            r3 = 0
            java.net.HttpURLConnection r0 = r7.openConnection(r0, r2)     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L6a
            long r1 = r7.getContentLength(r0)     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            java.lang.String r4 = r0.getContentType()     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            java.io.InputStream r3 = r0.getInputStream()     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            com.danikula.videocache.SourceInfo r5 = new com.danikula.videocache.SourceInfo     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            com.danikula.videocache.SourceInfo r6 = r7.sourceInfo     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            java.lang.String r6 = r6.url     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            r5.<init>(r6, r1, r4)     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            r7.sourceInfo = r5     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            com.danikula.videocache.sourcestorage.SourceInfoStorage r1 = r7.sourceInfoStorage     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            com.danikula.videocache.SourceInfo r2 = r7.sourceInfo     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            java.lang.String r2 = r2.url     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            com.danikula.videocache.SourceInfo r4 = r7.sourceInfo     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            r1.put(r2, r4)     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            org.slf4j.Logger r1 = com.danikula.videocache.HttpUrlSource.LOG     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            r2.<init>()     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            java.lang.String r4 = "Source info fetched: "
            r2.append(r4)     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            com.danikula.videocache.SourceInfo r4 = r7.sourceInfo     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            r2.append(r4)     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            r1.debug(r2)     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            com.danikula.videocache.ProxyCacheUtils.close(r3)
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
            org.slf4j.Logger r2 = com.danikula.videocache.HttpUrlSource.LOG     // Catch: java.lang.Throwable -> L63
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L63
            r4.<init>()     // Catch: java.lang.Throwable -> L63
            java.lang.String r5 = "Error fetching info from "
            r4.append(r5)     // Catch: java.lang.Throwable -> L63
            com.danikula.videocache.SourceInfo r5 = r7.sourceInfo     // Catch: java.lang.Throwable -> L63
            java.lang.String r5 = r5.url     // Catch: java.lang.Throwable -> L63
            r4.append(r5)     // Catch: java.lang.Throwable -> L63
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L63
            r2.error(r4, r1)     // Catch: java.lang.Throwable -> L63
            com.danikula.videocache.ProxyCacheUtils.close(r3)
            if (r0 == 0) goto L8e
        L8b:
            r0.disconnect()
        L8e:
            return
        L8f:
            com.danikula.videocache.ProxyCacheUtils.close(r3)
            if (r0 == 0) goto L97
            r0.disconnect()
        L97:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.danikula.videocache.HttpUrlSource.fetchContentInfo():void");
    }

    private HttpURLConnection openConnection(long j, int i) throws IOException, ProxyCacheException {
        String str;
        HttpURLConnection httpURLConnection;
        boolean z;
        String str2 = this.sourceInfo.url;
        int i2 = 0;
        do {
            Logger logger = LOG;
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
            injectCustomHeaders(httpURLConnection, str2);
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
                throw new ProxyCacheException("Too many redirects: " + i2);
            }
        } while (z);
        return httpURLConnection;
    }

    private void injectCustomHeaders(HttpURLConnection httpURLConnection, String str) {
        for (Map.Entry<String, String> entry : this.headerInjector.addHeaders(str).entrySet()) {
            httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    public synchronized String getMime() throws ProxyCacheException {
        if (TextUtils.isEmpty(this.sourceInfo.mime)) {
            fetchContentInfo();
        }
        return this.sourceInfo.mime;
    }

    public String getUrl() {
        return this.sourceInfo.url;
    }

    public String toString() {
        return "HttpUrlSource{sourceInfo='" + this.sourceInfo + "}";
    }
}
