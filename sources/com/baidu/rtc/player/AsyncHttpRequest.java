package com.baidu.rtc.player;

import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.webrtc.Logging;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AsyncHttpRequest {
    private static final String DEFAULT_CONTENT_TYPE = "application/json";
    private static final String DEFAULT_METHOD = "POST";
    private static final int HTTP_REQUEST_TIMEOUT_MS = 5000;
    private static final String TAG = "AsyncHttpRequest";
    private HttpURLConnection connection;
    private String contentType;
    private AsyncHttpEvents events;
    private Object mConnectionOpenLock;
    private boolean mConnectionOpened;
    private String message;
    private String method;
    private OutputStream outStream;
    private final ExecutorService requestProcessor;
    private final String url;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface AsyncHttpEvents {
        void onConnectionOpened();

        void onHttpComplete(String str);

        void onHttpError(String str);
    }

    public AsyncHttpRequest(String str, String str2, String str3, String str4, AsyncHttpEvents asyncHttpEvents) {
        this.requestProcessor = Executors.newSingleThreadExecutor();
        this.method = "POST";
        this.contentType = "application/json";
        this.mConnectionOpenLock = new Object();
        this.mConnectionOpened = false;
        this.method = str;
        this.url = str2;
        this.message = str3;
        this.events = asyncHttpEvents;
        this.contentType = str4;
    }

    public AsyncHttpRequest(String str, AsyncHttpEvents asyncHttpEvents) {
        this.requestProcessor = Executors.newSingleThreadExecutor();
        this.method = "POST";
        this.contentType = "application/json";
        this.mConnectionOpenLock = new Object();
        this.mConnectionOpened = false;
        this.url = str;
        this.events = asyncHttpEvents;
    }

    public void setContentType(String str) {
        this.contentType = str;
    }

    public void open() {
        if (this.requestProcessor.isShutdown()) {
            return;
        }
        this.requestProcessor.submit(new Runnable() { // from class: com.baidu.rtc.player.-$$Lambda$R6jplUckBUI6X3iGLK9scod-Eew
            @Override // java.lang.Runnable
            public final void run() {
                AsyncHttpRequest.this.openConnection();
            }
        });
    }

    public void openConnection() {
        try {
            Logging.m5305d("AsyncHttpRequest", "start open connection. ");
            this.connection = (HttpURLConnection) NBSInstrumentation.openConnection(new URL(this.url).openConnection());
            this.connection.setRequestMethod(this.method);
            this.connection.setUseCaches(false);
            this.connection.setDoInput(true);
            this.connection.setConnectTimeout(5000);
            this.connection.setReadTimeout(5000);
            if ("POST".equals(this.method)) {
                this.connection.setDoOutput(true);
            }
            if (this.contentType == null) {
                this.connection.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
            } else {
                this.connection.setRequestProperty("Content-Type", this.contentType);
            }
            this.connection.setRequestProperty("Accept", "application/json");
            this.outStream = this.connection.getOutputStream();
            synchronized (this.mConnectionOpenLock) {
                this.mConnectionOpened = true;
                this.mConnectionOpenLock.notifyAll();
            }
            if (this.events != null) {
                this.events.onConnectionOpened();
            }
        } catch (SocketTimeoutException unused) {
            AsyncHttpEvents asyncHttpEvents = this.events;
            asyncHttpEvents.onHttpError("HTTP " + this.method + " to " + this.url + " timeout");
            this.connection.disconnect();
            this.connection = null;
        } catch (IOException e) {
            AsyncHttpEvents asyncHttpEvents2 = this.events;
            asyncHttpEvents2.onHttpError("HTTP " + this.method + " to " + this.url + " error: " + e.getMessage());
            this.connection.disconnect();
            this.connection = null;
        }
    }

    public void request(final String str) {
        if (this.requestProcessor.isShutdown()) {
            return;
        }
        this.requestProcessor.submit(new Runnable() { // from class: com.baidu.rtc.player.AsyncHttpRequest.1
            @Override // java.lang.Runnable
            public void run() {
                AsyncHttpRequest.this.requestRemoteSdp(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestRemoteSdp(String str) {
        try {
            synchronized (this.mConnectionOpenLock) {
                while (true) {
                    if (this.mConnectionOpened && this.outStream != null) {
                        break;
                    }
                    try {
                        this.mConnectionOpenLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            byte[] bArr = new byte[0];
            if (!TextUtils.isEmpty(str)) {
                bArr = str.getBytes("UTF-8");
            }
            if (this.outStream != null && bArr.length > 0) {
                this.outStream.write(bArr);
                this.outStream.close();
                this.outStream = null;
            }
            int responseCode = this.connection.getResponseCode();
            if (responseCode != 200) {
                AsyncHttpEvents asyncHttpEvents = this.events;
                asyncHttpEvents.onHttpError("Non-200 response to " + this.method + " code " + responseCode + " to URL: " + this.url + " : " + this.connection.getHeaderField((String) null));
                this.connection.disconnect();
                this.connection = null;
                return;
            }
            InputStream inputStream = this.connection.getInputStream();
            this.events.onHttpComplete(drainStream(inputStream));
            inputStream.close();
            this.connection.disconnect();
            this.connection = null;
        } catch (SocketTimeoutException unused) {
            AsyncHttpEvents asyncHttpEvents2 = this.events;
            asyncHttpEvents2.onHttpError("HTTP " + this.method + " to " + this.url + " timeout");
        } catch (IOException e2) {
            AsyncHttpEvents asyncHttpEvents3 = this.events;
            asyncHttpEvents3.onHttpError("HTTP " + this.method + " to " + this.url + " error: " + e2.getMessage());
        }
    }

    public void request() {
        new Thread(new Runnable() { // from class: com.baidu.rtc.player.-$$Lambda$nhAQyNvvnw91BLNrKiuShmRJCLw
            @Override // java.lang.Runnable
            public final void run() {
                AsyncHttpRequest.this.sendHttpMessage();
            }
        }).start();
    }

    public void sendHttpMessage() {
        try {
            Logging.m5305d("AsyncHttpRequest", "start open connection... ");
            HttpURLConnection httpURLConnection = (HttpURLConnection) NBSInstrumentation.openConnection(new URL(this.url).openConnection());
            Logging.m5305d("AsyncHttpRequest", "after open connection... ");
            boolean z = false;
            byte[] bArr = new byte[0];
            if (this.message != null) {
                bArr = this.message.getBytes("UTF-8");
            }
            httpURLConnection.setRequestMethod(this.method);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            if ("POST".equals(this.method)) {
                httpURLConnection.setDoOutput(true);
                z = true;
            }
            if (this.contentType == null) {
                httpURLConnection.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
            } else {
                httpURLConnection.setRequestProperty("Content-Type", this.contentType);
            }
            httpURLConnection.setRequestProperty("Accept", "application/json");
            if (z && bArr.length > 0) {
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bArr);
                outputStream.close();
            }
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                this.events.onHttpError("Non-200 response to " + this.method + " code " + responseCode + " to URL: " + this.url + " : " + httpURLConnection.getHeaderField((String) null));
                httpURLConnection.disconnect();
                return;
            }
            InputStream inputStream = httpURLConnection.getInputStream();
            this.events.onHttpComplete(drainStream(inputStream));
            inputStream.close();
            httpURLConnection.disconnect();
        } catch (SocketTimeoutException e) {
            this.events.onHttpError("HTTP " + this.method + " to " + this.url + " timeout" + e.getMessage());
        } catch (IOException e2) {
            this.events.onHttpError("HTTP " + this.method + " to " + this.url + " error: " + e2.getMessage());
        }
    }

    private static String drainStream(InputStream inputStream) {
        Scanner useDelimiter = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
        return useDelimiter.hasNext() ? useDelimiter.next() : "";
    }

    public boolean isInvalid() {
        return this.connection == null || this.outStream == null;
    }

    public void closeInternal() {
        try {
            if (this.connection != null) {
                this.connection.disconnect();
                this.connection = null;
            }
            if (this.outStream != null) {
                this.outStream.close();
                this.outStream = null;
            }
            Logging.m5305d("AsyncHttpRequest", "closing connection.");
        } catch (IOException e) {
            Logging.m5305d("AsyncHttpRequest", "Error closing connection. " + e);
        } catch (IllegalArgumentException e2) {
            e = e2;
            Logging.m5305d("AsyncHttpRequest", "Error closing connection. " + e);
        } catch (NullPointerException e3) {
            e = e3;
            Logging.m5305d("AsyncHttpRequest", "Error closing connection. " + e);
        }
    }

    public void close() {
        if (this.requestProcessor.isShutdown()) {
            return;
        }
        try {
            this.requestProcessor.submit(new Runnable() { // from class: com.baidu.rtc.player.AsyncHttpRequest.2
                @Override // java.lang.Runnable
                public void run() {
                    AsyncHttpRequest.this.closeInternal();
                }
            });
            this.requestProcessor.shutdown();
            this.requestProcessor.awaitTermination(200L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Logging.m5305d("AsyncHttpRequest", "http request close interrupted exception:" + e.getMessage());
        } catch (Exception e2) {
            Logging.m5305d("AsyncHttpRequest", "http request close exception:" + e2.getMessage());
        }
    }
}
