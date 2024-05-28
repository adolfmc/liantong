package android.net.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.protocol.RequestContent;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\452516_dexfile_execute.dex */
public class Request {
    private static final String ACCEPT_ENCODING_HEADER = "Accept-Encoding";
    private static final String CONTENT_LENGTH_HEADER = "content-length";
    private static final String HOST_HEADER = "Host";
    private static RequestContent requestContentProcessor = new RequestContent();
    private int mBodyLength;
    private InputStream mBodyProvider;
    private Connection mConnection;
    EventHandler mEventHandler;
    HttpHost mHost;
    BasicHttpRequest mHttpRequest;
    String mPath;
    HttpHost mProxyHost;
    volatile boolean mCancelled = false;
    int mFailCount = 0;
    private int mReceivedBytes = 0;
    private final Object mClientResource = new Object();
    private boolean mLoadingPaused = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Request(String str, HttpHost httpHost, HttpHost httpHost2, String str2, InputStream inputStream, int i, EventHandler eventHandler, Map<String, String> map) {
        this.mEventHandler = eventHandler;
        this.mHost = httpHost;
        this.mProxyHost = httpHost2;
        this.mPath = str2;
        this.mBodyProvider = inputStream;
        this.mBodyLength = i;
        if (inputStream == null && !"POST".equalsIgnoreCase(str)) {
            this.mHttpRequest = new BasicHttpRequest(str, getUri());
        } else {
            this.mHttpRequest = new BasicHttpEntityEnclosingRequest(str, getUri());
            if (inputStream != null) {
                setBodyProvider(inputStream, i);
            }
        }
        addHeader("Host", getHostPort());
        addHeader("Accept-Encoding", "gzip");
        addHeaders(map);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void setLoadingPaused(boolean z) {
        this.mLoadingPaused = z;
        if (!z) {
            notify();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setConnection(Connection connection) {
        this.mConnection = connection;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EventHandler getEventHandler() {
        return this.mEventHandler;
    }

    void addHeader(String str, String str2) {
        if (str == null) {
            HttpLog.m22214e("Null http header name");
            throw new NullPointerException("Null http header name");
        } else if (str2 == null || str2.length() == 0) {
            String str3 = "Null or empty value for header \"" + str + "\"";
            HttpLog.m22214e(str3);
            throw new RuntimeException(str3);
        } else {
            this.mHttpRequest.addHeader(str, str2);
        }
    }

    void addHeaders(Map<String, String> map) {
        if (map == null) {
            return;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            addHeader(entry.getKey(), entry.getValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendRequest(AndroidHttpClientConnection androidHttpClientConnection) throws HttpException, IOException {
        if (this.mCancelled) {
            return;
        }
        requestContentProcessor.process(this.mHttpRequest, this.mConnection.getHttpContext());
        androidHttpClientConnection.sendRequestHeader(this.mHttpRequest);
        BasicHttpRequest basicHttpRequest = this.mHttpRequest;
        if (basicHttpRequest instanceof HttpEntityEnclosingRequest) {
            androidHttpClientConnection.sendRequestEntity((HttpEntityEnclosingRequest) basicHttpRequest);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00de, code lost:
        if (r9 == null) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0111, code lost:
        if (r9 == null) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0113, code lost:
        r9.close();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0105 A[Catch: all -> 0x00e1, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x00e1, blocks: (B:27:0x0084, B:31:0x0093, B:40:0x00ba, B:42:0x00c2, B:44:0x00c5, B:47:0x00ce, B:51:0x00d7, B:74:0x0105), top: B:84:0x0063 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0093 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void readResponse(android.net.http.AndroidHttpClientConnection r18) throws java.io.IOException, org.apache.http.ParseException {
        /*
            Method dump skipped, instructions count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.net.http.Request.readResponse(android.net.http.AndroidHttpClientConnection):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void cancel() {
        this.mLoadingPaused = false;
        notify();
        this.mCancelled = true;
        Connection connection = this.mConnection;
        if (connection != null) {
            connection.cancel();
        }
    }

    String getHostPort() {
        String schemeName = this.mHost.getSchemeName();
        int port = this.mHost.getPort();
        if ((port != 80 && schemeName.equals("http")) || (port != 443 && schemeName.equals("https"))) {
            return this.mHost.toHostString();
        }
        return this.mHost.getHostName();
    }

    String getUri() {
        if (this.mProxyHost == null || this.mHost.getSchemeName().equals("https")) {
            return this.mPath;
        }
        return this.mHost.getSchemeName() + "://" + getHostPort() + this.mPath;
    }

    public String toString() {
        return this.mPath;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reset() {
        this.mHttpRequest.removeHeaders("content-length");
        InputStream inputStream = this.mBodyProvider;
        if (inputStream != null) {
            try {
                inputStream.reset();
            } catch (IOException e) {
            }
            setBodyProvider(this.mBodyProvider, this.mBodyLength);
        }
        if (this.mReceivedBytes > 0) {
            this.mFailCount = 0;
            HttpLog.m22213v("*** Request.reset() to range:" + this.mReceivedBytes);
            this.mHttpRequest.setHeader("Range", "bytes=" + this.mReceivedBytes + "-");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void waitUntilComplete() {
        synchronized (this.mClientResource) {
            try {
                this.mClientResource.wait();
            } catch (InterruptedException e) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void complete() {
        synchronized (this.mClientResource) {
            this.mClientResource.notifyAll();
        }
    }

    private static boolean canResponseHaveBody(HttpRequest httpRequest, int i) {
        return ("HEAD".equalsIgnoreCase(httpRequest.getRequestLine().getMethod()) || i < 200 || i == 204 || i == 304) ? false : true;
    }

    private void setBodyProvider(InputStream inputStream, int i) {
        if (!inputStream.markSupported()) {
            throw new IllegalArgumentException("bodyProvider must support mark()");
        }
        inputStream.mark(Integer.MAX_VALUE);
        ((BasicHttpEntityEnclosingRequest) this.mHttpRequest).setEntity(new InputStreamEntity(inputStream, i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleSslErrorResponse(boolean z) {
        HttpsConnection httpsConnection = (HttpsConnection) this.mConnection;
        if (httpsConnection != null) {
            httpsConnection.restartConnection(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void error(int i, String str) {
        this.mEventHandler.error(i, str);
    }
}
