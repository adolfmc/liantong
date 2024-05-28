package com.baidu.cloud.media.download;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
class HttpTSTask implements Runnable {
    protected static final String TAG = "HttpRequestHelper";
    String destFile;
    HttpResponseListener responseListener;
    String url;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static abstract class HttpResponseListener {
        public abstract void onHttpResponse(int i, int i2, int i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class Result {
        int downloadSpeed;
        int result;
        int tsSize;
    }

    public HttpTSTask(String str, String str2, HttpResponseListener httpResponseListener) {
        this.url = str;
        this.destFile = str2;
        this.responseListener = httpResponseListener;
    }

    @Override // java.lang.Runnable
    public void run() {
        Result httpSave = DownloadUtils.httpSave(this.url, this.destFile);
        this.responseListener.onHttpResponse(httpSave.result, httpSave.downloadSpeed, httpSave.tsSize);
    }
}
