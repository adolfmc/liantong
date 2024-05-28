package com.baidu.cloud.download.core;

import android.os.Process;
import android.text.TextUtils;
import com.baidu.cloud.download.base.DownloadTask;
import com.baidu.cloud.download.exception.DownloadException;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class DownloadTaskImpl implements DownloadTask {
    private volatile int mCommend = 0;
    private final DownloadInfo mDownloadInfo;
    private final DownloadTask.OnDownloadListener mOnDownloadListener;
    private volatile int mStatus;
    private String mTag;
    private final ThreadRecord mThreadRecord;

    protected abstract RandomAccessFile getFile(File file, String str, long j) throws IOException;

    protected abstract Map<String, String> getHttpHeaders(ThreadRecord threadRecord);

    protected abstract int getResponseCode();

    protected abstract String getTag();

    protected abstract void insertIntoDB(ThreadRecord threadRecord);

    protected abstract void updateDB(ThreadRecord threadRecord);

    public DownloadTaskImpl(DownloadInfo downloadInfo, ThreadRecord threadRecord, DownloadTask.OnDownloadListener onDownloadListener) {
        this.mDownloadInfo = downloadInfo;
        this.mThreadRecord = threadRecord;
        this.mOnDownloadListener = onDownloadListener;
        this.mTag = getTag();
        if (TextUtils.isEmpty(this.mTag)) {
            this.mTag = getClass().getSimpleName();
        }
    }

    @Override // com.baidu.cloud.download.base.DownloadTask
    public void cancel() {
        this.mCommend = 107;
    }

    @Override // com.baidu.cloud.download.base.DownloadTask
    public void pause() {
        this.mCommend = 106;
    }

    @Override // com.baidu.cloud.download.base.DownloadTask
    public boolean isDownloading() {
        return this.mStatus == 104;
    }

    @Override // com.baidu.cloud.download.base.DownloadTask
    public boolean isComplete() {
        return this.mStatus == 105;
    }

    @Override // com.baidu.cloud.download.base.DownloadTask
    public boolean isPaused() {
        return this.mStatus == 106;
    }

    @Override // com.baidu.cloud.download.base.DownloadTask
    public boolean isCanceled() {
        return this.mStatus == 107;
    }

    @Override // com.baidu.cloud.download.base.DownloadTask
    public boolean isFailed() {
        return this.mStatus == 108;
    }

    @Override // com.baidu.cloud.download.base.DownloadTask, java.lang.Runnable
    public void run() {
        Process.setThreadPriority(10);
        insertIntoDB(this.mThreadRecord);
        try {
            this.mStatus = 104;
            executeDownload();
            synchronized (this.mOnDownloadListener) {
                this.mStatus = 105;
                this.mOnDownloadListener.onDownloadCompleted(createFileSavedPath());
            }
        } catch (DownloadException e) {
            handleDownloadException(e);
        }
    }

    private void handleDownloadException(DownloadException downloadException) {
        switch (downloadException.getErrorCode()) {
            case 106:
                synchronized (this.mOnDownloadListener) {
                    this.mStatus = 106;
                    this.mOnDownloadListener.onDownloadPaused();
                }
                return;
            case 107:
                synchronized (this.mOnDownloadListener) {
                    this.mStatus = 107;
                    this.mOnDownloadListener.onDownloadCanceled();
                }
                return;
            case 108:
                synchronized (this.mOnDownloadListener) {
                    this.mStatus = 108;
                    this.mOnDownloadListener.onDownloadFailed(downloadException);
                }
                return;
            default:
                throw new IllegalArgumentException("Unknown state");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007a  */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.net.URL] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.baidu.cloud.download.core.DownloadTaskImpl] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void executeDownload() throws com.baidu.cloud.download.exception.DownloadException {
        /*
            r7 = this;
            r0 = 108(0x6c, float:1.51E-43)
            java.net.URL r1 = new java.net.URL     // Catch: java.net.MalformedURLException -> L7e
            com.baidu.cloud.download.core.ThreadRecord r2 = r7.mThreadRecord     // Catch: java.net.MalformedURLException -> L7e
            java.lang.String r2 = r2.getUri()     // Catch: java.net.MalformedURLException -> L7e
            r1.<init>(r2)     // Catch: java.net.MalformedURLException -> L7e
            r2 = 0
            java.net.URLConnection r1 = r1.openConnection()     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L5f java.net.ProtocolException -> L6b
            java.net.URLConnection r1 = com.networkbench.agent.impl.instrumentation.NBSInstrumentation.openConnection(r1)     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L5f java.net.ProtocolException -> L6b
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L5f java.net.ProtocolException -> L6b
            r2 = 4000(0xfa0, float:5.605E-42)
            r1.setConnectTimeout(r2)     // Catch: java.io.IOException -> L58 java.net.ProtocolException -> L5a java.lang.Throwable -> L77
            r1.setReadTimeout(r2)     // Catch: java.io.IOException -> L58 java.net.ProtocolException -> L5a java.lang.Throwable -> L77
            java.lang.String r2 = "GET"
            r1.setRequestMethod(r2)     // Catch: java.io.IOException -> L58 java.net.ProtocolException -> L5a java.lang.Throwable -> L77
            com.baidu.cloud.download.core.ThreadRecord r2 = r7.mThreadRecord     // Catch: java.io.IOException -> L58 java.net.ProtocolException -> L5a java.lang.Throwable -> L77
            java.util.Map r2 = r7.getHttpHeaders(r2)     // Catch: java.io.IOException -> L58 java.net.ProtocolException -> L5a java.lang.Throwable -> L77
            r7.setHttpHeader(r2, r1)     // Catch: java.io.IOException -> L58 java.net.ProtocolException -> L5a java.lang.Throwable -> L77
            int r2 = r1.getResponseCode()     // Catch: java.io.IOException -> L58 java.net.ProtocolException -> L5a java.lang.Throwable -> L77
            int r3 = r7.getResponseCode()     // Catch: java.io.IOException -> L58 java.net.ProtocolException -> L5a java.lang.Throwable -> L77
            if (r2 != r3) goto L41
            r7.transferData(r1)     // Catch: java.io.IOException -> L58 java.net.ProtocolException -> L5a java.lang.Throwable -> L77
            if (r1 == 0) goto L40
            r1.disconnect()
        L40:
            return
        L41:
            com.baidu.cloud.download.exception.DownloadException r3 = new com.baidu.cloud.download.exception.DownloadException     // Catch: java.io.IOException -> L58 java.net.ProtocolException -> L5a java.lang.Throwable -> L77
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L58 java.net.ProtocolException -> L5a java.lang.Throwable -> L77
            r4.<init>()     // Catch: java.io.IOException -> L58 java.net.ProtocolException -> L5a java.lang.Throwable -> L77
            java.lang.String r5 = "UnSupported response code:"
            r4.append(r5)     // Catch: java.io.IOException -> L58 java.net.ProtocolException -> L5a java.lang.Throwable -> L77
            r4.append(r2)     // Catch: java.io.IOException -> L58 java.net.ProtocolException -> L5a java.lang.Throwable -> L77
            java.lang.String r2 = r4.toString()     // Catch: java.io.IOException -> L58 java.net.ProtocolException -> L5a java.lang.Throwable -> L77
            r3.<init>(r0, r2)     // Catch: java.io.IOException -> L58 java.net.ProtocolException -> L5a java.lang.Throwable -> L77
            throw r3     // Catch: java.io.IOException -> L58 java.net.ProtocolException -> L5a java.lang.Throwable -> L77
        L58:
            r2 = move-exception
            goto L63
        L5a:
            r2 = move-exception
            goto L6f
        L5c:
            r0 = move-exception
            r1 = r2
            goto L78
        L5f:
            r1 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
        L63:
            com.baidu.cloud.download.exception.DownloadException r3 = new com.baidu.cloud.download.exception.DownloadException     // Catch: java.lang.Throwable -> L77
            java.lang.String r4 = "IO error"
            r3.<init>(r0, r4, r2)     // Catch: java.lang.Throwable -> L77
            throw r3     // Catch: java.lang.Throwable -> L77
        L6b:
            r1 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
        L6f:
            com.baidu.cloud.download.exception.DownloadException r3 = new com.baidu.cloud.download.exception.DownloadException     // Catch: java.lang.Throwable -> L77
            java.lang.String r4 = "Protocol error"
            r3.<init>(r0, r4, r2)     // Catch: java.lang.Throwable -> L77
            throw r3     // Catch: java.lang.Throwable -> L77
        L77:
            r0 = move-exception
        L78:
            if (r1 == 0) goto L7d
            r1.disconnect()
        L7d:
            throw r0
        L7e:
            r1 = move-exception
            com.baidu.cloud.download.exception.DownloadException r2 = new com.baidu.cloud.download.exception.DownloadException
            java.lang.String r3 = "Bad url."
            r2.<init>(r0, r3, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.cloud.download.core.DownloadTaskImpl.executeDownload():void");
    }

    private void setHttpHeader(Map<String, String> map, URLConnection uRLConnection) {
        if (map != null) {
            for (String str : map.keySet()) {
                uRLConnection.setRequestProperty(str, map.get(str));
            }
        }
    }

    private void transferData(HttpURLConnection httpURLConnection) throws DownloadException {
        InputStream inputStream;
        try {
            try {
                inputStream = httpURLConnection.getInputStream();
                try {
                    long start = this.mThreadRecord.getStart() + this.mThreadRecord.getFinished();
                    try {
                        File dir = this.mDownloadInfo.getDir();
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        RandomAccessFile file = getFile(dir, this.mDownloadInfo.getName(), start);
                        transferData(inputStream, file);
                        try {
                            close(inputStream);
                            close(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e2) {
                        throw new DownloadException(108, "File occur IOException ", e2);
                    } catch (Exception e3) {
                        throw new DownloadException(108, "Occur Exception ", e3);
                    }
                } catch (Throwable th) {
                    th = th;
                    try {
                        close(inputStream);
                        close(null);
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    throw th;
                }
            } catch (IOException e5) {
                throw new DownloadException(108, "http get inputStream error", e5);
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
    }

    private void transferData(InputStream inputStream, RandomAccessFile randomAccessFile) throws DownloadException {
        byte[] bArr = new byte[8192];
        while (true) {
            checkPausedOrCanceled();
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return;
                }
                randomAccessFile.write(bArr, 0, read);
                long j = read;
                this.mThreadRecord.setFinished(this.mThreadRecord.getFinished() + j);
                synchronized (this.mOnDownloadListener) {
                    this.mDownloadInfo.setFinished(this.mDownloadInfo.getFinished() + j);
                    this.mOnDownloadListener.onDownloadProgress(this.mDownloadInfo.getFinished(), this.mDownloadInfo.getLength());
                }
            } catch (IOException e) {
                updateDB(this.mThreadRecord);
                throw new DownloadException(108, e);
            }
        }
    }

    private void checkPausedOrCanceled() throws DownloadException {
        if (this.mCommend == 107) {
            throw new DownloadException(107, "Download canceled!");
        }
        if (this.mCommend != 106) {
            return;
        }
        updateDB(this.mThreadRecord);
        throw new DownloadException(106, "Download paused!");
    }

    private final void close(Closeable closeable) throws IOException {
        if (closeable != null) {
            synchronized (DownloadTaskImpl.class) {
                closeable.close();
            }
        }
    }

    private final String createFileSavedPath() {
        return this.mDownloadInfo.getDir().getAbsolutePath() + File.separator + this.mDownloadInfo.getName();
    }
}
