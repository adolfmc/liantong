package com.baidu.cloud.download.core;

import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.cloud.download.base.HttpConnectTask;
import com.baidu.cloud.download.exception.DownloadException;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.net.HttpURLConnection;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HttpConnectTaskImpl implements HttpConnectTask {
    private final HttpConnectTask.OnConnectListener mOnConnectListener;
    private volatile long mStartTime;
    private volatile int mStatus;
    private final String mUri;

    public HttpConnectTaskImpl(String str, HttpConnectTask.OnConnectListener onConnectListener) {
        this.mUri = str;
        this.mOnConnectListener = onConnectListener;
    }

    @Override // com.baidu.cloud.download.base.HttpConnectTask
    public void pause() {
        this.mStatus = 106;
    }

    @Override // com.baidu.cloud.download.base.HttpConnectTask
    public void cancel() {
        this.mStatus = 107;
    }

    @Override // com.baidu.cloud.download.base.HttpConnectTask
    public boolean isConnecting() {
        return this.mStatus == 102;
    }

    @Override // com.baidu.cloud.download.base.HttpConnectTask
    public boolean isConnected() {
        return this.mStatus == 103;
    }

    @Override // com.baidu.cloud.download.base.HttpConnectTask
    public boolean isPaused() {
        return this.mStatus == 106;
    }

    @Override // com.baidu.cloud.download.base.HttpConnectTask
    public boolean isCanceled() {
        return this.mStatus == 107;
    }

    @Override // com.baidu.cloud.download.base.HttpConnectTask
    public boolean isFailed() {
        return this.mStatus == 108;
    }

    @Override // com.baidu.cloud.download.base.HttpConnectTask, java.lang.Runnable
    public void run() {
        Process.setThreadPriority(10);
        this.mStatus = 102;
        this.mOnConnectListener.onConnecting();
        try {
            executeConnection();
        } catch (DownloadException e) {
            handleDownloadException(e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a5  */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.net.URL] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.net.HttpURLConnection] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void executeConnection() throws com.baidu.cloud.download.exception.DownloadException {
        /*
            r7 = this;
            java.lang.String r0 = "RtcDownSo"
            java.lang.String r1 = "execute connnection"
            android.util.Log.d(r0, r1)
            long r0 = java.lang.System.currentTimeMillis()
            r7.mStartTime = r0
            r0 = 108(0x6c, float:1.51E-43)
            java.net.URL r1 = new java.net.URL     // Catch: java.net.MalformedURLException -> La9
            java.lang.String r2 = r7.mUri     // Catch: java.net.MalformedURLException -> La9
            r1.<init>(r2)     // Catch: java.net.MalformedURLException -> La9
            r2 = 0
            java.net.URLConnection r1 = r1.openConnection()     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L84 java.net.ProtocolException -> L93
            java.net.URLConnection r1 = com.networkbench.agent.impl.instrumentation.NBSInstrumentation.openConnection(r1)     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L84 java.net.ProtocolException -> L93
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L84 java.net.ProtocolException -> L93
            r2 = 4000(0xfa0, float:5.605E-42)
            r1.setConnectTimeout(r2)     // Catch: java.io.IOException -> L7d java.net.ProtocolException -> L7f java.lang.Throwable -> La2
            r1.setReadTimeout(r2)     // Catch: java.io.IOException -> L7d java.net.ProtocolException -> L7f java.lang.Throwable -> La2
            java.lang.String r2 = "GET"
            r1.setRequestMethod(r2)     // Catch: java.io.IOException -> L7d java.net.ProtocolException -> L7f java.lang.Throwable -> La2
            java.lang.String r2 = "Range"
            java.lang.String r3 = "bytes=0-"
            r1.setRequestProperty(r2, r3)     // Catch: java.io.IOException -> L7d java.net.ProtocolException -> L7f java.lang.Throwable -> La2
            int r2 = r1.getResponseCode()     // Catch: java.io.IOException -> L7d java.net.ProtocolException -> L7f java.lang.Throwable -> La2
            r3 = 200(0xc8, float:2.8E-43)
            if (r2 != r3) goto L42
            r2 = 0
            r7.parseResponse(r1, r2)     // Catch: java.io.IOException -> L7d java.net.ProtocolException -> L7f java.lang.Throwable -> La2
            goto L4a
        L42:
            r3 = 206(0xce, float:2.89E-43)
            if (r2 != r3) goto L50
            r2 = 1
            r7.parseResponse(r1, r2)     // Catch: java.io.IOException -> L7d java.net.ProtocolException -> L7f java.lang.Throwable -> La2
        L4a:
            if (r1 == 0) goto L4f
            r1.disconnect()
        L4f:
            return
        L50:
            java.lang.String r3 = "RtcDownSo"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L7d java.net.ProtocolException -> L7f java.lang.Throwable -> La2
            r4.<init>()     // Catch: java.io.IOException -> L7d java.net.ProtocolException -> L7f java.lang.Throwable -> La2
            java.lang.String r5 = "UnSupported response code:"
            r4.append(r5)     // Catch: java.io.IOException -> L7d java.net.ProtocolException -> L7f java.lang.Throwable -> La2
            r4.append(r2)     // Catch: java.io.IOException -> L7d java.net.ProtocolException -> L7f java.lang.Throwable -> La2
            java.lang.String r4 = r4.toString()     // Catch: java.io.IOException -> L7d java.net.ProtocolException -> L7f java.lang.Throwable -> La2
            android.util.Log.d(r3, r4)     // Catch: java.io.IOException -> L7d java.net.ProtocolException -> L7f java.lang.Throwable -> La2
            com.baidu.cloud.download.exception.DownloadException r3 = new com.baidu.cloud.download.exception.DownloadException     // Catch: java.io.IOException -> L7d java.net.ProtocolException -> L7f java.lang.Throwable -> La2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L7d java.net.ProtocolException -> L7f java.lang.Throwable -> La2
            r4.<init>()     // Catch: java.io.IOException -> L7d java.net.ProtocolException -> L7f java.lang.Throwable -> La2
            java.lang.String r5 = "UnSupported response code:"
            r4.append(r5)     // Catch: java.io.IOException -> L7d java.net.ProtocolException -> L7f java.lang.Throwable -> La2
            r4.append(r2)     // Catch: java.io.IOException -> L7d java.net.ProtocolException -> L7f java.lang.Throwable -> La2
            java.lang.String r2 = r4.toString()     // Catch: java.io.IOException -> L7d java.net.ProtocolException -> L7f java.lang.Throwable -> La2
            r3.<init>(r0, r2)     // Catch: java.io.IOException -> L7d java.net.ProtocolException -> L7f java.lang.Throwable -> La2
            throw r3     // Catch: java.io.IOException -> L7d java.net.ProtocolException -> L7f java.lang.Throwable -> La2
        L7d:
            r2 = move-exception
            goto L88
        L7f:
            r2 = move-exception
            goto L97
        L81:
            r0 = move-exception
            r1 = r2
            goto La3
        L84:
            r1 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
        L88:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> La2
            com.baidu.cloud.download.exception.DownloadException r3 = new com.baidu.cloud.download.exception.DownloadException     // Catch: java.lang.Throwable -> La2
            java.lang.String r4 = "IO error"
            r3.<init>(r0, r4, r2)     // Catch: java.lang.Throwable -> La2
            throw r3     // Catch: java.lang.Throwable -> La2
        L93:
            r1 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
        L97:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> La2
            com.baidu.cloud.download.exception.DownloadException r3 = new com.baidu.cloud.download.exception.DownloadException     // Catch: java.lang.Throwable -> La2
            java.lang.String r4 = "Protocol error"
            r3.<init>(r0, r4, r2)     // Catch: java.lang.Throwable -> La2
            throw r3     // Catch: java.lang.Throwable -> La2
        La2:
            r0 = move-exception
        La3:
            if (r1 == 0) goto La8
            r1.disconnect()
        La8:
            throw r0
        La9:
            r1 = move-exception
            com.baidu.cloud.download.exception.DownloadException r2 = new com.baidu.cloud.download.exception.DownloadException
            java.lang.String r3 = "Bad url."
            r2.<init>(r0, r3, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.cloud.download.core.HttpConnectTaskImpl.executeConnection():void");
    }

    private void parseResponse(HttpURLConnection httpURLConnection, boolean z) throws DownloadException {
        long contentLength;
        long j;
        long contentLength2;
        Log.d("RtcDownSo", "start parse response");
        String headerField = httpURLConnection.getHeaderField("Content-Length");
        if (TextUtils.isEmpty(headerField) || headerField.equals("0") || headerField.equals("-1")) {
            contentLength = httpURLConnection.getContentLength();
        } else {
            contentLength = Long.parseLong(headerField);
        }
        if (contentLength <= 0) {
            String headerField2 = httpURLConnection.getHeaderField("Ohc-File-Size");
            if (TextUtils.isEmpty(headerField2) || headerField2.equals("0") || headerField2.equals("-1")) {
                contentLength2 = httpURLConnection.getContentLength();
            } else {
                contentLength2 = Long.parseLong(headerField2);
            }
            if (contentLength2 <= 0) {
                throw new DownloadException(108, "length <= 0");
            }
            j = contentLength2;
        } else {
            j = contentLength;
        }
        checkCanceledOrPaused();
        this.mStatus = 103;
        this.mOnConnectListener.onConnected(System.currentTimeMillis() - this.mStartTime, j, z);
    }

    private void checkCanceledOrPaused() throws DownloadException {
        if (isCanceled()) {
            throw new DownloadException(107, "Connection Canceled!");
        }
        if (isPaused()) {
            throw new DownloadException(106, "Connection Paused!");
        }
    }

    private void handleDownloadException(DownloadException downloadException) {
        switch (downloadException.getErrorCode()) {
            case 106:
                synchronized (this.mOnConnectListener) {
                    this.mStatus = 106;
                    this.mOnConnectListener.onConnectPaused();
                }
                return;
            case 107:
                synchronized (this.mOnConnectListener) {
                    this.mStatus = 107;
                    this.mOnConnectListener.onConnectCanceled();
                }
                return;
            case 108:
                synchronized (this.mOnConnectListener) {
                    this.mStatus = 108;
                    this.mOnConnectListener.onConnectFailed(downloadException);
                }
                return;
            default:
                throw new IllegalArgumentException("Unknown state");
        }
    }
}
