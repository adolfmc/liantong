package com.p319ss.android.socialbase.downloader.exception;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.exception.DownloadHttpException */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class DownloadHttpException extends BaseException {
    private final int httpStatusCode;

    public DownloadHttpException(int i, int i2, String str) {
        super(i, str);
        this.httpStatusCode = i2;
    }

    public int getHttpStatusCode() {
        return this.httpStatusCode;
    }
}
