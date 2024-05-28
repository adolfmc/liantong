package com.p319ss.android.socialbase.downloader.exception;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.exception.DownloadTTNetException */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class DownloadTTNetException extends BaseException {
    public DownloadTTNetException(int i, String str) {
        super(i, str);
    }

    public DownloadTTNetException(int i, Throwable th) {
        super(i, th);
    }

    public DownloadTTNetException setRequestLog(String str) {
        setExtraInfo(str);
        return this;
    }

    public String getRequestLog() {
        return getExtraInfo();
    }
}
