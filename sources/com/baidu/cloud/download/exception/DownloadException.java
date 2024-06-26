package com.baidu.cloud.download.exception;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DownloadException extends Exception {
    private int errorCode;
    private String errorMessage;

    public DownloadException() {
    }

    public DownloadException(String str) {
        super(str);
        this.errorMessage = str;
    }

    public DownloadException(int i, String str) {
        super(str);
        this.errorCode = i;
        this.errorMessage = str;
    }

    public DownloadException(int i, Throwable th) {
        super(th);
        this.errorCode = i;
    }

    public DownloadException(String str, Throwable th) {
        super(str, th);
        this.errorMessage = str;
    }

    public DownloadException(int i, String str, Throwable th) {
        super(str, th);
        this.errorCode = i;
        this.errorMessage = str;
    }

    public DownloadException(Throwable th) {
        super(th);
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return super.toString();
    }
}
